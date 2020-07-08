package com.manappuram.ogl.modules.more;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityMoreBinding;
import com.manappuram.ogl.modules.login.LoginFragment;
import com.manappuram.ogl.modules.navigation.view.WebFragment;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.FragmentUtils;

public class MoreActivity extends BaseActivity {

    ActivityMoreBinding binding;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more);

        type = getIntent().getStringExtra("type");

        binding.back.setOnClickListener(view -> finish());

        switch (type) {
            case "demo":
                binding.title.setText("Demo Video");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.OGL_WORK), false, R.id.container, false);
                break;
            case "contact":
                binding.title.setText("Contact");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.CONTACT), false, R.id.container, false);
                break;
            case "faq":
                binding.title.setText("FAQ");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.FAQ), false, R.id.container, false);
                break;
            case "book":
                binding.title.setText("Book Appointment");
                Log.d("abc", "d");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        AppointmentFragment.newInstance(), false, R.id.container, false);
                break;

            case "schemes":
                binding.title.setText("Schemes");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.OGL_SCHEMES), false, R.id.container, false);
                break;
            case "locate":
                binding.title.setText("Locate Us");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.BRANCH_LOCATOR), false, R.id.container, false);
                break;
        }


    }
}
