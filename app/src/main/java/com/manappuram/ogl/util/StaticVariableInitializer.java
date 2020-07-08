package com.manappuram.ogl.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.manappuram.ogl.BuildConfig;
import com.manappuram.ogl.R;
import com.manappuram.ogl.network.RetrofitClient;
import com.manappuram.ogl.network.RetrofitLoggerClient;

public class StaticVariableInitializer {
    public static final String FAQ = "faq";
    public static final String CONTACT = "contact_us";
    public static final String ABOUT_US = "about_us";
    public static final String OGL_WORK = "ogl_work";
    public static final String OGL_SCHEMES = "ogl_schemes";
    public static final String PAY_SUCCESS = "pay_success";
    public static final String PAY_FAILED = "pay_fail";
    public static final String BASE_URL = "base_url";
    public static final String BRANCH_LOCATOR = "branch_locator";
    public static final String LOGGER_BASE_URL = "logger_base_url";

    private static SharedPreferences sharedPref;

    public static void init(Context context) {
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }


    public static void initConstants(){

        if (BuildConfig.DEBUG) {
            RetrofitClient.BASE_URL = "https://onpay.online.manappuram.com/app_mobapp/Data_Service.asmx/";
            // RetrofitClient.BASE_URL = "http://52.172.28.51:81/Data_Service.asmx/";
//            RetrofitLoggerClient.BASE_URL = "https://ogllogserver.manappuram.com/api/APILog/";
            RetrofitLoggerClient.BASE_URL = "http://14.141.164.230/oglauditlog/api/APILog/";
        }
    }

    public static void save(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String get(String key){
        return  sharedPref.getString(key,null);
    }

}
