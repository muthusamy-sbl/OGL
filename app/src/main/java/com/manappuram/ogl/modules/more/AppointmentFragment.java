package com.manappuram.ogl.modules.more;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.FragmentAppointmentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentFragment extends Fragment {

    FragmentAppointmentBinding binding;

    public static AppointmentFragment newInstance() {

        Bundle args = new Bundle();

        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_appointment, container, false);
        binding = FragmentAppointmentBinding.inflate(inflater, container, false);


        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioYes) {
                    binding.addressLayout.setVisibility(View.GONE);
                    binding.btnSubmit.setVisibility(View.VISIBLE);
                } else {
                    binding.addressLayout.setVisibility(View.VISIBLE);
                    binding.btnSubmit.setVisibility(View.VISIBLE);
                }
            }
        });

        return binding.getRoot();
    }

}
