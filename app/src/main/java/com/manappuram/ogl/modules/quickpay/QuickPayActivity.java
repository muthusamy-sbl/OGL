package com.manappuram.ogl.modules.quickpay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityQuickPayBinding;

public class QuickPayActivity extends BaseActivity {

    ActivityQuickPayBinding binding;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quick_pay);

        mActivity = this;

        qrScan = new IntentIntegrator(mActivity);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(mActivity, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                String phone = result.getContents();
//                callContactService(phone, true);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
