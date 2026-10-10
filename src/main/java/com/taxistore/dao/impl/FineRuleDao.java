package com.taxistore.dao.impl;


import com.taxistore.dao.IFineRuleDAO;
import com.taxistore.pojo.FineRule;
import com.taxistore.pojo.Result;

import java.util.Map;
import java.util.function.Supplier;

/**
 * FineRule DAO 實作，繼承通用泛型 DAO 類別
 */
public class FineRuleDao extends AbstractFormDao<FineRule> implements IFineRuleDAO
{
    private static final String TABLE_NAME = "Fine_Rule";
    private static final String KEY_NAME = "fineRuleSeq";
    private static final Map<String, Supplier<Object>> extraFields = Map.of();
    public FineRuleDao() {
        super(FineRule.class, TABLE_NAME,KEY_NAME);
    }


//==========================================================================================

    public static void main(String[] args) {
        FineRuleDao dao = new FineRuleDao();

        // 新增測試資料
        FineRule fineRule = new FineRule(null, "68-1", "1", "", 14000, "汽車未依規定裝設行車紀錄器");
        Result<Long> result0 = dao.insertEntity(fineRule);
        if (result0.isSuccess()) {
            Long newId = result0.getData();
            // 查詢該筆資料
            Result<FineRule> fetched = dao.getEntity(newId);
            // 修改資料
            FineRule ruleToUpdate = fetched.getData();
            ruleToUpdate.setDescription("【更新後】汽車未依規定裝設行車紀錄器");
            Result<Integer> updateResult = dao.updateEntity(ruleToUpdate);
            System.out.println("✏️ Update 結果: " + updateResult);

            // 再次查詢確認更新
            System.out.println("🔍 更新後查詢: " + dao.getEntity(newId));

            Result<FineRule> PreviousResult=dao.getPreviousEntity(newId);

            Result<FineRule> NextResult=dao.getNextEntity(PreviousResult.getData().getFineRuleSeq());
            // 刪除資料
            Result<Integer> deleteResult = dao.deleteEntity(newId);
            System.out.println("🗑️ Delete 結果: " + deleteResult);

        } else {
            System.out.println("❌ Insert 失敗: " + result0.getErrorMessage());
        }
    }
}
