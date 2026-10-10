function initTableP3(tagTable)
{
    let table=$(tagTable).DataTable({
        processing: false,  // 顯示 "處理中" 指示器
        serverSide: true,  // 啟用伺服器端模式
        searching: false,  // 關閉內建搜尋
        paging: false,         // 不使用分頁
        info: false,           // 不顯示表格資訊
        scrollY: "200px",      // 限制表格高度，讓滾動條生效
        scrollCollapse: false, // 避免 DataTables 自動調整表格高度
        ajax: {
            url: 'feeServlet', // Servlet 地址
            type: 'POST',
            data: function (d) {
                d.action='ajaxListFees';
            },
            dataSrc: function (json) {  //ajax的返回值。
                if (json.success) {
                    return json.data; // 從 JSON 內的 `data` 取出陣列
                } else {
                    console.error("後端返回錯誤:", json.errorMessage);
                    return []; // 若 `success=false`，回傳空陣列避免 DataTables 出錯
                }
            }
        },
        createdRow: function (row, data, dataIndex) {
            $('td:eq(0)', row).css({
                'white-space': 'nowrap',  // 禁止換行
                'overflow': 'hidden',      // 隱藏超出部分
                'text-overflow': 'ellipsis' // 超出部分顯示省略號（可選）
            });
        },
        columns:
            [   //要展示在DataTable的欄位
                { data: 'id' , title:"序號",visible: false},
                { data: 'code' , title:"科目代碼",visible: true},
                { data: 'accountCode',title:"會計科目",visible: true }
            ]
    });
    console.log(`initTableP3(${tagTable}) OK!`);
    return table;
}
//===========================tableP3==========================================================================
/*
 * 綁定 TableP3 的雙擊事件(選擇會計科目)，用來新增 recvEntry
 * @param tagTable3的 #tableP3
 * @param tagTable4的 #tableP4
 */
function bindTableP3ClickHandler(tagTable3,tagTable4) {
    let isCreatingEntry = false;

    $(tagTable3+" tbody").on("dblclick", "tr", function ()
    {
        if (isCreatingEntry) return;
        isCreatingEntry = true;
        setTimeout(() => { isCreatingEntry = false; }, 1000);  // 防止連點

        const tableP3 = $(tagTable3).DataTable();
        const rowData = tableP3.row(this).data();
        const recvFormId = $("#recvFormId").text().trim();

        if (!rowData) return;

        console.log("被雙擊的 Row ID:", rowData.id);
        console.log("recvFormId:", recvFormId);

        $.getJSON("recvEntryServlet", {
            action: 'ajaxCreateRecvEntry',
            codeId: rowData.id,
            amount: 0,
            summary: "",
            debitCredit: "C",
            recvFormId: recvFormId
        }, function (result)
        {
            if (!result.success)
            {
                console.error("新增失敗:", result.message || "未知錯誤");
                return;
            }

            loadRecvEntries(recvFormId, tagTable4, function () {
                setTimeout(() => {
                    let lastRow = $(tagTable4+" tbody tr:last");
                    if (lastRow.length) {
                        lastRow.addClass("selected");
                        lastRow[0].scrollIntoView({ behavior: "smooth", block: "nearest" });
                    }
                }, 200);
            });
        });
    });
}
