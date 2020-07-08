package com.manappuram.ogl.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;


import com.manappuram.ogl.R;

import io.reactivex.annotations.NonNull;

public class CustomLoader extends Dialog {

    public CustomLoader(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_loader);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);
    }

}
