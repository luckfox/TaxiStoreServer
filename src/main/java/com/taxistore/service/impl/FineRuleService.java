
package com.taxistore.service.impl;

import com.taxistore.dao.impl.FineRuleDao;
import com.taxistore.pojo.FineRule;
import com.taxistore.pojo.Result;
import com.taxistore.service.IFineRuleService;

import java.util.List;

public class FineRuleService implements IFineRuleService {
    private final FineRuleDao fineRuleDAO = new FineRuleDao();

    @Override
    public Result<List<FineRule>> getAllRules() {
        return fineRuleDAO.getEntities();
    }

    @Override
    public Result<FineRule> getFirstRule() {
        return fineRuleDAO.getFirstEntity();
    }

    @Override
    public Result<FineRule> getLastRule() {
        return fineRuleDAO.getLastEntity();
    }

    @Override
    public Result<FineRule> getPreviousRule(Long id)
    {
        Result<FineRule> result=fineRuleDAO.getPreviousEntity(id);
        if(result.isSuccess() && result.getData()==null)
        {
            //雖然SQL執行成功，但是卻沒有得到值，所以已經是第一筆資料。
            result.setSuccess(false).setErrorMessage("database.query.firstrecord","");
        }
        return result;
    }

    @Override
    public Result<FineRule> getNextRule(Long id)
    {
        Result<FineRule> result=fineRuleDAO.getNextEntity(id);
        if(result.isSuccess() && result.getData()==null)
        {
            //雖然SQL執行成功，但是卻沒有得到值，所以已經是最後一筆資料。
            result.setSuccess(false).setErrorMessage("database.query.lastrecord","");
        }
        return result;
    }

    @Override
    public Result<FineRule> getRuleById(Long id)
    {
        return fineRuleDAO.getEntity(id);
    }

    @Override
    public Result<Long> addRule(FineRule fineRule) {
        return fineRuleDAO.insertEntity(fineRule);
    }

    @Override
    public Result<Integer> updateRule(FineRule fineRule) {
        return fineRuleDAO.updateEntity(fineRule);
    }

    @Override
    public Result<FineRule> deleteRule(Long id)
    {
        Result<FineRule> c_result=new Result<FineRule>();
        Result<Integer> result=fineRuleDAO.deleteEntity(id);

        if(result.isSuccess() && (Integer)result.getData()!=0)
        {
            //表示，成功刪除。
            c_result = fineRuleDAO.getPreviousEntity(id);
            if (c_result.isSuccess() && c_result.getData() != null) return c_result;

            c_result = fineRuleDAO.getNextEntity(id);
            if (c_result.isSuccess() && c_result.getData() != null) return c_result;

        } else
        {
            c_result.setErrorMessage("database.delete.fail","");
        }
        return c_result;
    }
}
