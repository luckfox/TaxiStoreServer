package com.taxistore.service.impl;

import com.taxistore.dao.impl.FeeDao;
import com.taxistore.pojo.Fee;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;

public class FeeService {
    private final FeeDao feeDao = new FeeDao();

    /**
     * 新增費用資訊
     * @param fee 費用實體
     * @return 操作結果
     */
    public Result<Long> addFee(Fee fee) {
        if (fee == null || fee.getCode() == null || fee.getCode().isEmpty()) {
            return Result.failure("fee.add.invalidParameters");
        }
        return feeDao.insertEntity(fee);
    }

    /**
     * 根據 ID 查詢費用
     * @param id 費用 ID
     * @return 費用資訊
     */
    public Result<Fee> getFeeById(long id) {
        if (id <= 0) {
            return Result.failure("fee.query.invalidId");
        }
        return feeDao.getEntity(id);
    }

    /**
     * 查詢所有費用資訊
     * @return 費用列表
     */
    public Result<List<Fee>> getAllFees() {
        Log.debug("getAllFees");
        return feeDao.getEntities();
    }

    /**
     * 更新費用資訊
     *
     * @param fee 費用實體
     * @return 操作結果
     */
    public Result<Integer> updateFee(Fee fee) {
        if (fee == null || fee.getId() <= 0) {
            return Result.failure("fee.update.invalidParameters");
        }
        return feeDao.updateEntity(fee);
    }

    /**
     * 根據 ID 刪除費用資訊
     *
     * @param id 費用 ID
     * @return 操作結果
     */
    public Result<Integer> deleteFee(long id) {
        if (id <= 0) {
            return Result.failure("fee.delete.invalidId");
        }
        return feeDao.deleteEntity(id);
    }

    /**
     * 測試 FeeService 功能
     */
    public static void main(String[] args) {
        FeeService feeService = new FeeService();

        // 測試新增
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
        System.out.println("新增結果: " + feeService.addFee(fee));

        // 測試查詢
        System.out.println("查詢 ID 1: " + feeService.getFeeById(1));

        // 測試更新
        fee.setId(1L);
        fee.setPayForm("Cash");
        System.out.println("更新結果: " + feeService.updateFee(fee));

        // 測試刪除
        System.out.println("刪除 ID 1: " + feeService.deleteFee(1));
    }
}
