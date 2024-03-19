package entity.lan;

import controller.LanController;
import view.MainPanel;
import entity.player.PlayerList;
import controller.StartingScreen;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GameServer implements Runnable {

    private LanController lanController;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientHandlerPool;
    private boolean isRunning = false;
    private int i = 0;


    public GameServer(int port, LanController lanController) throws IOException {
        this.lanController = lanController;
        clientHandlerPool = new ArrayList<ClientHandler>();
        serverSocket = new ServerSocket(port);
    }

    public void run(){
        connection();
    }

    public void connection() {
        isRunning = true;
        try {
            while (isRunning) {
                Socket socket = serverSocket.accept();

                if (socket!=null) {
                    ClientHandler ch = new ClientHandler(socket, i);
                    clientHandlerPool.add(ch);
                    ch.start();
                    i++;
                }
            }
        } catch (IOException e) {

        }
    }

    public boolean IsRunning() {
        return isRunning;
    }



    /**
     * This inner class is responsible for handeling communication with one client
     */
    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private Object input;
        private int clientNumber;


        public ClientHandler(Socket socket, int nr) throws IOException {
            this.clientSocket = socket;
            this.clientNumber = nr;
        }

        public void initLobbyFrame(){
            try {
                oos.writeObject("lobbyClient,"+ clientNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendConnectedUserNamesAndColors(){
            try {
                PlayerList playerList = lanController.getPlayerList();
                ArrayList<String> playerNames = new ArrayList<String>();
                for (int i = 0; i < playerList.getLength(); i++) {
                    playerNames.add(playerList.getPlayerFromIndex(i).getName() + " ---- Color: " +
                            playerList.getPlayerFromIndex(i).getPlayerColorText(playerList.getPlayerFromIndex(i).getPlayerColor()));
                }
                oos.writeObject(playerNames);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        public void run() {
            try {

                this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
                this.ois = new ObjectInputStream(clientSocket.getInputStream());

                while (true) {
                    input = ois.readObject();

                    if (input instanceof String) {
                        if (String.valueOf(input).startsWith("UN")){
                            String temp = String.valueOf(input).substring(0);
                            String[] playerParts = temp.substring(2).split(",");
                            String userName = playerParts[0];
                            String color = playerParts[1];
                            lanController.addPlayer(userName, color);
                            if (clientNumber != 0) {
                                initLobbyFrame();
                            }
                        }
                        if (String.valueOf(input).equals("LobbyOK")) {
                            sendConnectedUserNamesAndColors();
                        }
                    }
                    oos.flush();
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    ois.close();
                    oos.close();
                    clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void main(String[] args) {
        new StartingScreen();
    }
}