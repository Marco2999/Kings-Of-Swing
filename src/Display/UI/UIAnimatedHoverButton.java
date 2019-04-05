package Display.UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import Resources.Animation;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIAnimatedHoverButton extends UIObject{
	private BufferedImage[] images;
	private ClickListlener clicker;
	Animation anim;
	boolean loop = false;

	public UIAnimatedHoverButton(float x, float y, int width, int height, BufferedImage[] images, int animspeed, ClickListlener clicker ) {
		super(x, y, width, height);
		this.images=images;
		this.clicker=clicker;
		anim = new Animation(animspeed, images);
	}


	@Override
	public void tick() {
		if(!loop) {
			if(hovering && anim.getIndex()<=images.length-1) {
				anim.tick();
			}
			if(hovering && anim.getIndex()==images.length-1) {
				loop=true;
			}
		}
		if(loop) {
			if(hovering && anim.getIndex()<=images.length-1) {
				anim.tick();
			}
			if(hovering && anim.getIndex()==images.length-1) {
				anim.setIndex(7);
			}
		}
		if(!hovering) {
			anim.reset();
		}
	}
	@Override
	public void render(Graphics g) {
		if(active){

		}
		if(hovering){
			g.drawImage(anim.getCurrentFrame(),(int)x,(int)y,width,heith,null);
		}else{
			g.drawImage(images[0],(int)x,(int)y,width,heith,null);

		}
	}


	@Override
	public void onClick()
	{
		clicker.onClick();
	}
}
