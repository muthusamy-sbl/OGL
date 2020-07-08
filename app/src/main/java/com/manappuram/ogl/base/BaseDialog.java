package com.manappuram.ogl.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.manappuram.ogl.session.UserSession;

public class BaseDialog extends DialogFragment {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Activity mActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("ess-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return super.onCreateDialog(savedInstanceState);

    }

    public void updateRequestId(String rId) {
        if (!rId.equals("") && !rId.equals("null")) {
            UserSession.getInstance().setRequestId(rId);
            editor.putString("requestId", rId);
            editor.apply();
        }
    }
}
