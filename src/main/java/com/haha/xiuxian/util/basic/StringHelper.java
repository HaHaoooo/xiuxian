package com.haha.xiuxian.util.basic;

import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StringHelper {

    public static ArrayList<String> splitString(String args, int rowWidth, TextFormatting... format) {
        ArrayList<String> list = new ArrayList<>();
        int length = args.length();
        for (int i = 0; i < length; i += rowWidth) {
            int end = Math.min(length, i + rowWidth);
            list.add(buildFormatString(format) + args.substring(i, end));
        }
        return list;
    }

    public static String buildFormatString(TextFormatting... formats) {
        StringBuilder formatString = new StringBuilder();
        for (TextFormatting format : formats) {
            formatString.append(format.toString());
        }
        return formatString.toString();
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
        return progressBar.toString();
    }
}
