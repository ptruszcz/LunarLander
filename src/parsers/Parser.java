package parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * To jest klasa, która zajmuje się parsowaniem pliku konfiguracyjnego w celu budowy poziomów oraz
 * pobraniem podstawowych wartości dotyczących sterowaniem statku
 */
public final class Parser
{
    /**
     * metoda ładująca plik konfiguracyjny
     */
    public static Properties loadProperties() {
        String path = new File(".\\resources\\config.properties").getAbsolutePath();
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
     * metoda pobierająca dane o zmiennej, która decyduje o odświeżaniu gry
     */
    public static int getSpeed(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String speeds = properties.getProperty("SPEED");
        int speed = Integer.parseInt(speeds);
        return speed;
    }
    /**
     * metoda pobierająca liczbę poziomów
     */
    public static int getLastlevel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String lastlevels = properties.getProperty("LAST_LEVEL");
        int lastlevel = Integer.parseInt(lastlevels);
        return lastlevel;
    }
    /**
     * metoda pobierająca rozdzielczość X
     */
    public static int getXres(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String xress = properties.getProperty("XRES");
        int xres = Integer.parseInt(xress);
        return xres;
    }
    /**
     * metoda pobierająca rozdzielczość Y
     */
    public static int getYres(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String yress = properties.getProperty("YRES");
        int yres = Integer.parseInt(yress);
        return yres;
    }
    /**
     * metoda pobierająca ilość punktów do budowy planszy
     */
    public static int getPoints(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String pointss = properties.getProperty("POINTS");
        int points = Integer.parseInt(pointss);
        return points;
    }
    /**
     * metoda pobierająca daną odpowiadającą za siłę grawitacji
     */
    public static double getGravity(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String gravitys = properties.getProperty("GRAVITY");
        double gravity = Double.parseDouble(gravitys);
        return gravity;
    }
    /**
     * metoda pobierająca dane o maksymalnej prędkości Y
     */
    public static double getMaxyvel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String maxyvels = properties.getProperty("MAX_Y_VEL");
        double maxyvel = Double.parseDouble(maxyvels);
        return maxyvel;
    }
    /**
     * metoda pobierająca dane o maksymalnej prędkości X
     */
    public static double getMaxxvel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String maxxvels = properties.getProperty("MAX_X_VEL");
        double maxxvel = Double.parseDouble(maxxvels);
        return maxxvel;
    }
    /**
     * metoda pobierająca dane o sile silnika głównego
     */
    public static double getMaineng(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String mainegns = properties.getProperty("MAIN_ENGINE");
        double mainegn = Double.parseDouble(mainegns);
        return mainegn;
    }
    /**
     * metoda pobierająca dane o sile silnika bocznego
     */
    public static double getSideeng(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String sideegns = properties.getProperty("SIDE_ENGINE");
        double sideegn = Double.parseDouble(sideegns);
        return sideegn;
    }
    /**
     * metoda pobierająca dane o rozmiarze statku
     */
    public static int getShipsize(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String shipsizes = properties.getProperty("SHIP_SIZE");
        int shipsize = Integer.parseInt(shipsizes);
        return shipsize;
    }
    /**
     * metoda pobierająca dane o początkowej ilości paliwa
     */
    public static int getStartfuel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String startfuels = properties.getProperty("START_FUEL");
        int startfuel = Integer.parseInt(startfuels);
        return startfuel;
    }
    /**
     * metoda pobierająca dane o tym jak szybko tracimy paliwo używając
     * silników bocznych
     */
    public static int getFuelconsy(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String fuelconsys = properties.getProperty("FUEL_CONS_Y");
        int fuelconsy = Integer.parseInt(fuelconsys);
        return fuelconsy;
    }
    /**
     * metoda pobierająca dane o tym jak szybko tracimy paliwo używając
     * silnika głównego
     */
    public static int getFuelconsx(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String fuelconsxs = properties.getProperty("FUEL_CONS_X");
        int fuelconsx = Integer.parseInt(fuelconsxs);
        return fuelconsx;
    }
    /**
     * metoda pobierająca dane o ilości żyć
     */
    public static int getLives(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String livess = properties.getProperty("LIVES");
        int lives = Integer.parseInt(livess);
        return lives;
    }
    /**
     * metoda pobierająca współrzędne Y
     */
    public static double[] getYval(String level) {
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] ys = properties.getProperty("YVAL"+level).split(" ");
        double[] yval = Arrays.stream(ys).mapToDouble(Double::parseDouble).toArray();
        return yval;
    }
    /**
     * metoda pobierająca współrzędne statku w zależności od poziomu trudności
     */
    public static int[] getShipcoords(String level, String diff){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] ss = properties.getProperty("SHIP"+level+diff).split(" ");
        int[] shipcoords = Arrays.stream(ss).mapToInt(Integer::parseInt).toArray();
        return shipcoords;
    }
    /**
     * metoda pobierająca współrzędne bonusa, czyli dodatkowego życia
     */
    public static int[] getBonuscoords(String level){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        int[] bonuscoords = null;
        int action = Integer.parseInt(properties.getProperty("ISBONUS"+level));
        if (action==1)
        {
            String[] bs = properties.getProperty("BONUS" + level).split(" ");
            bonuscoords = Arrays.stream(bs).mapToInt(Integer::parseInt).toArray();
        }
        return bonuscoords;
    }
    /**
     * metoda pobierająca współrzędne lądowiska
     */
    public static int[] getSpotcoords(String level, String num){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] sc = properties.getProperty("LANDING_SPOT"+level+num).split(" ");
        int[] spotcoords = Arrays.stream(sc).mapToInt(Integer::parseInt).toArray();
        return spotcoords;
    }
    /**
     * metoda pobierająca rozmiar lądowiska
     */
    public static int getSpotsize(String level, String num){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String ssize = properties.getProperty("LANDING_SPOT_SIZE"+level+num);
        int spotsize = Integer.parseInt(ssize);
        return spotsize;
    }
    /**
     * metoda pobierająca punktację za każde lądowisko
     */
    public static int getSpotpoints(String level, String num){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String pointss = properties.getProperty("LANDING_SPOT_POINTS"+level+num);
        int points = Integer.parseInt(pointss);
        return points;
    }
}