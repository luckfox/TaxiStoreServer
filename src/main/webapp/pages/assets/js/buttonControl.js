/*
 *  @param serialNoElementId 序號的id
 *  @param 目標的serlvet
 *  @param 目標的action
 *  @param callback，收集到資料所要傳送的call back function.
 */
function previousButtonHandler(serialNo, servletUrl, actionName, callback)
{
    nextButtonHandler(serialNo, servletUrl, actionName, callback);
}
function nextButtonHandler(serialNo, servletUrl, actionName, callback)
{
    console.log(`button click, serialNo= ${serialNo} servletUrl=${servletUrl}`);

    if (!serialNo) {
        console.warn("Serial number is empty."); // 提示錯誤
        alert("No sequence found. Please check the input.");
        return;
    }

    console.log("Fetching item for serialNo = " + serialNo);
    fetchCarData(servletUrl, actionName, serialNo, callback);
}



function updateButtonHandler(elem,updateMode,serialNo,servlet,action,ajaxCallBack)
{
    console.log(`addButtonHandlers updateMode=${updateMode} serialNo=${serialNo} servlet=${servlet} action=${action}`);
    if (updateMode === false)
    {   //目前並非編輯模式
        console.log("updateMode=" + updateMode);
        toggleInputs(true); // 启用输入框
        toggleButtons(true, ["#submit-button", "#update-button"]); // 禁用其他按钮，仅保留提交和更新按钮
        //tmpSerialNo = $("#serialNoTitle").text(); // 保存当前序列号
        //console.log("Save serialNo: " + tmpSerialNo);
        $(elem).html("復原 <i class=\"ft-rotate-ccw position-right\"></i>"); // 修改按钮文本
        updateMode = true;//進入編輯模式
        return updateMode;
    }
    console.log("updateMode=" + updateMode);
    // 取消更新操作
    toggleInputs(false); // 禁用输入框
    toggleButtons(false, ["#submit-button"]); // 启用其他按钮，禁用提交按钮
    console.log("Restore serialNo: " + serialNo);
    $.getJSON(`${servlet}`, {action: action, serialNo: serialNo}, ajaxCallBack); // 恢复数据
    $(elem).html("編輯 <i class=\"ft-refresh-cw position-right\"></i>"); // 修改按钮文本
    updateMode = false;
    return updateMode;
}
/*
         * 將表單內所有帶有.update-enabled的項目，保存起來，透過getJSON送至後台。
         *  @param {number} elem -觸發此handler的元素
         * @param {number} serialNo - 序號值，當取消作業時的參考點(用來向後端取回資料)
         * @param {string} servlet - 當取消作業時對應的servlet
         * @param {string} action - 當取消作業時對應的action
         * @param {function} onCallBack - 當取消新增作業時的回call函數。
*/
function addButtonHandler(elem,appendMode,serialNo,servlet,action,onCancel)
{
    console.log(`addButtonHandlers appendMode=${appendMode} serialNo=${serialNo} servlet=${servlet} action=${action}`);
    if (appendMode === false)
    {
        console.log("@@Start appending process");
        toggleInputs(true); // 啟用輸入，只要class帶有 update-enabled，則取消disabled
        AllFormControlClean2(["fc-0,fc-2"]);// 只要class帶有fc-0 fc-2，則清空內容。
        toggleButtons(true, ["#submit-button", "#add-button"]); // 禁用其他按钮，仅保留提交和新增按钮
        $(elem).html("取消 <i class=\"ft-x-circle position-right\"></i>"); // 修改按钮文本
        appendMode = true;
    }else
    {
        /*
         * 取消新增作業
         * 1.啟用其他按鈕，並禁止submit-button
         * 2.根據先前儲存的serialNo，透過sevlet取得相對應的資料。
         */
        console.log("Cancel append process");
        toggleInputs(false); // 禁用输入框
        toggleButtons(false, ["#submit-button"]); // 启用其他按钮，禁用提交按钮
        $(".update-enabled").css({"border-color": "", "border-width": ""});
        $.getJSON(`${servlet}`, {action: action, serialNo: serialNo}, onCancel); // 恢复数据
        $(elem).html("新增 <i class=\"ft-plus-circle position-right\"></i>"); // 修改按钮文本
        appendMode = false;
    }
    return appendMode;
}

/*
         * 將表單內所有input元素，並且帶有.update-enabled的項目，保存起來，透過getJSON送至後台。
         * @param {number} serialNo - "0": 新增, others: 更新的序號
         * @param {string}    key - 後臺對應資料庫的key
         * @param {string} servlet - 處理請求的 Servlet 名稱
         * @param {string} action - 對應的Servlet處理函數
         * @param {function} callback - Ajax 請求的回呼函式
*/
function submitButtonHandler(serialNo,key, servlet, action,callback)
{
    let submit_OK = true;
    console.log(`@@submit serialNo=${serialNo}  servlet=${servlet}`);

    // 1. 清除所有欄位的樣式
    $("input textarea").css("border-color", "").css("border-width", "");

    // 2. 檢查必填欄位是否有空值，如果有空值，則以黃色外框提醒
    $(".update-enabled[required]").each(function () {
        if ($(this).val().trim() === "") {
            submit_OK = false;
            $(this).css("border-color", "yellow").css("border-width", "medium");
        }
    });
    if (!submit_OK) {
        console.log("請注意必要欄位的填寫");
        alert("請注意必要欄位的填寫");
        return;
    } // 若有必填欄位未填，終止操作

    // 3. 元素input具有pattern的屬性。
    //用來檢查輸入的內容是否符合pattern的規範。
    $("input[pattern].update-enabled").each(function () {
        const pattern = $(this).attr("pattern");
        const regex = new RegExp(pattern);
        if (!regex.test($(this).val().trim())) {
            submit_OK = false;
            console.log(`check patten fail! ${this}`);
            $(this).css("border-color", "red").css("border-width", "medium");
        }
    });
    // 若有欄位不符合規範，終止操作
    if (!submit_OK) {
        console.log("請注意該欄位格式的正確性");
        alert("請注意該欄位格式的正確性");
        return;
    }

    // 6.資料進行打包：只要攜帶.update-enabled[name]，則蒐集name和value逕行資料打包。
    // 注意：如果具有diabled屬性，將不會被打包。
    let parameters = "";
    $(".update-enabled[name]").each(function () {
        const name = $(this).attr("name");
        const rawValue = $(this).val(); // 可能為 null
        const value = (rawValue || "").trim(); // 保證不爆炸，null 會變成空字串

        if (value) {
            parameters += `&${name}=${encodeURIComponent(value)}`;
            console.log(`✅ 打包欄位：${name} = "${value}"`);
        } else {
            //console.log(`⚠️ 略過空值欄位：${name}`);
        }
    });

    // 7. 根據 serialNo (0: 新增, other: 更新) 處理不同的提交邏輯
    if (serialNo === "0") // 新增模式
    {
        parameters = `action=${action}&${key}=0`+parameters;
        console.log("新增模式，參數: " + parameters);

        $.getJSON(servlet, parameters, callback);
        $("#add-button").html("新增 <i class=\"ft-plus-circle position-right\"></i>");
        return;
    }
    // 更新模式

    parameters = `action=${action}&${key}=${serialNo}`+parameters;
    console.log("@更新模式，參數: " + parameters);
    $.getJSON(servlet, parameters, callback);
    //toggleButtons(false, ["#submit-button", "#update-button"]); // 禁用其他按钮，仅保留提交和更新按钮
    $("#update-button").html("更新 <i class=\"ft-refresh-cw position-right\"></i>");

}
/*
    按下delete Button產生的事件
    @param {string} serialNo 刪除資料的key值
    @param {string} servletUrl servlet路徑
    @param {string} actionName servlet下的操作
    @param {function} callback 刪除作業完成後的callback
 */
function deleteButtonHandler(serialNo, servletUrl, actionName, callback)
{

    console.log(`delete-button click,serialNo=${serialNo}`);
    if (!serialNo || serialNo.trim() === "")
    {
        console.warn("serialNo is empty."); // 提示錯誤
        alert("No serialNo found. Please check the input.");
        return;
    }
    const confirmDelete = confirm("確定刪除嗎");
    if (!confirmDelete) {
        console.log("User canceled the delete operation.");
        return; // 使用者取消操作
    }
    console.log("Fetching delete item for serialNo=" + serialNo);
    fetchCarData(servletUrl,actionName, serialNo, callback);
}

// 封装 AJAX 请求逻辑
function fetchCarData(servlet, action, serialNo, callback)
{
    console.log("fetchCarData="+servlet);
    const url = new URL(servlet); // 构造完整 URL
    const params = { action: action, serialNo: serialNo};
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
    console.log("url="+url);
    $.getJSON(url.toString())
        .done(callback) // 成功回调
        .fail(function (jqXHR, textStatus, errorThrown)
        {
            console.error("AJAX Error:", textStatus, errorThrown); // 记录错误日志
            alert("Failed to fetch data. Please try again later."); // 提示用户
        });
}

function SubmitCallback({ setAppendMode, setUpdateMode }) {
    return function (result) {
        console.log("ajaxSubmitCallBack mode=" + result.mode + ", result=" + result.success);
        if (result.success === true) {
            toggleInputs(false);
            toggleButtons(false, ["#submit-button"]);
            if (result.mode === "append") {
                alert("新增作業成功");
                console.log("新增作業成功");
                $("#serialNoTitle").text(result.serialNo);
                setAppendMode(false);
            } else {
                $("#update-button").html("更新 <i class=\"ft-refresh-cw position-right\"></i>");
                alert("更新作業成功");
                console.log("更新作業成功");
                setUpdateMode(false);
            }
        } else {
            alert("作業失敗 " + result.errorMessage);
            console.log("作業失敗 " + result.errorMessage);
        }
    };
}

/**
 * 通用 AJAX 成功/失敗處理 callback 工具
 *
 * @param {Object} result - 從 AJAX 傳回的 JSON 結果
 * @param {Function} getSerialFn - 從 result.data 取出 serialNo 的函式
 * @param {Function} reflashFn - 負責重新整理畫面的函式
 */
function genericAjaxCallback(result, getSerialFn, reflashFn) {

    if (result.success === false) {
        //如果是最後一筆，也應該出現在此。
        alert(result.errorMessage);
        console.log(`error message=${result.errorMessage}`);
        return;
    }
    const data = result.data;
    if (!data) {
        console.warn("⚠️ AJAX 成功但 data 為 null(已經是最後一筆資料)，後續動作中止。");
        return;
    }
    //清除以select/input/textarea中帶有.fc-0 .fc-1，則清除內容。
    AllFormControlClean2(["fc-0","fc-1"]);
    const serialNo = getSerialFn(data);

    $("#serialNoTitle").text(serialNo);
    reflashFn(data);
}