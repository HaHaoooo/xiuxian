package xiuxian;

import java.text.DecimalFormat;

public class ProgressBarTest {
    public static void main(String[] args) {
        double max = 100.0;

        for (double current = 0.0; current <= max; current += 1.0) {
            String progressBar = drawTextProgressBar(current, max);
            System.out.print(progressBar);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String drawTextProgressBar(double current, double max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max value must be greater than zero.");
        }

        int length = 20;
        int progress = (int) Math.ceil((current / max) * length);

        // 构建进度条字符串
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            progressBar.append((i < progress) ? "=" : "-");
        }

        // 格式化进度百分比
        double percent = (current / max) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedProgress = df.format(percent);
        progressBar.append("] ").append(formattedProgress).append("%");

        return "\r" + progressBar;
    }
}
