package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Banana extends Item {
	public Animation anim;
	
    public Banana(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.smallbananas[0]);
        anim = new Animation(160,Images.smallbananas);
    }

    @Override
    public void tick(){

    	if(!used) {
        	super.tick();
        	anim.tick();
    	}
    
    }

}
