package plainsimple.space;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Draws space.
 */
public class DrawSpace {

    // color of stars
    private static Color starColor = Color.WHITE;

    // color of background
    private static Color backgroundColor = Color.BLACK;

    // used for random number generation
    private static Random random = new Random();

    public static BufferedImage drawSpace(int imgWidth, int imgHeight, double density,
                                          int brightness, int size) {
        BufferedImage generated = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = generated.createGraphics();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, imgWidth, imgHeight);
        int num_stars = imgWidth * imgHeight / 50 / 50;
        for (int i = 0; i < num_stars; i++) {
            drawStar(graphics, random.nextInt(imgWidth), random.nextInt(imgHeight), brightness, size);
        }
        return generated;
    }

    private static void drawStar(Graphics2D graphics, int x, int y, int brightness, int size) {
        graphics.setColor(starColor);
        graphics.setColor(new Color(starColor.getRed(), starColor.getBlue(), starColor.getGreen(), brightness));
        graphics.fillOval(x, y, size, size);
    }
}
