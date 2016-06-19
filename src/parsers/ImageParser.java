package parsers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by piotr on 19.06.2016.
 *
 */
public class ImageParser {
    static public Image loadImage(String imageName) {
        Image image = null;
        InputStream inputStream = ImageParser.class.getResourceAsStream("/" + imageName);

        try{
            image = ImageIO.read(inputStream);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return image;
    }
}
