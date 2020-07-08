package com.manappuram.ogl.network;

import android.content.Context;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ApplicationFormResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.CalculatorResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.CustAccDetailsResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanFinalResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeOnlineResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.model.OglHistoryresponse;
import com.manappuram.ogl.modules.navigation.model.PawnTicketResponse;
import com.manappuram.ogl.modules.navigation.model.ProfileResponse;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileResponse;
import com.manappuram.ogl.modules.pay.model.Billdeskresponse;
import com.manappuram.ogl.modules.pay.model.FinalCheckResponse;
import com.manappuram.ogl.modules.pay.model.PayuInputResponse;
import com.manappuram.ogl.modules.pay.model.PledgeDetailResponse;
import com.manappuram.ogl.modules.pay.model.ServiceChrgeResonse;
import com.manappuram.ogl.modules.pay.model.TransSuccessResponse;
import com.manappuram.ogl.modules.pay.model.repledge;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface DashboardAPIInterface {
    @GET("aboutManppuram")
    Call<BaseResponse> aboutManppuram();

    @POST("mAccountDetails")
    @FormUrlEncoded
    Call<ArrayList<AccDetailsResponse>> mAccountDetails(@Field("cus_id") String cus_id,
                                                        @Field("uniquesessionid") String uniquesessionid,
                                                        @Field("requestid") String requestid,
                                                        @Field("langId") String langId);

    @POST("mLoanDetails")
    @FormUrlEncoded
    Call<LoanResponse> mLoanDetails(@Field("plno") String plno,
                                    @Field("cusid") String cusid,
                                    @Field("uniquesessionid") String uniquesessionid,
                                    @Field("requestid") String requestid,
                                    @Field("langId") String langId);


    @POST("mCustomerProfile")
    @FormUrlEncoded
    Call<ProfileResponse> mCustomerProfile(@Field("cusid") String cusid,
                                           @Field("uniquesessionid") String uniquesessionid,
                                           @Field("requestid") String requestid,
                                           @Field("langId") String langId);

    @POST("mCustomerEditProfile")
    @FormUrlEncoded
    Call<BaseResponse> mCustomerEditProfile(@Field("cusid") String cusid,
                                            @Field("uniquesessionid") String uniquesessionid,
                                            @Field("mobileno") String mobileno,
                                            @Field("emailid") String emailid,
                                            @Field("changemode") String changemode,
                                            @Field("requestid") String requestid,
                                            @Field("langId") String langId);

    @POST("mCustomerReqEditProfile")
    @FormUrlEncoded
    Call<ReqEditProfileResponse> mCustomerReqEditProfile(@Field("cusid") String cusid,
                                                         @Field("uniquesessionid") String uniquesessionid,
                                                         @Field("oldmobno") String oldmobno,
                                                         @Field("newmobno") String newmobno,
                                                         @Field("oldemailid") String oldemailid,
                                                         @Field("newemailid") String newemailid,
                                                         @Field("sendOTP") String sendOTP,
                                                         @Field("requestid") String requestid,
                                                         @Field("langId") String langId);


    @POST("mCustomerProfileOTPverifciation")
    @FormUrlEncoded
    Call<BaseResponse> mCustomerProfileOTPverifciation(@Field("cusid") String cusid,
                                                       @Field("uniquesessionid") String uniquesessionid,
                                                       @Field("mobileno") String mobileno,
                                                       @Field("otp") String otp,
                                                       @Field("requestid") String requestid,
                                                       @Field("langId") String langId);

    @POST("mUpdatePassword")
    @FormUrlEncoded
    Call<BaseResponse> mUpdatePassword(@Field("uid") String uid,
                                       @Field("pass") String pass,
                                       @Field("newpass") String newpass,
                                       @Field("langId") String langId);

    @POST("checkOGLCustomer")
    @FormUrlEncoded
    Call<BaseResponse> checkOGLCustomer(@Field("uniquesessionid") String uniquesessionid,
                                        @Field("requestid") String requestid,
                                        @Field("langId") String langId,
                                        @Field("custid") String custid);

    @POST("mLogout")
    @FormUrlEncoded
    Call<BaseResponse> mLogout(@Field("loginid") String loginid,
                               @Field("uniquesessionid") String uniquesessionid,
                               @Field("requestid") String requestid,
                               @Field("langId") String langId);

    @POST("GetEligibilityDetails")
    @FormUrlEncoded
    Call<BaseResponse> GetEligibilityDetails(@Field("option") String option,
                                             @Field("cusid") String cusid,
                                             @Field("uniquesessionid") String uniquesessionid,
                                             @Field("requestid") String requestid,
                                             @Field("langId") String langId);

    @POST("GetCusStatus")
    @FormUrlEncoded
    Call<BaseResponse> GetCusStatus(@Field("cusid") String cusid,
                                    @Field("uniquesessionid") String uniquesessionid,
                                    @Field("requestid") String requestid,
                                    @Field("langId") String langId);

    @POST("GetCusAccntDetails")
    @FormUrlEncoded
    Call<CustAccDetailsResponse> GetCusAccntDetails(@Field("cusid") String cusid,
                                                    @Field("uniquesessionid") String uniquesessionid,
                                                    @Field("requestid") String requestid,
                                                    @Field("langId") String langId);

    @POST("GetPledeges")
    @FormUrlEncoded
    Call<ArrayList<PledgeResponse>> GetPledeges(@Field("cusid") String cusid,
                                                @Field("uniquesessionid") String uniquesessionid,
                                                @Field("requestid") String requestid,
                                                @Field("langId") String langId);

    @POST("GetItemDetails")
    @FormUrlEncoded
    Call<ArrayList<ItemResponse>> GetItemDetails(@Field("pledgeno") String pledgeno,
                                                 @Field("cusid") String cusid,
                                                 @Field("uniquesessionid") String uniquesessionid,
                                                 @Field("requestid") String requestid,
                                                 @Field("langId") String langId);

    @POST("GetSchemeDetails")
    @FormUrlEncoded
    Call<ArrayList<SchemeResponse>> GetSchemeDetails(@Field("pledgeno") String pledgeno,
                                                     @Field("cusid") String cusid,
                                                     @Field("uniquesessionid") String uniquesessionid,
                                                     @Field("requestid") String requestid,
                                                     @Field("langId") String langId);

    @POST("GetPledgeDetailsOnline")
    @FormUrlEncoded
    Call<PledgeOnlineResponse> GetPledgeDetailsOnline(@Field("cusid") String cusid,
                                                      @Field("uniquesessionid") String uniquesessionid,
                                                      @Field("pledgeno") String pledgeno,
                                                      @Field("requestid") String requestid,
                                                      @Field("langId") String langId);


    @POST("GetSelectedSchemeDetails")
    @FormUrlEncoded
    Call<SchemeItemResponse> GetSelectedSchemeDetails(@Field("schemerow") String schemerow,
                                                      @Field("plno") String plno,
                                                      @Field("cusid") String cusid,
                                                      @Field("uniquesessionid") String uniquesessionid,
                                                      @Field("requestid") String requestid,
                                                      @Field("langId") String langId);

    @POST("GetIntrstCalcDetails")
    @FormUrlEncoded
    Call<ArrayList<CalculatorResponse>> GetIntrstCalcDetails(@Field("pledgeValue") String pledgeValue,
                                                             @Field("intrstPayDate") String intrstPayDate,
                                                             @Field("schemeName") String schemeName,
                                                             @Field("branchID") String branchID,
                                                             @Field("cusid") String cusid,
                                                             @Field("uniquesessionid") String uniquesessionid,
                                                             @Field("requestid") String requestid,
                                                             @Field("langId") String langId);

    @POST("GetRequiredAmtDetails")
    @FormUrlEncoded
    Call<BaseResponse> GetRequiredAmtDetails(@Field("cusid") String cusid,
                                             @Field("pledgeAmount") String pledgeAmount,
                                             @Field("pledgeno") String pledgeno,
                                             @Field("uniquesessionid") String uniquesessionid,
                                             @Field("requestid") String requestid,
                                             @Field("langId") String langId);


    @POST("GetTermsandConditionsDetails")
    @FormUrlEncoded
    Call<BaseResponse> GetTermsandConditionsDetails(@Field("uniquesessionid") String uniquesessionid,
                                                    @Field("requestid") String requestid,
                                                    @Field("langId") String langId);


    @POST("GetApplicationFormDetails")
    @FormUrlEncoded
    Call<ApplicationFormResponse> GetApplicationFormDetails(@Field("cusid") String cusid,
                                                            @Field("invID") String invID,
                                                            @Field("plno") String plno,
                                                            @Field("uniquesessionid") String uniquesessionid,
                                                            @Field("requestid") String requestid,
                                                            @Field("langId") String langId);


    @POST("OglConfirmation")
    @FormUrlEncoded
    Call<ConfirmationResponse> OglConfirmation(@Field("paymethod") String paymethod,
                                               @Field("amount") String amount,
                                               @Field("plno") String plno,
                                               @Field("newpledgeamnt") String newpledgeamnt,
                                               @Field("cusid") String cusid,
                                               @Field("sendOTP") String sendOTP,
                                               @Field("uniquesessionid") String uniquesessionid,
                                               @Field("requestid") String requestid,
                                               @Field("langId") String langId);


    @POST("GetNewloanDetails")
    @FormUrlEncoded
    Call<LoanFinalResponse> GetNewloanDetails(@Field("pledgeno") String pledgeno,
                                              @Field("cusid") String cusid,
                                              @Field("otp") String otp,
                                              @Field("uniquesessionid") String uniquesessionid,
                                              @Field("requestid") String requestid,
                                              @Field("langId") String langId);


    @POST("GetOglHistory")
    @FormUrlEncoded
    Call<ArrayList<OglHistoryresponse>> GetOglHistory(@Field("cusid") String cusid,
                                                      @Field("uniquesessionid") String uniquesessionid,
                                                      @Field("plno") String plno,
                                                      @Field("newplno") String newplno,
                                                      @Field("fromdate") String fromdate,
                                                      @Field("todate") String todate,
                                                      @Field("txnid") String txnid,
                                                      @Field("requestid") String requestid,
                                                      @Field("langId") String langId);


    @POST("GetOnlpaymentHistory")
    @FormUrlEncoded
    Call<ArrayList<OglHistoryresponse>> GetOnlpaymentHistory(@Field("cusid") String cusid,
                                                      @Field("uniquesessionid") String uniquesessionid,
                                                      @Field("plno") String plno,
                                                      @Field("fromdate") String fromdate,
                                                      @Field("todate") String todate,
                                                      @Field("txnid") String txnid,
                                                     @Field("paymenttype") String paymenttype ,
                                                       @Field("status") String status,
                                                      @Field("requestid") String requestid,
                                                      @Field("langId") String langId);


    @POST("GetPawnTicket")
    @FormUrlEncoded
    Call<BaseResponse> GetPawnTicket(@Field("newpledgeno") String newpledgeno,
                                     @Field("cusid") String cusid,
                                     @Field("sendOTP") String sendOTP,
                                     @Field("uniquesessionid") String uniquesessionid,
                                     @Field("requestid") String requestid,
                                     @Field("langId") String langId);

    @POST("verifyOTPforPawnTicket")
    @FormUrlEncoded
    Call<PawnTicketResponse> verifyOTPforPawnTicket(@Field("newpledgeno") String newpledgeno,
                                                    @Field("cusid") String cusid,
                                                    @Field("otp") String otp,
                                                    @Field("uniquesessionid") String uniquesessionid,
                                                    @Field("requestid") String requestid,
                                                    @Field("langId") String langId);

    @POST("mcustomerMPINrequest")
    @FormUrlEncoded
    Call<BaseResponse> mcustomerMPINrequest(@Field("cusid") String cusid,
                                            @Field("uniquesessionid") String uniquesessionid,
                                            @Field("sendOTP") String sendOTP,
                                            @Field("requestid") String requestid,
                                            @Field("langId") String langId);

    @POST("mcustomerMPINotpVerification")
    @FormUrlEncoded
    Call<BaseResponse> mcustomerMPINotpVerification(@Field("cusid") String cusid,
                                                    @Field("uniquesessionid") String uniquesessionid,
                                                    @Field("otp") String otp,
                                                    @Field("requestid") String requestid,
                                                    @Field("langId") String langId);

    @POST("mcustomerMPINgeneration")
    @FormUrlEncoded
    Call<BaseResponse> mcustomerMPINgeneration(@Field("cusid") String cusid,
                                               @Field("uniquesessionid") String uniquesessionid,
                                               @Field("deviceid") String deviceid,
                                               @Field("devicetype") String devicetype,
                                               @Field("MPIN") String MPIN,
                                               @Field("reqtype") String reqtype,
                                               @Field("requestid") String requestid,
                                               @Field("langId") String langId);

    @POST("mcustomerChangeMPIN")
    @FormUrlEncoded
    Call<BaseResponse> mcustomerChangeMPIN(@Field("cusid") String cusid,
                                           @Field("MPIN") String MPIN,
                                           @Field("newMPIN") String newMPIN,
                                           @Field("deviceid") String deviceid,
                                           @Field("langId") String langId);


    @POST("mPledgeDetails")
    @FormUrlEncoded
    Call<PledgeDetailResponse> mPledgeDetails(@Field("cus_id") String cus_id,
                                              @Field("uniquesessionid") String uniquesessionid,
                                              @Field("pledgeno") String pledgeno,
                                              @Field("requestid") String requestid,
                                              @Field("langId") String langId);


    @POST("mGetServiceCharge")
    @FormUrlEncoded
    Call<ServiceChrgeResonse> mGetServiceCharge(@Field("uniquesessionid") String uniquesessionid,
                                                @Field("cus_id") String cus_id,
                                                @Field("paymethod") String paymethod,
                                                @Field("amount") String amount,
                                                @Field("plno") String plno,
                                                @Field("paymenttype") String paymenttype,
                                                @Field("requestid") String requestid,
                                                @Field("langId") String langId);

    @POST("payuinputdata")
    @FormUrlEncoded
    Call<PayuInputResponse> payuinputdata(@Field("amount") String amount,
                                          @Field("cusid") String cusid,
                                          @Field("plno") String plno,
                                          @Field("uniquesessionid") String uniquesessionid,
                                          @Field("requestid") String requestid,
                                          @Field("langId") String langId);

    @POST("mFinalCheck")
    @FormUrlEncoded
    Call<FinalCheckResponse> finalCheck(@Field("key") String key,
                                        @Field("txnid") String txnid,
                                        @Field("amount") String amount,
                                        @Field("productinfo") String productinfo,
                                        @Field("firstname") String firstname,
                                        @Field("email") String email,
                                        @Field("udf1") String udf1,
                                        @Field("udf2") String udf2,
                                        @Field("udf3") String udf3,
                                        @Field("udf4") String udf4,
                                        @Field("udf5") String udf5,
                                        @Field("udf6") String udf6,
                                        @Field("udf7") String udf7,
                                        @Field("udf8") String udf8,
                                        @Field("udf9") String udf9,
                                        @Field("udf10") String udf10,
                                        @Field("langId") String langId,
                                        @Field("requestid") String requestid,
                                        @Field("uniquesessionid") String uniquesessionid,
                                        @Field("status") String status);

    @POST("mTransaction")
    @FormUrlEncoded
    Call<TransSuccessResponse> transaction(@Field("amount") String amount,
                                           @Field("langId") String langId,
                                           @Field("paymode") String paymode,
                                           @Field("payutransactionid") String payutransactionid,
                                           @Field("pledgeno") String pledgeno,
                                           @Field("requestid") String requestid,
                                           @Field("uniquesessionid") String uniquesessionid);


    @POST("MFailedTransaction")
    @FormUrlEncoded
    Call<BaseResponse>failedTransaction(@Field("pledge") String pledge,
                                         @Field("payutransactionid") String payutransactionid,
                                         @Field("requestid") String requestid,
                                         @Field("uniquesessionid") String uniquesessionid,
                                         @Field("langId") String langId);

    @POST("GetBilldeskRequest")
    @FormUrlEncoded
    Call<Billdeskresponse>GetBilldeskRequest(@Field("cusid") String cusid,
                                              @Field("uniquesessionid") String uniquesessionid,
                                              @Field("plno") String plno,
                                              @Field("amount") String amount,
                                              @Field("requestid") String requestid,
                                              @Field("langId") String langId);


    @POST("mPartPayAmountUpdate")
    @FormUrlEncoded
    Call<BaseResponse>GetmPartPayAmountRequest(@Field("pledge_no") String pledge_no,
                                             @Field("partamt") String partamt,
                                             @Field("cusid") String cusid,
                                             @Field("uniquesessionId") String uniquesessionId,
                                             @Field("requestid") String requestid,
                                             @Field("langId") String langId);

    @POST("mRepledgeAccept")
    @FormUrlEncoded
    Call<repledge>repledge1(@Field("pledgeno") String pledge_no,
                      @Field("sendOTP") String sendOTP,
                      @Field("cus_id") String cusid,
                      @Field("uniquesessionId") String uniquesessionId,
                      @Field("requestid") String requestid,
                      @Field("langId") String langId);


}
