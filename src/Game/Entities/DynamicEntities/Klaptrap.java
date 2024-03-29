package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;

public class Klaptrap extends BaseDynamicEntity {

    public Animation anim;

    public Animation right;
    public Animation left;
    
    public Klaptrap(int x, int y, int width, int height, Handler handler) {
        super(x, y, 65, height, handler, Images.KlapRight[0]);
        anim = new Animation(50,Images.KlapRight);
        right = new Animation(50, Images.KlapRight);
		left = new Animation(50, Images.KlapLeft);
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
            if (falling) {
                y = (int) (y + velY);
                velY = velY + gravityAcc;
                checkFalling();
            }
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
		sprite = Images.KlapDead[0];
		else
		sprite = Images.KlapDead[1];
        ded=true;
    }
    

}
