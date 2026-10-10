<%--
  Created by IntelliJ IDEA.
  User: luckf
  Date: 2022/10/11
  Time: 下午 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <link rel="apple-touch-icon" href="/pages/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="/pages/app-assets/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700"
          rel="stylesheet">
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
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css">
    <!-- END Custom CSS-->

    <title>Title</title>
</head>
<body class="vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click"
      data-menu="vertical-menu-modern" data-color="bg-chartbg" data-col="2-columns">

<!--//start of navBar-->
<%@ include file="../../topNavBar.jsp" %>
<!--//end of navBar-->
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of left-side menu-->
<%@ include file="../../leftSideMenu.jsp" %>
<!--//end of left-side menu-->

<!-- ////////////////////////////////////////////////////////////////////////////-->
<div class="app-content content bg-dark">
    <div class="content-wrapper">
        <div class="content-header bg-dark row">
            <div class="content-header-left col-md-12 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">交通違規條文建檔</h3>
                <div class="row d-inline-block">
                    <div class=" mr-1">
                        <ol>
                            <li><h3 style="color:white"><span>流水編號:</span><span id="serialNoTitle">${requestScope.fineRule.fineRuleSeq}</span></h3>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xl-12 col-lg-12">
                <div class="card bg-dark text-white">
                    <div class="card-content collapse show">
                        <div class="card-body">
                            <input id="form-action" type=hidden name="action" value="createCompany"/>
                            <input id="serialNo" type=hidden name="serialNo"
                                   value="${requestScope.company.companyseq}"/>
                            <div class="row">
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text ">*條</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="條"
                                           id="rule1" name="rule1" aria-describedby="basic-addon1"
                                           value="${requestScope.fineRule.rule1}" required>
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text ">項</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="項"
                                           id="rule2" name="rule2" aria-describedby="basic-addon1"
                                           value="${requestScope.fineRule.rule2}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text ">款</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="款"
                                           id="rule3" name="rule3" aria-describedby="basic-addon1"
                                           value="${requestScope.fineRule.rule3}" required disabled="disabled">
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text ">金額</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="金額"
                                           id="amount" name="amount" aria-describedby="basic-addon1"
                                           value="${requestScope.fineRule.amount}" required disabled="disabled">
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-12 mb-1">
                                    <label class="input-group-text bg-grey-blue ">備註</label>
                                    <textarea rows="2" class="form-control update-enabled fc-0" placeholder="備註"
                                              id="description" name="description"
                                              disabled="disabled">${requestScope.fineRule.description}</textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-actions col-md-12">
                                    <div class="text-center">
                                        <button type="button" id="previous-button" class="btn btn-warning">上一筆<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="next-button" class="btn btn-warning">下一筆<i
                                                class="ft-file-plus position-right"></i></button>

                                        <button type="button" id="add-button" class="btn btn-warning">新增<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="delete-button" class="btn btn-danger">刪除 <i
                                                class="ft-file-text position-right"></i></button>
                                        <button type="button" id="update-button" class="btn btn-secondary">編輯 <i
                                                class="ft-refresh-cw position-right"></i></button>
                                        <button type="button" id="submit-button" class="btn btn-primary">送出 <i
                                                class="ft-thumbs-up position-right"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of footer menu-->
<%@ include file="../../footer.jsp" %>
<!--//end of footer menu-->
<!-- BEGIN VENDOR JS-->
<script src="/pages/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<script src="/pages/app-assets/vendors/js/charts/chartist.min.js" type="text/javascript"></script>
<script src="/pages/app-assets/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>
<script src="/pages/app-assets/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>
<script src="/pages/app-assets/vendors/js/extensions/dropzone.min.js" type="text/javascript"></script>
<script src="/pages/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<!-- BEGIN CHAMELEON  JS-->
<script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
<script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>
<!-- END CHAMELEON  JS-->
<!-- BEGIN PAGE LEVEL JS-->

<!-- END PAGE LEVEL JS-->
<!-- BEGIN CUSTOM LEVEL JS-->
<script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/pages/assets/js/loadHTML.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/buttonControl.js?<%= version %>"></script>
<script type="text/javascript">
    $(function () {
        let tmpSerialNo = ""; // 保存临时的序列号
        let updateMode = false; // 更新模式标志
        let appendMode = false; // 新增模式标志
        console.log("fineRule.jsp");
        $("#fine").attr("class", "open");
        $("#fineRule").attr("class", "active");

        /*
        * 往上一筆的事件
        */
        $("#previous-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>fineRuleServlet", "ajaxGetPreviousRule", ajaxCallBack);
        });

        /*
         * 往下一筆的事件
         */
        $("#next-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            nextButtonHandler(SerialNo, "<%=basePath%>fineRuleServlet", "ajaxGetNextRule", ajaxCallBack);
        });
        // 新增事件
        $("#add-button").click(function(){
            let SerialNo = $("#serialNoTitle").text().trim();
            appendMode=addButtonHandler(this,appendMode,SerialNo,"fineRuleServlet","ajaxGetFineRule",ajaxCallBack);
        });


        $("#update-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            updateMode=updateButtonHandler(this,updateMode,SerialNo,"fineRuleServlet","ajaxGetFineRule",ajaxCallBack)
        });

        $("#delete-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            deleteButtonHandler(SerialNo, "<%=basePath%>fineRuleServlet", "ajaxDeleteFineRule", ajaxCallBack);
        });

        $("#submit-button").click(function ()
        {
            const serialNo = $("#serialNoTitle").text().trim();
            console.log(`submit-button click,appendMode=${appendMode},updateMode=${updateMode}`);
            if(appendMode===true) submitButtonHandler("0", "fineRuleSeq","fineRuleServlet","ajaxUpdateFineRule", ajaxSubmitCallBack);
            else if(updateMode===true) submitButtonHandler(serialNo, "fineRuleSeq","fineRuleServlet","ajaxUpdateFineRule", ajaxSubmitCallBack);

        });


        /*
           Submit之後的處理方式
         */
        let ajaxSubmitCallBack = function (result)
        {
            console.log("ajaxSubmitCallBack mode="+result.mode+",result="+result.success);
            if(result.success==="true")
            {
                toggleInputs(false);

                $("#next-button, #previous-button, #update-button, #add-button, #delete-button").removeAttr("disabled");
                $("#submit-button").attr("disabled", "disabled");
                if(result.mode==="append")
                {
                    alert("新增作業成功");
                    console.log("新增作業成功");
                    $("#serialNoTitle").text(result.serialNo);
                    appendMode=false;
                }else
                {
                    $("#update-button").html("更新 <i class=\"ft-refresh-cw position-right\"></i>");
                    alert("更新作業成功");
                    console.log("更新作業成功");
                    updateMode=false;
                }
            }else
            {
                alert("作業失敗 "+result.errorMessage);
                console.log("作業失敗 "+result.errorMessage);
            }

        }




        let ajaxCallBack = function (jsonObj)
        {
            if (jsonObj.success === false)
            {
                alert(jsonObj.errorMessage);
                console.log(jsonObj.errorMessage);
                return 0;
            }
            let fineRule=jsonObj.data;
            $("#serialNoTitle").text(fineRule.fineRuleSeq);
            reflashData(jsonObj.data)
        };

    });
</script>
</body>
</html>
