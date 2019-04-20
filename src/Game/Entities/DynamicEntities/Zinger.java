package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;

public class Zinger extends BaseDynamicEntity {

    public Animation anim;

    public Animation right;
    public Animation left;
    
    public Zinger(int x, int y, int width, int height, Handler handler) {
        super(x, y, 65, height, handler, Images.ZingerRight[0]);
        anim = new Animation(75,Images.ZingerRight);
        right = new Animation(75, Images.ZingerRight);
		left = new Animation(75, Images.ZingerLeft);
    }
    
    @Override
    public void tick(){
    	
    	
        if(!ded) {
        	
        	if(direction == "Right") {
        		anim = right;
        	}else if(direction == "Left") {
        		anim = left;
        	}
        	
            super.tick();
            anim.tick();
            right.tick();
            left.tick();
            checkHorizontal();
            move();
        }else if(ded&&dedCounter==0){
            y = (int) (y + velY);
            velY = velY + gravityAcc/4;

        }
    }


	@Override
    public void kill() {
		if(direction == "Left") 
		sprite = Images.Zingerdead[0];
		else
		sprite = Images.Zingerdead[1];
        ded=true;
    }
    

}
