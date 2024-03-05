package controller;

import entity.lan.GameClient;
import entity.lan.GameServer;
import entity.player.PlayerList;
import view.LobbyFrame;

import java.io.IOException;
import java.util.ArrayList;

public class LanController {

    private GameServer gameServer;
    private BoardController boardController;
    private PlayerList playerList;
    private LobbyFrame lobbyFrame;


    public LanController(){
        playerList = new PlayerList();
        lobbyFrame = new LobbyFrame(this);
        lobbyFrame.initFrame(playerList.getLength());
    }

    public void startGame(){
        boardController = new BoardController(playerList);
        boardController.startBoard();
    }

    public void startServerAndConnectAsHost(String hostName, String hostColor) {
        try {
            gameServer = new GameServer(9090, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread gameServerThread = new Thread(gameServer);
        gameServerThread.start();
        createAndConnectClient(hostName, hostColor);
    }

    public static void createAndConnectClient(String username, String color){
        new GameClient(username, color, "0.0.0.0", 9090);
    }


    public void addPlayer(String name, String color) {
        playerList.addNewPlayer(name, color);
        lobbyFrame.appendLobby(name + " ---- Color: " + color);
    }

    public PlayerList getPlayerList(){
        return playerList;
    }
}
