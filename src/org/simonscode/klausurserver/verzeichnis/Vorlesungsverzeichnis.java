package org.simonscode.klausurserver.verzeichnis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Vorlesungsverzeichnis {

    List<Vorlesung> vorlesungen = new ArrayList<>();

    public Vorlesungsverzeichnis(String filename) throws IOException, TextFileFormatException {
        for (String line : Files.readAllLines(Path.of(filename))) {
            vorlesungen.add(new Vorlesung(line));
        }
    }

    public List<String> titles() {
        return vorlesungen.stream()
                .map(Vorlesung::getTitel)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    public Set<String> workaholics() {
        return vorlesungen.stream()
                .collect(Collectors.groupingBy(Vorlesung::getDozent, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(stringLongEntry -> stringLongEntry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Map<String, List<String>> groupToTitles() {
        return vorlesungen.stream()
                .collect(Collectors.groupingBy(Vorlesung::getGruppe,
                        Collectors.toList()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .map(Vorlesung::getTitel)
                                .collect(Collectors.toList())
                ));
    }

    public Map<String, List<String>> multipleTitles() {
        return vorlesungen.stream()
                .collect(
                        Collectors.groupingBy(Vorlesung::getTitel,
                                Collectors.toList())
                ).entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .map(Vorlesung::getDozent)
                                .collect(Collectors.toList())
                ));
    }

    public List<String> descendingTitles() {
        return vorlesungen.stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(Vorlesung::getTeilnehmenzahl)))
                .map(Vorlesung::getTitel)
                .collect(Collectors.toList());
    }
}
