package plainsimple.space;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Draws space.
 */
public class DrawSpace {

    // color of stars
    private Color starColor = Color.WHITE;
    // color of background
    private Color backgroundColor = Color.BLACK;
    // stars per 2500 px (50*50 square)
    private double density;
    // alpha value used when drawing stars
    private int brightness;
    // radius of stars, in px
    private int starSize;
    // used for random number generation
    private Random random;

    public Color getStarColor() {
        return starColor;
    }
    public void setStarColor(Color starColor) {
        this.starColor = starColor;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public double getDensity() {
        return density;
    }
    public void setDensity(double density) {
        this.density = density;
    }
    public int getBrightness() {
        return brightness;
    }
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
    public int getStarSize() {
        return starSize;
    }
    public void setStarSize(int starSize) {
        this.starSize = starSize;
    }

    // init with default values
    public DrawSpace() {
        density = 5;
        brightness = 150;
        starSize = 3;
        random = new Random();
    }

    public BufferedImage drawSpace(int imgWidth, int imgHeight) {
        BufferedImage generated = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = generated.createGraphics();
        drawBackground(graphics, imgWidth, imgHeight, backgroundColor);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int num_stars = (int) (imgWidth * imgHeight / 2500.0 * density);
        for (int i = 0; i < num_stars; i++) {
            drawStar(graphics, random.nextInt(imgWidth), random.nextInt(imgHeight), brightness, starSize);
        }
        return generated;
    }

    private static void drawBackground(Graphics2D graphics, int imgWidth, int imgHeight, Color backgroundColor) {
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, imgWidth, imgHeight);
    }

    private void drawStar(Graphics2D graphics, int x, int y, int brightness, int size) {
        graphics.setColor(starColor);
        graphics.setColor(new Color(starColor.getRed(), starColor.getBlue(), starColor.getGreen(), brightness));
        graphics.fillOval(x, y, size, size);
    }
}
