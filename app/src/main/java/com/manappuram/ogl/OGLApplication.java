package com.manappuram.ogl;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.manappuram.ogl.session.AppLockManager;
import com.manappuram.ogl.util.CryptLib;
import com.manappuram.ogl.util.LocaleHelper;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
public class OGLApplication extends Application {

    private static OGLApplication appInstance;
    private static CryptLib cryptLib;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
    public static Boolean showAnimation = true;
    public static OGLApplication getInstance() {
        return appInstance;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        Stetho.initializeWithDefaults(this);
        initCrypto();
        AppLockManager.getInstance().enableDefaultAppLockIfAvailable(
                this);
        sAnalytics = GoogleAnalytics.getInstance(this);
        //    StaticVariableInitializer.init(this);
        //    AppSignatureHelper appSignature = new AppSignatureHelper(this);
        //    System.out.println(" appsignature " + appSignature.getAppSignatures());
    }

    private void initCrypto() {
        try {
            cryptLib = new CryptLib();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public void touch()
    {
        AppLockManager.getInstance().updateTouch();
    }

    public static CryptLib getCyptoInstance() {
        return cryptLib;
    }

    synchronized public Tracker getDefaultTracker() {
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }
        return sTracker;
    }
}
