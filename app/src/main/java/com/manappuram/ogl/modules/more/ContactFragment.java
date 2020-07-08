package com.manappuram.ogl.modules.more;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.FragmentContactBinding;
import com.manappuram.ogl.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    FragmentContactBinding binding;

    public static ContactFragment newInstance() {

        Bundle args = new Bundle();

        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_contact, container, false);
        binding = FragmentContactBinding.inflate(inflater, container, false);


        binding.webView.loadUrl("https://www.google.com/");

        return binding.getRoot();

    }

}
