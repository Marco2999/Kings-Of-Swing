package Game.GameStates;

import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public abstract class State {

    private static State currentState = null;
    private static boolean P2 = false;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }
    
    public static void setP2(boolean b) {
    	P2 = b;
    }
    
    public static boolean getP2() {
    	return P2;
    }

    //CLASS

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    public abstract void renderP2(Graphics g);

}

