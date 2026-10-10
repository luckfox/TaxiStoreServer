package com.taxistore.servlet;

import com.taxistore.dto.PostcodeDTO;
import com.taxistore.pojo.Postcode;
import com.taxistore.pojo.Result;
import com.taxistore.service.impl.PostcodeService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/postcodeServlet")
public class PostcodeServlet extends BaseServlet {
    private final PostcodeService service = new PostcodeService();

    public void ajaxGetPostcodeByCodeLike(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String codePart =  request.getParameter("codePart");
        Result<List<PostcodeDTO>> result=service.getPostcodeByCodeLike(codePart);
        response.getWriter().write(result.toJSON());
    }
}