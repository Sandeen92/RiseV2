package board;

import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Seth Oberg 
 * Get a image icon object from a string value
 */
public class ColorIconMap {
	private HashMap<String, ImageIcon> colorMap = new HashMap<String, ImageIcon>();

	/**
	 * Adds all the colors to a hashmap
	 */
	public ColorIconMap() {
		addColorsToMap();
	}

	/**
	 * The method that adds all the colors to a hashmap
	 */
	private void addColorsToMap() {
		colorMap.put("MAGENTA", new ImageIcon("Program/images/playerMagenta.jpg"));
		colorMap.put("RED", new ImageIcon("Program/images/playerRed.jpg"));
		colorMap.put("ORANGE", new ImageIcon("Program/images/playerOrange.jpg"));
		colorMap.put("YELLOW", new ImageIcon("Program/images/playerYellow.jpg"));
		colorMap.put("GREEN", new ImageIcon("Program/images/playerGreen.jpg"));
		colorMap.put("CYAN", new ImageIcon("Program/images/playerCyan.jpg"));
		colorMap.put("PURPLE", new ImageIcon("Program/images/playerPurple.jpg"));
	}

	/**
	 * @param chosenColor
	 * @return ImageIcon
	 */
	public ImageIcon getColorFromMap(String chosenColor) {
		return colorMap.get(chosenColor);
	}

}
