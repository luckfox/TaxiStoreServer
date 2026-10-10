package com.taxistore.service;

import com.taxistore.pojo.Result;

import java.util.List;

/**
 * 通用資料操作服務介面，提供基本的資料操作方法。
 * 該介面針對特定資料表和鍵值名稱，執行 CRUD（增刪改查）操作。
 *
 * @param <T> 操作的資料類型
 * @author luckf
 * @date
 */
public interface IBasicOperatorService<T> {

    /**
     * 判斷是否存在任何項目。
     *
     * @return 如果存在返回 true，否則返回 false
     */
    Boolean existItem();

    /**
     * 獲取鍵值對應的最後一個項目。
     *
     * @return 最後一個項目，如果不存在則返回 null
     */

    Result<T> getLastItem(String KeyName);

    /**
     * 獲取指定序號之後的下一個項目。
     * @param KeyName
     * @param serialNo 當前項目的序號
     * @return 下一個項目，如果不存在則返回 null
     */
    Result<T> getNextItem(String KeyName,Integer serialNo);

    /**
     * 根據序號獲取指定的項目。
     * @param KeyName
     * @param serialNo 目標項目的序號
     * @return 指定的項目，如果不存在則返回 null
     */
    Result<T> getItem(String KeyName,Integer serialNo);

    /**
     * 根據鍵值獲取指定的項目。
     * @param KeyName
     * @param keyValue 目標項目的鍵值
     * @return 指定的項目，如果不存在則返回 null
     */
    Result<T> getItem(String KeyName,String keyValue);

    /**
     * 獲取指定序號之前的上一個項目。
     * @param KeyName
     * @param serialNo 當前項目的序號
     * @return 上一個項目，如果不存在則返回 null
     */
    Result<T> getPreviousItem(String KeyName,Integer serialNo);

    /**
     * 刪除指定序號的項目，並返回鄰近的項目。
     * 優先返回上一個項目，如果上一個項目不存在則返回下一個項目。
     * @param KeyName
     * @param serialNo 需要刪除的項目的序號
     * @return 鄰近的項目，如果沒有鄰近項目則返回 null
     */
    Result<T> deleteItem(String KeyName,Integer serialNo);

    /**
     * 獲取所有項目。
     *
     * @return 包含所有項目的列表，如果沒有項目則返回空列表
     */
    Result<List<T>> getItems();
}
