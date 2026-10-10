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
<div class="app-content content">
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-12 col-12 mb-0 breadcrumb-new">
                <h3 class="content-header-title mb-0 d-inline-block">驗車日期查詢</h3>
                <h3 class="content-header-title d-inline-block "><span>月份查詢:</span><span id="queryMonthLabel"  month=""></span></h3>
                <div class="row">
                    <div class="col-12">
                        <!-- 🔹 搜尋框 & Show Entries 在同一行 -->
                        <div class="d-flex justify-content-between align-items-center mb-2">
                                    <!-- 左側：Show Entries 選單 -->
<%--                                    <div id="VI_table_length"></div>--%>
                                    <div class="dataTables_length d-flex align-items-center">
                                        <label class="me-2">月份選擇  </label>
                                        <select id="customLength" class="form-select input-group-text me-2">
                                        </select>
                                        <label></label>
                                    </div>

                                    <!-- 右側：搜尋欄 -->
                                    <div class="d-flex">
                                        <select id="searchColumn" class="form-select input-group-text me-1">
                                            <option value="displayName" selected>代稱</option>
                                            <option value="driverId">身分證號</option>
                                            <option value="driverSC">特別碼</option>
                                            <option value="driverName">駕駛員名稱</option>
                                            <option value="tag">標籤</option>
                                        </select>
                                        <input type="text" id="searchValue" placeholder="搜尋內容..." class="form-control fc-0 w-50">
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

                <div class="row d-inline-block">
                    <div class=" mr-1">
                        <ol>
                            <li><h3 style="color:white"><span>流水編號:</span><span
                                    id="serialNoTitle">${requestScope.company.companyseq}</span></h3>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


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
                            <input type=hidden  id="P2_SerialId" value=""  >
                            <input type=hidden  id="P2_UserId" value=""  >
                            <input type="text" id="P2_DriverName" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>

                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_CarNo" class="input-group-text ">車號 </label>
                            <input type="text" id="P2_CarNo" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>
                    </div>

                    <div class="row mt-2 ">
                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_CompanyName" class="input-group-text ">車行名稱</label>
                            <input type="text" id="P2_CompanyName" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>

                        <div class="input-group col-md-6 mb-2">
                            <label for="P2_ShingleDat" class="input-group-text ">掛牌日期 </label>
                            <input type="text" id="P2_ShingleDat" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-sm-12">
                            <label for="P2_Message" class="input-group-text ">訊息</label>
                            <div class="position-relative has-icon-left">
                                <textarea type="text" id="P2_Message" class="form-control update-enabled fc-0"></textarea>
                                <div class="form-control-position pl-1"><i class="ft-message-circle"></i></div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="P2_MessageBtn"  data-carno="" data-shingledat="" class="btn btn-toolbar" >測試</button>
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
                            <input type=hidden  id="editSerialId" value=""  >
                            <input type="text" id="editUserId" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>
                        <!-- displayName Line User 本身登錄名稱-->
                        <div class="input-group col-md-6 mb-2">
                            <label for="editDisplayName" class="input-group-text ">Display Name</label>
                            <input type="text" id="editDisplayName" class="form-control fc-0" aria-describedby="basic-addon1" readonly >
                        </div>
                    </div>

                    <div class="row mt-2">
                        <!-- driverPortable -->
                            <div class="input-group col-md-6 mb-2">
                                <label class="input-group-text ">Portable</label>
                                <input type="text" class="form-control  fc-0"
                                       id="editDriverPortable" name="editDriverName" aria-describedby="basic-addon1" readonly>
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
                            <input type="text" id="editDriverSC" class="form-control update-enabled fc-0"  >
                            <!-- List container for search results -->
                            <ul class="dropdown-menu list-window w-25" id="autocomplete-list-editDriverSC"/>
                        </div>
                        <!-- driverID -->
                        <div class="input-group col-md-6  mb-2">
                            <label for="editDriverId" class="input-group-text ">Driver ID</label>
                            <input type="text" id="editDriverId" class="form-control update-enabled fc-0" >
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
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script src="<%= app_assetsPath %>/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js"></script>
<%--<script src="<%= app_assetsPath %>/js/scripts/tables/datatables-extensions/datatables-sources.js" type="text/javascript"></script>--%>
<script type="text/javascript">

    $(function ()
    {

        let servlet_url1="lineBotServlet";
        let servlet_url2="welcomeServlet";
        let originalData = {};//存放原始資料

        console.log("vehicle-inspection.jsp ver 1.00");

        //設定leftSideMenu.jsp 的階層式選單，避免重複點選頁面
        $("#apps").attr("class", "open");
        console.log("set #dashboard class=open");
        $("#vehicle-inspection").attr("class", "active");

        initMonth();

        if ($.fn.DataTable.isDataTable('#VI_table'))
        {
            $('#VI_table').DataTable().destroy();  // 銷毀已存在的 DataTable
        }
        function formatDate(dateString) {
            if (!dateString) return ""; // 如果是空值，返回空字串
            let date = new Date(dateString);
            return date.toISOString().split('T')[0]; // 轉換為 YYYY-MM-DD
        }
        //DataTable 設置
        $('#VI_table').DataTable({
            processing: true,  // 顯示 "處理中" 指示器
            serverSide: true,  // 啟用伺服器端模式
            searching: false,  // 關閉內建搜尋
            info: false,        // 不顯示資訊
            //lengthMenu: [[5, 10, 25, 50, 100], [5, 10, 25, 50, 100]],  // 自訂行數選擇
            pageLength: 10,  // 預設顯示 10 行
            ajax: {
                url: 'situationServlet',  // 後端 API 入口
                type: 'GET',
                data: function(d) {
                    console.log("🔍 DataTables Ajax 發送數據：", d);
                    d.action = 'ajaxGetCarsForInspection';
                    d.queryMonth = $("#queryMonthLabel").attr("month")|| "1";
                    // d.searchColumn= $("#searchColumn").val() || "displayName";  // ✅ 取得選擇的欄位
                    // d.searchValue = $("#searchValue").val().trim();  // ✅ 取得輸入的搜尋值
                    // d.search = d.search.value || '';    // 傳遞 DataTables 的搜尋參數
                     d.orderColumn =d.order[0].column;   // 取得排序的欄位索引
                     d.orderDir=d.order[0].dir;          // 取得排序方向
                }
            },
            order: [[3, 'desc']],  // 預設排序 (如 createDate 降序)
            columns:
            [
                { data: 'carno' , title:"車牌號碼",visible: true},
                { data:'driverSeq',title:"司機序號",visible:false},
                { data: 'driverName' , title:"駕駛人姓名",visible: true},
                { data: 'caryear' ,title:"出廠日期",
                        render: function(data, type, row)
                        {
                            return formatDate(data);
                            }
                },
                { data: 'companyName' ,title:"公司名稱",
                        render:function(data,type,row)
                        {
                            return data||"";
                        }
                },
                { data: 'shingleDat' ,title:"掛牌日期",
                        render: function(data, type, row)
                        {
                            return formatDate(data);
                        }
                },
                {
                    data: null,
                    title: "操作",
                    render: function(data, type, row)
                    {
                        console.log(`carno=\${row.carno} driverName=\${row.driverName} companyName=\${row.companyName}`);

                        let operation = `<button class="btn btn-sm btn-primary edit-btn"      data-id="\${row.driverSeq}" data-carno="\${row.carno}" data-drivername="\${row.driverName}" data-companyname="\${row.companyName}"  data-shingledat="\${row.shingleDat}">編輯</button>`+
                                        `<button class="btn btn-sm btn-danger deletee-btn"    data-id="\${row.driverSeq}" data-carno="\${row.carno}" data-drivername="\${row.driverName}" data-companyname="\${row.companyName}"  data-shingledat="\${row.shingleDat}">刪除</button>`+
                                        `<button class="btn btn-sm btn-secondary message-btn" data-id="\${row.driverSeq}" data-carno="\${row.carno}" data-drivername="\${row.driverName}" data-companyname="\${row.companyName}"  data-shingledat="\${row.shingleDat}">訊息</button>`;

                        return operation;
                    }
                }
            ]
            // ,createdRow: function(row, data, dataIndex) {
            //     $(row).attr("data-user-id", data.userId);  // 🔹為每一個row都補上一個ID， 設定 <tr data-user-id="12345">
            // }
            ,initComplete: function() {
                $("#VI_table_filter").empty();  // 隱藏 DataTables 預設搜尋框
                $("#VI_table_length").empty();  // 隱藏 DataTables 預設的 Show Entries
            }
        });
        //產生驗車訊息內容
        $('#VI_table tbody').on('click','.message-btn',function (){

            let driverSeq = $(this).data('id');
            let carno=$(this).data('carno');
            let shingleDat=formatDate($(this).data('shingledat'));
            let companyName=$(this).data('companyname');


            //for test
            driverSeq="897";
            console.log(`companyName=\${companyName} shingleDat=\${shingleDat}`);
            console.log(`sendMessage: driverSeq=\${driverSeq} carno=\${carno}`);
            let url=`lineUserServlet?action=ajaxGetUserWithDriverByDriverSeq&DriverSeq=`+driverSeq;

            $.getJSON(url, function(result) {
                if (result.success!==true)
                {
                    alert(result.errorMessage);
                    return;
                }
                let lineUserInfo = result.data;
                console.log("lineUserInfo=",lineUserInfo);
                if(!lineUserInfo)
                {
                    alert("此位司機尚未建立LineUser 連結!;")
                    return ;
                }

                console.log(`message-btn clicked by driverSeq=\${lineUserInfo.driverSeq} driverName=\${lineUserInfo.driverName}`);
                $('#P2_UserId').val(lineUserInfo.userId);
                $('#P2_DriverName').val(lineUserInfo.driverName);
                $('#P2_ShingleDat').val(shingleDat);
                $('#P2_CarNo').val(carno);
                $(`#P2_CompanyName`).val(companyName);
                $('#P2_Message').val("");


                $('#P2_MessageBtn').attr("data-shingledat",shingleDat).attr("data-carno",carno);
                $('#P2_Modal').modal('show'); // 打開編輯視窗
                toggleInputs(true);

            });


        });


        // 綁定編輯按鈕
        $('#VI_table tbody').on('click', '.edit-btn', function() {
            //data('serialID') 可以得到.edit-btn下的屬性 data-serialID
            let serialID = $(this).data('id');
            console.log('edit-btn clicked by serialID='+serialID);
            editUser(serialID);

        });

        // 綁定刪除按鈕
        $('#VI_table tbody').on('click', '.delete-btn', function() {
            //data('serialID') 可以得到.edit-btn下的屬性 data-serialID
            let serialID = $(this).data('id');
            console.log('delete-btn clicked by serialID='+serialID);
            deleteUser(serialID);
        });

        let table = $('#VI_table').DataTable();
        // 🔹 自訂 Show Entries
        $("#customLength").change(function()
        {
            let option = $(this).find("option:selected");     //找出目前被selected的選項。
            let selectedValue = $(option).val();
            let selectedText = $(option).text().trim();
            $("#queryMonthLabel").attr("month",selectedValue);
            $("#queryMonthLabel").text(selectedText);
            table.draw();
        });
        // 🔹 搜尋欄位 - 按 Enter 觸發搜尋
        $("#searchValue").keypress(function(event) {
            if (event.which === 13) {  // 13 = Enter 鍵
                let columnIndex = $("#searchColumn").val();  // 取得選擇的欄位索引
                let searchValue = $("#searchValue").val().trim();  // 取得搜尋值

                if (searchValue) {
                    table.column(columnIndex).search(searchValue).draw();  // ✅ 只搜尋選定的欄位
                } else {
                    table.column(columnIndex).search("").draw();  // 若搜尋值為空，則清空搜尋
                }
            }
        });

        /**
         * 點擊某個user的edit按鈕。
         * @param userId
         */
        function editUser(serialID)
        {
            let url=`lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=`+serialID;
            console.log("editUser："+url);
            $.getJSON(url, function(result) {
                if (result.success)
                {
                    let user = result.data;

                    //console.log("driverId="+user.driverId+":driverSC="+user.driverSC);
                    //將回傳的資料填入編輯視窗
                    $('#editSerialId').val(serialID);
                    $('#editUserId').val(user.userId);
                    $('#editDisplayName').val(user.displayName);
                    $('#editTag').val(user.tag);
                    $('#editDriverId').val(user.driverId);
                    $('#editDriverSC').val(user.driverSC);
                    $('#editDriverName').val(user.driverName);
                    $('#editDriverPortable').val(user.driverPortable);
                    $('#editDriverSeq').val(user.driverSeq); //存放原始的DriverSeq
                    $('#editModal').modal('show'); // 打開編輯視窗
                    toggleInputs(true);
                } else
                {
                    alert(result.errorMessage);
                }
            });
        }


        $('#VI_table').on('draw.dt', function()
        {
            $('#VI_table td').css('color', 'yellow');

        });

        let Input_driverSC = $('#editDriverSC');
        let Input_driverId = $('#editDriverId');

        const dropdown_editDriverId = $('#autocomplete-list-editDriverId');
        const dropdown_editDriverSC = $('#autocomplete-list-editDriverSC');
        const inputMappings = [
            { input: Input_driverSC, dropdown: dropdown_editDriverSC, api: 'driverServlet',action:'ajaxGetDriversBySpecialCode' },
            { input: Input_driverId, dropdown: dropdown_editDriverId, api: 'driverServlet',action:'ajaxGetDriversByDriverId' }
        ];
        /**
         * 對inputMappings裡面的每個row，設定input/keydown的事件觸發的handle
         */
        inputMappings.forEach(({input,dropdown,api,action})=>{
            input.on('input keydown',(e)=>input_handler(e,input,dropdown,api,action));
        });

        function input_handler(e, inputField, dropdownList, apiUrl, action) {
            const input_value = inputField.val().trim();
            console.log("input_value=" + input_value);

            if (input_value.length < 2) return; // 少於 2 個字元則不查詢
            if (e.type === 'keydown' && e.key !== 'Enter') return; // 只有按 Enter 才觸發查詢

            // 發送 AJAX 請求
            $.getJSON(apiUrl, {
                action: action,
                key: input_value,
                start: 0,
                length: 0
            }, function (result) {
                console.log("result.data.length=" + result.data.length);

                if (!result.success || result.data.length === 0) {
                    dropdownList.removeClass('show'); // 無資料時隱藏選單
                    return;
                }

                // 設置 Bootstrap Table
                let dropdownContent = `
        <div class="dropdown-table-container" style="max-height: 250px; overflow-y: auto; border: 1px solid #ccc;">
            <table class="table table-bordered table-hover table-sm" style="margin: 0;">
                <thead class="thead-dark" style="position: sticky; top: 0; background-color: #343a40; color: white; z-index: 10;">
                    <tr>
                        <th scope="col" style="width: 50%;; white-space: nowrap;">身分證號</th>
                        <th scope="col" style="width: 30%; white-space: nowrap;">姓名2</th>
                        <th scope="col" style="width: 20%; white-space: nowrap;">特別碼</th>
                    </tr>
                </thead>
                <tbody">`;

                // 產生dropdown-item表格選項(由driver table撈資料回來)
                dropdownContent += result.data.map(item => {

                    //console.log("item:", item);

                    return `<tr class="dropdown-row table-light" ` +
                        `data-driver-id="` + item.id + `" ` +
                        `data-driver-name="` + item.drivername + `" ` +
                        `data-driver-sc="` + item.specialcode + `" ` +
                        `data-driver-portable="` + item.portable + `" ` +
                        `data-driver-seq="` + item.driverseq + `" ` +
                        `style="cursor: pointer;"> ` +
                        `<td  style="width: 50%; white-space: nowrap;">` + item.id + `</td> ` +
                        `<td  style="width: 30%; white-space: nowrap;">` + item.drivername + `</td> ` +
                        `<td  style="width: 20%; white-space: nowrap;">` + item.specialcode + `</td> ` +
                        `</tr>`;
                }).join('');

                dropdownContent += `</tbody></table></div>`;

                // 設定 dropdown 位置並顯示
                const offset = inputField.position();
                dropdownList.css({
                    top: inputField.outerHeight(),
                    left: offset.left
                }).html(dropdownContent).addClass('show');

                // 綁定dropdown-item點擊事件，點擊行後填入表單
                dropdownList.find('.dropdown-row').click(function () {


                    $("#editDriverId").val($(this).data("driver-id"));
                    $("#editDriverName").val($(this).data("driver-name"));
                    $("#editDriverSC").val($(this).data("driver-sc"));
                    $("#editDriverPortable").val($(this).data("driver-portable"));
                    $("#editDriverSeq").val($(this).data("driver-seq"));
                    dropdownList.removeClass('show'); // 隱藏選單
                    console.log("editDriverId=" + $("#editDriverId").val() + ":editDriverName=" + $("#editDriverName").val() + ":editDriverSeq=" + $("#editDriverSeq").val());
                });
            });
        }
        $(document).on('click', '#warningMsg-button', function () {
            console.log("#warningMsg-button clicked");
            $('#inputMessage1').val("55555"); // 設置值
        });

        $(document).click(function (e)
        {
            if (!$(e.target).closest(dropdown_editDriverId).length ) {
                dropdown_editDriverId.removeClass("show");
            }
            if (!$(e.target).closest(dropdown_editDriverSC).length ) {
                dropdown_editDriverSC.removeClass("show");
            }

        });

        $('#editModal').on('hidden.bs.modal', function () {
            $(this).attr('inert', ''); // 讓 Modal 完全無法被聚焦
        }).on('shown.bs.modal', function ()
        {
            $(this).removeAttr('inert'); // 移除 inert 以恢復可用性

            //保存所有數據。
            originalData = {
                userId: $("#editUserId").val(),
                displayName: $('#editDisplayName').val(),
                driverSeq: $("#editDriverSeq").val(),
                tag: $("#editTag").val()
            };
            console.log("originalData:",originalData);
        });
        $("#P2_BtnSubmit").on("click",function(e)
        {
                console.log("123456");
                //e.preventDefault();
                //sendMessageHandler();
        });
        function sendMessageHandler()
        {
            let formData = {
                userId: $("#sendMessageUserId").val(),
                message: $("#sendMessageMessage").val()
            };
            $.getJSON("lineUserServlet",
                {
                    action:  "ajaxPushMessage",
                    userId: $("#sendMessageUserId").val(),
                    message: $('#sendMessageMessage').val(),

                }, function(response)
                {
                    console.log("response：",response);
                    if (response.success===true)
                    {
                        alert("發送成功！");
                        $("#sendMessageModal").modal("hide");


                    } else {
                        alert("發送失敗：" + response.message);
                    }
                }).fail(function(xhr, status, error) {
                console.error("提交失敗:", error);
                alert("發生錯誤，請稍後再試！");
            });
        }
        $("#P2_MessageBtn").on("click",function (e)
        {
            let carno=$(this).attr("data-carno");
            let shingleDat=$(this).attr("data-shingledat");

            let theString=`\${carno}的車主您好，您的驗車日期為\${shingleDat}，請盡速前往驗車，以免受罰!`;

            $("#P2_Message").val(theString);
        });
        $("#btnSubmit").on("click", function (e)
        //$("#editForm").submit(function (e)
        {
            e.preventDefault(); // 防止預設提交行為 (頁面重整)
            sumbitHandler();
        });
        function sumbitHandler()
        {
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
            if(keys1.every(key => originalData[key] === formData[key]))
            {
                console.log("資料沒有改變!");
                $("#editModal").modal("hide");
                return ;
            }

            let data=JSON.stringify({
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
                 action:  "ajaxUpdateByUserId",
                 userId: $("#editUserId").val(),
                 displayName: $('#editDisplayName').val(),
                 driverSeq: $("#editDriverSeq").val(),
                 tag: $("#editTag").val()

            }, function(response)
            {
                if (response.success===true)
                {
                    alert("更新成功！");
                    $("#editModal").modal("hide");

                    let row = $("#dataTable").find(`tr[data-user-id="${userId}"]`);
                    // loadDataTable(); // 如果有函式要刷新表格
                    updateTableRow(formData.userId, formData);

                } else {
                    alert("更新失敗：" + response.message);
                }
            }).fail(function(xhr, status, error) {
                console.error("提交失敗:", error);
                alert("發生錯誤，請稍後再試！");
            });
        }
        function updateTableRow(userId,newData)
        {
            let table = $('#VI_table').DataTable();

            // 🔍 找到符合 userId 的列
            let row = table.rows(function(idx, data, node) {
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

        function initMonth()
        {
            const $select = $("#customLength");
            for (let i = 0; i < 12; i++) {
                let $option = $("<option>").text(`\${i+1}月`).val(i+1);
                if (i === 0) {
                    $("#queryMonthLabel").attr("month",i+1);
                    $("#queryMonthLabel").text(`\${i+1}月`);
                }
                $select.append($option);
            }

        }
    });



</script>
<!-- END CUSTOM LEVEL JS-->
</body>
</html>