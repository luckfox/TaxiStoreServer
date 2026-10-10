package com.taxistore.dao.impl;

import com.taxistore.pojo.BaseImage;
import com.taxistore.pojo.Result;
import com.utils.Log;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


/**
 * <h1>AbstractImageDao</h1>
 * 超通用泛型 DAO，任何繼承 <code>BaseImage</code> 的圖片 POJO 都能爽爽用。
 * <p>
 * 使用範例：
 * <pre>
 * public class FineImageDao extends AbstractImageDao<FineImage> {
 *     public FineImageDao() {
 *         super(FineImage.class, "FineImage", "imageId", "fineNo");
 *     }
 * }
 * </pre>
 * </p>
 * <br>
 * 📌 只要把 TableName / KeyName / OwnerField 塞進建構子，其餘 CRUD 自動搞定。
 */
public abstract class AbstractImageDao<T extends BaseImage> extends   AbstractFormDao<T> {

    private final Class<T> clazz;
    private final String tableName;
    private final String keyName;
    private final String ownerField;

    private Map<String, Supplier<Object>> extraFields = Map.of();
    protected AbstractImageDao(Class<T> clazz, String tableName, String keyName, String ownerField)
    {
        super(clazz, tableName,keyName);
        this.clazz = clazz;
        this.tableName = tableName;
        this.keyName = keyName;
        this.ownerField = ownerField;
    }

    /**
     * 設定額外欄位與其提供者（如建立時間、自動欄位）
     * @param extraFields 欄位對應供應器
     */
    protected void setExtraFields(Map<String, Supplier<Object>> extraFields) {
        this.extraFields = extraFields;
    }
    /**
     * 取得額外欄位的值
     */
    @Override
    protected Object getExtraFieldValue(String fieldName) {
        Supplier<Object> supplier = extraFields.get(fieldName);
        return supplier != null ? supplier.get() : null;
    }

    /**
     * 根據 owner (如 fineNo 或 driverSeq) 查詢所屬圖片
     * @param ownerKey 所屬鍵值
     * @return 圖片清單結果包裝
     */
    public Result<List<T>> getImagesByOwner(Object ownerKey) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + ownerField + "=?";
        return queryForList(clazz,sql, ownerKey);
    }

    /**
     * 根據 owner 查詢所有 hashCode 清單
     * @param ownerKey 所屬鍵值
     * @return hashCode 清單包裝
     */
    public Result<List<String>> getHashCodesByOwner(Object ownerKey) {
        String sql = "SELECT hashCode FROM " + tableName + " WHERE " + ownerField + "=?";
        return queryForScalarList( String.class,sql, ownerKey);
    }

    /**
     * 根據 owner 與 hashCode 查詢圖片
     * @param entity 包含 owner 和 hashCode 的圖片物件
     * @return 結果包裝
     */
    public Result<T> getImageByOwnerAndHash(T entity) {
        try {
            Field ownerFld = getFieldRecursive(clazz, ownerField);
            Object ownerKey = ownerFld.get(entity);
            String sql = "SELECT * FROM " + tableName + " WHERE " + ownerField + "=? AND hashCode=?";
            return queryForObject(clazz,sql,ownerKey, entity.getHashCode());
        } catch (Exception e) {
            Log.error(e.getMessage());
            return Result.failure("reflection error");
        }
    }

    /**
     * 往上遞迴找指定欄位，包括父類別欄位
     * @param clazz 要搜尋的類別
     * @param fieldName 欄位名稱
     * @return 找到的欄位物件
     * @throws NoSuchFieldException 如果找不到欄位
     */
    private Field getFieldRecursive(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException("欄位不存在: " + fieldName);
    }

}

