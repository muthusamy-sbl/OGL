package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivitySubmitBinding;
import com.manappuram.ogl.modules.dashboard.DashboardActivity;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationRequest;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanFinalResponse;
import com.manappuram.ogl.modules.login.LoginActivity;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;
public class SubmitActivity extends BaseActivity {

    ActivitySubmitBinding binding;
    DashboardViewModel viewModel;
    String amount;
    String invId;
    String pledgeNo;
    String pledgeAmt;
    public int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        amount = getIntent().getStringExtra("amount");
        invId = sharedPreferences.getString(Constants.SAVED_INVISIBLE_ID, "");
        pledgeNo = sharedPreferences.getString(Constants.SAVED_PLEDGE_NO, "");
        pledgeAmt = sharedPreferences.getString(Constants.SAVED_PLEDGE_AMOUNT, "");

        mActivity = this;

        binding.back.setOnClickListener(v -> finish());

        ConfirmationRequest confirmationRequest = new ConfirmationRequest();
        confirmationRequest.setPaymethod("1");
        confirmationRequest.setAmount(amount);
        confirmationRequest.setPlno(pledgeNo);
        confirmationRequest.setNewpledgeamnt(pledgeAmt);
        Log.d("confirmationRequest", confirmationRequest.toString());

        viewModel.OglConfirmation(confirmationRequest, mActivity);


        viewModel.getConfirmationResponseMutableLiveData().observe(this, confirmationResponse -> {

            updateRequestId(confirmationResponse.getRequestid());
            Log.d("confirmationResponse", confirmationResponse.toString());

            binding.tvCustomerIfsc.setText(confirmationResponse.getIfscCode());
            binding.tvCustomerAccountNum.setText(confirmationResponse.getBeneficiaryAccount());
            binding.tvCustomerBank.setText(confirmationResponse.getBeneficiaryBank());

        });

        viewModel.getLoanFinalResponseLiveData().observe(this, loanFinalResponse -> {
            updateRequestId(loanFinalResponse.getRequestid());
            Log.d("loanFinalResponse", loanFinalResponse.toString());
            if (loanFinalResponse.getStatus().equals("111")) {
                Utility.showCustomErrorDialog(mActivity, "Success", loanFinalResponse.getResult(), false, v -> {
                    Utility.cancelProgressbar();
                    Intent intent = new Intent(mActivity, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                });
            } else {
                Utility.showCustomErrorDialog(mActivity, "Error", loanFinalResponse.getResult(), true, v -> Utility.cancelProgressbar());
            }

        });

        binding.resendOtp.setOnClickListener(v -> {
            if( binding.resendOtp.getText().toString().equals("Resend OTP")) {
                viewModel.OglConfirmation(confirmationRequest, mActivity);

                new CountDownTimer(60* 1000+1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000);
                        int minutes = seconds / 60;
                        seconds = seconds % 60;
                        binding.resendOtp.setText("TIME : " + String.format("%02d", minutes)
                                + ":" + String.format("%02d", seconds));
                    }

                    public void onFinish() {
                        binding.resendOtp.setText("");
                    }
                }.start();


            }
        });

        binding.btnSubmit.setOnClickListener(v -> {
            String pin = binding.pinView.getText().toString();
            if (!pin.equals("") && pin.length() == 6) {
                newLoanDetails(pin);
            }
        });

    }

    private void newLoanDetails(String pin) {

        viewModel.GetNewloanDetails(pledgeNo, pin, mActivity);

    }
}
