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
        DrawSpace drawSpace = new DrawSpace();
        drawSpace.setUseGradient(true);
        drawSpace.setBackgroundGradient(new GradientPaint(0, 0, new Color(8, 80, 120), 1280, 800, new Color(81, 160, 170)));
        BufferedImage test_image = drawSpace.drawSpace(1280, 800);
        try {
            File saved = new File("SpaceTest.png");
            ImageIO.write(test_image, "png", saved);
            Desktop.getDesktop().open(saved);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
