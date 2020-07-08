package com.manappuram.ogl.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.regex.Pattern;

/**
 * Custom Broadcast Receiver for Auto Read OTP Message
 */
public class SMSBroadcast extends BroadcastReceiver {

    public Pattern otpPattern = Pattern.compile("(|^)\\d{6}");

    @Override
    public void onReceive(Context context, Intent intent) {
//        Bundle bundle = intent.getExtras();
//        SmsMessage[] smsm;
//        String sms_str = "";
//        String otp;
//
//        if (bundle != null) {
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            smsm = new SmsMessage[pdus.length];
//            for (int i = 0; i < smsm.length; i++) {
//                smsm[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                sms_str += smsm[i].getMessageBody();
//                String sender = smsm[i].getOriginatingAddress();
//                //Check Sender is correct or not
//                if (!sender.contains(Constants.SMS_SENDER_ID) || sms_str == null)
//                    return;
//
//                Matcher matcher = otpPattern.matcher(sms_str);
//                if (matcher.find()) {
//                    otp = matcher.group(0);
//                } else
//                    return;
//
//                Intent smsIntent = new Intent(Constants.KEY_OTP);
//                smsIntent.putExtra(Constants.KEY_MESSAGE, otp);
//                LocalBroadcastManager.getInstance(context).sendBroadcast(smsIntent);
//            }
//        }

//        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.getAction()) {
//            Bundle extras = intent.getExtras();
//            Status status = ((Status) extras.get(SmsRetriever.EXTRA_STATUS));
//
//            switch (status.getStatusCode()) {
//                case CommonStatusCodes.SUCCESS: {
//                    // Get SMS message contents
//                    String sms_str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
//                    String otp = null;
//
//                    // Extract one-time code from the message and complete verification
//                    // by sending the code back to your server for SMS authenticity.
//                    // But here we are just passing it to MainActivity
//
//                    Matcher matcher = otpPattern.matcher(sms_str);
//                    if (matcher.find()) {
//                        otp = matcher.group(0);
//                    } else
//                        return;
//
//                    Intent smsIntent = new Intent(Constants.KEY_OTP);
//                    smsIntent.putExtra(Constants.KEY_MESSAGE, otp);
//                    LocalBroadcastManager.getInstance(context).sendBroadcast(smsIntent);
//
//                }
//
//                case CommonStatusCodes.TIMEOUT: {
//
//                }
//                // Waiting for SMS timed out (5 minutes)
//                // Handle the error ...
////                otpReceiver!!.onOTPTimeOut()
//            }
//        }
    }


}
