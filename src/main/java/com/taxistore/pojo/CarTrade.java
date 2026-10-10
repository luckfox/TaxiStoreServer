package com.taxistore.pojo;

import java.util.Date;

/**
 * Created by luckf on 2022/8/13.
 *
 *
 */
public class CarTrade
{
    private Integer id;              //登錄序號
    private String licence;             //原始車號
    private String brandname;           //廠牌
    private String modelName;           //型號
    private Date   prod_Date;           //出廠時間
    private Integer kiloMeters;         //里程數
    private String colorName;           //顏色
    private String statusValue;         //狀態

    public String getStatusValue()
    {
        return statusValue;
    }

    public void setStatusValue(String statusValue)
    {
        this.statusValue = statusValue;
    }

    public String getBrandname()
    {
        return brandname;
    }

    public void setBrandname(String brandname)
    {
        this.brandname = brandname;
    }

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getColorName()
    {
        return colorName;
    }

    public void setColorName(String colorName)
    {
        this.colorName = colorName;
    }

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

    public Date getProd_Date()
    {
        return prod_Date;
    }

    public void setProd_Date(Date prod_Date)
    {
        this.prod_Date = prod_Date;
    }

    public Integer getKiloMeters()
    {
        return kiloMeters;
    }

    public void setKiloMeters(Integer kiloMeters)
    {
        this.kiloMeters = kiloMeters;
    }



    @Override
    public String toString()
    {
        return "CarTrade{" +
                "id=" + id +
                ", licence='" + licence + '\'' +
                ", brandname='" + brandname + '\'' +
                ", modelName='" + modelName + '\'' +
                ", prod_Date=" + prod_Date +
                ", kiloMeters=" + kiloMeters +
                ", colorName='" + colorName + '\'' +
                ", statusValue='" + statusValue + '\'' +
                '}';
    }


}
