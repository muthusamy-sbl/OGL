package com.manappuram.ogl.modules.dashboardoptions.model;

import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;

public class AccDetailModel {


    AccDetailsResponse response;
    LoanResponse loanResponse;

    public AccDetailModel(AccDetailsResponse response, LoanResponse loanResponse) {
        this.response = response;
        this.loanResponse = loanResponse;
    }

    public AccDetailsResponse getResponse() {
        return response;
    }

    public LoanResponse getLoanResponse() {
        return loanResponse;
    }

    public void setResponse(AccDetailsResponse response) {
        this.response = response;
    }

    public void setLoanResponse(LoanResponse loanResponse) {
        this.loanResponse = loanResponse;
    }
}
