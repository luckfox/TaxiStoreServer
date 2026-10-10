package com.taxistore.servlet;

import com.google.gson.Gson;
import com.taxistore.pojo.Company;
import com.taxistore.dto.CompanyDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.CompanyTService;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "CompanyServlet",urlPatterns = "/companyServlet")
public class CompanyServlet extends BaseServlet
{

    CompanyTService companyTService= new CompanyTService();
    protected void createCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("createCompany@companyServlet");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Company c=new Company();
        WebUtils.copyParamToBean(request.getParameterMap(),c);

    }
    /**
     * 接受來自於
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getLastCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("getLastCompany@companyServlet");
        Result<CompanyDTO> c=companyTService.getLastCompany();
        request.setAttribute("company",c.getData());
        request.getRequestDispatcher("/pages/layout/form/createCompanyDB.jsp").forward(request,response);

    }
    protected void getFirstCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("getFirstCompany@companyServlet");
        Result<CompanyDTO> c=companyTService.getFirstCompany();
        request.setAttribute("company",c.getData());
        request.getRequestDispatcher("/pages/layout/form/createCompanyDB.jsp").forward(request,response);

    }
    protected void ajaxGetLastCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("ajaxGetLastCompany@companyServlet");
        Result<CompanyDTO>result=companyTService.getLastCompany();
        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetFirstCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("ajaxGetFirstCompany@companyServlet");
        Result<CompanyDTO>result=companyTService.getFirstCompany();
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetNextCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("ajaxGetNextCompany@companyServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CompanyDTO>result=companyTService.getNextCompany(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetPreviousCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("ajaxGetPreviousCompany@companyServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CompanyDTO> result=companyTService.getPreviousCompany(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, String> responseData = new HashMap<>();

        System.out.println("ajaxGetCompany@companyServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CompanyDTO> result=companyTService.getCompany(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxDeleteCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("ajaxDeleteCompany@companyServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CompanyDTO> result=companyTService.deleteCompany(serialNo);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxGetCompaniesBySpecialCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("ajaxGetCompanyBySpecialCode@companyServlet");
        String  SpecialCode=request.getParameter("SpecialCode");
        Result<List<CompanyDTO>> result=companyTService.getCompaniesBySpecialCode(SpecialCode);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxUpdateCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 初始化響應 Map
        Map<String, Object> responseData = new HashMap<>();
        Company company =WebUtils.copyParamToBean(request.getParameterMap(), new Company());
        Log.debug(company.toString());

        responseData.put("success", false);

        if (company.getCompanyseq() == 0) {
            handleAddCompany(company, responseData);
        } else {
            handleUpdateCompany(company, responseData);
        }
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(responseData));

    }

    // 新增公司邏輯
    private void handleAddCompany(Company company, Map<String, Object> responseData) {
        responseData.put("mode", "append");
        Result<Long> result = companyTService.addCompany(company);

        if (result.isSuccess()) {
            responseData.put("serialNo", String.valueOf(result.getData()));
            responseData.put("success", true);
        }
    }

    // 更新公司邏輯
    private void handleUpdateCompany(Company company, Map<String, Object> responseData) {
        responseData.put("mode", "update");
        Result<Integer> result = companyTService.updateCompany(company);

        if (result.isSuccess()) {
            responseData.put("success", true);
        }
    }
}
