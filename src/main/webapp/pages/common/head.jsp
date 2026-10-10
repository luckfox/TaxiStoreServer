<%--
  Created by IntelliJ IDEA.
  User: luckf
  Date: 2022/4/26
  Time: 上午 02:13
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

    request.setAttribute("basePath",basePath);
    String basePath2=(String)request.getAttribute("basePath");

    long version = new java.util.Date().getTime();

%>
<%
    String app_assetsPath = request.getContextPath() + "/pages/app-assets";
    String assetsPath = request.getContextPath() + "/pages/assets";
    String contextPath= request.getContextPath();
%>

<%--   http://192.168.5.22:8080/currentPath/    --%>
<base href="<%=basePath%>">

<%--${basePath}--%>
