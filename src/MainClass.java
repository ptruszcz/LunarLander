import javax.swing.*;

import gui.*;

/**
 * Created by piotr on 19.05.2016.
 *
 */
public class MainClass {
    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> new MainFrame().initialize());

    }
}
