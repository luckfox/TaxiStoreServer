package com.taxistore.servlet;

import com.taxistore.pojo.RecvEntry;
import com.taxistore.pojo.RecvForm;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.RecvEntryService;
import com.taxistore.service.impl.RecvFormService;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/recvformServlet")
public class RecvFormServlet  extends BaseServlet
{
    private RecvFormService recvformService=new RecvFormService();
    private RecvEntryService recvEntryService=new RecvEntryService();

    public void getWelcome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //request.setAttribute("result",situationService.GetLast());
        request.getRequestDispatcher("/welcome.jsp").forward(request,response);
    }

    /*
    根據situationId，查詢表格situation的
     */
    public void ajaxGetRecvFormsBySituationId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long SituationId= WebUtils.stringToLong(request.getParameter("SituationId"));
        Result<List<RecvForm>> r=recvformService.GetRecvFormsBySituationId(SituationId);
        response.getWriter().write(r.toJSON());
    }
    public void ajaxCreateRecvForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RecvForm recvForm=new RecvForm();
        recvForm.setUserName(request.getParameter("userName"));
        recvForm.setUserNo(request.getParameter("userNo"));
        recvForm.setType(request.getParameter("type"));
        recvForm.setSituation(WebUtils.stringToLong(request.getParameter("situationId")));
        recvForm.setRemark(request.getParameter("remark"));
        recvForm.setCreatedate(new Timestamp(System.currentTimeMillis()));

        Result<RecvForm> r=recvformService.CreateRecvForm(recvForm);
        response.getWriter().write(r.toJSON());
    }
    public void ajaxDeleteRecvForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long recvFormId=WebUtils.stringToLong(request.getParameter("recvFormId"));
        Result<Integer> result=recvformService.DeleteRecvForm(recvFormId);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxCreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecvForm recvForm=new RecvForm();
        RecvEntry recvEntry = new RecvEntry();
        recvForm.setUserName(request.getParameter("userName"));
        recvForm.setUserNo(request.getParameter("userNo"));
        recvForm.setType(request.getParameter("type"));
        recvForm.setSituation(WebUtils.stringToLong(request.getParameter("situationId")));
        recvForm.setRemark(request.getParameter("remark"));
        recvForm.setCreatedate(new Timestamp(System.currentTimeMillis()));

        Result<RecvForm> r=recvformService.CreateRecvForm(recvForm);
        if(r.isSuccess() && r.getData()!=null)
        {   recvForm=r.getData();
            recvEntry.setSummary(request.getParameter("summary"));
            recvEntry.setRecvFormId(recvForm.getId());
            recvEntry.setFormId(recvForm.getId());

            recvEntry.setAmount(WebUtils.stringToInt( request.getParameter("amount")));
            Result<RecvEntry> result=recvEntryService.createRecvEntry(recvEntry);
            response.getWriter().write(result.toJSON());
        }
        response.getWriter().write(Result.failure("create RecvEntry fail").toJSON());
    }

}
