package com.taxistore.servlet;


import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.UserService;
import com.utils.Convert;
import com.utils.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author luckf123123
 *
 http://localhost:8088/userServlet?action=login&userNo=0000&password=3795
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException
    {
        userService=new UserService();
        Log.normal("init OK");
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Password password=new Password();
        password.setUserNo(request.getParameter("userNo"));
        password.setPassword(request.getParameter("password"));
        Log.normal("login userNo="+password.getUserNo()+" password="+password.getPassword());
        Result<Integer>result=userService.login(password);
        response.getWriter().write(result.toJSON());
    }

    protected void ajaxGetUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Password password=new Password();
        password.setUserNo(request.getParameter("userNo"));
        Result<Password> result=userService.getUserNameByUserNo(password);
        response.getWriter().write(result.toJSON());
    }


}
