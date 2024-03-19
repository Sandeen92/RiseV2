package alex_tests.unit_test;
import entity.player.PlayerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerListTest {

    private PlayerList playerList;
    @BeforeEach
    public void setUp() {
        playerList = new PlayerList();
    }

    @Test
    @DisplayName("Test addNewPlayer")
    public void testAddNewPlayer() {
        assertAll(
                () -> {
                    playerList.addNewPlayer("Test Player", "red");
                    assertEquals(1, playerList.getLength(), "PlayerList should have 1 player");
                },
                () -> {
                    playerList.addNewPlayer("Test Player 2", "green");
                    assertEquals(2, playerList.getLength(), "PlayerList should have 2 players");
                }
        );
    }
    @Test
    @DisplayName("Test getPlayerFromIndex")
    public void testGetPlayerFromIndex() {
        playerList.addNewPlayer("Test Player", "red");
        playerList.addNewPlayer("Test Player 2", "green");
        assertAll(
                () -> assertEquals("Test Player", playerList.getPlayerFromIndex(0).getName(), "Player at index 0 should be Test Player"),
                () -> assertEquals("Test Player 2", playerList.getPlayerFromIndex(1).getName(), "Player at index 1 should be Test Player 2")
        );
    }
    @Test
    @DisplayName("Test getActivePlayer")
    public void testGetActivePlayer() {
        playerList.addNewPlayer("Test Player", "red");
        playerList.addNewPlayer("Test Player 2", "green");
        assertAll(
                () -> assertEquals("Test Player", playerList.getActivePlayer().getName(), "Active player should be Test Player"),
                () -> {
                    playerList.switchToNextPlayer();
                    assertEquals("Test Player 2", playerList.getActivePlayer().getName(), "Active player should be Test Player 2 after switching");
                }
        );
    }
}
