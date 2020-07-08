package com.manappuram.ogl.modules.navigation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.databinding.FragmentAddNeftBinding;
import com.manappuram.ogl.databinding.FragmentSetMpinBinding;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNeftFragment extends BaseFragment {

    FragmentAddNeftBinding binding;
    DashboardViewModel viewModel;

    public AddNeftFragment() {
        // Required empty public constructor
    }

    public static AddNeftFragment newInstance() {

        Bundle args = new Bundle();

        AddNeftFragment fragment = new AddNeftFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddNeftBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        viewModel.checkOGLCustomer(mActivity);

        viewModel.getCheckOglCustomerMutableLiveData().observe(this, baseResponse -> {

            updateRequestId(baseResponse.getRequestid());

            if (baseResponse.getStatus().equals("111")) {
                showAlertDialog();
            }

        });

        return binding.getRoot();
    }

    private void showAlertDialog() {

        Utility.showCustomErrorDialog(mActivity, "", "User is already exists",false, view -> {
            Utility.closeErrorDialog();
            mActivity.finish();
        });
    }
}
