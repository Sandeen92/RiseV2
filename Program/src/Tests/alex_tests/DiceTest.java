package Tests.alex_tests;
import controller.BoardController;
import entity.Dice;
import entity.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {
    private Dice dice;
    private BoardController boardController = new BoardController();

    @BeforeEach
    public void setUp() {
        boardController.addPlayerToList("Test Player", "RED");
        boardController.addPlayerToList("Test Player 2", "GREEN");
        dice = new Dice();
    }

    @Test
    @DisplayName("S2.0 Roll Dices Test")
    public void testDicesRoll() {
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        int diceValues = 0;
        boolean approved = true;

        for (int i = 0; i<10; i++) {
            diceValues = d1.roll() + d2.roll();
            if (diceValues < 2 || diceValues > 12) {
                approved = false;
            }
        }

        assertTrue(approved, "Dice roll should be between 2 and 12");
    }
    @Test
    @DisplayName("S2.1 Move Test")
    public void testMovement() {
        Player activePlayer = boardController.getPlayerList().getActivePlayer();
        int initialPosition = activePlayer.getPosition();
        activePlayer.setPosition(initialPosition + 5);
        int finalPosition = activePlayer.getPosition();
        assertEquals(initialPosition + 5, finalPosition, "Player position should change after rolling dice");
    }
    @Test
    @DisplayName("S2.2 Only Roll One Dice Test")
    public void testOneDiceRoll() {
        int diceValue = 0;
        boolean approved = true;

        for (int i = 0; i<10; i++) {
            diceValue = dice.roll();
            if (diceValue < 1 || diceValue > 6) {
                approved = false;
            }
        }

        assertTrue(approved, "Dice roll should be between 1 and 6");
    }

 /*   @Test
    @DisplayName("Generic Dice Class Test")
        assertAll("Dice",
                () -> {

                    int d1 = dice.roll();

                    int d2 = dice.roll();

                    dice.setRoll(d1 + d2);

                    int roll = dice.getRoll();

                    int roll = boardController.rollDices();
                    assertTrue(roll >= 2 && roll <= 12, "Roll value should be between 2 and 12");

                },

                () -> {

                    dice.endTurn();

                    assertTrue(dice.btnRollDice.isEnabled(), "Roll Dice button should be enabled after ending turn");

                    assertFalse(dice.btnEndTurn.isEnabled(), "End Turn button should be disabled after ending turn");

                },

                () -> {

                    int roll = dice.getRoll();

                    assertTrue(roll >= 2 && roll <= 12, "Roll value should be between 2 and 12");

                },

                () -> dice.activateRollDice(),

                () -> assertTrue(dice.btnRollDice.isEnabled(), "Should be true after called"),

                () -> assertFalse(dice.btnEndTurn.isEnabled(), "Should be false after called"),

                () -> {

                    PlayerList testList = new PlayerList();

                    testList.addNewPlayer("Test Player", new ImageIcon());

                    assertEquals(testList, dice.setPlayerList(testList), "PlayerList should be set to testList");
                }
    }

  */
}
