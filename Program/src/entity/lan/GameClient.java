package entity.lan;

import controller.StartingScreen;
import view.LobbyFrame;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameClient extends Thread {

    private Socket socket;
    private Connection connection;
    private String userName;
    private String color;


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


    public void connect() {
        if (connection == null) {
            try {
                connection = new Connection(socket);

            } catch (IOException e) {

            }
        }
    }



    public class Connection {
        private Socket clientSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;


        public Connection(Socket socket) throws IOException {
            clientSocket = socket;
            if(clientSocket != null){
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
            }
            new Listener().start();
            sendPlayerToServer();
        }



        public void sendPlayerToServer() {
            try {
                String o = "UN" + userName + "," + color;
                oos.writeObject(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        private class Listener extends Thread {

            LobbyFrame lobbyFrame;

            public void run() {
                try {
                    while (true) {
                        Object o = ois.readObject();
                        sleep(100);

                        if (o instanceof String) {
                            if (String.valueOf(o).startsWith("lobbyClient,")) {
                                String temp = String.valueOf(o).substring(0);
                                String[] playerParts = temp.substring(2).split(",");
                                int clientNr = Integer.parseInt(playerParts[1]);
                                lobbyFrame = new LobbyFrame();
                                lobbyFrame.initFrame(clientNr);
                                oos.writeObject("LobbyOK");
                            }
                        }

                        if (o instanceof ArrayList) {
                            for (int i = 0; i < ((ArrayList<String>) o).size(); i++){
                                lobbyFrame.appendLobby(((ArrayList<String>) o).get(i));
                            }
                        }
                        oos.flush();
                        }
                    } catch (IOException | ClassNotFoundException e) {
                    System.out.println(userName + " disconnected");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        ois.close();
                        oos.close();
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            }
        }


    public static void main(String[] args) {
        new StartingScreen();
    }
}

