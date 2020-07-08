package com.manappuram.ogl.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.chaos.view.PinView;
import com.google.android.material.snackbar.Snackbar;
import com.manappuram.ogl.OGLApplication;
import com.manappuram.ogl.R;
import com.manappuram.ogl.util.datatype_utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.graphics.Typeface.BOLD;

public class Utility {

    static Dialog errorDialog;
    private static Dialog progressDialog;
    private static Dialog otpDialog;

    public static String getFormattedDate(String dateInput) {
        return dateInput;
//        try {
//            String outPattern = "dd/MM/yyyy";
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
//            Date date = dateFormat.parse(dateInput);
//            String dateOut = simpleDateFormat.format(date);
//            return dateOut;
//        } catch (Exception e) {
//            return "";
//        }
    }

    public static String formatDate(String dateInput) {
        try {
            String outPattern = "dd-MMM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            Date date = dateFormat.parse(dateInput);
            String dateOut = simpleDateFormat.format(date);
            return dateOut;
        } catch (Exception e) {
            return dateInput;
        }
    }

    public static String getDate(int dayOfMonth, int month, int year) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);

        return getDate(calendar1);
    }

    public static Calendar getDateCal(int dayOfMonth, int month, int year) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);

        return calendar1;
    }

    public static String getDate(Calendar calendar) {
        try {
            String outPattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            String dateOut = simpleDateFormat.format(calendar.getTime());
            return dateOut;
        } catch (Exception e) {
            return "";
        }
    }

    public static int getcolorCode(String invcolor) {
        int color = R.string.circle_na;
        if (StringUtils.isEqual(invcolor, "blue")) {
            color = R.string.circle_blue;
        } else if (StringUtils.isEqual(invcolor, "green")) {
            color = R.string.circle_green;
        } else if (StringUtils.isEqual(invcolor, "yellow")) {
            color = R.string.circle_yellow;
        } else if (StringUtils.isEqual(invcolor, "pink")) {
            color = R.string.circle_pink;
        } else if (StringUtils.isEqual(invcolor, "red")) {
            color = R.string.circle_red;
        } else if (StringUtils.isEqual(invcolor, "orange")) {
            color = R.string.circle_orange;
        } else if (StringUtils.isEqual(invcolor, "na")) {
            color = R.string.circle_na;
        }
        return color;
    }

    public static String getLangCode(int position) {
        String langCode = null;

        switch (position) {
            case 0:
                langCode = "EN";
                break;
            case 1:
                langCode = "HI";
                break;
            case 2:
                langCode = "MA";
                break;
            case 3:
                langCode = "TA";
                break;
            case 4:
                langCode = "KA";
                break;
            case 5:
                langCode = "TE";
                break;
            case 6:
                langCode = "MR";
                break;
            case 7:
                langCode = "GU";
                break;
            case 8:
                langCode = "OR";
                break;
            case 9:
                langCode = "PU";
                break;
            case 10:
                langCode = "BE";
                break;
            case 11:
                langCode = "AS";
                break;
        }

        return langCode;
    }

//    public static String getARGB(int color){
//
//        float red=   (color >> 16) & 0xFF;
//        float green= (color >> 8) & 0xFF;
//        float blue=  (color >> 0) & 0xFF;
//        float alpha= (color >> 24) & 0xFF;
//
//    }

    public static boolean isNull(String string) {

        if (string == null)
            return true;

        if (string.equalsIgnoreCase("null"))
            return true;

        return false;
    }

    public static void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackbar(View view, int msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackbarWithRetry(View view, String msg, View.OnClickListener listener) {
        showSnackbarWithAction(R.string.retry, view, msg, listener);
//        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
//        snackbar.setAction(R.string.retry,new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                listener.onClick(v);
//                snackbar.dismiss();
//            }
//        });
//        snackbar.setActionTextColor(Color.RED);
//        snackbar.show();
    }


    public static void showSnackbarWithAction(String actionMsg, View view, String msg, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionMsg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public static void showSnackbarWithAction(int actionMsg, View view, String msg, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionMsg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public static void showSnackbarWithnoAction(int actionMsg, View view, String msg, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        if (actionMsg == R.string.retry) {

        } else {
            snackbar.setAction(actionMsg, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v);
                    snackbar.dismiss();
                }
            });
            snackbar.setActionTextColor(Color.RED);
        }
        snackbar.show();
    }

    public static void showSnackbarWithRetry(View view, int msg, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public static String encodeString(String text) {
        try {
            return OGLApplication.getCyptoInstance().encryptPlainText(text, Constants.CIPHER_KEY, Constants.CIPHER_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String decodeString(String text) {
        try {
            return OGLApplication.getCyptoInstance().decryptCipherText(text, Constants.CIPHER_KEY, Constants.CIPHER_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDeviceID() {
        String deviceID = SharedPreferenceHelper.getSharedString(OGLApplication.getInstance(), Constants.DEVICE_ID, null);
        return deviceID == null ? "0" : deviceID;
    }

    public static String getUniqueAndroidId(Context context) {
        String m_androidId = "";
        if (context != null) {
            m_androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return m_androidId;
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
        try {
            SpannableString spannableString = new SpannableString(textView.getText());
            for (int i = 0; i < links.length; i++) {
                ClickableSpan clickableSpan = clickableSpans[i];
                String link = links[i];

                int startIndexOfLink = textView.getText().toString().indexOf(link);
                spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new StyleSpan(BOLD), startIndexOfLink, startIndexOfLink + link.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setMovementMethod(LinkMovementMethod.getInstance());

            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String millisecondsToTime(long milliseconds) {
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        String secondsStr = Long.toString(seconds);
        String secs;
        if (secondsStr.length() >= 2) {
            secs = secondsStr.substring(0, 2);
        } else {
            secs = "0" + secondsStr;
        }
        return minutes + ":" + secs;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

//    public static void showKeyboard(Activity activity) {
//        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//    }

    public static Bitmap convertLayoutToImage(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public static String getDifference(String val1, String val2) {

        if (StringUtils.isNullOrEmpty(val1))
            val1 = "0";

        if (StringUtils.isNullOrEmpty(val2))
            val2 = "0";

        val1 = val1.trim();
        val2 = val2.trim();
        Double longVal1 = Double.valueOf(val1);
        Double longVal2 = Double.valueOf(val2);

        long diff = (long) (longVal1 - longVal2);

        return String.valueOf(Math.abs(diff));
    }

    public static String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }


    public static String getReceiptDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.RECEIPT_DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }

    public static String toCamelCase(String s) {
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            if (part != null && part.trim().length() > 0)
                camelCaseString = camelCaseString + toProperCase(part);
            else
                camelCaseString = camelCaseString + part + "";
        }
        return camelCaseString;
    }

    static String toProperCase(String s) {
        String temp = s.trim();
        String spaces = "";
        if (temp.length() != s.length()) {
            int startCharIndex = s.charAt(temp.indexOf(0));
            spaces = s.substring(0, startCharIndex);
        }
        temp = temp.substring(0, 1).toUpperCase() +
                spaces + temp.substring(1).toLowerCase() + "";
        return temp;

    }

    public static boolean noValue(String amt) {
        if (StringUtils.isNullOrEmpty(amt)) {
            return true;
        }

        return amt.equalsIgnoreCase("0");
    }


    public static void showCustomErrorDialog(Context context, String title, String msg, boolean cancelable, View.OnClickListener listener) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                errorDialog = new Dialog(context, R.style.CustomDialogTheme);
                errorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                errorDialog.setContentView(R.layout.custom_error_layout);
                errorDialog.setCanceledOnTouchOutside(cancelable);
                errorDialog.setCancelable(cancelable);


                Objects.requireNonNull(errorDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
                errorDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

                Window window = errorDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                window.setAttributes(wlp);

                TextView titleDialog = errorDialog.findViewById(R.id.titleDialog);
                TextView messageDialog = errorDialog.findViewById(R.id.messageDialog);
                TextView btnDialog = errorDialog.findViewById(R.id.btnDialog);

                if (!title.equals(""))
                    titleDialog.setText(title);

                messageDialog.setText(msg);

                btnDialog.setOnClickListener(listener);

                errorDialog.show();
            }
        }

    }


    public static void otpDialog(Context context, View.OnClickListener listener) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                otpDialog = new Dialog(context, R.style.CustomDialogTheme);
                otpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                otpDialog.setContentView(R.layout.custom_otp_dialog);
                otpDialog.setCanceledOnTouchOutside(true);
                otpDialog.setCancelable(true);


                Objects.requireNonNull(otpDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
                otpDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

                Window window = otpDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                window.setAttributes(wlp);

                TextView titleDialog = otpDialog.findViewById(R.id.titleDialog);
                PinView messageDialog = otpDialog.findViewById(R.id.pinView);
                TextView btnDialog = otpDialog.findViewById(R.id.btnDialog);


                btnDialog.setOnClickListener(listener);

                otpDialog.show();
            }
        }

    }

    public static void closeErrorDialog() {
        try {
            if (errorDialog != null && errorDialog.isShowing()) {
                errorDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setProgressbar(Context context) {


        progressDialog = new Dialog(context, R.style.AlertDialogCustom);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_progressbar);
        progressDialog.setCancelable(false);

        progressDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        progressDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;
        progressDialog.getWindow().setGravity(Gravity.CENTER);

        if (!((Activity) context).isFinishing()) {
            progressDialog.show();
        }else{
            progressDialog.hide();
        }

    }

    public static void cancelProgressbar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


//    public static String getDeductionNames(){}
}
