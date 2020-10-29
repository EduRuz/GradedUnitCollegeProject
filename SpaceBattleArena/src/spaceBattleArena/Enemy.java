package spaceBattleArena;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	/**X and y location of the enemy*/
	private double x, y; 
	/**Size of the enemy*/
	private double radius;
	/**Speed at which the enemy moves across the screen*/
	private int velocity;
	/**int used to determine fire rate*/
	private int shotDelay;
	/**int to control the shotDelay*/
	private int shotDelayLeft;
	
	/**
	 * Creates a non player controlled Polygon ship for the player to defeat
	 * @param double x: x coordinate
	 * @param double y: y coordinate
	 * @param double angle: The angle the ship is facing (Radians)
	 * @param double radius: Size of the enemy
	 * @param double velocity: How fast the ship moves across the screen
	 * @param int shotDelay: The delay between the enemy shots
	 **/
	public Enemy(double x, double y, double angle, double radius, int velocity, int shotDelay){
		
		
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.velocity = velocity;
		this.shotDelay = shotDelay;
		
		
		
	}
	/**Draws the enemy to the screen
	 * @param Graphics g - the Graphics to be painted on*/
	public void Draw(Graphics g){
		g.setColor(Color.red);
		g.fillOval((int)(x-radius+.5),(int)(y-radius+.5),(int)(2*radius),(int)(2*radius));
	}
	
	/**Called every frame to move the enemy ship across the screen*/
	public void Move(double PlayerX, double PlayerY){
		
		if(shotDelayLeft > 0)
			shotDelayLeft--;
		
		if (PlayerX < this.x && PlayerY < this.y){
			this.x-=velocity;
			this.y-=velocity;}
		else if (PlayerX < this.x && PlayerY > this.y){
			this.x-=velocity;
			this.y+=velocity;}
		else if (PlayerX > this.x && PlayerY > this.y){
			this.x+=velocity;
			this.y+=velocity;}
		else if (PlayerX > this.x && PlayerY < this.y){
			this.x+=velocity;
			this.y-=velocity;
		}
	}

	/**Checks if enemy has collided with the player*/
	public boolean shipCollision(Ship ship){
		if(Math.pow(radius+ship.getRadius(),2) > Math.pow(ship.getX()-x,2)+
					Math.pow(ship.getY()-y,2) && ship.isActive())
				return true;
			return false; 
		}
	
	/**Checks if the enemy has collided with a shot
	 * @Param Shot shot - pass the shot you want to check*/
	public boolean shotCollision(Shot shot){
		//The reason for the break down of the original code is in
		//an attempt to single out the bug and locate the source
		double ba;
		try{
			 ba = shot.getX();
		}catch(NullPointerException e ){
		      return false;}
		//double ba = shot.getX();
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
	
	/**Returns a new shot and sets the shot delay
	 * @Param Angle the shot will be fire along*/
	public Shot shoot(double angle){
		shotDelayLeft = shotDelay;
		return new Shot(x, y, angle, velocity, velocity, 40);
	}
	
	/**Checks to see if the enemy can fire again*/
	public boolean canShoot(){
		if(shotDelayLeft > 0)
			return false; 
		else
			return true;
		}
	
	/**Get the enemies Y location*/
	public double getY(){
		return this.y;
	}
	
	/**Get the enemies X location*/
	public double getX(){
		return this.x;
	}
}
