package board;

import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import player.Player;
import player.PlayerRanks;

import java.awt.Color;
import java.awt.Image;  

/**
 * @author Seth Oberg 
 * Get a image icon object from a string value
 */
public class ColorIconMap {
	private static HashMap<String, ImageIcon> colorMap = new HashMap<String, ImageIcon>();

	/**
	 * Adds all the colors to a hashmap
	 */
	public ColorIconMap() {
		addColorsToMap();
	}

	/**
	 * The method that adds all the colors to a hashmap
	 */
	public static void addColorsToMap() {
		colorMap.put("MAGENTA", new ImageIcon("Program/images/playerMagenta.jpg"));
		colorMap.put("PURPLE", new ImageIcon("Program/images/playerPurple.jpg"));
		colorMap.put("ORANGE", new ImageIcon("Program/images/playerOrange.jpg"));


		colorMap.put("RED", resizeImage(new ImageIcon("Program/images/Red_pawn.svg.png")));
		colorMap.put("YELLOW", resizeImage(new ImageIcon("Program/images/Yellow_pawn.svg.png")));
		colorMap.put("GREEN", resizeImage(new ImageIcon("Program/images/Green_pawn.svg.png")));
		colorMap.put("CYAN", resizeImage(new ImageIcon("Program/images/Blue_pawn.svg.png")));

	}

	private static ImageIcon resizeImage(ImageIcon originalIcon) {
		Image originalImage = originalIcon.getImage();
		int width = 40;
		int height = 40;
		Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	/**
	 * @param chosenColor
	 * @return ImageIcon
	 */
	public ImageIcon getColorFromMap(String chosenColor) {
		return colorMap.get(chosenColor);
	}	
}
