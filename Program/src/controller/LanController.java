package controller;

import entity.lan.GameClient;
import entity.lan.GameServer;
import entity.player.Player;
import entity.player.PlayerList;
import view.LobbyFrame;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LanController {

    private GameServer gameServer;
    private BoardController boardController;
    private static ArrayList<GameClient> clientList;
    private static PlayerList playerList;
    private static LobbyFrame lobbyFrame;


    public LanController(){
        playerList = new PlayerList();
        clientList = new ArrayList<>();
        lobbyFrame = new LobbyFrame(this);
    }

    public void startGame(){
        boardController = new BoardController(playerList);
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
        GameClient gameClient = new GameClient(username, "0.0.0.0", 9090);
        playerList.addNewPlayer(username, color);
        clientList.add(gameClient);
        lobbyFrame.appendLobby(username);
    }

    public int getAmountOfConnectedClients(){
        return clientList.size();
    }

    public String getPlayerNameAt(int index){
        return playerList.getPlayerFromIndex(index).getName();
    }

    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }
}
