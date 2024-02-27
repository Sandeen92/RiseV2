package view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import entity.player.PlayerList;

/**
 * this class add tabs that displays informations about the players
 * in tabs
 * @author Abdulkhuder Muhammad, Sebastian Viro.
 *
 */
public class EastPanel extends JPanel {

	private PlayerList playerList;
	private JTabbedPane tab;
	private PlayerInfoPanel playerInfoPnl;
	private int currentPlayer = 0;

	/**
	 * @param playerList
	 * this method is also used to update the information displayed
	 */
	public void addPlayerList(PlayerList playerList) {
		this.playerList = playerList;
		addtabs();
	}

	/**
	 * Draws the GUI
	 */
	public EastPanel() {

		setPreferredSize(new Dimension(365, 860));
		setOpaque(false);
		setLayout(null);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.selected", Color.cyan);

		tab = new JTabbedPane();

		tab.setBounds(0, 0, 375, 860);
		tab.setBackground(new Color(0, 0, 0));

		add(tab);

	}

	/**
	 * this method adds tabs according to the amount of players
	 */
	public void addtabs() {

		tab.removeAll();

		for (int i = 0; i < playerList.getLength(); i++) {
			new EastPanel();
			playerInfoPnl = new PlayerInfoPanel(playerList, i);
			playerInfoPnl.setOpaque(false);
			tab.addTab("Player " + (i + 1), playerInfoPnl);
			tab.setOpaque(false);

		}

		tab.setSelectedIndex(currentPlayer);
		tab.setForeground(Color.white);
		tab.setBackground(new Color(157, 0, 0));
		tab.setBackgroundAt(currentPlayer, new Color(0, 157, 0));
		revalidate();
		repaint();
	}
}
