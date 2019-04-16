package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class BananaBunch extends Item {
	public Animation anim;
	
    public BananaBunch(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.bigbananas[0]);
        anim = new Animation(160,Images.bigbananas);
    }

    @Override
    public void tick(){
        if(!used) {
            anim.tick();
        }
    }

}
