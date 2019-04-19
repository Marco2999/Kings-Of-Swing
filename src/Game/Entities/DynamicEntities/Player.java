package Game.Entities.DynamicEntities;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Display.UI.UIPointer;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BorderBlock;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.CloudBlock;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;

public class Player extends BaseDynamicEntity {

	protected double velX,velY;

	public String facing = "Left";
	public boolean moving = false;
	public Animation playerLeftAnimation,playerRightAnimation,playerIdleRightAnimation, playerIdleLeftAnimation,
	playerJumpRightAnimation, playerJumpLeftAnimation, playerRunRightAnimation, playerRunLeftAnimation;
	public boolean falling = true, jumping = false,isBig=true,running = false,changeDirrection=false;
	public double gravityAcc = 0.38;
	int changeDirectionCounter=0;
	public int tickCounter=0;
	public boolean canMove = false;
	public boolean dead = false;
	public int initialX;
	public int initialY;

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
		if (changeDirrection) {
			changeDirectionCounter++;
		}
		if(changeDirectionCounter>=10){
			changeDirrection=false;
			changeDirectionCounter=0;
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
			if (entity != null && getBounds().intersects(entity.getBounds()) && entity instanceof Item && !isBig) {
				isBig = true;
				this.y -= 48;
				this.height += 48;
				setDimension(new Dimension(width, this.height));
				((Item) entity).used = true;
				entity.y = -100000;
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
			if (marioBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof Item)) {
				if(!enemy.ded) {
					handler.getGame().getMusicHandler().playStomp();
				}
				enemy.kill();
				falling=false;
				velY=0;
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
				if(brick instanceof BorderBlock) {dead=true;}
				velY=0;
				mario.setY(brick.getY() + brick.height);
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
			if (marioBounds.intersects(enemyBounds)) {
				if(isBig) {
					isBig = false;
				}
				else {
					dead=true;
				}
				break;
			}
		} 
	}

	public void jump() {
		if(!jumping && !falling){
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

	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}


}
