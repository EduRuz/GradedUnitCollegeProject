package spaceBattleArena;

import java.awt.Color;
import java.awt.Graphics;

public class Shot {
	
	/**the speed at which the shots move, in pixels per frame*/
	private final double shotSpeed = 12;
	/**X and Y location of the shot respectively*/
	private double x,y;
	/**The speed the shot moves across the x and y axis respectively*/
	private double xVelocity, yVelocity;
	/**causes the shot to eventually disappear if it doesn’t hit anything*/
	private int lifeLeft;
	/**The sound for the lazer*/
	private static Sound lazer;
	
	/**creates a shot at the desired location and creates the sound of the shot
	 * @Param double x - X location of the desired shot
	 * @Param double y - Y location of the desired shot
	 * @Param double angle - the angle the shot is to be fired at
	 * @Param double shipXVel - keeps the ship from catching its own shot
	 * @Param double shipYVel - keeps the ship from catching its own shot
	 * @Param double lifeLeft - the number of frames the shots stays alive for
	 * */
	public Shot(double x, double y, double angle, double shipXVel, double shipYVel, int lifeLeft){
		this.x = x;
		this.y = y;
		xVelocity = shotSpeed*Math.cos(angle) + shipXVel;
		yVelocity = shotSpeed*Math.sin(angle) + shipYVel;
		this.lifeLeft = lifeLeft;
		lazer = new Sound();
		lazer.setToLazers();
	}
	
	/**Used to move the shots across the panel*/
	public void move(int scrnWidth, int scrnHeight){
		lifeLeft--; 
		
		x += xVelocity; 
		y += yVelocity;
		
		if(x<0)				
			x += 1100;
		else if(x>1100)
			x -= 1100;
		if(y<0)
			y += 800;
		else if(y > 800)
			y -= 800;
			}
	
	/**Used to draw the shots to screen*/
	public void draw(Graphics g){
		g.setColor(Color.yellow);
		g.fillOval((int)(x-.5),(int)(y-.5), 3, 3);  
		
	}
	
	/**return the x location of the shot*/
	public double getX(){
		return x;
	}
	
	/**return the y location of the shot*/
	public double getY(){
		return y;
	}
	
	/**return the value of the shots life left*/
	public int getLifeLeft(){
		return lifeLeft;
	}
	/**Call to play the lazer sound*/
	public static void lazerSound(){
		lazer.play();
	}
}
