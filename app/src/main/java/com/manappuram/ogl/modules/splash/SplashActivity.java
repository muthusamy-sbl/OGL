package com.manappuram.ogl.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ActivitySplashBinding;
import com.manappuram.ogl.modules.login.LoginActivity;
import com.manappuram.ogl.util.navigator.Navigator;


public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    private ActivitySplashBinding binding;
    private Navigator mNavigator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mNavigator = new Navigator(this);
        binding.shimmerViewContainer1.startShimmerAnimation();
        startLogin();
    }

//    private void initInternetListener() {
//////        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
//////        connectionLiveData.observe(this, connectionModel -> {
//////            if (connectionModel.getIsConnected()) {
//////        checkAppVersion();
//////            }
//////        });
////    }

    /**
     * Method check app version and update status
     */

    /**
     * Check internet Connection
     */
    // private void checkInternetConnectivity() {
//        if (Connectivity.isConnected(this))
//        checkAppVersion();
//        else {
//            initInternetListener();
//            Utility.showAlertDialog(binding.getRoot(), getString(R.string.no_connection));
//        }
//    }

    /**
     * Method to start Registration/Language Activity
     */
    private void startLogin() {
//        if (SharedPreferenceHelper.getSharedBoolean(this, Constants.IS_LANGUAGE_SELECTED, false)) {

        mRunnable = () -> {
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
            finish();
            binding.shimmerViewContainer1.stopShimmerAnimation();
        };
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, SPLASH_DISPLAY_LENGTH);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mHandler != null)
//            mHandler.removeCallbacks(mRunnable);
//    }

    /**
     * Method calls when App updated version is available on play store
     *
     * @param updateUrl
     */
//    @Override
//    public void onUpdateNeeded(String updateUrl) {
//        //todo : Add check box for dont remind again
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle(getString(R.string.update_available_title))
//                .setMessage(getString(R.string.update_app))
//                .setPositiveButton(getString(R.string.update), (dialog1, which) -> redirectStore(updateUrl))
//                .setNegativeButton(getString(R.string.no_update), (dialog12, which) -> {
//                    dialog12.dismiss();
//                    noUpdateRequire();
//                }).create();
//        dialog.show();
//    }

//    @Override
//    public void foceupdateRequire(String updateUrl) {
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle(getString(R.string.update_available_title))
//                .setMessage(getString(R.string.update_app))
//                .setPositiveButton(getString(R.string.update), (dialog1, which) -> redirectStore(updateUrl))
//                .setCancelable(false)
//                .create();
//        dialog.show();
//    }
//
//    @Override
//    public void noUpdateRequire() {
//        if (RootUtil.isDeviceRooted()) {
//            showPopupForRootedDevice();
//        } else {
//            startLogin();
//        }
//    }

    /**
     * Redirect User on play store to update app
     *
     * @param updateUrl
     */
//    private void redirectStore(String updateUrl) {
//        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }

//    public String getAppVersion(Context context) {
//        String result = "";
//
//        try {
//            result = context.getPackageManager()
//                    .getPackageInfo(context.getPackageName(), 0)
//                    .versionName;
//            result = result.replaceAll("[a-zA-Z]|-", "");
//        } catch (PackageManager.NameNotFoundException e) {
//        }
//        return result;
//    }
//
//    public void showPopupForRootedDevice() {
//        new AlertDialog.Builder(this)
//                .setTitle(getString(R.string.unsupported_device))
//                .setMessage(getString(R.string.app_not_supported))
//                .setCancelable(false)
//                .setPositiveButton(getString(R.string.ok), (dialog, id) -> {
//                    finish();
//                })
//                .show();
//    }
}
