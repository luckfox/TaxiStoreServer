
function initTableP2(tagTable, options) {
    let selectedRowIndex = -1;

    const table = $(tagTable).DataTable({
        processing: false,
        serverSide: false,
        searching: false,
        paging: false,
        info: false,
        scrollY: "200px",
        scrollX: true,
        scrollCollapse: false,
        fixedHeader: { header: true },
        //針對每產生一個row，就
        createdRow: function (row, data, dataIndex) {
            $('td', row).css('white-space', 'nowrap');
        },
        columns: [
            { data: "id", title: "單據編號", width: "30px" },
            {
                data: "createdate",
                title: "建立日期",
                render: function (data) {
                    if (!data) return "";
                    let dateObj = new Date(data);
                    return `${dateObj.getFullYear()}/${String(dateObj.getMonth() + 1).padStart(2, "0")}/${String(dateObj.getDate()).padStart(2, "0")}`;
                }
            },
            {
                data: "createdate",
                title: "建立時間",
                render: function (data) {
                    if (!data) return "";
                    let dateObj = new Date(data);
                    return `${String(dateObj.getHours()).padStart(2, "0")}:${String(dateObj.getMinutes()).padStart(2, "0")}`;
                }
            },
            { data: "userName", title: "打單人員" },
            {
                data: null,
                title: "操作",
                width: "30px",
                orderable: false,
                render: function (data, type, row) {
                    return `
                        <button class="btn btn-danger btn-sm delete-row" data-id="${row.id}">
                            <i class="ft-trash-2 position-right"></i>
                        </button>
                        <button class="btn btn-success btn-sm report-row" data-id="${row.id}">
                            <i class="ft-upload"></i>
                        </button>`;
                }
            }
        ]
    });

    function selectRowByIndex(index)
    {
        $(tagTable + " tbody tr").removeClass("selected");
        let row = $(tagTable + " tbody tr").eq(index).addClass("selected");
        selectedRowIndex = index;


        //let rowData = table.row(index).data();
        let rowData = table.row(row).data();  // ✅ 改這裡
        console.log(`selectedRowIndex=${selectedRowIndex} rowData.id=${rowData.id}`);
        if (rowData) {
            $("#recvFormId").text(rowData.id);
            if (typeof options.onRowSelect === "function") {
                options.onRowSelect(rowData.id);
            }
        }

        if (row.length) {
            // 先讓 DataTables 強制調整欄寬與重繪
            table.columns.adjust().draw(false);

            // 再延遲執行 scrollIntoView
            setTimeout(() => {
                row[0].scrollIntoView({ behavior: "smooth", block: "nearest" });
            }, 30); // 設個合理延遲（30~50ms）
        }

    }

    // 點選行事件
    $(tagTable + " tbody").on("click", "tr", function ()
    {
        selectedRowIndex = table.row(this).index();
        console.log(`click Index=${selectedRowIndex}`);
        selectRowByIndex(selectedRowIndex);

    });

    // 鍵盤上下鍵選取
    $(document).on("keydown "+tagTable, function (e)
    {
        const rowCount = table.rows().count();

        if (e.key === "ArrowUp" && selectedRowIndex > 0)
        {
            selectedRowIndex--;
            console.log(`ArrowUp Index=${selectedRowIndex}`);
            selectRowByIndex(selectedRowIndex);
        } else if (e.key === "ArrowDown" && selectedRowIndex < rowCount - 1)
        {
            selectedRowIndex++;
            console.log(`ArrowDown Index=${selectedRowIndex}`);
            selectRowByIndex(selectedRowIndex);
        }
    });

    // 刪除按鈕事件
    $(tagTable + " tbody").on("click", ".delete-row", function () {
        let rowId = $(this).data("id");
        let row = $(this).closest("tr");

        if (confirm("確定要刪除這筆資料嗎？")) {
            $.post("recvformServlet", {
                action: "ajaxDeleteRecvForm",
                recvFormId: rowId
            }, function (response) {
                if (response.success) {
                    table.row(row).remove().draw(false);

                    setTimeout(() => {
                        let firstRow = $(tagTable + " tbody tr:first");
                        if (firstRow.length) {
                            selectedRowIndex = 0;
                            firstRow.addClass("selected").siblings().removeClass("selected");

                            let newSelectedRowData = table.row(firstRow).data();
                            if (newSelectedRowData) {
                                $("#recvFormId").text(newSelectedRowData.id);
                                if (typeof options.onRowSelect === "function") {
                                    options.onRowSelect(newSelectedRowData.id);
                                }
                            }
                        }
                    }, 100);

                    alert(`刪除(${rowId})成功！`);
                } else {
                    alert("刪除失敗：" + response.message);
                }
            }, "json");
        }
    });

    // 上傳按鈕事件
    $(tagTable + " tbody").on("click", ".report-row", function () {
        let rowId = $(this).data("id");

        $.post("recvFormServlet", {
            action: "upload",
            id: rowId
        }, function (response) {
            if (response.success) {
                alert("上傳成功！");
            } else {
                alert("上傳失敗：" + response.message);
            }
        }, "json");
    });
    console.log(`initTableP2(${tagTable}) OK`);
    return table;
}
