package connection;

import game.GameController;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


//Only used if the player chooses "Create server"
public class BattleshipServer extends Thread {

    private final int DEFAULT_PORT = 2000;

    private int serverPort;

    private String hostAddress = null;

    private GameController gameController = new GameController(this);

    private final List<ClientHandlerThread> CLIENT_THREADS = new LinkedList<>();

    private boolean isAlive = false;
    private boolean isAvalible = false;

//    public BattleshipServer(Socket BattleshipClientSocket) {
////        this.connection = BattleshipClientSocket;
//
//    }

    public BattleshipServer() {
        serverPort = DEFAULT_PORT;

    }

    private class ClientHandlerThread extends Thread {
        Socket connection;
        BufferedReader in;
        PrintWriter out;
        int threadID;

        public ClientHandlerThread(Socket connection, int threadID) {
            this.connection = connection;
            this.threadID = threadID;
        }

        @Override
        public void run() {

            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String msg;
                while ((msg = in.readLine()) != null) {
//                    System.out.println("klienttråd läser : "+msg+" trådID: "+threadID);
                    //validerar drag
                    gameController.validateMove(msg);
                    //skickar


//                    GUI.broadcastedMessage(connection.getInetAddress(), msg);
                    Thread.sleep(20);
                }

//				out.close();
                in.close();
                connection.close();

            } catch (IOException ioe) {

            } catch (InterruptedException ie) {

            }

            synchronized (CLIENT_THREADS) {
                removeKilledThreadFromList(this);
//                broadcastMessage(connection.getInetAddress(), "*CLIENT DISCONNECTED*");
//                GUI.onClientDisconnect(CLIENT_THREADS.size(), connection.getInetAddress().getHostName());
            }

        }

        //        private void outputMessage(InetAddress clientAddress, String msg) {
        private void outputMessage(String msg) {

//			StringBuilder sb = new StringBuilder();

//            String output = "";
//
//            if (clientAddress == null) {
////				sb.append("Anonymous");
//                output += "Anonymous";
//            } else {
////				sb.append(clientAddress);
//                output += clientAddress;
//            }

//			sb.append(": ");
//			sb.append(msg);



            try {
                //kan inte denna göras i run och sen gör man bara out.println(msg) här?
                out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()), true);
                out.println(msg);
            } catch (IOException e) {
                System.err.println("Fel i outputMessage: " + e);
            }
        }


    }

    private synchronized void removeKilledThreadFromList(Thread thread) {
        System.out.println("trådar i listan innan: " + CLIENT_THREADS.size());

        Thread toRemove = null;

        for (ClientHandlerThread client : CLIENT_THREADS) {
            if (client.getId() == thread.getId()) {
                toRemove = client;
            }
        }

        if (toRemove != null) {
            CLIENT_THREADS.remove(toRemove);
        }

        System.out.println("trådar i listan efter: " + CLIENT_THREADS.size());

    }

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            isAlive = true;
            hostAddress = serverSocket.getInetAddress().getLocalHost().getHostAddress();

//            GUI = new ServerGUI(InetAddress.getLocalHost().getHostName(), port);

            //sen går den över till klienttrådarna och väntar
            while (CLIENT_THREADS.size() < 2) {
                try {

                    isAvalible = true;
                    Socket clientConnection = serverSocket.accept();

                    ClientHandlerThread clientThread = new ClientHandlerThread(clientConnection, CLIENT_THREADS.size() + 1);
                    CLIENT_THREADS.add(clientThread);
                    clientThread.start();
//                    GUI.onNewClientConnected(CLIENT_THREADS.size(), clientConnection.getInetAddress().getHostName());

                    System.out.println("tillkopplad");
                    isAvalible = false;

                } catch (IOException ioe) {
                    System.err.println("Couldn't initialize new ClientHandlerThread: " + ioe);
                }

            }
            try{
            Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
            gameController.twoConnectedPlayers();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server: " + ioe);

        }
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAvalible() {
        return isAvalible;
    }

    public synchronized void broadcastMessage(String msg) {
//        String[] tokens = msg.split(" ");

//        System.out.println(Arrays.toString(tokens));

        for (ClientHandlerThread client : CLIENT_THREADS) {

            client.outputMessage(msg);
        }

    }

    public int getPort() {
        return serverPort;
    }


    public String getHostAddress() {

        return hostAddress;
    }


}




