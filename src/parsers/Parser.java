package parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public final class Parser
{
    public static Properties loadProperties() {
        String path = new File("config.properties").getAbsolutePath();
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

    public static int getSpeed(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String speeds = properties.getProperty("SPEED");
        int speed = Integer.parseInt(speeds);
        return speed;
    }

    public static int getXres(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String xress = properties.getProperty("XRES");
        int xres = Integer.parseInt(xress);
        return xres;
    }

    public static int getYres(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String yress = properties.getProperty("YRES");
        int yres = Integer.parseInt(yress);
        return yres;
    }

    public static int getPoints(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String pointss = properties.getProperty("POINTS");
        int points = Integer.parseInt(pointss);
        return points;
    }

    public static double getGravity(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String gravitys = properties.getProperty("GRAVITY");
        double gravity = Double.parseDouble(gravitys);
        return gravity;
    }

    public static double getMaxyvel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String maxyvels = properties.getProperty("MAX_Y_VEL");
        double maxyvel = Double.parseDouble(maxyvels);
        return maxyvel;
    }

    public static double getMaxxvel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String maxxvels = properties.getProperty("MAX_X_VEL");
        double maxxvel = Double.parseDouble(maxxvels);
        return maxxvel;
    }

    public static double getMaineng(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String mainegns = properties.getProperty("MAIN_ENGINE");
        double mainegn = Double.parseDouble(mainegns);
        return mainegn;
    }

    public static double getSideeng(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String sideegns = properties.getProperty("SIDE_ENGINE");
        double sideegn = Double.parseDouble(sideegns);
        return sideegn;
    }

    public static int getShipsize(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String shipsizes = properties.getProperty("SHIP_SIZE");
        int shipsize = Integer.parseInt(shipsizes);
        return shipsize;
    }

    public static int getStartfuel(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String startfuels = properties.getProperty("START_FUEL");
        int startfuel = Integer.parseInt(startfuels);
        return startfuel;
    }

    public static int getFuelconsy(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String fuelconsys = properties.getProperty("FUEL_CONS_Y");
        int fuelconsy = Integer.parseInt(fuelconsys);
        return fuelconsy;
    }

    public static int getFuelconsx(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String fuelconsxs = properties.getProperty("FUEL_CONS_X");
        int fuelconsx = Integer.parseInt(fuelconsxs);
        return fuelconsx;
    }

    public static int getLives(){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String livess = properties.getProperty("LIVES");
        int lives = Integer.parseInt(livess);
        return lives;
    }

    public static double[] getYval(String level) {
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] ys = properties.getProperty("YVAL"+level).split(" ");
        double[] yval = Arrays.stream(ys).mapToDouble(Double::parseDouble).toArray();
        return yval;
    }

    public static int[] getShipcoords(String level, String diff){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] ss = properties.getProperty("SHIP"+level+diff).split(" ");
        int[] shipcoords = Arrays.stream(ss).mapToInt(Integer::parseInt).toArray();
        return shipcoords;
    }

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

    public static int[] getSpotcoords(String level, String diff){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String[] sc = properties.getProperty("LANDING_SPOT"+level+diff).split(" ");
        int[] spotcoords = Arrays.stream(sc).mapToInt(Integer::parseInt).toArray();
        return spotcoords;
    }

    public static int getSpotsize(String level, String diff){
        Properties properties = loadProperties();
        System.setProperty("file.encoding", "UTF-8");
        String ssize = properties.getProperty("LANDING_SPOT_SIZE"+level+diff);
        int spotsize = Integer.parseInt(ssize);
        return spotsize;
    }
}