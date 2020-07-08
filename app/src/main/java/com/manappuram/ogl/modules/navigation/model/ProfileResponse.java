package com.manappuram.ogl.modules.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class ProfileResponse extends BaseResponse {

    @SerializedName("custid")
    @Expose
    private String custid;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone1")
    @Expose
    private String phone1;

    @SerializedName("phone2")
    @Expose
    private String phone2;

    @SerializedName("emailid")
    @Expose
    private String emailid;

    @SerializedName("housename")
    @Expose
    private String housename;

    @SerializedName("postoffice")
    @Expose
    private String postoffice;

    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("districtname")
    @Expose
    private String districtname;

    @SerializedName("statename")
    @Expose
    private String statename;

    @SerializedName("branchid")
    @Expose
    private String branchid;

    @SerializedName("branchname")
    @Expose
    private String branchname;

    public String getCustid() {
        return custid;
    }

    public String getName() {
        return name;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getHousename() {
        return housename;
    }

    public String getPostoffice() {
        return postoffice;
    }

    public String getPincode() {
        return pincode;
    }

    public String getDistrictname() {
        return districtname;
    }

    public String getStatename() {
        return statename;
    }

    public String getBranchid() {
        return branchid;
    }

    public String getBranchname() {
        return branchname;
    }

    @Override
    public String toString() {
        return "ProfileResponse{" +
                "custid='" + custid + '\'' +
                ", name='" + name + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", emailid='" + emailid + '\'' +
                ", housename='" + housename + '\'' +
                ", postoffice='" + postoffice + '\'' +
                ", pincode='" + pincode + '\'' +
                ", districtname='" + districtname + '\'' +
                ", statename='" + statename + '\'' +
                ", branchid='" + branchid + '\'' +
                ", branchname='" + branchname + '\'' +
                '}';
    }
}
