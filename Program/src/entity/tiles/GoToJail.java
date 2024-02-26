package entity.tiles;

import utilities.Constants;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.ImageIcon;
/**
 * 
 * @author Sebastian Viro, Aevan Dino, MUHAMMAD ABDULKHUDER
 *
 */
public class GoToJail implements Tile, Serializable {

	private ImageIcon img = Constants.TileImages.GOTOJAIL_TILE;

	/**
	 * returns null
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * returns null
	 */
	public Boolean getPurchaseable() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * returns null
	 */
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * returns null
	 */
	public String getTileInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * returns null
	 */
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * returns image
	 */
	public ImageIcon getPicture() {
		return img;
	}
}
