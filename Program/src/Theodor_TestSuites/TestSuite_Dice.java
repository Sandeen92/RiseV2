package Theodor_TestSuites;


import entity.Dice;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSuite_Dice {

    private static Dice dice;
    @Before
    public static void setUpDice(){

        dice = new Dice();
    }

    @Test
    public void testSides(){

        ImageIcon[] diceImages = dice.getDiceImages();
        assertEquals(5, diceImages.length);
for (int i = 0; i < diceImages.length; i++) {
assertEquals(diceImages[i].get;