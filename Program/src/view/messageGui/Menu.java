package view.messageGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import utilities.BackgroundMusic;
import controller.StartingScreen;
import view.MainPanel;

/**
 * This class displays the game board as well as what the players are called and
 * how much they own.
 *
 * @autho Muhammad Hasan, Rohan Samandari
 */
public class Menu extends JPanel {
	private BackgroundMusic music;
	private MainPanel mainPanel;
	private JMenu jmMenu = new JMenu("Menu");
	private JMenuBar jmMenuBar = new JMenuBar();
	private JMenuItem jmExit = new JMenuItem("Exit");
	private JMenuItem jmMusic = new JMenuItem("Pause Music");
	private JMenuItem jmRestart = new JMenuItem("Restart Game");
	private JMenuItem jmRules = new JMenuItem("Read Rules");
	private JMenuItem jmChangeVolume = new JMenuItem("Adjust Volume");
	private JMenuItem jmChangebgMusic = new JMenuItem("Change Music");
	private Rules rules = new Rules();
	private boolean paused = false;

	/**
	 * Constructor which draws the gui
	 */
	public Menu(BackgroundMusic music, MainPanel mainPanel) {
		this.music = music;
		this.mainPanel = mainPanel;
		setOpaque(false);
		setPreferredSize(new Dimension(400, 40));
		setLayout(new BorderLayout());
		jmMenuBar.setPreferredSize(new Dimension(100,5));
		jmExit.addActionListener(new ButtonListener());
		jmMusic.addActionListener(new ButtonListener());
		jmRules.addActionListener(new ButtonListener());
		jmRestart.addActionListener(new ButtonListener());
		jmChangeVolume.addActionListener(new ButtonListener());
		jmChangebgMusic.addActionListener(new ButtonListener());
		jmMenu.add(jmChangebgMusic);
		jmMenu.add(jmChangeVolume);
		jmMenu.add(jmMusic);
		jmMenu.add(jmRules);
		jmMenu.add(jmRestart);
		jmMenu.add(jmExit);
		jmMenuBar.add(jmMenu);

		add(jmMenuBar, BorderLayout.WEST);
		setBackground(Color.black);
	}

	/**
	 * Sets music reference
	 * @param music
	 */
	//public Menu(BackgroundMusic music) {
	//	this.music = music;
	//}

	/**
	 * Button listener class used to listen for actions
	 * @author Rohan Samandari
	 *
	 */
	public class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== jmMusic) {
				if(!paused){
					music.pauseMusic();
					jmMusic.setText("Resume Music");
					paused = true;
				}
				else{
					music.startMusic();
					jmMusic.setText("Pause Music");
					paused = false;
				}
			} else if (e.getSource()==jmRestart) {
				mainPanel.Dispose();
				StartingScreen ss = new StartingScreen();
				Thread thread = new Thread(ss);
				thread.start();
			} else if (e.getSource()==jmExit) {
				System.exit(0);
			} else if (e.getSource()==jmRules) {
				rules.showRules();
			}
		}

	}
}
