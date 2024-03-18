package Tests.Fastighet;

import board.Board;
import dice.Dice;
import events.EventCases;
import events.ManageEvents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerRanks;
import startMenu.StartingScreen;
import tiles.Property;
import tiles.Tile;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("ID:11 ReqId: FA1.1 (Köpa)")
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
    @DisplayName("ID:12 ReqId: FA1.2 (Sälja)")
    public void sellProperty(){
        player.addNewProperty(property);

        player.sellProperty(property);
        assertFalse(player.getProperties().contains(property));
    }
    @Test
    @DisplayName("ID:13 ReqId: FA1.3 (Uppgradera)")
    public void upgradeProperty(){
        player.addNewProperty(property);
        property.setOwner(player);
        property.increaseLevel();
        assertEquals(property.getTotalRent(), 32);
    }
    @Test
    @DisplayName("ID:14 ReqId: FA1.3.1 (Nivåer)")
    public void upgradePropertyKnight(){
        player.setPlayerRank(PlayerRanks.KNIGHT);
        property.setOwner(player);
        property.increaseLevel();
        assertEquals(property.getTotalRent(), 32);
    }
    @Test
    @DisplayName("ID:15 ReqId: FA1.3.1 (Nivåer)")
    public void upgradePropertyLord(){
        player.setPlayerRank(PlayerRanks.LORD);
        property.setOwner(player);
        property.increaseLevel();
        assertEquals(property.getTotalRent(), 32);
    }
    @Test
    @DisplayName("ID:16 ReqId: FA2.1 (Hyra för Gata)")
    public void rentForProperty(){
        //OWN A WHOLE STREET

        //TEST THE VALUE FOR STREET(NOT DONE)
        assertEquals(property.getTotalRent(), 2);
    }
}
