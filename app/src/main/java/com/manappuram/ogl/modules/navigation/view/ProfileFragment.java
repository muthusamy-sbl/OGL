package com.manappuram.ogl.modules.navigation.view;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.chaos.view.PinView;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.FragmentProfileBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.navigation.model.AccDetailsRequest;
import com.manappuram.ogl.modules.navigation.model.EditProfileRequest;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileRequest;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Utility;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {

    FragmentProfileBinding binding;
    String mailResponse, phoneResponse;
    boolean iseditable = false;

    DashboardViewModel viewmodel;

    String changeMode = "1";

    String email, phone;
    Dialog otpDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        viewmodel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();
        AccDetailsRequest request = new AccDetailsRequest();
        request.setCus_id(UserSession.getInstance().getCustomerId());
        request.setUniquesessionid(UserSession.getInstance().getUniqueSessionId());
        request.setRequestid(UserSession.getInstance().getRequestId());
        request.setLangId(UserSession.getInstance().getLangId());
        Log.d("AccDetailsRequest", request.toString());
        viewmodel.profileDetails(request, mActivity);


        viewmodel.getProfileMutableLiveData().observe(this, profileResponse -> {

            if (profileResponse.getRequestid() != null)
                updateRequestId(profileResponse.getRequestid());
            Log.d("profileResponse", profileResponse.toString());

            phoneResponse = profileResponse.getPhone1();
            mailResponse = profileResponse.getEmailid();

            binding.phoneNumber.setText(profileResponse.getPhone1());
            binding.mailId.setText(profileResponse.getEmailid());
            binding.custData.setText(profileResponse.getName() + "\nID : " + profileResponse.getCustid());
        });


        viewmodel.getDashboardRepository().getErrorsMutable().observe(this, baseResponseEvent -> {
            Utility.showSnackbar(binding.getRoot(), baseResponseEvent.getContent().getResult());
        });


        viewmodel.getDashboardRepository().getFailMessageMutable().observe(this, stringEvent -> {
            Utility.showSnackbar(binding.getRoot(), stringEvent.getContent());
        });


        binding.btnSubmit.setOnClickListener(view -> {

            String btnTxt = binding.btnSubmit.getText().toString();
            if (btnTxt.equals("EDIT")) {
                makeEditable();
                binding.btnSubmit.setText("UPDATE");
            } else {
                checkValues();
            }

        });

        return binding.getRoot();
    }

    private void checkValues() {
        email = binding.mailId.getText().toString();
        phone = binding.phoneNumber.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(mActivity, "Enter Email ID", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)||phone.length()<10) {
            Toast.makeText(mActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(mActivity, "Enter Valid Email ID", Toast.LENGTH_SHORT).show();
        } else if (email.equals(mailResponse) && phone.equals(phoneResponse)) {
            Toast.makeText(mActivity, "Enter different values", Toast.LENGTH_SHORT).show();
        } else {

            ReqEditProfileRequest reqEditProfileRequest = new ReqEditProfileRequest(phoneResponse, phone, mailResponse, email, "1");
            Log.d("reqEditProfileRequest", reqEditProfileRequest.toString());
            viewmodel.reqEditProfile(reqEditProfileRequest,mActivity);
            viewmodel.getReqEditProfileLiveData().observe(this, reqEditProfileResponse -> {

                Log.d("reqEditProfileResponse", reqEditProfileResponse.toString());
                updateRequestId(reqEditProfileResponse.getRequestid());
                if (reqEditProfileResponse.getStatus().equals("111")) {
                    changeMode = reqEditProfileResponse.getChangemode();
                    otpDialog();
                }
            });


        }
    }


    private void makeEditable() {
        binding.phoneNumber.setEnabled(true);
        binding.mailId.setEnabled(true);
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
        viewmodel.profileOTPVerify(phoneResponse, pin, mActivity);
        viewmodel.getOtpProfileLiveData().observe(this, baseResponse -> {

            Log.d("baseResponse", baseResponse.toString());
            if (baseResponse.getStatus().equals("111")) {
                otpDialog.dismiss();
                updateRequestId(baseResponse.getRequestid());
                editProfileService();
            } else {
                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v -> {
                    Utility.closeErrorDialog();
                });
            }
        });
    }

    private void editProfileService() {

        EditProfileRequest request = new EditProfileRequest();
        request.setCusid(UserSession.getInstance().getCustomerId());
        request.setMobileno(phone);
        request.setEmailid(email);
        request.setChangemode(changeMode);
        request.setRequestid(UserSession.getInstance().getRequestId());
        request.setUniquesessionid(UserSession.getInstance().getUniqueSessionId());
        request.setLangId("EN");

        viewmodel.editProfile(request, mActivity);

        viewmodel.getEditProfileMutableLiveData().observe(this, baseResponse -> {
            if (baseResponse.getStatus().equals("111")) {
                updateRequestId(baseResponse.getRequestid());
                Toast.makeText(mActivity, baseResponse.getResult(), Toast.LENGTH_SHORT).show();
                mActivity.finish();
            } else {

                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v -> {
                    Utility.closeErrorDialog();
                    mActivity.finish();
                });
            }
        });
    }
}
