package Game.GameStates;

import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

import Display.UI.UIListener;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {
	int counter =0;
	boolean startCounter = false;

	public GameState(Handler handler){
		super(handler);
		handler.getGame().pointer = new UIPointer(28 * MapBuilder.pixelMultiplier,197 * MapBuilder.pixelMultiplier,128,128,handler);

	}

	@Override
	public void tick() {
		counter++;
		if(counter>=120) {handler.getMario().canMove = true;}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
			State.setState(handler.getGame().pauseState);
		}


		if(handler.getMap().getListener() != null && MapBuilder.mapDone) {
			handler.getMap().getListener().tick();
			handler.getMap().getHand().tick();
			handler.getMap().getWalls().tick();

		}
		for (BaseDynamicEntity entity:handler.getMap().getEnemiesOnMap()) {
			entity.tick();
		}
		for(BaseDynamicEntity entity:handler.getMap().getPlayersOnMap()) {
			entity.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		handler.getMap().drawMap(g2);
		if(counter<60) {
			g2.drawImage(Images.ready,handler.getWidth()/2-Images.ready.getWidth(),
					handler.getHeight()/2-Images.ready.getHeight(), Images.ready.getWidth()*2, Images.ready.getHeight()*2,null);
		}
		else if(counter<120) {
			g2.drawImage(Images.go,handler.getWidth()/2-Images.go.getWidth(),
					handler.getHeight()/2-Images.go.getHeight(), Images.go.getWidth()*2, Images.go.getHeight()*2,null);
		}
	}
	public void renderP2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		handler.getMap().drawMapP2(g2);
		if(counter<60) {
			g2.drawImage(Images.ready,handler.getWidth()/2-Images.ready.getWidth(),
					handler.getHeight()/2-Images.ready.getHeight(), Images.ready.getWidth()*2, Images.ready.getHeight()*2,null);
		}
		else if(counter<120) {
			g2.drawImage(Images.go,handler.getWidth()/2-Images.go.getWidth(),
					handler.getHeight()/2-Images.go.getHeight(), Images.go.getWidth()*2, Images.go.getHeight()*2,null);
		}
	}


}
