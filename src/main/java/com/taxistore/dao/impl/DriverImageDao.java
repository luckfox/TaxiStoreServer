package com.taxistore.dao.impl;

import com.taxistore.pojo.DriverImage;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class DriverImageDao extends AbstractImageDao<DriverImage>
{
    private static final String TABLE_NAME = "DriverImage";
    private static final String KEY_NAME = "imageId";
    private static final String OWNER_FIELD = "driverSeq";
    private static final Map<String, Supplier<Object>> extraFields = Map.of();
    /*
     * 建構子：初始化 DriverImageDao，設定表格名稱與主鍵名稱。
     */
    public DriverImageDao() {
        super(DriverImage.class, TABLE_NAME, KEY_NAME, OWNER_FIELD);
    }

    /**
     * 根據 driverSeq 查詢所有圖片 metadata。
     */
    public Result<DriverImage> getImageMetasByImageId(DriverImage driverImage)
    {
        return getEntity(driverImage.getImageId());
    }

    /**
     * 根據 driverSeq 與 hashCode 查詢圖片 metadata。
     */
    public Result<DriverImage> getImageMetasByDriverSeqAndHashCode(DriverImage driverImage)
    {
        return getImageByOwnerAndHash(driverImage);
    }

    /**
     * 取得某位司機的所有圖片 metadata。
     */
    public Result<List<DriverImage>> getImagesMetasByDriverSeq(Long driverSeq)
    {
        return getImagesByOwner(driverSeq);
    }

    /**
     * 根據 driverSeq 取得所有 hashCode。
     */
    public Result<List<String>> getHashCodesByFineNo(Long driverSeq)
    {
        return getHashCodesByOwner(driverSeq);
    }
    public static void main(String[] args) {
        DriverImageDao dao=new DriverImageDao();
        Result<DriverImage> result=dao.getEntity(1L);
        Result<List<DriverImage>> result1=dao.getEntities();
        Log.normal("12345");
    }
}
