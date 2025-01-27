package com.taxistore.pojo;

import java.sql.Timestamp;

public class CompanyDTO
{
    private long companyseq;            //bigint(20) NOT NULL AUTO_INCREMENT,流水號
    private Timestamp createdate;       //datetime DEFAULT NULL,建檔日期
    private String serialno;            //varchar(255) DEFAULT NULL,序號
    private String companyname;         //varchar(255) DEFAULT NULL,公司名稱
    private String companyab;           //varchar(255) DEFAULT NULL,公司簡稱
    private String specialcode;         //varchar(255) DEFAULT NULL,特別碼
    private String taxno;               //varchar(255) DEFAULT NULL,稅籍號碼
    private long owner;                 //bigint(20) DEFAULT NULL,所屬公司流水號
    private String ownerName;           //varchar(20) DEFAULT NULL,所屬公司名稱
    private String ownerSpecialCode;    //varchar(255) DEFAULT NULL,所屬公司特別號
    private long customerCategory;      //bigint(20) DEFAULT NULL,公司分類



    private String customerCategoryCode;
    private String customerCategoryName;
    private String contact;             //varchar(255) DEFAULT NULL,負責人
    private String banno;               //varchar(255) DEFAULT NULL,統一編號
    private String telno;               //varchar(255) DEFAULT NULL,電話號碼
    private String portable;            //varchar(255) DEFAULT NULL,行動電話
    private String faxno;               //varchar(255) DEFAULT NULL,傳真號碼
    private long postcode;              //bigint(20) DEFAULT NULL,區域號碼
    private String email;               //varchar(255) DEFAULT NULL,電子郵件
    private String address;             //varchar(255) DEFAULT NULL,聯絡住址
    private String contact2;            //varchar(255) DEFAULT NULL,聯絡人
    private String invaddr;             //varchar(255) DEFAULT NULL,發票地址
    private String remark;              //varchar(5000) DEFAULT NULL,註解

    public long getCompanyseq()
    {
        return companyseq;
    }
    public void setCompanyseq(long companyseq)
    {
        this.companyseq = companyseq;
    }
    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
    public String getCustomerCategoryName()
    {
        return customerCategoryName;
    }

    public void setCustomerCategoryName(String customerCategoryName)
    {
        this.customerCategoryName = customerCategoryName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }
    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyab() {
        return companyab;
    }

    public void setCompanyab(String companyab) {
        this.companyab = companyab;
    }

    public String getSpecialcode() {
        return specialcode;
    }

    public void setSpecialcode(String specialcode) {
        this.specialcode = specialcode;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }
    public String getOwnerSpecialCode()
    {
        return ownerSpecialCode;
    }

    public void setOwnerSpecialCode(String ownerSpecialCode)
    {
        this.ownerSpecialCode = ownerSpecialCode;
    }
    public long getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(long customerCategory) {
        this.customerCategory = customerCategory;
    }
    public String getCustomerCategoryCode()
    {
        return customerCategoryCode;
    }

    public void setCustomerCategoryCode(String customerCategoryCode)
    {
        this.customerCategoryCode = customerCategoryCode;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBanno() {
        return banno;
    }

    public void setBanno(String banno) {
        this.banno = banno;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    public long getPostcode() {
        return postcode;
    }

    public void setPostcode(long postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getInvaddr() {
        return invaddr;
    }

    public void setInvaddr(String invaddr) {
        this.invaddr = invaddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public CompanyDTO()
    {
    }
}
