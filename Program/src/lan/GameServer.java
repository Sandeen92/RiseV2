package lan;

import combinedPanels.GamePanels;
import player.PlayerList;
import startMenu.StartingScreen;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GameServer implements Runnable {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientHandlerPool;
    private int i = 0;
    private StartingScreen startingScreen;
    private GamePanels mainWindow;


    public GameServer(StartingScreen startingScreen, int port) throws IOException {
        clientHandlerPool = new ArrayList<ClientHandler>();
        serverSocket = new ServerSocket(port);
        this.startingScreen = startingScreen;
    }

    public void run(){
        connection();
    }

    public void connection() {
        try {
            while (true) {
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

    public void setMainWindow(GamePanels mainWindow) {
        this.mainWindow = mainWindow;
    }

    public ArrayList<ClientHandler> getClientHandlerPool() {
        return clientHandlerPool;
    }


    public void sendBoardToEachClient(){
        for (int i = 0; i < clientHandlerPool.size(); i++) {
            clientHandlerPool.get(i).sendBoard();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void assignPlayerListToEachClient(PlayerList playerList){
        for (int i = 0; i < clientHandlerPool.size(); i++) {
            clientHandlerPool.get(i).sendPlayerList(playerList);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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

        public void sendLobby() {
            try {
                oos.writeObject("Lobby");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendClientNameToLobby(String userName) {
            startingScreen.appendLobby(userName);
        }

        public void sendBoard(){
            try {
                oos.writeObject("Board");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendPlayerList(PlayerList playerList){
            try {
                oos.writeObject(playerList);
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
                        if (String.valueOf(input).startsWith("UN")) {
                            String userName = ((String) input).substring(2);
                            sendClientNameToLobby(userName);
                        }
                        if (String.valueOf(input).equals("connect")) {
                            sendLobby();
                        }
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
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
        StartingScreen su = new StartingScreen();
        Thread t = new Thread(su);
        t.start();
    }
}