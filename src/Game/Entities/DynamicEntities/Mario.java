package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Mario extends Player{

	private boolean hit = false;
	public boolean grabbed =false;

	public Mario(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.DKIdleRight[0]
                , new Animation(100,Images.DKWalkLeft)
                , new Animation(100,Images.DKWalkRight)
                , new Animation(175,Images.DKIdleRight)
                , new Animation(175,Images.DKIdleLeft)
                , new Animation(50,Images.DKJumpRight)
                , new Animation(50,Images.DKJumpLeft)
                , new Animation(50,Images.DKWalkRight)
                , new Animation(50,Images.DKWalkLeft));

		if(isBig){
			this.y-=48;
			this.height+=48;
			setDimension(new Dimension(width, this.height));
		}
	}
	@Override
	public void tick(){
		if(!grabbed) {
			super.tick();
			if (!this.hit) {
				if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !handler.getKeyManager().up && !handler.getKeyManager().down) {
					this.jump();
				}

				if (handler.getKeyManager().right && !handler.getKeyManager().up && !handler.getKeyManager().down) {
					if (handler.getKeyManager().runbutt) {
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
				} else if (handler.getKeyManager().left && !handler.getKeyManager().up && !handler.getKeyManager().down) {
					if (handler.getKeyManager().runbutt) {
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
				} else {
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

	public void drawMario(Graphics2D g2) {
		if(!grabbed) {

			if (!changeDirrection) {
				if (handler.getKeyManager().up) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.DKLook[1], x, y, width, height, null);
					} else {
						g2.drawImage(Images.DKLook[0], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.DKLook[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.DKLook[2], x, y, width, height, null);
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
				if (!running) {
					changeDirrection = false;
					changeDirectionCounter = 0;
					drawMario(g2);
				}
				if (facing.equals("Right")) {
					g2.drawImage(Images.DKTurn[0], x, y, width, height, null);
				} else {
					g2.drawImage(Images.DKTurn[1], x, y, width, height, null);
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
