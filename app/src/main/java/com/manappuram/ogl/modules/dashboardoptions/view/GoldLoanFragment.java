package com.manappuram.ogl.modules.dashboardoptions.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.FragmentGoldLoanBinding;
import com.manappuram.ogl.databinding.FragmentLoginBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.CustAccDetailsResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Utility;

import java.util.Objects;

public class GoldLoanFragment extends BaseFragment {

    FragmentGoldLoanBinding binding;
    DashboardViewModel viewModel;

    public GoldLoanFragment() {
        // Required empty public constructor
    }

    public static GoldLoanFragment newInstance() {

        Bundle args = new Bundle();

        GoldLoanFragment fragment = new GoldLoanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_gold_loan, container, false);
        binding = FragmentGoldLoanBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        binding.tvCustomerWish.setText("Hai " + UserSession.getInstance().getCustomerName());

//        Utility.setProgressbar(mActivity);

        checkIsEligible();
binding.tvterms.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dialog languageDialog;


                languageDialog = new Dialog(getContext(), R.style.CustomDialogTheme);
                languageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                languageDialog.setContentView(R.layout.popupterm);
                languageDialog.setCanceledOnTouchOutside(true);
                languageDialog.setCancelable(true);

                Objects.requireNonNull(languageDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
                languageDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

                Window window = languageDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                window.setAttributes(wlp);
                TextView h=languageDialog.findViewById(R.id.terms);
                h.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        languageDialog.cancel();
                    }
                });
                languageDialog.show();


    }
});

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.checkbox.isChecked()) {

                    startActivity(new Intent(mActivity, PledgeActivity.class));
                } else {
                    Utility.showCustomErrorDialog(mActivity, "", "Accept Terms & Conditions", true, v1 -> Utility.closeErrorDialog());
                }

            }
        });

        viewModel.getDashboardRepository().getErrorsMutable().observe(this, baseResponseEvent -> Utility.showSnackbar(binding.getRoot(), baseResponseEvent.getContent().getResult()));
        viewModel.getDashboardRepository().getFailMessageMutable().observe(this, stringEvent -> Utility.showSnackbar(binding.getRoot(), stringEvent.getContent()));

        return binding.getRoot();
    }

    private void checkIsEligible() {
        viewModel.GetEligibilityDetails(mActivity);

        viewModel.getEligibilityMutableLiveData().observe(this, baseResponse -> {
            Utility.cancelProgressbar();
            Log.d("baseResponse", baseResponse.toString());
            if (baseResponse.getStatus().equals("111")) {
                updateRequestId(baseResponse.getRequestid());
                binding.tvEligibility.setText(baseResponse.getResult());
                System.out.println(baseResponse.getResult());
                getCustomerStatus();
            }
        });
    }

    private void getCustomerStatus() {

        viewModel.GetCusStatus(mActivity);

        viewModel.getCustDataMutableLiveData().observe(this, baseResponse -> {
            updateRequestId(baseResponse.getRequestid());
            if (baseResponse.getStatus().equals("111")) {
                getCustomerAccountDetails();
            } else {
                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), false, v -> {
                    Utility.closeErrorDialog();
                    mActivity.finish();
                });
            }
        });

    }

    private void getCustomerAccountDetails() {

        viewModel.GetCusAccntDetails(mActivity);

        viewModel.getCustAccDetailsLiveData().observe(this, custAccDetailsResponse -> {

            updateRequestId(custAccDetailsResponse.getRequestid());
            binding.tvCustomerName.setText(custAccDetailsResponse.getCusName());
            binding.tvCustomerPhone.setText(custAccDetailsResponse.getCusPhone());
            binding.tvCustomerMail.setText(custAccDetailsResponse.getCusEmail());
            binding.tvCustomerAccountNum.setText(custAccDetailsResponse.getCusAccnt());
            binding.tvCustomerBank.setText(custAccDetailsResponse.getBankName());
            binding.tvCustomerIfsc.setText(custAccDetailsResponse.getIfsc());
            binding.tvBranch.setText(custAccDetailsResponse.getBranchName());

        });

    }
}
