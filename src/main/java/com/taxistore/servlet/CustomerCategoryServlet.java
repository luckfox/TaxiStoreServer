package com.taxistore.servlet;

import com.google.gson.Gson;
import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;

import com.taxistore.service.impl.CustomerCategoryTService;
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
import java.util.Map;

/**
 * @author luckf
 */
@WebServlet(name = "CustomerCategoryServlet",urlPatterns = "/customerCategoryServlet")
public class CustomerCategoryServlet  extends BaseServlet
{

    CustomerCategoryTService customerCategoryTService= new CustomerCategoryTService();
    protected void ajaxGetItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> responseData = new HashMap<>();

        System.out.println("ajaxGetItem@CustomerCategoryServlet");
        Integer serialNo= WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CustomerCategory> c=customerCategoryTService.getCustomerCategory(serialNo);

        Gson gson=new Gson();
        String json=gson.toJson(c);
        response.getWriter().write(json);
    }
    protected void ajaxUpdateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 初始化響應 Map
        Map<String, String> responseData = new HashMap<>();
        responseData.put("success", "false");

        // 將請求參數映射到 CustomerCategory 對象
        CustomerCategory cc = new CustomerCategory();
        WebUtils.copyParamToBean(request.getParameterMap(), cc);

        // 處理新增或更新邏輯
        if (cc.getCustCateID() == 0) {
            handleAddCustomerCategory(cc, responseData);
        } else {
            handleUpdateCustomerCategory(cc, responseData);
        }

        // 記錄日誌
        Log.debug("ajaxUpdateItem@CustomerCategoryServlet = " + responseData.get("success"));

        // 將結果轉換為 JSON 並返回
        try (PrintWriter out = response.getWriter()) {
            out.print(Convert.convertToJson(responseData));
            out.flush();
        }

    }
    // 新增公司邏輯
    private void handleAddCustomerCategory(CustomerCategory cc, Map<String, String> responseData) {
        responseData.put("mode", "append");
        Result<Long> result = customerCategoryTService.addCustomerCategory(cc);

        if (result.isSuccess()) {
            responseData.put("serialNo", String.valueOf(result.getData()));
            responseData.put("success", "true");
        }
    }

    // 更新公司邏輯
    private void handleUpdateCustomerCategory(CustomerCategory cc, Map<String, String> responseData) {
        responseData.put("mode", "update");
        Result<Integer> result = customerCategoryTService.updateCustomerCategory(cc);

        if (result.isSuccess()) {
            responseData.put("success", "true");
        }
    }
}
