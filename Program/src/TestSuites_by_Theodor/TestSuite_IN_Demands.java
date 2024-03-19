package TestSuites_by_Theodor;

import controller.LanController;
import controller.StartingScreen;
import entity.lan.GameServer;
import org.junit.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuite_IN_Demands {


    /**
     * TestID: ? Varför bara inte ha kravID
     *
     * Demand: IN6.0
     *
     * Tests the chooseLocalOrNetwork method in the StartingScreen class.
     *
     * This test case verifies that the method adds the correct components to the background panel when the user chooses to play locally or over a network.
     *
     * @author Theodor
     */
    @Test
    public void chooseLocalOrNetworkTest() {

        // Arrange
        StartingScreen startingScreen = new StartingScreen();

        // Act
        startingScreen.chooseLocalOrNetwork();

        // Assert
        // TODO: Add assertions for the components added to the background label

        JButton btnLocal = startingScreen.getBtnLocal();
        JButton btnLAN = startingScreen.getBtnLAN();

        assertNotNull(btnLocal);
        assertEquals(btnLocal.getText(), "Local");
        assertTrue(btnLocal.isVisible());
        assertTrue(btnLocal.isEnabled());
        assertFalse(btnLocal.isSelected());

        assertNotNull(btnLAN);
        assertEquals(btnLAN.getText(), "LAN");
        assertTrue(btnLAN.isVisible());
        assertTrue(btnLAN.isEnabled());
        assertFalse(btnLAN.isSelected());
    }
    /**
     * Demand: IN4.0
     *
     * Starts a LAN game.
     *
     * This method sets up the LAN game by creating a new LanController object and starting it. It also adds the necessary components to the
     * background panel to allow the user to start the game.
     *
     * @author Theodor
     */
    @Test
    public void startUpLANGame() {

        // Arrange
        StartingScreen startingScreen = new StartingScreen();

        // Act
        startingScreen.startUpLANGame();

        // Assert
        // TODO: Add assertions for the components added to the background label1

        LanController lanController = startingScreen.getLanController();
        GameServer gameServer = lanController.getGameServer();

        assertNotNull(gameServer);
        assertTrue(gameServer.IsRunning());

    }
    /**
     * Demand: IN4.0
     *
     * Starts a local game.
     *
     * This method sets up the local game by creating a new LanController object and starting it. It also adds the necessary components to the
     * background panel to allow the user to start the game.
     *
     * @author Theodor
     */
    @Test
    public void startUpLocalGame() {

        // Arrange
        StartingScreen startingScreen = new StartingScreen();

        // Act
        startingScreen.setUpLocal();

        // Assert
        // TODO: Add assertions for the components added to the background label1

        assertTrue(startingScreen.getLocalSetUpOK());
    }

    @Test
    public void numberOfPlayersLocal() {

        // Arrange
        StartingScreen startingScreen = new StartingScreen();

        // Act
        startingScreen.setUpLocal();

        // Assert
        JRadioButton[] radioButtons = startingScreen.getRadioButtons();

        for (int i = 0; i < radioButtons.length; i++) {
            assertNotNull(radioButtons[i]);
            assertEquals(radioButtons[i].getText(), String.valueOf(i + 2));
        }
        assertEquals(radioButtons.length, 3);
        assertEquals(radioButtons[0].getText(), "2");
        assertEquals(radioButtons[1].getText(), "3");
        assertEquals(radioButtons[2].getText(), "4");
        assertFalse(radioButtons[0].isSelected());
        assertFalse(radioButtons[1].isSelected());
        assertFalse(radioButtons[2].isSelected());
        assertTrue(radioButtons[0].isEnabled());
        assertTrue(radioButtons[1].isEnabled());
        assertTrue(radioButtons[2].isEnabled());
        assertTrue(radioButtons[0].isVisible());
        assertTrue(radioButtons[1].isVisible());
        assertTrue(radioButtons[2].isVisible());

    }

    

}