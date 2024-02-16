package lan;

import startMenu.StartingScreen;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameServer implements Runnable {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientHandlerPool;
    private int i = 0;


    public GameServer(int port) throws IOException {
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

        public void send() {
            try {
                String o = "Hello " + clientNumber;
                oos.writeObject(o);
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

                    if (input!= null) {
                        System.out.println(input);
                        send();
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                try {
                    ois.close();
                    oos.close();
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