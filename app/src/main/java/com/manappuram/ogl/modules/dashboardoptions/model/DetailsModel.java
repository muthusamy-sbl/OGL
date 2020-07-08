package com.manappuram.ogl.modules.dashboardoptions.model;

import com.manappuram.ogl.modules.pay.model.repledge;

public class DetailsModel {

    String type;
    ApplicationFormResponse applicationFormResponse;
    String terms;

    public com.manappuram.ogl.modules.pay.model.repledge getRepledge() {
        return repledge;
    }

    public void setRepledge(com.manappuram.ogl.modules.pay.model.repledge repledge) {
        this.repledge = repledge;
    }

    repledge repledge;

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    String Amount;

    public SchemeResponse getSchemeResponse() {
        return schemeResponse;
    }

    public void setSchemeResponse(SchemeResponse schemeResponse) {
        this.schemeResponse = schemeResponse;
    }

    SchemeResponse schemeResponse;

    public DetailsModel(String type, ApplicationFormResponse applicationFormResponse, String terms) {
        this.type = type;
        this.applicationFormResponse = applicationFormResponse;
        this.terms = terms;
    }

    public String getType() {
        return type;
    }

    public ApplicationFormResponse getApplicationFormResponse() {
        return applicationFormResponse;
    }

    public String getTerms() {
        return terms;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setApplicationFormResponse(ApplicationFormResponse applicationFormResponse) {
        this.applicationFormResponse = applicationFormResponse;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
