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

	/*public static ImageIcon changePlayerIcon(Player player) {
		Color playerColor = player.getPlayerColor();
		String playerColorString = playerColor.toString();

		switch (playerColorString) {
			case "RED":
				if (player.getPlayerRank() == PlayerRanks.KNIGHT) {
					//colorMap.put("RED", resizeImage(new ImageIcon("Program/images/Red_knight.svg.png")));
					ImageIcon icon = new ImageIcon("Program/images/Red_knight.svg.png");
					ImageIcon resizedImage = resizeImage(icon);

					return resizedImage; 


				} /*else if (player.getPlayerRank() == PlayerRanks.LORD) {
					colorMap.put("RED", resizeImage(new ImageIcon("Program/images/Red_rook.svg.png")));
				} else if (player.getPlayerRank() == PlayerRanks.KINGS) {
					colorMap.put("RED", resizeImage(new ImageIcon("Program/images/Red_king.svg.png")));
				}
	            break;
/* 
			case "CYAN":
				if (player.getPlayerRank() == PlayerRanks.KNIGHT) {
				colorMap.put("CYAN", resizeImage(new ImageIcon("Program/images/Blue_knight.svg.png")));
				} else if (player.getPlayerRank() == PlayerRanks.LORD) {
				colorMap.put("CYAN", resizeImage(new ImageIcon("Program/images/Blue_rook.svg.png")));
				} else if (player.getPlayerRank() == PlayerRanks.KINGS) {
				colorMap.put("CYAN", resizeImage(new ImageIcon("Program/images/Blue_king.svg.png")));
				}
				break;

            case "YELLOW":
				if (player.getPlayerRank() == PlayerRanks.KNIGHT) {
				colorMap.put("YELLOW", resizeImage(new ImageIcon("Program/images/Yellow_knight.svg.png")));
				} else if (player.getPlayerRank() == PlayerRanks.LORD) {
				colorMap.put("YELLOW", resizeImage(new ImageIcon("Program/images/Yellow_rook.svg.png")));
				} else if (player.getPlayerRank() == PlayerRanks.KINGS) {
				colorMap.put("YELLOW", resizeImage(new ImageIcon("Program/images/Yellow_king.svg.png")));
				}
				break;

            case "GREEN":
				if (player.getPlayerRank() == PlayerRanks.KNIGHT) {
					ImageIcon icon = new ImageIcon("Program/images/Green_knight.svg.png");
					ImageIcon resizedImage = resizeImage(icon);

					return resizedImage;
				
				
				//	colorMap.put("GREEN", resizeImage(new ImageIcon("Program/images/Green_knight.svg.png")));
			//	} else if (player.getPlayerRank() == PlayerRanks.LORD) {
			//	colorMap.put("GREEN", resizeImage(new ImageIcon("Program/images/Green_rook.svg.png")));
			//	} else if (player.getPlayerRank() == PlayerRanks.KINGS) {
			//	colorMap.put("GREEN", resizeImage(new ImageIcon("Program/images/Green_king.svg.png")));
				}
				break;
			}
		return null;
		}	*/  



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
