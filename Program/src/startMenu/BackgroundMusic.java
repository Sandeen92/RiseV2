package startMenu;

import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class is used to select a music file. The chosen music file
 * can be player and stopped via the GUI.
 * 
 * @author AevanDino
 */
public class BackgroundMusic extends Thread implements Serializable {

	private static final long serialVersionUID = 5162710183389028792L;
	private Clip clip;
	private Thread musicPlayer;
	public Boolean isPlaying;
	private int musicPausedAt = 0;
	
	public BackgroundMusic() {
		this.clip = null;
	}

	/**
	 * If music isn't already playing, this method will start
	 * playing the chosen file, that is if there is a file to be played.
	 */
	public void startMusic() {
		
		if(clip!=null && isPlaying) {
			clip.setFramePosition(musicPausedAt);
			clip.start();
			
		
		} else if(musicPlayer==null) {
			musicPlayer = new Thread(this);
			isPlaying=true;
			musicPlayer.start();
		}
	}    

	/**
	 * Pauses music, music continues where it stopped when player asks for music again.
	 */
	public void pauseMusic() {
		if(clip!=null) {
			musicPausedAt = clip.getFramePosition();
			clip.stop();
		}
	}

	/**
	 * Takes in a float value between 0 and 1 and sets the volume of the music.
	 * Could be implemented using a slider/buttons in the GUI.
	 * CURRENTLY BEING WORKED AT
	 * @param volume - float value between 0 and 1
	 */
	private void setVolume(float volume){
		if (clip != null){
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float min = volumeControl.getMinimum();
			float max = volumeControl.getMaximum();
			volumeControl.setValue((max - min) * volume + min);
		}
	}
	/**
	 * Run method from Thread class. This method starts playing music until told to stop.
	 */
	public void run() {
		while(isPlaying && clip == null) {
			try {
				File musicPath = new File("music/bgMusic.wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(ais);
				//clip.loop(Clip.LOOP_CONTINUOUSLY);
				//clip.start();
			}
			catch(Exception e)
			{
				System.out.println("You did not choose a WAV file");
			}
		}
	}
}