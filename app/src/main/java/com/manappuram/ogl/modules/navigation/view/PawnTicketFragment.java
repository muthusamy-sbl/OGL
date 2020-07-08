package com.manappuram.ogl.modules.navigation.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.aigestudio.wheelpicker.WheelPicker;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.databinding.FragmentOglHistoryBinding;
import com.manappuram.ogl.databinding.FragmentPawnTicketBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeResponse;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.model.PawnTicketResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class PawnTicketFragment extends BaseFragment {

    FragmentPawnTicketBinding binding;
    DashboardViewModel viewModel;
    ArrayList<PledgeResponse> pledgeList = new ArrayList<>();

    public static PawnTicketFragment newInstance() {

        Bundle args = new Bundle();

        PawnTicketFragment fragment = new PawnTicketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PawnTicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPawnTicketBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();


        viewModel.getPledges(mActivity);

        viewModel.getPledgeLiveData().observe(this, responseList -> {
            updateRequestId(responseList.get(0).getRequestid());
            Log.d("responseList", responseList.size() + "");
            pledgeList.clear();
            pledgeList.addAll(responseList);
        });


        binding.selectPledge.setOnClickListener(v -> showAccountDialog());

        binding.btnSubmit.setOnClickListener(v -> {

            String plno = binding.selectPledge.getText().toString();
            if (!plno.equals("")) {
                pawnTicketService(plno);
            }
        });

        binding.btnVerify.setOnClickListener(v -> {

            String pin = binding.pinView.getText().toString();
            if (pin.length() == 6) {
                viewModel.verifyOTPforPawnTicket(binding.selectPledge.getText().toString(), pin, mActivity);
            }
        });


        viewModel.getPawnTicketLiveData().observe(this, pawnTicketResponse -> {

            Log.d("pawnTicketResponse", pawnTicketResponse.toString());
            Log.d("pawnTicketResponse", pawnTicketResponse.getStatus());
            updateRequestId(pawnTicketResponse.getRequestid());
            if (pawnTicketResponse.getStatus().equals("111")) {

            }

        });

        viewModel.getPawnLiveData().observe(this, baseResponse -> {

            if (baseResponse.getStatus().equals("111")) {
                updateRequestId(baseResponse.getRequestid());

                binding.selectPledge.setEnabled(false);
                binding.btnSubmit.setVisibility(View.GONE);
                binding.otpLayout.setVisibility(View.VISIBLE);

            } else {
                Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v -> Utility.closeErrorDialog());
            }

        });

        return binding.getRoot();
    }

    private void pawnTicketService(String plno) {

        viewModel.getPawnTicket(plno, mActivity);
    }

    private void showAccountDialog() {

        Dialog accountDialog;
        if (!mActivity.isFinishing()) {
            accountDialog = new Dialog(mActivity, R.style.CustomDialogTheme);
            accountDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            accountDialog.setContentView(R.layout.custom_account_selctor);
            accountDialog.setCanceledOnTouchOutside(true);
            accountDialog.setCancelable(true);

            Objects.requireNonNull(accountDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
            accountDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

            Window window = accountDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            window.setAttributes(wlp);

            List<String> accounts = new ArrayList<>();
            for (int i = 0; i < pledgeList.size(); i++) {
                accounts.add(pledgeList.get(i).getPledgeNo());
            }

            WheelPicker wheelPicker = accountDialog.findViewById(R.id.picker_ui_view);
            TextView btnSelect = accountDialog.findViewById(R.id.btnSelect);
            wheelPicker.setData(accounts);

            btnSelect.setOnClickListener(v -> {
                accountDialog.dismiss();
                binding.selectPledge.setText(accounts.get(wheelPicker.getSelectedItemPosition()));
            });

            accountDialog.show();
        }


    }
}
