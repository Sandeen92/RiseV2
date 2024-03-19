package TestSuites_by_Theodor;

import controller.LanController;
import controller.StartingScreen;
import entity.lan.GameServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;


import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuite_IN_Demands {

    private StartingScreen startingScreen;
    private StartingScreen.ButtonListener buttonListenerMock;

    @Before
    public void setUp(){
        startingScreen = new StartingScreen();
    }

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
     * Starts a local game.
     *
     * This method sets up the local game by creating a new LanController object and starting it. It also adds the necessary components to the
     * background panel to allow the user to start the game.
     *
     * @author Theodor
     */
    @Test
    public void startUpLocalGame() {

        // Act
        startingScreen.setUpLocal();

        // Assert
        // TODO: Add assertions for the components added to the background label1

        assertTrue(startingScreen.getLocalSetUpOK());
    }


    /*
     * Demand: IN1.0, IN1.1
     */
    @Test
    public void radioButtonsLocal() {

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

    /**
     *  IN1.2
     */
   @Test
   public void testConfirmButtonLocal2() {
        startingScreen.setUpLocal();
        startingScreen.getRadioButtons()[0].setSelected(true); //Simulate selecting 2 players

       assertEquals("Confirm", startingScreen.getBtnConfirm().getText()); //Verify that the confirm button says confirm

        startingScreen.getBtnConfirm().doClick(); //Simulate clicking the confirm button

       assertEquals(2, startingScreen.getAmountOfPlayers()); //Verify that the amount of players is 2
    }

    /**
     *  IN1.2
     */
    @Test
   public void testConfirmButtonLocal3() {
        startingScreen.setUpLocal();
        startingScreen.getRadioButtons()[1].setSelected(true); //Simulate selecting 2 players

       assertEquals("Confirm", startingScreen.getBtnConfirm().getText()); //Verify that the confirm button says confirm

        startingScreen.getBtnConfirm().doClick(); //Simulate clicking the confirm button

       assertEquals(3, startingScreen.getAmountOfPlayers()); //Verify that the amount of players is 2
    }

    /**
     *   IN1.2
     */
    @Test
    public void testConfirmButtonLocal4() {
        startingScreen.setUpLocal();
        startingScreen.getRadioButtons()[2].setSelected(true); //Simulate selecting 2 players

       assertEquals("Confirm", startingScreen.getBtnConfirm().getText()); //Verify that the confirm button says confirm

        startingScreen.getBtnConfirm().doClick(); //Simulate clicking the confirm button

       assertEquals(4, startingScreen.getAmountOfPlayers()); //Verify that the amount of players is 2
    }

    /**
     *     IN2.0
     */
    @Test
    public void test_JTextField() {

        JTextField[] PlayerTextField = startingScreen.getPlayerTextField();

        for (int i = 0; i < PlayerTextField.length; i++) {
            assertNotNull(PlayerTextField[i]);
            assertEquals(PlayerTextField[i].getText(), "Name" + (i+1) + "...");
            assertTrue(PlayerTextField[i].isVisible());
            assertTrue(PlayerTextField[i].isEnabled());
            assertTrue(PlayerTextField[i].isEditable());
        }
    }

    /**
     * TestID:? Varför bara inte ha kravID.
     * KravID: IN 3.0
     *
     * This test verifies that each JCombobox in the starting screen:
     * 1. Is not null
     * 2. Is visible
     * 3. Is enabled
     * Additionally, it checks that each JComboBox contains exactly the same colors as those
     * obtained from the colors array
     */
    @Test
    public void test_JComboBox() {
        JComboBox[] playerColors = startingScreen.getPlayerColors();
        String[] colors = startingScreen.getColors();

        assertNotNull(playerColors);
        assertNotNull(colors);

        for (JComboBox comboBox: playerColors) {
            assertNotNull(comboBox);
            assertTrue(comboBox.isVisible());
            assertTrue(comboBox.isEnabled());
            for (int i = 0; i < comboBox.getItemCount(); i++) {
                assertEquals(colors[i], comboBox.getItemAt(i));
            }
        }
    }

    /**
     * IN 4.0
     */
    @Test
    public void test_StartButtonAction(){

        startingScreen.setUpLocal();

        startingScreen.getRadioButtons()[0].doClick();
        startingScreen.getPlayerTextField()[0].setText("Name1");
        startingScreen.getPlayerTextField()[1].setText("Name2");
        startingScreen.getPlayerColors()[0].setSelectedItem("RED");
        startingScreen.getPlayerColors()[1].setSelectedItem("GREEN");
        startingScreen.getBtnConfirm().doClick();

        startingScreen.getBtnStartGame().doClick();

        assertFalse(startingScreen.isVisible());
    }

    /**
     * IN 5.0
     */
    @Test
    public void test_ResetButton(){
        startingScreen.setUpLocal();

        startingScreen.getRadioButtons()[0].doClick();
        startingScreen.getPlayerTextField()[0].setText("Name1");
        startingScreen.getPlayerTextField()[1].setText("Name2");
        startingScreen.getPlayerColors()[0].setSelectedItem("RED");
        startingScreen.getPlayerColors()[1].setSelectedItem("GREEN");
        startingScreen.getBtnConfirm().doClick();

        startingScreen.getBtnReset().doClick();

        assertTrue(startingScreen.getBtnConfirm().isEnabled());

        assertTrue(startingScreen.getRadioButtons()[0].isVisible());
        assertTrue(startingScreen.getRadioButtons()[0].isEnabled());

        assertTrue(startingScreen.getRadioButtons()[1].isVisible());
        assertTrue(startingScreen.getRadioButtons()[1].isEnabled());

        assertTrue(startingScreen.getRadioButtons()[2].isVisible());
        assertTrue(startingScreen.getRadioButtons()[2].isEnabled());

    }


    //Tests for LAN


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

        // Act
        startingScreen.startUpLANGame();

        // Assert
        // TODO: Add assertions for the components added to the background label1

        LanController lanController = startingScreen.getLanController();
        GameServer gameServer = lanController.getGameServer();

        assertNotNull(gameServer);
        assertTrue(gameServer.IsRunning());

    }
    @Test
    public void test_createLAN(){

        startingScreen.getBtnLAN().doClick();

        startingScreen.getBtnCreateGame().doClick();

        JRadioButton[] radioButtons = startingScreen.getRadioButtons();



        for (int i = 0; i < radioButtons.length; i++) {
            assertNotNull(radioButtons[i]);
            assertEquals(radioButtons[i].getText(), String.valueOf(i + 2));
        }

        JComboBox[] playerColors = startingScreen.getPlayerColors();
        String[] colors = startingScreen.getColors();

        assertNotNull(playerColors);
        assertNotNull(colors);

        for (JComboBox comboBox: playerColors) {
            assertNotNull(comboBox);
            assertTrue(comboBox.isVisible());
            assertTrue(comboBox.isEnabled());
            for (int i = 0; i < comboBox.getItemCount(); i++) {
                assertEquals(colors[i], comboBox.getItemAt(i));
            }
        }

        assertTrue(startingScreen.getHostGame().isVisible());
        assertTrue(startingScreen.getHostGame().isEnabled());
       // startingScreen.getHostGame().doClick();

    }
}

