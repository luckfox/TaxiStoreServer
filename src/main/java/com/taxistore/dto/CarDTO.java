package com.taxistore.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CarDTO
{
    private long carseq;                    //bigint(20), NOT NULL AUTO_INCREMENT,
    private Timestamp createdate;           //datetime,建檔日期
    private String str_createdate;
    private String serialno;                //varchar(255),
    private String orlicense;               //varchar(255),原牌照號碼
    private String licensestatus;           //varchar(255),車牌狀態
    private String carno;                   //varchar(255),車牌號碼
    private String driverseq;               //
    private String driverName;              //
    private String driverSC;                //
    private String driverID;
    private String driverPhone;
    private Timestamp driverBirthday;
    private String str_driverBirthday;
    private long carBrand;                  //bigint(20),廠牌車款==>對映carband(carbandid)
    private String carBrandSC;              //*車輛廠牌特別碼
    private String carBrandName;            //*車輛廠牌名稱
    private Timestamp caryear;              //datetime,車輛出場年份,
    private String str_caryear;
    private String carcolor;                //varchar(255),顏色,
    private Integer pipeamount;             //int(11),排氣量,
    private String mechanicss;              //varchar(255),引擎號碼,
    private Timestamp shingledat;           //datetime,發照日,
    private String str_shingledat;
    private Timestamp canceldate;           //datetime,繳銷日,
    private String str_canceldate;
    private String carlineno;               //varchar(255),車身號碼,
    private Timestamp cpdate;               //datetime,下次驗表日,
    private String str_cpdate;
    private String cpstatus;                //varchar(255),
    private Integer paid_security_deposit;  //int(11),已收保證金,
    private Integer pre_security_deposit;   //int(11),預收保證金,
    private Timestamp check_date;           //datetime,指定驗車日,
    private String str_check_date;
    private Timestamp okcar;                //datetime,上次驗車月,
    private String str_okcar;
    private String remark;                  //varchar(5000),註解,
    private long companyseq;                //bigint(20),所屬車行序號＝＝>company (companyseq)
    private String ownerName;               //*所屬車行名稱
    private String ownerSC;                 //*所屬車行特別碼
    private String ownerCompanyName;            //所屬公司名稱
    private String ownerCompanySC;          //所屬公司特別碼
    private long acco;                      //bigint(20) ,貸款公司＝＝>company (companyseq)
    private String accoName;                //*貸款公司名稱
    private String accoSC;                  //*貸款公司特別碼
    private Timestamp accdate;              //datetime ,貸款起始日期
    private String str_accdate;
    private Integer acc_amount;             //int(11),貸款車價格,
    private Integer acccount;               //int(11),貸款期數,
    private Integer payamount;              //int(11),每期繳款金額,
    private Integer totalamount;            //int(11),貸款應繳總額,
    private Timestamp enddate;              //datetime,貸款截止日,
    private String str_enddate;
    private Integer stopamount;             //int(11),毛利,
    private long serv;                      //bigint(20),保全公司＝＝>company (companyseq)
    private String servName;                //*保全公司名稱
    private String servSC;                  //*保全公司特別碼
    private Timestamp servicedate;          //datetime,投保全日期,
    private String str_servicedate;
    private Timestamp stopservice;          //datetime,退保全日期,
    private String str_stopservice;
    private String servicetype;             //varchar(255),保全種類L,
    private Integer monthamount;            //int(11),保全月金額,
    private long any_serv;                  //bigint(20),任意險公司＝＝>company (companyseq)
    private String any_servName;            //*任意險公司名稱
    private String any_servSC;              //*任意險公司特別碼
    private String any_serv_no;             //varchar(255),任意險卡號,
    private Timestamp any_serv_start_date;  //datetime,任意險開始日,
    private String str_any_serv_start_date;
    private Integer any_serv_amount;        //int(11),繳款金額,
    private Timestamp any_serv_end_date;    //datetime,任意險結束日,
    private String str_any_serv_end_date;
    private long force_serv;                //bigint(20),強制險公司＝＝>company (companyseq)
    private String force_servName;          //*強制險公司名稱
    private String force_servSC;            //*強制險公司特別碼
    private String force_serv_no;           //varchar(255),強制險卡號,
    private Timestamp force_serv_start_date; //datetime,強制險開始日,
    private String str_force_serv_start_date;
    private Integer force_serv_amount;       //int(11),強制險年繳金額,
    private Timestamp force_serv_end_date;   //datetime,強制險結束日,
    private String str_force_serv_end_date;
    private long union_serv;                //bigint(20),聯保公司＝＝>company (companyseq)
    private String union_servName;          //*聯保公司名稱
    private String union_servSC;            //*聯保公司特別碼
    private Timestamp union_serv_start_date; //datetime,聯保起始日,
    private String str_union_serv_start_date;
    private Timestamp union_serv_end_date;   //datetime,聯保結束日,
    private String str_union_serv_end_date;
    private Integer union_serv_amount;       //int(11),聯保月繳金額,
    private Integer buy_amount;              //int(11),購入金額,
    private Timestamp buy_date;              //datetime,購入日期,
    private String str_buy_date;
    private Integer sell_amount;             //int(11),售價,
    private Timestamp sell_date;             //datetime,銷售日期,
    private String str_sell_date;


    public CarDTO()
    {
    }

    public String getStr_force_serv_end_date()
    {
        return str_force_serv_end_date;
    }

    public void setStr_force_serv_end_date(String str_force_serv_end_date)
    {
        this.str_force_serv_end_date = str_force_serv_end_date;
    }
    public String getStr_driverBirthday()
    {
        return str_driverBirthday;
    }

    public void setStr_driverBirthday(String str_driverBirthday)
    {
        this.str_driverBirthday = str_driverBirthday;
    }

    public String getStr_canceldate()
    {
        return str_canceldate;
    }

    public void setStr_canceldate(String str_canceldate)
    {
        this.str_canceldate = str_canceldate;
    }

    public String getStr_cpdate()
    {
        return str_cpdate;
    }

    public void setStr_cpdate(String str_cpdate)
    {
        this.str_cpdate = str_cpdate;
    }

    public String getStr_check_date()
    {
        return str_check_date;
    }

    public void setStr_check_date(String str_check_date)
    {
        this.str_check_date = str_check_date;
    }

    public String getStr_okcar()
    {
        return str_okcar;
    }

    public void setStr_okcar(String str_okcar)
    {
        this.str_okcar = str_okcar;
    }

    public String getStr_accdate()
    {
        return str_accdate;
    }

    public void setStr_accdate(String str_accdate)
    {
        this.str_accdate = str_accdate;
    }

    public String getStr_enddate()
    {
        return str_enddate;
    }

    public void setStr_enddate(String str_enddate)
    {
        this.str_enddate = str_enddate;
    }

    public String getStr_servicedate()
    {
        return str_servicedate;
    }

    public void setStr_servicedate(String str_servicedate)
    {
        this.str_servicedate = str_servicedate;
    }

    public String getStr_stopservice()
    {
        return str_stopservice;
    }

    public void setStr_stopservice(String str_stopservice)
    {
        this.str_stopservice = str_stopservice;
    }

    public String getStr_any_serv_start_date()
    {
        return str_any_serv_start_date;
    }

    public void setStr_any_serv_start_date(String str_any_serv_start_date)
    {
        this.str_any_serv_start_date = str_any_serv_start_date;
    }

    public String getStr_any_serv_end_date()
    {
        return str_any_serv_end_date;
    }

    public void setStr_any_serv_end_date(String str_any_serv_end_date)
    {
        this.str_any_serv_end_date = str_any_serv_end_date;
    }

    public String getStr_force_serv_start_date()
    {
        return str_force_serv_start_date;
    }

    public void setStr_force_serv_start_date(String str_force_serv_start_date)
    {
        this.str_force_serv_start_date = str_force_serv_start_date;
    }

    public String getStr_union_serv_start_date()
    {
        return str_union_serv_start_date;
    }

    public void setStr_union_serv_start_date(String str_union_serv_start_date)
    {
        this.str_union_serv_start_date = str_union_serv_start_date;
    }

    public String getStr_union_serv_end_date()
    {
        return str_union_serv_end_date;
    }

    public void setStr_union_serv_end_date(String str_union_serv_end_date)
    {
        this.str_union_serv_end_date = str_union_serv_end_date;
    }

    public String getStr_buy_date()
    {
        return str_buy_date;
    }

    public void setStr_buy_date(String str_buy_date)
    {
        this.str_buy_date = str_buy_date;
    }

    public String getStr_sell_date()
    {
        return str_sell_date;
    }

    public void setStr_sell_date(String str_sell_date)
    {
        this.str_sell_date = str_sell_date;
    }


    public String getStr_caryear()
    {
        return str_caryear;
    }

    public void setStr_caryear(String str_caryear)
    {
        this.str_caryear = str_caryear;
    }

    public String getStr_shingledat()
    {
        return str_shingledat;
    }

    public void setStr_shingledat(String str_shingledat)
    {
        this.str_shingledat = str_shingledat;
    }

    public String getStr_createdate() {
        return str_createdate;
    }

    public void setStr_createdate(String str_createdate) {
        this.str_createdate = str_createdate;
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
        if(createdate !=null) this.str_createdate = new SimpleDateFormat("yyyy-MM").format(createdate);

    }

    public String getSerialno()
    {
        return serialno;
    }

    public void setSerialno(String serialno)
    {
        this.serialno = serialno;
    }

    public String getOrlicense()
    {
        return orlicense;
    }

    public void setOrlicense(String orlicense)
    {
        this.orlicense = orlicense;
    }

    public String getLicensestatus()
    {
        return licensestatus;
    }

    public void setLicensestatus(String licensestatus)
    {
        this.licensestatus = licensestatus;
    }

    public String getCarno()
    {
        return carno;
    }

    public void setCarno(String carno)
    {
        this.carno = carno;
    }

    public long getCarBrand()
    {
        return carBrand;
    }

    public void setCarBrand(long carBrand)
    {
        this.carBrand = carBrand;
    }

    public String getCarBrandSC()
    {
        return carBrandSC;
    }

    public void setCarBrandSC(String carBrandSC)
    {
        this.carBrandSC = carBrandSC;
    }

    public String getCarBrandName()
    {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName)
    {
        this.carBrandName = carBrandName;
    }

    public Timestamp getCaryear()
    {
        return caryear;
    }

    public void setCaryear(Timestamp caryear)
    {
        this.caryear = caryear;
        if(caryear !=null) this.str_caryear = new SimpleDateFormat("yyyy-MM").format(caryear);
    }

    public String getCarcolor()
    {
        return carcolor;
    }

    public void setCarcolor(String carcolor)
    {
        this.carcolor = carcolor;
    }

    public Integer getPipeamount()
    {
        return pipeamount;
    }

    public void setPipeamount(Integer pipeamount)
    {
        this.pipeamount = pipeamount;
    }

    public String getMechanicss()
    {
        return mechanicss;
    }

    public void setMechanicss(String mechanicss)
    {
        this.mechanicss = mechanicss;
    }

    public Timestamp getShingledat()
    {
        return shingledat;
    }

    public void setShingledat(Timestamp shingledat)
    {
        this.shingledat = shingledat;
        if(shingledat !=null) this.str_shingledat=new SimpleDateFormat("yyyy-MM-dd").format(shingledat);
    }

    public Timestamp getCanceldate()
    {
        return canceldate;
    }

    public void setCanceldate(Timestamp canceldate)
    {
        this.canceldate = canceldate;
        if(canceldate !=null) this.str_canceldate=new SimpleDateFormat("yyyy-MM-dd").format(canceldate);
    }

    public String getCarlineno()
    {
        return carlineno;
    }

    public void setCarlineno(String carlineno)
    {
        this.carlineno = carlineno;
    }

    public Timestamp getCpdate()
    {
        return cpdate;
    }

    public void setCpdate(Timestamp cpdate)
    {
        this.cpdate = cpdate;
        if(cpdate !=null) this.str_cpdate=new SimpleDateFormat("yyyy-MM-dd").format(cpdate);
    }

    public String getCpstatus()
    {
        return cpstatus;
    }

    public void setCpstatus(String cpstatus)
    {
        this.cpstatus = cpstatus;
    }

    public Integer getPaid_security_deposit()
    {
        return paid_security_deposit;
    }

    public void setPaid_security_deposit(Integer paid_security_deposit)
    {
        this.paid_security_deposit = paid_security_deposit;
    }

    public Integer getPre_security_deposit()
    {
        return pre_security_deposit;
    }

    public void setPre_security_deposit(Integer pre_security_deposit)
    {
        this.pre_security_deposit = pre_security_deposit;
    }

    public Timestamp getCheck_date()
    {
        return check_date;
    }

    public void setCheck_date(Timestamp check_date)
    {
        this.check_date = check_date;
    }

    public Timestamp getOkcar()
    {
        return okcar;
    }

    public void setOkcar(Timestamp okcar)
    {
        this.okcar = okcar;
        if(okcar !=null) this.str_okcar=new SimpleDateFormat("yyyy-MM-dd").format(okcar);
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public long getCompanyseq()
    {
        return companyseq;
    }

    public void setCompanyseq(long companyseq)
    {
        this.companyseq = companyseq;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerSC()
    {
        return ownerSC;
    }

    public void setOwnerSC(String ownerSC)
    {
        this.ownerSC = ownerSC;
    }

    public String getOwnerCompanySC() {
        return ownerCompanySC;
    }

    public void setOwnerCompanySC(String ownerCompanySC) {
        this.ownerCompanySC = ownerCompanySC;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

    public long getAcco()
    {
        return acco;
    }

    public void setAcco(long acco)
    {
        this.acco = acco;
    }

    public String getAccoName()
    {
        return accoName;
    }

    public void setAccoName(String accoName)
    {
        this.accoName = accoName;
    }

    public String getAccoSC()
    {
        return accoSC;
    }

    public void setAccoSC(String accoSC)
    {
        this.accoSC = accoSC;
    }

    public Timestamp getAccdate()
    {
        return accdate;
    }

    public void setAccdate(Timestamp accdate)
    {
        this.accdate = accdate;
        if(accdate !=null) this.str_accdate=new SimpleDateFormat("yyyy-MM-dd").format(accdate);
    }

    public Integer getAcc_amount()
    {
        return acc_amount;
    }

    public void setAcc_amount(Integer acc_amount)
    {
        this.acc_amount = acc_amount;
    }

    public Integer getAcccount()
    {
        return acccount;
    }

    public void setAcccount(Integer acccount)
    {
        this.acccount = acccount;
    }

    public Integer getPayamount()
    {
        return payamount;
    }

    public void setPayamount(Integer payamount)
    {
        this.payamount = payamount;
    }

    public Integer getTotalamount()
    {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount)
    {
        this.totalamount = totalamount;
    }

    public Timestamp getEnddate()
    {
        return enddate;
    }

    public void setEnddate(Timestamp enddate)
    {
        this.enddate = enddate;
        if(enddate !=null) this.str_enddate=new SimpleDateFormat("yyyy-MM-dd").format(enddate);
    }

    public Integer getStopamount()
    {
        return stopamount;
    }

    public void setStopamount(Integer stopamount)
    {
        this.stopamount = stopamount;
    }

    public long getServ()
    {
        return serv;
    }

    public void setServ(long serv)
    {
        this.serv = serv;
    }

    public String getServName()
    {
        return servName;
    }

    public void setServName(String servName)
    {
        this.servName = servName;
    }

    public String getServSC()
    {
        return servSC;
    }

    public void setServSC(String servSC)
    {
        this.servSC = servSC;
    }

    public Timestamp getServicedate()
    {
        return servicedate;
    }

    public void setServicedate(Timestamp servicedate)
    {
        this.servicedate = servicedate;
    }

    public Timestamp getStopservice()
    {
        return stopservice;
    }

    public void setStopservice(Timestamp stopservice)
    {
        this.stopservice = stopservice;
        if(stopservice !=null) this.str_stopservice=new SimpleDateFormat("yyyy-MM-dd").format(stopservice);
    }

    public String getServicetype()
    {
        return servicetype;
    }

    public void setServicetype(String servicetype)
    {
        this.servicetype = servicetype;
    }

    public Integer getMonthamount()
    {
        return monthamount;
    }

    public void setMonthamount(Integer monthamount)
    {
        this.monthamount = monthamount;
    }

    public long getAny_serv()
    {
        return any_serv;
    }

    public void setAny_serv(long any_serv)
    {
        this.any_serv = any_serv;
    }

    public String getAny_servName()
    {
        return any_servName;
    }

    public void setAny_servName(String any_servName)
    {
        this.any_servName = any_servName;
    }

    public String getAny_servSC()
    {
        return any_servSC;
    }

    public void setAny_servSC(String any_servSC)
    {
        this.any_servSC = any_servSC;
    }

    public String getAny_serv_no()
    {
        return any_serv_no;
    }

    public void setAny_serv_no(String any_serv_no)
    {
        this.any_serv_no = any_serv_no;
    }

    public Timestamp getAny_serv_start_date()
    {
        return any_serv_start_date;
    }

    public void setAny_serv_start_date(Timestamp any_serv_start_date)
    {
        this.any_serv_start_date = any_serv_start_date;
        if(any_serv_start_date !=null) this.str_any_serv_start_date=new SimpleDateFormat("yyyy-MM-dd").format(any_serv_start_date);
    }

    public Integer getAny_serv_amount()
    {
        return any_serv_amount;
    }

    public void setAny_serv_amount(Integer any_serv_amount)
    {
        this.any_serv_amount = any_serv_amount;
    }

    public Timestamp getAny_serv_end_date()
    {
        return any_serv_end_date;
    }

    public void setAny_serv_end_date(Timestamp any_serv_end_date)
    {
        this.any_serv_end_date = any_serv_end_date;
        if(any_serv_end_date !=null) this.str_any_serv_end_date=new SimpleDateFormat("yyyy-MM-dd").format(any_serv_end_date);
    }

    public long getForce_serv()
    {
        return force_serv;
    }

    public void setForce_serv(long force_serv)
    {
        this.force_serv = force_serv;
    }

    public String getForce_servName()
    {
        return force_servName;
    }

    public void setForce_servName(String force_servName)
    {
        this.force_servName = force_servName;
    }

    public String getForce_servSC()
    {
        return force_servSC;
    }

    public void setForce_servSC(String force_servSC)
    {
        this.force_servSC = force_servSC;
    }

    public String getForce_serv_no()
    {
        return force_serv_no;
    }

    public void setForce_serv_no(String force_serv_no)
    {
        this.force_serv_no = force_serv_no;
    }

    public Timestamp getForce_serv_start_date()
    {
        return force_serv_start_date;
    }

    public void setForce_serv_start_date(Timestamp force_serv_start_date)
    {
        this.force_serv_start_date = force_serv_start_date;
        if(force_serv_start_date !=null) this.str_force_serv_start_date=new SimpleDateFormat("yyyy-MM-dd").format(force_serv_start_date);
    }

    public Integer getForce_serv_amount()
    {
        return force_serv_amount;
    }

    public void setForce_serv_amount(Integer force_serv_amount)
    {
        this.force_serv_amount = force_serv_amount;
    }

    public Timestamp getForce_serv_end_date()
    {
        return force_serv_end_date;
    }

    public void setForce_serv_end_date(Timestamp force_serv_end_date)
    {
        this.force_serv_end_date = force_serv_end_date;
        if(force_serv_end_date !=null) this.str_force_serv_end_date=new SimpleDateFormat("yyyy-MM-dd").format(force_serv_end_date);
    }

    public long getUnion_serv()
    {
        return union_serv;
    }

    public void setUnion_serv(long union_serv)
    {
        this.union_serv = union_serv;
    }

    public String getUnion_servName()
    {
        return union_servName;
    }

    public void setUnion_servName(String union_servName)
    {
        this.union_servName = union_servName;
    }

    public String getUnion_servSC()
    {
        return union_servSC;
    }

    public void setUnion_servSC(String union_servSC)
    {
        this.union_servSC = union_servSC;
    }

    public Timestamp getUnion_serv_start_date()
    {
        return union_serv_start_date;
    }

    public void setUnion_serv_start_date(Timestamp union_serv_start_date)
    {
        this.union_serv_start_date = union_serv_start_date;
        if(union_serv_start_date !=null) this.str_union_serv_start_date=new SimpleDateFormat("yyyy-MM-dd").format(union_serv_start_date);
    }

    public Timestamp getUnion_serv_end_date()
    {
        return union_serv_end_date;
    }

    public void setUnion_serv_end_date(Timestamp union_serv_end_date)
    {
        this.union_serv_end_date = union_serv_end_date;
        if(union_serv_end_date !=null) this.str_union_serv_end_date=new SimpleDateFormat("yyyy-MM-dd").format(union_serv_end_date);
    }

    public Integer getUnion_serv_amount()
    {
        return union_serv_amount;
    }

    public void setUnion_serv_amount(Integer union_serv_amount)
    {
        this.union_serv_amount = union_serv_amount;
    }

    public Integer getBuy_amount()
    {
        return buy_amount;
    }

    public void setBuy_amount(Integer buy_amount)
    {
        this.buy_amount = buy_amount;
    }

    public Timestamp getBuy_date()
    {
        return buy_date;
    }

    public void setBuy_date(Timestamp buy_date)
    {
        this.buy_date = buy_date;
        if(buy_date !=null) this.str_buy_date=new SimpleDateFormat("yyyy-MM-dd").format(buy_date);
    }

    public Integer getSell_amount()
    {
        return sell_amount;
    }

    public void setSell_amount(Integer sell_amount)
    {
        this.sell_amount = sell_amount;
    }

    public Timestamp getSell_date()
    {
        return sell_date;
    }

    public void setSell_date(Timestamp sell_date)
    {
        this.sell_date = sell_date;
        if(sell_date !=null) this.str_sell_date=new SimpleDateFormat("yyyy-MM-dd").format(sell_date);
    }

    public String getDriverseq()
    {
        return driverseq;
    }

    public void setDriverseq(String driverseq)
    {
        this.driverseq = driverseq;
    }

    public String getDriverName()
    {
        return driverName;
    }

    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
    }

    public String getDriverSC()
    {
        return driverSC;
    }

    public void setDriverSC(String driverSC)
    {
        this.driverSC = driverSC;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public Timestamp getDriverBirthday() {
        return driverBirthday;
    }

    public void setDriverBirthday(Timestamp driverBirthday) {
        this.driverBirthday = driverBirthday;
        if(driverBirthday !=null) this.str_driverBirthday=new SimpleDateFormat("yyyy-MM-dd").format(driverBirthday);
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "carseq=" + carseq +
                ", createdate=" + createdate +
                ", serialno='" + serialno + '\'' +
                ", orlicense='" + orlicense + '\'' +
                ", licensestatus='" + licensestatus + '\'' +
                ", carno='" + carno + '\'' +
                ", driverseq='" + driverseq + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverSC='" + driverSC + '\'' +
                ", driverID='" + driverID + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", driverBirthday=" + driverBirthday +
                ", str_driverBirthday='" + str_driverBirthday + '\'' +
                ", carBrand=" + carBrand +
                ", carBrandSC='" + carBrandSC + '\'' +
                ", carBrandName='" + carBrandName + '\'' +
                ", caryear=" + caryear +
                ", str_caryear='" + str_caryear + '\'' +
                ", carcolor='" + carcolor + '\'' +
                ", pipeamount=" + pipeamount +
                ", mechanicss='" + mechanicss + '\'' +
                ", shingledat=" + shingledat +
                ", str_shingledat='" + str_shingledat + '\'' +
                ", canceldate=" + canceldate +
                ", str_canceldate='" + str_canceldate + '\'' +
                ", carlineno='" + carlineno + '\'' +
                ", cpdate=" + cpdate +
                ", str_cpdate='" + str_cpdate + '\'' +
                ", cpstatus='" + cpstatus + '\'' +
                ", paid_security_deposit=" + paid_security_deposit +
                ", pre_security_deposit=" + pre_security_deposit +
                ", check_date=" + check_date +
                ", str_check_date='" + str_check_date + '\'' +
                ", okcar=" + okcar +
                ", str_okcar='" + str_okcar + '\'' +
                ", remark='" + remark + '\'' +
                ", companyseq=" + companyseq +
                ", ownerName='" + ownerName + '\'' +
                ", ownerSC='" + ownerSC + '\'' +
                ", ownerCompanyName='" + ownerCompanyName + '\'' +
                ", ownerCompanySC='" + ownerCompanySC + '\'' +
                ", acco=" + acco +
                ", accoName='" + accoName + '\'' +
                ", accoSC='" + accoSC + '\'' +
                ", accdate=" + accdate +
                ", str_accdate='" + str_accdate + '\'' +
                ", acc_amount=" + acc_amount +
                ", acccount=" + acccount +
                ", payamount=" + payamount +
                ", totalamount=" + totalamount +
                ", enddate=" + enddate +
                ", str_enddate='" + str_enddate + '\'' +
                ", stopamount=" + stopamount +
                ", serv=" + serv +
                ", servName='" + servName + '\'' +
                ", servSC='" + servSC + '\'' +
                ", servicedate=" + servicedate +
                ", str_servicedate='" + str_servicedate + '\'' +
                ", stopservice=" + stopservice +
                ", str_stopservice='" + str_stopservice + '\'' +
                ", servicetype='" + servicetype + '\'' +
                ", monthamount=" + monthamount +
                ", any_serv=" + any_serv +
                ", any_servName='" + any_servName + '\'' +
                ", any_servSC='" + any_servSC + '\'' +
                ", any_serv_no='" + any_serv_no + '\'' +
                ", any_serv_start_date=" + any_serv_start_date +
                ", str_any_serv_start_date='" + str_any_serv_start_date + '\'' +
                ", any_serv_amount=" + any_serv_amount +
                ", any_serv_end_date=" + any_serv_end_date +
                ", str_any_serv_end_date='" + str_any_serv_end_date + '\'' +
                ", force_serv=" + force_serv +
                ", force_servName='" + force_servName + '\'' +
                ", force_servSC='" + force_servSC + '\'' +
                ", force_serv_no='" + force_serv_no + '\'' +
                ", force_serv_start_date=" + force_serv_start_date +
                ", str_force_serv_start_date='" + str_force_serv_start_date + '\'' +
                ", force_serv_amount=" + force_serv_amount +
                ", force_serv_end_date=" + force_serv_end_date +
                ", str_force_serv_end_date='" + str_force_serv_end_date + '\'' +
                ", union_serv=" + union_serv +
                ", union_servName='" + union_servName + '\'' +
                ", union_servSC='" + union_servSC + '\'' +
                ", union_serv_start_date=" + union_serv_start_date +
                ", str_union_serv_start_date='" + str_union_serv_start_date + '\'' +
                ", union_serv_end_date=" + union_serv_end_date +
                ", str_union_serv_end_date='" + str_union_serv_end_date + '\'' +
                ", union_serv_amount=" + union_serv_amount +
                ", buy_amount=" + buy_amount +
                ", buy_date=" + buy_date +
                ", str_buy_date='" + str_buy_date + '\'' +
                ", sell_amount=" + sell_amount +
                ", sell_date=" + sell_date +
                ", str_sell_date='" + str_sell_date + '\'' +
                '}';
    }
}
