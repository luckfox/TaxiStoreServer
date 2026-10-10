package com.taxistore.servlet;

import com.google.gson.Gson;
import com.taxistore.dto.SituationDTO;
import com.taxistore.dto.VehicleInspDTO;
import com.taxistore.dto.VehicleInspStatusDTO;
import com.taxistore.pojo.*;
import com.taxistore.service.impl.SituationService;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/situationServlet")
public class SituationServlet extends BaseServlet
{
    SituationService situationService = new SituationService();
    public void getWelcome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //request.setAttribute("result",situationService.GetLast());
        request.getRequestDispatcher("/welcome.jsp").forward(request,response);
    }

    public void getLastSituation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Log.debug("");
        try {
            Result<SituationDTO> r=situationService.GetLast();
            request.setAttribute("result",r.getData());
            request.getRequestDispatcher("/pages/layout/form/AccountingOperation.jsp").forward(request,response);
        }catch (Exception e) {
            Log.error("getLastSituation error");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
        }

    }
    public void ajaxGetPreviousSituation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Log.debug("");
        Long serialNo= WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<SituationDTO> r=situationService.GetPreviousItem(serialNo);
        response.getWriter().write(r.toJSON());

    }
    public void ajaxGetNextSituation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Log.debug("");
        Long serialNo= WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<SituationDTO> r=situationService.GetNextItem(serialNo);
        response.getWriter().write(r.toJSON());
    }
    public void ajaxGetSituationBySpecialCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {

        String  key=request.getParameter("key");
        Result<List<SituationDTO>> lc=situationService.GetItemBySpecialCode (key);
        response.getWriter().write(lc.toJSON());
    }
    public void ajaxGetSituationDTOByCarnoLike(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {

        String  key=request.getParameter("key");
        Result<List<SituationDTO>> lc=situationService.GetItemByCarnoLike(key);
        response.getWriter().write(lc.toJSON());
    }
    protected void ajaxGetSituation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Log.debug("ajaxGetSituation@situationServlet");
        Long serialNo=WebUtils.stringToLong(request.getParameter("serialNo"));
        Result<SituationDTO> result=  situationService.GetItem(serialNo);
        response.getWriter().write(result.toJSON());

    }
    public void ajaxGetVehicleInspectionStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        int draw= request.getParameter("draw")!=null?Integer.parseInt(request.getParameter("draw")):0;
        int start = request.getParameter("start")!=null?Integer.parseInt(request.getParameter("start")):0;
        int length = request.getParameter("length")!=null?Integer.parseInt(request.getParameter("length")):10;
        String orderColumn = request.getParameter("orderColumn");
        String orderDir = request.getParameter("orderDir");

        int year=WebUtils.stringToInt(request.getParameter("year"));
        int season=WebUtils.stringToInt(request.getParameter("season"));
        String companySeq=request.getParameter("companySeq");
        Result<DataTableResponse<VehicleInspStatusDTO>> result=situationService.GetVehicleInspectionStatus(draw,start,length,orderDir,orderColumn,year,season,companySeq);
        response.getWriter().write(new Gson().toJson(result.isSuccess() ? result.getData() : Map.of("error", result.getErrorMessage())));
    }
    public void ajaxGetCarsForInspection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        int draw= request.getParameter("draw")!=null?Integer.parseInt(request.getParameter("draw")):0;
        int start = request.getParameter("start")!=null?Integer.parseInt(request.getParameter("start")):0;
        int length = request.getParameter("length")!=null?Integer.parseInt(request.getParameter("length")):0;
        String orderColumn = request.getParameter("orderColumn");
        String orderDir = request.getParameter("orderDir");
        int  queryMonth= WebUtils.stringToInt(request.getParameter("queryMonth"));
        Result<DataTableResponse<VehicleInspDTO>> result=situationService.GetCarsForInspection(draw,start,length,orderColumn,orderDir,queryMonth);
        response.getWriter().write(new Gson().toJson(result.isSuccess() ? result.getData() : Map.of("error", result.getErrorMessage())));

    }
    public void ajaxUpdateSituation(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxUpdateSituation@situationServlet");

        Map<String, Object> responseData = new HashMap<>();
        //將http的request paramter的參數映射到Bean class中
        Situation situation=WebUtils.copyParamToBean(request.getParameterMap(),new Situation());

        responseData.put("success",false);
        if(situation.getId()==0)
        {
            responseData.put("mode", "append");
            Result<Long> result=situationService.insertSituation(situation);
            if(result.isSuccess())
            {
                responseData.put("serialNo", String.valueOf(result.getData()));
                responseData.put("success", true);
            }
        }
        else
        {
            responseData.put("mode", "update");
            Result<Integer> result=situationService.updateSituation(situation);
            if(result.isSuccess())
            {
                responseData.put("success",true);
            }
        }
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(responseData));

    }
}
