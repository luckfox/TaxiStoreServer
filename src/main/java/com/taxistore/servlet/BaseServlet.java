package com.taxistore.servlet;

import com.utils.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 * @author luckf
 * @date 2022/4/27
 */

public abstract class BaseServlet extends HttpServlet
{


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        String servletName = request.getServletPath();
        Log.debug("doGet@baseServlet servletName/action="+servletName+"/"+action);
        if(action==null)
        {
            return;
        }
        try
        {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            if (method != null) method.invoke(this, request, response);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);//把異常往外拋,拋到TransactionFilter

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        doPost(request,response);
    }
}
