//package com.manappuram.ogl.util;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.util.Log;
//
//import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
//
//public class ForceUpdateChecker {
//
//    private static final String TAG = ForceUpdateChecker.class.getSimpleName();
//    private static final String VERSION_KEY = "version";
//    private static final String VERSION_CODE = "version_code";
//    private static final String FORCE_UPDATE = "force_update";
//
//    private final FirebaseRemoteConfig mFirebaseRemoteConfig;
//
//    private Context context;
//    private OnUpdateNeededListener onUpdateNeededListener;
//
//
//    public void check() {
//        //Make Network call to get all updated values
////        String currentVersion = mFirebaseRemoteConfig.getString(VERSION_KEY);
//        StaticVariableInitializer.save(FAQ,mFirebaseRemoteConfig.getString(FAQ));
//        StaticVariableInitializer.save(CONTACT , mFirebaseRemoteConfig.getString(CONTACT));
//        StaticVariableInitializer.save(ABOUT_US , mFirebaseRemoteConfig.getString(ABOUT_US));
//        StaticVariableInitializer.save(OGL_WORK , mFirebaseRemoteConfig.getString(OGL_WORK));
//        StaticVariableInitializer.save(OGL_SCHEMES , mFirebaseRemoteConfig.getString(OGL_SCHEMES));
//        StaticVariableInitializer.save(BRANCH_LOCATOR , mFirebaseRemoteConfig.getString(BRANCH_LOCATOR));
//        StaticVariableInitializer.save(PAY_SUCCESS , mFirebaseRemoteConfig.getString(PAY_SUCCESS));
//        StaticVariableInitializer.save(PAY_FAILED , mFirebaseRemoteConfig.getString(PAY_FAILED));
//        long versionCode = mFirebaseRemoteConfig.getLong(VERSION_CODE);
//        boolean forceUpdateRequire = mFirebaseRemoteConfig.getBoolean(FORCE_UPDATE);
//        StaticVariableInitializer.save(LOGGER_BASE_URL,mFirebaseRemoteConfig.getString(LOGGER_BASE_URL));
//        StaticVariableInitializer.save(BASE_URL,mFirebaseRemoteConfig.getString(BASE_URL));
//        StaticVariableInitializer.initConstants();
//        RetrofitClient.create();
//        RetrofitLoggerClient.create();
//        long appVersionCode = getAppVersionCode(context);
//        String updateUrl = Constants.Urls.PLAY_STORE + BuildConfig.APPLICATION_ID;
//
//
////        if (!TextUtils.equals(currentVersion, appVersion)
////                && onUpdateNeededListener != null) {
//
//       /** For UAT check
//        * forceUpdateRequire = true;
//        * versionCode = 6*/
//
//        if (appVersionCode < versionCode
//                && onUpdateNeededListener != null) {
//            if (forceUpdateRequire) {
//                onUpdateNeededListener.foceupdateRequire(updateUrl);
//            } else {
//                onUpdateNeededListener.onUpdateNeeded(updateUrl);
//            }
//        } else {
//            onUpdateNeededListener.noUpdateRequire();
//        }
//    }
//
//
//    public interface OnUpdateNeededListener {
//        void onUpdateNeeded(String updateUrl);
//
//        void foceupdateRequire(String updateUrl);
//
//        void noUpdateRequire();
//    }
//
//    public static Builder with(@NonNull Context context) {
//        return new Builder(context);
//    }
//
//    private ForceUpdateChecker(@NonNull Context context,
//                               OnUpdateNeededListener onUpdateNeededListener) {
//        this.context = context;
//        this.onUpdateNeededListener = onUpdateNeededListener;
//        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//        mFirebaseRemoteConfig.setDefaults(R.xml.version_remote_default);
//        getNewAppVersion();
//    }
//
//    private void getNewAppVersion() {
//        long cacheExpiration = 10000;
//        mFirebaseRemoteConfig.fetch(cacheExpiration).addOnCompleteListener((Activity) context, task -> {
//            if (task.isSuccessful()) {
//                Log.d(TAG, "Fetch Succeeded");
//                mFirebaseRemoteConfig.activateFetched();
//            } else {
//                Log.d(TAG, "Fetch Failed");
//            }
//        });
//    }
//
//    private String getAppVersion(Context context) {
//        String result = "";
//
//        try {
//            result = context.getPackageManager()
//                    .getPackageInfo(context.getPackageName(), 0)
//                    .versionName;
//            result = result.replaceAll("[a-zA-Z]|-", "");
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, e.getMessage());
//        }
//        return result;
//    }
//
//    private long getAppVersionCode(Context context) {
//        long result = 0;
//
//        try {
//            result = context.getPackageManager()
//                    .getPackageInfo(context.getPackageName(), 0)
//                    .versionCode;
////            result = result.replaceAll("[a-zA-Z]|-", "");
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, e.getMessage());
//        }
//        return result;
//    }
//
//    public static class Builder {
//
//        private Context context;
//        private OnUpdateNeededListener onUpdateNeededListener;
//
//        public Builder(Context context) {
//            this.context = context;
//        }
//
//        public Builder onUpdateNeeded(OnUpdateNeededListener onUpdateNeededListener) {
//            this.onUpdateNeededListener = onUpdateNeededListener;
//            return this;
//        }
//
//        public ForceUpdateChecker build() {
//            return new ForceUpdateChecker(context, onUpdateNeededListener);
//        }
//
//        public ForceUpdateChecker check() {
//            ForceUpdateChecker forceUpdateChecker = build();
//            forceUpdateChecker.check();
//            return forceUpdateChecker;
//        }
//    }
//}
