package com.manappuram.ogl.modules.dashboardoptions.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseDialog;
import com.manappuram.ogl.modules.dashboardoptions.model.ItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeOnlineResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SchemeDetailsFragment extends BaseDialog {

    DashboardViewModel viewModel;
    String pledgeNum;
    ItemDetailAdapter adapter;
    ArrayList<ItemResponse> itemList;

    public SchemeDetailsFragment(String pledgeNum) {
        // Required empty public constructor
        this.pledgeNum = pledgeNum;
    }


    OptionListener optionListener;

    public void setOptionListener(OptionListener optionListener) {
        this.optionListener = optionListener;
    }

    public interface OptionListener {
        void optionSelected(String item);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NotNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        mActivity = getActivity();
        View contentView = View.inflate(getContext(), R.layout.fragment_scheme_details, null);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        dialog.setContentView(contentView);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setGravity(Gravity.TOP);

            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        }


        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        TextView tvPrincipal = contentView.findViewById(R.id.tvPrincipal);
        TextView tvInterest = contentView.findViewById(R.id.tvInterest);
        TextView tvCharge = contentView.findViewById(R.id.tvCharge);
        TextView tvRebate = contentView.findViewById(R.id.tvRebate);
        TextView tvTotal = contentView.findViewById(R.id.tvTotal);
        Button btnDone = contentView.findViewById(R.id.btnDone);

        viewModel.getpledgeOnline(pledgeNum, mActivity);

        viewModel.getPledgeOnlineLiveData().observe(this, new Observer<PledgeOnlineResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(PledgeOnlineResponse pledgeOnlineResponse) {

                if (pledgeOnlineResponse.getStatus().equals("111")) {
                    updateRequestId(pledgeOnlineResponse.getRequestid());
                    tvPrincipal.setText("₹ " + pledgeOnlineResponse.getPrinciple_Amount());
                    tvInterest.setText("₹ " + pledgeOnlineResponse.getInterest_Amount());
                    tvCharge.setText("₹ " + pledgeOnlineResponse.getProcessing_Charge());
                    tvRebate.setText("₹ " + pledgeOnlineResponse.getFull_Rebate());
                    tvTotal.setText("₹ " + pledgeOnlineResponse.getFull_Total());
                }
            }
        });

        btnDone.setOnClickListener(v -> dismiss());


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }
}
