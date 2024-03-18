package Tests.Fastighet;



import controller.BoardController;
import controller.EventManager;
import entity.Board;
import entity.Dice;
import entity.player.Player;
import entity.tiles.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static entity.player.PlayerRanks.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Constants.TileImages.*;

import java.awt.*;
public class TestFastighetInteraktion {
    private Player player;
    private Dice dice = new Dice();
    private Board board;
    private BoardController boardController = new BoardController();
    private EventManager events;

    Property property;
    @BeforeEach
    public void beforeTest(){
        board = new Board();
        player = new Player("John",null,null,1);
        events = new EventManager(boardController);
        property = new Property("Wood Cutter Camp", 60, 2, 30, new Color(58,20,56,255), 50, PROPERTY_WOOD_TILE);
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
        player.setPlayerRank(KNIGHT);
        property.setOwner(player);
        property.increaseLevel();
        assertEquals(property.getTotalRent(), 32);
    }
    @Test
    @DisplayName("ID:15 ReqId: FA1.3.1 (Nivåer)")
    public void upgradePropertyLord(){
        player.setPlayerRank(LORD);
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
