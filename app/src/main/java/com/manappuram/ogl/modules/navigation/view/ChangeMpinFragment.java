package com.manappuram.ogl.modules.navigation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.databinding.FragmentChangeMpinBinding;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeMpinFragment extends BaseFragment {

    FragmentChangeMpinBinding binding;
    String oldMpin, newMpin1, newMpin2;
    DashboardViewModel viewModel;
    String m_androidId = "";

    public ChangeMpinFragment() {
        // Required empty public constructor
    }

    public static ChangeMpinFragment newInstance() {

        Bundle args = new Bundle();

        ChangeMpinFragment fragment = new ChangeMpinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChangeMpinBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        if (mActivity != null) {
            m_androidId = Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        binding.btnSubmit.setOnClickListener(v -> {
            isValid();
        });

        viewModel.getChangempinLiveData().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {

                if (baseResponse.getStatus() == null) {
                    Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v1 -> {
                        Utility.closeErrorDialog();
                        mActivity.finish();
                    });
                } else if (baseResponse.getStatus().equals("111")) {
                    updateRequestId(baseResponse.getRequestid());
                    Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v1 -> {
                        Utility.closeErrorDialog();
                        mActivity.finish();
                    });

                } else {
                    Utility.showCustomErrorDialog(mActivity, "", baseResponse.getResult(), true, v1 -> Utility.closeErrorDialog());
                }

            }
        });

        return binding.getRoot();
    }

    private void isValid() {
        oldMpin = binding.oldMpin.getText().toString();
        newMpin1 = binding.newMpin1.getText().toString();
        newMpin2 = binding.newMpin2.getText().toString();

        if (oldMpin.equals("")) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter Old MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (oldMpin.length() < 4) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter Valid Old MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (newMpin1.equals("")) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter New MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (newMpin1.length() < 4) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter Valid New MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (newMpin2.equals("")) {
            Utility.showCustomErrorDialog(mActivity, "", "Re-enter MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (newMpin2.length() < 4) {
            Utility.showCustomErrorDialog(mActivity, "", "Re-enter Valid MPIN", true, v1 -> Utility.closeErrorDialog());
        } else if (!newMpin1.equals(newMpin2)) {
            Utility.showCustomErrorDialog(mActivity, "", "Enter Same MPIN", true, v1 -> Utility.closeErrorDialog());
        } else {
            changeMpinService();
        }

    }

    private void changeMpinService() {
        viewModel.mcustomerChangeMPIN(oldMpin, newMpin1, m_androidId,mActivity);

    }
}
