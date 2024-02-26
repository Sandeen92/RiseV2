package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import entity.Board;
import entity.Dice;
import utilities.Constants;
import view.eastSidePanels.EastSidePanel;
import entity.player.PlayerList;
import utilities.BackgroundMusic;

/**
 * This class combines most of the panels in the game and adds appropriate
 * references.
 * 
 * @author Abdulkhuder Muhammad
 *
 */
public class GamePanels extends JPanel implements Serializable {


	private EastSidePanel tPanel = new EastSidePanel();
	private WestSidePanel westPanel = new WestSidePanel();
	private Board board = new Board(westPanel);
	private Dimension screenSize;
	private JFrame frame = new JFrame();
	private JLabel lblPic = new JLabel();
	private Menu m;
	private int width;
	private int height;
	private CheatGui cheatGui;

	/**
	 * adds the panels and sets the bounds
	 */
	public GamePanels(BackgroundMusic music) {
		setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));

		setBackground(Color.DARK_GRAY);

		setLayout(null);
		tPanel.setOpaque(false);
		tPanel.setBounds(1095, 0, 345, 860);
		add(tPanel);
		westPanel.setBounds(0, 0, 345, 860);
		add(westPanel);
		board.setBounds(346, 0, 750, 750);
		add(board);
		dice.setBounds(346, 751, 750, 109);
		add(dice);
		m = new Menu(music);
		m.setBounds(0, 0, 50, 18);
		add(m);
		setScreenSize(Constants.GameWindow.screenSize);
		repaint();
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
		width = screenSize.width;
		height = screenSize.height;
		setPreferredSize(new Dimension(width, height));

		lblPic.setBounds(0, 0, width, height);

		lblPic.setIcon(Constants.BoardImages.getBackgroundImage());
		add(lblPic);
		repaint();
	}

	/**
	 * This is where we call the frame
	 */
	public void startboard() {
		frame = new JFrame("Change your fate");
		frame.setPreferredSize(new Dimension(width + 18, height + 10));
		frame.setLocation(-9, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().add(this);
		frame.pack();
		repaint();
	}

	/**
	 * @param playerList
	 */
	public void addPlayer(PlayerList playerList) {

		board.addPlayers(playerList);
		board.setPlayers();
		tPanel.addPlayerList(playerList);
		dice.addPlayerList(playerList);

	}

	/**
	 * Disposes the frame
	 */
	public void Dispose() {
		frame.dispose();
	}

}
