package com.taxistore.service.impl;

import com.taxistore.dao.impl.RecvEntryDao;
import com.taxistore.pojo.RecvEntry;
import com.taxistore.dto.RecvEntryDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public class RecvEntryService {
    private final RecvEntryDao recvEntryDao = new RecvEntryDao();

    /**
     * 根據收入單據編號取得所有收入細項
     * @param recvFormId 收入單據編號
     * @return 收入細項列表
     */
    public Result<List<RecvEntryDTO>> GetItemsByRecvFormId(Long recvFormId) {
        return recvEntryDao.findByRecvFormId(recvFormId);
    }


    /**
     * 更新收款明細的摘要與金額
     * @param id 收款明細 ID
     * @param summary 摘要
     * @param amount 金額
     * @return 更新是否成功
     */
    public Result<Integer> updateEntry(Long id, String summary, Integer amount) {
        if (id == null || summary == null || amount == null) {
            return Result.failure("輸入參數有誤");
        }

        return recvEntryDao.updateEntry(id, summary, amount);
    }
    //public Result<Integer>updateField()
    /**
     * 產生一個新的RecvEntry (收款項目)
     * @param formId 收款單據序號
     * @param codeId 會計科目序號
     * @param amount 金額
     * @return 更新是否成功
     */
    public Result<RecvEntry>createRecvEntry(Long formId,Long codeId,String summary,String debitCredit,Integer amount)
    {
        Result<Integer> result=recvEntryDao.getMaxEntryOrder(formId);
        if(result.isSuccess())
        {
            return recvEntryDao.createRecvEntry(formId, codeId, summary, debitCredit,amount, formId, result.getData()+1);
        }
        return  Result.failure("資料庫發生錯誤");
    }

    public Result<RecvEntry>createRecvEntry(RecvEntry recvEntry)
    {
        //取得目前最大的Entry Order
        Result<Integer> result=recvEntryDao.getMaxEntryOrder(recvEntry.getRecvFormId());
        if(result.isSuccess())
        {
            return recvEntryDao.createRecvEntry(recvEntry.getFormId(),recvEntry.getAcc(),recvEntry.getSummary(),recvEntry.getDebitCredit(),recvEntry.getAmount(),recvEntry.getRecvFormId(), result.getData()+1);
        }
        return  Result.failure("資料庫發生錯誤");
    }
    /*
        刪除某個recvFormId下的recvEntry，並且回傳剩餘的recvEntry
     */
    public Result<List<RecvEntryDTO>>deleteRecvEntry(Long recvFormId,Integer OrderId)
    {
        recvEntryDao.deleteRecvEntry(recvFormId,OrderId);
        return recvEntryDao.findByRecvFormId(recvFormId);
    }
    /**
     * 更新收款單的指定字段
     *
     * @param id    收款單 ID
     * @param field 欄位名稱
     * @param value 欄位值
     * @return 更新成功回傳 true，否則回傳 false
     */
    public Result<Integer> updateField(Long recvFormId, String field, String value)
    {
        return recvEntryDao.updateField(recvFormId, field, value);
    }
    public static void main(String[] args)
    {

    }
}
