package org.simonscode.klausurserver.verzeichnis;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Vorlesungsverzeichnis_Test {
    String filename = "db_junit.txt";
    PrintWriter pw;

    Class<Vorlesung> c = Vorlesung.class; // Klasse Vorlesung vorhanden?

    @Before
    public void setUp() throws IOException {
        pw = new PrintWriter(filename);
    }

    @Test
    public void Titles() throws IOException, TextFileFormatException {
        List<String> al = new ArrayList<String>();

        al.add("Elektrodynamik");
        al.add("Quantenmechanik");
        al.add("Quantenphysik");
        al.add("Relativit�tstheorie");
        al.add("Theoretische Physik");
        al.add("Thermodynamik");

        pw.print(
                "A1:Relativit�tstheorie:Einstein:15\n" +
                        "B2:Quantenmechanik:Heisenberg:17\n" +
                        "C2:Quantenphysik:Planck:5\n" +
                        "T4:Thermodynamik:Kelvin:78\n" +
                        "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();


        Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
//		System.out.println(al);
//		System.out.println(l.titles());
        assertEquals(al, l.titles());
    }

    @Test
    public void Workaholics() throws IOException, TextFileFormatException {
        Set<String> s = new HashSet<String>();

        s.add("Planck");
        s.add("Kelvin");

        pw.print(
                "A1:Relativit�tstheorie:Einstein:15\n" +
                        "B2:Quantenmechanik:Heisenberg:17\n" +
                        "C2:Quantenphysik:Planck:5\n" +
                        "T4:Thermodynamik:Kelvin:78\n" +
                        "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();


        Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
//		System.out.println(s);
//		System.out.println(l.workaholics());
        assertEquals(s, l.workaholics());
    }

    @Test
    public void GroupToTitles() throws IOException, TextFileFormatException {
        Map<String, List<String>> map2 = new HashMap<String, List<String>>();
        List<String> s1 = new ArrayList<String>();
        List<String> s2 = new ArrayList<String>();
        List<String> s3a = new ArrayList<String>();
        List<String> s3b = new ArrayList<String>();
        List<String> s4a = new ArrayList<String>();
        List<String> s4b = new ArrayList<String>();

        s1.add("Relativit�tstheorie");
        map2.put("A1", s1);
        s2.add("Quantenmechanik");
        s2.add("Thermodynamik");
        s2.add("Elektrodynamik");
        map2.put("B2", s2);
        s3a.add("Quantenphysik");
        s3a.add("Theoretische Physik");
        s3b.add("Theoretische Physik");
        s3b.add("Quantenphysik");
        map2.put("C2", s3a);
        s4a.add("Thermodynamik");
        s4a.add("Quantenphysik");
        map2.put("T4", s4a);
        s4b.add("Quantenphysik");
        s4b.add("Thermodynamik");

        pw.print(
                "A1:Relativit�tstheorie:Einstein:15\n" +
                        "B2:Quantenmechanik:Heisenberg:17\n" +
                        "C2:Quantenphysik:Planck:5\n" +
                        "T4:Thermodynamik:Kelvin:78\n" +
                        "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();

        Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
//		System.out.println(map2);
//		System.out.println(l.groupToTitles());
//		assertEquals(map2,l.groupToTitles());
        assertEquals(s1, l.groupToTitles().get("A1"));
        assertTrue(s3a.equals(l.groupToTitles().get("C2")) || s3b.equals(l.groupToTitles().get("C2")));
        assertTrue(s4a.equals(l.groupToTitles().get("T4")) || s4b.equals(l.groupToTitles().get("T4")));
    }

    @Test
    public void multipleTitles() throws IOException, TextFileFormatException {
        Map<String, List<String>> map2 = new HashMap<String, List<String>>();
        List<String> s1a = new ArrayList<String>();
        List<String> s1b = new ArrayList<String>();

        s1a.add("Kelvin");
        s1a.add("Planck");
        s1b.add("Planck");
        s1b.add("Kelvin");
        map2.put("Thermodynamik", s1a);

        pw.print(
                "A1:Relativit�tstheorie:Einstein:15\n" +
                        "B2:Quantenmechanik:Heisenberg:17\n" +
                        "C2:Quantenphysik:Planck:5\n" +
                        "T4:Thermodynamik:Kelvin:78\n" +
                        "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();


        Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
//		System.out.println(map2);
//		System.out.println(l.multipleTitles());
//		assertEquals(map2,l.multipleTitles());
        assertTrue(s1a.equals(l.multipleTitles().get("Thermodynamik")) || s1b.equals(l.multipleTitles().get("Thermodynamik")));
    }

    @Test
    public void descendingTitles() throws IOException, TextFileFormatException {
        {
            List<String> al = new ArrayList<String>();

            al.add("Thermodynamik");
            al.add("Theoretische Physik");
            al.add("Quantenphysik");
            al.add("Thermodynamik");
            al.add("Elektrodynamik");
            al.add("Quantenmechanik");
            al.add("Relativit�tstheorie");
            al.add("Quantenphysik");

            pw.print(
                    "A1:Relativit�tstheorie:Einstein:15\n" +
                            "B2:Quantenmechanik:Heisenberg:17\n" +
                            "C2:Quantenphysik:Planck:5\n" +
                            "T4:Thermodynamik:Kelvin:78\n" +
                            "C2:Theoretische Physik:Kelvin:54\n" +
                            "B2:Thermodynamik:Planck:44\n" +
                            "T4:Quantenphysik:Planck:45\n" +
                            "B2:Elektrodynamik:Kelvin:34");
            pw.close();

            Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
//			System.out.println(al);
//			System.out.println(l.descendingTitles());
            assertEquals(al, l.descendingTitles());
        }
    }

    @Test(expected = TextFileFormatException.class)
    public void Liste_zu_kurz() throws IOException, TextFileFormatException {
        pw.print(
                "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();
        Vorlesungsverzeichnis k = new Vorlesungsverzeichnis(filename);
    }

    @Test(expected = TextFileFormatException.class)
    public void Liste_zu_lang() throws IOException, TextFileFormatException {
        pw.print(
                "C2:Theoretische Physik:Kelvin:54:55\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();
        Vorlesungsverzeichnis k = new Vorlesungsverzeichnis(filename);
    }

    @Test(expected = TextFileFormatException.class)
    public void Listenfeld_leer() throws IOException, TextFileFormatException {
        pw.print(
                "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        ":Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:34");
        pw.close();
        Vorlesungsverzeichnis k = new Vorlesungsverzeichnis(filename);
    }

    @Test(expected = TextFileFormatException.class)
    public void Teilnehmerzahl_keine_Zahl() throws IOException, TextFileFormatException {
        pw.print(
                "C2:Theoretische Physik:Kelvin:54\n" +
                        "B2:Thermodynamik:Planck:44\n" +
                        "T4:Quantenphysik:Planck:45\n" +
                        "B2:Elektrodynamik:Kelvin:ABC");
        pw.close();
        Vorlesungsverzeichnis k = new Vorlesungsverzeichnis(filename);
    }
}
