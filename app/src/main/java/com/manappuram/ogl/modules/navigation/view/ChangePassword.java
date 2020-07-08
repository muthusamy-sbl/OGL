package com.manappuram.ogl.modules.navigation.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.FragmentChangePasswordBinding;
import com.manappuram.ogl.databinding.FragmentLoginBinding;
import com.manappuram.ogl.modules.login.LoginActivity;
import com.manappuram.ogl.modules.login.viewmodel.LoginViewModel;
import com.manappuram.ogl.modules.navigation.model.ChangePwdRequest;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends BaseFragment {

    FragmentChangePasswordBinding binding;
    String pass1, pass2;
    DashboardViewModel viewmodel;
    String custId;

    public ChangePassword(String custId) {
        // Required empty public constructor
        this.custId = custId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        viewmodel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pass1 = binding.edtPassword.getText().toString();
                pass2 = binding.edtPassword2.getText().toString();

                if (pass1.equals("")) {
                    binding.password.setError("Enter password");
                } else if (pass2.equals("")) {
                    binding.password2.setError("Re-Enter password");
                } else if (pass1.length() < 8) {
                    binding.password.setError("Enter password of minimum 8 characters");
                } else if (pass2.length() < 8) {
                    binding.password2.setError("Enter password of minimum 8 characters");
                } else if (!pass1.equals(pass2)) {
                    binding.password.setError("Passwords are not same");
                } else {
                    changePassword();
                }

            }
        });

        return binding.getRoot();
    }

    private void changePassword() {

        ChangePwdRequest request = new ChangePwdRequest(custId, Utility.encodeString(pass1), Utility.encodeString(pass2), "EN");
        viewmodel.updatePassword(request,mActivity);

        viewmodel.getChangePwdMutableLiveData().observe(this, baseResponse -> {

            if (baseResponse.getStatus().equals("111")) {
                Toast.makeText(mActivity, "Password updated successfully", Toast.LENGTH_SHORT).show();
                gotoLogin();
            } else {
                Utility.showSnackbar(binding.getRoot(), baseResponse.getResult());
            }
        });


        viewmodel.getDashboardRepository().getErrorsMutable().observe(this, baseResponseEvent -> Utility.showSnackbar(binding.getRoot(), baseResponseEvent.getContent().getResult()));

        viewmodel.getDashboardRepository().getFailMessageMutable().observe(this, stringEvent -> Utility.showSnackbar(binding.getRoot(), stringEvent.getContent()));

    }

    private void gotoLogin() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
