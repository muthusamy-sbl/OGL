package com.manappuram.ogl.modules.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.ActivityChangePasswordBinding;
import com.manappuram.ogl.modules.login.viewmodel.LoginViewModel;
import com.manappuram.ogl.modules.navigation.model.ChangePwdRequest;
import com.manappuram.ogl.modules.navigation.view.ChangePassword;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.FragmentUtils;
import com.manappuram.ogl.util.Utility;

public class ChangePasswordActivity extends BaseActivity {

    ActivityChangePasswordBinding binding;
    String otp;
    String cusid;
    LoginViewModel viewmodel;
    DashboardViewModel dashboardViewModel;
    String pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel.class);
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        cusid = getIntent().getStringExtra("cusid");


        binding.back.setOnClickListener(v -> finish());

        ChangePassword changePassword = new ChangePassword(cusid);
        FragmentUtils.replaceFragment(getSupportFragmentManager(),
                changePassword, false, R.id.container, false);


    }


}
