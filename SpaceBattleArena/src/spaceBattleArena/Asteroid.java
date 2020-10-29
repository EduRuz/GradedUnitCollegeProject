package spaceBattleArena;

import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {

	/**The x and y location of the ship respectively*/
	private double x, y;
	/**The Velocity the asteroid move along the X axis and Y axis respectively*/
	private double xVelocity, yVelocity;
	/**The size of the asteroid*/
	private double radius;
	/**The number of hits to destroy the asteroid*/
	private int hitsLeft; 
	
	/**Creates the asteroid
	 * @param double x - X location of the asteroid
	 * @param double y - Y location of the asteroid
	 * @param double radius - the size of the asteroid
	 * @param double minVelocity - the minimum speed the asteroid will move across the screen
	 * @param double maxVelocity - the maximum speed the asteroid will move across the screen
	 */
	public Asteroid(double x, double y, double radius, double minVelocity, double maxVelocity){
		
	this.x=x;
	this.y=y;
	this.radius=radius;

		
	double vel = minVelocity + Math.random()*(maxVelocity-minVelocity), dir=2*Math.PI*Math.random();
	xVelocity=vel*Math.cos(dir);
	yVelocity=vel*Math.sin(dir);
	}
	
	/**Moves the asteroid on the screen*/
	public void move(){
	
	x+=xVelocity; 
	y+=yVelocity; 
	
	if(x<0-radius)
		x+=1100+2*radius;
	else if(x>1100+radius)
		x-=1100+2*radius;
	if(y<0-radius)
		y+=800+2*radius;
	else if(y>800+radius)
		y-=800+2*radius; 
		
	}
	
	/**Draws the asteroid to screen
	 * @param Graphics g - the Graphics to be painted on*/
	public void draw(Graphics g){
		g.setColor(Color.gray);
		g.fillOval((int)(x-radius+.5),(int)(y-radius+.5),(int)(2*radius),(int)(2*radius));
	}
	
	/**Returns the hits left*/
	public int getHitsLeft(){
		return hitsLeft;
	}
	
	/**Checks to see if the asteroid has collided with the player
	 * @param Ship ship - the ship to collision detect with*/
	public boolean shipCollision(Ship ship){
	if(Math.pow(radius+ship.getRadius(),2) > Math.pow(ship.getX()-x,2)+
				Math.pow(ship.getY()-y,2) && ship.isActive())
			return true;
		return false; 
	}
	
	/**Checks to see if the asteroid has collided with a shot
	 * @param Shot shot - the shot to collision detect with*/
	public boolean shotCollision(Shot shot){
		//The reason for the break down of the original code is in
		//an attempt to single out the bug and locate the source
		double ba;
		
		try{
			 ba = shot.getX();
		}catch(NullPointerException e ){
		      return false;}
		
		double ca = shot.getY();
		
		
		double a = (Math.pow(radius, 2));
		double b = Math.pow(ba - x, 2);
		double c = Math.pow(ca - y, 2);
		
		
		if(a > (b + c)){
			return true;}
		else 
			return false;
//		if(Math.pow(radius,2) > Math.pow(shot.getX()-x,2) + Math.pow(shot.getY()-y,2))
//		 return true;
//		return false; 
		}
	
}
