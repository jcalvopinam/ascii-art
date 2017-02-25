package com.jcalvopinam.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private static final String FONT_NAME = "SansSerif";
    private static final String CHARACTER_ART = "$";

    /**
     * Ref: https://www.mkyong.com/java/ascii-art-java-example/
     *
     * @param text
     * @return
     */
    @Override
    public String convert(String text) {
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

}
