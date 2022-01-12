package com.arpangroup.inventory.util;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CommonUtils {
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(new Locale("en", "in"));


    /**
     * Input: 22.121455, Output: Rs.22.12146
     * @param value
     * @return
     */
    public static String formatRupees(double value) {//Input: 22.121455, Output: Rs.22.12146
        NUMBER_FORMAT.setMinimumFractionDigits(2);
        NUMBER_FORMAT.setMaximumFractionDigits(5);
        NUMBER_FORMAT.setRoundingMode(RoundingMode.HALF_EVEN);
        return NUMBER_FORMAT.format(value);
    }
}
