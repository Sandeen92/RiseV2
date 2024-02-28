package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is used to select a music file. The chosen music file
 * can be entity.player and stopped via the GUI.
 * 
 * @author AevanDino
 */
public class BackgroundMusic extends Thread implements Serializable {

	private static final long serialVersionUID = 5162710183389028792L;
	private Clip clip;
	private Thread musicPlayer;
	public Boolean isPlaying;
	private int musicPausedAt = 0;
	final JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);


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
	 * Pauses music, music continues where it stopped when entity.player asks for music again.
	 */
	public void pauseMusic() {
		if(clip!=null) {
			musicPausedAt = clip.getFramePosition();
			clip.stop();
		}
	}

	public void stopMusic(){
		if (clip != null){
			clip.stop();
			clip.close();
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

	public void selectFile(String file){
		try {
			stopMusic();

			File musicFile = new File(file);
			AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);

			if (clip != null){
				clip.stop();
				clip.close();
			}

			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Run method from Thread class. This method starts playing music until told to stop.
	 */
	public void run() {
		while(isPlaying && clip == null) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(Constants.AudioFiles.bgMusic);
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

	public  void changeVolume() {
		volumeSlider.setMajorTickSpacing(20);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setBackground(Color.white);
		volumeSlider.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		volumeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				float volume = (float) volumeSlider.getValue() / 100;
				setVolume(volume);
			}
		});
		createFrameForSlider();
	}

	private void createFrameForSlider(){
		JFrame vSlider = new JFrame();
		vSlider.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vSlider.getContentPane().add(volumeSlider);
		vSlider.pack();
		vSlider.setLocationRelativeTo(null);
		vSlider.setVisible(true);
	}


	public void changeBackgroundMusic(JFrame frame){
		String[] musicOptions = {"bgMusic","Hips Don't Lie","Yeah!"};
		String selectedMusic = (String) JOptionPane.showInputDialog(frame, "Select a music option", "Music Options", JOptionPane.PLAIN_MESSAGE, null, musicOptions, musicOptions[0]);
		if (selectedMusic != null) {
			stopMusic();
			selectFile("Program/src/resources/music/" + selectedMusic + ".wav");
			startMusic();
			frame.dispose();
		}
	}

	public JFrame createFrameForChangeMusic(){
		JFrame frame = new JFrame("Background Music Player");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		frame.setVisible(true);
		return frame;
	}
}