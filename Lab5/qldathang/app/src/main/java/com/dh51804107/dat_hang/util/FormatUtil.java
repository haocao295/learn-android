package com.dh51804107.dat_hang.util;

import java.text.DecimalFormat;

public class FormatUtil {
    static DecimalFormat decimalFormat = new DecimalFormat("#,##0");

    public static String formatNumber(int num){
        return decimalFormat.format(num);
    }
}
