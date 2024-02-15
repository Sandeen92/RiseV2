package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerRanks;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestStartLord {
    Player player;
    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null, Color.blue,1);
        player.setNetWorth(5000);
    }

    @Test
    public void testIfPlayerIsLord(){
        player.checkPlayerRank();
        assertEquals(PlayerRanks.LORD,player.getPlayerRank(),"Should be a Lord");
    }
}
