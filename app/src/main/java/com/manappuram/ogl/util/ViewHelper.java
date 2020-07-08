package com.manappuram.ogl.util;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
public class ViewHelper {

    public static SpannableString addFont(String str, String substring){
        SpannableString builder = new SpannableString(str);
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
        int start = str.lastIndexOf(substring);
        int end = str.length();
        builder.setSpan(new TypefaceSpan("sans-serif-medium"),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
    public static SpannableString addFont1(String str, String substring){
        SpannableString builder = new SpannableString(str);
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
        int start = str.lastIndexOf(substring);
        int end = start+ substring.length();
        builder.setSpan(new TypefaceSpan("sans-serif-medium"),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
    public static SpannableString addFont2(String str, String substring){
        SpannableString builder = new SpannableString(str);
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
        int start = str.lastIndexOf(substring);
        int end = start+ substring.length();
        builder.setSpan(new TypefaceSpan("sans-serif-medium"),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(51,51,51));
        builder.setSpan(fcs,start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
