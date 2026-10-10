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

    <%@ include file="../../common/head.jsp"%>
    <title>Dashboard eCommerce - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
    <link rel="apple-touch-icon" href="<%= app_assetsPath %>/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="<%= app_assetsPath %>/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700"
          rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/vendors.css">
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
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/fonts/simple-line-icons/style.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/timeline.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/plugins/file-uploaders/dropzone.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/dashboard-ecommerce.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/ImagePreviewManager.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css?<%= version %>">
    <!-- END Custom CSS-->

</head>
<body class="bg-dark vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click"
      data-menu="vertical-menu-modern" data-color="bg-chartbg" data-col="2-columns">

<!--//start of navBar-->
<%@ include file="../../topNavBar.jsp" %>
<!--//end of navBar-->
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of left-side menu-->
<%@ include file="../../leftSideMenu.jsp" %>
<!--//end of left-side menu-->


<!-- ////////////////////////////////////////////////////////////////////////////-->
<div id="root" class="app-content content">
    <div class="content-wrapper">
        <%--        <div class="content-wrapper-before"></div>--%>
        <div class="content-header row">
            <div class="content-header-left col-md-8 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">駕駛員資料建檔</h3>
                <div class="row d-inline-block">
                    <div class=" mr-1">
                        <ol>
                            <li><h3 style="color:white"><span>流水編號:</span><span
                                    id="serialNoTitle">${requestScope.driverDTO.driverseq}</span></h3>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xl-12 col-lg-12">
                <div class="card bg-dark text-white">
                    <div class="card-content collapse show">
                        <div class="card-body">

                            <div class="row">
                                <input id="form-action" type=hidden name="action" value="createCompany"/>
                                <input id="driverseq" type=hidden name="driverseq"
                                       value="${requestScope.driverDTO.driverseq}"/>

                                <div class="input-group col-md-2 mb-1">
                                    <label class="input-group-text" for="drivername">*姓名</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="姓名" id="drivername" name="drivername" aria-label=""
                                           aria-describedby="basic-addon1" value="${requestScope.driverDTO.drivername}"
                                           required>
                                </div>

                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text" for="specialcode">*特別碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="特別碼" id="specialcode" name="specialcode" aria-label=""
                                           aria-describedby="basic-addon1" value="${requestScope.driverDTO.specialcode}"
                                           required>
                                    <input type="text" class="form-control fc-1" placeholder="特別碼"
                                           id="specialCodeSearch" name="specialCodeSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-specialCodeSearch"/>
                                </div>

                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="id">*身分證號</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="身分證號" id="id" name="id"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.id}" required>
                                    <input type="text" class="form-control fc-1" placeholder="身分證號"
                                           id="idSearch" name="idSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-idSearch"/>
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text" for="str_birthday">*生日</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="生日" id="str_birthday" name="birthday"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.str_birthday}" required>
                                </div>
                            </div>
                            <div class="row">


                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text " for="postcodeCode">*郵遞區號</label>

                                    <input type="hidden" class="form-control update-enabled fc-0" placeholder="郵遞區號" id="postcodeId" name="postcodeId"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.postcodeId}">

                                    <input type="text" class="form-control update-enabled fc-0" placeholder="郵遞區號" id="postcodeCode" name="postcodeCode"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.postcodeCode}" required>

                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-postcodeCode"/>

                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text" for="commtel">行動電話</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="行動電話" id="portable" name="portable"
                                           aria-label="" aria-describedby="basic-addon1" autocomplete="off"
                                           value="${requestScope.driverDTO.portable}">
                                </div>

                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text" for="householdt">戶籍電話</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="戶籍電話" id="householdt" name="householdt"
                                           aria-label="" aria-describedby="basic-addon1" autocomplete="off"
                                           value="${requestScope.driverDTO.householdt}">
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text" for="commtel">通訊電話</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="通訊電話" id="commtel" name="commtel"
                                           aria-label="" aria-describedby="basic-addon1" autocomplete="off"
                                           value="${requestScope.driverDTO.commtel}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-6 mb-1">
                                    <label class="input-group-text" for="householda">戶籍地址</label>

                                    <!-- 新增 input，1/4 寬度（3） -->
                                    <input type="text" class="form-control col-3 fc-0" placeholder="郵遞區號" id="str_postcode" name="str_postcode" disabled="disabled"
                                           aria-label="" aria-describedby="basic-addon1" autocomplete="off"
                                           value="${requestScope.driverDTO.regionName}${requestScope.driverDTO.districtName}">
                                    <!-- 原有 input，占 3/4 寬度（9） -->
                                    <input type="text" class="form-control col-9 update-enabled fc-0" placeholder="戶籍地址" id="householda" name="householda"
                                           aria-label="" aria-describedby="basic-addon1" autocomplete="off"
                                           value="${requestScope.driverDTO.householda}">
                                </div>
                                <div class="input-group col-md-6 mb-1">
                                    <label class="input-group-text " for="commadd">通訊地址</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="通訊地址" id="commadd" name="commadd"
                                           aria-label="" aria-describedby="basic-addon1" autocomplete="off"
                                           value="${requestScope.driverDTO.commadd}">
                                </div>

                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="pfine">欠款保留金</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="滯納金底數" id="pfine" name="pfine"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.pfine}">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="pfineper">滯納金比例</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="滯納金比例" id="pfineper" name="pfineper"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.pfineper}">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="pfineday">催收日</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="催收日" id="pfineday" name="pfineday"
                                           aria-label="" aria-describedby="basic-addon1"
                                           value="${requestScope.driverDTO.pfineday}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-12 mb-1">
                                    <label class="input-group-text" for="remark">備註</label>
                                    <textarea rows="2" class="form-control update-enabled fc-0" placeholder="備註" id="remark" name="remark"
                                              aria-label="remark">${requestScope.driverDTO.remark}</textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-12">
                                    <div id="collapse3" class="card-accordion">
                                        <div class="card bg-dark collapse-icon accordion-icon-rotate">
                                            <div class="card bg-dark ">
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCOne">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC1" aria-expanded="false"
                                                           aria-controls="collapseC1">
                                                        <h4><span class=" text-white bg-dark ">受僱資料</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC1" class="collapse" aria-labelledby="headingCOne">
                                                    <div class="card bg-dark">
                                                        <div class="input-group col-md-6  mt-1">
                                                            <div class="row">
                                                                <div class="input-group col-md-6  mt-1">
                                                                    <label class="input-group-text" for="carno">受僱車號</label>
                                                                    <input type="text" class="form-control fc-0" id="carno" name="carno"
                                                                           placeholder="受僱車號" aria-label="" aria-describedby="basic-addon1" disabled="disabled"
                                                                           value="${requestScope.driverDTO.carno}">
                                                                </div>
                                                                <div class="input-group col-md-6  mt-1">
                                                                    <label class="input-group-text" for="str_indate">受僱日期</label>
                                                                    <input type="text" class="form-control update-enabled fc-0"
                                                                           placeholder="受僱日期" id="str_indate" name="indate" aria-label="" placeholder="yyyy-mm-dd"
                                                                           aria-describedby="basic-addon1"
                                                                           value="${requestScope.driverDTO.str_indate}">

                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-group col-md-6  mt-1">
                                                                    <label class="input-group-text" for="ownerName">受僱車行</label>
                                                                    <input type="text" class="form-control fc-0" id="ownerName" name="ownerName"
                                                                           placeholder="受僱車行" aria-label="" aria-describedby="basic-addon1"  disabled="disabled"
                                                                           value="${requestScope.driverDTO.ownerName}">
                                                                    <select class=" input-group-text form-select fc-2" disabled="disabled"
                                                                            id="ownerSC" name="ownerSC">
                                                                        <option selected>${requestScope.driverDTO.ownerSC}</option>
                                                                        <option value="1">張育瑋</option>
                                                                        <option value="2">劉國泰</option>
                                                                        <option value="3">張黃阿甘</option>
                                                                    </select>
                                                                </div>
                                                                <div class="input-group col-md-6  mt-1">
                                                                    <label class="input-group-text" for="str_outdate">解約日期</label>
                                                                    <input type="date" class="form-control update-enabled fc-0"
                                                                           placeholder="解約日期" id="str_outdate" name="outdate" aria-label="" placeholder="yyyy-mm-dd"
                                                                           aria-describedby="basic-addon1"
                                                                           value="${requestScope.driverDTO.str_outdate}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCTwo">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                       data-target="#collapseC2" aria-expanded="false"
                                                       aria-controls="collapseC2">
                                                    <h4><span class=" text-white bg-dark ">駕駛員證件</span></h4>
                                                </label>
                                                </div>
                                                <div id="collapseC2" class="collapse" aria-labelledby="headingCTwo">
                                                <div class="card bg-dark">
                                                    <div id="mainRow" class="row mt-3">
                                                        <!-- 左邊按鈕區 -->
                                                        <div id="columnA" class="col-md-3">
                                                            <div class="row mb-2">
                                                                <div class="col-6">
                                                                    <button id="syncBtn" class="btn btn-primary w-100">遠端同步</button>
                                                                </div>
                                                                <div class="col-6">
                                                                    <button id="uploadBtn" class="btn btn-primary w-100">上傳圖片</button>
                                                                </div>
                                                            </div>
                                                            <div class="row mb-2">
                                                                <div class="col-6">
                                                                    <button id="clearBtn" class="btn btn-primary w-100">重置預覽</button>
                                                                </div>
                                                                <div class="col-6">
                                                                    <label id="dropzoneLabel"  class="btn btn-primary w-100 d-inline-block" style="height: 38px;">
                                                                        拖曳上傳
                                                                        <input id="fileInput" type="file" accept="image/*" hidden multiple>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                            <div class="row mb-2">
                                                                <div class="col-6">
                                                                    <button id="LineBtn" class="btn btn-primary w-100">Line訊息</button>
                                                                </div>
                                                                <div class="col-6">
                                                                    <button id="ResetBtn" class="btn btn-primary w-100">aaa訊息</button>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!-- 右邊圖片預覽區 -->
                                                        <div id="columnB" class="col-md-9">
                                                            <div class="border rounded p-1 bg-light" style="height: 150px; overflow-x: auto; overflow-y: hidden;">
                                                                <div id="previewArea" class="d-flex flex-nowrap gap-2">
                                                                    <!-- 預覽圖片會加在這裡，每張圖片固定大小 -->
                                                                    <!-- <img src="xxx.jpg" class="preview-thumb"> -->
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                                </div>
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCThree">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC3" aria-expanded="false"
                                                           aria-controls="collapseC3">
                                                        <h4><span class=" text-white bg-dark ">保證人資料</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC3" class="collapse" aria-labelledby="headingCThree">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text" for="guarantor">姓名</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="姓名"
                                                                       id="guarantor" name="guarantor" aria-label=""
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.guarantor}">
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text" for="guarantorid">身分證號</label>
                                                                <input type="text" class="form-control update-enabled fc-0"
                                                                       placeholder="身分證號" id="guarantorid" name="guarantorid"
                                                                       aria-label="driver_birthday"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.guarantorid}">
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text" for="relation">關係</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="關係"
                                                                       id="relation" name="relation" aria-label="driver_birthday"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.relation}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-6  mt-1">
                                                                <label class="input-group-text " for="guarantoradd">地址</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="地址"
                                                                       id="guarantoradd" name="guarantoradd" aria-label="driver_birthday"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.guarantoradd}">
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text" for="guarantortel">電話</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="電話"
                                                                       id="guarantortel" name="guarantortel" aria-label="driver_birthday"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.guarantortel}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCFour">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC4" aria-expanded="false"
                                                           aria-controls="collapseC3">
                                                        <h4><span class=" text-white bg-dark ">緊急聯絡人資料</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC4" class="collapse" aria-labelledby="headingCFour">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <div class="input-group col-md-6  mt-1">
                                                                <label class="input-group-text" for="contact2">姓名</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="姓名"
                                                                       id="contact2" name="contact2" aria-label=""
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.contact2}">
                                                            </div>
                                                            <div class="input-group col-md-6  mt-1">
                                                                <label class="input-group-text" for="contactel">電話</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="電話"
                                                                       id="contactel" name="contactel" aria-label="driver_birthday"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.driverDTO.contactel}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <div class="form-actions col-md-12">
                                    <div class="text-center">
                                        <button type="button" id="first-button" class="btn btn-warning">首筆<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="previous-button" class="btn btn-warning">上一筆<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="next-button" class="btn btn-warning">下一筆<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="last-button" class="btn btn-warning">末筆<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="add-button" class="btn btn-warning">新增<i
                                                class="ft-file-plus position-right"></i></button>
                                        <button type="button" id="delete-button" class="btn btn-danger">刪除 <i
                                                class="ft-file-text position-right"></i></button>
                                        <button type="button" id="update-button" class="btn btn-secondary">更新 <i
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
<div id="modalContainer"></div>
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of left-side menu-->
<%@ include file="../../footer.jsp" %>
.
<!--//end of left-side menu-->


<!-- BEGIN VENDOR JS-->
<script src="<%= app_assetsPath %>/vendors/js/vendors.min.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<script src="<%= app_assetsPath %>/vendors/js/charts/chartist.min.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/vendors/js/charts/chartist-plugin-tooltip.min.js"
        type="text/javascript"></script>
<script src="<%= app_assetsPath %>/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/vendors/js/extensions/dropzone.min.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<!-- BEGIN CHAMELEON  JS-->
<script src="<%= app_assetsPath %>/js/core/app-menu.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/js/core/app.js" type="text/javascript"></script>
<!-- END CHAMELEON  JS-->
<!-- BEGIN PAGE LEVEL JS-->

<!-- END PAGE LEVEL JS-->
<!-- BEGIN CUSTOM LEVEL JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/LinePushMessage.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/addImagePreviewWithHash.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/modal/showModal.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/fineImageUtils/fineImageUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/hashUtils.js?<%= version %>"></script>
<script type="module" src="<%= assetsPath %>/js/alert/autoAlert.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/CarouselRotationManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/BasePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImagePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImageUploader.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/buttonControl.js?<%= version %>"></script>
<script type="text/javascript">
    $(function () {
        console.log("createDriverDB.jsp");
        $("#form").attr("class", "open");
        $("#createDriverDB").attr("class", "active");

        const alertBox = new AutoAlert();
        let tmpSerialNo = ""; // 保存临时的序列号
        let updateMode = false; // 更新模式标志
        let appendMode = false; // 新增模式标志

        const $driverseq=$("#driverseq");
        console.log(`sync button click $driverseq=\${$driverseq.val()}`);

        const $specialCodeSearch=$('#specialCodeSearch');
        const $idSearch = $('#idSearch');
        const $postcodeCode=$("#postcodeCode");

        const $autocomplete_list_specialCodeSearch = $('#autocomplete-list-specialCodeSearch');
        const $autocomplete_list_idSearch = $('#autocomplete-list-idSearch');
        const $autocomplete_list_postcodeCode = $('#autocomplete-list-postcodeCode');

        let _metaMap=new Map();
        let manager = null;

        const previewTag="#previewArea";
        const carouselTag="#carouselPreview";
        const carouselModalTag="#imageCarouselModal";
        const carouselModalHTML="/pages/layout/modals/ImageCarousel.html?_="+ new Date().getTime();
        const $module_container=$("#modalContainer");

        (async () =>
        {
            const config = {
                previewTag: previewTag,                 // 預覽區容器
                carouselTag: carouselTag,               // Carousel 內部
                carouselModalTag: carouselModalTag,     // Modal 外層
                modalContentPath: carouselModalHTML,    // Modal HTML路徑
                modalContainer: $module_container,      // Modal掛載容器
                imagesMeta:{servlet:"driverImageServlet",action:"ajaxGetImageMetasByDriverSeq",parameter:"driverseq"},  //取得imagesMeta
                imageData:{servlet:"driverImageServlet",action:"ajaxGetImageByImageId",parameter:"ImageId"},      //取得image圖像資料
                imageDelete:{servlet:"driverImageServlet",action:"ajaxDeleteImageByImageId",parameter:"ImageId"},
                imageUpload:{servlet:"driverImageServlet",action:"ajaxUploadDriverImage",parameter:"driverseq"},
                imageRotation:{servlet:"driverImageServlet",action:"ajaxRotationDriverImageByImageId",parameter:"ImageId",parameter1:"angle"},
                callbacks: {
                    onProgress:(imageId,percent)=>{
                        console.log(`event:onProgress imageId=\${imageId} percent=\${percent}`);
                    },
                    onBuildPreview: (imageId)=>{
                        console.log(`event:onBuildPreview imageId=\${imageId}`);
                    },
                    onBuildCarouselItem:(imageId)=>{
                        console.log(`event:onBuildCarouselItem imageId=\${imageId}`);
                    },
                    onUploadSuccess:(imageId,meta)=>{

                        console.log(`event:onUploadSuccess imageId=\${imageId} meta=\${meta}`);
                        if (_metaMap instanceof Map) {
                            _metaMap.set(imageId, meta);
                        } else {
                            _metaMap = new Map([[imageId, meta]]);
                        }
                    },
                    onUploadAllSuccess:()=>{
                        console.log(`event:onUploadAllSuccess `);
                    },
                    onUploadError:(key,msg)=>{
                        console.log(`event:onUploadError key=\${key},msg=[\${msg}]`);
                    },
                    onMarkUploaded:(tempId,imageId)=>{
                        console.log(`event:onMarkUploaded tempId=\${tempId} imageId=\${imageId}`);
                    },
                    onRemoteSync:(driverSeq,metaMap)=>{
                        if (metaMap instanceof Map) {
                            for (let [hash, meta] of metaMap) {
                                console.log(`event:onRemoteSync driverSeq=\${driverSeq} imageId=\${meta.imageId}`);
                            }
                        }
                        _metaMap=metaMap;
                    },
                    onReleasePreview:()=>{
                        console.log(`event:onReleasePreview `);
                    },
                    onModalLoaded:()=>{
                        console.log(`event:onModalLoaded `);
                    },
                    onRemoteDeleteSuccess:(imageId)=>{
                        if (_metaMap.has(imageId)) {
                            _metaMap.delete(imageId);
                            //console.log(`remove hash=\${hash} in _metaMap OK`);
                            console.log(`event:onRemoteDeleteSuccess imageId=\${imageId}`);
                        }
                    },
                    onDeleteFailed:( imageId, err)=>
                    {
                        console.log(`event:onDeleteFailed  \${err}`);
                    },
                    onLocalDelete:(imageId)=>
                    {
                        console.log(`event:onLocalDelete  \${imageId}`);
                    }
                }
            };

            manager = new ImagePreviewManager(config);
            await manager.loadModal();
            manager.releaseImagePreview();
            console.log(`sync button click $driverseq=\${$driverseq.val()}`);
            manager.remoteSync($driverseq.val());
            // 你可以呼叫 remoteSync 去同步現有資料


            // 假設你有 <input type="file" multiple id="fileInput">
            $("#fileInput").on("change", async function () {
                for (const file of this.files) {
                    await manager.addImage(file);
                }
            });

            $("#uploadBtn").on("click", async () => {
                //let fineNo=$("#fineNo").val();
                console.log(`upload driverseq=\${$driverseq.val()}`);
                await manager.uploadAll($driverseq.val());
                alertBox.show("✅ 全部上傳完成！");
            });
            $('#syncBtn').on('click',function()
            {
                //let fineNo=$(`#fineNo`).val();
                console.log(`sync button click $driverseq=\${$driverseq.val()}`);
                manager.releaseImagePreview();
                manager.remoteSync($driverseq.val());
            });
            $('#clearBtn').on('click',function()
            {
                manager.releaseImagePreview();
                console.log('✅ 圖片清除完畢');
            });

            $(`#LineBtn`).on(`click`,function(){
                console.log('asdfg');
                //確認是否存在LineID

                fetch("lineUserServlet?action=ajaxGetUserWithDriverByDriverSeq&DriverSeq="+$driverseq.val(),
                    {
                        method: 'POST',
                        headers:
                            {
                                'Content-Type': 'application/json'
                            }
                    })
                    .then(response => response.json())
                    .then(result => {
                        if (result.success)
                        {
                            let lineUserDTO=result.data;
                            if(!lineUserDTO)
                            {
                                alert("使用者尚未連結LINE ID");
                            }else
                            {
                                console.log(`lineId=\${lineUserDTO.userId}`);
                                let message=`\${$("#driverName").val()},你於 \${$("#str_fineDate").val()} 駕駛 \${$("#carno").val()} 違反 \${$("#rule1").val()}條 \${$("#rule2").val()}項 \${$("#fineContact").val()}遭致罰款 \${$("#amount").val()}。罰單將近日進行轉移，請密切注意。`;
                                pushMultiImageMessageUnified(lineUserDTO.userId,message, _metaMap,false);
                            }
                            //alert("訊息發送成功！");
                            //console.log("訊息已紀錄，訊息ID:", result.data);
                        } else {
                            alert("訊息發送失敗：" + result.errorMessage);
                        }
                    })
                    .catch(error => {
                        console.error("發送時發生錯誤: ", error);
                        alert("發送時發生錯誤，請稍後重試！");
                    });
            });
            // 處理拖曳檔案
            $('#dropzoneLabel').on('dragover', function (e)
            {
                e.preventDefault();
                $(this).addClass('bg-light');
            }).on('dragleave drop', function (e)
            {
                e.preventDefault();
                $(this).removeClass('bg-light');
            }).on('drop', async function (e)
            {
                const dt = e.originalEvent.dataTransfer;
                const files = dt.files;

                for (const file of files)
                {
                    await manager.addImage(file);

                }
            });
        })();



        const inputMappings = [
            {
                input: $postcodeCode,
                dropdown: $autocomplete_list_postcodeCode,
                api: 'postcodeServlet',
                action: 'ajaxGetPostcodeByCodeLike',
                keyLength: 1,
                keyName: 'codePart',
                dataMapper:item => {
                    return autoMapItemToList(item, ['postcodeId', 'str_postcode','postcodeCode'],[]);// 顯示用的欄位
                },
                onSelect: ($btn) => {
                    console.log("postcode="+$btn.attr("data-postcodeCode"));
                    autoFillFromDataAttrs($btn, `#root`);
                }
            },
            {
                input: $specialCodeSearch,
                dropdown: $autocomplete_list_specialCodeSearch,
                api: 'driverServlet',
                action: 'ajaxGetDriversBySpecialCode',
                keyLength: 3,
                keyName: 'driverSC',
                dataMapper:mapDriverToList,
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-driverseq"));
                    autoFillFromDataAttrs($btn, `#root`);
                }
            },
            {
                input: $idSearch,
                dropdown: $autocomplete_list_idSearch,
                api: 'driverServlet',
                action: 'ajaxGetDriversByDriverId',
                keyLength: 3,
                keyName: 'key',
                dataMapper: mapDriverToList,
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-driverseq"));
                    autoFillFromDataAttrs($btn, `#root`);
                }
            }];
        inputMappings.forEach(({input, dropdown, api, action, keyLength, keyName, dataMapper, onSelect}) => {
            input.on('input keydown', (e) => {
                handleAutoCompleteDropdown(e, input, dropdown, {
                    apiUrl: api, action, keyName, keyLength, dataMapper, onSelect
                });
            });
        });


        $(".update-enabled").attr("disabled","disabled");

        toggleButtons(false, ["#submit-button"]);//將#submit-button禁能

        $("#first-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>driverServlet", "ajaxGetFirstDriverDTO", ajaxCallBack);
        });
        $("#previous-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>driverServlet", "ajaxGetPreviousDriverDTO", ajaxCallBack);
        });

        /*
         * 往下一筆的事件
         */
        $("#next-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            nextButtonHandler(SerialNo, "<%=basePath%>driverServlet", "ajaxGetNextDriverDTO", ajaxCallBack);
        });
        $("#last-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>driverServlet", "ajaxGetLastDriverDTO", ajaxCallBack);
        });
        // 更新按钮事件
        $("#update-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            updateMode=updateButtonHandler(this,updateMode,SerialNo,"<%=basePath%>driverServlet","ajaxGetDriverDTO",ajaxCallBack)
        });

        // 新增事件
        $("#add-button").click(function(){
            let SerialNo = $("#serialNoTitle").text().trim();
            appendMode=addButtonHandler(this,appendMode,SerialNo,"<%=basePath%>driverServlet","ajaxGetDriverDTO",ajaxCallBack);
        });
        $("#delete-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            deleteButtonHandler(SerialNo, "<%=basePath%>driverServlet", "ajaxDeleteDriverDTO", ajaxCallBack);

        });
        $("#submit-button").click(function ()
        {
            const serialNo = $("#serialNoTitle").text().trim();
            console.log("submit-button click,appendMode="+appendMode+",updateMode="+updateMode);
            if(appendMode===true) submitButtonHandler("0","driverseq", "driverServlet", "ajaxInsertDriver",SubmitInsertCallback);
            else if(updateMode===true)
            {
                submitButtonHandler(serialNo,"driverseq","driverServlet","ajaxUpdateDriver", SubmitUpdateCallback);
            }

        });
        //將資料填回表單中。
        let ajaxCallBack = function(result) {
            genericAjaxCallback(result, driverDTO => driverDTO.driverseq, (driverDTO)=>{
                // 這裡只在 data 存在時才會被呼叫，已通過防呆
                manager.releaseImagePreview();
                manager.remoteSync(driverDTO.driverseq);
                autoFillFormFields(driverDTO,$("#root"), ["fc-0", "fc-2"]);

            });
        };

        function SubmitUpdateCallback(result)
        {
            console.log("ajaxSubmitCallBack mode=" + result.mode + ", result=" + result.success);
            if (result.success === true)
            {
                toggleInputs(false);
                toggleButtons(false, ["#submit-button"]);
                $("#update-button").html("更新 <i class=\"ft-refresh-cw position-right\"></i>");

                alertBox.show("更新作業成功", 2);
                console.log("更新作業成功");
                updateMode=false;

            } else
            {
                alert("作業失敗 " + result.errorMessage);
                console.log("作業失敗 " + result.errorMessage);
            }

        }
        function SubmitInsertCallback(result)
        {
            console.log("ajaxSubmitCallBack mode=" + result.mode + ", result=" + result.success);
            if (result.success === true)
            {
                const driverSeq=result.data;
                toggleInputs(false);
                toggleButtons(false, ["#submit-button"]);
                alertBox.show("新增作業成功",3000);
                console.log(`新增作業成功 driverSeq=\${driverSeq}`);
                $("#add-button").html("新增 <i class=\"ft-refresh-cw position-right\"></i>");
                $("#serialNoTitle").text(driverSeq);
                appendMode=false;

            } else
            {
                alertBox.show(`作業失敗 $\{result.errorMessage}`, 2);
                console.log("作業失敗 " + result.errorMessage);
            }
        }

    });
</script>
<!-- END CUSTOM LEVEL JS-->


</body>
</html>