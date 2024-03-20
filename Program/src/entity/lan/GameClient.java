package entity.lan;

import controller.BoardController;
import controller.LanController;
import controller.StartingScreen;
import entity.player.Player;
import entity.player.PlayerList;
import view.LobbyFrame;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameClient extends Thread {

    private BoardController boardController;
    private Socket socket;
    private Connection connection;
    private String userName;
    private String color;

    private GameState gameState;


    public GameClient(String userName, String color, String ip, int port) {
        this.userName = userName;
        this.color = color;
        try {
            socket = new Socket(ip, port);
            connect();
        } catch (IOException e) {
            System.out.println(userName + " cant connect");
        }
    }

    public String getUserName() {
        return userName;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void connect() {
        if (connection == null) {
            try {
                connection = new Connection(socket);

            } catch (IOException e) {

            }
        }
    }

    public void notifyNewTurnForPlayer(String playerName, Color playerColor) {
        connection.getListener().notifyNewPlayerTurn(playerName, playerColor);
    }

    public void notifyEndTurnForPlayer(String playerName, Color playerColor) {
        connection.getListener().notifyNewPlayerTurn(playerName, playerColor);
    }



    public class Connection {
        private Socket clientSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        private Listener listener;


        public Connection(Socket socket) throws IOException {
            clientSocket = socket;
            if(clientSocket != null){
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
            }
            listener = new Listener();
            listener.start();
            sendPlayerToServer();
        }

        public Listener getListener() {
            return listener;
        }

        public void sendPlayerToServer() {
            try {
                String o = "UN" + userName + "," + color;
                oos.writeObject(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendGameStateToServer() throws IOException {
            gameState = new GameState(boardController.getDiceSum(), boardController.getPlayerList().getActivePlayer());
            //lägg in  hela propertylistan från currentplayer i gamestate
            gameState.setPropertyList(boardController.getPlayerList().getActivePlayer().getProperties());
            //lägg in ranken i gamestate
            gameState.setRank(boardController.getPlayerList().getActivePlayer().getPlayerRank());
            //lägg in pengar i gamestate

            oos.writeObject(gameState);
        }


        private class Listener extends Thread {

            LobbyFrame lobbyFrame;

            public void run() {
                try {
                    while (true) {
                        Object o = ois.readObject();

                        if (o instanceof String) {
                            if (String.valueOf(o).startsWith("lobbyClient,")) {
                                String temp = String.valueOf(o).substring(0);
                                String[] playerParts = temp.substring(2).split(",");
                                int clientNr = Integer.parseInt(playerParts[1]);
                                lobbyFrame = new LobbyFrame();
                                lobbyFrame.initFrame(clientNr);
                                oos.writeObject("LobbyOK");
                            }else if (String.valueOf(o).equals("Your Turn")){

                            }
                        }

                        if (o instanceof PlayerList playerList) {
                            boardController = new BoardController(playerList);
                            boardController.addPlayerTabs();
                            boardController.startBoard();
                            lobbyFrame.dispose();
                        }

                        if (o instanceof ArrayList) {
                            for (int i = 0; i < ((ArrayList<String>) o).size(); i++){
                                lobbyFrame.appendLobby(((ArrayList<String>) o).get(i));
                            }
                        }
                        if (o instanceof GameState){
                            //Step 1 update movement
                            gameState = (GameState) o;
                            boardController.updateBoardAfterTurn(gameState);

                        }


                        oos.flush();
                        }
                    } catch (IOException | ClassNotFoundException e) {
                    System.out.println(userName + " disconnected");
                }
                 finally {
                    try {
                        ois.close();
                        oos.close();
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            public void notifyNewPlayerTurn(String playerName, Color playerColor) {

                for(Player player : boardController.getPlayerList().getListOfPlayers()){

                    if(player.getName().equals(playerName) && player.getPlayerColor().equals(playerColor)){
                        //TODO enable the buttons only for this user. (i think)
                        GameState state = new GameState();

                    }
                }

            }

        }
    }


    public static void main(String[] args) {
        new StartingScreen();
    }
}

