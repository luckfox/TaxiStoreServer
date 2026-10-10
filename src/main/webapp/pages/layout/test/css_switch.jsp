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
<body class="bg-body">
    <h1>Hello!</h1>
    <div class="mb-2">
        <label class="form-label" for="email">email :*</label>
        <input type="text" class="form-control"   id="email" name="email" value="" pattern="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$" required >
    </div>
    <div class="mb-2">
        <label class="form-label" for="phone">phone :*</label>
        <input type="text" class="form-control"   id="phone" name="phone" value="" pattern="[0-9]{3}" required >
    </div>

    <div class="mb-3">
        <label for="exampleFormControlInput1" class="form-label">Email address</label>
        <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
    </div>
    <div class="mb-3">
        <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
    </div>

    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-primary" id="white_border">white</button>
        <button type="button" class="btn btn-primary" id="yellow_border">yellow</button>
        <button type="button" class="btn btn-primary" id="black_border">dark</button>
    </div>

    <script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>

    <!-- BEGIN VENDOR JS-->
<%--    <script src="/pages/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>--%>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="/pages/app-assets/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
<%--    <script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>--%>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <script src="/pages/app-assets/js/scripts/tables/datatables/datatable-basic.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS-->

    <script type="text/javascript" src="/pages/assets/js/loadHTML.js"> </script>
    <script type="text/javascript">
    $(function()
    {
        alert("hello!");
        console.log("hello");
        $("#white_border").click(function()
        {
            $("input[required]").each(function()
            {
                $(this).css("border-color","white");
                $(this).css("border-width","medium");
            });
        });
        $("#yellow_border").click(function()
        {
            $("input[required]").each(function()
            {
                $(this).css("border-color","yellow");
                $(this).css("border-width","thin");
            });
        });
        $("#black_border").click(function()
        {
           $("input[required]").each(function()
           {
               $(this).css("border-color","black");
               $(this).css("border-width","medium");
           });
        });
    });
    </script>
</body>
</html>
