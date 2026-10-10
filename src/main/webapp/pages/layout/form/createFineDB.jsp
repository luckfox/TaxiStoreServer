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
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/ImagePreviewManager.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css?<%= version %>">
    <!-- END Custom CSS-->
    <style>

    </style>

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
            <div class="content-header-left col-md-12 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">罰單資料建檔</h3>
                <div class="row d-inline-block">
                    <div class=" mr-1">
                        <ol>
                            <li>
                                <h3 class="content-header-title mb-0 d-inline-block"><span>建檔日期：</span><span id="createDate">${requestScope.fineDTO.str_createdate}</span></h3>
                                <h3 class="content-header-title mb-0 d-inline-block"><span>流水編號:</span><span id="serialNoTitle">${requestScope.fineDTO.fineSeq}</span></h3>
                            </li>
                        </ol>

                    </div>
                </div>
            </div>
            <div class="col-xl-12 col-lg-12">
                <div class="card bg-dark text-white">
                    <div class="card-content collapse show">
                        <div class="card-body">
                            <input id="form-action" type=hidden name="action" value=""/>
                            <input id="serialNo" type=hidden name="serialNo" value="${requestScope.fineDTO.fineSeq}"/>
                            <input id="situationId" type=hidden class="update-enabled" name="situationId" value="${requestScope.fineDTO.situationId}"/>

                            <div class="row">
                                <div class="input-group col-md-5 mb-1">
                                    <label class="input-group-text" for="fineNo">*罰單號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="罰單號碼"
                                           id="fineNo" name="fineNo" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.fineNo}" required>
                                    <input type="text" class="form-control fc-1" placeholder="罰單號碼"
                                           id="fineNoSearch" name="fineNoSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-fineNoSearch"/>
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text " for="tactName">被通知人</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="公司簡稱"
                                           id="tactName" name="tactName" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.tactName}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-3 mb-1">
                                    <label class="input-group-text " for="amount">罰單金額</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="罰單金額"
                                           id="amount" name="amount" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.amount}" disabled="disabled">
                                </div>

                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="str_fineDate">罰單開立日期</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="罰單開立日期"
                                           id="str_fineDate" name="str_fineDate" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.str_fineDate}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="fineHH">罰單開立時間</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="時"
                                           id="fineHH" name="fineHH" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.fineHH}" disabled="disabled">
                                    <input type="text" class="form-control update-enabled fc-0" id="fineNN" name="fineNN"  placeholder="分"
                                           value="${requestScope.fineDTO.fineNN}">

                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="str_fineClDate">到案日期</label>
                                    <input type="date" class="form-control update-enabled fc-0" placeholder="到案日期"
                                           id="str_fineClDate" name="str_fineClDate" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.str_fineDate}" disabled="disabled">
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="rule1">舉發法條</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="條"
                                           id="rule1" name="rule1" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.rule1}" disabled="disabled">
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="項"
                                           id="rule2" name="rule2" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.rule2}" disabled="disabled">
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="款"
                                           id="rule3" name="rule3" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.rule3}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text cursor-pointer-hover" id="labelCarNo" for="carno">車號</label>
                                    <input id="carseq" type=hidden name="carseq" value="${requestScope.fineDTO.carseq}"/>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="車號"
                                           id="carno" name="carno" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.carno}" disabled="disabled">

                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-carno"/>
                                </div>

                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text cursor-pointer-hover" id="labelDriverName" for="driverName">駕駛人</label>
                                    <input id="driverseq" type=hidden name="driverseq" value="${requestScope.fineDTO.driverseq}"/>
                                    <input type="text" class="form-control  fc-0" placeholder="駕駛人"
                                           id="driverName" name="driverName" aria-describedby="basic-addon1"
                                           value="${requestScope.fineDTO.driverName}" disabled="disabled">
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
                                                        <h4><span class=" text-white bg-dark ">罰單內容</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC1" class="collapse" aria-labelledby="headingCOne">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <div class="input-group col-md-6 mt-1">
                                                                <label class="input-group-text" for="fineCreate">違規地點</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="違規地點"
                                                                       id="fineCreate" name="fineCreate" aria-describedby="basic-addon1"
                                                                       value="${requestScope.fineDTO.fineCreate}" disabled="disabled">
                                                            </div>
                                                            <div class="input-group col-md-6 mt-1">
                                                                <label class="input-group-text" for="banno">到案地點</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="到案地點"
                                                                       id="banno" name="banno" aria-describedby="basic-addon1"
                                                                       value="${requestScope.fineDTO.fineAdd}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-group col-md-12 mt-1">
                                                                <label class="input-group-text" for="fineContact">違規事實</label>
                                                                <input type="text" class="form-control update-enabled fc-0" placeholder="違規事實"
                                                                       id="fineContact" name="fineContact" aria-describedby="basic-addon1"
                                                                       value="${requestScope.fineDTO.fineContact}" disabled="disabled">
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCTwo">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC2" aria-expanded="false"
                                                           aria-controls="collapseC2">
                                                        <h4><span class=" text-white bg-dark ">罰單移轉</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC2" class="collapse" aria-labelledby="headingCTwo">
                                                    <div class="card bg-dark">
                                                        <div class="row">
                                                            <input id="passDate" type="hidden" name="passDate" value="${requestScope.fineDTO.str_passDate}"/>
                                                            <input id="payStatus" type=hidden name="payStatus" value="${requestScope.fineDTO.payStatus}"/>
                                                            <input id="fineStatus" type=hidden name="fineStatus" value="${requestScope.fineDTO.fineStatus}"/>
                                                            <div class="input-group col-md-6 mt-1">
                                                                <button id="accountBtn" class="btn btn-primary w-100">罰單入帳</button>
                                                                <label class="input-group-text" for="accountDateTime">入帳時間</label>
                                                                <input type="text" class="form-control  fc-0" placeholder="入帳時間"
                                                                       id="accountDateTime" name="accountDateTime" aria-describedby="basic-addon1"
                                                                       value="${requestScope.fineDTO.fineAdd}" disabled="disabled">
                                                            </div>
                                                            <div class="input-group col-md-6 mt-1">
                                                                <button id="transferBtn" class="btn btn-primary w-100">移轉司機</button>
                                                                <label class="input-group-text" for="transferDateTime">移轉時間</label>
                                                                <input type="text" class="form-control fc-0" placeholder="移轉時間"
                                                                       id="transferDateTime" name="transferDateTime" aria-describedby="basic-addon1"
                                                                       value="${requestScope.fineDTO.fineAdd}" disabled="disabled">

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-header bg-dark" style="padding:5px" id="headingCThree">
                                                    <label class="btn collapsed mb-0" data-toggle="collapse"
                                                           data-target="#collapseC3" aria-expanded="false"
                                                           aria-controls="collapseC3">
                                                        <h4><span class=" text-white bg-dark ">罰單照片</span></h4>
                                                    </label>
                                                </div>
                                                <div id="collapseC3" class="collapse" aria-labelledby="headingCThree">
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
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
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
<div id="modalContainer"></div>
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of footer-->
<%@ include file="../../footer.jsp" %>

<!--//end of footer-->

<!-- 確保 jQuery 先載入 -->
<script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<!-- BEGIN VENDOR JS-->
<script src="<%= app_assetsPath %>/vendors/js/vendors.min.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->

<!-- END PAGE VENDOR JS-->
<!-- BEGIN CHAMELEON  JS-->
<script src="<%= app_assetsPath %>/js/core/app-menu.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/js/core/app.js" type="text/javascript"></script>
<!-- END CHAMELEON  JS-->
<!-- BEGIN PAGE LEVEL JS-->

<!-- END PAGE LEVEL JS-->
<!-- BEGIN CUSTOM LEVEL JS-->
<script type="text/javascript" src="<%= assetsPath %>/js/alert/autoAlert.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%= app_assetsPath %>/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/editableHelper.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/buttonControl.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/LinePushMessage.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/addImagePreviewWithHash.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/modal/showModal.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/fineImageUtils/fineImageUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/hashUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/BasePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImagePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/CarouselRotationManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImageUploader.js?<%= version %>"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/dropzone@5.9.3/dist/min/dropzone.min.css" />
<script src="https://cdn.jsdelivr.net/npm/dropzone@5.9.3/dist/min/dropzone.min.js"></script>

<script type="text/javascript">
    $( function () {
        const $serialNoTitle=$(`#serialNoTitle`);
        let updateMode = false; // 更新模式标志
        let appendMode = false; // 新增模式标志
        let $uploadBtn = $(`#uploadBtn`);
        let $syncBtn = $(`#syncBtn`);
        let $clearBtn = $(`#clearBtn`);
        const $situationId = $(`#situationId`);
        const $accountBtn = $(`#accountBtn`);
        const $transferBtn = $(`#transferBtn`);
        const $amount = $(`#amount`);
        const $payStatus = $(`#payStatus`);
        const $fineStatus = $(`#fineStatus`);
        const $fineContact = $(`#fineContact`);
        const $passDate = $(`#passDate`);
        const $accountDateTime = $(`#accountDateTime`);
        const $transferDateTime = $(`#transferDateTime`);

        let _metaMap=new Map();
        let m_simpleModalPath= "/pages/layout/modals/simple.html";
        let m_driverInfoPath = "/pages/layout/modals/driverInfo.html";
        let m_carInfoPath = "/pages/layout/modals/carInfoEditor.html";
        let m_companyInfoPath = "/pages/layout/modals/companyInfo.html";
        let url_ImageMetas ="fineImageServlet?action=ajaxGetImageMetasByFineNo";


        const previewTag="#previewArea";
        const carouselTag="#carouselPreview";
        const carouselModalTag="#imageCarouselModal";
        const carouselModalHTML="/pages/layout/modals/ImageCarousel.html?_="+ new Date().getTime();
        const $module_container=$("#modalContainer");


        let manager = null;
        $transferBtn.text("移轉給司機");
        if($fineStatus.val()=="P")//已經移轉至帳務系統，則transferBtn和accountBtn disabled
        {
            $transferBtn.attr("disabled","disabled");
            $accountBtn.attr("disabled","disabled");
            $accountDateTime.val($passDate.val());
        }else if($fineStatus.val()=="N")//已經移轉給司機，則accountBtn disabled
        {

            $accountBtn.attr("disabled","disabled");
            $transferDateTime.val($passDate.val());
            $transferBtn.text("移轉回車行");
        }
        /*
            因為動態modal屬於非同步作業，所以不能使用原本的constructor，所以重新寫一個支援async的靜態方法create，然後在create內部，再重新
            new ImagePreviewManager。
         */
        (async () =>
        {
            const config = {
                previewTag: previewTag,                 // 預覽區容器
                carouselTag: carouselTag,               // Carousel 內部
                carouselModalTag: carouselModalTag,     // Modal 外層
                modalContentPath: carouselModalHTML,    // Modal HTML路徑
                modalContainer: $module_container,      // Modal掛載容器
                imagesMeta:{servlet:"fineImageServlet",action:"ajaxGetImageMetasByFineNo",parameter:"fineNo"},  //取得imagesMeta
                imageData:{servlet:"fineImageServlet",action:"ajaxGetImageByImageId",parameter:"ImageId"},      //取得image圖像資料
                imageDelete:{servlet:"fineImageServlet",action:"ajaxDeleteImageByImageId",parameter:"ImageId"},
                imageUpload:{servlet:"fineImageServlet",action:"ajaxUploadFineImage",parameter:"fineNo"},
                imageRotation:{servlet:"fineImageServlet",action:"ajaxRotationFineImageByImageId",parameter:"ImageId"},    //圖片選轉90度

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
                    onUploadSuccess:(hash,imageId)=>{

                        console.log(`event:onUploadSuccess hash=\${hash} imageId=\${imageId}`);
                        if (_metaMap instanceof Map) {
                            _metaMap.set(hash, meta);
                        } else {
                            _metaMap = new Map([[hash, meta]]);
                        }
                    },
                    onUploadAllSuccess:()=>{
                        console.log(`event:onUploadAllSuccess `);
                    },
                    onRemoteSync:(fineNo,metaMap)=>{
                        if (metaMap instanceof Map) {
                            for (let [hash, meta] of metaMap) {
                                console.log(`event:onRemoteSync hash=\${hash} imageId=\${meta.imageId}`);
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
                    onRemoteDeleteSuccess:(hash)=>{
                        if (_metaMap.has(hash)) {
                            _metaMap.delete(hash);
                            console.log(`remove hash=\${hash} in _metaMap OK`);
                            console.log(`event:onRemoteDeleteSuccess hash=\${hash}`);
                        }
                    },
                    onDeleteFailed:( hash, err)=>
                    {
                        console.log(`event:onDeleteFailed  \${err}`);
                    },
                    onLocalDelete:(hash)=>
                    {
                        console.log(`event:onLocalDelete  \${hash}`);
                    }
                }
            };

            manager = new ImagePreviewManager(config);
            await manager.loadModal();
            manager.releaseImagePreview();
            manager.remoteSync($("#fineNo").val());
            // 你可以呼叫 remoteSync 去同步現有資料


            // 假設你有 <input type="file" multiple id="fileInput">
            $("#fileInput").on("change", async function () {
                for (const file of this.files) {
                    await manager.addImage(file);
                }
            });

            $("#uploadBtn").on("click", async () => {
                let fineNo=$("#fineNo").val();
                await manager.uploadAll(fineNo);
                alertBox.show("✅ 全部上傳完成！");
            });
            $('#syncBtn').on('click',function()
            {
                let fineNo=$(`#fineNo`).val();
                console.log(`sync button click fineNo=\${fineNo}`);
                manager.releaseImagePreview();
                manager.remoteSync(fineNo);
            });
            $('#clearBtn').on('click',function()
            {
                manager.releaseImagePreview();
                console.log('✅ 圖片清除完畢');
            });

            $(`#LineBtn`).on(`click`,function(){
                console.log('asdfg');
                //確認是否存在LineID
                const $driverseq=$("#driverseq");
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

        let $fineNoSearch=$('#fineNoSearch');
        const $carnoSearch=$(`#carnoSearch`);
        const $carno=$(`#carno`);
        let $dropdown_fineNoSearch=$(`#autocomplete-list-fineNoSearch`);
        let $dropdown_carnoSearch=$(`#autocomplete-list-carnoSearch`);
        let $dropdown_carno=$(`#autocomplete-list-carno`);
        console.log("createFineDB.jsp");

        $("#form").attr("class", "open");
        $("#createFineDB").attr("class", "active");


        $(".fc-0").attr("disabled", "disabled");

        const $listWindow = $("#listWindow"); // 静态列表窗口
        toggleButtons(false, ["#submit-button"]);


        const inputMappings = [
            {
                input: $fineNoSearch,
                dropdown: $dropdown_fineNoSearch,
                api: 'fineServlet',
                action: 'ajaxGetFineDTOByFineNoLike',
                keyLength: 2,
                keyName:'fineNo',
                dataMapper:item =>{
                    console.log(`$situationId.val=\${$("#situationId").val()}`);
                    let row= autoMapItemToList(item, ['fineNo', 'carno',`driverName`,`situationId`],[`fineSeq`],[]);// 顯示用的欄位
                    //console.log(`\${row}`);
                    return row;
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-fineSeq"));
                    //autoFillFromDataAttrs($btn, `#root`);
                    autoFillFormFields($btn, `#root`);
                }
            },
            {
                input: $carno,
                dropdown: $dropdown_carno,
                api: 'situationServlet',
                action: 'ajaxGetSituationDTOByCarnoLike',
                keyLength: 2,
                keyName:'key',
                //透過後端(situationServlet&aciton=ajaxGetSituationDTOByCarnoLike 取回的資料。
                dataMapper:item =>{
                    console.log("item 原始資料：", item); // ←←← 加這行！
                    console.log(`$situationId.val=\${$("#situationId").val()}`);
                    let row= autoMapItemToList(item, ['carno',`driverName`,`situationId`],[`fineSeq`],[]);// 顯示用的欄位
                    //console.log(`\${row}`);
                    return row;
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-fineSeq"));
                    //autoFillFromDataAttrs($btn, `#root`);
                    autoFillFormFields($btn, `#root`);
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

        // 點擊其他地方隱藏下拉
        $(document).on('click', function (e)
        {
            console.log(`document click`);
            if (!$(e.target).closest($fineNoSearch).length && !$(e.target).closest($dropdown_fineNoSearch).length) {
                $dropdown_fineNoSearch.removeClass('show');
            }
        });
        //移轉到應收帳款
        $accountBtn.click(function()
        {
            const servlet='fineServlet';
            const currentTime = new Date().toISOString(); // 例如：2025-06-29T16:38:12.345Z
            $.getJSON(servlet, {
                action: 'ajaxUpdateFine',
                fineSeq:$serialNoTitle.text(),
                fineStatus:`P`,
                passDate:currentTime,
                payStatus:`P`
            }, function (result)
            {
                if (result.success) {
                    $accountBtn.attr("disabled","disabled");
                    $transferBtn.attr("disabled","disabled");
                    //1 產生一個RecvFrom
                    const servlet=`recvformServlet`;
                    $.getJSON(servlet,
                        {
                            action: `ajaxCreateAccount`,
                            userName:`SYSTEM`,
                            userNo:`SYSTEM`,
                            situationId:$situationId.val(),
                            type:"P",
                            remark:"123456",
                            summary:$fineContact.val(),
                            amount:$amount.val(),

                        },function(result){
                            if (result.success)
                            {
                                alert(`account fine success`);
                            }
                        });
                    //2 產生一個RecvEntry
                    alert("update ok");
                } else {
                    alert("update failed: " + (result.message || '未知錯誤'));
                }
            });
        });

        /*
            將罰單移轉司機/轉回車行。
         */
        $transferBtn.click(function()
        {
            let restStatus="";
            const servlet='fineServlet';
            const currentTime = new Date().toISOString(); // 例如：2025-06-29T16:38:12.345Z
            if($fineStatus.val()=="N")//目前已經移轉到司機
            {
                restStatus="";
            }else
            {
                restStatus="N";
            }
            $.getJSON(servlet, {
                action: 'ajaxUpdateFine',
                fineSeq:$serialNoTitle.text(),
                fineStatus:restStatus,
                passDate:currentTime,
                payStatus:restStatus
            }, function (result)
            {
                if (result.success)
                {
                    if(restStatus=="")
                    {
                        $transferBtn.text("移轉給司機");
                        $fineStatus.val("");
                        $payStatus.val("");
                        $transferDateTime.val("");
                    }else
                    {
                        $transferBtn.text("移轉回車行");
                        $fineStatus.val("N");
                        $payStatus.val("N");
                        $transferDateTime.val(currentTime);
                    }
                    $accountBtn.attr("disabled","disabled");
                    alert("update ok");
                } else {
                    alert("update failed: " + (result.message || '未知錯誤'));
                }
            });
        });

        // 更新按钮事件
        $("#update-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            updateMode=updateButtonHandler(this,updateMode,SerialNo,"fineServlet","ajaxGetFineDTO",ajaxCallBack)
            if(updateMode) $('#collapseC3 :input').prop('disabled', true);
            else $('#collapseC3 :input').prop('disabled', false);
        });

        // 新增事件
        $("#add-button").click(function(){
            let SerialNo = $("#serialNoTitle").text().trim();
            appendMode=addButtonHandler(this,appendMode,SerialNo,"fineServlet","ajaxGetFineDTO",ajaxCallBack);
            if(appendMode)
            {
                $('#collapseC3 :input').prop('disabled', true);
                manager.releaseImagePreview();
                manager.remoteSync(fineNo);
            }
            else {
                $('#collapseC3 :input').prop('disabled', false);
            }
        });
        /*
         * 刪除的事件
         */
        $("#delete-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            deleteButtonHandler(SerialNo, "<%=basePath%>fineServlet", "ajaxDeleteFine", ajaxCallBack);

        });

        $("#first-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>fineServlet", "ajaxGetFirstFineDTO", ajaxCallBack);
        });
        /*
         * 往上一筆的事件
         */
        $("#previous-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>fineServlet", "ajaxGetPreviousFineDTO", ajaxCallBack);
        });

        /*
         * 往下一筆的事件
         */
        $("#next-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            nextButtonHandler(SerialNo, "<%=basePath%>fineServlet", "ajaxGetNextFineDTO", ajaxCallBack);

        });
        $("#last-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>fineServlet", "ajaxGetLastFineDTO", ajaxCallBack);
        });

        $("#submit-button").click(function ()
        {
            const serialNo = $("#serialNoTitle").text().trim();
            console.log("submit-button click,appendMode="+appendMode+",updateMode="+updateMode);
            //注意：只有class帶有update-enabled的元素，才會被更新到後端。
            if(appendMode===true) submitButtonHandler("0","fineSeq", "fineServlet", "ajaxUpdateFine",ajaxSubmitCallBack);
            else if(updateMode===true) submitButtonHandler(serialNo,"fineSeq","fineServlet","ajaxUpdateFine", ajaxSubmitCallBack);
            $('#collapseC3 :input').prop('disabled', false);
        });
        $("#labelCarNo").click(function() {
            let url=`carServlet?action=ajaxGetCarDTO&serialNo=`+$("#carseq").val();
            show_Modal($module_container,m_carInfoPath,url);
        });

        $("#labelDriverName").click(function (){
            console.log(`labelDriverName clicked!`);
            let url=`driverServlet?action=ajaxGetDriverDTO&serialNo=`+$("#driverseq").val();
            show_Modal($module_container,m_driverInfoPath,url);

        });
        let ajaxSubmitCallBack= SubmitCallback({
            setAppendMode: (val) => appendMode = val,
            setUpdateMode: (val) => updateMode = val
        });

//=================================================================================
        //
        let ajaxCallBackRefreshCustomerCategory= function (jsonObj)
        {
            console.log("ajaxCallBackRefreshCustomerCategory");
            if (jsonObj.success === false) return 0;
            RefreshCustomerCategory(jsonObj.data);
        }
        function RefreshCustomerCategory(CustomerCategory)
        {
            console.log("CustCateID="+CustomerCategory.CustCateID+":CustCateName="+CustomerCategory.CustCateName+":customerCategoryCode="+CustomerCategory.CustCateCode);
            $("#customerCategory").val(CustomerCategory.CustCateID);
            $("#customerCategoryName").html(CustomerCategory.CustCateName);
            $("#customerCategoryCode").val(CustomerCategory.CustCateCode);
        }


//=================================================================================

        //根據序號，將資料填回表單中。
        let ajaxCallBack = function(result) {
            genericAjaxCallback(result, fineDTO => fineDTO.fineSeq, (fineDTO) => {
                // 這裡只在 data 存在時才會被呼叫，已通過防呆
                manager.releaseImagePreview();
                manager.remoteSync(fineDTO.fineNo);
                autoFillFormFields(fineDTO,$("#root"), ["fc-0", "fc-2"]);
                console.log(`fineSeq=\${fineDTO.fineSeq} passDate=\${fineDTO.passDate}  payStatus=\${fineDTO.payStatus} `);
                $accountBtn.removeAttr("disabled");
                $transferBtn.removeAttr("disabled");
                if(fineDTO.passDate!=null)//已經移轉
                {   console.log(`12345678`);

                    if(fineDTO.payStatus=="N")//已經移轉給司機了。
                    {   //不能再罰單入帳。
                        $accountBtn.attr("disabled","disabled");
                        $transferDateTime.val($passDate.val());
                    }else if(fineDTO.payStatus=="P")//已經發單入帳。
                    {   $accountBtn.attr("disabled","disabled");
                        $transferBtn.attr("disabled","disabled");
                        $accountDateTime.val($passDate.val());
                    }
                }
            });
        };
//=================================================================================


        /*
        * 當 ImagePreviewManager 初始化完成後的處理邏輯，相關的處理操作，應該要定義在此function以內。
        * @param {ImagePreviewManager} manager
        */

        function lockUploadButtons() {
            $uploadBtn.prop('disabled', true);
            $syncBtn.prop('disabled', true);
            $clearBtn.prop('disabled', true);
        }
        function resetUploadUI()
        {
            $uploadBtn.prop('disabled', false);
            $syncBtn.prop('disabled', false);
            $clearBtn.prop('disabled', false);

            setTimeout(() => {
            }, 800);
        }
    });


</script>
<!-- END CUSTOM LEVEL JS-->


</body>
</html>