package Tests;


import controller.BoardController;
import entity.Dice;
import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.DicePanel;
import view.messageGui.WinGui;


import static org.junit.jupiter.api.Assertions.*;
public class GeneralTests {
    private WinGui winGui = new WinGui();
    private Player player;
    private BoardController boardController = new BoardController();
    private Dice dice = new Dice();
    private DicePanel dp = new DicePanel(boardController);

    @BeforeEach
    public void beforeTest(){
        player = new Player("John",null,null,1);
    }

    @Test
    public void endTurn(){
        assertDoesNotThrow(() -> dp.getBtnEndTurn().doClick());
    }

    @Test
    public void bankruptcy(){
        player.setNetWorth(10000);
        assertTrue(winGui.getLblLblpic().isVisible());
    }
}
