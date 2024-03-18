package Tests.alex_tests;


import controller.BoardController;
import entity.player.*;
import entity.tiles.Property;
import entity.tiles.Tavern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;

public class PlayerTests {
   private Player player;
   private Player player2;
   private BoardController boardController;
   @BeforeEach
   public void setUp() {
      boardController = new BoardController();
      player = new Player("Test Player", new ImageIcon(), new Color(255, 0, 0), 0);
      player2 = new Player("Test Player 2", new ImageIcon(), new Color(0, 255, 0), 1);
      boardController.addPlayerToList(player.getName(), "RED");
      boardController.addPlayerToList(player2.getName(), "GREEN");
   }

   @Test
   @DisplayName("ID:1 Generic Player Class Test")
   public void testGeneric(){
      assertAll(
              () -> assertEquals("Test Player", player.getName(), ""),
              () -> assertTrue(player.isAlive()),
              () -> assertEquals(1500, player.getBalance(), "Testing that the balance is correct"),
              () -> assertEquals(0, player.getJailCounter()),
              () -> assertFalse(player.isPlayerInJail()),
              () -> assertEquals(0, player.getPosition()),
              () -> assertFalse(player.passedGo()),
              () -> assertNotNull(player.getImage()),
              () -> assertEquals(PlayerRanks.PEASANT, player.getPlayerRank()),
              () -> assertEquals(1500, player.getNetWorth()),
              () -> assertTrue(player.getProperties().isEmpty()),
              () -> assertTrue(player.getTaverns().isEmpty()),
              () -> assertEquals(new Color(255, 0, 0), player.getPlayerColor())
      );
   }
   @Test
   @DisplayName("ID:2 Automated tests")
   public void testPlayerClassComprehensive() {
      assertAll(
              () -> {
                 player.increaseBalance(500);
                 assertEquals(2000, player.getBalance(), "Balance should increase correctly");
                 player.decreaseBalace(500);
                 assertEquals(1500, player.getBalance(), "Balance should decrease correctly");
              },
              () -> {
                 player.increaseNetWorth(500);
                 assertEquals(2000, player.getNetWorth(), "Net worth should increase correctly");
                 player.decreaseNetWorth(500);
                 assertEquals(1500, player.getNetWorth(), "Net worth should decrease correctly");
              },
              () -> {
                 Property property = new Property("Test Property", 100, 10, 10, Color.BLACK, 10, null);
                 player.addNewProperty(property);
                 assertTrue(player.getProperties().contains(property), "Property should be added correctly");
                 player.removeProperty(property);
                 assertFalse(player.getProperties().contains(property), "Property should be removed correctly");
              },
              () -> {
                 Tavern tavern = new Tavern("Test Tavern", 100);
                 player.addNewTavern(tavern);
                 assertTrue(player.getTaverns().contains(tavern), "Tavern should be added correctly");
              },
              () -> {
                 player.setNetWorth(2000);
                 assertEquals(PlayerRanks.KNIGHT, player.checkPlayerRank(), "Player rank should be updated correctly");
                 player.setNetWorth(5000);
                 assertEquals(PlayerRanks.LORD, player.checkPlayerRank(), "Player rank should be updated correctly");
                 player.setNetWorth(9000);
                 assertEquals(PlayerRanks.KING, player.checkPlayerRank(), "Player rank should be updated correctly");
              }
      );
   }
   @Test
   @DisplayName("ID:3 S1.1: Color Test")
   public void testPlayerColorS1_1() { // Checks whether the player color is set correctly
        assertAll(
                () -> assertEquals(new Color(255, 0, 10), boardController.getPlayerList().getPlayerFromIndex(0).getPlayerColor()),
                () -> assertEquals(new Color(35, 254, 14), boardController.getPlayerList().getPlayerFromIndex(1).getPlayerColor())
        );
   }
   @Test
   @DisplayName("ID:4 S1.2: Rank Change Test")
   public void testPlayerRankS1_2(){
      assertAll(
              () -> player.setNetWorth(1000),
              () -> player.checkPlayerRank(),
              () -> assertEquals(PlayerRanks.PEASANT, player.getPlayerRank()),
              () -> player.setNetWorth(2000),
              () -> player.checkPlayerRank(),
              () -> assertEquals(PlayerRanks.KNIGHT, player.getPlayerRank()),
              () -> player.setNetWorth(5000),
              () -> player.checkPlayerRank(),
              () -> assertEquals(PlayerRanks.LORD, player.getPlayerRank()),
              () -> player.setNetWorth(9000),
              () -> player.checkPlayerRank(),
              () -> assertEquals(PlayerRanks.KING, player.getPlayerRank())
      );
   }
   @Test
   @DisplayName("ID:5 S4.0 Player Economy Test")
   public void testPlayerHasGC() {
      assertAll(
              () -> assertTrue(player.getBalance() >= 0, "Player should have a balance."),
              () -> assertTrue(player.getNetWorth() >= 0, "Player should have a net worth.")
      );
   }
   @Test
   @DisplayName("S4.1 Player Start Balance Test")
   public void testPlayerStartBalance() {
      assertTrue(player.getBalance() > 0, "Player should have a balance.");
   }
   @Test
   @DisplayName("S4.2 Player Start Net Worth Test")
   public void testPlayerStartNetWorth() {
      assertTrue(player.getNetWorth() > 0, "Player should have a net worth.");
   }
   @Test
   @DisplayName("S9 One Property Per Round Test")
   public void purchasePropertyTest(){
      Property testProp = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);

      boardController.purchaseProperty("YES", testProp, player);

      assertFalse(testProp.getPurchaseable(), "Property should not be purchasable after purchase");
   }
   @Test
   @DisplayName("S10 Player Own Property Test")
   public void ownPropertyTest(){

      Property testProp1 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);
      Property testProp2 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);
      Tavern testTavern1 = new Tavern("TestTavern", 100);
      Tavern testTavern2 = new Tavern("TestTavern", 100);


      boardController.purchaseProperty("YES", testProp1, player );
      boardController.purchaseProperty("YES", testProp2, player2 );

      boardController.purchaseTavern("YES", testTavern1, player);
      boardController.purchaseTavern("YES", testTavern2, player2);

      assertAll(
              () -> assertEquals(testProp1.getOwner(), player, "Property should be owned by the correct player"),
              () -> assertEquals(testProp2.getOwner(), player2, "Property should be owned by the correct player"),
              () -> assertEquals(testTavern1.getOwner(), player, "Tavern should be owned by the correct player"),
              () -> assertEquals(testTavern2.getOwner(), player2, "Tavern should be owned by the correct player"),
              () -> assertNotSame(testProp1.getOwner(), player2, "Property should not be owned by the wrong player"),
              () -> assertNotSame(testProp2.getOwner(), player, "Property should not be owned by the wrong player"),
              () -> assertNotSame(testTavern1.getOwner(), player2, "Tavern should not be owned by the wrong player"),
              () -> assertNotSame(testTavern2.getOwner(), player, "Tavern should not be owned by the wrong player")
      );
   }
   @Test
   @DisplayName("S10.1 Player Sell Property Test")
   public void sellPropertyTest(){
      Property testProp1 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);

      player.addNewProperty(testProp1);
      player.decreaseBalace(testProp1.getPrice());

      player.sellProperty(testProp1);

        assertAll(
                () -> assertNull(testProp1.getOwner(), "Property should not have an owner after being sold"),
                () -> assertEquals(1500, player.getBalance(), "Player should have the correct balance after selling property")
        );
   }
   @Test
   @DisplayName("S10.4 Property List Test")
   public void allOwnedPropertiesTest(){
      Property testProp1 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);
      Property testProp2 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);
      Property testProp3 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);
      Property testProp4 = new Property("TestProp", 100, 10, 10, Color.BLACK, 10, null);

      boardController.purchaseProperty("YES", testProp1, player );
      boardController.purchaseProperty("YES", testProp2, player );
      boardController.purchaseProperty("YES", testProp3, player );
      boardController.purchaseProperty("YES", testProp4, player );

      assertEquals(4, player.getProperties().size(), "Tab should have the same amount of tabs as owned properties");
   }
}