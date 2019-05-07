package org.simonscode.klausurserver.network;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Vereinfachter Datenspeicher fuer Demo-Zwecke
 */
public class Datenspeicher {

    private static Map<String, String> register = new HashMap<>();

    public static void load(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));

        //noinspection unchecked
        register = (Map<String, String>) ois.readObject();

        ois.close();
    }

    public static void save(String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));

        oos.writeObject(register);
        oos.close();
    }


    public static String put(String key, String value) {
        return register.put(key, value);
    }

    public static String get(String key) {
        return register.get(key);
    }

    public static String delete(String key) {
        return register.remove(key);
    }
}
