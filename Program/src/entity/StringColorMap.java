package entity;

import utilities.Constants;

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
		colorIconMap.put("MAGENTA", Constants.PlayerTokenImages.MAGENTA);
		colorIconMap.put("PURPLE", Constants.PlayerTokenImages.PURPLE);
		colorIconMap.put("ORANGE", Constants.PlayerTokenImages.ORANGE);
		colorIconMap.put("RED", Constants.PlayerTokenImages.RED_PAWN);
		colorIconMap.put("YELLOW", Constants.PlayerTokenImages.YELLOW_PAWN);
		colorIconMap.put("GREEN", Constants.PlayerTokenImages.GREEN_PAWN);
		colorIconMap.put("CYAN", Constants.PlayerTokenImages.CYAN_PAWN);
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
