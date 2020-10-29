package spaceBattleArena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	/**Buffered Image for the Background Image in the game */
	private BufferedImage bgImage;
	/**An Image used for creating the bGI*/
	private Image img;
	/**An image to paint to in the background*/
	private Graphics bGI;
	/**True if the game is paused, false otherwise*/
	private boolean paused;
	/**Thread for running the game*/
	private Thread thread;
	/**long to mark the start of a frame*/
	private long startTime;
	/**long to mark the end of a frame*/
	private long endTime;
	/**long to hold the frame period*/
	private long framePeriod;
	/**Object to act as the player controlled ship */
	private Ship player;
	/**An array of Shots to store the and manage the players shots*/
	private Shot[] shots;
	/**An array of Shots to store the and manage the enemies shots*/
	private Shot[] eneShots;
	/**Tracks the amount of player shots active at one time*/
	private int numShots;
	/**True if the ship is shooting, false otherwise*/
	private boolean shooting;
	/**Object to act as the asteroids in the game*/
	private Asteroid[] asteroids;
	/**int to keep track of how many asteroids are active in the game*/
	private int numAsteroids;
	/**double that controls the asteroids size*/
	private double astRadius;
	/**double that controls the minimum velocity of the asteroid*/
	private double minAstVel;
	/**double that controls the maximum velocity of the asteroid*/
	private double maxAstVel;
	/**Object that acts as the non player controlled character*/
	private Enemy ufo;
	/**True is the ufo is alive, false otherwise*/
	private boolean deed;
	/**Tracks the non player controlled character's life*/
	private int eneHealth;
	/**Tracks the amount of enemy shots active at one time*/
	private int eneNumShots;
	/**True if the player is dead, false otherwise
	 * Not Fully implemented yet*/
	@SuppressWarnings("unused")
	private boolean playerDeed;
	/**Array used during Testing*/
	private int arrayTest=0;
	/**int used to track current level*/
	private int currLevel;
	/**Tracks the number of enemies*/
	private int noOfEnemies;
	
	/**Creates the Panel that runs the game
	 * @param int lvl The level to be loaded*/
	public GamePanel(int lvl){
		
		addKeyListener(this);
		startUp(lvl);
		
		shots = new Shot[41];
		numShots = 0;
		shooting = false;
		deed=false;

		endTime = 0;
		startTime = 0;
		framePeriod = 25;
		
		thread = new Thread(this);
		thread.start();
		}
	
	/**Called when gamePanel is initialised to create the level depended on what level needs to be loaded
	 * @Param int lvl The level to be loaded*/
	private void startUp(int lvl){
	switch (lvl) {
	case 1:	player = new Ship(850, 550, 3.8, .20, .98, .1, 12);
			player.setPlayerHealth(3);
			try {
					bgImage = ImageIO.read(new File("src/spaceBattleArena/SimpleStaryBackgroundGame.png"));
				} catch (IOException e) {}
			
			
			ufo = new Enemy(80, 80, 0, 20, 1, 12);
			eneHealth = 4;
			eneShots = new Shot[41];
			eneNumShots = 0;
			
			
			numAsteroids=4;
			astRadius=20; //values used to create the asteroids
			minAstVel=.1;
			maxAstVel=1;
			
			asteroids=new Asteroid[numAsteroids];
			for(int i=0;i<numAsteroids;i++)
				 asteroids[i]=new Asteroid(Math.random()*1100,Math.random()*800,astRadius,minAstVel,maxAstVel);
			
			noOfEnemies = 1;
			currLevel = lvl;
		break;
	case 2: player = new Ship(850, 550, 3.8, .35, .98, .1, 12);
			player.setPlayerHealth(3);
			try {
					bgImage = ImageIO.read(new File("src/SpaceBattleArena/SimpleStaryBackgroundGame2.png"));
				} catch (IOException e) {}
			
			
			ufo = new Enemy(80, 80, 0, 30, 2, 10);
			eneHealth = 7;
			eneShots = new Shot[41];
			eneNumShots = 0;
			deed = false;
			
			numAsteroids=8;
			astRadius=25; 
			minAstVel=.2;
			maxAstVel=2;
	
			asteroids=new Asteroid[numAsteroids];
			for(int i=0;i<numAsteroids;i++)
				 asteroids[i]=new Asteroid(Math.random()*1100,Math.random()*800,astRadius,minAstVel,maxAstVel);
			
			noOfEnemies = 1;
			currLevel = lvl;
		break;
	case 3: player = new Ship(850, 550, 3.8, .50, .98, .1, 12);
			player.setPlayerHealth(3);
			try {
					bgImage = ImageIO.read(new File("src/SpaceBattleArena/SimpleStaryBackgroundGame2.png"));
			} catch (IOException e) {}

			ufo = new Enemy(80, 80, 0, 40, 3, 8);
			eneHealth = 10;
			eneShots = new Shot[41];
			eneNumShots = 0;
			deed = false;
			
			numAsteroids=12;
			astRadius=30;
			minAstVel=.3;
			maxAstVel=3;
			asteroids=new Asteroid[numAsteroids];
			for(int i=0;i<numAsteroids;i++)
				 asteroids[i]=new Asteroid(Math.random()*1100,Math.random()*800,astRadius,minAstVel,maxAstVel);
			
			noOfEnemies = 1;
			currLevel = lvl;
		break;
	}
	} 
	
	/**Paints the game a background screen then paints it all to the front
	 * @param Graphics g - the Graphics to be painted on*/
	public void paint(Graphics gfx){
		
		if (img == null){
			img = createImage(1100,800);
		}
		bGI = img.getGraphics();
		super.paintComponent(bGI);
		bGI.drawImage(bgImage, 0, 0, 1100, 1000, null);
		this.player.draw(bGI);
	
		if(!deed){
		this.ufo.Draw(bGI);}
		
		for (int i=0;i<eneNumShots;i++)
			eneShots[i].draw(bGI);
		
		arrayTest = 0;
		for (int i=0;i<numShots;i++){
			if(shots[i]!=null)
				arrayTest++;
		}
		if(arrayTest != numShots){
			numShots = arrayTest;
		}
		
		for(int i=0;i<numShots;i++){
			shots[i].draw(bGI);
		}
		
		for(int i=0;i<numAsteroids;i++)
			 asteroids[i].draw(bGI);
			 player.draw(bGI);
			 
		this.player.drawPlayerHealth(bGI);
		
		if (paused){
			bGI.setColor(Color.white);
			bGI.drawString(("Paused"), 500, 350);
			bGI.drawString(("*Return to Menu Coming Soon*"), 430, 400);
			}
		
		gfx.drawImage(img, 0, 0, this);
	    repaint();
	}
	
	/**Called as part of the paint method to draw to screen
	 * @param Graphics g - the Graphics to be painted on*/
	public void update(Graphics gfx){
		paint(gfx);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_E)
			player.setAccelerating(true);
		
		else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_S)
			player.setTurningLeft(true);
			
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_F)
			player.setTurningRight(true);
			
		else if (e.getKeyCode()==KeyEvent.VK_SPACE)
			shooting = true;
		
		else if(e.getKeyCode()==80){
			paused = !paused;
			if (paused)
				player.setActive(false);
			else
				player.setActive(true);
			
		
				
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_E){
			player.setAccelerating(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_S){
			player.setTurningLeft(false);
			}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_F){
			player.setTurningRight(false);
			}
		else if (e.getKeyCode()==KeyEvent.VK_SPACE){
			shooting = false;}
			
	}
	
	
	public void run(){
		for(;;){
				startTime = System.currentTimeMillis();
				if (!paused){
					player.move();
				//The code below was used in an attempt to debug the null Pointer in the shots Array
				//System.out.println("numShots is: " + numShots);  
				//System.out.println("arrayTest is: " + arrayTest);
				
				arrayTest = 0;
				for (int k=0;k<numShots;k++){
					if(shots[k]!=null)
						arrayTest++;
				}
				if(arrayTest != numShots){
					numShots = arrayTest;
				}
					//try{
					for (int i=0;i<numShots;i++){
						shots[i].move(1100, 800);
						
						if(shots[i].getLifeLeft()<=0){
							deleteShot(i);
							i--;
						}
					}

					for (int i=0;i<eneNumShots;i++){
						eneShots[i].move(1100, 800);
					
						if(eneShots[i].getLifeLeft()<=0){
							deleteEneShot(i);
							i--;
						}
					}
				
					isUFODead();
					updateAsteroids();
					isPlayerDead();
				
					if(!deed && ufo.canShoot() && !paused){
						eneShots[eneNumShots]=ufo.shoot(Math.atan2((player.getY()- ufo.getY()), (player.getX()-ufo.getX())));
						eneNumShots++;
					}
					ufo.Move(player.getX(),player.getY());
				}
				
				if(shooting && player.canShoot()){
					shots[numShots]=player.shoot();
					numShots++;
					Shot.lazerSound();
				}

				repaint();
				
				try{
					endTime = System.currentTimeMillis();
					if(framePeriod-(endTime-startTime)>0)
						Thread.sleep(framePeriod -(endTime-startTime));
					}catch(InterruptedException e){
				}
			}
}
	
	/**Method run to delete Enemy shots from the screen
	 * @Param index of the array to be deleted*/
	private void deleteEneShot(int index){
		eneNumShots--;
		for(int i=index; i<eneNumShots; i++)
			eneShots[i]=eneShots[i+1];
		shots[eneNumShots]=null;
	}
	
	/**Method run to delete Player shots from the screen
	 * @Param index of the array to be deleted*/
	private void deleteShot(int index){
		numShots--;
		for(int i=index; i<numShots; i++)
			shots[i]=shots[i+1];
		shots[numShots]=null;
	}
	
	/**Method run to delete asteroids from the screen
	 * @Param index of the array to be deleted*/
	private void deleteAsteroid(int index){
		numAsteroids--;
		for(int i = index; i < numAsteroids; i++){
			asteroids[i] = asteroids[i+1];
		}
	}
	
	/**Run every frame to control the asteroids on screen*/
	private void updateAsteroids(){
		
		for(int i=0;i<numAsteroids;i++){
			asteroids[i].move();
			
			if(asteroids[i].shipCollision(player)){
				deleteAsteroid(i);
				return;
			}
			
			for(int j=0;j<numShots;j++){
				if(asteroids[i].shotCollision(shots[j])){
					deleteShot(j);
					deleteAsteroid(i);
					j = numShots;
					i--;
				}
			}
		}
	}
	
	/**Checks players health value and ends the game if it is zero*/
	private void isPlayerDead(){
		if(player.getPlayerHealth() == 0){
			playerDeed=true;
		 }
		if(player.getPlayerHealth() > 0){
			for(int j=0;j<eneNumShots;j++){
				if(player.shotCollision(eneShots[j])){
					deleteEneShot(j); 
					player.decrementPlayerHealth();
					j = eneNumShots;
				}
			}
		}
	}
	
	/**Checks the life of the ufo, if zero deletes ufo from screen*/
	private void isUFODead(){
		if(eneHealth == 0){
				deed=true;
				noOfEnemies--;
				hasPlayerWon();
		 }
		else if(eneHealth > 0){
			for(int j=0;j<numShots;j++){
				if(ufo.shotCollision(shots[j])){
					deleteShot(j);
					eneHealth--;
					j = numShots;
				}
			}
		}
	}
	
	/**Checks to see if player has completed the level. If so, the player is moved onto the next level, exception at level 3 where once the player has won, the game closes*/
	private void hasPlayerWon(){
		if (noOfEnemies == 0){
			switch (currLevel){
			case 1:
				startUp(2);
				paused = true;
				break;
			case 2:
				startUp(3);
				paused = true;
				break;
			case 3:
				System.exit(0);
		}
	}
			
		
	}
	/**Need for the KeyListener implement to work*/
	@Override
	public void keyTyped(KeyEvent e) {}
}
