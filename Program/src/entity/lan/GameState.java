package entity.lan;

import controller.BoardController;
import controller.LanController;
import entity.Dice;
import entity.player.Player;
import entity.player.PlayerList;

import java.util.List;

public class GameState {

    private PlayerList playerList;
    private Player activePlayer;
    private int stepsToTake;
    private GameClient gameClient;
    private List<GameClient> gameClientsList;
    private GameServer gameServer;
    private LanController lanController;
    private String identifier;
    //a problem right now can be that we can not be sure what the GameState object we're sending is for.
    //Maybe each GameState object can have an identifier like a String.

    public GameState(PlayerList playerList
            , Player activePlayer
            , GameClient gameClient
            , GameServer gameServer
            , LanController lanController) {

        this.playerList = playerList;
        this.activePlayer = activePlayer;
        this.gameClient = gameClient;
        this.gameServer = gameServer;
        this.lanController = lanController;
        identifier = "Initialization";
        gameClientsList = lanController.getGameClients();
    }

    /**
     * GameState that can be used to update the Board pieces for the fellow Clients.
     * @param stepsToTake x
     * @param gameClient x
     */
    public GameState(int stepsToTake, GameClient gameClient){
        identifier = "Piece Movement";
        //each GameClient has a BoardController instance, maybe make a getter that returns the BoardController instance
        //And then it's used to update the board components for that specific client?

        //in the Movement inner class, there's a PlayerList instance. However, maybe we can send the player for which the
        //game piece will be moved through this method?
        //The Movement inner class also updated the GUI.
        gameClient.getBoardController().movePlayer(gameClient.getUserName(), stepsToTake);

    }

    public GameState(boolean buttonValue, GameClient gameClient){
        identifier = "Button";
        gameClient.getBoardController().

    }
}
