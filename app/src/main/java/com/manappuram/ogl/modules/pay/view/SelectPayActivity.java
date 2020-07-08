package com.manappuram.ogl.modules.pay.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivitySelectPayBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.pay.PaymentViewModel;
import com.manappuram.ogl.modules.pay.model.PledgeDetailResponse;
import com.manappuram.ogl.util.Utility;

public class SelectPayActivity extends BaseActivity {

    PaymentViewModel viewModel;
    ActivitySelectPayBinding binding;
    String pledgeNo;
    String pledgeAmt;
    String total = "";
    int mustPay = 0;
    String payMethod="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_pay);
        viewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);
        mActivity = this;
        pledgeNo = getIntent().getStringExtra("pledgeNo");
        pledgeAmt = getIntent().getStringExtra("pledgeAmt");
        binding.loanAccountNum.setText(pledgeNo);
        binding.back.setOnClickListener(v -> finish());
        tabSettings();
        getPlegeDetails();
        binding.btnSubmit.setOnClickListener(v -> {
            if (!total.equals("")) {
                if(payMethod.equals("2")){
                    total=binding.partpay.getText().toString();
                    viewModel.Partpayment(pledgeNo,total,mActivity);

                }else{
                    Intent intent = new Intent(mActivity, PaymentDetailActivity.class);
                    intent.putExtra("pledgeNo", pledgeNo);
                    intent.putExtra("pledgeAmt", total);
                    intent.putExtra("payMethod", payMethod);
                    startActivity(intent);
                }

            }
        });

        viewModel.getPartpayment().observe(this, baseResponse -> {

            updateRequestId(baseResponse.getRequestid());
            Log.d("loanResponse", baseResponse.toString());

            if(baseResponse.getStatus().equals("111")){
                Intent intent = new Intent(mActivity, PaymentDetailActivity.class);
                intent.putExtra("pledgeNo", pledgeNo);
                intent.putExtra("pledgeAmt", total);
                intent.putExtra("payMethod", payMethod);
                startActivity(intent);
            }else{
                Toast.makeText(mActivity, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        binding.addMore.setOnClickListener(v -> {
            if (binding.addMore.getText().equals("REMOVE")) {
                binding.addMore.setText("ADD");
                binding.finalTotalLayout.setVisibility(View.GONE);
            } else {
                binding.addMore.setText("REMOVE");
                binding.finalTotalLayout.setVisibility(View.VISIBLE);
                binding.finalTotal.setText("Rs." + mustPay);
            }
        });

        viewModel.getLoanMutableLiveData().observe(this, loanResponse -> {

            updateRequestId(loanResponse.getRequestid());
            Log.d("loanResponse", loanResponse.toString());
        });

    }

    private void getPlegeDetails() {

        Log.d("pledgeNo-->", pledgeNo);

//        Utility.setProgressbar(mActivity);

        viewModel.mPledgeDetails(pledgeNo, mActivity);

        viewModel.getPledgeDetailResponseMutableLiveData().observe(this, pledgeDetailResponse -> {
            Utility.cancelProgressbar();
            updateRequestId(pledgeDetailResponse.getRequestid());
            getLoanDetails();
            Log.d("pledgeDetailResponse", pledgeDetailResponse.toString());
            binding.tvInterest.setText(pledgeDetailResponse.getInterest_Amount());
            binding.tvRebate.setText(pledgeDetailResponse.getInterest_Rebate());
            binding.tvTotal.setText(pledgeDetailResponse.getInterest_Total());
            total = pledgeDetailResponse.getInterest_Total();

            String text=pledgeDetailResponse.getInterest_Amount();
            if(text.isEmpty()||text.equals("0")|text.equals("null")){
              binding.btnSubmit.setEnabled(false);
            }

            if (pledgeDetailResponse.getEligibility().equals("True")) {

                int minPay = Integer.parseInt(pledgeDetailResponse.getMinpay());
                int maxPay = Integer.parseInt(pledgeDetailResponse.getMaxPay());
                int intstTotal = Integer.parseInt(pledgeDetailResponse.getInterest_Total());
                mustPay = minPay + intstTotal;

                if (mustPay > maxPay) {
                    binding.renewLayout.setVisibility(View.GONE);
                } else {
                    binding.renewLayout.setVisibility(View.VISIBLE);
                    binding.tvRenew.setText("Add more Rs." + minPay + " for renew eligibility");
                }

            } else {
                binding.renewLayout.setVisibility(View.GONE);
            }

        });

    }


    private void getLoanDetails() {

        viewModel.loanDetails(pledgeNo, mActivity);

    }


    private void tabSettings() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Utility.hideKeyboard(mActivity);
                if (tab.getPosition() == 0) {
                    binding.interestLayout.setVisibility(View.VISIBLE);
                    binding.partLayout.setVisibility(View.GONE);
                    payMethod="1";
                } else if (tab.getPosition() == 1) {
                    binding.partLayout.setVisibility(View.VISIBLE);
                    binding.interestLayout.setVisibility(View.GONE);
                    payMethod="2";
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
}
