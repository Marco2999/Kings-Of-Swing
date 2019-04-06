package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class Klaptrap extends BaseDynamicEntity {

    public Animation anim;
    public Animation anim1;
    public Animation anim2;

    public Klaptrap(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.DKLook[0]);
//        		, new Animation(50, Images.KlapLeft[4]));
        anim = new Animation(50,Images.KlapRight);
        anim1 = new Animation(50,Images.KlapRight);
        anim2 = new Animation(50,Images.KlapLeft);
    }

    @Override
    public void tick(){
    	if(direction.equals("Right")) {
    		anim=anim1;
    	}
    	else {
    		anim=anim2;
    	}
    	
    	
        if(!ded && dedCounter==0) {
            super.tick();
            anim.tick();
            anim1.tick();
            anim2.tick();
            if (falling) {
                y = (int) (y + velY);
                velY = velY + gravityAcc;
                checkFalling();
            }
            checkHorizontal();
            move();
        }else if(ded&&dedCounter==0){
            y++;
            height--;
            setDimension(new Dimension(width,height));
            if (height==0){
                dedCounter=1;
                y=-10000;
            }
        }
    }

    @Override
    public void kill() {
        sprite = Images.goombaDies;
        ded=true;
    }
}
