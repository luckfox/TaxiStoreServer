<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/10/7
  Time: 下午12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../common/head.jsp"%>
    <link rel="apple-touch-icon" href="webRoot/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="webRoot/pages/app-assets/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700" rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="webRoot/pages/app-assets/css/vendors.css">
    <link rel="stylesheet" type="text/css" href="webRoot/pages/app-assets/vendors/css/tables/datatable/datatables.min.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON  CSS-->
    <link rel="stylesheet" type="text/css" href="webRoot/pages/app-assets/css/app.css">
    <!-- END CHAMELEON  CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css" href="webRoot/pages/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
    <link rel="stylesheet" type="text/css" href="webRoot/pages/app-assets/css/core/colors/palette-gradient.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="webRoot/pages/assets/css/style.css">
    <!-- END Custom CSS-->
    <link href="/pages/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
    <h1>Hello!</h1>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <label class="card-title" for="LargeSelect">Large Select</label>
            </div>
            <div class="card-block">
                <div class="card-body">
                    <p>Use <code>.input-lg</code> class for large select</p>
                    <fieldset class="form-group position-relative">
                        <select class="form-control input-lg" id="LargeSelect">
                            <option selected>Large select options</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>

    <!-- BEGIN VENDOR JS-->
    <script src="/pages/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="/pages/app-assets/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
    <script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <script src="/pages/app-assets/js/scripts/tables/datatables/datatable-basic.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS-->
    <script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/pages/assets/js/loadHTML.js"> </script>
    <script type="text/javascript">
        $(function()
        {

        });
    </script>
</body>
</html>
