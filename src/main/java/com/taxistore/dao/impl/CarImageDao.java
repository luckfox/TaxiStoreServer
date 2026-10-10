package com.taxistore.dao.impl;

import com.taxistore.pojo.CarImage;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CarImageDao   extends AbstractImageDao<CarImage>
{
    private static final String TABLE_NAME = "CarImage";
    private static final String KEY_NAME = "imageId";
    private static final String OWNER_FIELD = "carSeq";
    private static final Map<String, Supplier<Object>> extraFields = Map.of();
    /*
     * 建構子：初始化 DriverImageDao，設定表格名稱與主鍵名稱。
     */
    public CarImageDao() {
        super(CarImage.class, TABLE_NAME, KEY_NAME, OWNER_FIELD);
    }
//    @Override
//    protected CarImage createNewInstance() {
//        return new CarImage();
//    }
//
//
//
//    @Override
//    protected void setFieldValue(CarImage instance, String fieldName, Object value) {
//        switch (fieldName) {
//            case "carSeq" -> instance.setCarSeq((Long) value);
//            case "hashCode" -> instance.setHashCode((String) value);
//        }
//    }
//    @Override
//    protected String getTableName() {
//        return TABLE_NAME;
//    }
    /**
     * 根據 driverSeq 查詢所有圖片 metadata。
     */
    public Result<CarImage> getImageMetasByImageId(CarImage carImage)
    {
        return getEntity(carImage.getImageId());
    }

    /**
     * 根據 driverSeq 與 hashCode 查詢圖片 metadata。
     */
    public Result<CarImage> getImageMetasByDriverSeqAndHashCode(CarImage carImage)
    {
        return getImageByOwnerAndHash(carImage);
    }

    /**
     * 取得某位司機的所有圖片 metadata。
     */
    public Result<List<CarImage>> getImagesMetasByDriverSeq(Long carImage)
    {
        return getImagesByOwner(carImage);
    }

    /**
     * 根據 driverSeq 取得所有 hashCode。
     */
    public Result<List<String>> getHashCodesByFineNo(Long carImage)
    {
        return getHashCodesByOwner(carImage);
    }
    public static void main(String[] args) {
        CarImageDao dao=new CarImageDao();
        Result<CarImage> result=dao.getEntity(1L);
        Result<List<CarImage>> result1=dao.getEntities();
        Log.normal("12345");
    }
}
