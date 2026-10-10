package com.taxistore.dao.impl;

import com.taxistore.dao.IFeeDao;
import com.taxistore.pojo.Fee;
import com.taxistore.pojo.Result;
import java.util.List;

public class FeeDao extends AbstractFormDao<Fee> implements IFeeDao {
    /** 對應的資料表名稱：car */
    private static final String TABLE_NAME = "fee";
    /** 主鍵欄位名稱：carseq */
    private static final String KEY_NAME = "id";

    public FeeDao() {
        super(Fee.class, TABLE_NAME, KEY_NAME);
    }

    /**
     * 測試 FeeDao 功能
     */
    public static void main(String[] args)
    {
        FeeDao feeDao = new FeeDao();
        Fee fee = new Fee();
        fee.setCode("F123");
        fee.setAccountCode("A001");
        fee.setPayForm("Credit");
        fee.setGenForm("Auto");
        fee.setRecvForm("Online");
        fee.setBillByDays("30");
        fee.setNotToIncome("N");
        fee.setNotToAccount("N");
        fee.setSpecial("None");
        Result<Long> result0=feeDao.insertEntity(fee);
        if(!result0.isSuccess()) return;
        Result<Fee>result8=feeDao.getEntity(result0.getData());
        if(!result8.isSuccess()) return;
        Fee fee2=result8.getData();
        fee2.setBillByDays("60");
        Result<Integer> result1=feeDao.updateEntity(fee2);
        Result<List<Fee>> result2=feeDao.getEntities();

        Result<Fee>result3=feeDao.getPreviousEntity(result0.getData());
        feeDao.getNextEntity(result3.getData().getId());
        feeDao.deleteEntity(result0.getData());
    }
}

