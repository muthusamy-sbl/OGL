package com.manappuram.ogl.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.manappuram.ogl.OGLApplication;
import com.manappuram.ogl.R;
import com.manappuram.ogl.modules.login.LoginActivity;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.CustomLoader;
import com.manappuram.ogl.util.KeyboardVisibilityListenerHandler;
import com.manappuram.ogl.util.LocaleHelper;
import com.manappuram.ogl.util.StaticVariableInitializer;

import io.fabric.sdk.android.Fabric;
import io.reactivex.annotations.Nullable;

/**
 * Base Activity extended by all Activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private CustomLoader customLoader;
    public Activity mActivity;
    public String requestId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //StaticVariableInitializer.initConstants();
        sharedPreferences = getSharedPreferences("ogl-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        customLoader = new CustomLoader(this);
        LocaleHelper.onAttach(BaseActivity.this);
        //Fabric.with(this, new Crashlytics());
        //initAnalytics();
    }

    /**
     * Initialize google Analytics
     */
    private void initAnalytics() {
        Tracker mTracker = OGLApplication.getInstance().getDefaultTracker();
        mTracker.setScreenName(this.getClass().getName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    /**
     * Get Application Instance
     *
     * @return
     */
    public OGLApplication getApp() {
        return (OGLApplication) this.getApplication();
    }

    /**
     * Update session time on user touch event
     */
//    @Override
//    public void onUserInteraction() {
//        super.onUserInteraction();
//        getApp().touch();
//    }
    public void updateRequestId(String rId) {
        if (!rId.equals("") && !rId.equals("null")) {
            UserSession.getInstance().setRequestId(rId);
            editor.putString("requestId", rId);
            editor.apply();
        }
        requestId = sharedPreferences.getString("requestId", "");

        Log.d("doorKey", "-->" + requestId);
    }

    /**
     * Show Progress Dialog
     */
    public void showProgress() {
        if (!customLoader.isShowing())
            customLoader.show();
    }

    /**
     * Hide Progress Dialog
     */
    public void hideProgress() {
        if (customLoader.isShowing())
            customLoader.dismiss();
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleHelper.onAttach(base));
//    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * Override Activity Transition
     *
     * @param intent
     */

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void showSessionDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.session_expire))
                .setMessage(getString(R.string.your_session_exp))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.login), (dialog, id) -> {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .show();
    }


    public void showBackPopup() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.session_expire))
                .setMessage(getString(R.string.exit_msg))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok), (dialog, id) -> {
                })
                .show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.onAttach(BaseActivity.this);
        // Checks whether a hardware keyboard is available
        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            KeyboardVisibilityListenerHandler.getHandler().onKeyboardVisible();
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            KeyboardVisibilityListenerHandler.getHandler().onKeyboardHidden();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LocaleHelper.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocaleHelper.onAttach(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBoolean("restart", true);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getBoolean("restart", false)) {
            showSessionDialog();
        }
    }
}
