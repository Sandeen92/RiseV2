package alex_tests;

import board.Board;
import combinedPanels.GamePanels;
import dice.Dice;
import eastSidePanels.EastSidePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.PlayerList;
import startMenu.BackgroundMusic;
import startMenu.StartingScreen;
import westSidePanel.WestSidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {
    Dice dice;
    PlayerList playerList = new PlayerList();
    @BeforeEach
    public void setUp() {
        playerList.addNewPlayer("Test Player", new ImageIcon());
        playerList.addNewPlayer("Test Player 2", new ImageIcon());
        dice = new Dice(new Board(new WestSidePanel()), playerList, new WestSidePanel(), new EastSidePanel());
        dice.addPlayerList(playerList);
    }
    @Test
    @DisplayName("S2.0 Roll Dice Test")
    public void testDiceRollButton() {
        int initialRoll = dice.getRoll();
        dice.actionPerformed(new ActionEvent(dice.btnRollDice, 0, "Roll Dice"));
        assertNotEquals(initialRoll, dice.getRoll(), "Dice roll value should change after button click");
    }
    @Test
    @DisplayName("S2.2 Only Roll Once Test")
    public void testDiceRollOnce() {
        dice.actionPerformed(new ActionEvent(dice.btnRollDice, 0, "Roll Dice"));
        assertFalse(dice.btnRollDice.isEnabled(), "Roll Dice button should be disabled after clicking it");
    }
    @Test
    @DisplayName("End Turn Test")
    public void testEndTurn(){
        dice.btnEndTurn.doClick();
        assertFalse(dice.btnEndTurn.isEnabled(), "End Button should be disabled after clicking it");
    }
    @Test
    @DisplayName("Generic Dice Class Test")
    public void testDiceClass() {
        assertAll("Dice",
                () -> {
                    int d1 = dice.roll();
                    int d2 = dice.roll();
                    dice.setRoll(d1 + d2);
                    int roll = dice.getRoll();
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
                }
        );
    }
}
