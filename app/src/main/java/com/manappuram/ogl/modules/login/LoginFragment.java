package com.manappuram.ogl.modules.login;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.FragmentLoginBinding;
import com.manappuram.ogl.modules.dashboard.DashboardActivity;
import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.modules.login.model.VersionResponse;
import com.manappuram.ogl.modules.login.viewmodel.LoginViewModel;
import com.manappuram.ogl.modules.more.MoreActivity;
import com.manappuram.ogl.modules.register.RegisterActivity;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.LocaleHelper;
import com.manappuram.ogl.util.SharedPreferenceHelper;
import com.manappuram.ogl.util.Utility;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import io.reactivex.annotations.Nullable;

import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;


public class LoginFragment extends BaseFragment {

    private static final int SETTINGS_REQ_CODE = 101;
    private FragmentLoginBinding binding;
    private ArrayList<String> langlist = new ArrayList<>();
    private LoginViewModel viewmodel;
    private String m_androidId = "";
    private String lang = "EN";
    Locale myLocale;
    String currentLanguage = "en", currentLang;

    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(Constants.TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mActivity = getActivity();

//        new NoInternetLayout.Builder(mActivity, R.layout.fragment_login)
//                .setImage(LayoutImage.SIMPLE);

//        viewmodel.versionCheck(mActivity);

        m_androidId = Utility.getUniqueAndroidId(mActivity);
String language =LocaleHelper.getLanguage(mActivity);
System.out.println(language);
if(language.equals("en")){
    binding.spinnerlang.setText("English");
    lang = "EN";

}else if(language.equals("hi")){
    binding.spinnerlang.setText("हिंदी");
    lang = "HI";

}
 else if(language.equals("tl")){
    binding.spinnerlang.setText("తెలుగు");
    lang = "TE";

}else if(language.equals("ka")){
    binding.spinnerlang.setText("ಕನ್ನಡ");
    lang = "KA";

}else if(language.equals("ta")){
    binding.spinnerlang.setText("தமிழ்");
    lang = "TA";

}else if(language.equals("ma")){
    binding.spinnerlang.setText("മലയാളം");
    lang = "MA";

}

        clickListeners();

        onsetListener();

        serviceResponses();

        return binding.getRoot();
    }

    private void clickListeners() {
        binding.login.setOnClickListener(view -> loginCheck());
        binding.schemlist.setOnClickListener(v -> gotoActivity("schemes"));
        binding.locate.setOnClickListener(v -> gotoActivity("locate"));
        binding.mpinLogin.setOnClickListener(v -> checkMPIN());
        binding.spinnerlang.setOnClickListener(view -> showLanguage(mActivity));
        binding.more.setOnClickListener(v -> clickedOnMore());
        binding.btnDemoVideo.setOnClickListener(view -> gotoActivity("demo"));
        binding.btnContact.setOnClickListener(view -> gotoActivity("contact"));
        binding.btnFaq.setOnClickListener(view -> gotoActivity("faq"));
        binding.btnBook.setOnClickListener(view -> gotoActivity("book"));
        binding.register.setOnClickListener(view -> startActivity(new Intent(mActivity, RegisterActivity.class)));
        binding.forgetPassword.setOnClickListener(view -> startActivity(new Intent(mActivity, ForgotActivity.class)));
    }


    private void serviceResponses() {
        viewmodel.getVersionMutableLiveData().observe(this, versionResponse -> Log.d("versionResponse", versionResponse.toString()));
        viewmodel.getLoginResponseMutable().observe(this, this::loginSuccess);
        viewmodel.getLoginRepository().getErrorsMutable().observe(this, baseResponseEvent -> Utility.showCustomErrorDialog(mActivity, "Error", "Check username and password", true, v -> Utility.closeErrorDialog()));
        viewmodel.getLoginRepository().getFailMessageMutable().observe(this, stringEvent -> Utility.showCustomErrorDialog(mActivity, "Error", "Check username and password", true, v -> Utility.closeErrorDialog()));
    }

    private void loginSuccess(LoginResponse loginResponse) {
        Log.d("loginResponse", loginResponse.toString());
        if (loginResponse.getStatus().equals("111")) {
            UserSession.getInstance().setUserData(loginResponse);
            Intent intent = new Intent(mActivity, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            mActivity.finish();

        } else if (loginResponse.getStatus().equals("222")) {
            Utility.showCustomErrorDialog(mActivity, "Error", "Check username and password", true, v -> Utility.closeErrorDialog());
        }
    }


    private void loginCheck() {
        String username = Objects.requireNonNull(binding.usernameInput.getText()).toString();
        String password = Objects.requireNonNull(binding.passwordInput.getText()).toString();
        if (TextUtils.isEmpty(username)) {
            Utility.showCustomErrorDialog(mActivity, "Error", "Enter Username", true, v -> Utility.closeErrorDialog());
        } else if (TextUtils.isEmpty(password)) {
            Utility.showCustomErrorDialog(mActivity, "Error", "Enter Password", true, v -> Utility.closeErrorDialog());
        } else if (password.length() < 8) {
            Utility.showCustomErrorDialog(mActivity, "Error", "Password should need 8 character length", true, v -> Utility.closeErrorDialog());
        } else {
            LoginRequest loginRequest = new LoginRequest(username, password, lang);
            Log.d("loginRequest", loginRequest.toString());
            viewmodel.login(loginRequest, mActivity);
        }
    }

    private void clickedOnMore() {
        if (binding.dockmore.isExpanded()) {
            binding.more.setText("More");
            binding.dockmore.collapse();
        } else {
            binding.more.setText("Less");
            binding.dockmore.expand();
        }
    }

    private void checkMPIN() {
        String pin = Objects.requireNonNull(binding.firstPinView.getText()).toString();
        if (pin.length() == 4) {
            viewmodel.loginWithMPIN(Utility.encodeString(pin), m_androidId, mActivity);
        }
    }

    private void gotoActivity(String type) {
        Intent intent = new Intent(mActivity, MoreActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }


    private void onsetListener() {
        binding.usernameInput.requestFocus();
        Utility.showKeyboard(mActivity);
        binding.loginTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Utility.hideKeyboard(mActivity);
                if (tab.getPosition() == 0) {
                    binding.layoutPasswordLogin.setVisibility(View.VISIBLE);
                    binding.layoutMpinLogin.setVisibility(View.GONE);
                    binding.firstPinView.setText(null);
                    binding.usernameInput.requestFocus();
                    Utility.showKeyboard(mActivity);
                } else if (tab.getPosition() == 1) {
                    binding.layoutPasswordLogin.setVisibility(View.GONE);
                    binding.layoutMpinLogin.setVisibility(View.VISIBLE);
                    binding.usernameInput.setText("");
                    binding.passwordInput.setText("");
                    binding.firstPinView.requestFocus();
                    Utility.showKeyboard(mActivity);
                    binding.username.setErrorEnabled(false);
                    binding.password.setErrorEnabled(false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void showLanguage(Context context) {

        Dialog languageDialog;
        TextView selectEnglish, selectHindi, selectMalayalam, selectKannada, selectTamil, selectTelugu;

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {

                languageDialog = new Dialog(context, R.style.CustomDialogTheme);
                languageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                languageDialog.setContentView(R.layout.custom_lanuage_selctor);
                languageDialog.setCanceledOnTouchOutside(true);
                languageDialog.setCancelable(true);

                Objects.requireNonNull(languageDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
                languageDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

                Window window = languageDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                window.setAttributes(wlp);

                selectEnglish = languageDialog.findViewById(R.id.selectEnglish);
                selectHindi = languageDialog.findViewById(R.id.selectHindi);
                selectKannada = languageDialog.findViewById(R.id.selectKannada);
                selectMalayalam = languageDialog.findViewById(R.id.selectMalayalam);
                selectTamil = languageDialog.findViewById(R.id.selectTamil);
                selectTelugu = languageDialog.findViewById(R.id.selectTelugu);

                selectEnglish.setOnClickListener(view -> {
                    binding.spinnerlang.setText("English");
                    lang = "EN";
                    setlan("en");
                    languageDialog.dismiss();
                });
                selectHindi.setOnClickListener(view -> {
                    binding.spinnerlang.setText("हिंदी");
                    lang = "HI";
                    setlan("hi");
                    languageDialog.dismiss();
                });
                selectMalayalam.setOnClickListener(view -> {
                    binding.spinnerlang.setText("മലയാളം");
                    lang = "MA";
                    setlan("ma");
                    languageDialog.dismiss();
                });
                selectKannada.setOnClickListener(view -> {
                    binding.spinnerlang.setText("ಕನ್ನಡ");
                    lang = "KA";
                    setlan("ka");
                    languageDialog.dismiss();
                });
                selectTamil.setOnClickListener(view -> {
                    binding.spinnerlang.setText("தமிழ்");
                    lang = "TA";
                    setlan("ta");
                    languageDialog.dismiss();
                });
                selectTelugu.setOnClickListener(view -> {
                    binding.spinnerlang.setText("తెలుగు");
                    lang = "TE";
                    setlan("tl");
                    languageDialog.dismiss();
                });
                languageDialog.show();

            }
        }

    }

    private void setlan(String localeName) {
        LocaleHelper.setLocale(mActivity, localeName);

        Intent refresh = new Intent(mActivity, LoginActivity.class);
        refresh.putExtra(currentLang, localeName);
        startActivity(refresh);
        mActivity.finish();
    }

}
