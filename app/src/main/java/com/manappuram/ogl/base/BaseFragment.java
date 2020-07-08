package com.manappuram.ogl.base;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Utility;



public class BaseFragment extends Fragment {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Activity mActivity;

    public String empCode = "";
    public String sessionId = "";
    public String empName = "";
    public String requestId = "";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("ogl-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        empCode = sharedPreferences.getString("empCode", "");
        sessionId = sharedPreferences.getString("sessionId", "");
        empName = sharedPreferences.getString("name", "");
        requestId = sharedPreferences.getString("requestId", "");
        Log.d("doorKeyonCreate", "-->" + requestId);

    }

    @Override
    public void onResume() {
        super.onResume();
        requestId = sharedPreferences.getString("doorKey", "");
        Log.d("doorKeyonResume", "-->" + requestId);
    }

    public void updateRequestId(String rId) {
        if (!rId.equals("") && !rId.equals("null")) {
            UserSession.getInstance().setRequestId(rId);
            editor.putString("requestId", rId);
            editor.apply();
        }
        requestId = sharedPreferences.getString("requestId", "");

        Log.d("doorKey", "-->" + requestId);
    }
}
