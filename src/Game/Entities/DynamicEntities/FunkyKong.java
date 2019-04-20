package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FunkyKong extends Player{

	public boolean hit = false;
	public boolean grabbed =false;
	public int cooldown = 0;

	public FunkyKong(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.FKIdleRight[0]
				,new Animation(100,Images.FKWalkLeft)
				, new Animation(100,Images.FKWalkRight)
				, new Animation(175,Images.FKIdleRight)
				, new Animation(175,Images.FKIdleLeft)
				, new Animation(50,Images.FKJumpRight)
				, new Animation(50,Images.FKJumpLeft)
				, new Animation(50,Images.FKWalkRight)
				, new Animation(50,Images.FKWalkLeft));

		if(isBig){
			this.y-=48;
			this.height+=48;
			this.x-=48;
			this.width+=48;
			setDimension(new Dimension(this.width, this.height));
		}
	}
	@Override
	public void tick(){
		if(!touchFinish)
			tickCounter++;
		else 
			canMove=false;

		if(gameType.equals("Banana")){
			if(tickCountDown>0 && tickCounter>120)
				tickCountDown--;
			else
				canMove=false;
		}
		if(tickCounter==120) {canMove=true;}
		if(!grabbed && !dead) {
			super.tick();
			if (!this.hit) {
				if (handler.getKeyManager().jumpbutt2 && !handler.getKeyManager().up2 && !handler.getKeyManager().down2 && canMove) {
					this.jump();
				}

				if (handler.getKeyManager().right2 && !handler.getKeyManager().up2 && !handler.getKeyManager().down2 && canMove) {
					if (handler.getKeyManager().runbutt2) {
						velX = 6;
						running = true;
					} else {
						velX = 3;
						running = false;
					}
					if (facing.equals("Left")) {
						changeDirrection = true;
					}
					facing = "Right";
					moving = true;
				} else if (handler.getKeyManager().left2 && !handler.getKeyManager().up2 && !handler.getKeyManager().down2 && canMove) {
					if (handler.getKeyManager().runbutt2) {
						velX = -6;
						running = true;
					} else {
						velX = -3;
						running = false;
					}
					if (facing.equals("Right")) {
						changeDirrection = true;
					}
					facing = "Left";
					moving = true;
				} else if(!falling && !jumping){
					velX = 0;
					moving = false;
				}
				if (jumping && velY <= 0) {
					jumping = false;
					falling = true;
				} else if (jumping) {
					velY = velY - gravityAcc;
					y = (int) (y - velY);
				}

				if((falling || jumping) && handler.getKeyManager().down2 && tickCounter-cooldown>100) {
					jumping = false;
					falling = true;
					groundpound = true;
					cooldown=tickCounter;
				}

				if(groundpound) {
					velY = velY + gravityAcc*2;
					y = (int) (y + velY);
				}

				if(groundpound && !falling) {
					groundpound = false;
					handler.getCameraP2().shakeCamera();
					if(Math.abs(handler.getMario().getX()-this.getX())<handler.getWidth()*3/4 &&
							Math.abs(handler.getMario().getY()-this.getY())<handler.getHeight()*3/4) {
						handler.getCamera().shakeCamera();
					}
				}

				if (falling) {
					y = (int) (y + velY);
					velY = velY + gravityAcc;
				}
				x += velX;
			} else {
				this.setX(this.getX() - 30);
				this.setY(this.getY() - 30);
			}

		}
	}

	public void drawFK(Graphics2D g2) {
		if(!grabbed && !dead) {
			if (!changeDirrection) {
				if (handler.getKeyManager().up2) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.FKLook[1], x, y, width, height, null);
					} else {
						g2.drawImage(Images.FKLook[0], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down2 || groundpound) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.FKLook[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.FKLook[2], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving && running) {
						g2.drawImage(playerRunLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Left") && moving && !running) {
						g2.drawImage(playerLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Left") && !moving) {
						g2.drawImage(playerIdleLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving && running) {
						g2.drawImage(playerRunRightAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving && !running) {
						g2.drawImage(playerRightAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(playerIdleRightAnimation.getCurrentFrame(), x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(playerJumpLeftAnimation.getCurrentFrame(), x, y, width, height, null);
						} else {
							g2.drawImage(playerJumpRightAnimation.getCurrentFrame(), x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(playerJumpLeftAnimation.getCurrentFrame(), x, y, width, height, null);
						} else {
							g2.drawImage(playerJumpRightAnimation.getCurrentFrame(), x, y, width, height, null);
						}
					}
				}
			} else {
				if (!running || jumping || falling) {
					changeDirrection = false;
					changeDirectionCounter = 0;
					drawFK(g2);
				}
				if (facing.equals("Right")) {
					g2.drawImage(Images.FKTurn[0], x, y, width, height, null);
				} else {
					g2.drawImage(Images.FKTurn[1], x, y, width, height, null);
				}
			}
		}
	}

	public boolean getHit() {
		return this.hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}
}
