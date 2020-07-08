package com.manappuram.ogl.util;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.manappuram.ogl.R;
import com.manappuram.ogl.util.datatype_utils.StringUtils;


public class BindingUtils {

    @BindingAdapter({"url"})
    public static void loadImage(ImageView view, String url) {
        if (null != url) {
            Uri uri = Uri.parse(url);
            view.setImageURI(uri);
        }
    }

    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout view, boolean isRefreshing) {
        view.setRefreshing(isRefreshing);
    }

    @BindingAdapter({"amount"})
    public static void setAmount(TextView textView, String currency) {
        textView.setText(CurrencyFormatter.format(currency));
    }

    @BindingAdapter({"percent"})
    public static void setPercentage(TextView textView, String percent) {
        textView.setText(percent + "%");
    }

    @BindingAdapter({"number"})
    public static void setNumber(TextView textView, String number) {

        if (StringUtils.isNullOrEmpty(number))
            number = "0";

        if(StringUtils.isEqual(number,"null"))
            number = "0";

        setPercentage(textView,number);
    }

    @BindingAdapter({"customfont_text"})
    public static void withCustomFont(TextView textView, String text) {
        textView.setText(ViewHelper.addFont(text, text.substring(text.length() - 4)));
    }

    @BindingAdapter({"min", "max"})
    public static void setMinMax(TextView textView, String min, String max) {
        String text = textView.getContext().getString(R.string.enter_min_max_amount);
        text = text.replace(textView.getContext().getString(R.string.min), CurrencyFormatter.format((min != null ? min : "0")));
        text = text.replace(textView.getContext().getString(R.string.max), CurrencyFormatter.format((max != null ? max : "0")));
        textView.setText(text);
    }

    @BindingAdapter({"user_value"})
    public static void setValue(TextView textView, String value) {
        String val = null;
        if (StringUtils.isNullOrEmpty(value)) {
            val = "NA";
        } else {
            val = value;
        }
        textView.setText(val);
    }
}
