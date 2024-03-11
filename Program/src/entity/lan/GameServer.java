package entity.lan;

import controller.LanController;
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

    public void sendConnectedUserNamesAndColorsToAllClients(){
        PlayerList playerList = lanController.getPlayerList();
        for (int i = 0; i < clientHandlerPool.size(); i++) {
            clientHandlerPool.get(i).sendConnectedUserNamesAndColors(playerList);
            System.out.println(clientHandlerPool.size());
        }
    }
    public void openBoardForEachClient(PlayerList playerList){
        System.out.println("Entering openBoardForEachClient method" + " clientHandlerPool size: " + clientHandlerPool.size());
        for (ClientHandler c : clientHandlerPool){
            System.out.println("Inside loop, sending playerList to client");
            try {
                if (!c.clientSocket.isClosed()) {
                    c.oos.writeObject(playerList);
                    c.oos.flush();

                    System.out.println("Skickar iväg playerlist till client:" + c.clientNumber);
                } else {
                    System.out.println("Socket is closed for client: " + c.clientNumber);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting openBoardForEachClient method");
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

        public void sendConnectedUserNamesAndColors(PlayerList playerList){
            try {
                ArrayList<String> playerNames = new ArrayList<String>();
                for (int i = 0; i < playerList.getLength(); i++) {
                    playerNames.add(playerList.getPlayerFromIndex(i).getName() + " ---- Color: " +
                            playerList.getPlayerFromIndex(i).getPlayerColorText(playerList.getPlayerFromIndex(i).getPlayerColor()));
                }
                oos.writeObject(playerNames);
                oos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        public void run() {
            try {

                this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
                this.ois = new ObjectInputStream(clientSocket.getInputStream());

                while (true) {
                    sleep(200);
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
                            sendConnectedUserNamesAndColorsToAllClients();
                        }
                    }
                    oos.flush();
                }

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
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