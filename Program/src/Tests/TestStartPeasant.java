package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerRanks;
import startMenu.StartingScreen;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestStartPeasant {
    Player player;
    StartingScreen startingScreen = new StartingScreen();

    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null, Color.blue,1);
    }
    @Test
    public void testIfPlayerIsPesant(){
        player.checkPlayerRank();
        assertEquals(PlayerRanks.PEASANT,player.getPlayerRank(),"Should be a Peasant");
    }
    @Test
    public void PesantRankUp(){
        player.setNetWorth(3000);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.KNIGHT,player.getPlayerRank(),"Should be a Knight");
    }

}
