package com.manappuram.ogl.modules.pay.model;

import com.manappuram.ogl.base.BaseResponse;

public class Billdeskresponse extends BaseResponse {

    private String amount;

    private String strPayCategory;

    private String uniquetxnid;

    private String postString;



    private String merchantID;

    private String phone;


    private String action;

    private String customername;

    private String email;

    private String checksumhash;


    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getStrPayCategory ()
    {
        return strPayCategory;
    }

    public void setStrPayCategory (String strPayCategory)
    {
        this.strPayCategory = strPayCategory;
    }

    public String getUniquetxnid ()
    {
        return uniquetxnid;
    }

    public void setUniquetxnid (String uniquetxnid)
    {
        this.uniquetxnid = uniquetxnid;
    }

    public String getPostString ()
    {
        return postString;
    }

    public void setPostString (String postString)
    {
        this.postString = postString;
    }



    public String getMerchantID ()
    {
        return merchantID;
    }

    public void setMerchantID (String merchantID)
    {
        this.merchantID = merchantID;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }


    public String getAction ()
    {
        return action;
    }

    public void setAction (String action)
    {
        this.action = action;
    }

    public String getCustomername ()
    {
        return customername;
    }

    public void setCustomername (String customername)
    {
        this.customername = customername;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getChecksumhash ()
    {
        return checksumhash;
    }

    public void setChecksumhash (String checksumhash)
    {
        this.checksumhash = checksumhash;
    }



}
