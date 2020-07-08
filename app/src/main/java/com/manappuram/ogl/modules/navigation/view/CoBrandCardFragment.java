package com.manappuram.ogl.modules.navigation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.FragmentCoBrandCardBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoBrandCardFragment extends Fragment {

    FragmentCoBrandCardBinding binding;

    public CoBrandCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCoBrandCardBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}
