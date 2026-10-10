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
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css">
    <style>

    </style>

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
<div class="app-content content" id="root">
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-12 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">車輛現況查詢</h3>
                <h3 class="content-header-title d-inline-block "><span>查詢範圍:</span><span id="queryRangeLabel"  year="" season=""></span></h3>
                <h3 class="content-header-title d-inline-block "><span>查詢車行:</span><span id="queryCompanyLabel" companySeq=""></span></h3>
                <div class="row">
                    <div class="col-12">
                        <!-- 🔹 搜尋框 & Show Entries 在同一行 -->
                        <div class="d-flex justify-content-between align-items-center mb-1">
                            <!-- 左側：Show Entries 選單 -->
                            <div class="d-flex align-items-center ml-1">
                                <label class="input-group-text ">日期</label>
                                <select id="seasonSelect" class="form-select input-group-text w-80">
                                </select>

                            </div>

                            <!-- 右側：搜尋欄 -->
                            <div class="d-flex align-items-center justify-content-end mr-1">
                                <select id="searchColumn" class="form-select input-group-text">
                                    <option value="0" selected>全部</option>
                                    <option value="1">車行特別碼</option>
                                </select>
                                <input type="text" id="CompanySC_Search" placeholder="搜尋內容..."
                                       class="form-control fc-0 w-50">
                                <ul class="dropdown-menu list-window w-25" id="autocomplete-list-CompanySC"/>
                            </div>
                        </div>
                        <table id="VI_table" class="table table-striped table-bordered sourced-data">
                            <thead>
                            </thead>
                            <tfoot>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="modalContainer"></div>

<div id="P2_Modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-dark text-white" style="border: 2px solid white;"> <!-- 加入白色邊框 -->
            <div class="modal-header">
                <h3 class="modal-title text-white">傳送訊息</h3>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="P2_Form">
                    <div class="row mt-2 ">
                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_DriverName" class="input-group-text ">司機名稱</label>
                            <input type=hidden id="P2_SerialId" value="">
                            <input type=hidden id="P2_UserId" value="">
                            <input type="text" id="P2_DriverName" class="form-control fc-0"
                                   aria-describedby="basic-addon1" readonly>
                        </div>

                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_CarNo" class="input-group-text ">車號 </label>
                            <input type="text" id="P2_CarNo" class="form-control fc-0" aria-describedby="basic-addon1"
                                   readonly>
                        </div>
                    </div>

                    <div class="row mt-2 ">
                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_CompanyName" class="input-group-text ">車行名稱</label>
                            <input type="text" id="P2_CompanyName" class="form-control fc-0"
                                   aria-describedby="basic-addon1" readonly>
                        </div>

                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_ShingleDat" class="input-group-text ">掛牌日期 </label>
                            <input type="text" id="P2_ShingleDat" class="form-control fc-0"
                                   aria-describedby="basic-addon1" readonly>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-sm-12">
                            <label for="P2_Message" class="input-group-text ">訊息</label>
                            <div class="position-relative has-icon-left">
                                <textarea type="text" id="P2_Message"
                                          class="form-control update-enabled fc-0"></textarea>
                                <div class="form-control-position pl-1"><i class="ft-message-circle"></i></div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="P2_MessageBtn" data-carno="" data-shingledat="" class="btn btn-toolbar">測試
                </button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                <button type="submit" id="P2_BtnSubmit" class="btn btn-primary">發送</button>
            </div>
        </div>
    </div>
</div>


<!-- 🔥 編輯彈出視窗 (放在這裡，靠近 body 結尾) -->
<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-dark text-white" style="border: 2px solid white;"> <!-- 加入白色邊框 -->
            <div class="modal-header">
                <h3 class="modal-title text-white">編輯使用者資訊</h3>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <div class="row mt-2 ">
                        <!-- userId -->
                        <div class="input-group col-md-6 mb-2">
                            <label for="editUserId" class="input-group-text ">User ID</label>
                            <input type=hidden id="editSerialId" value="">
                            <input type="text" id="editUserId" class="form-control fc-0" aria-describedby="basic-addon1"
                                   readonly>
                        </div>
                        <!-- displayName Line User 本身登錄名稱-->
                        <div class="input-group col-md-6 mb-2">
                            <label for="editDisplayName" class="input-group-text ">Display Name</label>
                            <input type="text" id="editDisplayName" class="form-control fc-0"
                                   aria-describedby="basic-addon1" readonly>
                        </div>
                    </div>

                    <div class="row mt-2">
                        <!-- driverPortable -->
                        <div class="input-group col-md-6 mb-2">
                            <label class="input-group-text ">Portable</label>
                            <input type="text" class="form-control  fc-0"
                                   id="editDriverPortable" name="editDriverName" aria-describedby="basic-addon1"
                                   readonly>
                        </div>
                        <!-- driverName -->
                        <div class="input-group col-md-6 mb-2">
                            <label class="input-group-text ">Driver Name</label>
                            <input type="text" class="form-control  fc-0"
                                   id="editDriverName" name="editDriverName" aria-describedby="basic-addon1" readonly>
                        </div>
                    </div>

                    <div class="row mt-2">
                        <!-- driverSC -->
                        <div class="input-group col-md-6  mb-2">
                            <input id="editDriverSeq" type=hidden name="editDriverSeq" value=""/>
                            <label for="editDriverSC" class="input-group-text">Driver SC</label>
                            <input type="text" id="editDriverSC" class="form-control update-enabled fc-0">
                            <!-- List container for search results -->
                            <ul class="dropdown-menu list-window w-25" id="autocomplete-list-editDriverSC"/>
                        </div>
                        <!-- driverID -->
                        <div class="input-group col-md-6  mb-2">
                            <label for="editDriverId" class="input-group-text ">Driver ID</label>
                            <input type="text" id="editDriverId" class="form-control update-enabled fc-0">
                            <!-- List container for search results -->
                            <ul class="dropdown-menu list-window w-25" id="autocomplete-list-editDriverId"/>
                        </div>
                    </div>

                    <div class="row mt-2">
                        <!-- Tag (佔據兩倍寬度) -->
                        <div class="input-group col-md-12  mb-2">
                            <label for="editTag" class="input-group-text ">Tag</label>
                            <input type="text" id="editTag" class="form-control update-enabled fc-0">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                <button type="submit" id="btnSubmit" class="btn btn-primary">儲存</button>
            </div>
        </div>
    </div>
</div>


<!--//end of body-->
<!--//start of footer menu-->
<%@ include file="../../footer.jsp" %>
.
<!--//end of footer menu-->


<!-- 確保 jQuery 先載入 -->
<script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>

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
<%--    <script src="<%= app_assetsPath %>/js/scripts/pages/dashboard-ecommerce.js" type="text/javascript"></script>--%>


<!-- END PAGE LEVEL JS-->
<!-- BEGIN CUSTOM LEVEL JS-->
<script type="text/javascript" src="<%= assetsPath %>/js/modal/LineUserInfoEditor.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/modal/showModal.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/DynamicModalEditor.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/DynamicModalHelper.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script src="<%= app_assetsPath %>/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/form/vehicleStatus/vi_table.js?<%= version %>"></script>
<%--<script src="<%= app_assetsPath %>/js/scripts/tables/datatables-extensions/datatables-sources.js" type="text/javascript"></script>--%>
<script type="text/javascript">

    $(function () {

        console.log("vehicleStatus.jsp ver 1.00");
        //設定leftSideMenu.jsp 的階層式選單，避免重複點選頁面
        $("#apps").attr("class", "open");
        console.log("set #dashboard class=open");
        $("#vehicleStatus").attr("class", "active");


        let originalData = {};//存放原始資料
        const $Input_companySC_Search = $('#CompanySC_Search');
        const $dropdown_CompanySC = $('#autocomplete-list-CompanySC');
        const $modalContainer=$("#modalContainer");
        const m_userinfoEditorPath = "/pages/layout/modals/userinfoEditor.html";

        initSeasonOption($("#seasonSelect"));
        initTypeOption();


        // if ($.fn.DataTable.isDataTable('#VI_table')) {
        //     $('#VI_table').DataTable().destroy();  // 銷毀已存在的 DataTable
        // }

        const inputMappings = [
            {
                input: $Input_companySC_Search,
                dropdown: $dropdown_CompanySC,
                api: 'companyServlet',
                action: 'ajaxGetCompaniesBySpecialCode',
                keyLength: 2,
                keyName:'SpecialCode',
                dataMapper:item =>{
                    let row= autoMapItemToList(item, ['specialcode', 'companyname'],[`companyseq`,`companyname`],[]);// 顯示用的欄位
                    //console.log(`\${row}`);
                    return row;
                },
                onSelect: ($btn) => {
                    $("#serialNoTitle").text($btn.attr("data-companyseq"));
                    console.log(`@@@ \${$btn.attr("data-companyname")} @@@`);
                    $("#queryCompanyLabel").text($btn.attr("data-companyname"));
                    $("#queryCompanyLabel").attr("companySeq", $btn.attr("data-companyseq"));

                    autoFillFromDataAttrs($btn, `#root`);
                    $('#VI_table').DataTable().draw();
                    //table.draw();
                }
            }
        ];
        /**
         * 對inputMappings裡面的每個row，設定input/keydown的事件觸發的handle
         */
        inputMappings.forEach(({input, dropdown, api, action, keyLength, keyName, dataMapper, onSelect}) => {
            input.on('input keydown', (e) =>
            {
                handleAutoCompleteDropdown(e, input, dropdown,
                    {
                    apiUrl: api, action, keyName, keyLength, dataMapper, onSelect
                });
            });
        });
        const viTable = new VIDataTableManager('#VI_table', {
            onEdit: ($elem,data) => {
                console.log("👉 觸發編輯：", data);
                const editor=editHandler(data.id,$elem);
                editor.open();
                //const url=`carServlet?action=ajaxGetCarDTO&serialNo=`+$("#carseq").val();

                //editLineUserInfo(data.id,$elem,$modalContainer);
                //show_Modal($modalContainer,m_userinfoEditorPath,)
                //editUser(data.id); // or do whatever
            },
            onDelete: (data) => {
                console.log("❌ 觸發刪除：", data);
                //deleteUser(data.id);
            },
            onMessage: (data) => {
                console.log("📨 觸發訊息：", data);
                //showMessageModal(data);
            }
        });

        viTable.init();
        //const table =initVI_table('#VI_table');

        $("#searchColumn").change(function ()
        {

            console.log(`"#searchColumn".change()`);

            let option = $(this).find("option:selected");     //找出目前被selected的選項。
            let selectedValue = $(option).val();
            let selectedText = $(option).text().trim();
            let companySC_search=$("#CompanySC_Search");
            console.log(`value=\${selectedValue} text=\${selectedText}`);
            if (selectedValue === "0")
            {
                console.log(`@ $("#CompanySC_Search").text()=\${$("#CompanySC_Search").text()}`);

                $("#CompanySC_Search").val("");
                console.log(`@@ $("#CompanySC_Search").text()=\${$("#CompanySC_Search").text()}`);
                companySC_search.attr("disabled", "disabled");
                $("#queryCompanyLabel").attr("companySeq", "");
                $("#queryCompanyLabel").text(selectedText);
                viTable.dataTable.draw();
            }else
            {
                companySC_search.removeAttr("disabled");
            }


        });
        $("#seasonSelect").change(function () {
            let option = $(this).find("option:selected");
            let selectedValue = $(this).val();
            let selectedText = $(this).find("option:selected").text();
            let year = option.attr("data-year");
            let season = option.attr("data-season");

            $("#queryRangeLabel").text(selectedText);
            $("#queryRangeLabel").attr("year", option.attr("data-year")).attr("season", option.attr("data-season"));

            console.log(`選擇的值: \${selectedValue}, 選擇的文字: \${selectedText} year=\${option.attr("data-year")},season=\${option.attr("data-season")}`);
            let companySeq = $("queryCompanyLabel").attr("companySeq");

            if (companySeq !== null) {
                //table.draw();
                viTable.dataTable.draw();
            }

        });
        function editHandler(id,btn)
        {
            const modalPath = "/pages/layout/modals/userinfoEditor.html";
            const openRequest=`situationServlet?action=ajaxGetSituation&serialNo=\${id}`;
            return new DynamicModalEditor(btn, $modalContainer, modalPath, {
                getOpenRequest: () => ({
                    url: openRequest
                }),
                onUpdate: (formData) => ({
                    url: `lineUserServlet?action=ajaxUpdateByUserId&userId=\${formData.userId}`,
                    mapPayload: (data) => ({
                        serialID: data.serialID,
                        userId: data.userId,
                        displayName: data.displayName,
                        driverseq: data.driverSeq,
                        tag: data.tag
                    })
                }),
                onUpdateTableRow: (formData) => {
                    const table = $('#VI_table').DataTable();
                    const row = table.rows().nodes().to$().filter(function () {
                        return $(this).data('user-id') == formData.userId;
                    });
                    if (row.length) {
                        const rowIndex = table.row(row).index();
                        const rowData = table.row(rowIndex).data();
                        rowData.driverName = formData.driverName;
                        rowData.driverPortable = formData.driverPortable;
                        rowData.driverSC = formData.driverSC;
                        rowData.driverId = formData.driverId;
                        rowData.tag = formData.tag;
                        table.row(rowIndex).data(rowData).invalidate().draw(false);
                    }
                },
                onBindDropdown: (modalHelper, modalPrefix) => {
                    const $root=modalHelper.getRoot();

                    const $input_driverSC = $("#"+findFullElementId($root, "driverSC"));
                    const $input_driverId = $("#"+findFullElementId($root, "driverId"));
                    const $dropdown_driverId = $("#"+findFullElementId($root, "autocomplete-list-driverId"));
                    const $dropdown_driverSC = $("#"+findFullElementId($root, "autocomplete-list-driverSC"));

                    const inputMappings = [
                        {
                            input:$input_driverSC,
                            dropdown: $dropdown_driverSC,
                            api: 'driverServlet',
                            action: 'ajaxGetDriversBySpecialCode',
                            keyLength:3,
                            keyName: 'driverSC',
                            dataMapper: item => {
                                return autoMapItemToList(item,['drivername','id'],['*'],[],$root);// 顯示用的欄位
                            },
                            onSelect: ($btn) => {
                                $("#driverName").val($btn.attr("data-drivername"));
                                $("#driverId").val($btn.attr("data-id"));
                                $("#portable").val($btn.attr("data-portable"));
                                $("#specialCode").val($btn.attr("data-specialcode"));
                            }
                        },
                        {
                            input:$input_driverId,
                            dropdown: $dropdown_driverId,
                            api: 'driverServlet',
                            action: 'ajaxGetDriversByDriverId',
                            keyLength:3,
                            keyName: 'driverId',
                            dataMapper: item => {
                                return autoMapItemToList(item,['drivername','id'],['*'],[],$root);// 顯示用的欄位
                            },
                            onSelect: ($btn) => {
                                $("#driverName").val($btn.attr("data-drivername"));
                                $("#driverId").val($btn.attr("data-id"));
                                $("#portable").val($btn.attr("data-portable"));
                                $("#specialCode").val($btn.attr("data-specialcode"));
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

                }
            });
        }
        function reflashTable(year, season, companySeq) {
            console.log(`year=\${year} season=\${season} companySeq=\${companySeq}`);
            $.ajax({
                url: "situationServlet",
                type: "GET",
                data: {
                    action: "ajaxGetVehicleInspectionStatus",
                    year: year,
                    season: season,
                    companySeq: companySeq
                },
                dataType: "json",
                success: function (response) {
                    //let table = $("#VI_table").DataTable();
                    // 清空舊資料並新增新資料
                    //table.clear().rows.add(response.data).draw();
                    viTable.dataTable.clear().rows.add(response.data).draw();

                },
                error: function (xhr, status, error) {
                    console.error("Error fetching data:", error);
                }
            });
        }



        $(document).on('click', '#warningMsg-button', function () {
            console.log("#warningMsg-button clicked");
            $('#inputMessage1').val("55555"); // 設置值
        });

        $(document).click(function (e) {
            if (!$(e.target).closest($dropdown_CompanySC).length) {
                $dropdown_CompanySC.removeClass("show");
            }

        });

        $('#editModal').on('hidden.bs.modal', function () {
            $(this).attr('inert', ''); // 讓 Modal 完全無法被聚焦
        }).on('shown.bs.modal', function () {
            $(this).removeAttr('inert'); // 移除 inert 以恢復可用性

            //保存所有數據。
            originalData = {
                userId: $("#editUserId").val(),
                displayName: $('#editDisplayName').val(),
                driverSeq: $("#editDriverSeq").val(),
                tag: $("#editTag").val()
            };
            console.log("originalData:", originalData);
        });
        $("#P2_BtnSubmit").on("click", function (e) {
            console.log("123456");
            //e.preventDefault();
            //sendMessageHandler();
        });

        function sendMessageHandler() {
            let formData = {
                userId: $("#sendMessageUserId").val(),
                message: $("#sendMessageMessage").val()
            };
            $.getJSON("lineUserServlet",
                {
                    action: "ajaxPushMessage",
                    userId: $("#sendMessageUserId").val(),
                    message: $('#sendMessageMessage').val(),

                }, function (response) {
                    console.log("response：", response);
                    if (response.success === true) {
                        alert("發送成功！");
                        $("#sendMessageModal").modal("hide");


                    } else {
                        alert("發送失敗：" + response.message);
                    }
                }).fail(function (xhr, status, error) {
                console.error("提交失敗:", error);
                alert("發生錯誤，請稍後再試！");
            });
        }

        $("#P2_MessageBtn").on("click", function (e) {
            let carno = $(this).attr("data-carno");
            let shingleDat = $(this).attr("data-shingledat");

            let theString = `\${carno}的車主您好，您的驗車日期為\${shingleDat}，請盡速前往驗車，以免受罰!`;

            $("#P2_Message").val(theString);
        });
        $("#btnSubmit").on("click", function (e)
            //$("#editForm").submit(function (e)
        {
            e.preventDefault(); // 防止預設提交行為 (頁面重整)
            sumbitHandler();
        });

        function sumbitHandler() {
            // 準備提交的表單資料
            let formData = {
                userId: $("#editUserId").val(),
                displayName: $('#editDisplayName').val(),
                driverSeq: $("#editDriverSeq").val(),
                tag: $("#editTag").val(),
            };

            //比對資料是否有經過變動。
            let keys1 = Object.keys(originalData).sort();
            let keys2 = Object.keys(formData).sort();
            console.log(originalData);
            console.log(formData);
            // 檢查所有 key-value 是否一致,如果一致則不更新。
            if (keys1.every(key => originalData[key] === formData[key])) {
                console.log("資料沒有改變!");
                $("#editModal").modal("hide");
                return;
            }

            let data = JSON.stringify({
                action: "ajaxUpdateByUserId",
                userId: $("#editUserId").val(),
                displayName: $('#editDisplayName').val(),
                driverSeq: $("#editDriverSeq").val(),
                tag: $("#editTag").val()
            });
            console.log(data);
            // 送出 AJAX 請求
            $.getJSON("lineUserServlet",
                {
                    action: "ajaxUpdateByUserId",
                    userId: $("#editUserId").val(),
                    displayName: $('#editDisplayName').val(),
                    driverSeq: $("#editDriverSeq").val(),
                    tag: $("#editTag").val()

                }, function (response) {
                    if (response.success === true) {
                        alert("更新成功！");
                        $("#editModal").modal("hide");

                        let row = $("#dataTable").find(`tr[data-user-id="${userId}"]`);
                        updateTableRow(formData.userId, formData);

                    } else {
                        alert("更新失敗：" + response.message);
                    }
                }).fail(function (xhr, status, error) {
                console.error("提交失敗:", error);
                alert("發生錯誤，請稍後再試！");
            });
        }

        function updateTableRow(userId, newData) {

            const row=viTable.dataTable.rows(function (idx, data, node) {
                return data.userId === userId;
            });
            // 🔄 更新資料
            if (row.any()) {
                row.data(newData).draw(false); // 只重繪這一行，不影響其他行
                console.log("已更新 userId=" + userId + " 的資料");
            } else {
                console.warn("找不到 userId=" + userId + " 的列");
            }
        }

        function initTypeOption()
        {
            console.log("initTypeOption");
            let option = $("#searchColumn").find("option:selected");
            if (option.val() === "0")
            {
                $("#queryCompanyLabel").text(`\${option.text()}`);
                $("#queryCompanyLabel").attr("companySeq", "");
                $("#CompanySC_Search").attr("disabled","disabled");
            }
        }

        function initSeasonOption($select) {
            console.log("initSeasonOption");

            const now = new Date();
            let year = now.getFullYear();
            let month = now.getMonth() + 1; // JavaScript getMonth() 是從 0 開始的
            let season = Math.ceil(month / 3); // 1: Q1, 2: Q2, 3: Q3, 4: Q4

            for (let i = 0; i < 12; i++) {
                let seasonText = ["第一", "第二", "第三", "第四"][season - 1] + "季";
                let $option = $("<option>")
                    .text(`\${year}年\${seasonText}`)
                    .attr("data-year", year)
                    .attr("data-season", season);
                if (i === 0) {
                    $("#queryRangeLabel").text(`\${year}年\${seasonText}`);
                    $("#queryRangeLabel").attr("year", year).attr("season", season);

                }
                $select.append($option);
                // 退回上一季度
                season--;
                if (season === 0) {
                    season = 4;
                    year--;
                }
            }
        }

    });

</script>
<!-- END CUSTOM LEVEL JS-->
</body>
</html>