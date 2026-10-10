<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/10/10
  Time: 下午5:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../../common/head.jsp" %>
    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/vendors.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/forms/selects/select2.min.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/ui/prism.min.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/app.css">
    <!-- END CHAMELEON CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css"
          href="/pages/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/plugins/animate/animate.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <!-- END Custom CSS-->
    <style>
        tr.focus {
            background-color: #eee;
        }
        body .modal-dialog { /* Width */
            max-width: 100%;
            width: auto !important;
            display: inline-block;
        }
    </style>
    <link href="/pages/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/pages/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="form-group">
        <h5>Default Modal</h5>
        <p>Toggle a modal via JavaScript by clicking the button above.</p>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary " data-toggle="modal"
                data-target="#default">
            Launch Demo Modal
        </button>

        <!-- Modal -->
        <div class="modal fade  text-center" id="default" tabindex="-1"
             aria-labelledby="basicModalLabel1" aria-hidden="true">
            <div class="modal-dialog" role="document" style="display: inline-block; width: 1500px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="basicModalLabel1">Basic Modal</h4>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Woohoo, you're reading this text in a modal!you're reading this text in a modal!</p>
                        <table class="table table-bordered scroll-vertical">
                            <thead>
                            <tr>
                                <th>序號</th>
                                <th>公司名稱</th>
                                <th>負責人</th>
                                <th>電子郵件</th>
                                <th>統一編號</th>
                                <th>電話1</th>
                                <th>傳真1</th>
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
                                <th>傳真1</th>
                                <th>住址1</th>
                            </tr>
                            </tfoot>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn grey btn-secondary"
                                data-dismiss="modal">Close
                        </button>
                        <button type="button" class="btn btn-danger">Save</button>
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
<script src="/pages/app-assets/vendors/js/forms/select/select2.full.min.js" type="text/javascript"></script>
<script src="/pages/app-assets/vendors/js/ui/prism.min.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<!-- BEGIN CHAMELEON  JS-->
<script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
<script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>
<!-- END CHAMELEON  JS-->
<!-- BEGIN PAGE LEVEL JS-->
<script src="/pages/app-assets/js/scripts/forms/select/form-select2.js" type="text/javascript"></script>
<script src="/pages/app-assets/js/scripts/modal/components-modal.js" type="text/javascript"></script>
<!-- END PAGE LEVEL JS-->
<script type="text/javascript">
    $(function () {
        console.log("start script");
        $("tbody >tr").on("click", function () {
            console.log("select =" + $(this).find("td").eq(0).text());
        });
        $("tbody > tr").hover(function () {
            $(this).parent().find("tr.focus").toggleClass("focus");
            $(this).toggleClass("focus");
        });
    });
</script>
</body>
</html>
