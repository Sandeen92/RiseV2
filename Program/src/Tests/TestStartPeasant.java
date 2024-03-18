package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.player.Player;

import java.awt.*;
import static entity.player.PlayerRanks.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestStartPeasant {
    private Player player;

    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null, Color.blue,1);
    }
    @Test
    public void testIfPlayerIsPesant(){
        player.checkPlayerRank();
        assertEquals(PEASANT,player.getPlayerRank(),"Should be a Peasant");
    }
    @Test
    public void PesantRankUp(){
        player.setNetWorth(3000);
        player.checkPlayerRank();
        assertEquals(KNIGHT,player.getPlayerRank(),"Should be a Knight");
    }

}
