package com.manappuram.ogl.network;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitLoggerClient {

    public static String BASE_URL = "";

    private static RetrofitLoggerClient sInstance;
    private Retrofit retrofit = null;

    public static void create() {
        if (sInstance == null) {
            synchronized (RetrofitLoggerClient.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitLoggerClient();
                }
            }
        }
//        else  18601207777
//            throw new IllegalStateException("RetrofitClient instance is already been created.");
    }


    private RetrofitLoggerClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        CertificatePinner certPinner = new CertificatePinner.Builder()
                .add("online.manappuram.com",
                        "sha256/YQZSq+E740B7gRPm6yvL3FLt6FTNPawsbp8L91F6y3k=")
                .add("onpay.online.manappuram.com",
                        "sha256/Tsy3B1IW/bOoqbH1etn+bl8V7sMWzy1maTrEczXdauY=")
                .add("ogllogserver.manappuram.com",
                        "sha256/vHPG3XdOAob9IZFUEhBfu32nUo6bJbYqCVTiLLfUndQ=")
                .build();
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClient.addInterceptor(interceptor);
//        }
        httpClient.connectTimeout(300, TimeUnit.SECONDS);
        httpClient.writeTimeout(300, TimeUnit.SECONDS);
        httpClient.readTimeout(300, TimeUnit.SECONDS);

//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.certificatePinner(certPinner);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /**
     * Returns the instance of {@link Retrofit}.
     * This method must be called after {@link #create}.
     */
    public static Retrofit retrofit() {
        synchronized (RetrofitLoggerClient.class) {
            if (sInstance == null)
                create();
//                throw new IllegalStateException("RetrofitClient instance is not created yet. Call RetrofitClient.create() before calling getInstance()");

        }
        return sInstance.retrofit;
    }

    /**
     * Returns the instance of {@link APIInterface}.
     * This method must be called after {@link #create}.
     */
    public static APIInterface getAPIInterface() {

        return retrofit().create(APIInterface.class);
    }

}
