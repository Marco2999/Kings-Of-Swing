package Game.Entities.DynamicEntities;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Display.UI.UIPointer;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BorderBlock;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.CloudBlock;
import Game.Entities.StaticEntities.MisteryBlock;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;

public class Player extends BaseDynamicEntity {

	protected double velX,velY;

	public String facing = "Left";
	public boolean moving = false;
	public Animation playerLeftAnimation,playerRightAnimation,playerIdleRightAnimation, playerIdleLeftAnimation,
	playerJumpRightAnimation, playerJumpLeftAnimation, playerRunRightAnimation, playerRunLeftAnimation;
	public boolean falling = true, jumping = false,isBig=true,running = false,changeDirrection=false, doublejump=false;
	public int jumpc = 2, djcounter =0, djcountertick =0;
	public double gravityAcc = 0.38;
	int changeDirectionCounter=0;
	public int tickCounter=0;
	public boolean canMove = false;
	public boolean dead = false;
	public int initialX;
	public int initialY;
	public int hitInvin = 0;
	public boolean hit = false;
	public boolean groundpound = false;
	public boolean touchFinish = false;
	public double timeCompleted;
	public int bananaCounter =0;
	public String gameType = "";
	public int tickCountDown = 3600;

	public static int misc = 3;

	public Player(int x, int y, int width, int height, Handler handler, BufferedImage sprite,Animation PLA,Animation PRA,Animation PIRA,
			Animation PILA, Animation PJRA, Animation PJLA, Animation PRRA, Animation PRLA) {
		super(x, y, width, height, handler, sprite);

		playerLeftAnimation=PLA;
		playerRightAnimation=PRA;
		playerIdleRightAnimation=PIRA;
		playerIdleLeftAnimation=PILA;
		playerJumpRightAnimation=PJRA;
		playerJumpLeftAnimation=PJLA;
		playerRunRightAnimation=PRRA;
		playerRunLeftAnimation=PRLA;
		initialX=x;
		initialY=y;
	}

	@Override
	public void tick(){

		if(gameType.equals("")) {
			for(BaseDynamicEntity entity: handler.getMap().getEnemiesOnMap()) {
				if(entity!=null && entity instanceof Item) {
					if(entity instanceof Banana || entity instanceof BananaBunch) {
						gameType="Banana";
					break;
					}
					if(entity instanceof FinishLine) {
						gameType="Race";
					break;
					}
				}
			}
		}

		//DK double jump
		if(!jumping && !falling) {jumpc =2; }
		djcountertick++;
		if(djcountertick > 100) {
			djcounter =1;
			djcountertick =0;
			jumpc=2;
		}

		if (changeDirrection) {
			changeDirectionCounter++;
		}
		if(changeDirectionCounter>=10){
			changeDirrection=false;
			changeDirectionCounter=0;
		}
		if(hit) {
			hitInvin++;
		}
		if(hitInvin==61) {
			hit=false;
			hitInvin=0;
		}

		checkBottomCollisions();
		checkMarioHorizontalCollision();
		checkTopCollisions();
		checkItemCollision();
		if (facing.equals("Left") && moving && !jumping && !falling) {
			playerLeftAnimation.tick();
			playerRunLeftAnimation.tick();
			playerJumpRightAnimation.end();
			playerJumpLeftAnimation.end();
		} else if (facing.equals("Right") && moving && !jumping  && !falling) {
			playerRightAnimation.tick();
			playerRunRightAnimation.tick();
			playerJumpRightAnimation.end();
			playerJumpLeftAnimation.end();
		}
		else if(facing.equals("Left") && !moving && !jumping  && !falling) {
			playerIdleLeftAnimation.tick();
			playerJumpRightAnimation.end();
			playerJumpLeftAnimation.end();
		}
		else if(facing.equals("Right") && !moving && !jumping  && !falling){
			playerIdleRightAnimation.tick();
			playerJumpRightAnimation.end();
			playerJumpLeftAnimation.end();
		}
		else if(jumping || falling) {
			playerJumpLeftAnimation.tick();
			playerJumpRightAnimation.tick();
		}
	}

	private void checkItemCollision() {

		for (BaseDynamicEntity entity : handler.getMap().getEnemiesOnMap()) {
			if (entity != null && getBounds().intersects(entity.getBounds()) && entity instanceof Item) {
				if(entity instanceof Mushroom && !isBig) {
					isBig = true;
					this.y -= 48;
					this.x-= 48;
					this.height += 48;
					this.width +=48;
					setDimension(new Dimension(this.width, this.height));
					((Item) entity).used = true;
					entity.y = -100000;
				}
				else if(entity instanceof Banana) {
					entity.x=1000000;
					bananaCounter++;
				}
				else if(entity instanceof BananaBunch) {
					entity.x=10000;
					bananaCounter+=5;
				}
			}
		}
	}


	public void checkBottomCollisions() {
		Player mario = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();

		Rectangle marioBottomBounds =getBottomBounds();

		if (!mario.jumping) {
			falling = true;
		}

		for (BaseStaticEntity brick : bricks) {
			Rectangle brickTopBounds = brick.getTopBounds();
			if (marioBottomBounds.intersects(brickTopBounds)) {
				if(brick instanceof CloudBlock && jumping) {}
				else if(brick instanceof BorderBlock) {dead=true;}
				else {
					mario.setY(brick.getY() - mario.getDimension().height + 1);
					falling = false;
					velY=0;
				}
			}
		}

		for (BaseDynamicEntity enemy : enemies) {
			Rectangle enemyTopBounds = enemy.getTopBounds();
			if (marioBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof Item) && !hit) {
				if(!enemy.ded) {
					handler.getGame().getMusicHandler().playStomp();
				}
				if(!groundpound && !enemy.ded) {
					falling=false;
					jump();
				}
				enemy.kill();
			}
		}

	}

	public void checkTopCollisions() {
		Player mario = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();

		Rectangle marioTopBounds = mario.getTopBounds();
		for (BaseStaticEntity brick : bricks) {
			Rectangle brickBottomBounds = brick.getBottomBounds();
			if (marioTopBounds.intersects(brickBottomBounds)) {
				mario.setY(brick.getY() + brick.height);
				if(brick instanceof BorderBlock) {dead=true;}
				if(brick instanceof BreakBlock && jumping) {
					brick.x=100000;
					brick.y=100000;
				}
				falling=true;
				velY=0;

			}
		}
	}



	public void checkMarioHorizontalCollision(){
		Player mario = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies = handler.getMap().getEnemiesOnMap();
		boolean toRight = moving && facing.equals("Right");

		Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

		for (BaseStaticEntity brick : bricks) {
			Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
			if (marioBounds.intersects(brickBounds)) {
				if(brick instanceof BorderBlock) {dead=true;}
				velX=0;
				if(toRight)
					mario.setX(brick.getX() - mario.getDimension().width);
				else
					mario.setX(brick.getX() + brick.getDimension().width);
			}
		}

		for(BaseDynamicEntity enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (marioBounds.intersects(enemyBounds) && !(enemy instanceof Item)) {
				if(isBig) {
					hit=true;
					isBig=false;
					this.x+=48;
					this.y+=48;
					this.height-=48;
					this.width-=48;
					setDimension(new Dimension(this.width, this.height));
				}
				else if(hitInvin==0)
					dead=true;
			}
		} 
	}

	public void jump() {
		if(this instanceof Mario && jumpc ==2 ) {
			falling = false;
			jumpc--;
			jumping = true;
			velY = 10;
			handler.getGame().getMusicHandler().playJump();

		}
		else if(this instanceof Mario && jumpc == 1 && djcounter==1) {
			falling = false;
			jumpc--;
			jumping = true;
			velY = 10;
			handler.getGame().getMusicHandler().playJump();
			djcounter--;
		}
		else if(this instanceof FunkyKong && !jumping && !falling){
			jumping=true;
			velY=10;
			handler.getGame().getMusicHandler().playJump();
		}

	}
	public void respawn() {
		dead=false;
		this.x=initialX;
		this.y=initialY;
	}
	public void setRespawn(int x, int y) {
		initialX = x;
		initialY = y;
	}

	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}


}
