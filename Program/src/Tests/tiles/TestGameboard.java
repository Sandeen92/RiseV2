package Tests.tiles;


import board.Board;
import dice.Dice;
import events.ManageEvents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;
import tileCollection.TileCollection;
import tiles.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameboard {

    Player player;
    Dice dice = new Dice();
    Board board;

    ManageEvents events;
    TileCollection tileCollection;

    @BeforeEach
    public void beforeTest(){
        board = new Board();
        player = new Player("John",null,null,1);
        events = new ManageEvents();
        tileCollection = new TileCollection();



    }

    @Test
    @DisplayName("ID:1 ReqId: SB2.1 (Fastighet)")
    public void Property(){
        Tile propertyTile = tileCollection.getTileAtIndex(1);
        assertInstanceOf(Property.class, propertyTile);

    }

    @Test
    @DisplayName("ID:2 ReqId: SB2.2 (Jobb)")
    public void Work(){
        Tile propertyTile = tileCollection.getTileAtIndex(5);
        assertInstanceOf(Work.class, propertyTile);
    }

    @Test
    @DisplayName("ID:3 ReqId: SB2.3 (Tavern)")
    public void Tavern(){
        Tile propertyTile = tileCollection.getTileAtIndex(28);
        assertInstanceOf(Tavern.class, propertyTile);
    }

    @Test
    @DisplayName("ID:4 ReqId: SB2.4 (Spågumma)")
    public void FortuneTeller(){
        Tile propertyTile = tileCollection.getTileAtIndex(17);
        assertInstanceOf(FortuneTeller.class, propertyTile);
    }

    @Test
    @DisplayName("ID:5 ReqId: SB2.5 (Gå)")
    public void Go(){
        Tile propertyTile = tileCollection.getTileAtIndex(0);
        assertInstanceOf(Go.class, propertyTile);
    }

    @Test
    @DisplayName("ID:6 ReqId: SB2.6 (Skatt)")
    public void Tax(){
        Tile propertyTile = tileCollection.getTileAtIndex(4);
        assertInstanceOf(Tax.class, propertyTile);
    }

    @Test
    @DisplayName("ID:7 ReqId: SB2.7 (Söndags kyrka)")
    public void SundayChurch(){
        Tile propertyTile = tileCollection.getTileAtIndex(4);
        assertInstanceOf(Tax.class, propertyTile);
    }

    @Test
    @DisplayName("ID:8 ReqId: SB2.8 (Fängelse)")
    public void Jail(){
        Tile propertyTile = tileCollection.getTileAtIndex(10);
        assertInstanceOf(Jail.class, propertyTile);
    }

    @Test
    @DisplayName("ID:9 ReqId: SB2.8.1 (På Besök)")
    public void INTEGRALTESTOnVisit(){

        dice.moveWCheat(10);


    }

    @Test
    @DisplayName("ID:10 ReqId: SB2.9 (Åk i fängelse)")
    public void GoToJail(){
        Tile propertyTile = tileCollection.getTileAtIndex(30);
        assertInstanceOf(GoToJail.class, propertyTile);
    }




}
