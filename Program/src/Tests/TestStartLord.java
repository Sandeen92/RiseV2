package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.player.Player;

import java.awt.*;
import static entity.player.PlayerRanks.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestStartLord {
    private Player player;

    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null, Color.blue,1);
        player.setNetWorth(5000);
    }

    @Test
    public void testIfPlayerIsLord(){
        player.checkPlayerRank();
        assertEquals(LORD,player.getPlayerRank(),"Should be a Lord");
    }
}
