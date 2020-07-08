package com.manappuram.ogl.modules.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chaos.view.PinView;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.ActivityForgotBinding;
import com.manappuram.ogl.modules.login.viewmodel.LoginViewModel;
import com.manappuram.ogl.modules.navigation.view.ChangePassword;
import com.manappuram.ogl.util.Utility;
import java.util.Objects;

public class ForgotActivity extends BaseActivity {

    ActivityForgotBinding binding;
    String mobile;
    LoginViewModel viewmodel;
    Dialog otpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot);
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mActivity = this;

        binding.back.setOnClickListener(view -> finish());


        binding.btnSubmit.setOnClickListener(view -> {

            mobile = binding.edtMobile.getText().toString();
            if (TextUtils.isEmpty(mobile)) {
                binding.mobileLayout.setError("Enter Customer ID/Mobile Num");
            } else if (mobile.length() < 10) {
                binding.mobileLayout.setError("Enter Valid Customer ID/Mobile Num");
            } else {
                forgotPassword();
            }

        });


        viewmodel.getForgotMutableLiveData().observe(this, baseResponse -> {

            Log.d("baseResponse", baseResponse.toString());

            if (baseResponse.getStatus() == null || baseResponse.getStatus().equals("null")) {
                Utility.showCustomErrorDialog(mActivity, "", "Check Your Customer ID", true, v -> Utility.closeErrorDialog());
            } else if (baseResponse.getStatus().equals("111")) {
                otpDialog();
            } else {
                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v -> Utility.closeErrorDialog());

            }

        });


        viewmodel.getOtpVerifiedMutableLiveData().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                Log.d("baseResponse", baseResponse.toString());
                if (baseResponse.getStatus().equals("101")) {
                    otpDialog.dismiss();
                    Intent intent = new Intent(mActivity, ChangePasswordActivity.class);
                    intent.putExtra("cusid", mobile);
                    startActivity(intent);

                } else if (baseResponse.getStatus().equals("102")) {
                    Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v -> Utility.closeErrorDialog());
                }

            }
        });
        viewmodel.getLoginRepository().getErrorsMutable().observe(this, baseResponseEvent -> Utility.showSnackbar(binding.getRoot(), baseResponseEvent.getContent().getResult()));
        viewmodel.getLoginRepository().getFailMessageMutable().observe(this, stringEvent -> Utility.showSnackbar(binding.getRoot(), stringEvent.getContent()));

    }

    private void forgotPassword() {
        viewmodel.forgetPassword(mobile,mActivity);
    }
    private void otpDialog() {
        if (!mActivity.isFinishing()) {
            otpDialog = new Dialog(mActivity, R.style.OtpDialogTheme);
            otpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            otpDialog.setContentView(R.layout.custom_otp_dialog);
            otpDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            otpDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            otpDialog.setCanceledOnTouchOutside(true);
            otpDialog.setCancelable(true);
            Objects.requireNonNull(otpDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
            otpDialog.getWindow().getAttributes().windowAnimations = R.style.OtpDialogTheme;
            Window window = otpDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            window.setAttributes(wlp);
            PinView pinView = otpDialog.findViewById(R.id.pinView);
            TextView btnDialog = otpDialog.findViewById(R.id.btnDialog);
            btnDialog.setOnClickListener(v -> {
                String pin = pinView.getText().toString();

                if (!pin.equals("") && pin.length() == 6) {
                    callOtpVerifyService(pin);
                }
            });
            otpDialog.show();
        }
    }


    private void callOtpVerifyService(String pin) {

        viewmodel.otpVerification(mobile, pin, mActivity);


    }

}
