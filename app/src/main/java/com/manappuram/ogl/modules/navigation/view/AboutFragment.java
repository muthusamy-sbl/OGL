package com.manappuram.ogl.modules.navigation.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.FragmentAboutBinding;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {

    private FragmentAboutBinding binding;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        DashboardViewModel viewmodel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        viewmodel.aboutManappuram(mActivity);

        viewmodel.getAboutMutableLiveData().observe(this, baseResponse -> {
            binding.cardAbout.setVisibility(View.VISIBLE);
            binding.aboutText.setText(baseResponse.getResult());
        });


        return binding.getRoot();
    }
}
