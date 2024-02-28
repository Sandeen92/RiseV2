package entity;

import entity.tiles.Tile;


public class Board {

	private TileCollection tileCollection;

	public Board() {
        tileCollection = new TileCollection();
    }

	public Tile getTiletIndex(int index) {
        return tileCollection.getTileAtIndex(index);
    }
}
