package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import entity.player.Player;

/**
 * 
 * Every tile is a guiTile object they are used to display the players. It could
 * also be used to show the level on the properties.
 * 
 * @author sethoberg
 */

public class GUITile extends JLabel {

	private static final long serialVersionUID = 1L;
	private Font labelFont = new Font("Arial", Font.BOLD, 10);
	private JLabel infoLabel = new JLabel("", SwingConstants.CENTER);
	private JLabel labelArray = new JLabel();
	private JLabel[] labels = new JLabel[4];
	private int alignment = 1;
	private Border tileBorder = BorderFactory.createLineBorder(Color.decode("#ff7723"));

	public GUITile(int SouthWestNorthEast) {
		alignment = SouthWestNorthEast;

		setPreferredSize(new Dimension(200, 300));
		setLayout(new BorderLayout());

		labelArray.setLayout(new GridLayout(2, 2));
		labelArray.setOpaque(false);
		labelArray.setBackground(Color.decode("#ffe9c6"));

		styleAndAddInfoLabel();
		addLabelsToArray();
		addLabelsToGrid();
	}

	public void styleAndAddInfoLabel() {
		infoLabel.setPreferredSize(new Dimension(200, 20));
		infoLabel.setOpaque(false);
		infoLabel.setForeground(Color.white);
		infoLabel.setFont(labelFont);
		infoLabel.setText("");
		infoLabel.setBackground(Color.magenta);

		if (alignment == 1) {
			add(infoLabel, BorderLayout.NORTH);
		} else if (alignment == 2) {
			infoLabel.setPreferredSize(new Dimension(20, 200));
			add(infoLabel, BorderLayout.EAST);
		} else if (alignment == 3) {
			add(infoLabel, BorderLayout.SOUTH);
		} else if (alignment == 4) {
			infoLabel.setPreferredSize(new Dimension(20, 200));
			add(infoLabel, BorderLayout.WEST);
		}

	}

	public void addLabelsToArray() {
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();
		}
	}

	// Adds 4 j label
	public void addLabelsToGrid() {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setPreferredSize(new Dimension(200, 200));
			labelArray.add(labels[i]);
		}
		add(labelArray, BorderLayout.CENTER);
	}


	public void setPlayer(Player player) {
		labels[player.getPlayerIndex()].setIcon(player.getImage());
		labels[player.getPlayerIndex()].setHorizontalAlignment(CENTER);
	}

	public void removePlayer(Player player) {
		labels[player.getPlayerIndex()].setIcon(null);
		labels[player.getPlayerIndex()].setHorizontalAlignment(CENTER);
	}

}
