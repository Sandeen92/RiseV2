package Theodor_TestSuites;


import controller.BoardController;
import entity.Dice;
import org.junit.Before;
import org.junit.Test;
import view.DicePanel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class contains unit tests for the Dice class.
 */
public class TestSuite_Dice {

    private BoardController boardController;
    private static Dice dice;

    /**
     * Sets up the Dice object for use in the tests.
     */
    @Before
    public void setUpDice() {
        dice = new Dice();
        boardController = new BoardController();
    }

    /**
     * Demand ID: T1.0
     * Tests that the Dice object has 6 sides.
     */
    @Test
    public void testDice_Sides() {
        assertEquals(6, dice.getDiceImages().length);
    }
    /**
     * Demand ID: T2.0
     * Tests that there is 2 dices
     */
    @Test
    public void test_2dices() {
      Dice dice1 = boardController.getDice1();
      Dice dice2 = boardController.getDice2();

        assertNotNull(dice1);
        assertNotNull(dice2);
    }

    /**
     * Demand ID: T2.0
     * Tests that the dice class method called roll() is called twice in the BoardController class when the user clicks the Roll Dice button in the Dice Panel class.
     */
    @Test
    public void test_DiceRollIsCalledTwice() {
        DicePanel dicePanel = new DicePanel(boardController);

        JButton btnRollDice = new JButton();
        btnRollDice.addActionListener(dicePanel);

        //Simulates a click on the Roll Dice button in the Dice Panel class.
        dicePanel.actionPerformed(new ActionEvent(btnRollDice, ActionEvent.ACTION_PERFORMED, ""));

        Dice dice1 = boardController.getDice1();
        Dice dice2 = boardController.getDice2();

        boardController.rollDices();

        //Checks that both Dice objects roll method has been called after BoardController.rollDices() is called.
        assertTrue(dice1.roll() > 0);
        assertTrue(dice2.roll() > 0);
    }



    /**
     * Demand ID: T1.0
     * Tests that a random roll of the Dice object is between 1 and 6.
     */
    @Test
    public void testRollBetween1And6() {
        int roll = dice.roll();
        assertTrue(roll >= 1 && roll <= 6);
    }

    /**
     * Tests that the value of the Dice object matches the result of a roll.
     */
    @Test
    public void testDiceImagesMatchValue() {
        for (int i = 0; i < 100; i++) {
            int roll = dice.roll();
            assertEquals(roll, dice.getValue());
        }
    }

    /**
     * Demand ID: T1.0
     * Tests that the rollDice button calls the rollDices method in the BoardController.
     */
    @Test
    public void testRollDiceButtonCallsRollDicesMethod() {
        DicePanel dicePanel = new DicePanel(boardController);

        JButton btnRollDice = new JButton();
        btnRollDice.addActionListener(dicePanel);

        //Simulates a click on the Roll Dice button in the Dice Panel class.
        dicePanel.actionPerformed(new ActionEvent(btnRollDice, ActionEvent.ACTION_PERFORMED, ""));

        //Confirm that the BoardController.rollDices() method has been called.
        assertTrue(boardController.rollDices() > 0);
    }

    /**
     * Demand ID: T3.0
     * Tests that the checkIfDouble method returns 0 for two 0 values.
     */
    @Test
    public void testRollDicesWithSameValuesOf0() {
        int test = boardController.checkIfDouble(0, 0);
        int expected = 0;

        assertEquals(expected, test);
    }

    /**
     * Demand ID: T3.0
     * Tests that the checkIfDouble method returns 4 for two 1 values.
     */
    @Test
    public void testRollDicesWithSameValuesOf1() {
        int test = boardController.checkIfDouble(1, 1);
        int expected = 4;

        assertEquals(expected, test);
    }

    /**
     * Demand ID: T3.0
     * Tests that the checkIfDouble method returns -4 for two -1 values.
     */
    @Test
    public void testRollDicesWithSameValuesOfNegative1() {
        int test = boardController.checkIfDouble(-1, -1);
        int expected = -4;

        assertEquals(expected, test);
    }

    /**
     * Demand ID: T3.0
     * Tests that the checkIfDouble method returns 16 for two 4 values.
     */
    @Test
    public void testRollDicesWithSameValuesOf4() {
        int test = boardController.checkIfDouble(4, 4);
        int expected = 16;

        assertEquals(expected, test);
    }

    /**
     * Demand ID: T3.0
     * Tests that the checkIfDouble method returns 20 for two 5 values.
     */
    @Test
    public void testRollDicesWithSameValuesOf5() {
        int test = boardController.checkIfDouble(5, 5);
        int expected = 20;

        assertEquals(expected, test);
    }

    /**
     * Demand ID: T3.0
     * Tests that the checkIfDouble method returns 24 for two 6 values.
     */
    @Test
    public void testRollDicesWithSameValuesOf6() {
        int test = boardController.checkIfDouble(6, 6);
        int expected = 24;

        assertEquals(expected, test);
    }

}