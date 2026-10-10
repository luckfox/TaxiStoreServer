package com.taxistore.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testEventServlet")
public class TestEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String data = request.getParameter("data");

        if ("submitForm".equals(action)) {
            response.getWriter().write("Received: " + data);
        } else {
            response.getWriter().write("Invalid action.");
        }
    }
}
