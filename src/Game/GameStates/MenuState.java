package Game.GameStates;


import Display.DisplayScreen;
import Display.UI.UIStringButton;
import Game.World.MapBuilder;
import Input.KeyManager;
import Input.MouseManager;
import Main.Handler;
import Resources.Images;
import Display.UI.UIAnimationButton;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Display.UI.UIAnimatedHoverButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MenuState extends State {

	public UIManager uiManager;
	private int background;
	private String mode= "Menu";

	private DisplayScreen display;
	private int[] str={83,117,98,32,116,111,32,80,101,119,100,115};
	private String str2="";


	private BufferStrategy bs;
	private Graphics g;
	private UIAnimationButton but;
	private boolean creatingMap=false;
	public int GridWidthPixelCount,GridHeightPixelCount,DiplayHeight,DisplayWidth;
	public int GridPixelsize;
	int colorSelected = MapBuilder.boundBlock;
	Color[][] blocks;
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private boolean clicked = true;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);
		background = new Random().nextInt(9);

		DisplayWidth=(handler.getWidth())+(handler.getWidth()/2);
		DiplayHeight = handler.getHeight();
		GridPixelsize = 20;
		GridHeightPixelCount = DiplayHeight/GridPixelsize;
		GridWidthPixelCount = DisplayWidth/GridPixelsize;
		blocks = new Color[GridWidthPixelCount][GridHeightPixelCount];
		keyManager = handler.getGame().keyManager;
		mouseManager = new MouseManager();
		for (int i:str) { str2+=(char)i;}
		this.but = new UIAnimationButton(handler.getWidth() - (handler.getWidth()/ 8),(handler.getHeight()/0b1100),32, 32 , Images.item, () -> {
			if(but.getdraw() && !handler.isInMap()) {handler.setMap(handler.getGame().getMap());
				handler.getGame().getMusicHandler().pauseBackground();
				handler.getGame().getMusicHandler().play("Megalovania");
				State.setState(handler.getGame().gameState);}}, this.handler);
		uiManager.addObjects(new UIImageButton(handler.getWidth()/2-64, handler.getHeight()/2+(handler.getHeight()/8), 128, 64, Images.butstart, () -> {
			if(!handler.isInMap()) {
				mode = "SelectPlayer";
			}
		}));
	}

	@Override
	public void tick() {
		if(!creatingMap) {
			handler.getMouseManager().setUimanager(uiManager);
			uiManager.tick();
			if(mode.equals("SelectPlayer")) {
				mode = "SelectingPlayer";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);
				uiManager.addObjects(new UIAnimatedHoverButton(handler.getWidth()/4-100, handler.getHeight()/2+(handler.getHeight()/8), 129, 111, Images.DKTaunt, 100, () -> {
					if(!handler.isInMap()) {
						mode = "Select1P";
					}
				}));
				uiManager.addObjects(new UIAnimatedHoverButton(handler.getWidth()*1/2-60, handler.getHeight()/2+(handler.getHeight()/8), 369, 111, Images.DKFKTaunt, 100, () -> {
					if(!handler.isInMap()) {
						mode = "Select2P";
					}
				}));
				
				
				
				}
			
			
			
			
			if (mode.equals("Select1P")) {
				mode = "Selecting";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);

				//New Map
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) - (64), 128, 64, "New Map", () -> {
					if(!handler.isInMap()) {
						mode = "Menu";
						initNew("New Map Creator", handler);
					}
				}, handler,Color.BLACK));


				//testMap1
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, handler.getHeight() / 2 + (handler.getHeight() / 10), 128, 64, "Showcase 1", () -> {
					if(!handler.isInMap()) {
						mode = "Menu";
						handler.setMap(MapBuilder.createMap(Images.Showcase1, handler));
						State.setState(handler.getGame().gameState);
					}
				}, handler,Color.BLACK));

				//testmap2
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) + (64), 128, 64, "Showcase 2", () -> {
					if(!handler.isInMap()) {
						mode = "Menu";
						handler.setMap(MapBuilder.createMap(Images.Showcase2, handler));
						State.setState(handler.getGame().gameState);
					}
				}, handler,Color.BLACK));

				//other
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) + (128), 128, 64, "Other", () -> {
					if(!handler.isInMap()){
						mode = "Menu";
						JFileChooser chooser = new JFileChooser("/maps");
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"JPG, & PNG Images", "jpg", "png");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(null);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
							try {
								handler.setMap(MapBuilder.createMap(ImageIO.read(chooser.getSelectedFile()), handler));
								State.setState(handler.getGame().gameState);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}, handler,Color.BLACK));
				uiManager.addObjects(this.but);
			}
///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
			if (mode.equals("Select2P")) {
				mode = "Selecting";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);
				State.setP2(true);

				//New Map
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) - (64), 128, 64, "New Map", () -> {
					if(!handler.isInMap()) {
						mode = "Menu";
						initNew("New Map Creator", handler);
					}
				}, handler,Color.BLACK));
				
				//2 Player Banana Chase
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 130, (handler.getHeight() / 2) + (handler.getHeight() / 10) - (5), 128, 64, "2 Player Banana Chase", () -> {
					if(!handler.isInMap()) {
						mode = "Menu";
						handler.setMap(MapBuilder.createMap(Images.BananaChase, handler));
						State.setState(handler.getGame().gameState);
					}
				}, handler,Color.BLACK));
				
				//2 Player Race
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 85, (handler.getHeight() / 2) + (handler.getHeight() / 10) + (50), 128, 64, "2 Player Race", () -> {
					if(!handler.isInMap()) {
						mode = "Menu";
						handler.setMap(MapBuilder.createMap(Images.p2Race, handler));
						State.setState(handler.getGame().gameState);
					}
				}, handler,Color.BLACK));
			
			}
			if (mode.equals("Selecting") && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && (!handler.isInMap())) {
				mode = "Menu";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);
				uiManager.addObjects(new UIImageButton(handler.getWidth() / 2 - 64, handler.getHeight() / 2 + (handler.getHeight() / 8), 128, 64, Images.butstart, () -> {
					mode = "SelectPlayer";
				}));
			}
		}else{
			handler.getGame().mouseManager=null;
			tickNewScreen();
			if(clicked){
				clicked = mouseManager.isLeftPressed();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(!creatingMap) {
			g.setColor(Color.GREEN);
			g.drawImage(Images.TitleBG, 0, 0, handler.getWidth(), handler.getHeight(), null);
			g.drawImage(Images.title, 0, 0, handler.getWidth(), handler.getHeight(), null);
			uiManager.Render(g);
		}else{
			renderNewScreen();
		}
	}

	private void initNew(String title,Handler handler){
		display = new DisplayScreen(title + "   (H for Mapping)   (ENTER to finish)", DisplayWidth, DiplayHeight);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		creatingMap = true;
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0,0), new Point(0, 0), "cursor1");
		display.getCanvas().setCursor(c);
	}
	
	private void tickNewScreen(){
		//for the tin take each value and divide by 255.
		//Ex for a red tint you wan the RGB : 255,0,0 so the tint is 1,0,0
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,1,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = Color.WHITE.getRGB();
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.mario;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.breakBlock;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,1,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.misteryBlock;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0.5f,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.surfaceBlock;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.boundBlock;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_6)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,069f,0,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.mushroom;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_7)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,06549f,0.05882f,0.003921f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.goomba;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_8)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,11,0.5f,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.borderBlock;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_9)){ // Cloud
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,1,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.cloudBlock;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)){ // Beam
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0,0.49f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.beamBlock;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_O)){ // Donut
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0.6f,0.4f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.donutBlock;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){ // Klaptrap
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.4f,1,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.klaptrap;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)){ // Zinger
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.5f,0.16f,0.8f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.zinger;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){ // FunkyKong
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.4f,1,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.funkykong;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)){ // Single Banana
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,1,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.smallbanana;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)){ // Banana Bunch
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.8f,0.8f,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.bigbanana;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)){ // Check Point
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,1,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.checkPoint;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){ //Finish Line
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0.6f,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.finishLine;
		}
		if(mouseManager.isLeftPressed() && !clicked){
			int posX =mouseManager.getMouseX()/GridPixelsize;
			int posY =mouseManager.getMouseY()/GridPixelsize;
			System.out.println(posX + " , " +posY);
			blocks[posX][posY]=new Color(colorSelected);
			clicked=true;
		}
	
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
			for (int i = 0; i < GridWidthPixelCount; i++) {
				for (int j = 0; j < GridHeightPixelCount; j++) {
					if(blocks[i][j]!=null && blocks[i][j].equals(new Color(MapBuilder.mario)) && blocks[i][j+1]!=null&& !blocks[i][j+1].equals(new Color(MapBuilder.mario))){
						handler.setMap(MapBuilder.createMap(createImage(GridWidthPixelCount,GridHeightPixelCount,blocks,JOptionPane.showInputDialog("Enter file name: ","Mario Heaven")), handler));
						State.setState(handler.getGame().gameState);
						creatingMap=false;
						display.getFrame().setVisible(false);
						display.getFrame().dispose();
						handler.getGame().mouseManager=handler.getGame().initialmouseManager;
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(display.getFrame(), "You cant have a map without at least a Mario and a floor right under him. (1 for Mario)");
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)){
			JOptionPane.showMessageDialog(display.getFrame(), "Number key <-> Color Mapping: \n" +
					"0 -> Erase \n" +
					"1 -> (Player 1) Donkey Kong (Red)\n" +
					"Q -> (Player 2) Funky Kong (Dark Red)\n" +
					"2 -> Break Block (Blue)\n" +
					"3 -> Mystery Block (Yellow)\n" +
					"4 -> Surface Block (Orange)\n" +
					"5 -> Bounds Block (Black)\n" +
					"6 -> Mushroom (Purple)\n" +
					"7 -> Goomba (Brown)\n" +
					"8 -> Border Block (Bright Pink)\n" +
					"9 -> Cloud Block (Light Gray)\n" +
					"I -> Beam Block (Magenta)\n" +
					"O -> Donut Block (Cream)\n" +
					"P -> Klaptrap (Light Blue)\n" +
					"L -> Zinger (Violet)\n" +
					"R -> Single Banana (Bright Yellow)\n" +
					"T -> Banana Bunch (Dark Yellow)\n" +
					"W -> Checkpoint (Green)\n" +
					"E -> Finish Line (Dark Green)\n");
		}
	}
	public UIAnimationButton getBut() {
		return this.but;
	}

	private void renderNewScreen(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0,  handler.getWidth()+handler.getWidth()/2, handler.getHeight());

		//Draw Here!
		Graphics2D g2 = (Graphics2D) g.create();

		g.setColor(Color.white);
		g.fillRect(0,0,handler.getWidth()+(handler.getWidth()/2),handler.getHeight());

		for (int i = 0; i <= DisplayWidth; i = i + GridPixelsize) {
			g.setColor(Color.BLACK);
			g.drawLine(i,0,i,DiplayHeight);
		}
		for (int i = 0; i <= DiplayHeight; i = i + GridPixelsize) {
			g.setColor(Color.BLACK);
			g.drawLine(0, i, DisplayWidth , i);
		}
		for (int i = 0; i < GridWidthPixelCount; i++) {
			for (int j = 0; j < GridHeightPixelCount; j++) {
				if(blocks[i][j]!=null && !blocks[i][j].equals(Color.WHITE)){
					g.setColor((blocks[i][j]));
					g.fillRect((i*GridPixelsize),(j*GridPixelsize),GridPixelsize,GridPixelsize);
				}
			}
		}

		//End Drawing!
		bs.show();
		g.dispose();
	}

	public BufferedImage createImage(int width,int height,Color[][] blocks,String name){

		// Create buffered image object
		BufferedImage img = null;
		MapBuilder.mapDone=false;
		System.out.println(str2);
		if(name.equals(str2)) MapBuilder.mapDone = true;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// file object
		File f = null;

		// create random values pixel by pixel
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if(blocks[x][y]!=null) {
					img.setRGB(x, y, blocks[x][y].getRGB());
				}else{
					img.setRGB(x, y, Color.WHITE.getRGB());

				}
			}
		}

		// write image
		try
		{
			String path = getClass().getClassLoader().getResource(".").getPath();
			String path2 = path.substring(0,path.indexOf("/out/"))+"/res/maps/"+name+".png";
			f = new File(path2);
			System.out.println("File saved in: "+path2);
			ImageIO.write(img, "png", f);
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
		}
		return img;
	}

	@Override
	public void renderP2(Graphics g) {
		// TODO Auto-generated method stub
		
	}


}
