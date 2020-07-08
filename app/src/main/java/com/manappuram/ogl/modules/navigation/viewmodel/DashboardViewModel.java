package com.manappuram.ogl.modules.navigation.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ApplicationFormResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.CalcRequest;
import com.manappuram.ogl.modules.dashboardoptions.model.CalculatorResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationRequest;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.CustAccDetailsResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanFinalResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeOnlineResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.modules.login.repository.LoginRepository;
import com.manappuram.ogl.modules.navigation.model.AccDetailsRequest;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.model.ChangePwdRequest;
import com.manappuram.ogl.modules.navigation.model.EditProfileRequest;
import com.manappuram.ogl.modules.navigation.model.OglHistoryRequest;
import com.manappuram.ogl.modules.navigation.model.OglHistoryresponse;
import com.manappuram.ogl.modules.navigation.model.PawnTicketResponse;
import com.manappuram.ogl.modules.navigation.model.ProfileResponse;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileRequest;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileResponse;
import com.manappuram.ogl.modules.navigation.repository.DashboardRepository;
import com.manappuram.ogl.modules.navigation.view.ChangeMpinFragment;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    DashboardRepository dashboardRepository;
    MutableLiveData<BaseResponse> aboutMutableLiveData;
    MutableLiveData<ArrayList<AccDetailsResponse>> accDetailsMutableLiveData;
    MutableLiveData<ProfileResponse> profileMutableLiveData;
    MutableLiveData<BaseResponse> changePwdMutableLiveData;
    MutableLiveData<BaseResponse> AmountLiveData;
    MutableLiveData<BaseResponse> editProfileMutableLiveData;
    MutableLiveData<BaseResponse> logoutMutableLiveData;
    MutableLiveData<BaseResponse> checkOglCustomerMutableLiveData;
    MutableLiveData<BaseResponse> eligibilityMutableLiveData;
    MutableLiveData<BaseResponse> custDataMutableLiveData;
    MutableLiveData<CustAccDetailsResponse> custAccDetailsLiveData;
    MutableLiveData<ArrayList<PledgeResponse>> pledgeLiveData;
    MutableLiveData<ArrayList<ItemResponse>> itemLiveData;
    MutableLiveData<ArrayList<SchemeResponse>> schemeLiveData;
    MutableLiveData<ArrayList<CalculatorResponse>> calcLiveData;

    MutableLiveData<ArrayList<OglHistoryresponse>> OglHistory;
    MutableLiveData<LoanResponse> loanMutableLiveData;
    MutableLiveData<SchemeItemResponse> selectedSchemeLiveData;
    MutableLiveData<ReqEditProfileResponse> reqEditProfileLiveData;
    MutableLiveData<BaseResponse> otpProfileLiveData;
    MutableLiveData<PledgeOnlineResponse> pledgeOnlineLiveData;
    MutableLiveData<BaseResponse> pawnLiveData;
    MutableLiveData<BaseResponse> mpinRequestLiveData;
    MutableLiveData<BaseResponse> mpinOtpLiveData;
    MutableLiveData<BaseResponse> mpinGenerateLiveData;
    MutableLiveData<BaseResponse> ChangempinLiveData;
    MutableLiveData<PawnTicketResponse> pawnTicketLiveData;
    MutableLiveData<BaseResponse> termsLiveData;
    MutableLiveData<ConfirmationResponse> confirmationResponseMutableLiveData;
    MutableLiveData<LoanFinalResponse> loanFinalResponseLiveData;
    MutableLiveData<ApplicationFormResponse> applicationFormLiveData;

    public DashboardViewModel() {
        dashboardRepository = new DashboardRepository();
        aboutMutableLiveData = new MutableLiveData<>();
        OglHistory=new MutableLiveData<>();
        accDetailsMutableLiveData = new MutableLiveData<>();
        profileMutableLiveData = new MutableLiveData<>();
        changePwdMutableLiveData = new MutableLiveData<>();
        editProfileMutableLiveData = new MutableLiveData<>();
        logoutMutableLiveData = new MutableLiveData<>();
        checkOglCustomerMutableLiveData = new MutableLiveData<>();
        eligibilityMutableLiveData = new MutableLiveData<>();
        custDataMutableLiveData = new MutableLiveData<>();
        custAccDetailsLiveData = new MutableLiveData<>();
        pledgeLiveData = new MutableLiveData<>();
        itemLiveData = new MutableLiveData<>();
        schemeLiveData = new MutableLiveData<>();
        calcLiveData = new MutableLiveData<>();
        loanMutableLiveData = new MutableLiveData<>();
        selectedSchemeLiveData = new MutableLiveData<>();
        reqEditProfileLiveData = new MutableLiveData<>();
        otpProfileLiveData = new MutableLiveData<>();
        pledgeOnlineLiveData = new MutableLiveData<>();
        pawnLiveData = new MutableLiveData<>();
        mpinRequestLiveData = new MutableLiveData<>();
        mpinOtpLiveData = new MutableLiveData<>();
        mpinGenerateLiveData = new MutableLiveData<>();
        ChangempinLiveData = new MutableLiveData<>();
        pawnTicketLiveData = new MutableLiveData<>();
        termsLiveData = new MutableLiveData<>();
        confirmationResponseMutableLiveData = new MutableLiveData<>();
        loanFinalResponseLiveData = new MutableLiveData<>();
        applicationFormLiveData = new MutableLiveData<>();
        AmountLiveData=new MutableLiveData<>();
    }


    public void aboutManappuram(Context context) {
        dashboardRepository.aboutManappuram(
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    aboutMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void customerDetails(Context context) {
        dashboardRepository.customerDetails(
                (DashboardRepository.SuccessResponse<ArrayList<AccDetailsResponse>>) accDetailsResponse -> {
                    accDetailsMutableLiveData.setValue(accDetailsResponse);
                }, context);
    }

    public void loanDetails(String plno, Context context) {
        dashboardRepository.loanDetails(plno,
                (DashboardRepository.SuccessResponse<LoanResponse>) loanResponse -> {
                    loanMutableLiveData.setValue(loanResponse);
                }, context);
    }

    public void profileDetails(AccDetailsRequest request, Context context) {
        dashboardRepository.profileDetails(request,
                (DashboardRepository.SuccessResponse<ProfileResponse>) profileResponse -> {
                    profileMutableLiveData.setValue(profileResponse);
                }, context);
    }

    public void editProfile(EditProfileRequest request, Context context) {
        dashboardRepository.editProfile(request,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    editProfileMutableLiveData.setValue(baseResponse);
                }, context);
    }


    public void reqEditProfile(ReqEditProfileRequest request, Context context) {
        dashboardRepository.reqEditProfile(request,
                (DashboardRepository.SuccessResponse<ReqEditProfileResponse>) reqEditProfileResponse -> {
                    reqEditProfileLiveData.setValue(reqEditProfileResponse);
                }, context);
    }

    public void profileOTPVerify(String mobile, String otp, Context context) {
        dashboardRepository.profileOTPVerify(mobile, otp,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    otpProfileLiveData.setValue(baseResponse);
                }, context);
    }

    public void updatePassword(ChangePwdRequest request, Context context) {
        dashboardRepository.changePassword(request,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    changePwdMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void checkOGLCustomer(Context context) {
        dashboardRepository.checkOglCustomer(
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    checkOglCustomerMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void logout(Context context) {
        dashboardRepository.logout(
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    logoutMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void GetEligibilityDetails(Context context) {
        dashboardRepository.GetEligibilityDetails(
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    eligibilityMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void GetCusStatus(Context context) {
        dashboardRepository.GetCusStatus(
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    custDataMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void GetCusAccntDetails(Context context) {
        dashboardRepository.GetCusAccntDetails(
                (DashboardRepository.SuccessResponse<CustAccDetailsResponse>) custAccDetailsResponse -> {
                    custAccDetailsLiveData.setValue(custAccDetailsResponse);
                }, context);
    }

    public void getPledges(Context context) {
        dashboardRepository.GetPledeges(
                (DashboardRepository.SuccessResponse<ArrayList<PledgeResponse>>) responseList -> {
                    pledgeLiveData.setValue(responseList);
                }, context);
    }

    public void getItemDetails(String pledgeNum, Context context) {
        dashboardRepository.GetItemDetails(pledgeNum,
                (DashboardRepository.SuccessResponse<ArrayList<ItemResponse>>) responseList -> {
                    itemLiveData.setValue(responseList);
                }, context);
    }


    public void getSchemeDetails(String pledgeNum, Context context) {
        dashboardRepository.GetSchemeDetails(pledgeNum,
                (DashboardRepository.SuccessResponse<ArrayList<SchemeResponse>>) responseList -> {
                    schemeLiveData.setValue(responseList);
                }, context);
    }

    public void getpledgeOnline(String pledgeNum, Context context) {
        dashboardRepository.GetPledgeDetailsOnline(pledgeNum,
                (DashboardRepository.SuccessResponse<PledgeOnlineResponse>) pledgeOnlineResponse -> {
                    pledgeOnlineLiveData.setValue(pledgeOnlineResponse);
                }, context);
    }

    public void getSelectedSchemeDetails(String schemerow, String pledgeNum, Context context) {
        dashboardRepository.GetSelectedSchemeDetails(schemerow, pledgeNum,
                (DashboardRepository.SuccessResponse<SchemeItemResponse>) schemeItemResponse -> {
                    selectedSchemeLiveData.setValue(schemeItemResponse);
                }, context);
    }

    public void getCalculator(CalcRequest calcRequest, Context context) {
        dashboardRepository.GetIntrstCalcDetails(calcRequest,
                (DashboardRepository.SuccessResponse<ArrayList<CalculatorResponse>>) responseList -> {
                    calcLiveData.setValue(responseList);
                }, context);
    }

    public void getRequiredAmount(String pledgeAmt, String pledgeNo, Context context) {
        dashboardRepository.getRequiredAmountDetails(pledgeAmt, pledgeNo,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                     AmountLiveData.setValue(baseResponse);
                }, context);
    }

    public void getTermsandConditionsDetails(String lang, Context context) {
        dashboardRepository.GetTermsandConditionsDetails(lang, (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
            termsLiveData.setValue(baseResponse);
        }, context);
    }

    public void getApplicationFormDetails(String invId, String plno, Context context) {
        dashboardRepository.GetApplicationFormDetails(invId, plno,
                (DashboardRepository.SuccessResponse<ApplicationFormResponse>) applicationFormResponse -> {
                    applicationFormLiveData.setValue(applicationFormResponse);
                }, context);
    }

    public void getOglHistory(OglHistoryRequest request, Context context,String num) {
        dashboardRepository.getOglHistory(request,num,
                (DashboardRepository.SuccessResponse<ArrayList<OglHistoryresponse>>) responseList -> {
                    OglHistory.setValue(responseList);
                }, context);
    }

    public void getPawnTicket(String plno, Context context) {
        dashboardRepository.getPawnTicket(plno,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    pawnLiveData.setValue(baseResponse);
                }, context);
    }

    public void verifyOTPforPawnTicket(String plno, String otp, Context context) {
        dashboardRepository.verifyOTPforPawnTicket(plno, otp,
                (DashboardRepository.SuccessResponse<PawnTicketResponse>) pawnTicketResponse -> {
                    pawnTicketLiveData.setValue(pawnTicketResponse);
                }, context);
    }

    public void MPINrequest(Context context) {
        dashboardRepository.MPINrequest(
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    mpinRequestLiveData.setValue(baseResponse);
                }, context);
    }

    public void mpinOtpVerification(String otp, Context context) {
        dashboardRepository.mpinOtpVerification(otp,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    mpinOtpLiveData.setValue(baseResponse);
                }, context);
    }

    public void mcustomerMPINgeneration(String deviceid, String mpin, Context context) {
        dashboardRepository.mcustomerMPINgeneration(deviceid, mpin,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    mpinGenerateLiveData.setValue(baseResponse);
                }, context);
    }

    public void mcustomerChangeMPIN(String oldpin, String newpin, String deviceid, Context context) {
        dashboardRepository.mcustomerChangeMPIN(oldpin, newpin, deviceid,
                (DashboardRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    ChangempinLiveData.setValue(baseResponse);
                }, context);
    }

    public void OglConfirmation(ConfirmationRequest request, Context context) {
        dashboardRepository.OglConfirmation(request,
                (DashboardRepository.SuccessResponse<ConfirmationResponse>) confirmationResponse -> {
                    confirmationResponseMutableLiveData.setValue(confirmationResponse);
                }, context);
    }

    public void GetNewloanDetails(String plno, String otp, Context context) {
        dashboardRepository.GetNewloanDetails(plno, otp,
                (DashboardRepository.SuccessResponse<LoanFinalResponse>) loanFinalResponse -> {
                    loanFinalResponseLiveData.setValue(loanFinalResponse);
                }, context);
    }


    public DashboardRepository getDashboardRepository() {
        return dashboardRepository;
    }

    public MutableLiveData<BaseResponse> getAboutMutableLiveData() {
        return aboutMutableLiveData;
    }

    public MutableLiveData<ArrayList<AccDetailsResponse>> getAccDetailsMutableLiveData() {
        return accDetailsMutableLiveData;
    }

    public MutableLiveData<ProfileResponse> getProfileMutableLiveData() {
        return profileMutableLiveData;
    }

    public MutableLiveData<ArrayList<OglHistoryresponse>> getOglHistory() {
        return OglHistory;
    }

    public MutableLiveData<BaseResponse> getChangePwdMutableLiveData() {
        return changePwdMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getEditProfileMutableLiveData() {
        return editProfileMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getLogoutMutableLiveData() {
        return logoutMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getCheckOglCustomerMutableLiveData() {
        return checkOglCustomerMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getEligibilityMutableLiveData() {
        return eligibilityMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getCustDataMutableLiveData() {
        return custDataMutableLiveData;
    }

    public MutableLiveData<CustAccDetailsResponse> getCustAccDetailsLiveData() {
        return custAccDetailsLiveData;
    }

    public MutableLiveData<ArrayList<PledgeResponse>> getPledgeLiveData() {
        return pledgeLiveData;
    }

    public MutableLiveData<ArrayList<ItemResponse>> getItemLiveData() {
        return itemLiveData;
    }

    public MutableLiveData<ArrayList<SchemeResponse>> getSchemeLiveData() {
        return schemeLiveData;
    }

    public MutableLiveData<ArrayList<CalculatorResponse>> getCalcLiveData() {
        return calcLiveData;
    }
    public MutableLiveData<BaseResponse> getCalAmountresponse() {
        return AmountLiveData;
    }

    public MutableLiveData<LoanResponse> getLoanMutableLiveData() {
        return loanMutableLiveData;
    }

    public MutableLiveData<SchemeItemResponse> getSelectedSchemeLiveData() {
        return selectedSchemeLiveData;
    }

    public MutableLiveData<ReqEditProfileResponse> getReqEditProfileLiveData() {
        return reqEditProfileLiveData;
    }

    public MutableLiveData<BaseResponse> getOtpProfileLiveData() {
        return otpProfileLiveData;
    }

    public MutableLiveData<PledgeOnlineResponse> getPledgeOnlineLiveData() {
        return pledgeOnlineLiveData;
    }

    public MutableLiveData<BaseResponse> getPawnLiveData() {
        return pawnLiveData;
    }

    public MutableLiveData<BaseResponse> getMpinRequestLiveData() {
        return mpinRequestLiveData;
    }

    public MutableLiveData<BaseResponse> getMpinOtpLiveData() {
        return mpinOtpLiveData;
    }

    public MutableLiveData<BaseResponse> getMpinGenerateLiveData() {
        return mpinGenerateLiveData;
    }

    public MutableLiveData<BaseResponse> getChangempinLiveData() {
        return ChangempinLiveData;
    }

    public MutableLiveData<PawnTicketResponse> getPawnTicketLiveData() {
        return pawnTicketLiveData;
    }

    public MutableLiveData<BaseResponse> getTermsLiveData() {
        return termsLiveData;
    }

    public MutableLiveData<ConfirmationResponse> getConfirmationResponseMutableLiveData() {
        return confirmationResponseMutableLiveData;
    }

    public MutableLiveData<LoanFinalResponse> getLoanFinalResponseLiveData() {
        return loanFinalResponseLiveData;
    }

    public MutableLiveData<ApplicationFormResponse> getApplicationFormLiveData() {
        return applicationFormLiveData;
    }
}
