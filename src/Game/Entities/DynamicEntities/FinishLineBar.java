package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class FinishLineBar extends Item {
	int initX;
	int initY;

    public FinishLineBar(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.FinishLineBar);
        initX = x;
        initY= y;
        direction = "Up";
    }

    @Override
    public void tick(){
    	if(this.y==initY) {
    		direction = "Up";
    	}
    	if(this.y==initY-228) {
    		direction = "Down";
    	}
    	if(direction.equals("Up")) {
    		y-=3;
    	}
    	if(direction.equals("Down")) {
    		y+=3;
    	}
    	
    	if(handler.getMario().getBounds().intersects(this.getBounds())) {
    		handler.getMario().touchFinish = true;
    		handler.getMario().timeCompleted = (double) (handler.getMario().tickCounter-120)/60;
    		this.x=1000000;
    	}
    	
    	
    	
    }


}
