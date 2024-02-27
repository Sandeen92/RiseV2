package alex_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import player.Player;
import player.PlayerList;
import player.PlayerRanks;
import startMenu.StartingScreen;

import javax.swing.*;
import java.awt.*;

public class PlayerTests {
   private Player player;

   @BeforeEach
   public void setUp() {
      player = new Player("Test Player", new ImageIcon(), new Color(255, 0, 0), 0);
   }

   @Test
   @DisplayName("Generic Player Class Test")
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
   @DisplayName("S1.1: Color Test")
   public void testPlayerColorS1_1() { // Checks whether the player color is set correctly
        StartingScreen startingScreen = new StartingScreen();
        PlayerList playerList = startingScreen.setUpTest();
        assertAll(
                () -> assertEquals(new Color(255, 0, 10), playerList.getPlayerFromIndex(0).getPlayerColor()),
                () -> assertEquals(new Color(35, 254, 14), playerList.getPlayerFromIndex(1).getPlayerColor())
        );
   }

   @Test
   @DisplayName("S1.2: Rank Change Test")
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
              () -> assertEquals(PlayerRanks.KINGS, player.getPlayerRank())
      );
   }
}