package com.haha.xiuxian.util.basic;

import net.minecraft.util.text.TextFormatting;

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

    private static String buildFormatString(TextFormatting... formats) {
        StringBuilder formatString = new StringBuilder();
        for (TextFormatting format : formats) {
            formatString.append(format.toString());
        }
        return formatString.toString();
    }
}
