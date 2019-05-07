package org.simonscode.klausurserver.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class KlausurenServer extends Thread {

    private int port;
    private boolean running = true;
    private ServerSocket serverSocket;

    public KlausurenServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            while (running) {
                Socket clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(this, clientSocket);
                Thread clientThread = new Thread(clientHandler);
                clientThread.setName("ClientHandler");
                clientThread.start();
            }

        } catch (SocketException e) {
            System.out.println("Server heruntergefahren!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRunning() {
        running = false;
        try {
            Datenspeicher.save("data.bin");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
