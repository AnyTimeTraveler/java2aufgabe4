package org.simonscode.klausurserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TelnetClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2000);

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner ui = new Scanner(System.in);

        // Read message from User
        pw.println(ui.nextLine());
        // Read reply from Server
        System.out.println(br.readLine());

        // Exit
        pw.close();
        br.close();
        socket.close();
    }
}
