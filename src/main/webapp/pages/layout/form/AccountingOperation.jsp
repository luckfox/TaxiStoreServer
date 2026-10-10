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
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/dashboard-ecommerce.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css?<%= version %>">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css?<%= version %>">

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
                <div class="row no-wrap-row">
                <h3 class="content-header-title mb-0 d-inline-block">駕駛員收款單</h3>
                <h3 class="content-header-title mb-0 d-inline-block"><span>建檔日期：</span><span id="createDate">${requestScope.result.createDate}</span></h3>
                <h3 class="content-header-title mb-0 d-inline-block"><span>流水編號:</span><span id="serialNoTitle">${requestScope.result.situationId}</span></h3>
                <h3 class="content-header-title mb-0 d-inline-block"><span>單據編號:</span><span id="recvFormId">000000</span></h3>
                <button type="button" id="previous-button" class="btn btn-warning">上一筆<i
                        class="ft-file-plus position-right"></i></button>
                <button type="button" id="next-button" class="btn btn-warning">下一筆<i
                        class="ft-file-plus position-right"></i></button>
                <button type="button" id="last-button" class="btn btn-warning">末筆<i
                        class="ft-file-plus position-right"></i></button>
                <button type="button" id="add-button" class="btn btn-warning">新增<i
                            class="ft-file-plus position-right"></i></button>
                <button type="button" id="update-button" class="btn btn-warning">編輯<i
                            class="ft-file-plus position-right"></i></button>
                <button type="button" id="submit-button" class="btn btn-primary">送出 <i
                            class="ft-upload  position-right"></i></button>
<%--                <button type="button" id="create-button" class="btn btn-danger">建立新單<i--%>
<%--                        class="ft-file-plus position-right"></i></button>--%>
<%--                <button type="button" id="deliever-button" class="btn btn-amber">發送<i--%>
<%--                        class="ft-file-plus position-right"></i></button>--%>
<%--                <button type="button" id="test-button" class="btn btn-dropbox">測試<i--%>
<%--                        class="ft-file-plus position-right"></i></button>--%>
                </div>
                <!-- 上半區域 -->
                <div class="row border border-white">
                    <!-- 左側區域 9/12 -->
                    <div class="col-md-9" id="root_tableP1">
                        <div class="row match-height ">
                            <div class="card custom-border bg-chartbg">
                                <div class="card-content  ">
                                    <div class="row mt-1">
                                        <div class="input-group col-md-4 mb-1 ">
                                            <label class="input-group-text cursor-pointer-hover" for="carno" id="labelCarno">*車號</label>
                                            <input type="hidden" class="form-control update-enabled" id="carseq" name="carseq" value="${requestScope.result.carseq}">
                                            <input type="text" class="form-control update-enabled fc-0"
                                                   placeholder="車號" id="carno" name="carno" value="${requestScope.result.carno}"
                                                   aria-describedby="basic-addon1" required
                                                   disabled="disabled">

                                            <input type="text" class="form-control fc-1" placeholder="車號" id="carNoSearch" name="carNoSearch"
                                                   value="${requestScope.result.carno}"
                                                   aria-describedby="basic-addon1">
                                            <!-- List container for search results -->
                                            <ul class="dropdown-menu list-window w-25" id="autocomplete-list-carNoSearch"/>

                                        </div>
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text cursor-pointer-hover" id="labelDriverNo" for="driverName">*駕駛員</label>
                                            <input type="hidden" class="form-control update-enabled" id="driverseq" name="driverseq" value="${requestScope.result.driverseq}">
                                            <input type="text" class="form-control fc-0"
                                                   placeholder="駕駛員" id="driverName" value="${requestScope.result.driverName}"
                                                   aria-describedby="basic-addon1"
                                                   disabled="disabled" required>
                                        </div>
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text " for="specialCode">*特別碼</label>
                                            <input type="text" class="form-control update-enabled fc-0"
                                                   placeholder="特別碼" id="specialCode" value="${requestScope.result.specialCode}"
                                                   aria-describedby="basic-addon1"
                                                   required disabled="disabled">
                                            <input type="text" class="form-control fc-1" placeholder="特別碼"
                                                   id="specialcodeSearch" name="specialcodeSearch" aria-describedby="basic-addon1">
                                                    <!-- List container for search results -->
                                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-specialcodeSearch"/>

                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text cursor-pointer-hover" id="labelCompanyName" for="companyName">公司名稱</label>
                                            <input type="hidden" class="form-control update-enabled" id="companyseq" name="companyseq" value="${requestScope.result.companyseq}">
                                            <input type="text" class="form-control fc-0"
                                                   placeholder="公司名稱" value="${requestScope.result.companyName}"
                                                   id="companyName" name="companyName" aria-describedby="basic-addon1" disabled="disabled"
                                                   required>

                                        </div>

                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text " for="driverId">身分證號</label>
                                            <input type="text" class="form-control fc-0"
                                                   placeholder="身分證號" value="${requestScope.result.driverId}"
                                                   id="driverId" name="driverId" aria-describedby="basic-addon1"
                                                   disabled="disabled">
                                        </div>
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text " for="portable">電話</label>
                                            <input type="text" class="form-control fc-0"
                                                   placeholder="電話" value="${requestScope.result.portable}"
                                                   id="portable" name="portable" aria-describedby="basic-addon1"
                                                   disabled="disabled">

                                            <!-- List container for search results -->

                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text " for="status">現況</label>
                                            <select class="update-enabled form-select fc-0" disabled="disabled"
                                                    id="status" name="status">
                                                <option value="0" ${requestScope.driverDTO.status == "0" ? "selected" : ""}>日</option>
                                                <option value="1" ${requestScope.driverDTO.status == "1" ? "selected" : ""}>夜</option>
                                                <option value="2" ${requestScope.driverDTO.status == "2" ? "selected" : ""}>靠行</option>
                                                <option value="3" ${requestScope.driverDTO.status == "3" ? "selected" : ""}>合作社</option>
                                                <option value="4" ${requestScope.driverDTO.status == "4" ? "selected" : ""}>租送</option>
                                                <option value="5" ${requestScope.driverDTO.status == "5" ? "selected" : ""}>包月</option>
                                            </select>
                                        </div>
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text " for="giveCarDate">交車日期</label>
                                            <input type="date" class="form-control update-enabled fc-0"
                                                   placeholder="受僱日期" id="giveCarDate" name="giveCarDate" aria-label="" placeholder="yyyy/mm/dd"
                                                   aria-describedby="basic-addon1"
                                                   value="${requestScope.driverDTO.giveCarDate}">
                                        </div>
                                        <div class="input-group col-md-4 mb-1">
                                            <label class="input-group-text " for="terminateDate">解約日期</label>
                                            <input type="date" class="form-control update-enabled fc-0"
                                                   placeholder="受僱日期" id="terminateDate" name="terminateDate" aria-label="" placeholder="yyyy-mm-dd"
                                                   aria-describedby="basic-addon1"
                                                   value="${requestScope.driverDTO.terminateDate}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-group col-md-12 mb-1">
                                            <label class="input-group-text " for="memo">說明</label>
                                            <textarea id="memo" name="memo" class="form-control update-enabled fc-0" row="2"> </textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 右側區域 3/12 -->
                    <div class="col-md-3">
                        <div class="card  bg-chartbg border border-white">
                        <table id="tableP2" class="table text-white bg-chartbg table-hover">
                            <thead>
                            <tr>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
                <!-- 下半區域 -->
                <div class="row mt-3">
                    <!-- P3: 左側科目列表 -->
                    <div class="col-md-3 bg-chartbg">
                        <div class="card custom-border bg-chartbg border border-white">
                            <div class="card-content  bg-chartbg">
                                <table id="tableP3" class="table text-white table-hover">
                                    <thead>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- P4: 右側詳細資訊表格 -->
                    <div class="col-md-9 bg-chartbg border-white">
                        <div class="card border bg-chartbg border-white">
                            <table id="tableP4" class="table text-white table-hover">
                                <thead>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div class="d-flex justify-content-end">
                                <span class="table-summary">小計：<strong>5,000</strong></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="modalContainer"></div>

<!-- 🔥 編輯彈出視窗 (放在這裡，靠近 body 結尾) -->

<div id="receiptModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-dark text-white" style="border: 2px solid white;"> <!-- 加入白色邊框 -->
            <div class="modal-header">
                <h3 class="modal-title text-white">傳送訊息</h3>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="sendMessageForm">
                    <div class="row mt-2 ">

                        <div class="input-group col-md-6 mb-2">
                            <label for="sendMessageDriverName" class="input-group-text ">DriverName</label>
                            <input type=hidden  id="sendMessageSerialId" value=""  >
                            <input type=hidden  id="sendMessageUserId" value=""  >
                            <input type="text" id="sendMessageDriverName" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>

                        <div class="input-group col-md-6 mb-2">
                            <label for="sendMessageTag" class="input-group-text ">Tag</label>
                            <input type="text" id="sendMessageTag" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>
                    </div>
                    <div class="row mt-2">

                        <div class="input-group col-md-12  mb-2">
                            <label for="sendMessageMessage" class="input-group-text ">Message</label>
                            <input type="text" id="sendMessageMessage" class="form-control update-enabled fc-0">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                <button type="submit" id="sendMessageBtnSubmit" class="btn btn-primary">發送</button>
            </div>
        </div>
    </div>
</div>
<div id="custom-alert" style="position:fixed; top:20px; right:20px; z-index:9999; display:none; padding:10px 20px; background-color:#f44336; color:white; border-radius:5px;"></div>
<!--//end of body-->
<!--//start of footer menu-->
<%@ include file="../../footer.jsp" %>

<!--//end of footer menu-->


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

<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script type="text/javascript" src="<%= app_assetsPath %>/vendors/js/tables/datatable/datatables.min.js" ></script>
<script type="text/javascript" src="<%= assetsPath %>/js/editableHelper.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/buttonControl.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/form/AccountingOperation/AccountingOperation.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/form/AccountingOperation/tableP2.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/form/AccountingOperation/tableP3.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/form/AccountingOperation/tableP4.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/modal/showModal.js?<%= version %>"></script>

<script type="text/javascript">

    $(function ()
    {
        console.log("AccountingOperation.jsp");
        $("#accounting").attr("class", "open");
        console.log("set #dashboard class=open");
        $("#accountingOperation").attr("class", "active");
        console.log("accountingOperation.jsp ver 1.00");

        let m_driverInfoPath = "/pages/layout/modals/driverInfo.html";
        let m_carInfoPath = "/pages/layout/modals/carInfoEditor.html";
        let m_companyInfoPath = "/pages/layout/modals/companyInfo.html";

        let servlet_url1="lineBotServlet";
        let servlet_url2="welcomeServlet";
        let originalData = {};//存放原始資料
        let updateMode = false; // 更新模式标志
        let appendMode = false; // 新增模式标志

        const $module_container=$("#modalContainer");
        const $carnoInput=$('#carno');
        const $carnoSearchInput = $('#carNoSearch');
        const $specialCodeInput=$('#specialCode');
        const $specialCodeSearchInput = $('#specialcodeSearch');

        const $dropdown_specialcodeSearch = $('#autocomplete-list-specialcodeSearch');
        const $dropdown_carnoSearch = $('#autocomplete-list-carNoSearch');

        const inputMappings = [
            {
                input: $carnoInput,
                dropdown: $dropdown_carnoSearch,
                api: 'carServlet',
                action: 'ajaxGetCarsByCarnoLike',
                keyLength: 3,
                keyName: 'carNoPart',
                dataMapper:item => {
                    return autoMapItemToList(item,['carno','ownerName'],['*'],[],"#root_tableP1");// 顯示用的欄位
                },
                onSelect: ($btn) => {
                    $("#companyName").val($btn.attr("data-ownerName"));
                    $("#carno").val($btn.attr("data-carno"));
                    $("#carseq").val($btn.attr("data-carseq"));
                }
            },
            {
                input: $carnoSearchInput,
                dropdown: $dropdown_carnoSearch,
                api: 'situationServlet',
                action: 'ajaxGetSituationDTOByCarnoLike',
                keyLength: 3,
                keyName: 'key',
                dataMapper:
                    item => {
                    return autoMapItemToList(item,['carno', 'driverName','companyName'],['*'],[`receipts`],"#root_tableP1");// 顯示用的欄位
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-situationId"));
                    autoFillFromDataAttrs($btn, "#root_tableP1");
                    reflashRcvFrom($btn.attr("data-situationId"), "#tableP2", "#tableP4");
                }
            },
            {
                input: $specialCodeInput,
                dropdown: $dropdown_specialcodeSearch,
                api: 'driverServlet',
                action: 'ajaxGetDriversBySpecialCode',
                keyLength: 3,
                keyName: 'driverSC',
                dataMapper: item => {
                    return autoMapItemToList(item,['drivername','id'],['*'],[],"#root_tableP1");// 顯示用的欄位
                },
                onSelect: ($btn) => {
                    $("#driverName").val($btn.attr("data-drivername"));
                    $("#driverId").val($btn.attr("data-id"));
                    $("#portable").val($btn.attr("data-portable"));
                    $("#specialCode").val($btn.attr("data-specialcode"));
                }
            },
            {
                input: $specialCodeSearchInput,
                dropdown: $dropdown_specialcodeSearch,
                api: 'situationServlet',
                action: 'ajaxGetSituationBySpecialCode',
                keyLength: 2,
                keyName: 'key',
                dataMapper:item => {
                    return autoMapItemToList(item,['carno', 'driverName','companyName'],['*'],[`receipts`],"#root_tableP1");// 顯示用的欄位
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-situationId"));
                    autoFillFromDataAttrs($btn, "#root_tableP1");
                    reflashRcvFrom($btn.attr("data-situationId"), "#tableP2", "#tableP4");
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

        reflashRcvFrom($("#serialNoTitle").text(),"#tableP2","#tableP4");
        //==============================================================================================================================
        let tableP4=initTableP4("#tableP4");
        let tableP3=initTableP3("#tableP3");
        let tableP2 = initTableP2("#tableP2 ",{
            onRowSelect: function (recvFormId)
            {
                loadRecvEntries(recvFormId, "#tableP4", function ()
                {
                    setTimeout(() => {
                        let lastRow = $("#tableP4 tbody tr:last");
                        if (lastRow.length) {
                            lastRow.addClass("selected");
                            lastRow[0].scrollIntoView({ behavior: "smooth", block: "nearest" });
                        }
                    }, 200);
                });
            }
        });


//===========================tableP4==========================================================================
//

        bindTableP3ClickHandler("#tableP3","#tableP4");
        // 金額欄位只能輸入整數
        initEditableBehavior(tableP4, ['editable', 'amount'], function(rowData, columnIndex, newValue)
        {
            $.post("recvEntryServlet",
                {
                    action: "ajaxUpdateEntry",
                    id: rowData.id,
                    amount: newValue,
                    summary: rowData.summary
                });
        }, function(value) {
            return /^\d+$/.test(value);  // ✅ 金額必須為整數
        });

        // 摘要欄位（不需驗證）
        initEditableBehavior(tableP4, ['editable', 'summary'], function(rowData, columnIndex, newValue)
        {
            $.post("recvEntryServlet",
                {
                    action: "ajaxUpdateEntry",
                    id: rowData.id,
                    summary: newValue,
                    amount: rowData.amount
                });
        });

        $("#labelCompanyName").click(function (){
            let url=`companyServlet?action=ajaxGetCompany&serialNo=`+$("#companyseq").val();
            show_Modal($module_container,m_companyInfoPath,url);
        });

        $("#labelCarno").click(function() {
            let url=`carServlet?action=ajaxGetCarDTO&serialNo=`+$("#carseq").val();
            show_Modal($module_container,m_carInfoPath,url);
        });

        $("#labelDriverNo").click(function (){

            let url=`driverServlet?action=ajaxGetDriverDTO&serialNo=`+$("#driverseq").val();
            show_Modal($module_container,m_driverInfoPath,url);
        });
        $("#test-button").click(function(){
            console.log("#test-button click");
            $("#tag").val($("#tag").val()+"12345");
        });
        /*
          * 往上一筆的事件
          */
        $("#previous-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>situationServlet", "ajaxGetPreviousSituation", ajaxCallBack);
        });

        /*
         * 往下一筆的事件
         */
        $("#next-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            nextButtonHandler(SerialNo, "<%=basePath%>situationServlet", "ajaxGetNextSituation", ajaxCallBack);
        });
        $("#add-button").click(function(){
            let SerialNo = $("#serialNoTitle").text().trim();
            tableP2.clear().draw();
            tableP4.clear().draw();
            appendMode=addButtonHandler(this,appendMode,SerialNo,"<%=basePath%>situationServlet","ajaxGetSituation",ajaxCallBack);
        });
        $("#update-button").click(function()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            updateMode=updateButtonHandler(this,updateMode,SerialNo,"<%=basePath%>situationServlet","ajaxGetSituation",ajaxCallBack)
        })
        $("#submit-button").click(function()
        {
            const serialNo = $("#serialNoTitle").text().trim();
            console.log(`submit-button click,appendMode=${appendMode},updateMode=${updateMode}`);
            if(appendMode===true) submitButtonHandler("0", "id","<%=basePath%>situationServlet","ajaxUpdateSituation", ajaxSubmitCallBack);
            else if(updateMode===true) submitButtonHandler(serialNo, "id","<%=basePath%>situationServlet","ajaxUpdateSituation", ajaxSubmitCallBack);

        });
        let ajaxSubmitCallBack= SubmitCallback({
            setAppendMode: (val) => appendMode = val,
            setUpdateMode: (val) => updateMode = val
        });
        /*
            建立一份recvForm表單
         */
        $("#create-button").click(function () {

            $.getJSON("recvformServlet",
            {
                action: 'ajaxCreateRecvForm',
                situationId:$("#serialNoTitle").text(),
                type:"P",
                remark:"",
                userNo:"0000",
                userName:"大中原車行"
            }, function (result)
            {
                if(result.success===false)
                {
                    return 0;
                }
                console.log("create new recvForm success! ")
                let tableP2 = $("#tableP2").DataTable();
                let newRowData = result.data; // 假設後端返回的是單筆資料物件

                // ✅ 插入新資料到最上方（索引 0）
                let newRow = tableP2.row.add(newRowData).draw(false).node();

                // ✅ 設置新行為 selected 並確保視窗滾動到該行
                setTimeout(() => {
                    $(newRow).addClass("selected").siblings().removeClass("selected");
                    newRow.scrollIntoView({ behavior: "smooth", block: "nearest" });
                }, 200);
                let tableP4 = $("#tableP4").DataTable();
                tableP4.clear().draw();

            });
        })
        let ajaxCallBack = function (jsonObj)
        {
            if (jsonObj.success === false)
            {
                alert(jsonObj.errorMessage);
                console.log(jsonObj.errorMessage);
                return 0;
            }
            //將整個頁面的控制項(".fc-0")重新置換。
            replaceData(jsonObj.data)
            //根據situationId取得recvForm存放至"#tableP2"，並且根據最新一筆的recvForm取得所有的recvEntry，
            //並存放置"#tableP4"
            reflashRcvFrom(jsonObj.data.situationId,"#tableP2","#tableP4");
        };

        $("#deliever-button").on("click", function ()
        {
            $('#receiptModal').modal('show'); // 打開編輯視窗
            return;
            let tableElement = document.querySelector("#tableP4");

            html2canvas(tableElement, {
                backgroundColor: null, // 保持透明背景
                scale: 2 // 提高解析度，避免圖片模糊
            }).then(canvas =>
            {   console.log("image processing.....")
                let imgData = canvas.toDataURL("image/jpeg", 1.0); // 轉換為 JPG 格式
                let link = document.createElement("a");
                link.href = imgData;
                link.download = "d:/worktableP4.jpg"; // 設定下載檔案名稱
                link.click();
            });
        });

    });



</script>
<!-- END CUSTOM LEVEL JS-->
</body>
</html>