package com.taxistore.servlet;

import com.taxistore.pojo.Driver;
import com.taxistore.dto.DriverDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.DriverService;
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


/**
 * Created by luckf on 2022/9/29.
 */
@WebServlet(name = "driverServlet",urlPatterns = "/driverServlet")
public class DriverServlet extends BaseServlet
{

    DriverService driverService=new DriverService();
    protected void getLastDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Log.debug("getLastDriver@driverServlet");
        Result<Driver> result= driverService.getLastDriver();

        request.setAttribute("driver",result.getData());
        request.getRequestDispatcher("/pages/layout/form/createDriverDB.jsp").forward(request,response);

    }
    protected void getLastDriverDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Log.debug("getLastDriverDTO@driverServlet");
        Result<DriverDTO> result= driverService.getLastDriverDTO();

        request.setAttribute("driverDTO",result.getData());
        request.getRequestDispatcher("/pages/layout/form/createDriverDB.jsp").forward(request,response);

    }

    protected void ajaxGetDriversBySpecialCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /* 如果以"driverSC"為名的Key找不到，則改以"key"當作key值*/
        Log.debug("ajaxGetDriversBySpecialCode@driverServlet");
        String driverSC = request.getParameter("driverSC") != null
                ? request.getParameter("driverSC")
                : request.getParameter("key");

        Integer start=WebUtils.stringToInt(request.getParameter("start"));
        Integer length=WebUtils.stringToInt(request.getParameter("length"));
        Result<List<DriverDTO>> result=driverService.getDriversBySpecialCode(driverSC,start==-1?0:start,length==-1?0:length);
        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetDriversByDriverId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetDriversByDriverId@driverServlet");
        /* 如果以"driverId"為名的Key找不到，則改以"key"當作key值*/
        String driverId = request.getParameter("driverId") != null
                ? request.getParameter("driverId")
                : request.getParameter("key");

        Integer start=WebUtils.stringToInt(request.getParameter("start"));
        Integer length=WebUtils.stringToInt(request.getParameter("length"));
        Result<List<DriverDTO>> result=driverService.getDriversByDriverId(driverId,start==-1?0:start,length==-1?0:length);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetPreviousDriverDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetPreviousDriver@driverServlet");
        Integer driverseq= WebUtils.stringToInt(request.getParameter("serialNo"));

        Result<DriverDTO> result=driverService.getPreviousDriverDTO(driverseq);
        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetNextDriverDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetNextDriver@driverServlet");

        Integer driverseq=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<DriverDTO> result=driverService.getNextDriverDTO(driverseq);

        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetFirstDriverDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetFirstDriver@driverServlet");
        Result<DriverDTO> result=driverService.getFirstDriverDTO();
        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetLastDriverDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetLastDriver@driverServlet");
        Result<DriverDTO> result=driverService.getLastDriverDTO();
        response.getWriter().write(result.toJSON());

    }
    protected void ajaxGetDriverDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetDriver@driverServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<DriverDTO> result=driverService.getDriverDTO(serialNo);
        response.getWriter().write(result.toJSON());

    }

    protected void ajaxDeleteDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxDeleteDriver@driverServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<Driver> result=driverService.deleteDriver(serialNo);
        response.getWriter().write(result.toJSON());

    }
    public void ajaxInsertDriver(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxUpdateDriver@driverServlet");

        Map<String, Object> responseData = new HashMap<>();
        DriverDTO driverDTO=WebUtils.copyParamToBean(request.getParameterMap(),new DriverDTO());
        Log.debug(driverDTO.toString());
        Result<Long>result=driverService.insertDriver(driverDTO);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxUpdateDriver(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxUpdateDriver@driverServlet");

        Map<String, Object> responseData = new HashMap<>();
        DriverDTO driverDTO=WebUtils.copyParamToBean(request.getParameterMap(),new DriverDTO());
        Log.debug(driverDTO.toString());

        Result<Integer>result=driverService.updateDriver(driverDTO);

        response.getWriter().write(result.toJSON());

    }

}
