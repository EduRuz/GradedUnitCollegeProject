package spaceBattleArena;

import java.awt.Color;
import java.awt.Graphics;

public class Ship {
	
/**Constant double used for the points that create the polygon player ship*/
private final double[] origXPts = {14, -10, -6, -10}, origYPts = {0, -8, 0, 8};
/**Constant double used for the points that create the polygon flames for the players ship*/
private final double[] origFlameXPts = {-6, -23, -6}, origFlameYPts = {-3,0,3};
/**Radius of the circle used to approximate the ship*/
private final int radius =6;
/**double used for movement*/
private double x, y;
/**Controls the angle the ship is facing*/
private double angle;
/**Controls how fast the x and y location of the player change when it moves in the Game*/
private double xVelocity, yVelocity;
/**Controls how quick the x and velocity increase*/
private double acceleration;
/**Controls the rate at which the ship slows to a stop*/
private double velocityDecay;
/**The speed at which the ship rotates*/
private double rotationalSpeed;
/**True if the ship is turning, called when a button is pressed 
 * and set to true, set to false when the button is released */
private boolean turningLeft, turningRight;
/**True if the ship is accelerating, called when a button is pressed
 * and set to true, set to false when the button is released*/
private boolean accelerating;
/**True if the ship is paused, false otherwise*/
private boolean active;
/**int to store location of ship and its flame*/
private int[] xPts, yPts, flameXPts, flameYPts;
/**int used to determine how much time between each shot*/
private int shotDelay;
/**int to control how much delay remains*/
private int shotDelayLeft;
/**Private to control how much health he player has*/
private int pHealth;

	/**
	 * Creates a Polygon ship for the player to control
	 * @param double x: x coordinate
	 * @param double y: y coordinate
	 * @param double angle: The angle the ship is facing (Radians)
	 * @param double acceleration: How fast the ship accelerates
	 * @param double velocityDecay: How quickly the ship velocity slows to zero
	 * @param double rotationalSpeed: How fast the ship rotates
	 * @param int shotDelay: The delay between the players shots
	 */
	public Ship(double x, double y, double angle, double acceleration, double velocityDecay, double rotationalSpeed, int shotDelay){

		this.x = x;
		this.y = y;
		this.angle = angle;
		this.acceleration = acceleration;
		this.velocityDecay = velocityDecay;
		this.rotationalSpeed = rotationalSpeed;
		xVelocity = 0; 
		yVelocity = 0;
		turningLeft = false; 
		turningRight = false;
		accelerating = false; 
		active = true; 
		flameXPts = new int[3];
		flameYPts = new int[3];
		xPts = new int[4];  
		yPts = new int[4];	
		this.shotDelay = shotDelay;
		shotDelayLeft = 0;
		
	}
	/**Draws the ship and the flames behind it
	 * @param Graphics g - the Graphics to be painted on*/
	public void draw(Graphics g){
		if(accelerating && active){ 
		 for(int i=0;i<3;i++){
		 flameXPts[i]=(int)(origFlameXPts[i]*Math.cos(angle)-
		 origFlameYPts[i]*Math.sin(angle)+
		 x+.5);
		 flameYPts[i]=(int)(origFlameXPts[i]*Math.sin(angle)+
		 origFlameYPts[i]*Math.cos(angle)+
		 y+.5);
		 }
		 g.setColor(Color.cyan); 
		 g.fillPolygon(flameXPts,flameYPts,3); 
		 }
		
		for(int i=0;i<4;i++){
		 xPts[i]=(int)(origXPts[i]*Math.cos(angle)- origYPts[i]*Math.sin(angle)+ x+.5);
		 yPts[i]=(int)(origXPts[i]*Math.sin(angle)+ origYPts[i]*Math.cos(angle)+ y+.5);
		 }
		if(active)
		 g.setColor(Color.white);
		else 
		 g.setColor(Color.darkGray);
		 g.fillPolygon(xPts,yPts,4);
		}
	
	/**Called every frame to move the ship*/
	public void move(){
		if(shotDelayLeft > 0)
			shotDelayLeft--;
		if(turningLeft)
			angle -= rotationalSpeed;
		if(turningRight)
			angle += rotationalSpeed;
		if(angle>(2*Math.PI))
			angle -= (2*Math.PI);
		else if (angle < 0)
			angle += (2*Math.PI);
		
		if(accelerating) {
			xVelocity += acceleration*Math.cos(angle);
			yVelocity += acceleration*Math.sin(angle);
		}
		x += xVelocity; 
		y += yVelocity;
		xVelocity *= velocityDecay; 
		yVelocity *= velocityDecay; 
		
		if(x < 0)
			x += 1100;
		else if(x > 1100)
			x -= 1100;
		if(y < 0)
			y += 800;
		else if(y > 800)
			y -= 800;
	}
	
	/**sets the shot delay then returns a new shot*/
	public Shot shoot(){
		shotDelayLeft = shotDelay;
		return new Shot(x, y, angle, xVelocity, yVelocity, 40);
	}
	
	/**set the ship to accelerate or stop*/
	public void setAccelerating(boolean accelerating){
		this.accelerating=accelerating; 
		}
	/**set the ship to turn left or stop turning left*/
	public void setTurningLeft(boolean turningLeft){
		this.turningLeft=turningLeft; 
		}
	
	/**set the ship to turn right or stop turning right*/
	public void setTurningRight(boolean turningRight){
	this.turningRight=turningRight;
	}
		
	/**Returns the ships x location*/
	public double getX(){
	return x; 
	}
		
	/**Returns the ships y location*/
	public double getY(){
	return y;
	}
		
	/**returns radius of circle that approximates the ship*/
	public double getRadius(){
	return radius;
	}
		
	/**used to pause the game*/
	public void setActive(boolean active){
	this.active=active; 
	}
		
	/**Return the state of the pause*/
	public boolean isActive(){
	return active;
	}
		
	/**Checks to see if the ship is ready to fire or if it needs to wait*/
	public boolean canShoot(){
	if(shotDelayLeft>0) 
		return false;
	else
		return true;
	}
	/**Checks if the ship has collided with a Shot
	 * @param Shot shot - the shot to collision detect with*/
	public boolean shotCollision(Shot shot){
		if(Math.pow(radius,2) > Math.pow(shot.getX()-x,2) + Math.pow(shot.getY()-y,2))
		 return true;
		return false; 
		}
	
	
	
	/**Draws the players health to screen
	 * @param Graphics g - the Graphics to be painted on*/
	public void drawPlayerHealth(Graphics g){
		switch (pHealth){
		case 1: 
			g.setColor(Color.red);  
			g.fillRect(5, 5, 30, 10);
			break;
		case 2: 
			g.setColor(Color.green);  
			g.fillRect(5, 5, 30, 10);
			
			g.setColor(Color.green);  
			g.fillRect(40, 5, 30, 10);
			
			break;
		case 3:
			g.setColor(Color.green);  
			g.fillRect(5, 5, 30, 10);
			
			g.setColor(Color.green);  
			g.fillRect(40, 5, 30, 10);
			
			g.setColor(Color.green);  
			g.fillRect(75, 5, 30, 10);
		}
	}
	
	/**returns the angle to ship is facing*/
	public double getAngle(){
		return this.angle;
	}
	/**Set player health
	 * @param int to set players health to*/
	public void setPlayerHealth(int playerHealth) {
		pHealth = playerHealth;
		
	}
	/**Returns the player current health*/
	public int getPlayerHealth() {
		return pHealth;
	}
	/**Decrement the Players Health*/
	public void decrementPlayerHealth(){
		pHealth --;
	}
}
