
package com.taxistore.service;

import com.taxistore.pojo.FineRule;
import com.taxistore.pojo.Result;

import java.util.List;

public interface IFineRuleService {
    Result<List<FineRule>> getAllRules();
    Result<FineRule> getFirstRule();
    Result<FineRule> getLastRule();
    Result<FineRule> getPreviousRule(Long id);
    Result<FineRule> getNextRule(Long id);
    Result<FineRule> getRuleById(Long id);
    Result<Long> addRule(FineRule fineRule);
    Result<Integer> updateRule(FineRule fineRule);
    Result<FineRule> deleteRule(Long id);
}
