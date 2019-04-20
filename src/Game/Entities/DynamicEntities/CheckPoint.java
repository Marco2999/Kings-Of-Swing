package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class CheckPoint extends Item {

    public Animation anim;
    public static int misc = 3;
    

    public CheckPoint(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.CheckPoint[0]);
        anim = new Animation(160,Images.CheckPoint);
    }

    @Override
    public void tick(){
    	anim.tick();

    }


}
