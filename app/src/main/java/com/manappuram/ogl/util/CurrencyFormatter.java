package com.manappuram.ogl.util;


import android.text.TextUtils;

import java.text.DecimalFormat;

public class CurrencyFormatter {


    //    @InverseMethod("money_setter")
    public static String format(String string, boolean addSymbol) {

        if (string == null)
            return null;

        if (TextUtils.isEmpty(string))
            return null;

        string = string.replace(",", "");

        double value = Double.valueOf(string);
        String format = null;

        if (value < 1000) {
            format = format("###.##", value);
        } else {
            double hundreds = value % 1000;
            int other = (int) (value / 1000);
            format = format(",##", other) + ',' + format("000.##", hundreds);
        }

        if (addSymbol)
            format = "\u20B9" + " " + format;

        return format;
    }

    public static String format(String string) {
        return format(string, true);
    }

    private static String format(String pattern, Object value) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(value);
    }






}
