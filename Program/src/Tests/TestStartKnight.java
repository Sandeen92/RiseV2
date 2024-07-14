package Tests;


import colorsAndIcons.StringColorMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerRanks;
import startMenu.StartingScreen;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestStartKnight {
    private StringColorMap colorMap = new StringColorMap();
    Player player;
    StartingScreen startingScreen = new StartingScreen();
    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null, Color.blue,1);
        player.setNetWorth(3000);
    }

    @Test
    public void testIfPlayerIsKnight(){
        player.checkPlayerRank();
        assertEquals(PlayerRanks.KNIGHT,player.getPlayerRank(),"Should be a Knight");
    }

    @Test
    public void KnightRankUp(){
        player.setNetWorth(5000);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.LORD,player.getPlayerRank(),"Should be a Lord");
    }
}
