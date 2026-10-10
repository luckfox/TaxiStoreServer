<%--
  Created by IntelliJ IDEA.
  User: luckf
  Date: 2022/10/4
  Time: 下午 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../common/head.jsp"%>
    <title>Title</title>
</head>
<body>
<div>
    <a href="companyServlet?action=getLastCompany" target="_blank">取得最後一筆公司資料 </a>

    <form class="form row" action="/companyServlet">
        <input type=hidden name="action" value="createCompany"/>
        <div class="form-group col-md-6 mb-2">
            <label class="text-white" for="companyName">公司名稱 :*</label>
            <input type="text" class="form-control"   id="companyName" name="companyName" value="${requestScope.company.companyName}" required >
        </div>
        <div class="form-group col-md-4 mb-2">
            <label class="text-white" for="companySimpleName">公司簡稱 :*</label>
            <input type="text" class="form-control"  id="companySimpleName" name="companySimpleName" value="${requestScope.company.companySimpleName}"  required>
        </div>
        <div>
            <input id="principal" name="principal" type="text" value="${requestScope.company.principal}"/>
        </div>

    </form>
</div>

</body>
</html>
