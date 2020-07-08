package com.manappuram.ogl.modules.navigation.model;

public class OglHistoryresponse {

    private String amount;

    private String pledgeno;

    private String requestid;

    private String newpledgeno;

    private String txndate;

    private String txnid;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getPledgeno ()
    {
        return pledgeno;
    }

    public void setPledgeno (String pledgeno)
    {
        this.pledgeno = pledgeno;
    }

    public String getRequestid ()
    {
        return requestid;
    }

    public void setRequestid (String requestid)
    {
        this.requestid = requestid;
    }

    public String getNewpledgeno ()
    {
        return newpledgeno;
    }

    public void setNewpledgeno (String newpledgeno)
    {
        this.newpledgeno = newpledgeno;
    }

    public String getTxndate ()
    {
        return txndate;
    }

    public void setTxndate (String txndate)
    {
        this.txndate = txndate;
    }

    public String getTxnid ()
    {
        return txnid;
    }

    public void setTxnid (String txnid)
    {
        this.txnid = txnid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", pledgeno = "+pledgeno+", requestid = "+requestid+", newpledgeno = "+newpledgeno+", txndate = "+txndate+", txnid = "+txnid+"]";
    }
}
