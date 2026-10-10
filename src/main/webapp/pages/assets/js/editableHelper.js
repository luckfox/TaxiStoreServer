/**
 * 初始化 DataTables 欄位可編輯行為
 * @param {object} table - DataTables 實例
 * @param {string[]} editableColumns - 欲編輯的欄位 class 名稱，例如 ['editable amount']
 * @param {function} updateCallback - 當值變更後要執行的處理函式 (rowData, columnIndex, newValue)
 * @param {function} [validateFn] - 可選：驗證函式 (newValue) => boolean
 */
function initEditableBehavior(table, editableColumns, updateCallback, validateFn = null) {

    // 點擊 cell 進入編輯模式
    table.on("click", "td", function () {
        let $cell = $(this);
        if (!$cell.closest("table").hasClass("dataTable")) return; // 安全檢查

        // 檢查欄位是否有指定的 editable class
        if (!editableColumns.some(cls => $cell.hasClass(cls))) return;

        // 避免重複編輯
        if ($cell.hasClass("editing")) return;

        let cell = table.cell(this);
        let oldValue = cell.data();
        let input = $("<input type='text' class='form-control'/>").val(oldValue);

        // 進入編輯狀態
        $cell.addClass("editing").html(input);
        input.focus();


        const commitEdit = () => {
            let newValue = input.val().trim();

            // 驗證失敗 → 還原原值並跳出提示
            if (validateFn && !validateFn(newValue)) {
                cell.data(oldValue).draw(false);        // 還原原值
                $cell.removeClass("editing");
                if (!input.data("alerted")) {
                    //alert("輸入格式不正確，請輸入整數金額");
                    alertBox("輸入格式不正確，請輸入整數金額");
                    input.data("alerted", true);
                }
                return;
            }

            // 沒有改變值
            if (newValue === oldValue) {
                cell.data(oldValue).draw(false);
                $cell.removeClass("editing");
                return;
            }

            let scrollTop = $(table.table().container()).find(".dataTables_scrollBody").scrollTop();
            // 通過驗證 → 更新資料
            cell.data(newValue).draw(false);
            $cell.removeClass("editing");
            // ✅ 還原 scroll
            $(table.table().container()).find(".dataTables_scrollBody").scrollTop(scrollTop);
            let rowData = table.row(cell.index().row).data();
            updateCallback(rowData, cell.index().column, newValue);

        };

        // 事件綁定
        input.on("blur", () => {
            setTimeout(() => {
                commitEdit();
            }, 0);
        });
        input.on("keydown", function (e) {
            if (e.key === "Enter") {
                commitEdit();
            }
        });
    });

    // 點擊頁面其他地方 → 結束目前編輯
    // 處理使用者點擊表格以外的地方，結束編輯
    $(document).on("click", function (e) {
        if (!$(e.target).closest('td.editable, input.form-control').length) {
            table.cells('td.editing').every(function () {
                let cell = this;
                let $cell = $(cell.node());
                let input = $cell.find("input");
                let oldValue = cell.data();
                if (input.length > 0) {
                    let newValue = input.val().trim();

                    if (validateFn && !validateFn(newValue)) {
                        cell.data(oldValue).draw();  // 還原舊值
                    } else {
                        cell.data(newValue).draw(); // 更新值
                        let rowData = table.row(cell.index().row).data();
                        updateCallback(rowData, cell.index().column, newValue);
                    }

                    $cell.removeClass("editing");
                }
            });
        }
    });
}

function alertBox(msg,timeout=2500)
{
    if(timeout<2500) timeout=2500;
    $("#custom-alert").text(msg).fadeIn();
    setTimeout(() => {
        $("#custom-alert").fadeOut();
    }, timeout);
}