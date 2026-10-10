package com.taxistore.servlet;

import com.taxistore.pojo.Fee;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.FeeService;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/feeServlet")
public class FeeServlet extends BaseServlet {
    private final FeeService feeService = new FeeService();

    public void ajaxAddFee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Fee fee = WebUtils.copyParamToBean(request.getParameterMap(), new Fee());
        Result<Long> result = feeService.addFee(fee);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxUpdateFee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Fee fee = WebUtils.copyParamToBean(request.getParameterMap(), new Fee());
        Result<Integer> result = feeService.updateFee(fee);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxDeleteFee(HttpServletRequest request, HttpServletResponse response) throws IOException {
       long id = WebUtils.stringToInt(request.getParameter("id"));
       Result<Integer> result = feeService.deleteFee(id);
       response.getWriter().write(result.toJSON());
    }

    public void ajaxListFees(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<List<Fee>> result = feeService.getAllFees();
        response.getWriter().write(result.toJSON());
    }
}
