package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

import Game.GameStates.State;

public class CheckPointBarP2 extends Item {

	int initX;
	int initY;

	public CheckPointBarP2(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.CheckPointBar);
		initX=x;
		initY=y;
	}

	@Override
	public void tick(){
		if(State.getP2()) {
			if(handler.getFunkyKong().getBounds().intersects(this.getBounds())) {
				handler.getFunkyKong().setRespawn(initX, initY);
				this.x=10000000;
			}
		}	
	}


}
