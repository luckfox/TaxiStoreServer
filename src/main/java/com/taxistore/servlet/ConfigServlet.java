package com.taxistore.servlet;

import com.taxistore.pojo.Result;
import com.utils.*;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Map;
import java.util.Properties;


/**
 * @author web端的多國語言
 */
//@WebServlet("/config")
@WebServlet(urlPatterns = "/config", loadOnStartup = 1) // 加 loadOnStartup
public class ConfigServlet extends BaseServlet
{
    private static final String APPLICATION_PROPERTIES_FILE="application.properties";
    private static final Properties applicationProperties = PropertiesUtil.load(APPLICATION_PROPERTIES_FILE);


    @Override
    public void init() throws ServletException
    {
        super.init();

        ServletContext context = getServletContext();
        //Properties props = new Properties();

        Config config = new Config(applicationProperties); // 封裝成 Config 物件
        //已經把 Config 物件放進了 applicationScope：
        context.setAttribute("config", config);
        Log.debug(config.toString());
        //System.out.println("[ConfigServlet] application.properties 已載入");

    }
    protected void ajaxGetProperty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String key = request.getParameter("key");
        if(key==null)  {
            response.getWriter().write(Result.failure("no paramter").toJSON());
            return;
        }
        String value=applicationProperties.getProperty(key);
        response.getWriter().write(Result.success(value).toJSON());
    }
    protected void getLanguage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String language = request.getParameter("language");

        // 使用 LanguageUtil 加載語言文件
        Map<String, String> messages = Language2Utils.loadLanguageProperties(language, getServletContext());

        // 使用 LanguageUtil 轉換為 JSON
        String jsonResponse = Convert.convertToJson(messages);

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
