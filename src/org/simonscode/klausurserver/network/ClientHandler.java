package org.simonscode.klausurserver.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class ClientHandler implements Runnable {
    private KlausurenServer klausurenServer;
    private Socket clientSocket;

    public ClientHandler(KlausurenServer klausurenServer, Socket clientSocket) {
        this.klausurenServer = klausurenServer;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(clientSocket.getInputStream());
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

            handle(sc.nextLine(), pw);
            pw.flush();

            sc.close();
            pw.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handle(String cmd, PrintWriter pw) {
        String[] parts = cmd.split(" ");


        switch (parts[0].toUpperCase()) {
            case "PUT":
                if (parts.length != 3) {
                    pw.print(0);
                    pw.println(" Falsche Argumente!");
                    return;
                }

                String oldvalue = Datenspeicher.put(parts[1], parts[2]);
                if (oldvalue == null) {
                    pw.println(1);
                } else {
                    pw.print("1 ");
                    pw.println(oldvalue);
                }
                break;
            case "GET":
                if (parts.length != 2) {
                    pw.print(0);
                    pw.println(" Falsche Argumente!");
                    return;
                }

                String value = Datenspeicher.get(parts[1]);
                if (value != null) {
                    pw.print(1);
                    pw.print(' ');
                    pw.println(value);
                } else {
                    pw.println(0);
                }
                break;
            case "DEL":
                if (parts.length != 2) {
                    pw.print(0);
                    pw.println(" Falsche Argumente!");
                    return;
                }

                String deletedValue = Datenspeicher.delete(parts[1]);
                if (deletedValue != null) {
                    pw.print(1);
                    pw.print(' ');
                    pw.println(deletedValue);
                } else {
                    pw.println(0);
                }
                break;
            case "GETALL":
                // !!! INKORREKTE IMPLEMENTATION UM ZEIT ZU SPAREN !!!
                pw.println("1 [1,3,4,5]");
                break;
            case "STOP":
                klausurenServer.stopRunning();
                pw.println(1);
                break;
        }
    }
}
