package com.manappuram.ogl.modules.pay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manappuram.ogl.base.BaseResponse;

public class repledge  extends BaseResponse {
    private String postoffice;

    private String period;

    @Override
    public String toString() {
        return "repledge{" +
                "postoffice='" + postoffice + '\'' +
                ", period='" + period + '\'' +
                ", eligibleloan='" + eligibleloan + '\'' +
                ", address='" + address + '\'' +
                ", Rate='" + Rate + '\'' +
                ", interst='" + interst + '\'' +
                ", loanamount='"  + '\'' +
                ", emailid='" + emailid + '\'' +
                ", itemcount='" + itemcount + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", result='" +   '\'' +
                ", Invid='" + Invid + '\'' +
                ", requestid='" +   '\'' +
                ", district='" + district + '\'' +
                ", custid='" + custid + '\'' +
                ", name='" + name + '\'' +
                ", Actualweight='" + Actualweight + '\'' +
                ", state='" + state + '\'' +
                ", status='" +  '\'' +
                '}';
    }

    private String eligibleloan;

    private String address;

    private String Rate;

    private String interst;



    private String emailid;

    private String itemcount;

    private String mobileno;


    private String Invid;


    private String district;

    private String custid;

    private String name;

    private String Actualweight;

    private String state;


    public String getPostoffice ()
    {
        return postoffice;
    }

    public void setPostoffice (String postoffice)
    {
        this.postoffice = postoffice;
    }

    public String getPeriod ()
    {
        return period;
    }

    public void setPeriod (String period)
    {
        this.period = period;
    }

    public String getEligibleloan ()
    {
        return eligibleloan;
    }

    public void setEligibleloan (String eligibleloan)
    {
        this.eligibleloan = eligibleloan;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getRate ()
    {
        return Rate;
    }

    public void setRate (String Rate)
    {
        this.Rate = Rate;
    }

    public String getInterst ()
    {
        return interst;
    }

    public void setInterst (String interst)
    {
        this.interst = interst;
    }



    public String getEmailid ()
    {
        return emailid;
    }

    public void setEmailid (String emailid)
    {
        this.emailid = emailid;
    }

    public String getItemcount ()
    {
        return itemcount;
    }

    public void setItemcount (String itemcount)
    {
        this.itemcount = itemcount;
    }

    public String getMobileno ()
    {
        return mobileno;
    }

    public void setMobileno (String mobileno)
    {
        this.mobileno = mobileno;
    }


    public String getInvid ()
    {
        return Invid;
    }

    public void setInvid (String Invid)
    {
        this.Invid = Invid;
    }



    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public String getCustid ()
    {
        return custid;
    }

    public void setCustid (String custid)
    {
        this.custid = custid;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getActualweight ()
    {
        return Actualweight;
    }

    public void setActualweight (String Actualweight)
    {
        this.Actualweight = Actualweight;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }



}
