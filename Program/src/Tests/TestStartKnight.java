package Tests;

import entity.StringColorMap;
import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static entity.player.PlayerRanks.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestStartKnight {
    private StringColorMap colorMap = new StringColorMap();
    private Player player;

    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null, Color.blue,1);
        player.setNetWorth(3000);
    }

    @Test
    public void testIfPlayerIsKnight(){
        player.checkPlayerRank();
        assertEquals(KNIGHT,player.getPlayerRank(),"Should be a Knight");
    }

    @Test
    public void KnightRankUp(){
        player.setNetWorth(5000);
        player.checkPlayerRank();
        assertEquals(LORD,player.getPlayerRank(),"Should be a Lord");
    }
}
