import javax.swing.*;

import gui.*;
import parsers.*;
import java.util.Arrays;

/**
 * Created by piotr on 19.05.2016.
 *
 */
public class MainClass {
    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> new MainFrame().initialize());
        //System.out.println(Arrays.toString(HsParser.getEasy()));
    }
}
