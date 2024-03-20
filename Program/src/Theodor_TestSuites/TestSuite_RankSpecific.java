package Theodor_TestSuites;

import entity.player.Player;
import org.junit.Before;
import org.junit.Test;
import utilities.Constants;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestSuite_RankSpecific {

    @Test
    public void testPlayerIconChange() {

        Color color = new Color(255, 0, 10, 255);
        Player player = new Player("TestPlayer", Constants.PlayerTokenImages.RED_PAWN, color, 0 );

        player.setNetWorth(1700);
        player.checkPlayerRank();
        assertEquals(Constants.PlayerTokenImages.RED_KNIGHT, player.getImage());

        player.setNetWorth(4100);
        player.checkPlayerRank();
        assertEquals(Constants.PlayerTokenImages.RED_ROOK, player.getImage());

        player.setNetWorth(7500);
        player.checkPlayerRank();
        assertEquals(Constants.PlayerTokenImages.RED_KING, player.getImage());

        player.setNetWorth(5000);
        player.checkPlayerRank();
        assertEquals(Constants.PlayerTokenImages.RED_ROOK, player.getImage());
        
        player.setNetWorth(2500);
        player.checkPlayerRank();
        assertEquals(Constants.PlayerTokenImages.RED_KNIGHT, player.getImage());

        player.setNetWorth(1000);
        player.checkPlayerRank();
        assertEquals(Constants.PlayerTokenImages.RED_PAWN, player.getImage());
    }
}
