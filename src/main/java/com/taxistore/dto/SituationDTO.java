package com.taxistore.dto;


import com.taxistore.pojo.RecvForm;
import lombok.Builder;

import java.util.List;

/**
 * `SituationDTO` 用來存放車輛對應的司機名稱與所有收款單資訊。
 */
@Builder
public class SituationDTO {

    private Long situationId;
    private Long carseq;
    private String carno;
    private Long driverseq;
    private String driverName;
    private String driverId;
    private String portable;
    private String specialCode;
    private String companyName;
    private Long companyseq;
    private String createDate;
    private String createTime;
    private String giveCarDate;
    private String terminateDate;
    private String status;
    private Integer recvDeposit;
    private Integer retDeposit;
    private List<RecvForm> receipts;


    /**
     * 建構子
     *
     * @param driverName 司機名稱
     * @param receipts   司機對應的收款單列表
     */
    public SituationDTO(
            Long situationId,
            Long carseq,
            String carno,
            Long driverseq,
            String driverName,
            String driverId,
            String portable,
            String specialCode,
            String companyName,
            Long companyseq,
            String createDate,
            String createTime,
            String giveCarDate,
            String terminateDate,
            String status,
            Integer recvDeposit,
            Integer retDeposit,
            List<RecvForm> receipts)
    {
        this.carseq=carseq;
        this.carno = carno;
        this.driverseq=driverseq;
        this.driverName = driverName;
        this.driverId = driverId;
        this.portable = portable;
        this.specialCode = specialCode;
        this.companyName = companyName;
        this.companyseq=companyseq;
        this.createDate = createDate;
        this.createTime = createTime;
        this.status=status;
        this.giveCarDate = giveCarDate;
        this.terminateDate = terminateDate;
        this.situationId = situationId;
        this.recvDeposit = recvDeposit;
        this.retDeposit = retDeposit;
        this.receipts = receipts;
    }

    public SituationDTO()
    {

    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        companyName = companyName;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getSituationId() {
        return situationId;
    }

    public void setSituationId(Long situationId) {
        this.situationId = situationId;
    }

    public Integer getRecvDeposit() {
        return recvDeposit;
    }

    public void setRecvDeposit(Integer recvDeposit) {
        this.recvDeposit = recvDeposit;
    }

    public Integer getRetDeposit() {
        return retDeposit;
    }

    public void setRetDeposit(Integer retDeposit) {
        this.retDeposit = retDeposit;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Long getCompanyseq() {
        return companyseq;
    }

    public void setCompanyseq(Long companyseq) {
        this.companyseq = companyseq;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }


    // Getter 和 Setter 方法
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<RecvForm> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<RecvForm> receipts) {
        this.receipts = receipts;
    }
    public String getGiveCarDate() {
        return giveCarDate;
    }

    public void setGiveCarDate(String giveCarDate) {
        this.giveCarDate = giveCarDate;
    }

    public String getTerminateDate() {
        return terminateDate;
    }

    public void setTerminateDate(String terminateDate) {
        this.terminateDate = terminateDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCarseq() {
        return carseq;
    }

    public void setCarseq(Long carseq) {
        this.carseq = carseq;
    }

    public Long getDriverseq() {
        return driverseq;
    }

    public void setDriverseq(Long driverseq) {
        this.driverseq = driverseq;
    }

    @Override
    public String toString() {
        return "SituationDTO{" +
                "situationId=" + situationId +
                ", carseq=" + carseq +
                ", carno='" + carno + '\'' +
                ", driverseq=" + driverseq +
                ", driverName='" + driverName + '\'' +
                ", driverId='" + driverId + '\'' +
                ", portable='" + portable + '\'' +
                ", specialCode='" + specialCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", giveCarDate='" + giveCarDate + '\'' +
                ", terminateDate='" + terminateDate + '\'' +
                ", status='" + status + '\'' +
                ", recvDeposit=" + recvDeposit +
                ", retDeposit=" + retDeposit +
                ", receipts=" + receipts +
                '}';
    }
}
