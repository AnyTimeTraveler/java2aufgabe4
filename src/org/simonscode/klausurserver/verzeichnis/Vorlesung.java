package org.simonscode.klausurserver.verzeichnis;

public class Vorlesung {
    private String gruppe;
    private String titel;
    private String dozent;
    private int teilnehmenzahl;

    public Vorlesung(String gruppe, String titel, String dozent, int teilnehmenzahl) {
        this.gruppe = gruppe;
        this.titel = titel;
        this.dozent = dozent;
        this.teilnehmenzahl = teilnehmenzahl;
    }

    public Vorlesung(String line) throws TextFileFormatException {
        String[] split = line.split(":");
        if (split.length != 4) {
            throw new TextFileFormatException("Length");
        }
        for (String item : split) {
            if (item.isBlank()) {
                throw new TextFileFormatException("Blank");
            }
        }

        gruppe = split[0];
        titel = split[1];
        dozent = split[2];
        try {
            teilnehmenzahl = Integer.parseInt(split[3]);
        } catch (NumberFormatException e) {
            throw new TextFileFormatException("Number");
        }
    }


    public String getGruppe() {
        return gruppe;
    }

    public void setGruppe(String gruppe) {
        this.gruppe = gruppe;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDozent() {
        return dozent;
    }

    public void setDozent(String dozent) {
        this.dozent = dozent;
    }

    public int getTeilnehmenzahl() {
        return teilnehmenzahl;
    }

    public void setTeilnehmenzahl(int teilnehmenzahl) {
        this.teilnehmenzahl = teilnehmenzahl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vorlesung vorlesung = (Vorlesung) o;

        if (teilnehmenzahl != vorlesung.teilnehmenzahl) return false;
        if (gruppe != null ? !gruppe.equals(vorlesung.gruppe) : vorlesung.gruppe != null) return false;
        if (titel != null ? !titel.equals(vorlesung.titel) : vorlesung.titel != null) return false;
        return dozent != null ? dozent.equals(vorlesung.dozent) : vorlesung.dozent == null;

    }

    @Override
    public int hashCode() {
        int result = gruppe != null ? gruppe.hashCode() : 0;
        result = 31 * result + (titel != null ? titel.hashCode() : 0);
        result = 31 * result + (dozent != null ? dozent.hashCode() : 0);
        result = 31 * result + teilnehmenzahl;
        return result;
    }

    @Override
    public String toString() {
        return "Vorlesung{" +
                "gruppe='" + gruppe + '\'' +
                ", titel='" + titel + '\'' +
                ", dozent='" + dozent + '\'' +
                ", teilnehmenzahl=" + teilnehmenzahl +
                '}';
    }
}
