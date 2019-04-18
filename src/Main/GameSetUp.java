package Main;

import Display.DisplayScreen;
import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BreakBlock;
import Game.GameStates.DeadState;
import Game.GameStates.GameState;
import Game.GameStates.MenuState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Game.World.Map;
import Game.World.MapBuilder;
import Input.Camera;
import Input.KeyManager;
import Input.MouseManager;
import Resources.Images;
import Resources.MusicHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class GameSetUp implements Runnable {
    public DisplayScreen display;
    public DisplayScreen displayP2;
    public String title;

    private boolean running = false;
    private boolean createdP2 = false;
    private Thread thread;
    public static boolean threadB;

    private BufferStrategy bs;
    private BufferStrategy bsP2;
    private Graphics g;
    private Graphics gP2;
    public UIPointer pointer;

    //Input
    public KeyManager keyManager;
    public MouseManager mouseManager;
    public MouseManager initialmouseManager;

    //Handler
    private Handler handler;

    //States
    public State gameState;
    public State menuState;
    public State pauseState;
    public State deadState;
    
    
    
    
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int screenWidth = gd.getDisplayMode().getWidth();
    int screenHeight = gd.getDisplayMode().getHeight();
    
    //Res.music
    private MusicHandler musicHandler;

    public GameSetUp(String title,Handler handler) {
        this.handler = handler;
        this.title = title;
        threadB=false;

        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        initialmouseManager = mouseManager;
        musicHandler = new MusicHandler(handler);
        handler.setCamera(new Camera());
        handler.setCameraP2(new Camera());
        
    }

    private void init(){
        display = new DisplayScreen(title, handler.width, handler.height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        Images img = new Images();

        musicHandler.restartBackground();

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        pauseState = new PauseState(handler);
        deadState = new DeadState(handler);

        State.setState(menuState);
    }

    public void reStart(){
        gameState = new GameState(handler);
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        //this runs the run method in this  class
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        //initiallizes everything in order to run without breaking
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            //makes sure the games runs smoothly at 60 FPS
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                //re-renders and ticks the game around 60 times per second
                tick();
                render();
                if(createdP2) {
                	renderP2();
                }
                ticks++;
                delta--;
            }
            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    private void tick(){
        //checks for key types and manages them
        keyManager.tick();
        	
        if(musicHandler.ended()){
            musicHandler.restartBackground();
        }

        //game states are the menus
        if(State.getState() != null)
            State.getState().tick();
        if (handler.isInMap()) {
            updateCamera();
            if(createdP2) {
            	updateCameraP2();
            }
        }
        if(State.getState() == menuState) {
        	reStart();
        }
        if(State.getP2() && !createdP2) {
        	displayP2 = new DisplayScreen("Player 2", handler.width, handler.height);
        	display.getFrame().setLocation(screenWidth/2-handler.getWidth(), screenHeight/2-handler.getHeight()/2);
        	displayP2.getFrame().setLocation(screenWidth/2, screenHeight/2-handler.getHeight()/2);
        	displayP2.getFrame().addKeyListener(keyManager);
            displayP2.getFrame().addMouseListener(mouseManager);
            displayP2.getFrame().addMouseMotionListener(mouseManager);
            displayP2.getCanvas().addMouseListener(mouseManager);
            displayP2.getCanvas().addMouseMotionListener(mouseManager);
            createdP2 = true;
        }

    }
    private void updateCamera() {
        Player mario = handler.getMario();
        double marioVelocityX = mario.getVelX();
        double marioVelocityY = mario.getVelY();
        double shiftAmount = 0;
        double shiftAmountY = 0;

        if (marioVelocityX > 0 && mario.getX() - 2*(handler.getWidth()/3) > handler.getCamera().getX()) {
            shiftAmount = marioVelocityX;
        }
        if (marioVelocityX < 0 && mario.getX() +  2*(handler.getWidth()/3) < handler.getCamera().getX()+handler.width) {
            shiftAmount = marioVelocityX;
        }
        if (marioVelocityY > 0 && mario.getY() - 2*(handler.getHeight()/3) > handler.getCamera().getY()) {
            shiftAmountY = marioVelocityY;
        }
        if (marioVelocityX < 0 && mario.getY() +  2*(handler.getHeight()/3) < handler.getCamera().getY()+handler.height) {
            shiftAmountY = -marioVelocityY;
        }
        handler.getCamera().moveCam(shiftAmount,shiftAmountY);
    }
    
    private void updateCameraP2() {
        Player FunkyKong = handler.getFunkyKong();
        double FunkyKongVelocityX = FunkyKong.getVelX();
        double FunkyKongVelocityY = FunkyKong.getVelY();
        double shiftAmount = 0;
        double shiftAmountY = 0;

        if (FunkyKongVelocityX > 0 && FunkyKong.getX() - 2*(handler.getWidth()/3) > handler.getCameraP2().getX()) {
            shiftAmount = FunkyKongVelocityX;
        }
        if (FunkyKongVelocityX < 0 && FunkyKong.getX() +  2*(handler.getWidth()/3) < handler.getCameraP2().getX()+handler.width) {
            shiftAmount = FunkyKongVelocityX;
        }
        if (FunkyKongVelocityY > 0 && FunkyKong.getY() - 2*(handler.getHeight()/3) > handler.getCameraP2().getY()) {
            shiftAmountY = FunkyKongVelocityY;
        }
        if (FunkyKongVelocityX < 0 && FunkyKong.getY() +  2*(handler.getHeight()/3) < handler.getCameraP2().getY()+handler.height) {
            shiftAmountY = -FunkyKongVelocityY;
        }
        handler.getCameraP2().moveCam(shiftAmount,shiftAmountY);
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0,  handler.width, handler.height);

        //Draw Here!
        Graphics2D g2 = (Graphics2D) g.create();

        if(State.getState() != null)
            State.getState().render(g);

        //End Drawing!
        bs.show();
        g.dispose();
    }
    private void renderP2(){
        bsP2 = displayP2.getCanvas().getBufferStrategy();

        if(bsP2 == null){
            displayP2.getCanvas().createBufferStrategy(3);
            return;
        }
        gP2 = bsP2.getDrawGraphics();
        //Clear Screen
        gP2.clearRect(0, 0,  handler.width, handler.height);

        //Draw Here!
        Graphics2D g2 = (Graphics2D) gP2.create();

        if(State.getState() != null)
            State.getState().renderP2(gP2);

        //End Drawing!
        bsP2.show();
        gP2.dispose();
    }
    
    
    
    
    public Map getMap() {
    	Map map = new Map(this.handler);
    	Images.makeMap(0, MapBuilder.pixelMultiplier, 31, 200, map, this.handler);
    	for(int i = 195; i < 200; i++) {
    		map.addBlock(new BreakBlock(0, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
    		map.addBlock(new BreakBlock(30*MapBuilder.pixelMultiplier, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
    	}
    	Mario mario = new Mario(24 * MapBuilder.pixelMultiplier, 196 * MapBuilder.pixelMultiplier, 48,48, this.handler);
    	map.addEnemy(mario);
        map.addEnemy(pointer);
        threadB=true;
    	return map;
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MusicHandler getMusicHandler() {
        return musicHandler;
    }


    public MouseManager getMouseManager(){
        return mouseManager;
    }

}

