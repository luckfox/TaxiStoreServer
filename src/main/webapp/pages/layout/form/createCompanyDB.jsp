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
        <div class="content-header row">
            <div class="content-header-left col-md-12 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">公司資料建檔</h3>
                <div class="row d-inline-block">
                    <div class=" mr-1">
                        <ol>
                            <li><h3 style="color:white"><span>流水編號:</span><span id="serialNoTitle">${requestScope.company.companyseq}</span></h3>
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
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="companyname">*公司名稱</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="公司名稱"
                                           id="companyname" name="companyname" aria-describedby="basic-addon1"
                                           value="${requestScope.company.companyname}" required>

                                </div>

                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text " for="companyab">公司簡稱</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="公司簡稱"
                                           id="companyab" name="companyab" aria-describedby="basic-addon1"
                                           value="${requestScope.company.companyab}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="specialcode">*特別碼*</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="特別碼"
                                           id="specialcode" name="specialcode" aria-describedby="basic-addon1"
                                           value="${requestScope.company.specialcode}" required disabled="disabled">
                                    <input type="text" class="form-control fc-1" placeholder="特別碼"
                                           id="specialcodeSearch" name="specialcodeSearch" aria-describedby="basic-addon1">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-specialcodeSearch"/>
                                </div>

                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="contact2">聯絡人</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="聯絡人"
                                           id="contact2" name="contact2" aria-describedby="basic-addon1"
                                           value="${requestScope.company.contact2}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="ownerSpecialCode">所屬公司</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="所屬公司"
                                           id="ownerSpecialCode" name="ownerSpecialCode" aria-describedby="basic-addon1"
                                           value="${requestScope.company.ownerSpecialCode}" disabled="disabled">
                                    <label id="ownerName" class="input-group-text flex-grow-1 ">${requestScope.company.ownerName}</label>
                                    <!-- 隱藏的 input，用來存放資料 -->
                                    <input type="hidden" class="update-enabled" id="owner" name="owner" value="${requestScope.company.owner}">

                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-ownerSpecialCode"/>

                                </div>

                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="customerCategoryCode">公司分類</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="公司分類"
                                           id="customerCategoryCode" name="customerCategoryCode" aria-describedby="basic-addon1"
                                           value="${requestScope.company.customerCategoryCode}" disabled="disabled">
                                    <label id="customerCategoryName" class="input-group-text flex-grow-1 " >${requestScope.company.customerCategoryName}</label>
                                    <!-- 隱藏的 input，用來存放資料 -->
                                    <input type="hidden" class="update-enabled" id="customerCategory" name="customerCategory" value="${requestScope.company.customerCategory}">
                                    <!-- List container for search results -->
                                    <ul class="dropdown-menu list-window w-25" id="autocomplete-list-CCSpecialCode"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="telno">電話號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="電話號碼"
                                           id="telno" name="telno" aria-describedby="basic-addon1"
                                           value="${requestScope.company.telno}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="portable">手機號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="手機號碼"
                                           id="portable" name="portable" aria-describedby="basic-addon1"
                                           value="${requestScope.company.portable}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="faxno">傳真號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="傳真號碼"
                                           id="faxno" name="faxno" aria-describedby="basic-addon1"
                                           value="${requestScope.company.faxno}" disabled="disabled">
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="contact">負責人</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="負責人"
                                           id="contact" name="contact" aria-describedby="basic-addon1"
                                           value="${requestScope.company.contact}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="taxno">稅籍號碼</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="稅籍號碼"
                                           id="taxno" name="taxno" aria-describedby="basic-addon1"
                                           value="${requestScope.company.taxno}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="banno">統一編號</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="統一編號"
                                           id="banno" name="banno" aria-describedby="basic-addon1"
                                           value="${requestScope.company.banno}" disabled="disabled">
                                </div>
                            </div>
                            <div class="row">

                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text " for="address">通訊地址</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="通訊地址"
                                           id="address" name="address" aria-describedby="basic-addon1"
                                           value="${requestScope.company.address}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text " for="invaddr">發票地址</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="發票地址"
                                           id="invaddr" name="invaddr" aria-describedby="basic-addon1"
                                           value="${requestScope.company.invaddr}" disabled="disabled">
                                </div>
                                <div class="input-group col-md-4 mb-1">
                                    <label class="input-group-text" for="email">電子郵件</label>
                                    <input type="text" class="form-control update-enabled fc-0" placeholder="電子郵件"
                                           id="email" name="email" aria-describedby="basic-addon1"
                                           value="${requestScope.company.email}" disabled="disabled">
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-group col-md-12 mb-1">
                                    <label class="input-group-text" for="remark">備註</label>
                                    <textarea rows="2" class="form-control update-enabled fc-0" placeholder="備註"
                                              id="remark" name="remark"
                                              disabled="disabled">${requestScope.company.remark}</textarea>
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
<!-- ////////////////////////////////////////////////////////////////////////////-->

<!--//start of footer-->
<%@ include file="../../footer.jsp" %>

<!--//end of footer-->

<!-- BEGIN VENDOR JS-->
<script src="<%= app_assetsPath %>/vendors/js/vendors.min.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<script src="<%= app_assetsPath %>/vendors/js/charts/chartist.min.js" type="text/javascript"></script>
<script src="<%= app_assetsPath %>/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>
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
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/buttonControl.js?<%= version %>"></script>
<script type="text/javascript">
    $(function () {
        let tmpSerialNo = ""; // 保存临时的序列号
        let updateMode = false; // 更新模式标志
        let appendMode = false; // 新增模式标志
        let listWindow;
        let $searchInput = $('#specialcodeSearch');
        let $ownerSpecialCodeInput= $('#ownerSpecialCode');
        let $customerCategoryCodeInput=$('#customerCategoryCode');

        const $dropdown_specialcodeSearch = $('#autocomplete-list-specialcodeSearch');
        const $dropdown_ownerSpecialCode = $('#autocomplete-list-ownerSpecialCode');
        const $dropdown_customerCategoryCode=$('#autocomplete-list-CCSpecialCode');

        console.log("createCompanyDB.jsp");
        console.log("dropdown id="+$dropdown_customerCategoryCode.prop("id"));
        $("#form").attr("class", "open");
        $("#createCompanyDB").attr("class", "active");


        //$(".form-control").attr("disabled", "disabled");
        $(".fc-0").attr("disabled", "disabled");

        const $listWindow = $("#listWindow"); // 静态列表窗口
        toggleButtons(false, ["#submit-button"]);

        $ownerSpecialCodeInput.on('blur',(event)=>{ ownerSpecialCode_blur_Handler(event,$ownerSpecialCodeInput);});

        // 綁定輸入事件
        const inputMappings = [
            {
                input: $searchInput,
                dropdown: $dropdown_specialcodeSearch,
                api: 'companyServlet',
                action: 'ajaxGetCompaniesBySpecialCode',
                keyLength: 2,
                keyName:'SpecialCode',
                dataMapper:item =>{
                    let row= autoMapItemToList(item, ['specialcode', 'companyname'],[`companyseq`],[]);// 顯示用的欄位
                    //console.log(`\${row}`);
                    return row;
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-companyseq"));
                    autoFillFromDataAttrs($btn, `#root`);
                }
            },
            {
                input: $ownerSpecialCodeInput,
                dropdown: $dropdown_ownerSpecialCode,
                api: 'companyServlet',
                action: 'ajaxGetCompaniesBySpecialCode',
                keyLength: 2,
                keyName:'SpecialCode',
                dataMapper:item =>{
                    let row= autoMapItemToList(item, ['specialcode', 'companyname'],[`companyseq`]);// 顯示用的欄位
                    //console.log(`\${row}`);
                    return row;
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-companyseq"));
                    autoFillFromDataAttrs($btn, `#root`);
                }
            },
            {
                input: $customerCategoryCodeInput,
                dropdown: $dropdown_customerCategoryCode,
                api: 'companyServlet',
                action: 'ajaxGetCompaniesBySpecialCode',
                keyLength: 2,
                keyName:'SpecialCode',
                dataMapper:item =>{
                    let row= autoMapItemToList(item, ['specialcode', 'companyname'],[`companyseq`]);// 顯示用的欄位
                    //console.log(`\${row}`);
                    return row;
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-companyseq"));
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


        function ownerSpecialCode_blur_Handler(event,object)
        {
            //ownerSpecialCode 觸發blur(失去焦點)，則透過ajax檢查此欄位是否正確，
            //若是正確，則將相關資料填入#owner,#ownerSpecialCode，#ownerName
            //若不正確，則此欄位內容值便和specialCode一樣。
            const value = object.val().trim();

            console.log("!!!!", value);

        }

        // 點擊其他地方隱藏下拉
        $(document).on('click', function (e) {
            if (!$(e.target).closest($searchInput).length && !$(e.target).closest($dropdown_specialcodeSearch).length) {
                $dropdown_specialcodeSearch.removeClass('show');
            }
            if (!$(e.target).closest($ownerSpecialCodeInput).length && !$(e.target).closest($dropdown_ownerSpecialCode).length) {
                $dropdown_ownerSpecialCode.removeClass('show');
            }

        });

        // 更新按钮事件
        $("#update-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            updateMode=updateButtonHandler(this,updateMode,SerialNo,"companyServlet","ajaxGetCompany",ajaxCallBack)
        });

        // 新增事件
        $("#add-button").click(function(){
            let SerialNo = $("#serialNoTitle").text().trim();
            appendMode=addButtonHandler(this,appendMode,SerialNo,"companyServlet","ajaxGetCompany",ajaxCallBack);
        });
        /*
         * 刪除的事件
         */
        $("#delete-button").click(function ()
        {
            let SerialNo = $("#serialNoTitle").text().trim();
            deleteButtonHandler(SerialNo, "<%=basePath%>companyServlet", "ajaxDeleteCompany", ajaxCallBack);

        });

        $("#first-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>companyServlet", "ajaxGetFirstCompany", ajaxCallBack);
        });
        /*
         * 往上一筆的事件
         */
        $("#previous-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>companyServlet", "ajaxGetPreviousCompany", ajaxCallBack);
        });

        /*
         * 往下一筆的事件
         */
        $("#next-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            nextButtonHandler(SerialNo, "<%=basePath%>companyServlet", "ajaxGetNextCompany", ajaxCallBack);
        });
        $("#last-button").click(function () {
            let SerialNo = $("#serialNoTitle").text().trim();
            previousButtonHandler(SerialNo, "<%=basePath%>companyServlet", "ajaxGetLastCompany", ajaxCallBack);
        });

        $("#submit-button").click(function ()
        {
            const serialNo = $("#serialNoTitle").text().trim();
            console.log("submit-button click,appendMode="+appendMode+",updateMode="+updateMode);
            if(appendMode===true) submitButtonHandler("0","companyseq", "companyServlet", "ajaxUpdateCompany",ajaxSubmitCallBack);
            else if(updateMode===true) submitButtonHandler(serialNo,"companyseq","companyServlet","ajaxUpdateCompany", ajaxSubmitCallBack);

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
        let ajaxCallBackRenewOwnerCompany= function (jsonObj)
        {
            if (jsonObj.success === false) return 0;
            RenewOwnerCompany(jsonObj.data);

        };
        function RenewOwnerCompany(company)
        {
            console.log("#ownerName="+company.companyname+":#owner="+company.owner+":#ownerSpecialCode="+company.ownerSpecialCode);
            $("#ownerName").html(company.companyname);
            $("#owner").val(company.owner);
            $("#ownerSpecialCode").val(company.ownerSpecialCode);
        }
//=================================================================================
        //將資料填回表單中。
        let ajaxCallBack = function(result) {
            genericAjaxCallback(result, company => company.companyseq, reflashData);
        };
//=================================================================================
    });


</script>
<!-- END CUSTOM LEVEL JS-->


</body>
</html>