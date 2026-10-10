package com.taxistore.servlet;

import com.taxistore.servlet.BaseServlet;
import com.utils.Convert;
import com.utils.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @
 * @author luckf

http://localhost:8088/welcomeServlet?action=ajaxGetMessage
 */
@WebServlet("/welcomeServlet")
public class WelcomeServlet extends BaseServlet
{

    protected void ajaxGetMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = response.getWriter();
        Map<String, String> responseData = new HashMap<>();
        responseData.put("success", "true");
        responseData.put("message","歡迎光臨我們的網站！");
        out.print(Convert.convertToJson(responseData) );
        out.flush();
    }

}
