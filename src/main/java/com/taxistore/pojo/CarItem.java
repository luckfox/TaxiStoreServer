package com.taxistore.pojo;

import java.util.Date;

/**
 * Created by luckf on 2022/8/12.
 * 車輛記錄表
 */
public class CarItem
{
    private Integer id;      //登錄序號
    private String licence;     //原始牌照號碼
    private Integer band_id;    //廠牌
    private Integer model_id;   //型號
    private Date prod_date;     //出廠日
    private Date issued_date;   //發照日
    private Date created_date;  //登錄日---此項資料被登錄在系統的日期
    private Integer color;      //顏色
    private String engNo;       //引擎號碼
    private String carNo;       //車身號碼
    private String thumbPath;   //圖示連結
    private Integer status;     //目前狀態

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLicence()
    {
        return licence;
    }

    public void setLicence(String licence)
    {
        this.licence = licence;
    }

    public Integer getBand_id()
    {
        return band_id;
    }

    public void setBand_id(Integer band_id)
    {
        this.band_id = band_id;
    }

    public Integer getModel_id()
    {
        return model_id;
    }

    public void setModel_id(Integer model_id)
    {
        this.model_id = model_id;
    }

    public Date getProd_date()
    {
        return prod_date;
    }

    public void setProd_date(Date prod_date)
    {
        this.prod_date = prod_date;
    }

    public Date getIssued_date()
    {
        return issued_date;
    }

    public void setIssued_date(Date issued_date)
    {
        this.issued_date = issued_date;
    }

    public Date getCreated_date()
    {
        return created_date;
    }

    public void setCreated_date(Date created_date)
    {
        this.created_date = created_date;
    }

    public Integer getColor()
    {
        return color;
    }

    public void setColor(Integer color)
    {
        this.color = color;
    }

    public String getEngNo()
    {
        return engNo;
    }

    public void setEngNo(String engNo)
    {
        this.engNo = engNo;
    }

    public String getCarNo()
    {
        return carNo;
    }

    public void setCarNo(String carNo)
    {
        this.carNo = carNo;
    }

    public String getThumbPath()
    {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath)
    {
        this.thumbPath = thumbPath;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "CarItem{" +
                "id=" + id +
                ", licence='" + licence + '\'' +
                ", band_id=" + band_id +
                ", model_id=" + model_id +
                ", prod_date=" + prod_date +
                ", issued_date=" + issued_date +
                ", created_date=" + created_date +
                ", color=" + color +
                ", engNo='" + engNo + '\'' +
                ", carNo='" + carNo + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", status=" + status +
                '}';
    }
}
