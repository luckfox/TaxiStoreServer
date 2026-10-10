package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;

import com.taxistore.pojo.RecvEntry;
import com.taxistore.dto.RecvEntryDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;
import com.utils.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
CREATE TABLE `recventry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  //收入細項序號
  `FORMID` bigint(20) DEFAULT NULL,         //總帳單據編號
  `acc` bigint(20) DEFAULT NULL,            //會計科目
  `summary` varchar(5000) DEFAULT NULL,     //說明
  `debitcredit` varchar(255) DEFAULT NULL,  //借貸
  `amount` int(11) DEFAULT NULL,            //金額
  `noteno` varchar(255) DEFAULT NULL,       //票據編號
  `notebank` varchar(255) DEFAULT NULL,     //票據銀行
  `notebranch` varchar(255) DEFAULT NULL,   //票據分行
  `notepaydate` datetime DEFAULT NULL,      //兌票日期
  `RecvFormId` bigint(20) DEFAULT NULL,     //收入單據編號
  `entryorder` int(11) DEFAULT NULL,        //細項順序
PRIMARY KEY (`id`),
UNIQUE KEY `noteno_UNIQUE` (`noteno`),
KEY `FKEC8FFE2C4C79536B` (`RecvFormId`),
KEY `FKEC8FFE2CC3490D2B` (`acc`),
KEY `FKEC8FFE2C76917E05` (`FORMID`),
CONSTRAINT `FKEC8FFE2C4C79536B` FOREIGN KEY (`RecvFormId`) REFERENCES `recvform` (`id`),
CONSTRAINT `FKEC8FFE2C76917E05` FOREIGN KEY (`FORMID`) REFERENCES `recvform` (`id`),
CONSTRAINT `FKEC8FFE2CC3490D2B` FOREIGN KEY (`acc`) REFERENCES `fee` (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=168718 DEFAULT CHARSET=utf8

 */
public class RecvEntryDao extends AbstractFormDao<RecvEntry>
{

    private static final String TABLE_NAME = "recventry";
    private static final String KEY_NAME = "id";
    public RecvEntryDao()
    {
        super(RecvEntry.class, TABLE_NAME, KEY_NAME);
    }

    /**
     * 根據 RecvFormId 查詢所有收入細項
     * @param recvFormId 收入單據編號
     * @return 收入細項列表
     */
    public Result<List<RecvEntryDTO>> findByRecvFormId(Long recvFormId) {
        String sql = """
            SELECT r.id,
                   r.formId,
                   f.code as code,
                   f.accountCode as accountCode,
                   r.summary,
                   r.debitCredit,
                   r.amount,
                   r.recvFormId,
                   r.entryOrder
            FROM recvEntry r
            LEFT JOIN fee f ON f.id=acc
            WHERE RecvFormId = ? 
            ORDER BY entryorder ASC""";
        List<RecvEntryDTO> details = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, recvFormId);
            ResultSet rs = pstmt.executeQuery();

            List<RecvEntryDTO> recvEntryDTOs = new ResultSetMapper<RecvEntryDTO>().mapResultSetToList(rs, RecvEntryDTO.class);
            return Result.success(recvEntryDTOs);
        } catch (Exception e) {
            return Result.failure("資料庫查詢發生錯誤：" + e.getMessage());
        }
    }
    /*
     * 更新收款明細的摘要與金額
     * @param id 收款明細 ID
     * @param summary 摘要
     * @param amount 金額
     * @return 更新是否成功
     */
    public Result<Integer> updateEntry(Long id, String summary, int amount)
    {
        List<String> list = List.of("summary", "amount");
        RecvEntry recvEntry=new RecvEntry();
        recvEntry.setId(id);
        recvEntry.setAmount(amount);
        recvEntry.setSummary(summary);
        Result<Integer>result=this.updateEntityOnlyFields(recvEntry,list);
        return result;
    }

    /*
     * 更新收款單的特定欄位值
     *
     * @param id    收款單 ID
     * @param field 欄位名稱
     * @param value 欄位值
     * @return 更新成功回傳 true，否則回傳 false
     */
    public Result<Integer> updateField(Long recvFormId, String field, String value) {
        Integer iRet;
        String sql =
                "UPDATE recvform SET " + field + " = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, value);
            pstmt.setLong(2, recvFormId);
            iRet=pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("資料庫更新發生錯誤：" + e.getMessage());
        }
        return Result.success(iRet);
    }
    /* 刪除表格recvEntry中某個fromId下的
    */
    public Result<Integer> deleteRecvEntry(Long formId,Integer entryOrder)
    {
        List<String> list = List.of("formId", "entryOrder");
        RecvEntry recvEntry=new RecvEntry();
        recvEntry.setFormId(formId);
        recvEntry.setEntryOrder(entryOrder);
        Result<Integer>result=this.deleteEntitiesByFields(recvEntry,list);
        return result;
    }


    /*
     * 插入新的 RecvEntry 記錄
     * @param formId        單據 ID
     * @param codeId        會計科目ID
     * @param debitCredit   借貸方
     * @param summary       摘要
     * @param amount        金額
     * @param recvFormId    收款單據 ID
     * @param entryOrder entryOrder
     * @return 新產生的RecvEntry
     */
    public Result<RecvEntry> createRecvEntry(Long formId, Long codeId, String summary,String debitCredit, Integer amount, Long recvFormId, Integer entryOrder)
    {
        RecvEntry recvEntry = new RecvEntry();
        recvEntry.setFormId(formId);
        recvEntry.setAcc(codeId);
        recvEntry.setSummary(summary);
        recvEntry.setDebitCredit(debitCredit);
        recvEntry.setAmount(amount);
        recvEntry.setRecvFormId(recvFormId);
        recvEntry.setEntryOrder(entryOrder);

        Result<Long> result=insertEntity(recvEntry);
        if(!result.isSuccess()) return Result.failure("create RecvEntry fail");
        recvEntry.setId(result.getData());
        return Result.success(recvEntry) ;

    }

    public Result<Integer> getMaxEntryOrder(Long recvFormId)
    {
        RecvEntry recvEntry=new RecvEntry();
        recvEntry.setRecvFormId(recvFormId);
        return this.getMaxValueByField(recvEntry,"entryOrder","recvFormId");

    }


    public static void main(String[] args)
    {
        RecvEntryDao dao = new RecvEntryDao();
        Result<List<RecvEntryDTO>> result = dao.findByRecvFormId(3590L);

        if (result.isSuccess())
        {
            for (RecvEntryDTO detail : result.getData())
            {
                System.out.println("收入細項: " + detail.getSummary() + ", 金額: " + detail.getAmount());
            }
        } else
        {
            System.out.println("錯誤: " + result.getErrorMessage());
        }

        Result<Integer> result2=dao.getMaxEntryOrder(133224L);
        if (result2.isSuccess())
        {
            Integer MaxOrder=result2.getData();
            Result<RecvEntry> result1=dao.createRecvEntry(133224L,40L,"車租","C",15000,133224L,MaxOrder+1);
            Log.normal("result="+result1.toJSON());
            Result<RecvEntry> result9=dao.getEntity(result1.getData().getId());

            RecvEntry recvEntry=result9.getData();
            recvEntry.setId(result1.getData().getId());
            recvEntry.setSummary("hello5");
            recvEntry.setAmount(5000);
            Result<Integer> result6=dao.updateEntityOnlyFields(recvEntry,List.of("summary","amount"));
            Result<Integer> result4=dao.updateEntry(result1.getData().getId(),"hello",3000);

            recvEntry.setSummary("hello8");
            Result<Integer> result8=dao.updateEntityOnlyFieldsByColumn(recvEntry,List.of("summary","amount"),"recvFormId");
            Result<Integer>result5=dao.getMaxEntryOrder(result1.getData().getId());
            Result<Integer> result3=dao.deleteRecvEntry(result1.getData().getFormId(),result1.getData().getEntryOrder());
            Log.normal("result="+result3.toJSON());
        }


    }
}

