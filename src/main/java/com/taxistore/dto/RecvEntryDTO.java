package com.taxistore.dto;

public class RecvEntryDTO {
    private Long id;
    private Long formId;
    private String code;
    private String accountCode;
    private String summary;
    private String debitCredit;
    private Integer amount;
    private Long recvFormId;
    private Integer entryOrder;

    public RecvEntryDTO(Long id, Long formId, String code, String accountCode, String summary, String debitCredit, Integer amount, Long recvFormId, Integer entryOrder) {
        this.id = id;
        this.formId = formId;
        this.code = code;
        this.accountCode = accountCode;
        this.summary = summary;
        this.debitCredit = debitCredit;
        this.amount = amount;
        this.recvFormId = recvFormId;
        this.entryOrder = entryOrder;
    }

    public RecvEntryDTO() {
    }

    public Long getRecvFormId() {
        return recvFormId;
    }

    public void setRecvFormId(Long recvFormId) {
        this.recvFormId = recvFormId;
    }

    public Integer getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
        if(accountCode==null) this.accountCode="";

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
        if(code==null) this.code="";
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RecvEntryDTO{" +
                "id=" + id +
                ", formId=" + formId +
                ", accountCode='" + accountCode + '\'' +
                ", code='" + code + '\'' +
                ", summary='" + summary + '\'' +
                ", debitCredit='" + debitCredit + '\'' +
                ", amount=" + amount +
                ", recvFormId=" + recvFormId +
                ", entryOrder=" + entryOrder +
                '}';
    }
}
