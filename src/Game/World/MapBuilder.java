package Game.World;

import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.FunkyKong;
import Game.Entities.DynamicEntities.Goomba;
import Game.Entities.DynamicEntities.Klaptrap;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Mushroom;
import Game.Entities.StaticEntities.*;
import Main.Handler;
import Resources.Images;
import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.image.BufferedImage;

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
					BaseDynamicEntity Mario = new Mario(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
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
					mapInCreation.addBorderBlock(BorderBlock);
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
