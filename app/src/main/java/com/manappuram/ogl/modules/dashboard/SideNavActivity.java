package com.manappuram.ogl.modules.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivitySideNavBinding;
import com.manappuram.ogl.modules.login.LoginFragment;
import com.manappuram.ogl.modules.more.AppointmentFragment;
import com.manappuram.ogl.modules.navigation.view.AboutFragment;
import com.manappuram.ogl.modules.navigation.view.AddNeftFragment;
import com.manappuram.ogl.modules.navigation.view.ChangeMpinFragment;
import com.manappuram.ogl.modules.navigation.view.ChangePassword;
import com.manappuram.ogl.modules.navigation.view.FeedbackFragment;
import com.manappuram.ogl.modules.navigation.view.OglHistoryFragment;
import com.manappuram.ogl.modules.navigation.view.PawnTicketFragment;
import com.manappuram.ogl.modules.navigation.view.ProfileFragment;
import com.manappuram.ogl.modules.navigation.view.ReferFragment;
import com.manappuram.ogl.modules.navigation.view.SetMpinFragment;
import com.manappuram.ogl.modules.navigation.view.TransHistoryFragment;
import com.manappuram.ogl.modules.navigation.view.WebFragment;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.FragmentUtils;

public class SideNavActivity extends BaseActivity {

    ActivitySideNavBinding binding;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_side_nav);

        position = getIntent().getStringExtra("position");
        Activity i=this;
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(i);
                finish();

            }
        });

        loadFragments();
    }

    private void loadFragments() {

        switch (position) {
            case "0":
                finish();
//                binding.title.setText("Profile");
//                FragmentUtils.replaceFragment(getSupportFragmentManager(),
//                        ProfileFragment.newInstance(), false, R.id.container, false);

                break;

            case "1":
                binding.title.setText("Profile");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        ProfileFragment.newInstance(), false, R.id.container, false);

                break;
            case "2":
                binding.title.setText("Add NEFT/One Time Registration");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        AddNeftFragment.newInstance(), false, R.id.container, false);
                break;
            case "3":
                binding.title.setText("Refer A Friend");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        ReferFragment.newInstance(), false, R.id.container, false);
                break;
            case "4":
                binding.title.setText("Payment Outlet");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        ReferFragment.newInstance(), false, R.id.container, false);
                break;
            case "5":
                binding.title.setText("Download Pawn Ticket");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        PawnTicketFragment.newInstance(), false, R.id.container, false);
                break;
            case "6":
                binding.title.setText("Feedback");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        FeedbackFragment.newInstance(), false, R.id.container, false);
                break;
            case "7":
                binding.title.setText("Transaction History");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        TransHistoryFragment.newInstance(), false, R.id.container, false);
                break;
            case "8":
                binding.title.setText("OGL History");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        OglHistoryFragment.newInstance(), false, R.id.container, false);
                break;
            case "9":
                binding.title.setText("Book Appointment");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        AppointmentFragment.newInstance(), false, R.id.container, false);
                break;
            case "10":
                binding.title.setText("Co-Branded Card");
                break;
            case "11":

                binding.title.setText("Set MPIN");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        SetMpinFragment.newInstance(), false, R.id.container, false);
                break;
            case "12":

                binding.title.setText("Change MPIN");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        ChangeMpinFragment.newInstance(), false, R.id.container, false);
                break;
            case "13":
                binding.title.setText("Locate Us");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.BRANCH_LOCATOR), false, R.id.container, false);
                break;
            case "14":
                binding.title.setText("Contact");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.CONTACT), false, R.id.container, false);
                break;
            case "15":

                binding.title.setText("About");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        AboutFragment.newInstance(), false, R.id.container, false);
                break;
            case "16":
                binding.title.setText("Faq");
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        new WebFragment(Constants.Urls.FAQ), false, R.id.container, false);
                break;
            case "17":

                binding.title.setText("Change Password");
                ChangePassword changePassword = new ChangePassword(UserSession.getInstance().getCustomerId());
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        changePassword, false, R.id.container, false);
                break;

        }

    }
    public static void hideKeyboard(Activity activity) {
        View v = activity.getCurrentFocus();
        if (v != null) {


            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null && v != null;
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
