package spaceBattleArena;

/**************
 
  @author Ruaraidh Canaway
  
  This is a prototype for the Space Battle Arena Game
  A 2d Space Shooter, in which the goal is to defeat
  other ships in space combat, with Lazers.
  
  The Lazer.wav sound file was taken from a sound file "Laser Machine Gun Sound" i got from: http://soundbible.com/1774-Laser-Machine-Gun.html
  All credit to the original artist
  
  The SpaceBGMusic.wav was taken from: https://www.dl-sounds.com/royalty-free/arpeggio1-120/
  All credit to the original artist
  
  Notes:
  There is a nullPointer exception when the shot is moved or painted sometimes... due to a bug where the int that tracks the shot array is greater than 
  the number of non null values in the array. Usually only 1 greater. I was unable to fix the bug in time.
  
**************/

public class Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		UserInterface spaceBattleArena = new UserInterface();
	}
}
