package com.manappuram.ogl.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Common TextWatcher to reset Error of EditText
 */
public class GenericTextWatcher implements TextWatcher {
    TextInputLayout layout;

    public GenericTextWatcher(TextInputLayout layout) {
        this.layout = layout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (layout.isErrorEnabled()) {
            layout.setErrorEnabled(false);
            layout.setError(null);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
