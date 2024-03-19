package alex_tests;
import entity.Board;
import entity.Dice;
import entity.player.*;
import view.eastSidePanels.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {
    Dice dice1;
    Dice dice2;
    PlayerList playerList = new PlayerList();
    @BeforeEach
    public void setUp() {
        playerList.addNewPlayer("Test Player", "Green");
        playerList.addNewPlayer("Test Player 2","Red");
        dice1 = new Dice();
        dice2 = new Dice();
    }
    @Test
    @DisplayName("S2.0 Roll Dice Test")
    public void testDiceRollButton() {
        int initialRoll = dice1.getValue() + dice2.getValue();
        dice1.roll();
        dice2.roll();
        assertNotEquals(initialRoll,dice1.getValue() + dice2.getValue(), "Dice roll value should change after button click");
    }
    @Test
    @DisplayName("S2.1 Move Test")
    public void testMovement() {

    }
    @Test
    @DisplayName("S2.2 Only Roll Once Test")
    public void testDiceRollOnce() {

    }
    @Test
    @DisplayName("End Turn Test")
    public void testEndTurn(){

    }
    @Test
    @DisplayName("Generic Dice Class Test")
    public void testDiceClass() {
        assertAll(
                () -> assertEquals(1, dice1.getValue(), "Dice value should be 1"),
                () -> assertEquals(1, dice2.getValue(), "Dice value should be 1"),
                () -> assertNotNull(dice1.getCurrentValueImage(), "Dice image should not be null"),
                () -> assertNotNull(dice2.getCurrentValueImage(), "Dice image should not be null")
        );
    }
}
