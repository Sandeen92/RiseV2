package controller.lan;

import entity.player.PlayerList;
import controller.StartingScreen;

import java.io.*;
import java.net.*;

public class GameClient extends Thread {
    private Socket socket;
    private Connection connection;
    private String userName;
    private StartingScreen startingScreen;


    public GameClient(StartingScreen startingScreen, String userName, String ip, int port) {
        this.userName = userName;
        this.startingScreen = startingScreen;
        try {
            socket = new Socket(ip, port);
            connect();
        } catch (IOException e) {
            System.out.println("Can not connect");
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

            sendConnect();
        }

        public void sendConnect() {
            try {
                String o = "connect";
                oos.writeObject(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendUserName() {
            try {
                String o = "UN" + userName;
                oos.writeObject(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        //TODO Måste skicka playerlist till alla clients på ngt sätt

        private class Listener extends Thread {

            public void run() {
                try {
                    while (true) {
                        Object o = ois.readObject();


                        if (o instanceof String) {
                            if (String.valueOf(o).equals("Board")) {
                                startingScreen.startUpLANGame();
                            }
                            if (String.valueOf(o).equals("Lobby")) {
                                sendUserName();
                            }
                        }
                        if (o instanceof PlayerList playerList) {
                            startingScreen.setPlayerList(playerList);
                        }
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
            }
        }


    public static void main(String[] args) {
        StartingScreen su = new StartingScreen();
        Thread t = new Thread(su);
        t.start();
    }
}

