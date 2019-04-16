package Game.World;

import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.*;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.Wall;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import Display.UI.UIListener;

public class Map {

	ArrayList<BaseStaticEntity> blocksOnMap;
	ArrayList<BaseStaticEntity> bordersOnMap;
	ArrayList<BaseDynamicEntity> enemiesOnMap;
	ArrayList<BaseDynamicEntity> playersOnMap;
	Handler handler;
	private double bottomBorder;
	private UIListener listener;
	private Background hand;
	private Random rand;
	private Wall walls;
	private int mapBackground;

	public Map(Handler handler) {
		this.handler=handler;
		this.rand = new Random();
		this.hand = new Background(this.handler);
		this.listener = new UIListener( this.handler);
		this.walls = new Wall(this.handler);
		this.blocksOnMap = new ArrayList<>();
		this.bordersOnMap = new ArrayList<>();
		this.enemiesOnMap = new ArrayList<>();
		this.playersOnMap = new ArrayList<>();
		bottomBorder=handler.getHeight();
		this.mapBackground = this.rand.nextInt(6);
	}

	public void addBlock(BaseStaticEntity block){
		blocksOnMap.add(block);
	}
	public void addBorderBlock(BaseStaticEntity block){
		bordersOnMap.add(block);
	}
	public void addEnemy(BaseDynamicEntity entity){
		if(entity instanceof Mario){
			handler.setMario((Mario) entity);
			playersOnMap.add(entity);
			handler.getCamera().setX(handler.getMario().x- (MapBuilder.pixelMultiplier*6));
			handler.getCamera().setY(handler.getMario().y - (MapBuilder.pixelMultiplier*10));
			bottomBorder=handler.getHeight()+handler.getMario().y;
		}else if(entity instanceof FunkyKong){
			handler.setFunkyKong((FunkyKong) entity);
			playersOnMap.add(entity);
			handler.getCameraP2().setX(handler.getFunkyKong().x- (MapBuilder.pixelMultiplier*6));
			handler.getCameraP2().setY(handler.getFunkyKong().y - (MapBuilder.pixelMultiplier*10));
			bottomBorder=handler.getHeight()+handler.getFunkyKong().y;
		}else {
			enemiesOnMap.add(entity);

		}
	}

	public void drawMap(Graphics2D g2) {
		handler.setIsInMap(true);
		Point camLocation = new Point((int)handler.getCamera().getX(), (int)handler.getCamera().getY());
		g2.translate(-camLocation.x, -camLocation.y);
		g2.drawImage(Images.backgrounds2[this.mapBackground], camLocation.x, camLocation.y, this.handler.getWidth(), this.handler.getHeight(),null);
		for (BaseStaticEntity block:blocksOnMap) {
			g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
		}
		for (BaseStaticEntity block:bordersOnMap) {
			g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
		}
		for (BaseDynamicEntity entity:enemiesOnMap) {
			if(entity instanceof Item){
				if(!((Item)entity).used){
					if(entity instanceof Banana) {
						g2.drawImage(((Banana)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					}else {
						g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
					}
				}

			}else if(entity instanceof Goomba && !entity.ded){
				g2.drawImage(((Goomba)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
			}else if(entity instanceof Klaptrap && !entity.ded){
				g2.drawImage(((Klaptrap)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
			}else if(entity instanceof UIPointer ){
				((UIPointer) entity).render(g2);
			}else {
				g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
			}
		}
		for(BaseDynamicEntity entity:playersOnMap) {
			if(entity instanceof Mario) {
				handler.getMario().drawMario(g2);
			}
			else if(entity instanceof FunkyKong) {
				handler.getFunkyKong().drawFK(g2);
			}
		}
		if(this.listener != null && MapBuilder.mapDone) {
			this.listener.render(g2);
			this.hand.render(g2);
			this.walls.render(g2);
		}
		g2.translate(camLocation.x, camLocation.y);
	}

	public void drawMapP2(Graphics2D g2) {
		handler.setIsInMap(true);
		Point camLocation = new Point((int)handler.getCameraP2().getX(), (int)handler.getCameraP2().getY());
		g2.translate(-camLocation.x, -camLocation.y);
		g2.drawImage(Images.backgrounds2[this.mapBackground], camLocation.x, camLocation.y, this.handler.getWidth(), this.handler.getHeight(),null);
		for (BaseStaticEntity block:blocksOnMap) {
			g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
		}
		for (BaseStaticEntity block:bordersOnMap) {
			g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
		}
		for (BaseDynamicEntity entity:enemiesOnMap) {
			if(entity instanceof Item){
				if(!((Item)entity).used){
					if(entity instanceof Banana) {
						g2.drawImage(((Banana)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
					}else {
						g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
					}
				}

			}else if(entity instanceof Goomba && !entity.ded){
				g2.drawImage(((Goomba)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
			}else if(entity instanceof Klaptrap && !entity.ded){
				g2.drawImage(((Klaptrap)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
			}else if(entity instanceof UIPointer ){
				((UIPointer) entity).render(g2);
			}else {
				g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
			}
		}
		for(BaseDynamicEntity entity:playersOnMap) {
			if(entity instanceof Mario) {
				handler.getMario().drawMario(g2);
			}
			else if(entity instanceof FunkyKong) {
				handler.getFunkyKong().drawFK(g2);
			}
		}
		if(this.listener != null && MapBuilder.mapDone) {
			this.listener.render(g2);
			this.hand.render(g2);
			this.walls.render(g2);
		}
		g2.translate(camLocation.x, camLocation.y);
	}    



	public ArrayList<BaseStaticEntity> getBlocksOnMap() {
		return blocksOnMap;
	}
	public ArrayList<BaseStaticEntity> getBordersOnMap() {
		return bordersOnMap;
	}

	public ArrayList<BaseDynamicEntity> getEnemiesOnMap() {
		return enemiesOnMap;
	}

	public ArrayList<BaseDynamicEntity> getPlayersOnMap() {
		return playersOnMap;
	}

	public double getBottomBorder() {
		return bottomBorder;
	}

	public UIListener getListener() {
		return this.listener;
	}
	public Background getHand() {
		return this.hand;
	}
	public Wall getWalls() {
		return this.walls;
	}

	public void reset() {
	}

}
