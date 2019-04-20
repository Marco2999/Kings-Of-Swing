package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class CheckPointBar extends Item {

	int initX;
	int initY;

	public CheckPointBar(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.CheckPointBar);
		initX=x;
		initY=y;
	}

	@Override
	public void tick(){
		if(handler.getMario().getBounds().intersects(this.getBounds())) {
			handler.getMario().setRespawn(initX, initY);
			this.x=100000;
		}
	}
}
