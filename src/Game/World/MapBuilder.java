package Game.World;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Game.Entities.DynamicEntities.Banana;
import Game.Entities.DynamicEntities.BananaBunch;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.CheckPoint;
import Game.Entities.DynamicEntities.CheckPointBar;
import Game.Entities.DynamicEntities.CheckPointBarP2;
import Game.Entities.DynamicEntities.FinishLine;
import Game.Entities.DynamicEntities.FinishLineBar;
import Game.Entities.DynamicEntities.FinishLineBarP2;
import Game.Entities.DynamicEntities.FunkyKong;
import Game.Entities.DynamicEntities.Goomba;
import Game.Entities.DynamicEntities.Klaptrap;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Mushroom;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BeamBlock;
import Game.Entities.StaticEntities.BorderBlock;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.CloudBlock;
import Game.Entities.StaticEntities.DonutBlock;
import Game.Entities.StaticEntities.MisteryBlock;
import Game.Entities.StaticEntities.SurfaceBlock;
import Main.Handler;
import Resources.Images;

public class MapBuilder {

	public static int pixelMultiplier = 48;
	public static int boundBlock = new Color(0,0,0).getRGB();
	public static int mario = new Color(255,0,0).getRGB();
	public static int surfaceBlock = new Color(255,106,0).getRGB();
	public static int breakBlock = new Color(0,38,255).getRGB();
	public static int misteryBlock = new Color(255,216,0).getRGB();
	public static int mushroom = new Color(178,0,255).getRGB();
	public static int goomba = new Color(167,15,1).getRGB();
	public static int borderBlock = new Color(255,102,255).getRGB(); // 
	public static int cloudBlock = new Color(194,194,194).getRGB();	// Light Gray
	public static int beamBlock = new Color(255,0,127).getRGB(); // Magenta
	public static int donutBlock = new Color(255,178,102).getRGB(); // Light Orange
	public static int klaptrap = new Color(102,255,255).getRGB(); // Baby Blue
	public static int funkykong = new Color(130,35,0).getRGB(); // Baby Blue
	public static int smallbanana = new Color(255, 255,0).getRGB(); // Bright Yellow
	public static int bigbanana = new Color(204, 204,0).getRGB(); // Dark yellow
	public static int checkPoint = new Color(255, 0, 131).getRGB(); // Pink
	public static int finishLine = new Color(242, 0, 255).getRGB(); //Bright Purple
	public static boolean mapDone = false;

	public static Map createMap(BufferedImage mapImage, Handler handler){
		Map mapInCreation = new Map(handler);
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				if(currentPixel == boundBlock){
					BaseStaticEntity BoundBlock = new BoundBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BoundBlock);
				}else if(currentPixel == mario){
					BaseDynamicEntity Mario = new Mario(xPos+8,yPos+8,pixelMultiplier-8,pixelMultiplier-8,handler);
					mapInCreation.addEnemy(Mario);
				}else if(currentPixel == surfaceBlock){
					BaseStaticEntity SurfaceBlock = new SurfaceBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(SurfaceBlock);
				}else if(currentPixel == breakBlock){
					BaseStaticEntity BreakBlock = new BreakBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BreakBlock);
				}else if(currentPixel == misteryBlock){
					BaseStaticEntity MisteryBlock = new MisteryBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(MisteryBlock);
				}else if(currentPixel == mushroom){
					BaseDynamicEntity Mushroom = new Mushroom(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Mushroom);
				}else if(currentPixel == goomba){
					BaseDynamicEntity Goomba = new Goomba(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Goomba);
				}else if(currentPixel == borderBlock){
					BaseStaticEntity BorderBlock = new BorderBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BorderBlock);
				}else if(currentPixel == cloudBlock){
					BaseStaticEntity cloudBlock = new CloudBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(cloudBlock);
				}else if(currentPixel == beamBlock){
					BaseStaticEntity beamBlock = new BeamBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(beamBlock);
				}else if(currentPixel == donutBlock){
					BaseStaticEntity donutBlock = new DonutBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(donutBlock);
				}else if(currentPixel == klaptrap){
					BaseDynamicEntity Klaptrap = new Klaptrap(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Klaptrap);
				}else if(currentPixel == funkykong){
					BaseDynamicEntity FunkyKong = new FunkyKong(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(FunkyKong);
				}else if(currentPixel == smallbanana){
					BaseDynamicEntity Banana = new Banana(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Banana);
				}else if(currentPixel == bigbanana){
					BaseDynamicEntity BananaBunch = new BananaBunch(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(BananaBunch);
				}else if(currentPixel == checkPoint) {
					BaseDynamicEntity CheckPoint = new CheckPoint(xPos,yPos-120, pixelMultiplier*2, 168,handler);
					mapInCreation.addEnemy(CheckPoint);
					BaseDynamicEntity CheckPointBar = new CheckPointBar(xPos+15,yPos,60, 9,handler);
					mapInCreation.addEnemy(CheckPointBar);
					BaseDynamicEntity CheckPointBarP2 = new CheckPointBarP2(xPos+15,yPos,60, 9,handler);
					mapInCreation.addEnemy(CheckPointBarP2);
				}else if(currentPixel == finishLine) {
					BaseDynamicEntity FinishLine = new FinishLine(xPos,yPos-282, 168, 330,handler);
					mapInCreation.addEnemy(FinishLine);
					BaseDynamicEntity FinishLineBar = new FinishLineBar(xPos+24,yPos,96, 24,handler);
					mapInCreation.addEnemy(FinishLineBar);
					BaseDynamicEntity FinishLineBarP2 = new FinishLineBarP2(xPos+24,yPos,96, 24,handler);
					mapInCreation.addEnemy(FinishLineBarP2);
				}
			}

		}
		if(mapDone) {
			Images.makeMap(50, pixelMultiplier, mapImage.getWidth(), 100, mapInCreation, handler);
			for(int i = 96; i < 101; i++) {
				mapInCreation.addBlock(new BreakBlock(49*pixelMultiplier, i*pixelMultiplier,48,48,handler));
				mapInCreation.addBlock(new BreakBlock(54*pixelMultiplier, i*pixelMultiplier,48,48,handler));
			}
		}
		return mapInCreation;
	}

}
