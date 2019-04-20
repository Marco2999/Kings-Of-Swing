package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class FinishLine extends Item {
    public FinishLine(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.FinishLine);
    }

    @Override
    public void tick(){

    }


}
