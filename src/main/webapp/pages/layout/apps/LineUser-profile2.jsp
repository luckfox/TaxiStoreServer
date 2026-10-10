<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loading" lang="zh-tw" data-textdirection="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description"
          content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords"
          content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">

    <%@ include file="../../common/head.jsp" %>

    <title>Dashboard eCommerce - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
    <link rel="apple-touch-icon" href="<%= app_assetsPath %>/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="<%= app_assetsPath %>/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700"
          rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/vendors.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/tables/datatable/datatables.min.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist-plugin-tooltip.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/file-uploaders/dropzone.min.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/forms/icheck/icheck.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/forms/icheck/custom.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/timeline/vertical-timeline.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON  CSS-->
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/app.css">
    <!-- END CHAMELEON  CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css"
          href="<%= app_assetsPath %>/css/core/menu/menu-types/vertical-menu-modern.css">
    <%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">--%>
    <%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/fonts/simple-line-icons/style.css">--%>
    <%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">--%>
    <%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/timeline.css">--%>
    <%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/plugins/file-uploaders/dropzone.css">--%>
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/dashboard-ecommerce.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css">


</head>
<body class="bg-dark vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click"
      data-menu="vertical-menu-modern" data-color="bg-chartbg" data-col="2-columns">

<!--//start of navBar-->
<%@ include file="../../topNavBar.jsp" %>
.
<!--//end of navBar-->
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of left-side menu-->
<%@ include file="../../leftSideMenu.jsp" %>
.
<!--//end of left-side menu-->

<!--//start of body-->
<div class="app-content content">
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-12 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">公司資料建檔</h3>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-content collpase show">
                                <div id="my_table" class="card-body card-dashboard">
                                    <table class="table table-striped table-bordered sourced-data">
                                        <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start date</th>
                                            <th>Salary</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Tiger2 Nixon</td>
                                            <td>System Architect</td>
                                            <td>Edinburgh</td>
                                            <td>61</td>
                                            <td>2011/04/25</td>
                                            <td>$320,800</td>
                                        </tr>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start date</th>
                                            <th>Salary</th>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!--//end of body-->
<!--//start of footer menu-->
<%@ include file="../../footer.jsp" %>
.
<!--//end of footer menu-->

<!-- BEGIN VENDOR JS-->
<script src="<%= app_assetsPath %>/vendors/js/vendors.min.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<%--<script src="/pages/app-assets/vendors/js/charts/chartist.min.js" type="text/javascript"></script>--%>
<%--<script src="/pages/app-assets/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>--%>
<%--<script src="/pages/app-assets/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>--%>
<%--<script src="/pages/app-assets/vendors/js/extensions/dropzone.min.js" type="text/javascript"></script>--%>
<%--<script src="/pages/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>--%>
<script src="<%= app_assetsPath %>/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<!-- BEGIN CHAMELEON  JS-->
<script src="<%= app_assetsPath %>/js/core/app-menu.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/js/core/app.js" type="text/javascript"></script>
<!-- END CHAMELEON  JS-->
<!-- BEGIN PAGE LEVEL JS-->
<%--    <script src="<%= app_assetsPath %>/js/scripts/pages/dashboard-ecommerce.js" type="text/javascript"></script>--%>
<script src="<%= app_assetsPath %>/js/scripts/tables/datatables-extensions/datatables-sources.js"
        type="text/javascript"></script>

<!-- END PAGE LEVEL JS-->
<!-- BEGIN CUSTOM LEVEL JS-->
<script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script type="text/javascript">

    $(function () {
        //設定leftSideMenu.jsp 的階層式選單，避免重複點選頁面
        console.log("lineUser-profile.jsp");
        $("#apps").attr("class", "open");
        console.log("set #dashboard class=open");
        $("#lineUser-profile").attr("class", "active");

        $('#my_table').DataTable({
            "serverSide": false,  // 啟用伺服器端處理
            "paging": true,      // 分頁
            "searching": true,   // 啟用搜尋
            "ordering": true,    // 啟用排序
            "responsive": true
        });
    });


</script>
<!-- END CUSTOM LEVEL JS-->


</body>
</html>