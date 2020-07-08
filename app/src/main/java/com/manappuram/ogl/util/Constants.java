package com.manappuram.ogl.util;

/**
 * Class to keep all constant values
 */
public interface Constants {



    class Urls {
        public static String BRANCH_LOCATOR = "https://www.manappuram.com/branchlocator/";
        public static String FAQ = "https://www.manappuram.com/help-ogl.html";
        public static String CONTACT = "https://www.manappuram.com/contact.html";
        public static String ABOUT_US = "https://www.manappuram.com/company/overview-of-the-company.html";
        public static String OGL_WORK = "https://www.youtube.com/watch?v=vFRT_3ZPvXk";
        public static String OGL_SCHEMES = "https://www.manappuram.com/gold-loan/operational-schemes.html";
        //Payment Gateway Url
//        public static String PAY_SUCCESS;
//        public static String PAY_FAILED;
         public static String PAY_SUCCESS="https://www.manappuram.com/app/success.php";
//
        public static String PAY_FAILED=" https://www.manappuram.com/app/failure.php";
        //Playstore Url
        //Playstore Url
        public static String PLAY_STORE = "https://play.google.com/store/apps/details?id=";

    }

    interface Menu {
        //Side Menu
        int HOME = 0;
        int MY_PROFILE = 1;
        int CHANGE_PASS = 6;
        int CHANGE_PIN = 7;
        int CHANGE_LANG = 5;
        int SCHEMES = 2;
        int HOW_OGL_WORK = 8;
        int FAQ = 9;
        int CONTACT_US = 10;
        int ABOUT_US = 11;
        int OGH_HISTORY = 3;
        int BRANCH_LOCATOR = 12;
        int TRANSACTION_HISTORY = 4;
        int ONETIME_REGISTRATION = 13;

    }

    interface MenuLogin {
        int CHANGE_LANG = 0;
        int SCHEMES = 1;
        int HOW_OGL_WORK = 2;
        int FAQ = 3;
        int CONTACT_US = 4;
        int ABOUT_US = 5;
        int BRANCH_LOCATOR = 6;
    }

    //Request Code
    int LOGIN_FRAG_REQ = 101;
    int FORGET_PASS_REQ = 102;
    int REGISTRATION_REQ = 103;
    int PROFILE_REQ = 104;
    int PAYMENT_REQUEST_CODE = 105;
    long OTP_EXPIRE_TIME = 120000;
    int DEBIT_CARD = 1;
    int NET_BANKING = 2;
    int UPI = 15;
    int REPLEDGE_SUCCESS = 110;
    int REPLEDGE_REQ_CODE = 112;
    int REPLEDGE_FAILED = 111;
    int AUTOREPLEDGE_REQUEST_CODE = 114;


    //Encryption Key
    String CIPHER_KEY = "8080808080808080";
    String CIPHER_IV = "8080808080808080";
    String SUPPORT_NO = "18004202233";

    //PaymentStatus Method
    String FULL_INTEREST_PAYMENT = "1";
    String FULL_SETTLEMENT_PAYMENT = "2";
    String PART_PAYMENT = "3";

    //PaymentStatus Types
    String BILLDESK_NETBANKING = "11";
    String BILLDESK_DEBIT_CARD = "12";
    String PAYU_DEBIT_CARD = "1";
    String PAYU_NETBANKING = "2";
    String PAYU_UPI = "15";

    //Default Values
    String RECEIPT_DATE_FORMAT = "dd/MMM/yyyy hh:mm a";
    int PASSWORD_MIN_VALUE = 8;
    int PASSWORD_MAX_VALUE = 16;

    //Keys
    String KEY_OTP = "otp";
    String KEY_MESSAGE = "message";
    String TITLE = "title";
    String IS_LANGUAGE_SELECTED = "is_language_selected";
    String IS_FIRST_RUN = "is_first_run";
    String MPIN_REQUEST_COUNT = "mpin_request_count";
    String MPIN_CREATED = "mpin_created";
    String CUSTOMER_ID = "customer_id";
    String REQUEST_ID = "request_id";
    String SEARCH_REQ_MODEL = "search_request_model";
    String REQUEST_TYPE = "request_type";
    String TYPE_FORGET_PASSWORD = "forget_password";
    String SMS_SENDER_ID = "MAFILD";
    String TYPE_REQ = "Request";
    String TYPE_RESP = "Response";
    String PAYMENT_SUCCESS = "payment_success";
    String PAYMENT_FAILED = "payment_failed";
    String PAY_REQUEST = "pay_request";
    String URL = "url";
    String DEVICE_TYPE = "Android";
    String DEVICE_ID = "device_id";
    String REQUEST_CHANGE_MPIN = "request_change_mpin";
    String REQUEST_CHANGE_PASSWORD = "request_change_password";
    String PROFILE_VERIFY = "profile_verify";
    String SUCCESS = "Successful";
    String FAILED = "Failed";
    String IS_MPIN_LOGIN = "is_mpin_login";
    String LOGIN_RESPONSE = "login_response";
    String REQUEST_SET_MPIN = "request_set_mpin";
    String IS_GUIDE_COMPLETED = "is_first_run_competed";


    String SAVED_INVISIBLE_ID = "invId";
    String SAVED_PLEDGE_NO = "pledgeNo";
    String SAVED_PLEDGE_AMOUNT = "pledgeAmount";
    String SAVED_settlement_AMOUNT = "pledgesettlement";
    String Amount_symbol="₹";
}
