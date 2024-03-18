package entity;

import utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import static utilities.Constants.PlayerTokenImages.*;


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
		colorIconMap.put("MAGENTA", MAGENTA_PAWN);
		colorIconMap.put("PURPLE", PURPLE_PAWN);
		colorIconMap.put("ORANGE", ORANGE_PAWN);
		colorIconMap.put("RED", RED_PAWN);
		colorIconMap.put("YELLOW", YELLOW_PAWN);
		colorIconMap.put("GREEN", GREEN_PAWN);
		colorIconMap.put("CYAN", CYAN_PAWN);
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
