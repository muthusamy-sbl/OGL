package com.manappuram.ogl.modules.dashboard;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.FragmentDashBoardBinding;
import com.manappuram.ogl.databinding.FragmentLoginBinding;
import com.manappuram.ogl.modules.dashboardoptions.view.CommonActivity;
import com.manappuram.ogl.modules.login.LoginActivity;
import com.manappuram.ogl.modules.navigation.model.AccDetailsRequest;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.session.UserSession;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends BaseFragment {


    FragmentDashBoardBinding binding;
    DashboardViewModel viewmodel;


    public static DashBoardFragment newInstance() {

        Bundle args = new Bundle();

        DashBoardFragment fragment = new DashBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dash_board, container, false);
        binding = FragmentDashBoardBinding.inflate(inflater, container, false);
        viewmodel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        binding.customerName.setText(UserSession.getInstance().getCustomerName());

        clickListeners();

        serviceResponses();

        return binding.getRoot();

    }

    private void clickListeners() {
        binding.logout.setOnClickListener(view -> viewmodel.logout(mActivity));
        binding.goldLoan.setOnClickListener(view -> gotoActivity("goldloan"));
        binding.viewAccount.setOnClickListener(view -> gotoActivity("viewaccount"));
        binding.payOnline.setOnClickListener(view -> gotoActivity("pay"));
    }


    private void serviceResponses() {
        viewmodel.getLogoutMutableLiveData().observe(this, baseResponse -> gotoLogin());
        viewmodel.getDashboardRepository().getErrorsMutable().observe(this, baseResponseEvent -> gotoLogin());
        viewmodel.getDashboardRepository().getFailMessageMutable().observe(this, stringEvent -> gotoLogin());
    }

    private void gotoActivity(String s) {
        Intent intent = new Intent(mActivity, CommonActivity.class);
        intent.putExtra("type", s);
        startActivity(intent);
    }

    private void gotoLogin() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
