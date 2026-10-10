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
                <h3 class="content-header-title mb-0 d-inline-block">公司資料建檔</h3>
                <div class="row">
                    <div class="col-12">
                        <!-- 🔹 搜尋框 & Show Entries 在同一行 -->
                        <div class="d-flex justify-content-between align-items-center mb-2">
                                    <!-- 左側：Show Entries 選單 -->
<%--                                    <div id="VI_table_length"></div>--%>
                                    <div class="dataTables_length d-flex align-items-center">
                                        <label class="me-2">Show  </label>
                                        <select id="customLength" class="form-select input-group-text me-2">
                                            <option value="5">5</option>
                                            <option value="10" selected>10</option>
                                            <option value="25">25</option>
                                            <option value="50">50</option>
                                            <option value="100">100</option>
                                        </select>
                                        <label>entries</label>
                                    </div>

                                    <!-- 右側：搜尋欄 -->
                                    <div class="d-flex">
                                        <select id="searchColumn" class="form-select input-group-text me-1">
                                            <option value="all" selected >全部</option>
                                            <option value="displayName" >代稱</option>
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


<div id="modalContainer"></div>
<!-- 🔥 編輯彈出視窗 (放在這裡，靠近 body 結尾) -->


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

<%--<script type="text/javascript" src="<%= assetsPath %>/js/modal/LineUserInfoEditor.js?<%= version %>"></script>--%>
<script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"></script>
<script src="<%= app_assetsPath %>/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/DynamicModalEditor.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/DynamicModalHelper.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
<%--<script type="text/javascript" src="<%= assetsPath %>/js/form/LineUser-profile/deleteLineUserInfo.js?<%= version %>"></script>--%>
<script type="text/javascript" src="<%= assetsPath %>/js/form/LineUser-profile/vi_table.js?<%= version %>"></script>
<script type="text/javascript">

    $(function ()
    {

        let servlet_url1="lineBotServlet";
        let servlet_url2="welcomeServlet";
        let originalData = {};//存放原始資料
        //提供給modal使用，用來作為modal內部所有id的前綴，避免發生id重複。
        let modal1_PreFix=new Date().getTime()+"_";
        let $userInfoEditorModal_ID;
        let sendMessageEditorModal_ID="#LineMessageEditor"
        console.log("lineUser-profile.jsp ver 1.00");
        let $modalContainer=$('#modalContainer');
        //設定leftSideMenu.jsp 的階層式選單，避免重複點選頁面
        $("#apps").attr("class", "open");
        console.log("set #dashboard class=open");
        $("#lineUser-profile").attr("class", "active");

        /*
            提供給table的callback函數清單。
         */
        const tableCallbacks = {
            onEdit: (id, btn) =>{
                const editor=editHandler(id,btn);
                editor.open();
            },
            onDelete: (id, btn) =>{
                //deleteUser?.(id, btn,$modalContainer)                   //deleteUser.js
            },
            onMessage: (id, btn) => {
                //sendLineUserMessage?.(id, btn, $modalContainer)         //sendLineUserMessage.js
                const modal=lineMessageHandler(id,btn);
                modal.open();
            }
        };
        let table = initVI_table('#VI_table',tableCallbacks);

        sendColumnListToServlet('#VI_table');
        // 🔹 自訂 Show Entries
        $("#customLength").change(function()
        {
            let newLength = $(this).val();
            table.page.len(newLength).draw();
        });
        // 🔹 搜尋欄位 - 按 Enter 觸發搜尋
        $("#searchValue").keypress(function(event)
        {
            const searchColumn = $("#searchColumn").val();   // ✅ 取得搜尋欄位
            const searchValue = $("#searchValue").val().trim();  // ✅ 取得搜尋值

            console.log(`#searchColumn = ${searchColumn}, #searchValue length = ${searchValue.length}`);

            if (event.which === 13) {
                if (searchColumn === "all" || searchValue.length >= 2) {
                    table.draw();  // ✅ 搜尋執行
                } else {
                    console.warn("搜尋字元太短，請輸入至少兩個字！");
                }
            }
        });


        $(document).click(function (e)
        {
            // if (!$(e.target).closest(dropdown_editDriverId).length ) {
            //     dropdown_editDriverId.removeClass("show");
            // }
            // if (!$(e.target).closest(dropdown_editDriverSC).length ) {
            //     dropdown_editDriverSC.removeClass("show");
            // }
            // console.log("...");
        });
        function lineMessageHandler(id,btn)
        {
            const initUrl = `lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=\${id}`;
            const modalPath=${applicationScope.config.get('modal.lineMessageEditor.html.path')}+"?_="+ new Date().getTime();
            return new DynamicModalEditor(btn, $modalContainer, modalPath, {
                getOpenRequest: () => (
                    {
                        url: initUrl
                    }
                ),
                onBtnClick:(btnInfo)=>{
                    console.log(`btn.id=\${btnInfo.id} click`);
                }
            });
        }
        function editHandler(id,btn)
        {
            return new DynamicModalEditor(btn, $modalContainer, "/pages/layout/modals/LineUserInfoEditor.html", {
                getOpenRequest: () => ({
                    url: `lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=\${id}`
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
        function sendColumnListToServlet(tagTable)
        {
            const columns = getDataTableColumnTitles(tagTable);
            const formData = new URLSearchParams();

            columns.forEach(col => formData.append('columns', col));

            fetch('lineUserServlet?action=ajaxUpdateColumnList', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formData.toString()
            }).then(res => res.json())
                .then(json => console.log('回應：', json))
                .catch(err => console.error('錯誤：', err));
        }


        $("#testBtn").on("click",function (e)
        {
            $("#sendMessageMessage").val($("#sendMessageMessage").val()+"12345");
        });
        /*
            針對userInfoEditor Modal中的Submit行為做處理。

         */
        $("#btnSubmit").on("click", function (e)
        //$("#editForm").submit(function (e)
        {
            e.preventDefault(); // 防止預設提交行為 (頁面重整)
            //sumbitHandler();
        });

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
    });



</script>
<!-- END CUSTOM LEVEL JS-->
</body>
</html>