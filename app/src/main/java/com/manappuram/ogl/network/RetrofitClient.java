package com.manappuram.ogl.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manappuram.ogl.BuildConfig;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    public static String BASE_URL = "https://onpay.online.manappuram.com/appservice/Data_Service.asmx/";
    private static RetrofitClient sInstance;
    private Retrofit retrofit = null;
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    public static void create() {
        if (sInstance == null) {
            synchronized (RetrofitClient.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitClient();
                }
            }
        }
//        else
//            throw new IllegalStateException("RetrofitClient instance is already been created.");
    }

    public static APIInterface getAPIInterface() {

        if (sInstance == null)
            create();

        return sInstance.retrofit.create(APIInterface.class);
    }

    private RetrofitClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }

        // httpClient.addInterceptor(new ApplicationInterceptor());
        httpClient.connectTimeout(300, TimeUnit.SECONDS);
        httpClient.writeTimeout(300, TimeUnit.SECONDS);
        httpClient.readTimeout(300, TimeUnit.SECONDS);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //   .client(httpClient.build())
                .client(okHttpClient)
                //  .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * Returns the instance of {@link DashboardAPIInterface}.
     * This method must be called after {@link #create}.
     */
    public static DashboardAPIInterface getDashboardApiInterface() {

        if (sInstance == null)
            create();

        return sInstance.retrofit.create(DashboardAPIInterface.class);
    }


    /**
     * Returns the instance of {@link Retrofit}.
     * This method must be called after {@link #create}.
     */
    public static Retrofit retrofit() {
        synchronized (RetrofitClient.class) {
            if (sInstance == null)
//                throw new IllegalStateException("RetrofitClient instance is not created yet. Call RetrofitClient.create() before calling getInstance()");
                create();

        }
        return sInstance.retrofit;
    }

    /* private class ApplicationInterceptor implements Interceptor {


         public ApplicationInterceptor() {
         }

         @Override
         public Response intercept(Chain chain) throws IOException {
             Request original = chain.request();


             logRequest(original);

             Request.Builder requestBuilder = original.newBuilder();
             Request request = requestBuilder.build();
             Response response = chain.proceed(request);
             String rawJson = response.body().string().replace("{\"d\":null}", "");

             logResponse(response, rawJson);

             return response.newBuilder()
                     .body(ResponseBody.create(response.body().contentType(), rawJson)).build();
         }

         public void logRequest(Request request) {

             Call<BaseResponse> call = null;
             try {
                 call = RetrofitLoggerClient.getAPIInterface().logger(Utility.encodeString(request.url().toString()),
                         Constants.TYPE_REQ, UserSession.getInstance().getRequestId(),
                         UserSession.getInstance().getUniqueSessionId(), Utility.encodeString(requestBodyToString(request.body())),
                         System.currentTimeMillis() + "", Utility.encodeString(Utility.getDeviceID()));


 //                call = RetrofitLoggerClient.getAPIInterface().logger(Utility.encodeString(request.url().toString()),
 //                        Constants.TYPE_REQ, UserSession.getInstance().getRequestId(),
 //                        UserSession.getInstance().getUniqueSessionId(), Utility.encodeString(requestBodyToString(request.body())),
 //                        System.currentTimeMillis() + "", Utility.encodeString(Utility.getDeviceID()));


             } catch (IOException e) {
                 e.printStackTrace();
             }

             call.enqueue(new Callback<BaseResponse>() {
                 @Override
                 public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                     System.out.println("");
                 }

                 @Override
                 public void onFailure(Call<BaseResponse> call, Throwable t) {
                     System.out.println("");
                 }
             });
         }


         public void logResponse(Response response, String rawJson) {
             Call<BaseResponse> call = null;
             try {
                 call = RetrofitLoggerClient.getAPIInterface().logger(Utility.encodeString(response.request().url().toString()),
                         Constants.TYPE_RESP, UserSession.getInstance().getRequestId(),
                         UserSession.getInstance().getUniqueSessionId(), Utility.encodeString(rawJson),
                         System.currentTimeMillis() + "", Utility.encodeString(Utility.getDeviceID()));
 //                call = RetrofitLoggerClient.getAPIInterface().logger(response.request().url().toString(),
 //                        Constants.TYPE_RESP, UserSession.getInstance().getRequestId(),
 //                        UserSession.getInstance().getUniqueSessionId(), rawJson,
 //                        System.currentTimeMillis() + "", Utility.getDeviceID());
             } catch (Exception e) {
                 e.printStackTrace();
             }

             call.enqueue(new Callback<BaseResponse>() {
                 @Override
                 public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                     System.out.println("");
                 }

                 @Override
                 public void onFailure(Call<BaseResponse> call, Throwable t) {
                     System.out.println("");
                 }
             });
         }

         public String requestBodyToString(RequestBody requestBody) throws IOException {
             Buffer buffer = new Buffer();
             requestBody.writeTo(buffer);
             return buffer.readUtf8();
         }
     }*/
    public static class UnsafeOkHttpClient {
        public static OkHttpClient getUnsafeOkHttpClient() {
            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.addNetworkInterceptor(new StethoInterceptor());
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

                OkHttpClient okHttpClient = builder.build();
                return okHttpClient;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
