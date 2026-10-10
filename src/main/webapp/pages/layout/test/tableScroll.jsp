<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords" content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">
    <%@ include file="../../common/head.jsp"%>
    <title>Basic DataTables - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
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
    <style>
        tr.focus
        {
            background-color:#eee;
        }
        </style>
</head>
<body>
<div class="container">
    <!-- Scroll - vertical table -->
    <section id="scroll">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Scroll - vertical</h4>
                        <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                        <div class="heading-elements">
                            <ul class="list-inline mb-0">
                                <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                <li><a data-action="close"><i class="ft-x"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-content collapse show">
                        <div class="card-body card-dashboard">
                            <p class="card-text">This example shows the DataTables table body scrolling in the vertical direction. This can generally be seen as an alternative method to pagination for displaying a large table in a fairly small vertical area, and as such pagination has been disabled here (note that this is not mandatory, it will work just fine with pagination enabled as well!).</p>
                            <table class="table table-bordered scroll-vertical">
                                <thead>
                                <tr>
                                    <th>序號</th>
                                    <th>公司名稱</th>
                                    <th>負責人</th>
                                    <th>電子郵件</th>
                                    <th>統一編號</th>
                                    <th>電話1</th>
                                    <th>傳真2</th>
                                    <th>住址1</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.Companies}" var="company">
                                <tr>
                                    <td>${company.serialNo}</td>
                                    <td>${company.companyName}</td>
                                    <td>${company.principal}</td>
                                    <td>${company.email}</td>
                                    <td>${company.uniformNo}</td>
                                    <td>${company.phone1}</td>
                                    <td>${company.fax1}</td>
                                    <td>${company.address1}</td>
                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>序號</th>
                                    <th>公司名稱</th>
                                    <th>負責人</th>
                                    <th>電子郵件</th>
                                    <th>統一編號</th>
                                    <th>電話1</th>
                                    <th>傳真2</th>
                                    <th>住址1</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/ Scroll - vertical table -->
    <!-- Scroll - horizontal table -->
    <section id="horizontal">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Scroll - horizontal</h4>
                        <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                        <div class="heading-elements">
                            <ul class="list-inline mb-0">
                                <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                <li><a data-action="close"><i class="ft-x"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-content collapse show">
                        <div class="card-body card-dashboard">
                            <p class="card-text">Scroll - horizontal DataTables has the ability to show tables with horizontal scrolling, which is very useful for when you have a wide table, but want to constrain it to a limited horizontal display area. To enable x-scrolling simply set the scrollX parameter to be whatever you want the container wrapper's width to be (this should be 100% in almost all cases with the width being constrained by the container element).</p>
                            <table class="table display nowrap table-bordered scroll-horizontal">
                                <thead>
                                <tr>
                                    <th>序號</th>
                                    <th>公司名稱</th>
                                    <th>負責人</th>
                                    <th>電子郵件</th>
                                    <th>統一編號</th>
                                    <th>電話1</th>
                                    <th>傳真2</th>
                                    <th>住址1</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.Companies}" var="company">
                                    <tr>
                                        <td>${company.serialNo}</td>
                                        <td>${company.companyName}</td>
                                        <td>${company.principal}</td>
                                        <td>${company.email}</td>
                                        <td>${company.uniformNo}</td>
                                        <td>${company.phone1}</td>
                                        <td>${company.fax1}</td>
                                        <td>${company.address1}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/ Scroll - horizontal table -->

</div>
<!-- ////////////////////////////////////////////////////////////////////////////-->


<footer class="footer footer-static footer-light navbar-border navbar-shadow">
    <div class="clearfix blue-grey lighten-2 text-sm-center mb-0 px-2"><span class="float-md-left d-block d-md-inline-block">2018  &copy; Copyright <a class="text-bold-800 grey darken-2" href="https://themeselection.com" target="_blank">ThemeSelection</a></span>
        <ul class="list-inline float-md-right d-block d-md-inline-blockd-none d-lg-block mb-0">
            <li class="list-inline-item"><a class="my-1" href="https://themeselection.com/" target="_blank"> More themes</a></li>
            <li class="list-inline-item"><a class="my-1" href="https://themeselection.com/support" target="_blank"> Support</a></li>
            <li class="list-inline-item"><a class="my-1" href="https://themeselection.com/products/chameleon-admin-modern-bootstrap-webapp-dashboard-html-template-ui-kit/" target="_blank"> Purchase</a></li>
        </ul>
    </div>
</footer>

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
<script type="text/javascript">
$(function()
{
    console.log("start script");
    $("tbody >tr").on("click",function()
    {
        console.log("select ="+ $(this).find("td").eq(0).text());
    });
    $("tbody > tr").hover(function()
    {
        $(this).parent().find("tr.focus").toggleClass("focus");
        $(this).toggleClass("focus");
    });
});
</script>
</body>
</html>