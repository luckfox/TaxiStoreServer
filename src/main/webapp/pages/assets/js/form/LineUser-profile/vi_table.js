//DataTable 設置
function initVI_table(tagTable,callbacks = {})
{
    // 安全地定義 callback，沒傳就給空函數，不會爆炸
    const safeCall = (fn, ...args) => typeof fn === 'function' ? fn(...args) : console.warn("未提供對應 callback");

    let table=$(tagTable).DataTable({
        processing: true,  // 顯示 "處理中" 指示器
        serverSide: true,  // 啟用伺服器端模式
        searching: true,  // 關閉內建搜尋
        lengthMenu: [[5, 10, 25, 50, 100], [5, 10, 25, 50, 100]],  // 自訂行數選擇
        pageLength: 12,  // 預設顯示 10 行,會以length 參數傳送。
        ajax: {
            url: 'lineUserServlet',  // 後端 API 入口
            type: 'GET',
            data: function(d) {

                d.action = 'ajaxGetLineUsersList';
                d.searchColumn= $("#searchColumn").val() || "displayName";  // ✅ 取得選擇的欄位
                d.searchValue = $("#searchValue").val().trim() || '';  // ✅ 取得輸入的搜尋值
                d.orderColumn =d.order[0].column;   // 取得排序的欄位索引
                d.orderColumnName = d.columns[d.order[0].column].data;  // 取得排序的欄位名稱
                d.orderDir=d.order[0].dir;          // 取得排序方向
                console.log(`d.searchColumn=${d.searchColumn} d.searchValue=${d.searchValue} d.search=${d.search} d.orderColumn=${d.orderColumn} d.orderColumnName=${d.orderColumnName} d.orderDir=${d.orderDir}`);
            }
        },
        order: [[3, 'desc']],  // 預設排序 (如 createDate 降序)
        columns:
            [
                { data: 'serialID' , title:"序號",visible: false},
                { data: 'userId' , title:"Line",
                    render:function(data,type,row) {
                        return "*";
                    },visible:false},
                { data: 'displayName' ,title:"顯示名稱",width:"12%"},
                { data: 'tag' ,title:"標籤",width:"10%"},
                { data: 'createDate' ,title:"建檔日期",width:"10%",
                    render: function(data, type, row)
                    {
                        return formatDate(data);
                    }},
                { data: 'driverSeq' ,title:"駕駛編號",visible: false,
                    render: function (data, type, row)
                    {
                        return data != null ? data : "";  // 👈 null 就給它空字串，不讓它亂跑
                    }},
                { data: 'driverId' ,title:"駕駛身分證號",width:"8%"},
                { data: 'driverSC' ,title:"特別碼",width:"5%"},
                { data: 'driverName' ,title:"駕駛姓名",width:"8%"},
                { data: 'driverPortable' ,title:"駕駛連絡電話",width:"10%"},
                /*
                    以上是要放進表格的欄位，必須要和<tr>中的<th>數量一致。
                 */
                {
                    data: null,
                    title: "操作",
                    width: "12%",
                    orderable: false, // 👈 加這行禁止排序
                    render: function(data, type, row)
                    {
                        console.log(`@serialID=${row.serialID}:userId=${row.userId}:displayName=${row.displayName}`);
                        let operation = `<button class="btn btn-sm btn-primary edit-btn"       data-id="${row.serialID}" data-userId="${row.userId}" data-displayName="${row.displayName}">編輯</button>
                                         <button class= "btn btn-sm btn-danger delete-btn"     data-id="${row.serialID}" data-userId="${row.userId}" data-displayName="${row.displayName}">刪除</button>
                                         <button class= "btn btn-sm btn-secondary message-btn" data-id="${row.serialID}" data-userId="${row.userId}" data-displayName="${row.displayName}">訊息</button>`;
                        return operation;
                    }
                }
            ],
        createdRow: function(row, data, dataIndex) {
            $(row).attr("data-user-id", data.userId);  // 🔹 設定 <tr data-user-id="12345">
        },
        initComplete: function() {
            $(tagTable+"_filter").empty();  // 隱藏 DataTables 預設搜尋框
            $(tagTable+"_length").empty();  // 隱藏 DataTables 預設的 Show Entries
        }
    });


    $(tagTable+' tbody').on('click','.message-btn',function (){

        let serialID = $(this).data('id');
        console.log(`message-btn clicked by serialID=[${serialID}]`);
        safeCall(callbacks.onMessage, serialID,this);
        //sendMessage(serialID);

    });
// 綁定編輯按鈕
    $(tagTable+' tbody').on('click', '.edit-btn', function() {
        //data('serialID') 可以得到.edit-btn下的屬性 data-serialID
        let serialID = $(this).data('id');
        console.log('edit-btn clicked by serialID='+serialID);
        safeCall(callbacks.onEdit, serialID,this);
        //editUser(serialID);

    });

// 綁定刪除按鈕
    $(tagTable+' tbody').on('click', '.delete-btn', function() {
        //data('serialID') 可以得到.edit-btn下的屬性 data-serialID
        let serialID = $(this).data('id');
        console.log('delete-btn clicked by serialID='+serialID);
        safeCall(callbacks.onDelete, serialID,this);
        //deleteUser(serialID);
    });
    $(tagTable).on('draw.dt', function()
    {
        $(tagTable+' td').css('color', 'yellow');

    });
    return table;
}
/*
 * 取得 DataTable 欄位名稱列表（由 title 欄位構成）
 *
 * @param {string|jQuery} tagTable - DataTable 的 selector，例如 "#myTable"
 * @returns {string[]} 欄位名稱陣列（不包含隱藏欄位除非特別指定）
 */
function getDataTableColumnTitles(tagTable) {
    const table = $(tagTable).DataTable();
    const columns = table.settings().init().columns || [];

    return columns
        .map(col => (typeof col.data === 'string' ? col.data : null))
        .filter(data => data !== null && data !== '');
}