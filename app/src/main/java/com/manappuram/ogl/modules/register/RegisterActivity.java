package com.manappuram.ogl.modules.register;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.manappuram.ogl.databinding.ActivityRegisterBinding;
import com.manappuram.ogl.modules.login.viewmodel.LoginViewModel;
import com.manappuram.ogl.modules.register.model.RegisterRequest;
import com.manappuram.ogl.util.Utility;
import java.util.Objects;
import java.util.UUID;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding binding;
    String randomText = "";
    String customerId, mobile, mail, captcha, password;
    LoginViewModel viewmodel;


    String mailResponse;
    String phoneResponse;
    String idResponse;

    Dialog otpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mActivity = this;

        binding.back.setOnClickListener(view -> finish());

        binding.refreshCaptcha.setOnClickListener(view -> createRandomText());

        createRandomText();

        binding.btnSubmit.setOnClickListener(view -> {
            checkInput();
        });


        viewmodel.getRegisterMutableLiveData().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {

                Log.d("baseResponse", baseResponse.getResult());
                if (!baseResponse.getStatus().equals("111")) {
                    Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true,

                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Utility.closeErrorDialog();
                                }
                            });
                } else {
                    Utility.showCustomErrorDialog(mActivity, "Success", baseResponse.getResult(), true, v -> {
                        Utility.cancelProgressbar();
                        finish();
                    });
                }

            }
        });

        viewmodel.getCustomerCheckMutableLiveData().observe(this, customerCheckResponse -> {
            if (customerCheckResponse.getStatus().equals("111")) {
                idResponse = customerCheckResponse.getCustomerid();
                mailResponse = customerCheckResponse.getEmailid();
                phoneResponse = customerCheckResponse.getPhoneno();

                sendOtp();

            } else {
                Utility.showCustomErrorDialog(mActivity, "", customerCheckResponse.getResult(), true,

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Utility.closeErrorDialog();
                            }
                        });}
        });
        viewmodel.getLoginRepository().getErrorsMutable().observe(this, baseResponseEvent ->
                Utility.showCustomErrorDialog(mActivity, "", baseResponseEvent.getContent().getResult(), true,

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utility.closeErrorDialog();
                    }
                }));


        viewmodel.getLoginRepository().getFailMessageMutable().observe(this, stringEvent -> Utility.showCustomErrorDialog(mActivity, "", stringEvent.getContent(), true,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utility.closeErrorDialog();
                    }
                }));
    }

    private void sendOtp() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setCustomerId(idResponse);
        registerRequest.setMobileNo(mobile);
        registerRequest.setEmailId(mail);
        registerRequest.setPassword(Utility.encodeString(password));
        viewmodel.registerUser(registerRequest, mActivity);
    }

    private void createRandomText() {
        randomText = UUID.randomUUID().toString().substring(0, 6);
        binding.captcha.setText(randomText);
    }

    private void registerUser() {
        viewmodel.customerCheck(customerId, mActivity);
    }

    private void checkInput() {

        customerId = binding.edtCustomerId.getText().toString();
        mobile = binding.edtMobile.getText().toString();
        mail = binding.edtEmail.getText().toString();
        captcha = binding.edtCaptcha.getText().toString();
        password = binding.edtPassword.getText().toString();

        if (TextUtils.isEmpty(customerId)) {
            binding.customerIdLayout.setError("Enter Customer Id");
        } else if (TextUtils.isEmpty(mobile)) {
            binding.mobileLayout.setError("Enter Mobile Number");
        } else if (TextUtils.isEmpty(mail)) {
            binding.mailLayout.setError("Enter Mail ID");
        } else if (TextUtils.isEmpty(captcha)) {
            binding.captchaLayout.setError("Enter Captcha");
        } else if (captcha.length() < 6) {
            binding.captchaLayout.setError("Enter Valid Captcha");
        } else if (!captcha.equals(randomText)) {
            binding.captchaLayout.setError("Invalid Captcha");
        } else if (customerId.length() < 16) {
            binding.customerIdLayout.setError("Enter Valid Customer ID");
        } else if (!Patterns.PHONE.matcher(mobile).matches()) {
            binding.mobileLayout.setError("Enter Valid Mobile Number");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            binding.mailLayout.setError("Enter Valid Mail ID");
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordLayout.setError("Enter Password");
        } else if (password.length() < 8) {
            binding.passwordLayout.setError("Password minimum length is 8");
        } else {
            registerUser();
        }
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
//                    callOtpVerifyService(pin);
                }
            });

            otpDialog.show();
        }
    }
}
