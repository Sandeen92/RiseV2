package Tests;

import board.Board;
import dice.Dice;
import events.ManageEvents;
import messageGui.WinGui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerRanks;
import startMenu.StartingScreen;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class GeneralTests {
    WinGui winGui = new WinGui();
    Player player;
    Dice dice = new Dice();
    Board board;
    @BeforeEach
    public void beforeTest(){
        board = new Board();
        player = new Player("John",null,null,1);
    }

    @Test
    public void endTurn(){
        assertDoesNotThrow(() -> dice.getBtnEndTurn().doClick());
    }

    @Test
    public void bankruptcy(){
        player.setNetWorth(10000);
        assertTrue(winGui.getLblLblpic().isVisible());
    }
}
