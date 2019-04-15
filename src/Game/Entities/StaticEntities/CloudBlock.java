package Game.Entities.StaticEntities;

import java.awt.Rectangle;

import Main.Handler;
import Resources.Images;

public class CloudBlock extends BaseStaticEntity {

    public CloudBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height,handler, Images.cloudBlock);
    }
    @Override
    public Rectangle getLeftBounds(){
        return new Rectangle(0, 0, 0, 0);
    }
    @Override
    public Rectangle getRightBounds(){
        return new Rectangle(0, 0, 0, 0);
    }
    @Override
    public Rectangle getBottomBounds(){
        return new Rectangle(0, 0, 0, 0);
    }

}
