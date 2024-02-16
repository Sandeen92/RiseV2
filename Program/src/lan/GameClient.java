package lan;

import java.io.*;
import java.net.*;

public class GameClient extends Thread {
    private Socket socket;
    private Connection connection;
    private String userName;


    public GameClient(String userName, String ip, int port) {
        this.userName = userName;
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

            send();
        }

        public void send() {
            try {
                String o = userName;
                oos.writeObject(o);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        private class Listener extends Thread {

            public void run() {
                try {
                    while (true) {
                        Object o = ois.readObject();

                        if (o!= null) {
                            System.out.println(o);
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
        }

}

