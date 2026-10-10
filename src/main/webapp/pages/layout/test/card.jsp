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
    <div class="card border-success mb-3" style="max-width: 18rem;">
        <div class="card-header bg-transparent border-success">Header</div>
        <div class="card-body text-success">
            <h5 class="card-title">Success card title</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
        </div>
        <div class="card-footer bg-transparent border-success">Footer</div>
    </div>


    <div class="col-lg-12 col-md-6">
        <div id="collapse3" class="card-accordion">
            <div class="card bg-dark collapse-icon accordion-icon-rotate">
                <div class="card bg-dark">
                    <div class="card-header" id="headingCOne">
                            <label class="btn  text-white collapsed " data-toggle="collapse" data-target="#collapseC1" aria-expanded="false" aria-controls="collapseC1">
                                <span class="text-white" >駕駛員相關資料</span>
                            </label>
                    </div>
                    <div id="collapseC1" class="collapse" aria-labelledby="headingCOne">
                        <div class="card bg-dark">
                            <div class="input-group col-md-6 mb-1 mt-1">
                                <span class="input-group-text bg-grey-blue text-white" >預收保證金</span>
                                <input type="text" class="form-control" placeholder="預收保證金" disabled="disabled" id="pre_security_deposit" name="pre_security_deposit" aria-label="pre_security_deposit" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                            </div>
                            <div class="input-group col-md-6 mb-1">
                                <span class="input-group-text bg-grey-blue text-white" >指定驗車日</span>
                                <input type="text" class="form-control" placeholder="指定驗車日" disabled="disabled" id="chkec_date" name="chkec_date" aria-label="chkec_date" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingCTwo">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseC2" aria-expanded="false" aria-controls="collapseC2">
                            駕駛員
                        </button>
                    </div>
                    <div id="collapseC2" class="collapse" aria-labelledby="headingCTwo">
                        <div class="card-body">
                            <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</p>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingCThree">

                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseC3" aria-expanded="false" aria-controls="collapseC3">
                                Collapsible Group Item #3
                            </button>

                    </div>
                    <div id="collapseC3" class="collapse" aria-labelledby="headingCThree">
                        <div class="card-body">
                            <p class="text-black" >Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</p>
                        </div>
                    </div>
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
    <script src="/pages/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/pages/assets/js/loadHTML.js"> </script>
    <script type="text/javascript">
    </script>
</body>
</html>
