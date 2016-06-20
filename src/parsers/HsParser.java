package parsers;

import gui.MainFrame;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * To jest klasa, która zajmuje się parsowaniem pliku zawierającego najlepsze wyniki(highscores) w celu ich wczytywania i edycji
 */
public final class HsParser {

    /** ilość rekordowych wyników dla każdego poziomu trudności */
    private static final int NUMBER_OF_RECORDS = 5;

    /**
     * metoda ładująca plik konfiguracyjny
     */
    public static Properties loadProperties() {
        String path = new File(".\\resources\\highscores.properties").getAbsolutePath();
        File file = new File(path);
        Properties properties = new Properties();
        InputStream sets;
        try {
            sets = new FileInputStream(file);
            properties.load(sets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * metoda pobierająca jeden rekord z pliku konfiguracyjnego
     */
    public static String[] getRecord(String diff, String n){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] record = new String[2];
        record[0] = properties.getProperty(diff+"_NAME"+n);
        record[1] = properties.getProperty(diff+"_POINTS"+n);
        return record;
    }
    /**
     * metoda pobierająca wszystkie rekordy na poziomie łatwym
     */
    public static String[] getEasy(){
        String[] easy = new String [NUMBER_OF_RECORDS];
        for(int i=0; i<NUMBER_OF_RECORDS; i++){
            String num = Integer.toString(i);
            easy[i] = Arrays.toString(getRecord("EASY",num));
        }
        return easy;
    }
    /**
     * metoda pobierająca wszystkie rekordy na poziomie średnim
     */
    public static String[] getMedium(){
        String[] medium = new String [NUMBER_OF_RECORDS];
        for(int i=0; i<NUMBER_OF_RECORDS; i++){
            String num = Integer.toString(i);
            medium[i] = Arrays.toString(getRecord("MEDIUM",num));
        }
        return medium;
    }
    /**
     * metoda pobierająca wszystkie rekordy na poziomie trudnym
     */
    public static String[] getHard(){
        String[] hard = new String [NUMBER_OF_RECORDS];
        for(int i=0; i<NUMBER_OF_RECORDS; i++){
            String num = Integer.toString(i);
            hard[i] = Arrays.toString(getRecord("HARD",num));
        }
        return hard;
    }
    /**
     * metoda służąca do edycji rekordów i zapisie pliku properties
     */
    public static void saveProperties(String nameKey, String scoreKey, String name, String score) {
        String path = new File("highscores.properties").getAbsolutePath();
        File file = new File(path);
        Properties properties = new Properties();
        OutputStream sets;
        try {
            sets = new FileOutputStream(file);
            properties.setProperty(nameKey, name);
            properties.setProperty(scoreKey, score);
            properties.store(sets, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * metoda zapisująca odpowiedni wynik do pliku konfiguracyjnego
     */
    public static void setScore(String n, String name, String score){
        saveProperties(Integer.toString(MainFrame.getDifficulty())+"_NAME"+n, Integer.toString(MainFrame.getDifficulty())+"_POINTS"+n, name, score);
    }

}
