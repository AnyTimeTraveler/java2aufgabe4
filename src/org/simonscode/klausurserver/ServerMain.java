package org.simonscode.klausurserver;

import org.simonscode.klausurserver.network.Datenspeicher;
import org.simonscode.klausurserver.network.KlausurenServer;

import java.io.File;
import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        KlausurenServer server = new KlausurenServer(2000);

        if (new File("data.bin").exists()) {
            Datenspeicher.load("data.bin");
        }

        server.start();


        // Dies sorgt dafuer, dass der Datenspeicher auch speichert, wenn man das Programm einfach so schliesst.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Datenspeicher.save("data.bin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}
