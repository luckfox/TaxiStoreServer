package com.taxistore.pojo;

import java.sql.Timestamp;
/*
CREATE TABLE `car` (
  `carseq` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` datetime DEFAULT NULL,
  `serialno` varchar(255) DEFAULT NULL,
  `orlicense` varchar(255) DEFAULT NULL,
  `licensestatus` varchar(255) DEFAULT NULL,
  `carno` varchar(255) DEFAULT NULL,
  `carBrand` bigint(20) DEFAULT NULL,
  `caryear` datetime DEFAULT NULL,
  `carcolor` varchar(255) DEFAULT NULL,
  `pipeamount` int(11) DEFAULT NULL,
  `mechanicss` varchar(255) DEFAULT NULL,
  `shingledat` datetime DEFAULT NULL,
  `canceldate` datetime DEFAULT NULL,
  `carlineno` varchar(255) DEFAULT NULL,
  `cpdate` datetime DEFAULT NULL,
  `cpstatus` varchar(255) DEFAULT NULL,
  `paid_security_deposit` int(11) DEFAULT NULL,
  `pre_security_deposit` int(11) DEFAULT NULL,
  `check_date` datetime DEFAULT NULL,
  `okcar` datetime DEFAULT NULL,
  `remark` varchar(5000) DEFAULT NULL,
  `companyseq` bigint(20) DEFAULT NULL,
  `acco` bigint(20) DEFAULT NULL,
  `accdate` datetime DEFAULT NULL,
  `acc_amount` int(11) DEFAULT NULL,
  `acccount` int(11) DEFAULT NULL,
  `payamount` int(11) DEFAULT NULL,
  `totalamount` int(11) DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `stopamount` int(11) DEFAULT NULL,
  `serv` bigint(20) DEFAULT NULL,
  `servicedate` datetime DEFAULT NULL,
  `stopservice` datetime DEFAULT NULL,
  `servicetype` varchar(255) DEFAULT NULL,
  `monthamount` int(11) DEFAULT NULL,
  `any_serv` bigint(20) DEFAULT NULL,
  `any_serv_no` varchar(255) DEFAULT NULL,
  `any_serv_start_date` datetime DEFAULT NULL,
  `any_serv_amount` int(11) DEFAULT NULL,
  `any_serv_end_date` datetime DEFAULT NULL,
  `force_serv` bigint(20) DEFAULT NULL,
  `force_serv_no` varchar(255) DEFAULT NULL,
  `force_serv_start_date` datetime DEFAULT NULL,
  `force_serv_amount` int(11) DEFAULT NULL,
  `force_serv_end_date` datetime DEFAULT NULL,
  `union_serv` bigint(20) DEFAULT NULL,
  `union_serv_start_date` datetime DEFAULT NULL,
  `union_serv_end_date` datetime DEFAULT NULL,
  `union_serv_amount` int(11) DEFAULT NULL,
  `buy_amount` int(11) DEFAULT NULL,
  `buy_date` datetime DEFAULT NULL,
  `sell_amount` int(11) DEFAULT NULL,
  `sell_date` datetime DEFAULT NULL,
  PRIMARY KEY (`carseq`),
  UNIQUE KEY `carno` (`carno`),
  KEY `FK107B43735E663` (`companyseq`),
  KEY `FK107B4EEAA3DA2` (`carBrand`),
  KEY `FK107B469F1E3B7` (`serv`),
  KEY `FK107B411092F27` (`union_serv`),
  KEY `FK107B471C42C8B` (`force_serv`),
  KEY `FK107B454E6762A` (`any_serv`),
  KEY `FK107B469E9ABAF` (`acco`)
) ENGINE=InnoDB AUTO_INCREMENT=2021 DEFAULT CHARSET=utf8

 */
public class Car
{
    private long carseq;                    //bigint(20), NOT NULL AUTO_INCREMENT,
    private Timestamp createdate;           //datetime,建檔日期
    private String serialno;                //varchar(255),
    private String orlicense;               //varchar(255),原牌照號碼
    private String licensestatus;           //varchar(255),車牌狀態
    private String carno;                   //varchar(255),車牌號碼
    private long carBrand;                  //bigint(20),廠牌車款==>對映carband(carbandid)
    private Timestamp caryear;              //datetime,車輛年份,
    private String carcolor;                //varchar(255),顏色,
    private Integer pipeamount;             //int(11),排氣量,
    private String mechanicss;              //varchar(255),引擎號碼,
    private Timestamp shingledat;           //datetime,發照日,
    private Timestamp canceldate;           //datetime,繳銷日,
    private String carlineno;               //varchar(255),車身號碼,
    private Timestamp cpdate;               //datetime,下次驗表日,
    private String cpstatus;                //varchar(255),
    private Integer paid_security_deposit;  //int(11),已收保證金,
    private Integer pre_security_deposit;   //int(11),預收保證金,
    private Timestamp check_date;           //datetime,指定驗車日,
    private Timestamp okcar;                //datetime,上次驗車月,
    private String remark;                  //varchar(5000),註解,
    private long companyseq;                //bigint(20),公司序號＝＝>company (companyseq)
    private long acco;                      //bigint(20) ,貸款公司＝＝>company (companyseq)
    private Timestamp accdate;              //datetime ,貸款起始日期
    private Integer acc_amount;             //int(11),貸款車價格,
    private Integer acccount;               //int(11),貸款期數,
    private Integer payamount;              //int(11),每期繳款金額,
    private Integer totalamount;            //int(11),貸款應繳總額,
    private Timestamp enddate;              //datetime,貸款截止日,
    private Integer stopamount;             //int(11),毛利,
    private long serv;                      //bigint(20),保全公司ID＝＝>company (companyseq)
    private Timestamp servicedate;          //datetime,投保全日期,
    private Timestamp stopservice;          //datetime,退保全日期,
    private String servicetype;             //varchar(255),保全種類L,
    private Integer monthamount;            //int(11),保全月金額,
    private long any_serv;                  //bigint(20),任意險公司＝＝>company (companyseq)
    private String any_serv_no;             //varchar(255),任意險卡號,
    private Timestamp any_serv_start_date;  //datetime,任意險開始日,
    private Integer any_serv_amount;        //int(11),繳款金額,
    private Timestamp any_serv_end_date;    //datetime,任意險結束日,
    private long force_serv;                //bigint(20),強制險公司＝＝>company (companyseq)
    private String force_serv_no;           //varchar(255),強制險卡號,
    private Timestamp force_serv_start_date; //datetime,強制險開始日,
    private Integer force_serv_amount;       //int(11),強制險年繳金額,
    private Timestamp force_serv_end_date;   //datetime,強制險結束日,
    private long union_serv;                //bigint(20),聯保公司＝＝>company (companyseq)
    private Timestamp union_serv_start_date; //datetime,聯保起始日,
    private Timestamp union_serv_end_date;   //datetime,聯保結束日,
    private Integer union_serv_amount;       //int(11),聯保月繳金額,
    private Integer buy_amount;              //int(11),購入金額,
    private Timestamp buy_date;              //datetime,購入日期,
    private Integer sell_amount;             //int(11),售價,
    private Timestamp sell_date;             //datetime,銷售日期,

    class company
    {
        private String companyname;
        private String specialCode;

        public String getSpecialCode()
        {
            return specialCode;
        }
        public void setSpecialCode(String specialCode)
        {
            this.specialCode = specialCode;
        }
        public String getCompanyname()
        {
            return companyname;
        }

        public void setCompanyname(String companyname)
        {
            this.companyname = companyname;
        }


    }


    public long getCarseq()
    {
        return carseq;
    }

    public void setCarseq(long carseq)
    {
        this.carseq = carseq;
    }

    public Timestamp getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate)
    {
        this.createdate = createdate;
    }
    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getOrlicense() {
        return orlicense;
    }

    public void setOrlicense(String orlicense) {
        this.orlicense = orlicense;
    }

    public String getLicensestatus() {
        return licensestatus;
    }

    public void setLicensestatus(String licensestatus) {
        this.licensestatus = licensestatus;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public long getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(long carBrand) {
        this.carBrand = carBrand;
    }

    public Timestamp getCaryear() {
        return caryear;
    }

    public void setCaryear(Timestamp caryear) {
        this.caryear = caryear;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public Integer getPipeamount() {
        return pipeamount;
    }

    public void setPipeamount(Integer pipeamount) {
        this.pipeamount = pipeamount;
    }

    public String getMechanicss() {
        return mechanicss;
    }

    public void setMechanicss(String mechanicss) {
        this.mechanicss = mechanicss;
    }

    public Timestamp getShingledat() {
        return shingledat;
    }

    public void setShingledat(Timestamp shingledat) {
        this.shingledat = shingledat;
    }

    public Timestamp getCanceldate() {
        return canceldate;
    }

    public void setCanceldate(Timestamp canceldate) {
        this.canceldate = canceldate;
    }

    public String getCarlineno() {
        return carlineno;
    }

    public void setCarlineno(String carlineno) {
        this.carlineno = carlineno;
    }

    public Timestamp getCpdate() {
        return cpdate;
    }

    public void setCpdate(Timestamp cpdate) {
        this.cpdate = cpdate;
    }

    public String getCpstatus() {
        return cpstatus;
    }

    public void setCpstatus(String cpstatus) {
        this.cpstatus = cpstatus;
    }

    public Integer getPaid_security_deposit() {
        return paid_security_deposit;
    }

    public void setPaid_security_deposit(Integer paid_security_deposit) {
        this.paid_security_deposit = paid_security_deposit;
    }

    public Integer getPre_security_deposit() {
        return pre_security_deposit;
    }

    public void setPre_security_deposit(Integer pre_security_deposit) {
        this.pre_security_deposit = pre_security_deposit;
    }

    public Timestamp getCheck_date() {
        return check_date;
    }

    public void setCheck_date(Timestamp check_date) {
        this.check_date = check_date;
    }

    public Timestamp getOkcar() {
        return okcar;
    }

    public void setOkcar(Timestamp okcar) {
        this.okcar = okcar;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCompanyseq() {
        return companyseq;
    }

    public void setCompanyseq(long companyseq) {
        this.companyseq = companyseq;
    }

    public long getAcco() {
        return acco;
    }

    public void setAcco(long acco) {
        this.acco = acco;
    }

    public Timestamp getAccdate() {
        return accdate;
    }

    public void setAccdate(Timestamp accdate) {
        this.accdate = accdate;
    }

    public Integer getAcc_amount() {
        return acc_amount;
    }

    public void setAcc_amount(Integer acc_amount) {
        this.acc_amount = acc_amount;
    }

    public Integer getAcccount() {
        return acccount;
    }

    public void setAcccount(Integer acccount) {
        this.acccount = acccount;
    }

    public Integer getPayamount() {
        return payamount;
    }

    public void setPayamount(Integer payamount) {
        this.payamount = payamount;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Integer getStopamount() {
        return stopamount;
    }

    public void setStopamount(Integer stopamount) {
        this.stopamount = stopamount;
    }

    public long getServ() {
        return serv;
    }

    public void setServ(long serv) {
        this.serv = serv;
    }

    public Timestamp getServicedate() {
        return servicedate;
    }

    public void setServicedate(Timestamp servicedate) {
        this.servicedate = servicedate;
    }

    public Timestamp getStopservice() {
        return stopservice;
    }

    public void setStopservice(Timestamp stopservice) {
        this.stopservice = stopservice;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public Integer getMonthamount() {
        return monthamount;
    }

    public void setMonthamount(Integer monthamount) {
        this.monthamount = monthamount;
    }

    public long getAny_serv() {
        return any_serv;
    }

    public void setAny_serv(long any_serv) {
        this.any_serv = any_serv;
    }

    public String getAny_serv_no() {
        return any_serv_no;
    }

    public void setAny_serv_no(String any_serv_no) {
        this.any_serv_no = any_serv_no;
    }

    public Timestamp getAny_serv_start_date() {
        return any_serv_start_date;
    }

    public void setAny_serv_start_date(Timestamp any_serv_start_date) {
        this.any_serv_start_date = any_serv_start_date;
    }

    public Integer getAny_serv_amount() {
        return any_serv_amount;
    }

    public void setAny_serv_amount(Integer any_serv_amount) {
        this.any_serv_amount = any_serv_amount;
    }

    public Timestamp getAny_serv_end_date() {
        return any_serv_end_date;
    }

    public void setAny_serv_end_date(Timestamp any_serv_end_date) {
        this.any_serv_end_date = any_serv_end_date;
    }

    public long getForce_serv() {
        return force_serv;
    }

    public void setForce_serv(long force_serv) {
        this.force_serv = force_serv;
    }

    public String getForce_serv_no() {
        return force_serv_no;
    }

    public void setForce_serv_no(String force_serv_no) {
        this.force_serv_no = force_serv_no;
    }

    public Timestamp getForce_serv_start_date() {
        return force_serv_start_date;
    }

    public void setForce_serv_start_date(Timestamp force_serv_start_date) {
        this.force_serv_start_date = force_serv_start_date;
    }

    public Integer getForce_serv_amount() {
        return force_serv_amount;
    }

    public void setForce_serv_amount(Integer force_serv_amount) {
        this.force_serv_amount = force_serv_amount;
    }

    public Timestamp getForce_serv_end_date() {
        return force_serv_end_date;
    }

    public void setForce_serv_end_date(Timestamp force_serv_end_date) {
        this.force_serv_end_date = force_serv_end_date;
    }

    public long getUnion_serv() {
        return union_serv;
    }

    public void setUnion_serv(long union_serv) {
        this.union_serv = union_serv;
    }

    public Timestamp getUnion_serv_start_date() {
        return union_serv_start_date;
    }

    public void setUnion_serv_start_date(Timestamp union_serv_start_date) {
        this.union_serv_start_date = union_serv_start_date;
    }

    public Timestamp getUnion_serv_end_date() {
        return union_serv_end_date;
    }

    public void setUnion_serv_end_date(Timestamp union_serv_end_date) {
        this.union_serv_end_date = union_serv_end_date;
    }

    public Integer getUnion_serv_amount() {
        return union_serv_amount;
    }

    public void setUnion_serv_amount(Integer union_serv_amount) {
        this.union_serv_amount = union_serv_amount;
    }

    public Integer getBuy_amount() {
        return buy_amount;
    }

    public void setBuy_amount(Integer buy_amount) {
        this.buy_amount = buy_amount;
    }

    public Timestamp getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(Timestamp buy_date) {
        this.buy_date = buy_date;
    }

    public Integer getSell_amount() {
        return sell_amount;
    }

    public void setSell_amount(Integer sell_amount) {
        this.sell_amount = sell_amount;
    }

    public Timestamp getSell_date() {
        return sell_date;
    }

    public void setSell_date(Timestamp sell_date) {
        this.sell_date = sell_date;
    }


    @Override
    public String toString() {
        return "Car{" +
                "carseq=" + carseq +
                ", createdate=" + createdate +
                ", serialno='" + serialno + '\'' +
                ", orlicense='" + orlicense + '\'' +
                ", licensestatus='" + licensestatus + '\'' +
                ", carno='" + carno + '\'' +
                ", carBrand=" + carBrand +
                ", caryear=" + caryear +
                ", carcolor='" + carcolor + '\'' +
                ", pipeamount=" + pipeamount +
                ", mechanicss='" + mechanicss + '\'' +
                ", shingledat=" + shingledat +
                ", canceldate=" + canceldate +
                ", carlineno='" + carlineno + '\'' +
                ", cpdate=" + cpdate +
                ", cpstatus='" + cpstatus + '\'' +
                ", paid_security_deposit=" + paid_security_deposit +
                ", pre_security_deposit=" + pre_security_deposit +
                ", check_date=" + check_date +
                ", okcar=" + okcar +
                ", remark='" + remark + '\'' +
                ", companyseq=" + companyseq +
                ", acco=" + acco +
                ", accdate=" + accdate +
                ", acc_amount=" + acc_amount +
                ", acccount=" + acccount +
                ", payamount=" + payamount +
                ", totalamount=" + totalamount +
                ", enddate=" + enddate +
                ", stopamount=" + stopamount +
                ", serv=" + serv +
                ", servicedate=" + servicedate +
                ", stopservice=" + stopservice +
                ", servicetype='" + servicetype + '\'' +
                ", monthamount=" + monthamount +
                ", any_serv=" + any_serv +
                ", any_serv_no='" + any_serv_no + '\'' +
                ", any_serv_start_date=" + any_serv_start_date +
                ", any_serv_amount=" + any_serv_amount +
                ", any_serv_end_date=" + any_serv_end_date +
                ", force_serv=" + force_serv +
                ", force_serv_no='" + force_serv_no + '\'' +
                ", force_serv_start_date=" + force_serv_start_date +
                ", force_serv_amount=" + force_serv_amount +
                ", force_serv_end_date=" + force_serv_end_date +
                ", union_serv=" + union_serv +
                ", union_serv_start_date=" + union_serv_start_date +
                ", union_serv_end_date=" + union_serv_end_date +
                ", union_serv_amount=" + union_serv_amount +
                ", buy_amount=" + buy_amount +
                ", buy_date=" + buy_date +
                ", sell_amount=" + sell_amount +
                ", sell_date=" + sell_date +
                '}';
    }

    public Car()
    {
    }

}
