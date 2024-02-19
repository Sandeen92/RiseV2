package Tests.Fastighet;

import board.Board;
import dice.Dice;
import events.EventCases;
import events.ManageEvents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerRanks;
import startMenu.StartingScreen;
import tiles.Property;
import tiles.Tile;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
public class TestFastighetInteraktion {
    Player player;
    Dice dice = new Dice();
    Board board;

    ManageEvents events;

    Property property;
    @BeforeEach
    public void beforeTest(){
        board = new Board();
        player = new Player("John",null,null,1);
        events = new ManageEvents();
        property = new Property("Wood Cutter Camp", 60, 2, 30, new Color(58,20,56,255), 50,new ImageIcon("Program/tilePics/Wood.png"));
    }

    @Test
    public void buyPropertyWhenPurchaseable(){
        assertTrue(property.getPurchaseable(), "Property should be purchasable when player has enough balance");
    }
    @Test
    public void buyPropertyWhenNotPurchaseable(){
        //Set boolean purchaseable to false
        property.setPurchaseable(false);
        assertFalse(property.getPurchaseable(), "Property should not be purchasable");
    }
    @Test
    public void sellProperty(){

    }
    @Test
    public void upgradeProperty(){

    }
    @Test
    public void upgradePropertyPeasant(){

    }
    @Test
    public void upgradePropertyKnight(){

    }
    @Test
    public void upgradePropertyLord(){

    }



}
