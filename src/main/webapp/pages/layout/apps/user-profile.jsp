<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loading" lang="zh-tw" data-textdirection="ltr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords" content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">

    <%@ include file="../../common/head.jsp"%>

    <title>Dashboard eCommerce - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
    <link rel="apple-touch-icon" href="/pages/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="/pages/app-assets/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700" rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/vendors.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/charts/chartist.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/charts/chartist-plugin-tooltip.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/file-uploaders/dropzone.min.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/forms/icheck/icheck.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/forms/icheck/custom.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/timeline/vertical-timeline.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON  CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/app.css">
    <!-- END CHAMELEON  CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/fonts/simple-line-icons/style.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/pages/timeline.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/plugins/file-uploaders/dropzone.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/pages/dashboard-ecommerce.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/assets/css/style.css">
    <!-- END Custom CSS-->
    <style>
      .main-menu-content li
      {
        Font-size:25px;
      }
      .menu-content li
      {
        Font-size:18px;
      }
    </style>

  </head>
  <body class="vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu-modern" data-color="bg-chartbg" data-col="2-columns">

    <!--//start of navBar-->
    <div w3-include-html="webRoot/pages/topNavBar.html"></div>
    <!--//end of navBar-->
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!--//start of left-side menu-->
    <div w3-include-html="webRoot/pages/leftSideMenu.html"></div>
    <!--//end of left-side menu-->

    <!--//start of footer menu-->
    <div w3-include-html="webRoot/pages/footer.html"></div>
    <!--//end of footer menu-->

    <!-- BEGIN VENDOR JS-->
    <script src="/pages/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <%--<script src="/pages/app-assets/vendors/js/charts/chartist.min.js" type="text/javascript"></script>--%>
    <%--<script src="/pages/app-assets/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>--%>
    <%--<script src="/pages/app-assets/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>--%>
    <%--<script src="/pages/app-assets/vendors/js/extensions/dropzone.min.js" type="text/javascript"></script>--%>
    <%--<script src="/pages/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>--%>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
    <script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <script src="/pages/app-assets/js/scripts/pages/dashboard-ecommerce.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS-->
    <!-- BEGIN CUSTOM LEVEL JS-->
    <script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/pages/assets/js/loadHTML.js"> </script>
    <script type="text/javascript">
        console.log("user-profile.jsp");
        loadHTML();
        $(function()
        {
            $("#apps").attr("class","open");
            $("#user-profile").attr("class","active");
        });

    </script>
    <!-- END CUSTOM LEVEL JS-->


  </body>
</html>