package entity;

import entity.tiles.Tile;
import view.WestSidePanel;


public class Board {

	private TileCollection tileCollection;

	public Board() {
        tileCollection = new TileCollection();
    }

	public Tile getTileInfoAtIndex(int index) {
        return tileCollection.getTileAtIndex(index);
    }
}
