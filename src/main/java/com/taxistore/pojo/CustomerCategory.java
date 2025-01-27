package com.taxistore.pojo;

public class CustomerCategory
{

    private long  CustCateID;
    private String CustCateCode;
    private String CustCateName;
    public long getCustCateID()
    {
        return CustCateID;
    }

    public void setCustCateID(long custCateID)
    {
        CustCateID = custCateID;
    }

    public String getCustCateCode()
    {
        return CustCateCode;
    }

    public void setCustCateCode(String custCateCode)
    {
        CustCateCode = custCateCode;
    }

    public String getCustCateName()
    {
        return CustCateName;
    }

    public void setCustCateName(String custCateName)
    {
        CustCateName = custCateName;
    }

    @Override
    public String toString()
    {
        return "CustomerCategory{" +
                "CustCateID=" + CustCateID +
                ", CustCateCode='" + CustCateCode + '\'' +
                ", CustCateName='" + CustCateName + '\'' +
                '}';
    }
}
