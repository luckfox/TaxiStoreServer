package com.taxistore.servlet;


import com.taxistore.pojo.Fine;
import com.taxistore.dto.FineDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.FineService;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fineServlet")
public class FineServlet extends BaseServlet {
    FineService service=new FineService();
    protected void getLastFineDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Log.debug("getLastFineDTO@fineServlet");
        Result<FineDTO> result= service.getLastFineDTO();

        request.setAttribute("fineDTO",result.getData());
        request.getRequestDispatcher("/pages/layout/form/createFineDB.jsp").forward(request,response);
    }
    protected void ajaxGetFineDTOByFineNoLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fineNo=request.getParameter("fineNo");
        Result<List<FineDTO>> result=service.getFineDTOByFineNoLike(fineNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetFineDTOByFineNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fineNo=request.getParameter("fineNo");
        Result<FineDTO> result=service.getFineDTOByFineNo(fineNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetFineDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetFineDTO@fineServlet");
        Integer serialNo= WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<FineDTO> result=service.getFineDTO(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetFirstFineDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetFirstFineDTO@fineServlet");
        Result<FineDTO> result=service.getFirstFineDTO();
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetLastFineDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetFineDTO@fineServlet");
        Result<FineDTO> result=service.getLastFineDTO();
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetPreviousFineDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetPreviousDTO@fineServlet");
        Integer serialNo= WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<FineDTO> result=service.getPreviousFineDTO(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetNextFineDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetNextFineDTO@fineServlet");
        Long serialNo= WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<FineDTO> result=service.getNextFineDTO(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxUpdateFine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Fine fine=new Fine();
        WebUtils.copyParamToBean(request.getParameterMap(),fine);
        Result<Integer> result=service.updateEntityByNonNullFields(fine);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxDeleteFine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Fine fine=new Fine();
        Long serialNo= WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<FineDTO> result=service.deleteFine(serialNo);
        response.getWriter().write(result.toJSON());
    }
}
