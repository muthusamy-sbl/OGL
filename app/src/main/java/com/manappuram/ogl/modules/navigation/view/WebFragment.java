package com.manappuram.ogl.modules.navigation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.FragmentAccountBinding;
import com.manappuram.ogl.databinding.FragmentWebBinding;
import com.manappuram.ogl.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends BaseFragment {

    FragmentWebBinding binding;
    String url;

    public WebFragment(String url) {
        this.url = url;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWebBinding.inflate(inflater, container, false);

        mActivity = getActivity();

        binding.webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        binding.webView.loadUrl(url);

        return binding.getRoot();
    }
}
