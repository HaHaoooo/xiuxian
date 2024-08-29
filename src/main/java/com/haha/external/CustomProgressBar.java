package com.haha.external;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomProgressBar extends JProgressBar {

    private BufferedImage backgroundImg;
    private BufferedImage progressImg;
    private BufferedImage[] digits;
    private BufferedImage percent;

    public CustomProgressBar(int min, int max) {
        super(min, max);
        try {
            backgroundImg = ImageIO.read(new File("src/main/java/com/haha/external/resources/background.png"));
            progressImg = ImageIO.read(new File("src/main/java/com/haha/external/resources/progress.png"));
            BufferedImage fontImg = ImageIO.read(new File("src/main/java/com/haha/external/resources/font.png"));

            digits = new BufferedImage[10];
            digits[0] = fontImg.getSubimage(141, 0, 16, 14);
            digits[1] = fontImg.getSubimage(0, 0, 13, 14);
            digits[2] = fontImg.getSubimage(13, 0, 16, 14);
            digits[3] = fontImg.getSubimage(29, 0, 16, 14);
            digits[4] = fontImg.getSubimage(45, 0, 16, 14);
            digits[5] = fontImg.getSubimage(61, 0, 16, 14);
            digits[6] = fontImg.getSubimage(77, 0, 16, 14);
            digits[7] = fontImg.getSubimage(93, 0, 16, 14);
            digits[8] = fontImg.getSubimage(109, 0, 16, 14);
            digits[9] = fontImg.getSubimage(125, 0, 16, 14);

            percent = fontImg.getSubimage(157, 0, 13, 14);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int backgroundWidth = backgroundImg.getWidth();
        int backgroundHeight = backgroundImg.getHeight();
        int scaledBackgroundWidth = backgroundWidth * 2;
        int scaledBackgroundHeight = backgroundHeight * 2;
        int progressWidth = (int) (progressImg.getWidth() * ((double) getValue() / getMaximum())) * 2;
        int x = (getWidth() - scaledBackgroundWidth) / 2;
        int y = (getHeight() - scaledBackgroundHeight) / 2;

        g.drawImage(backgroundImg, x, y, scaledBackgroundWidth, scaledBackgroundHeight, this);
        g.drawImage(progressImg, x, y, x + progressWidth, y + scaledBackgroundHeight, 0, 0, progressWidth / 2, backgroundHeight, this);
        String valueStr = String.valueOf(getValue());

        int totalDigitsWidth = valueStr.length() * 16 + 13;
        int digitX = x + (scaledBackgroundWidth - totalDigitsWidth) / 2;
        int digitY = y + (scaledBackgroundHeight - 14) / 2 - 20;

        for (char c : valueStr.toCharArray()) {
            int digit = c - '0';
            int digitWidth = (c == '1') ? 10 : 13;
            g.drawImage(digits[digit], digitX, digitY, null);
            digitX += digitWidth;
        }
        g.drawImage(percent, digitX, digitY, null);

        if (getValue() == getMaximum()) {
            try {
                BufferedImage mark = ImageIO.read(new File("src/main/java/com/haha/external/resources/checkmark.png"));
                g.drawImage(mark, (getWidth() / 2) - 18, digitY + 35, 18, 16, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
