function initTableP4(tagTable)
{
    const table = $(tagTable).DataTable({
        processing: false,  // 顯示 "處理中" 指示器
        serverSide: false,  // 不使用伺服器端模式
        searching: false,   // 關閉內建搜尋
        paging: false,      // 不使用分頁
        info: false,        // 不顯示資訊
        ordering: true,     // 啟用排序
        scrollY: "200px",      // 限制表格高度，讓滾動條生效
        scrollCollapse: false, // 避免 DataTables 自動調整表格高度
        columnDefs: [
            { targets: [0, 1, 5, 7, 8], visible: false } // 隱藏不需顯示的欄位
        ],
        columns: [
            { data: "id", title: "序號" },   // ✅0. 隱藏
            { data: "formId", title: "表單號" }, // ✅ 1.隱藏
            { data: "code", title: "科目編號" ,
                render: function (data, type, row)
                {
                    return data != null ? data : "";  // 👈 null 就給它空字串，不讓它亂跑
                }}, // ✅ 2.顯示
            { data: "accountCode",title: "會計科目",
                render: function (data, type, row)
                {
                    return data != null ? data : "";  // 👈 null 就給它空字串，不讓它亂跑
                }},// ✅ 3.顯示
            { data: "summary", title: "摘要",className:"editable summary" }, // ✅ 4.顯示,添增可編輯的class
            { data: "debitCredit", title: "借貸" }, // ✅ 5.隱藏
            { data: "amount", title: "金額" ,className:"editable amount"}, // ✅ 6.顯示，添增可編輯的class
            { data: "recvFormId", title: "收款單號" }, // ✅ 7.隱藏
            { data: "entryOrder", title: "排序號" }, // ✅ 8.隱藏
            {
                data: null,
                title: "操作",
                orderable: false,
                render: function (data, type, row) {
                    return `<button class="btn btn-danger btn-sm delete-row" data-entryOrder="${row.entryOrder}" data-recvFormId="${row.recvFormId}" data-id="${row.id}"> <i class="ft-trash-2 position-right"></i>刪除</button>`;
                }
            }
        ]
    });


    $(tagTable+" tbody").on("click", "tr", function () {
        table.$("tr.selected").removeClass("selected"); // 清除舊的選取行
        $(this).addClass("selected"); // 設定新選取行
    });


    $(tagTable+" tbody").on("click", ".delete-row", function() {
        let rowId = $(this).attr("data-id");
        let recvFormId=$(this).attr("data-recvFormId");
        let orderId=$(this).attr("data-entryOrder");
       // let table = $("#tableP4").DataTable();
        let row = table.row($(this).closest("tr"));
        console.log(`rowID=${rowId} recvFormId=${recvFormId} orderId=${orderId}`);
        if (confirm(`確定要刪除這筆資料嗎(${recvFormId}:${orderId})？`))
        {
            let url="recvEntryServlet";
            $.getJSON(url,
                {
                    action: "ajaxDeleteRecvEntry",
                    recvFormId:recvFormId,
                    orderId:orderId
                },
                function(result)
                {
                    if (result.success!==true)
                    {
                        alert("刪除失敗: " + response.message);
                    }
                    table.row(row).remove().draw(); // 從表格中刪除行
                });
        }
    });
    console.log(`initTableP4(${tagTable}) OK`);
    return table;
}

