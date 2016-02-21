package plainsimple.space;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Renders an image of a starry sky based on several parameters.
 * Copyright(C) Plain Simple Apps 2015
 * See github.com/Plain-Simple/GalaxyDraw for more information.
 * Licensed under GPL GNU Version 3 (see license.txt)
 */
public class DrawSpace {

    // color of stars
    private Color starColor;
    // color of background
    private Color backgroundColor;
    // whether or not to use gradient
    private boolean useGradient;
    // gradient to use, if backgroundGradient = true
    private GradientPaint backgroundGradient;
    // stars per 2500 px (50*50 square)
    private double density;
    // alpha value used when drawing stars
    private int brightness;
    // radius of stars, in px
    private int starSize;
    // random variance from given values
    private double variance;
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

    public boolean usesGradient() {
        return useGradient;
    }

    public void setUseGradient(boolean useGradient) {
        this.useGradient = useGradient;
    }

    public GradientPaint getBackgroundGradient() {
        return backgroundGradient;
    }

    public void setBackgroundGradient(GradientPaint backgroundGradient) {
        this.backgroundGradient = backgroundGradient;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    // init with default values
    public DrawSpace() {
        density = 5;
        brightness = 150;
        starSize = 3;
        variance = 0.4;
        starColor = new Color(255, 255, 238);
        backgroundColor = Color.BLACK;
        useGradient = false;
        random = new Random();
    }

    // creates BufferedImage of given dimensions and renders space on it
    public BufferedImage drawSpace(int imgWidth, int imgHeight) {
        BufferedImage generated = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        drawSpace(generated);
        return generated;
    }

    // renders space on given BufferedImage
    public void drawSpace(BufferedImage canvas) {
        Graphics2D graphics = canvas.createGraphics();
        drawBackground(graphics, canvas.getWidth(), canvas.getHeight());
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int num_stars = (int) (canvas.getWidth() * canvas.getHeight() / 2500.0 * density);
        for (int i = 0; i < num_stars; i++) {
            drawStar(graphics, random.nextInt(canvas.getWidth()), random.nextInt(canvas.getHeight()),
                    varyBrightness(brightness, variance), varySize(starSize, variance));
        }
    }

    private void drawBackground(Graphics2D graphics, int imgWidth, int imgHeight) {
        if (useGradient) {
            graphics.setPaint(backgroundGradient);
            graphics.fillRect(0, 0, imgWidth, imgHeight);
        } else {
            graphics.setColor(backgroundColor);
            graphics.fillRect(0, 0, imgWidth, imgHeight);
        }
    }

    private void drawStar(Graphics2D graphics, int x, int y, int brightness, int size) {
        graphics.setColor(starColor);
        graphics.setColor(new Color(starColor.getRed(), starColor.getBlue(), starColor.getGreen(), brightness));
        graphics.fillOval(x, y, size, size);
    }

    private int varyBrightness(int value, double variance) {
        int varied = value + random.nextInt((int) (value * variance * 100)) / 100;
        if (varied > 255) {
            return 255;
        } else {
            return varied;
        }
    }

    private int varySize(int value, double variance) {
        return value + random.nextInt((int) (value * variance * 100)) / 100;
    }
}
