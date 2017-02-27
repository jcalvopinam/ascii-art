package com.jcalvopinam.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
public class AsciiServiceImpl implements AsciiService {

    private static final int TEXT_HEIGHT = 30;
    private static final int AXIS_X = 10;
    private static final int AXIS_Y = 20;
    private static final int FONT_SIZE = 24;
    private static final int MAX_LIMIT = -16777216;
    private static final double RED = 0.2989;
    private static final double BLUE = 0.5870;
    private static final double GREEN = 0.1140;
    private static final String FONT_NAME = "SansSerif";
    private static final String CHARACTER_ART = "$";
    private static boolean negative = false;

    /**
     * Converts a text to ASCII art, it was taken as reference from
     * https://www.mkyong.com/java/ascii-art-java-example/
     *
     * @param text
     * @return
     */
    @Override
    public String convertText(String text) {
        int textLength = 25 * text.length();
        String response = "";

        BufferedImage image = new BufferedImage(textLength, TEXT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(text, AXIS_X, AXIS_Y);

        StringBuilder sb;

        for (int y = 0; y < TEXT_HEIGHT; y++) {
            sb = new StringBuilder();

            for (int x = 0; x < textLength; x++) {
                sb.append(image.getRGB(x, y) == MAX_LIMIT ? " " : CHARACTER_ART);
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
            response += sb.toString() + System.lineSeparator();
        }

        return response;
    }

    /**
     * Converts an image to ASCII art, it was taken as reference from
     * https://gist.github.com/shmert/3859200
     *
     * @param urlImage
     * @return
     * @throws IOException
     */
    @Override
    public String convertImage(String urlImage) throws IOException {
        URL url = new URL(urlImage);
        BufferedImage image = ImageIO.read(url);
        if (image == null) throw new IllegalArgumentException(image + " is not a valid image.");

        StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            if (sb.length() != 0) sb.append("\n");
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                double gValue = (double) pixelColor.getRed() * RED + (double) pixelColor.getBlue() * BLUE + (double) pixelColor.getGreen() * GREEN;
                final char s = negative ? darkCharacter(gValue) : lightCharacter(gValue);
                sb.append(s);
            }
        }
        System.out.println(sb);

        return sb.toString();
    }

    /**
     * If the greyscale value is very high, the pixel is very bright and assign characters such as
     * '.' and ','. If the greyscale value is very low the pixel is very dark and assign characters
     * such as '#' and '@'.
     *
     * @param pixel
     * @return char
     */
    private char lightCharacter(double pixel) {
        final char str;

        if (pixel >= 230.0) str = ' ';
        else if (pixel >= 200.0) str = '.';
        else if (pixel >= 180.0) str = '*';
        else if (pixel >= 160.0) str = ':';
        else if (pixel >= 130.0) str = 'o';
        else if (pixel >= 100.0) str = '&';
        else if (pixel >= 70.0) str = '8';
        else if (pixel >= 50.0) str = '#';
        else str = '@';

        return str;
    }

    /**
     * If the greyscale value is very high, the pixel is very dark and assign characters such as
     * '#' and '@'. If the greyscale value is very low the pixel is very light and assign characters
     * such as '.' and ','.
     *
     * @param pixel
     * @return char
     */
    private char darkCharacter(double pixel) {
        final char str;

        if (pixel >= 230.0) str = '@';
        else if (pixel >= 200.0) str = '#';
        else if (pixel >= 180.0) str = '8';
        else if (pixel >= 160.0) str = '&';
        else if (pixel >= 130.0) str = 'o';
        else if (pixel >= 100.0) str = ':';
        else if (pixel >= 70.0) str = '*';
        else if (pixel >= 50.0) str = '.';
        else str = ' ';

        return str;
    }

}
