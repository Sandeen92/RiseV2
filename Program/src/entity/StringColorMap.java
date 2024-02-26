package entity;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;


/**
 * This class returns a color with a string 
 * @author Seth Oberg
 *
 */
public class StringColorMap implements Serializable {
	private HashMap<String, Color> colorMap = new HashMap<String, Color>();
	private static HashMap<String, ImageIcon> colorIconMap = new HashMap<String, ImageIcon>();
	
	
	/**
	 * Add colors to hashmap
	 */
	public StringColorMap() {
		addColorsToMap();
		addImageIcons();
	}
	
	
	private void addColorsToMap() { 
		colorMap.put("MAGENTA", new Color(255, 15, 226));
		colorMap.put("RED", new Color(255, 0, 10, 255)); 
		colorMap.put("ORANGE", new Color(254, 119, 14, 255));
		colorMap.put("YELLOW", new Color(206, 183, 51, 255)); 
		colorMap.put("GREEN", new Color(35, 254, 14, 255));
		colorMap.put("CYAN", new Color(93, 188, 210, 255));
		colorMap.put("PURPLE", Color.decode("#9542f4"));
	}


	private void addImageIcons(){
		colorIconMap.put("MAGENTA", new ImageIcon("playerMagenta.jpg"));
		colorIconMap.put("PURPLE", new ImageIcon("playerPurple.jpg"));
		colorIconMap.put("ORANGE", new ImageIcon("playerOrange.jpg"));
		colorIconMap.put("RED", resizeImage(new ImageIcon("Red_pawn.svg.png")));
		colorIconMap.put("YELLOW", resizeImage(new ImageIcon("Yellow_pawn.svg.png")));
		colorIconMap.put("GREEN", resizeImage(new ImageIcon("Green_pawn.svg.png")));
		colorIconMap.put("CYAN", resizeImage(new ImageIcon("Blue_pawn.svg.png")));
	}

	private static ImageIcon resizeImage(ImageIcon originalIcon) {
		Image originalImage = originalIcon.getImage();
		int width = 40;
		int height = 40;
		Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	 
	
	/**
	 * Either magenta, red, orange, yellow, green, cyan, purple
	 * @param chosenColor color to Get
	 * @return
	 */
	public Color getColorFromMap(String chosenColor) {
		return colorMap.get(chosenColor);
	}


	public ImageIcon getImageIconFromMap(String chosenColor) {
		return colorIconMap.get(chosenColor);
	}
	
}
