package plainsimple.space;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Test Class
 */
public class Main {

    public static void main(String[] args) {
        BufferedImage test_image = DrawSpace.drawSpace(1280, 800, 5, 255, 5);
        try {
            File saved = new File("SpaceTest.png");
            ImageIO.write(test_image, "png", saved);
            Desktop.getDesktop().open(saved);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
