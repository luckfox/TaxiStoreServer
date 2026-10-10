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
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist.css">
    <link rel="stylesheet" type="text/css"
          href="<%= app_assetsPath %>/vendors/css/charts/chartist-plugin-tooltip.css">
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
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/alert/autoAlert.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css">
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
        <div class="content-header row">
            <div class="content-header-left col-md-8 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">車輛資料建檔</h3>
                <div class="row d-inline-block">
                    <div class=" mr-1">
                        <ol>
                            <li><h3 style="color:white"><span>流水編號:</span><span
                                    id="serialNoTitle">${requestScope.CarDTO.carseq}</span></h3>
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
                            <input id="serialNo" type=hidden name="serialNo" value="${requestScope.CarDTO.carseq}"/>
                            <div class="row">
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">原牌照號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="原牌照號碼" id="orlicense"
                                           name="orlicense"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.orlicense}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">*車號</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="車號" id="carno" name="carno"
                                           aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.carno}" disabled="disabled" required >
                                    <input type="text" class="form-control fc-1" placeholder="車號"
                                           id="carnoSearch" name="carnoSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-carnoSearch"/>
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">出廠年月</label>
                                    <input type="month" class="form-control update-enabled fc-0" placeholder="yyyy/mm" id="str_caryear"
                                           name="caryear"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.str_caryear}" disabled="disabled" >
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">廠牌</label>
                                    <input type="text" class="form-control update-enabled carbrand-select fc-0" placeholder="廠牌" id="carBrandSC"
                                           value="${requestScope.CarDTO.carBrandSC}" disabled="disabled" >
                                    <select class="input-group-text form-select update-enabled"
                                            id="carBrandName" disabled="disabled">
                                        <option selected>${requestScope.CarDTO.carBrandName}</option>
                                        <option sp="001" value="HONDA">HONDA</option>
                                        <option sp="002" value="TOYOTA">TOYOTA</option>
                                        <option sp="003" value="BMW">BMW</option>
                                    </select>
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-carBrandSC"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-1 mb-1">
                                    <label class="input-group-text">顏色</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="顏色" id="carcolor"
                                           name="carcolor"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.carcolor}"  disabled="disabled" >
                                </div>
                                <div class="input-group col-md-2 mb-1">
                                    <label class="input-group-text">排氣量</label>
                                    <input type="number" class="form-control update-enabled fc-0" placeholder="排氣量" id="pipeamount"
                                           name="pipeamount"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.pipeamount}"  disabled="disabled" >
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text ">引擎號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="引擎號碼" id="mechanicss"
                                           name="mechanicss" aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.mechanicss}"  disabled="disabled" >
                                    <input type="text" class="form-control fc-1" placeholder="引擎號碼"
                                           id="mechanicssSearch" name="mechanicssSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-mechanicssSearch"/>
                                </div>
                                <div class="input-group col-md-5 mb-1">
                                    <label class="input-group-text">車身號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="車身號碼" id="carlineno"
                                           name="carlineno"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.carlineno}"  disabled="disabled" >
                                    <input type="text" class="form-control fc-1" placeholder="車身號碼"
                                           id="carlinenoSearch" name="carlinenoSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-carlinenoSearch"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-3 mb-1">
                                    <%--<span class="input-group-text">*原發照日期</span>--%>
                                    <label for="str_shingledat" class="input-group-text">*原發照日</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy-mm-dd" id="str_shingledat"
                                           name="shingledat" value="2020-1-1" aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.str_shingledat}"  disabled="disabled" required >
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">繳銷日期</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/mm/dd" id="str_canceldate"
                                           name="canceldate" aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.str_canceldate}"  disabled="disabled" >
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">已付保證金</label>
                                    <input type="number" class="form-control update-enabled fc-0" placeholder="已付保證金"
                                           id="paid_security_deposit" name="paid_security_deposit"
                                            aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.paid_security_deposit}"  disabled="disabled" >
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text">預收保證金</label>
                                    <input type="number" class="form-control update-enabled fc-0" placeholder="預收保證金"
                                           id="pre_security_deposit" name="pre_security_deposit"
                                            aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.pre_security_deposit}"  disabled="disabled" >
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text">下次驗表日</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/mm/dd" id="str_cpdate"
                                           name="cpdate"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.str_cpdate}"  disabled="disabled" >
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text">指定驗車日</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/mm/dd" id="str_check_date"
                                           name="check_date"  aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.str_check_date}"  disabled="disabled" >
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text">上次驗車月</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="yyyy/mm/dd" id="str_okcar" name="okcar"
                                           aria-describedby="basic-addon1"
                                           value="${requestScope.CarDTO.str_okcar}"  disabled="disabled" >
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-12 mb-1">
                                    <label class="input-group-text">備註</label>
                                    <textarea rows="2" class="form-control update-enabled fc-0" placeholder="備註" id="remark" name="remark"
                                              disabled="disabled" >${requestScope.CarDTO.remark}</textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 col-md-12">
                                    <div id="collapse3" class="card-accordion">
                                        <div class="card bg-dark collapse-icon accordion-icon-rotate">
                                            <div class="card bg-dark ">
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCOne">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC1" aria-expanded="false"
                                                           aria-controls="collapseC1">
                                                        <h4><span class=" text-white bg-dark ">車輛相關證件</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC1" class="collapse" aria-labelledby="headingCOne">
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
                                            <div class="card bg-dark ">
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCTwo">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC2" aria-expanded="false"
                                                           aria-controls="collapseC2">
                                                        <h4><span class=" text-white bg-dark ">駕駛員相關資料</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC2" class="collapse" aria-labelledby="headingCTwo">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text">司機姓名</label>
                                                                <input type="text" class="form-control fc-0"
                                                                       placeholder="司機姓名" id="driverSC"
                                                                       name="driverSC"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.driverSC}"
                                                                       disabled="disabled" >
                                                                <select class="input-group-text form-select"
                                                                        id="driverName"  disabled="disabled" >
                                                                    <option selected>${requestScope.CarDTO.driverName}</option>
                                                                    <option value="1">張育瑋</option>
                                                                    <option value="2">劉國泰</option>
                                                                    <option value="3">張黃阿甘</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-driverSC"/>
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text ">身分證號</label>
                                                                <input type="text" class="form-control fc-0"
                                                                       placeholder="身分證號" id="driverID"
                                                                       name="driverID" pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.driverID}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text ">出生日期</label>
                                                                <input type="date" class="form-control fc-0"
                                                                       placeholder="出生日期" id="str_driverBirthday"
                                                                       name="driverBirthday"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_driverBirthday}"
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-4 mt-1">
                                                                <label class="input-group-text ">所屬車行</label>
                                                                <input type="text" class="form-control company-select update-enabled fc-0"
                                                                       placeholder="所屬車行" id="ownerSC"
                                                                       name="ownerSC"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.ownerSC}"
                                                                       disabled="disabled" >

                                                                <select class="input-group-text form-select update-enabled" id="ownerName" disabled="disabled">
                                                                    <option selected>${requestScope.CarDTO.ownerName}</option>
                                                                    <option value="1">勝順</option>
                                                                    <option value="2">大中原</option>
                                                                    <option value="3">祥德</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-ownerName"/>
                                                            </div>
                                                            <div class="input-group col-md-4 mt-1">
                                                                <label class="input-group-text">車主電話</label>
                                                                <input type="text" class="form-control fc-0"
                                                                       placeholder="車主電話" id="driverPhone"
                                                                       name="driverPhone"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.driverPhone}"
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-4 mt-1">
                                                                <label class="input-group-text">所屬公司</label>
                                                                <input type="text" class="form-control company-select update-enabled fc-0"
                                                                       placeholder="特別碼" id="ownerCompanySC"
                                                                       name="ownerCompanySC"
                                                                       aria-describedby="basic-addon1"
                                                                       disabled="disabled"
                                                                       value="${requestScope.CarDTO.ownerCompanySC}">
                                                                <select class="input-group-text form-select update-enabled" disabled="disabled" id="ownerCompanyName">
                                                                    <option selected>${requestScope.CarDTO.ownerCompanyName}</option>
                                                                    <option value="1">勝順</option>
                                                                    <option value="2">大中原</option>
                                                                    <option value="3">祥德</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-ownerCompanyName"/>
                                                            </div>
                                                            <div class="input-group col-md-4 mt-1">
                                                                <label class="input-group-text">公司電話</label>
                                                                <input type="text" class="form-control fc-0"
                                                                       placeholder="車主電話" id=""
                                                                       name=""
                                                                       aria-describedby="basic-addon1"
                                                                       value=""
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card">
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCThree">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC3" aria-expanded="false"
                                                           aria-controls="collapseC3">
                                                        <h4><span class=" text-white bg-dark ">貸款業務</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC3" class="collapse" aria-labelledby="headingCThree">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text pl-1 pr-1">貸款公司</label>
                                                                <input type="text" class="form-control update-enabled company-select fc-0"
                                                                       placeholder="貸款公司" id="accoSC" name="accoSC"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.accoSC}"
                                                                       disabled="disabled" >
                                                                <select class="input-group-text form-select update-enabled" id="accoName" disabled="disabled">
                                                                    <option selected>${requestScope.CarDTO.accoName}</option>
                                                                    <option value="1">和潤</option>
                                                                    <option value="2">中租</option>
                                                                    <option value="3">國泰</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-accoName"/>
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text  pl-1 pr-1">貸款起始日</label>
                                                                <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/MM/dd"
                                                                       id="str_accdate" name="accdate"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_accdate}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-4  mt-1">
                                                                <label class="input-group-text  pl-1 pr-1">貸款終止日</label>
                                                                <input type="date" class="form-control fc-0" placeholder="yyyy/MM/dd"
                                                                       id="str_enddate" name="enddate"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_enddate}"
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text update-enabled">期數</label>
                                                                <input type="number" class="form-control update-enabled fc-0" placeholder="期數"
                                                                       id="acccount" name="acccount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.acccount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text update-enabled">每期繳費</label>
                                                                <input type="number" class="form-control update-enabled fc-0"
                                                                       placeholder="每期繳費" id="payamount" name="payamount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.payamount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-3  mt-1">
                                                                <label class="input-group-text ">應繳總額</label>
                                                                <input type="number" class="form-control fc-0"
                                                                       placeholder="繳款總額" id="totalamount" name="totalamount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.totalamount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-3  mt-1">
                                                                <label class="input-group-text ">貸款總額</label>
                                                                <input type="number" class="form-control update-enabled fc-0"
                                                                       placeholder="貸款總額" id="acc_amount" name="acc_amount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.acc_amount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">毛利</label>
                                                                <input type="number" class="form-control fc-0" placeholder="毛利"
                                                                       id="stopamount" name="stopamount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.stopamount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
<%--                                            headingCFour--%>
                                            <div class="card">
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCFour">

                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC4" aria-expanded="false"
                                                           aria-controls="collapseC4">
                                                        <h4><span class=" text-white bg-dark ">保險業務</span></h4>
                                                    </label>

                                                </div>
                                                <div id="collapseC4" class="collapse" aria-labelledby="headingCFour">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <div class="input-group col-md-3  mt-1">
                                                                <label class="input-group-text bg-grey-blue">強制險</label>
                                                                <input type="text" class="form-control update-enabled company-select fc-0"
                                                                       placeholder="強制險公司" id="force_servSC" name="force_servSC"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.force_servSC}"
                                                                       disabled="disabled" >
                                                                <select class="input-group-text form-select update-enabled " id="force_servName" disabled="disabled">
                                                                    <option selected>${requestScope.CarDTO.force_servName}</option>
                                                                    <option value="1">和潤</option>
                                                                    <option value="2">中租</option>
                                                                    <option value="3">國泰</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-force_servName"/>
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">卡號</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="卡號"
                                                                       id="force_serv_no" name="force_serv_no"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.force_serv_no}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">起始日</label>
                                                                <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/MM/dd"
                                                                       id="str_force_serv_start_date" name="force_serv_start_date"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_force_serv_start_date}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">終止日</label>
                                                                <input type="date" class="form-control fc-0" placeholder="yyyy/MM/dd"
                                                                       id="str_force_serv_end_date" name="force_serv_end_date"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_force_serv_end_date}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">年費</label>
                                                                <input type="number" class="form-control update-enabled fc-0" placeholder="年費"
                                                                       id="force_serv_amount" name="force_serv_amount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.force_serv_amount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-3  mt-1">
                                                                <label class="input-group-text ">任意險</label>
                                                                <input type="text" class="form-control update-enabled company-select fc-0"
                                                                       placeholder="任意險公司" id=any_servSC" name="any_servSC"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.any_servSC}"
                                                                       disabled="disabled" >
                                                                <select class="input-group-text form-select update-enabled" id="any_servName" disabled="disabled">
                                                                    <option selected>${requestScope.CarDTO.any_servName}</option>
                                                                    <option value="1">和潤</option>
                                                                    <option value="2">中租</option>
                                                                    <option value="3">國泰</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-any_servName"/>
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text">卡號</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="卡號"
                                                                       id="any_serv_no" name="any_serv_no" aria-label=""
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.any_serv_no}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">起始日</label>
                                                                <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/mm/dd"
                                                                       id="str_any_serv_start_date" name="any_serv_start_date" aria-label=""
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_any_serv_start_date}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text">終止日</label>
                                                                <input type="date" class="form-control fc-0" placeholder="yyyy/mm/dd"
                                                                       id="any_serv_end_date" name="any_serv_end_date" aria-label=""
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.any_serv_end_date}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text ">年費</label>
                                                                <input type="number" class="form-control update-enabled fc-0" placeholder="金額"
                                                                       id="any_serv_amount" name="any_serv_amount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.any_serv_amount}"
                                                                       disabled="disabled" >
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-3  mt-1">
                                                                <label class="input-group-text ">聯保</label>
                                                                <input type="text" class="form-control update-enabled company-select fc-0"
                                                                       placeholder="聯保公司" id="union_servSC" name="union_servSC"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.union_servSC}"
                                                                       disabled="disabled" >
                                                                <select class="input-group-text form-select  update-enabled" id="union_servName" disabled="disabled">
                                                                    <option selected>${requestScope.CarDTO.union_servName}</option>
                                                                    <option value="1">和潤</option>
                                                                    <option value="2">中租</option>
                                                                    <option value="3">國泰</option>
                                                                </select>
                                                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-union_servName"/>
                                                            </div>

                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text">起始日</label>
                                                                <input type="date" class="form-control update-enabled fc-0" placeholder="yyyy/mm/dd"
                                                                       id="str_union_serv_start_date" name="union_serv_start_date"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_union_serv_start_date}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text">終止日</label>
                                                                <input type="date" class="form-control fc-0" placeholder="yyyy/mm/dd"
                                                                       id="str_union_serv_end_date" name="union_serv_end_date"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.str_union_serv_end_date}"
                                                                       disabled="disabled" >
                                                            </div>
                                                            <div class="input-group col-md-2  mt-1">
                                                                <label class="input-group-text">月費</label>
                                                                <input type="number" class="form-control update-enabled fc-0" placeholder="月費"
                                                                       id="union_serv_amount" name="union_serv_amount"
                                                                       aria-describedby="basic-addon1"
                                                                       value="${requestScope.CarDTO.union_serv_amount}"
                                                                       disabled="disabled" >
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
                                    <button type="button" id="previous-button" class="btn btn-warning">上一筆
                                        <i class="icon-arrow-left position-right"></i>
                                    </button>
                                    <button type="button" id="next-button" class="btn btn-warning">下一筆
                                        <i class="icon-arrow-right position-right"></i>
                                    </button>
                                    <button type="button" id="last-button" class="btn btn-warning">末筆<i
                                            class="ft-file-plus position-right"></i></button>
                                    <button type="button" id="add-button" class="btn btn-warning">新增
                                            <i class=" ft-plus-circle position-right"></i>
                                    </button>
                                    <button type="button" id="delete-button" class="btn btn-danger">刪除
                                        <i class="ft-x-circle position-right"></i>
                                    </button>
                                    <button type="button" id="update-button" class="btn btn-secondary">更新 <i
                                            class="ft-refresh-cw  position-right"></i></button>
                                    <button type="button" id="submit-button" class="btn btn-primary">送出 <i
                                            class="ft-upload  position-right"></i></button>
                                    <button type="button" id="test-button" class="btn btn-dark">測試 <i
                                            class="ft-upload  position-right"></i></button>
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
<script type="text/javascript" src="<%= assetsPath %>/js/alert/autoAlert.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/BasePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/CarouselRotationManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImagePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImageUploader.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/buttonControl.js?<%= version %>"></script>
<script type="text/javascript">
$(function ()
{
    console.log("createCarDB.jsp");
    $("#form").attr("class", "open");
    $("#createCarDB").attr("class", "active");


    const $carseq=$("#serialNoTitle");
    console.log(`carseq= \${$("#serialNoTitle").text()}`);
    console.log(`carseq= \${$("#serialNo").val()}`);
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
            carouselTag: carouselTag,               // Carousel Modal內部(modal-body)必須要有的Tag，用以來展示Carousel 圖片之區域。
            carouselModalTag: carouselModalTag,     // Carousel Modal在modal必須要有的Tag，用來確認是否為合法的Carousel modal。
            modalContentPath: carouselModalHTML,    // Carousel Modal HTML所存放之檔名與路徑
            modalContainer: $module_container,      // Modal掛載容器，也就是
            imagesMeta:{servlet:"carImageServlet",action:"ajaxGetImageMetasByCarSeq",parameter:"carseq"},  //取得imagesMeta
            imageData:{servlet:"carImageServlet",action:"ajaxGetImageByImageId",parameter:"ImageId"},      //取得imageId圖像資料
            imageDelete:{servlet:"carImageServlet",action:"ajaxDeleteImageByImageId",parameter:"ImageId"}, //刪除某個ImageId的圖像資料
            imageUpload:{servlet:"carImageServlet",action:"ajaxUploadCarImage",parameter:"carseq"},
            imageRotation:{servlet:"carImageServlet",action:"ajaxRotationCarImageByImageId",parameter:"ImageId"},    //圖片選轉90度

            callbacks: {
                onProgress:(key,percent)=>{
                    console.log(`event:onProgress key=\${key} percent=\${percent}`);
                },
                onBuildPreview: (key)=>{
                    console.log(`event:onBuildPreview key=\${key}`);
                },
                onBuildCarouselItem:(key)=>{
                    console.log(`event:onBuildCarouselItem key=\${key}`);
                },
                onUploadSuccess:(key,meta)=>{

                    console.log(`event:onUploadSuccess key=\${key} meta=\${meta}`);
                    if (_metaMap instanceof Map) {
                        _metaMap.set(key, meta);
                    } else {
                        _metaMap = new Map([[key, meta]]);
                    }
                },
                onUploadAllSuccess:()=>{
                    console.log(`event:onUploadAllSuccess `);
                },
                onRemoteSync:(carSeq,metaMap)=>{
                    if (metaMap instanceof Map) {
                        for (let [key, meta] of metaMap) {
                            console.log(`event:onRemoteSync key=\${key} imageId=\${meta.imageId}`);
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
                onRemoteDeleteSuccess:(key)=>{
                    if (_metaMap.has(key)) {
                        _metaMap.delete(key);
                        console.log(`remove hash=\${key} in _metaMap OK`);
                        console.log(`event:onRemoteDeleteSuccess key=\${key}`);
                    }
                },
                onDeleteFailed:( key, err)=>
                {
                    console.log(`event:onDeleteFailed  \${err}`);
                },
                onLocalDelete:(key)=>
                {
                    console.log(`event:onLocalDelete  \${key}`);
                }
            }
        };

        manager = new ImagePreviewManager(config);
        await manager.loadModal();
        manager.releaseImagePreview();

        manager.remoteSync($carseq.text());
        // 你可以呼叫 remoteSync 去同步現有資料


        // 假設你有 <input type="file" multiple id="fileInput">
        $("#fileInput").on("change", async function () {
            for (const file of this.files) {
                await manager.addImage(file);
            }
        });

        $("#uploadBtn").on("click", async () => {
            //let fineNo=$("#fineNo").val();
            console.log(`upload $carseq=\${$carseq.text()}`);
            await manager.uploadAll($carseq.text());
            alertBox.show("✅ 全部上傳完成！");
        });
        $('#syncBtn').on('click',function()
        {

            console.log(`sync button click carseq=[\${$carseq.text()}]`);
            manager.releaseImagePreview();
            manager.remoteSync($carseq.text());
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
                        alertBox.show("訊息發送成功！");
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

    $("#test-button").click(function()
    {
        console.log("date="+$("#str_caryear").val());
    });

    let tmpSerialNo = ""; // 保存临时的序列号
    let updateMode = false; // 更新模式标志
    let appendMode = false; // 新增模式标志

    let $carnoSearch=$('#carnoSearch');
    let $mechanicssSearch = $('#mechanicssSearch');
    let $carlinenoSearch = $(`#carlinenoSearch`);

    const $autocomplete_list_carnoSearch = $('#autocomplete-list-carnoSearch');
    const $autocomplete_list_mechanicssSearch = $('#autocomplete-list-mechanicssSearch');
    const $autocomplete_list_carlinenoSearch = $('#autocomplete-list-carlinenoSearch');

    const inputMappings = [
        {
            input: $carnoSearch,
            dropdown: $autocomplete_list_carnoSearch,
            api: 'carServlet',
            action: 'ajaxGetCarDTOsByCarNoLike',
            keyLength: 3,
            keyName: 'carNoPart',
            dataMapper:item =>{
                let row= autoMapItemToList(item, ['carno', 'ownerName'],[`carseq`],[]);// 顯示用的欄位
                //console.log(`\${row}`);
                return row;
             },
            onSelect: ($btn) => {
                $("#serialNoTitle").text($btn.attr("data-carseq"));
                autoFillFromDataAttrs($btn, `#root`);
            }
        },
        {
            input: $mechanicssSearch,
            dropdown: $autocomplete_list_mechanicssSearch,
            api: 'carServlet',
            action: 'ajaxGetCarDTOsByMechanicssLike',
            keyLength: 4,
            keyName: 'MechanicssPart',
            dataMapper:item =>{
                let row= autoMapItemToList(item, ['carno', 'ownerName'],[`carseq`],[]);// 顯示用的欄位
                //console.log(`\${row}`);
                return row;
            },
            onSelect: ($btn) => {
                $("#serialNoTitle").text($btn.attr("data-carseq"));
                autoFillFromDataAttrs($btn, `#root`);
            }
        },
        {
            input: $carlinenoSearch,
            dropdown: $autocomplete_list_carlinenoSearch,
            api: 'carServlet',
            action: 'ajaxGetCarDTOsByCarLineNoLike',
            keyLength: 4,
            keyName: 'CarLineNoPart',
            dataMapper:item =>{
                let row= autoMapItemToList(item, ['carno', 'ownerName'],[`carseq`],[]);// 顯示用的欄位
                //console.log(`\${row}`);
                return row;
            },
            onSelect: ($btn) => {
                $("#serialNoTitle").text($btn.attr("data-carseq"));
                autoFillFromDataAttrs($btn, `#root`);
            }
        }
    ];
    inputMappings.forEach(({input, dropdown, api, action, keyLength, keyName, dataMapper, onSelect}) => {
        input.on('input keydown', (e) => {
            handleAutoCompleteDropdown(e, input, dropdown, {
                apiUrl: api, action, keyName, keyLength, dataMapper, onSelect
            });
        });
    });



    //取得內定值並保存
    var org_border_color=$($("input[required]")[0]).css("border-color");
    var org_border_width=$($("input[required]")[0]).css("border-width");

    console.log("org_border_color="+org_border_color);
    console.log("org_border_width="+org_border_width);

    $(".fc-0").attr("disabled", "disabled");
    toggleButtons(false, ["#submit-button"]);




    $("#first-button").click(function () {
        let SerialNo = $("#serialNoTitle").text().trim();
        previousButtonHandler(SerialNo, "<%=basePath%>carServlet", "ajaxGetFirstCarDTO", ajaxCallBack);
    });
    /*
     * 往上一筆的事件
     */
    $("#last-button").click(function () {
        let SerialNo = $("#serialNoTitle").text().trim();
        previousButtonHandler(SerialNo, "<%=basePath%>carServlet", "ajaxGetLastCarDTO", ajaxCallBack);
    });
    $("#previous-button").click(function(){
        const serialNo = $(`#serialNoTitle`).text().trim();
        previousButtonHandler(serialNo,"<%=basePath%>carServlet","ajaxGetPreviousCarDTO",ajaxCallBack);
    });

    $("#next-button").click(function(){
        const serialNo = $(`#serialNoTitle`).text().trim();
        nextButtonHandler(serialNo,"<%=basePath%>carServlet","ajaxGetNextCarDTO",ajaxCallBack);
    });

    $("#update-button").click(function()
    {
        let SerialNo = $("#serialNoTitle").text().trim();
        updateMode=updateButtonHandler(this,updateMode,SerialNo,"<%=basePath%>carServlet","ajaxGetCarDTO",ajaxCallBack)
    });
    //append record
    //新增==>保存新增時所存在的serialNo
    //取消新增==>讀取保留的 serialNo,去後台抓取資料
    $("#add-button").click(function()
    {
        let SerialNo = $("#serialNoTitle").text().trim();
        appendMode=addButtonHandler(this,appendMode,SerialNo,"<%=basePath%>carServlet","ajaxGetCarDTO",ajaxCallBack);
    });
    //將資料填回表單中。
    let ajaxCallBack = function(result) {
        genericAjaxCallback(result, carDTO => carDTO.carseq, (carDTO)=>{
            manager.releaseImagePreview();
            manager.remoteSync(carDTO.carseq);
            autoFillFormFields(carDTO,$("#root"), ["fc-0", "fc-2"]);
        });

    };

    $("#submit-button").click(function()
    {
        const serialNo = $("#serialNoTitle").text().trim();
        console.log(`submit-button click,appendMode=${appendMode},updateMode=${updateMode}`);
        if(appendMode===true) submitButtonHandler("0", "carseq","<%=basePath%>carServlet","ajaxUpdateCar", ajaxSubmitCallBack);
        else if(updateMode===true) submitButtonHandler(serialNo, "carseq","<%=basePath%>carServlet","ajaxUpdateCar", ajaxSubmitCallBack);

    });
    let ajaxSubmitCallBack= SubmitCallback({
        setAppendMode: (val) => appendMode = val,
        setUpdateMode: (val) => updateMode = val
    });

});


</script>
<!-- END CUSTOM LEVEL JS-->


</body>
</html>