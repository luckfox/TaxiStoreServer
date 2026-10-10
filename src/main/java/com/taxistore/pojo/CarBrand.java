package com.taxistore.pojo;

/**
 * Created by luckf on 2022/11/7.
 */
public class CarBrand
{
    private long carBrandId;
    private String code;
    private String name;

    public CarBrand()
    {
    }
    public long getCarBrandId()
    {
        return carBrandId;
    }

    public void setCarBrandId(long carBrandId)
    {
        this.carBrandId = carBrandId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "CarBrand{" +
                "carBrandId=" + carBrandId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
