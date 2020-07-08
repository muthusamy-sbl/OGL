package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.databinding.ActivityAmountBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;

public class AmountActivity extends BaseActivity {

    ActivityAmountBinding binding;
    String response;
    SchemeResponse schemeResponse;
    DashboardViewModel viewModel;
    String pledgeNum;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String branchId = "";
    String amount= "";
    String Eligible="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_amount);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        response = getIntent().getStringExtra("response");
        pledgeNum = getIntent().getStringExtra("pledgeNum");
        Gson gson = new Gson();
        schemeResponse = gson.fromJson(response, SchemeResponse.class);
        mActivity = this;
        sharedPreferences = mActivity.getSharedPreferences("ogl-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        viewModel.getSelectedSchemeDetails(getSchemeString(), pledgeNum,mActivity);
        viewModel.getSelectedSchemeLiveData().observe(this, new Observer<SchemeItemResponse>() {
            @Override
            public void onChanged(SchemeItemResponse schemeItemResponse) {
                Log.d("schemeResponse", schemeItemResponse.toString());
                if (schemeItemResponse.getStatus().equals("111")) {
                    String channel = (sharedPreferences.getString(Constants.SAVED_settlement_AMOUNT, ""));
                    int NetAmt= Math.round(Float.parseFloat(schemeResponse.getLoanValue().trim())-Float.parseFloat(channel));
                    updateRequestId(schemeItemResponse.getRequestid());
                    branchId = schemeItemResponse.getBranchId();
                    binding.tvlastSettledAmt.setText(Constants.Amount_symbol+channel);
                    binding.tvScheme.setText(schemeItemResponse.getSchemeName());
                    binding.tvEligibleLoan.setText(Constants.Amount_symbol+schemeItemResponse.getEligibleLoanAmt());
                    binding.tvBalance.setText(Constants.Amount_symbol+ NetAmt);
                    binding.tvStampDuty.setText(schemeItemResponse.getStampDuty());
                    Eligible= schemeItemResponse.getEligibleLoanAmt();

                } else {
                    Utility.showCustomErrorDialog(mActivity, "", schemeItemResponse.getResult(), false, v -> {
                        Utility.closeErrorDialog();
                        mActivity.finish();
                    });
                }
            }
        });

        viewModel.getCalAmountresponse().observe(this, calculatorResponses -> {
            Log.d("calculatorResponses", calculatorResponses.toString());
            updateRequestId(calculatorResponses.getRequestid());
            if (calculatorResponses.getStatus().equals("111")) {


                Intent intent = new Intent(mActivity, DetailsActivity.class);
                intent.putExtra("response", response);
                intent.putExtra("amount", amount);
                intent.putExtra("pledgeNum", pledgeNum);
                startActivity(intent);


            }else{
                Utility.showCustomErrorDialog(mActivity, "", calculatorResponses.getResult(), false, v -> {
                    Utility.closeErrorDialog();

                });
            }

        });
    }

    private String getSchemeString() {
        return schemeResponse.getSchmName().trim() + "*" +
                schemeResponse.getLendRate().trim() + "*" +
                schemeResponse.getDuration().trim() + "*" +
                schemeResponse.getInterest().trim() + "*" +
                schemeResponse.getLoanValue().trim() + "*" +
                schemeResponse.getMaxLoan().trim();
    }


    public void gotoCalculator(View view) {

          amount = binding.edtAmount.getText().toString();

        if (!amount.equals("")) {
            Intent intent = new Intent(mActivity, CalculatorActivity.class);
            intent.putExtra("response", response);
            intent.putExtra("branchId", branchId);
            intent.putExtra("amount", amount);
            startActivity(intent);
        }

    }

    public void clickContinue(View view) {
          amount = binding.edtAmount.getText().toString();
        if (!amount.equals("")&&Integer.parseInt(amount)<=Float.parseFloat(Eligible)) {
            viewModel.getRequiredAmount(amount,pledgeNum,mActivity);

        }else{

            Utility.showCustomErrorDialog(mActivity, "","Required loan amount should be less than eligible loan amount", false, v -> {
                Utility.closeErrorDialog();

            });
        }


    }


}
