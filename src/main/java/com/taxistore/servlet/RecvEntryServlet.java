package com.taxistore.servlet;
import com.taxistore.pojo.RecvEntry;
import com.taxistore.dto.RecvEntryDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.RecvEntryService;
import com.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet：處理收入細項相關請求
 */
@WebServlet("/recvEntryServlet")
public class RecvEntryServlet extends BaseServlet {
    private final RecvEntryService recvEntryService = new RecvEntryService();

    public void getWelcome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //request.setAttribute("result",situationService.GetLast());
        request.getRequestDispatcher("/welcome.jsp").forward(request,response);
    }

    /**
     * 根據 recvFormId 取得收入細項
     */
    protected void ajaxGetItemsByRecvFormId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Long recvFromId =  WebUtils.stringToLong(request.getParameter("RecvFormId"));
        Result<List<RecvEntryDTO>> result=recvEntryService.GetItemsByRecvFormId(recvFromId);
        response.getWriter().write(result.toJSON());

    }

    protected void ajaxUpdateEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        // 取得entryId
        Long id =  WebUtils.stringToLong(request.getParameter("id"));
        String summary = request.getParameter("summary");
        Integer amount = WebUtils.stringToInt(request.getParameter("amount"));
        Result<Integer>result=recvEntryService.updateEntry(id, summary, amount);

        response.getWriter().write(result.toJSON());

    }
    protected void ajaxUpdateField(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long recvFormId = WebUtils.stringToLong(request.getParameter("recvFormId"));
        String field = request.getParameter("field");
        String value = request.getParameter("value");

         Result<Integer>result=recvEntryService.updateField(recvFormId, field, value);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxCreateRecvEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long codeId = WebUtils.stringToLong(request.getParameter("codeId"));
        Integer amount = WebUtils.stringToInt(request.getParameter("amount"));
        String s=request.getParameter("recvFormId");
        Long recvFormID = WebUtils.stringToLong(request.getParameter("recvFormId"));
        String summary=request.getParameter("summary");
        String debitCredit=request.getParameter("debitCredit");
        Result<RecvEntry>result =recvEntryService.createRecvEntry(recvFormID,codeId,summary,debitCredit,amount);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxDeleteRecvEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long recvFormId = WebUtils.stringToLong(request.getParameter("recvFormId"));
        Integer orderId = WebUtils.stringToInt(request.getParameter("orderId"));

        Result<List<RecvEntryDTO>>result =recvEntryService.deleteRecvEntry(recvFormId,orderId);
        response.getWriter().write(result.toJSON());
    }
}
