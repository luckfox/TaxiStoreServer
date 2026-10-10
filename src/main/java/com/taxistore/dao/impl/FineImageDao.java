package com.taxistore.dao.impl;


import com.taxistore.pojo.FineImage;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/*
 * FineImageDao 提供針對 fineimage 表的資料操作方法。
 * 繼承 AbstractFormDao，享有通用 CRUD 查詢能力。
 */
public class FineImageDao extends  AbstractImageDao<FineImage> {

    private static final String TABLE_NAME = "FineImage";
    private static final String KEY_NAME = "imageId";
    private static final String OWNER_FIELD = "fineNo";
    private static final Map<String, Supplier<Object>> extraFields = Map.of();

    /*
     * 建構子：初始化 FineImageDao，設定表格名稱與主鍵名稱。
     */
    public FineImageDao() {
        super(FineImage.class, TABLE_NAME, KEY_NAME, OWNER_FIELD);
    }


//    @Override
//    protected FineImage createNewInstance() {
//        return new FineImage();
//    }
//
//    @Override
//    protected void setFieldValue(FineImage instance, String fieldName, Object value) {
//        switch (fieldName) {
//            case "fineNo" -> instance.setFineNo((String) value);
//            case "hashCode" -> instance.setHashCode((String) value);
//        }
//    }
//    @Override
//    protected String getTableName() {
//        return TABLE_NAME;
//    }
    /**
     * 根據 fineNo 查詢所有圖片 metadata。
     */
    public Result<FineImage> getImageMetasByImageId(FineImage fineImage) {
        return getEntity(fineImage.getImageId());
    }

    /**
     * 根據 fineNo 與 hashCode 查詢圖片 metadata。
     */
    public Result<FineImage> getImageMetasByFineNoAndHashCode(FineImage fineImage) {
        return getImageByOwnerAndHash(fineImage);
    }

    /**
     * 取得某筆 fineNo 的所有圖片 metadata。
     */
    public Result<List<FineImage>> getImagesMetasByFineNo(String fineNo) {
        return getImagesByOwner(fineNo);
    }

    /**
     * 根據 fineNo 取得所有 hashCode。
     */
    public Result<List<String>> getHashCodesByFineNo(String fineNo) {
        return getHashCodesByOwner(fineNo);
    }
    public static void main(String[] args) {
        FineImageDao dao=new FineImageDao();
        Result<FineImage> result=dao.getEntity(1L);
        Result<List<FineImage>> result1=dao.getEntities();
        Log.normal("12345");
    }
}
