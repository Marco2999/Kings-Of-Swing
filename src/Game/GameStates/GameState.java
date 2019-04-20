package Game.GameStates;

import Display.UI.UIPointer;
import Display.UI.UIStringButton;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

import Display.UI.UIListener;
import Display.UI.UIManager;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {

	private UIManager uiManager;

	public GameState(Handler handler){
		super(handler);
		handler.getGame().pointer = new UIPointer(28 * MapBuilder.pixelMultiplier,197 * MapBuilder.pixelMultiplier,128,128,handler);
		uiManager = new UIManager(handler);

		uiManager.addObjects(new UIStringButton(56, 223, 128, 64, "Respawn", () -> {
			if(handler.getMario().dead || handler.getFunkyKong().dead) {
				handler.getMouseManager().setUimanager(null);
				if(handler.getMario().dead)
					handler.getMario().respawn();
				else
					handler.getFunkyKong().respawn();
			}},handler,Color.WHITE));

		uiManager.addObjects(new UIStringButton(56, 223+(64+16), 128, 64, "Options", () -> {
			if(handler.getMario().dead  || handler.getFunkyKong().dead) {
				handler.getMouseManager().setUimanager(null);
				handler.setIsInMap(false);
				State.setState(handler.getGame().menuState);
			}},handler,Color.WHITE));

		uiManager.addObjects(new UIStringButton(56, (223+(64+16))+(64+16), 128, 64, "Title", () -> {
			if(handler.getMario().dead  || handler.getFunkyKong().dead) {
				handler.getMouseManager().setUimanager(null);
				handler.setIsInMap(false);
				State.setState(handler.getGame().menuState);
			}},handler,Color.WHITE));

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

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

		if(!handler.getMario().dead) {
			handler.getMap().drawMap(g2);
			if(handler.getMario().tickCounter<60) {
				g2.drawImage(Images.ready,handler.getWidth()/2-Images.ready.getWidth()*3/2,
						handler.getHeight()/2-Images.ready.getHeight()*3/2, Images.ready.getWidth()*3, Images.ready.getHeight()*3,null);
			}
			else if(handler.getMario().tickCounter<120) {
				g2.drawImage(Images.go,handler.getWidth()/2-Images.go.getWidth()*3/2,
						handler.getHeight()/2-Images.go.getHeight()*3/2, Images.go.getWidth()*3, Images.go.getHeight()*3,null);
			}
			if(handler.getMario().touchFinish) {
				g2.drawImage(Images.goal,handler.getWidth()/2-Images.go.getWidth()*3/2,
						handler.getHeight()/2-Images.go.getHeight()*3/2, Images.go.getWidth()*3, Images.go.getHeight()*3,null);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 100));
				g2.setColor(Color.red);
				g2.drawString(String.format("%.2f", handler.getMario().timeCompleted) + " s", handler.getWidth()/2-100, handler.getHeight()/2+150);
			} 
		}
		else {
			g.drawImage(Images.Pause,0,0,handler.getWidth(),handler.getHeight(),null);
			uiManager.Render(g);
		}
	}
	public void renderP2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		if(!handler.getFunkyKong().dead) {
			handler.getMap().drawMapP2(g2);
			if(handler.getFunkyKong().tickCounter<60) {
				g2.drawImage(Images.ready,handler.getWidth()/2-Images.ready.getWidth()*3/2,
						handler.getHeight()/2-Images.ready.getHeight()*3/2, Images.ready.getWidth()*3, Images.ready.getHeight()*3,null);
			}
			else if(handler.getFunkyKong().tickCounter<120) {
				g2.drawImage(Images.go,handler.getWidth()/2-Images.go.getWidth()*3/2,
						handler.getHeight()/2-Images.go.getHeight()*3/2, Images.go.getWidth()*3, Images.go.getHeight()*3,null);
			}
			if(handler.getFunkyKong().touchFinish) {
				g2.drawImage(Images.goal,handler.getWidth()/2-Images.go.getWidth()*3/2,
						handler.getHeight()/2-Images.go.getHeight()*3/2, Images.go.getWidth()*3, Images.go.getHeight()*3,null);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 100));
				g2.setColor(Color.red);
				g2.drawString(String.format("%.2f", handler.getFunkyKong().timeCompleted) + " s", handler.getWidth()/2-100, handler.getHeight()/2+150);
			} 
		}
		else {
			g.drawImage(Images.Pause,0,0,handler.getWidth(),handler.getHeight(),null);
			uiManager.Render(g);
		}
	}

}
