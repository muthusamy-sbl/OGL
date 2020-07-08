package com.manappuram.ogl.modules.pay.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.databinding.ActivityPaymentBinding;
import com.manappuram.ogl.modules.pay.PaymentViewModel;
import com.manappuram.ogl.modules.pay.model.PayuInputRequest;
import com.manappuram.ogl.modules.pay.model.PayuInputResponse;
import com.manappuram.ogl.modules.pay.model.PayulogRequest;
import com.manappuram.ogl.modules.pay.model.PayulogResponse;
import com.manappuram.ogl.modules.pay.model.TransSuccessResponse;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentActivity extends BaseActivity {

    private WebView mWebView;
    //private WebView mWebViewupi;
    public Activity mActivity;
    private PaymentViewModel viewModel;
    private PayuInputRequest request;
    PayuInputResponse response;
    private boolean isFailed, isSuccess = false;
    String TAG = "PaymentActivity";

    String merchantVpa = "";
    String merchantName = "";
    String referenceId = "";
    String amount = "";
    String txnId = "";
    String txnId1 = "";
    String salt = "";
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        viewModel = new PaymentViewModel();
        mActivity = this;
        initView();
        getValues();
        initListener();


        ImageView k=findViewById(R.id.back);
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.logFailedTransaction(request,mActivity,txnId1);
            }
        });

        viewModel.getfilled().observe(this, response -> {
           System.out.println(response);
            updateRequestId(response.getRequestid());
        });

        viewModel.getsuccess().observe(this, response -> {
            System.out.println(response);
            updateRequestId(response.getRequestid());
        });

    }

    private void initListener(){
        if (request.getPaymentMethod().equals(Constants.PAYU_UPI)) {
            mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    /* This call inject JavaScript into the page which just finished loading. */
                    mWebView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                }
            });
        }

        viewModel.getFinalCheckResponseMutableLiveData().observe(this, response1 -> {
            hideProgress();
            System.out.println("final"+response1.getHash());
            System.out.println(response1.getRequestid());
            updateRequestId(response1.getRequestid());

            try {
                salt = response.getSalt();
                key = response.getKey();
                String postData = "";
                System.out.println(response.toString());
                txnId1=response.getTxnid();
                if (request.getPaymentMethod().equals(Constants.PAYU_UPI)) {
                    postData = response.getUrlWithPGUPI(request.getPaymentMethod(),request.getAmount());
                } else {
                    postData = response.getUrlWithPG(request.getPaymentMethod(),request.getAmount());
                }
                String url = response.getAction();

                System.out.println(postData);
//                mWebView.postUrl(url, postData.getBytes());
                if (!isSuccess) {
                    if (request.getPaymentMethod().equals(Constants.PAYU_UPI)) {
                        showProgress();
                        mWebView.setVisibility(View.INVISIBLE);
                        mWebView.postUrl(url, postData.getBytes());
                    } else {
                        mWebView.setVisibility(View.VISIBLE);
                        mWebView.postUrl(url, postData.getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                });

            viewModel.getUrl().observe(this, response2-> {
            hideProgress();
                updateRequestId(response2.getRequestid());

                System.out.println(response2.getStatus());
                response=response2;
                viewModel.FinalCheck(response,mActivity);

        });

//        viewModel.getBaseResponse().observe(this, TransSuccessResponse -> {
//            setBackResult(Constants.SUCCESS, TransSuccessResponse);
//        });
//
//        viewModel.getFailedResponse().observe(this, baseResponse -> setBackResult(Constants.FAILED, null));

        viewModel.getPaymentRepository().getErrorsMutable().observe(this, stringEvent -> {
            hideProgress();
            if (stringEvent.getContentIfNotHandled() != null) {
                Toast.makeText(this, stringEvent.getContent().getResult(), Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED);
                finish();
            }
        });



        viewModel.getPaymentRepository().getFailMessageMutable().observe(this, stringEvent -> {
            hideProgress();
            if (stringEvent.getContentIfNotHandled() != null) {
                Toast.makeText(this, getString(R.string.network_failure), Toast.LENGTH_LONG).show();
//                Utility.showSnackbar(binding.getRoot(), getString(R.string.network_failure));
            }
        });
    }


    class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            if (request.getPaymentMethod().equals(Constants.PAYU_UPI)) {
                Log.d("html-->", html);

                String body = "";
                Pattern pattern = Pattern.compile("<body>(.*?)</body>", Pattern.DOTALL);
                Matcher matcher = pattern.matcher(html);
                while (matcher.find()) {
                    body = matcher.group(1);
                }

                try {

                    JSONObject jsonObject = new JSONObject(body);


                    merchantVpa = jsonObject.getString("merchantVpa");
                    merchantName = jsonObject.getString("merchantName");
                    referenceId = jsonObject.getString("referenceId");
                    amount = jsonObject.getString("amount");
                    txnId = jsonObject.getString("txnId");

                    Log.d("merchantVpa-->", merchantVpa);
                    Log.d("merchantVpa-->", merchantName);
                    Log.d("merchantVpa-->", referenceId);
                    Log.d("merchantVpa-->", amount);

                    String upiurl = "upi://pay?pa=" + merchantVpa + "&pn=" + merchantName + "&tr=" + referenceId + "&am=" + amount;

                    hideProgress();


                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(upiurl));
                    Intent chooser = Intent.createChooser(intent, "Pay with..");
                    try {
                        startActivityForResult(chooser, 1, null);
                    } catch (ActivityNotFoundException err) {
                        Log.d("err", err.toString());
                    }
                } catch (JSONException err) {
                    Log.d("Error", err.toString());
                }
            }
        }
    }

    private void setBackResult(String status, TransSuccessResponse transSuccessResponse) {
        hideProgress();
        String transId = viewModel.getUrl().getValue().getTxnid();
//        PaymentStatus paymentStatus = new PaymentStatus(viewModel.getUrl().getValue().getAmount(), request, status, transId);
        if (getIntent().hasExtra("parent")) {
            getIntent().putExtra("transSuccessResponse", transSuccessResponse);
        }
//        getIntent().putExtra(Constants.PAY_REQUEST, paymentStatus);
        setResult(RESULT_OK, getIntent());
        finish();
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initView() {
        //Configure WebView
        mWebView = findViewById(R.id.web);
        //Configure WebView
        mWebView.setWebViewClient(new PaymentWebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
    }


    private void getValues() {
        showProgress();
        request = getIntent().getParcelableExtra(Constants.PAY_REQUEST);
        Log.d("payu1 amount", request.getAmount());
        Log.d("payu1 m", request.getPaymentMethod());
        Log.d("payu1 t", request.getPaymentType());
        viewModel.payuinput(request, mActivity);
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private class PaymentWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return isSuccess;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (Uri.parse(url).toString().equals(Constants.Urls.PAY_SUCCESS)) {
                Log.d("payu responce", "pay success");
                isSuccess = true;

                onSuccessPayment();
            } else if (Uri.parse(url).toString().equals(Constants.Urls.PAY_FAILED)) {
                Log.d("payu responce", "pay failed");
                onFailed();
            }else{
                System.out.println("failedtrans");
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (Uri.parse(url).toString().equals(Constants.Urls.PAY_SUCCESS)) {
                isSuccess = true;
            }
            super.onPageFinished(view, url);
        }
    }

    private void onFailed() {
        isFailed = true;
//        viewModel.logFailedTransaction();
         viewModel.logFailedTransaction(request,mActivity,txnId1);
        Toast.makeText(PaymentActivity.this, getString(R.string.payment_failed), Toast.LENGTH_LONG).show();
    }

    private void onSuccessPayment() {
        isFailed = false;

       viewModel.performFinalCheck(request,mActivity,txnId1);
//        viewModel.performFinalCheck(Constants.SUCCESS);
        Toast.makeText(PaymentActivity.this, getString(R.string.payment_success), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        exitAlertDialog();
    }

    private void exitAlertDialog() {
//        Log.d("payu response", "pay failed");
//        new AlertDialog.Builder(this)
//                .setTitle(getString(R.string.cancel))
//                .setMessage(getString(R.string.confirm_payment_cancel))
//                .setCancelable(false)
//                .setPositiveButton("", (dialog, which) -> {
//                        viewModel.logFailedTransaction();
//                })
//                .setNegativeButton(getString(R.string.no), null)
//                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProgress();
        String upistatusurl = "https://test.payu.in/merchant/postservice.php?form=2";
        //String upistatusurl = "https://info.payu.in/merchant/postservice.php?form=2 ";


        try {
            String text = key + "|check_upi_txn_status|" + txnId + "|" + salt;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String hash = sb.toString();

            HashMap<String, String> params = new HashMap<>();
            params.put("key", key);
            params.put("command", "check_upi_txn_status");
            params.put("hash", hash);
            params.put("var1", txnId);
//            VolleyUtils volleyUtils = new VolleyUtils();
//            volleyUtils.callService(mActivity, PaymentActivity.this, upistatusurl, params, TAG);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }


}



