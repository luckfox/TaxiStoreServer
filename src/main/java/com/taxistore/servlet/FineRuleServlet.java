package com.taxistore.servlet;

import com.taxistore.pojo.*;
import com.taxistore.service.impl.FeeService;
import com.taxistore.service.impl.FineRuleService;
import com.utils.Convert;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/fineRuleServlet")
public class FineRuleServlet extends BaseServlet
{
    private final FineRuleService fineRuleService = new FineRuleService();



    protected void getLastFineRule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("getLastFineRule@fineRuleServlet");
        Result<FineRule> fineRule=fineRuleService.getLastRule();
        request.setAttribute("fineRule",fineRule.getData());
        request.getRequestDispatcher("/pages/layout/fine/fineRule.jsp").forward(request,response);
    }

    public void ajaxAddFineRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FineRule fineRule = WebUtils.copyParamToBean(request.getParameterMap(), new FineRule());
        Result<Long> result = fineRuleService.addRule(fineRule);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetFineRule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("getFineRule@fineRuleServlet");
        Long serialNo=WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<FineRule> result=fineRuleService.getRuleById(serialNo);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxUpdateFineRule(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 初始化響應 Map
        Map<String, String> responseData = new HashMap<>();
        String queryString = request.getQueryString();

        Log.debug(queryString);
        responseData.put("success", "false");


        FineRule fineRule = WebUtils.copyParamToBean(request.getParameterMap(), new FineRule());

        if (fineRule.getFineRuleSeq() == 0) {
            handleAddFineRule(fineRule, responseData);
        } else {
            handleUpdateFineRule(fineRule, responseData);
        }

        Log.debug("ajaxUpdateFineRule@fineRuleServlet = " + responseData.get("success"));

        // 將結果轉換為 JSON 並返回
        try (PrintWriter out = response.getWriter()) {
            out.print(Convert.convertToJson(responseData));
            out.flush();
        }

    }
    public void ajaxGetFirstRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<FineRule> result=fineRuleService.getFirstRule();
        response.getWriter().write(result.toJSON());
    }
    public void ajaxGetLastRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<FineRule> result=fineRuleService.getLastRule();
        response.getWriter().write(result.toJSON());
    }
    public void ajaxGetNextRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long serialNo = WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<FineRule> result=fineRuleService.getNextRule(serialNo);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxGetPreviousRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long serialNo = WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<FineRule> result=fineRuleService.getPreviousRule(serialNo);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxDeleteFineRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long serialNo = WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<FineRule> result = fineRuleService.deleteRule(serialNo);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxListFineRules(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<List<FineRule>> result = fineRuleService.getAllRules();
        response.getWriter().write(result.toJSON());
    }

    // 新增公司邏輯
    private void handleAddFineRule(FineRule fineRule, Map<String, String> responseData) {
        responseData.put("mode", "append");
        Result<Long> result = fineRuleService.addRule(fineRule);

        if (result.isSuccess()) {
            responseData.put("serialNo", String.valueOf(result.getData()));
            responseData.put("success", "true");
        }
    }

    // 更新公司邏輯
    private void handleUpdateFineRule(FineRule fineRule, Map<String, String> responseData) {
        responseData.put("mode", "update");
        Result<Integer> result = fineRuleService.updateRule(fineRule);

        if (result.isSuccess()) {
            responseData.put("success", "true");
        }
    }
}
