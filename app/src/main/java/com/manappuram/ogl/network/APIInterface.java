package com.manappuram.ogl.network;


import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.modules.login.model.VersionResponse;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.model.ProfileResponse;
import com.manappuram.ogl.modules.register.model.CustomerCheckResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Interface to keep allmCustomerProfile API calls
 */
public interface APIInterface {


    @GET("VersionCheck")
    Call<VersionResponse> VersionCheck();

    @POST("mLoginEncrpt")
    @FormUrlEncoded
    Call<LoginResponse> mLogin(@Field("uid") String uid,
                               @Field("pass") String pass,
                               @Field("langId") String langId);


    @POST("mRegisterCustomer")
    @FormUrlEncoded
    Call<BaseResponse> mRegisterCustomer(@Field("customerId") String customerId,
                                         @Field("mobileNo") String mobileNo,
                                         @Field("emailId") String emailId,
                                         @Field("password") String password,
                                         @Field("langId") String langId);

    @POST("mCustomerContactDetails")
    @FormUrlEncoded
    Call<CustomerCheckResponse> mCustomerContactDetails(@Field("cusid") String cusid,
                                                        @Field("langId") String langId);


    @POST("mSearchCustomerandSendOTP")
    @FormUrlEncoded
    Call<CustomerCheckResponse> mSearchCustomerandSendOTP(@Field("cusid") String cusid,
                                                          @Field("mobileno") String mobileno,
                                                          @Field("emailid") String emailid,
                                                          @Field("sendOTP") String sendOTP,
                                                          @Field("langId") String langId);

    @POST("mcustomerMPINlogin")
    @FormUrlEncoded
    Call<BaseResponse> mcustomerMPINlogin(@Field("MPIN") String MPIN,
                                          @Field("deviceid") String deviceid,
                                          @Field("langId") String langId);

    @POST("mChangePassword")
    @FormUrlEncoded
    Call<BaseResponse> mChangePassword(@Field("cusid") String cusid,
                                       @Field("sendOTP") String sendOTP,
                                       @Field("langId") String langId);

    @POST("otpVerification")
    @FormUrlEncoded
    Call<BaseResponse> otpVerification(@Field("cusid") String cusid,
                                       @Field("otp") String otp,
                                       @Field("langId") String langId);

}
