package com.manappuram.ogl.modules.login;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;


import androidx.databinding.DataBindingUtil;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityLoginBinding;
import com.manappuram.ogl.util.FragmentUtils;
import com.manappuram.ogl.util.navigator.Navigator;
import com.wessam.library.LayoutImage;
import com.wessam.library.NoInternetLayout;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);



        loadloginfragment();


    }

    private void loadloginfragment() {
        FragmentUtils.replaceFragment(getSupportFragmentManager(),
                LoginFragment.newInstance(getString(R.string.login)), false, R.id.container, false);
    }

}
