package com.manappuram.ogl.modules.navigation.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.databinding.FragmentSetMpinBinding;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetMpinFragment extends BaseFragment {

    FragmentSetMpinBinding binding;
    DashboardViewModel viewModel;
    String mpin1, mpin2;
    Dialog otpDialog;

    String m_androidId = "";

    public SetMpinFragment() {
        // Required empty public constructor
    }

    public static SetMpinFragment newInstance() {

        Bundle args = new Bundle();

        SetMpinFragment fragment = new SetMpinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("HardwareIds")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSetMpinBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();


        if (mActivity != null) {
            m_androidId = Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
        }

        binding.btnSubmit.setOnClickListener(v -> isValid());


        viewModel.getMpinRequestLiveData().observe(this, baseResponse -> {

            if (baseResponse.getStatus().equals("111")) {
                updateRequestId(baseResponse.getRequestid());
                otpDialog();
            } else {
                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v1 -> Utility.closeErrorDialog());
            }
        });


        viewModel.getMpinOtpLiveData().observe(this, baseResponse -> {

            updateRequestId(baseResponse.getRequestid());
            if (baseResponse.getStatus().equals("101")) {

                otpDialog.dismiss();
                setMpin();
            } else {
                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v1 -> Utility.closeErrorDialog());
            }

        });

        viewModel.getMpinGenerateLiveData().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                updateRequestId(baseResponse.getRequestid());
                if (baseResponse.getStatus().equals("111")) {
                    Utility.showCustomErrorDialog(mActivity, "Success", baseResponse.getResult(), true, v1 -> {
                        Utility.closeErrorDialog();
                        mActivity.finish();
                    });
                } else {
                    Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v1 -> Utility.closeErrorDialog());
                }
            }
        });

        return binding.getRoot();
    }

    private void setMpin() {

        Log.d("deviceid-->", m_androidId);
        Log.d("deviceid-->", mpin1);
        viewModel.mcustomerMPINgeneration(m_androidId, mpin1, mActivity);
    }

    private void isValid() {
        mpin1 = binding.mpin1.getText().toString();
        mpin2 = binding.mpin2.getText().toString();

        if (mpin1.equals("")) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (mpin1.length() < 4) {
            Utility.showCustomErrorDialog(mActivity, "", "MPIN length should be 4", true, v1 -> Utility.closeErrorDialog());
        } else if (mpin2.equals("")) {
            Utility.showCustomErrorDialog(mActivity, "", "Re-enter MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (mpin2.length() < 4) {
            Utility.showCustomErrorDialog(mActivity, "", "MPIN length should be 4", true, v1 -> Utility.closeErrorDialog());
        } else if (!mpin1.equals(mpin2)) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter Same MPIN", true, v1 -> Utility.closeErrorDialog());
        } else {

            viewModel.MPINrequest(mActivity);
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
                    callOtpVerifyService(pin);
                }
            });

            otpDialog.show();
        }
    }

    private void callOtpVerifyService(String pin) {

        viewModel.mpinOtpVerification(pin, mActivity);

    }


}
