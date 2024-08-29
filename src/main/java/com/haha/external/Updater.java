package com.haha.external;

import javax.swing.*;
import java.awt.*;

public class Updater extends JFrame {

    public static int WIDTH = 400;
    public static int HEIGHT = 100;
    private final JProgressBar progressBar;

    public Updater() {
        setTitle("灵仙Updater");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        progressBar = new CustomProgressBar(0, 100);
        progressBar.setValue(0);
        add(progressBar, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void progressing(int current, int total) {
        SwingUtilities.invokeLater(() -> {
            int progress = (int) ((current / (double) total) * 100);
            progressBar.setValue(progress);
            if (progress == 100) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dispose();
                    System.exit(DISPOSE_ON_CLOSE);
                }).start();
            }
        });
    }
}
