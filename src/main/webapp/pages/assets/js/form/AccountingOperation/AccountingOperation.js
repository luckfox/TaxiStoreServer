/*
    此部分提供了AccountingOperation.jsp適用的相關函數。
 */


/*
    根據recvFormId去後台讀取所有的OrderEntry
 */
function loadRecvEntries(recvFormId,tagTable,callback)
{
    console.log(`loadRecvEntries 1.00 recvFormId=${recvFormId} table=${tagTable}`);
    $.getJSON("recvEntryServlet", {
        action: 'ajaxGetItemsByRecvFormId',
        RecvFormId: recvFormId
    }, function (result) {
        if (!result.success) {
            console.error("Error: Failed to retrieve data.");
            return;
        }

        let entries = result.data;
        let table=$(tagTable).DataTable();
        //檢驗DataTable是否完成initialized
        if (!$.fn.DataTable.isDataTable(table)) {
            console.warn("Warning: DataTable is not initialized yet.");
            return;
        }

        console.log("Table columns count:", table.columns().count());
        if (table.columns().count() < 9) {
            console.error("Error: Column index 8 (entryOrder) is out of bounds");
            return;
        }

        table.clear().rows.add(entries).order([8, 'asc']).draw(); // 依 entryOrder 排序
        callback();
        // 設定選取最後一行並滾動

    });

}
/*
    透過situationId取得所有的recvForm存放於tagTable1，同時會selected最新一筆資料，同時會根據此最新一筆的recvForm資料
    將讀取的recvEntry存放於tagTable2
    @param situationId
    @param tagTable1 存放recvForm的table
    @param tagTable2 存放recvEntry的table
 */
/*
 * 透過 situationId 取得所有的 recvForm 存放於 tagTable1，
 * 並自動選取最新一筆資料（依 createDate 降冪排序第一筆），
 * 根據這筆 recvForm 資料再查詢 recvEntry 存放於 tagTable2。
 *
 * @param situationId 傳入情境編號
 * @param tagTable1 存放 recvForm 的 table selector（如 "#table1"）
 * @param tagTable2 存放 recvEntry 的 table selector（如 "#table2"）
 */

function reflashRcvFrom(situationId, tagTable1, tagTable2) {
    console.log(`reflashRcvForm situationId=${situationId} tagTable1=${tagTable1} tagTable2=${tagTable2}`);

    $.ajax({
        url: "recvformServlet",
        type: "GET",
        data: {
            action: "ajaxGetRecvFormsBySituationId",
            SituationId: situationId
        },
        dataType: "json",
        success: function (response) {

            let table1 = $(tagTable1).DataTable();

            // 1.清空table1的內容，並且將透過getJSON取得的recvForm資料，丟到table1。
            table1.clear().rows.add(response.data);
            console.log("ajaxGetRecvFormsBySituationId done! count=", table1.rows().count());

            // 2.根據 id 進行排序（降序，最後產生的資料在最前）
            table1.order([0, 'desc']).draw();
            // 3.設置最新的一行為 selected
            setTimeout(() => {
                // 取得最新一行（索引 0）
                let lastRow = table1.row(0).node();
                //let lastRow = table1.row(':eq(0)', { order: 'applied' }).node();
                //確定至少有lastRow存在
                if (lastRow) {
                    //加註.selected ，表明此列為被選中。
                    $(lastRow).addClass("selected").siblings().removeClass("selected");
                    console.log("set last row on index=", $(lastRow).index())
                    // 確保鍵盤焦點不會消失
                    $(lastRow).focus();
                    // ✅ 取得 selected row 的 id
                    let selectedData = table1.row(0).data(); // 取得該行的資料
                    if (selectedData)//如果該筆RecvForm存在，則取出該資料，並取得所有RecvEntry
                    {
                        console.log(`Selected Row ID:${selectedData.id}`);
                        $("#recvFormId").text(selectedData.id);
                        loadRecvEntries(selectedData.id, tagTable2, function () {
                            setTimeout(() => {

                                let lastRow = $(tagTable2 + ` tbody tr:last`);
                                if (lastRow.length) {
                                    lastRow.addClass("selected");
                                    lastRow[0].scrollIntoView({behavior: "smooth", block: "nearest"});
                                }
                            }, 200);
                        });
                    }
                }
            }, 500);
        },
        error: function (xhr, status, error) {
            console.error("Error fetching data:", error);
        }
    });
}

/*
    將.getJSON取得的資料，按照key去核對html內符合id的元素。
 */

function replaceData(result)
{

    AllFormControlClean2(["fc-0"]); //清除所有帶有".fc-0" 控制項(input/label)的內容。
    $("#serialNoTitle").text(result.situationId);
    $('#createDate').text(result.createDate);

    console.log(result);
    //根據傳回值的Key，去尋找相對應的元素id相符者
    $.each(result, function (key, value) {
        let element = $("#" + key);
        let tagName = element.prop("tagName");
        if (element.length > 0 && tagName != null) {
            //元素存在。

            //1.檢查此id所在的元素 是否為select
            if (tagName === "SELECT")
            {
                value = String(value);
                let matched = element.find(`option[value="${value}"]`);
                if (matched.length > 0)
                {
                    console.log(`✅ Select matched: setting value to ${value}`);
                    element.val(value);
                } else
                {
                    console.warn(`❌ Select id=${key} has no matching option for value=${value}`);
                    console.log("All available options:", element.find("option").map((i, e) => $(e).val()).get());
                    element.append(`<option value="${value}" selected>（未知值：${value}）</option>`);
                }
            } else if (tagName === "LABEL")
            {
                //2.檢查此id所在的元素 是否為label
                element.html(value); // 将内容修改为 value
            } else
            {
                element.val(value);
            }

        }
    });
}
