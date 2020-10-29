package spaceBattleArena;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	/**A Clip of audio that can be loaded prior to play back, used for the background music*/
	private Clip clip;	
	/**Audio Input Stream for the background music*/
	private AudioInputStream battleBGmusic;
	/**Audio Input Stream for the lazers*/
	private AudioInputStream lazers;
	
	/**Creates a Sound that can be used
	 * @param URL url - the path of the music to be played*/
	public Sound() {	 
		try {
			 clip = AudioSystem.getClip();;
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
	/**Plays the audio Once*/
	 public void play() {
		  try {
		      if (clip != null) {
		      new Thread() {
		    	  public void run() {
		    		  synchronized (clip) {
		    			  clip.stop();
		    			  clip.setFramePosition(0);
		    			  clip.start();
		    		  }
		    	  }
		      }.start();
		  }
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		 
	 }
		 
		      
	/**Stops the playing audio*/
	public void stop(){
		 if(clip == null) return;
		 	clip.stop();
	}
		
	/**Plays and loops the audio*/
	public void loop() {
		try {
		 if (clip != null) {
			 new Thread() {
				 public void run() {
					 synchronized (clip) {
						 clip.stop();
						 clip.setFramePosition(0);
						 clip.loop(Clip.LOOP_CONTINUOUSLY);
					 }
				 }
			 }.start();
		  	}
		 } catch (Exception e) {
		 	e.printStackTrace();
		 	}
	}

	/**To check if the audio is playing or not*/
	public boolean isActive(){
		return clip.isActive();
	 }
	
	/**loads and sets the Sound to hold the background music*/
	public void setToBattleMusic(){
		try {
			battleBGmusic = AudioSystem.getAudioInputStream(UserInterface.class.getResource("/spaceBattleArena/SpaceBGMusic.wav"));
			clip.open(battleBGmusic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**loads and sets the Sound to hold the lazer sound*/
	public void setToLazers(){
		try {
			lazers = AudioSystem.getAudioInputStream(UserInterface.class.getResource("/spaceBattleArena/Lazer.wav"));
			clip.open(lazers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
