package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityCommonBinding;
import com.manappuram.ogl.modules.pay.view.CheckDueFragment;
import com.manappuram.ogl.util.FragmentUtils;

public class CommonActivity extends BaseActivity {

    ActivityCommonBinding binding;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_common);

        type = getIntent().getStringExtra("type");

        binding.back.setOnClickListener(v -> finish());

        if (type != null) {
            loadFragments();
        }

    }

    private void loadFragments() {
        switch (type) {
            case "goldloan":
                binding.title.setText("Online Gold Loan");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        GoldLoanFragment.newInstance(), false, R.id.container, false);
                break;

            case "viewaccount":
                binding.title.setText("Account Details");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        AccountFragment.newInstance(), false, R.id.container, false);
                break;
            case "pay":
                binding.title.setText("Payment Details");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        CheckDueFragment.newInstance(), false, R.id.container, false);
                break;
        }
    }
}
