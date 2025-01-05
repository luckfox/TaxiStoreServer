
    function AllFormControlClean()
    {
        $(".form-control").each(function()
        {
            $(this).attr("value","");
            $(this).val("");
        });
        $(".form-select").html("");
    }
    /*
     *  用途：針對具有.update-enabled，表示該元素為能編輯的，當enable為ture時，
     *  將元素改為能編輯狀態，並且字體顏色設為黃色。
     */
    function toggleInputs(enable)
    {
        $(".update-enabled").each(function ()
        {
            enable?$(this).removeAttr("disabled"):$(this).attr("disabled","disabled");

            const label = $(this).parent().find("label");
            label.css("color", enable ? "yellow" : "white");
        });
    }
    // 通用函数：设置按钮状态
    function toggleButtons(enable, exclude = []) {
        const buttons = ["#delete-button", "#next-button", "#previous-button", "#update-button", "#add-button", "#submit-button"];
        buttons.forEach((selector) => {
            if (exclude.includes(selector)) {
                // 如果在 exclude 中
                $(selector).prop("disabled", !enable);
            } else {
                // 如果不在 exclude 中
                $(selector).prop("disabled", enable);
            }
        });
    }

    // 封装 AJAX 请求逻辑
    function fetchCarData(servlet, action, serialNo, callback)
    {
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
    function previousButtonHandler(serialNoElementId, servletUrl, actionName, callback)
    {
        nextButtonHandler(serialNoElementId, servletUrl, actionName, callback);
    }
    function nextButtonHandler(serialNoElementId, servletUrl, actionName, callback)
    {
        console.log("button click, serialNo element ID = " + serialNoElementId);
        const serialNo = $(`#${serialNoElementId}`).text().trim();
        if (!serialNo) {
            console.warn("Serial number is empty."); // 提示錯誤
            alert("No sequence found. Please check the input.");
            return;
        }

        console.log("Fetching item for serialNo = " + serialNo);
        fetchCarData(servletUrl, actionName, serialNo, callback);
    }
    function deleteButtonHandler(serialNoElementId, servletUrl, actionName, callback)
    {
        const serialNo = $("#serialNoTitle").text().trim();
        console.log("delete-button click,serialNo="+ $("#serialNoTitle").text());
        if (!serialNo)
        {
            console.warn("companySeq sequence is empty."); // 提示錯誤
            alert("No company sequence found. Please check the input.");
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

    /**
     * 處理提交邏輯
     * @param {number} mode - 0: 新增, 1: 更新
     * @param {string} servlet - 處理請求的 Servlet 名稱
     * @param {function} callback - Ajax 請求的回呼函式
     */
    function submitButtonHandler(mode, servlet, callback)
    {

        let submit_OK = true;
        console.log(`submit mode=${mode} servlet=${servlet}`);
        // 1. 清除所有欄位的樣式
        $("input").css("border-color", "").css("border-width", "");

        // 2. 檢查必填欄位是否有空值
        $("input[required]").each(function () {
            if ($(this).val().trim() === "") {
                submit_OK = false;
                $(this).css("border-color", "yellow").css("border-width", "medium");
            }
        });
        if (!submit_OK) return; // 若有必填欄位未填，終止操作

        // 3. 檢查 pattern 屬性的規範
        $("input[pattern]").each(function () {
            const pattern = $(this).attr("pattern");
            const regex = new RegExp(pattern);
            if (!regex.test($(this).val().trim())) {
                submit_OK = false;
                $(this).css("border-color", "red").css("border-width", "medium");
            }
        });
        if (!submit_OK) return; // 若有欄位不符合規範，終止操作

        // 4. 禁能所有 update-enabled 的欄位
        toggleInputs(false);

        // 5. 啟用其他按鈕，禁用提交按鈕
        $("#next-button, #previous-button, #update-button, #add-button, #delete-button").removeAttr("disabled");
        $("#submit-button").attr("disabled", "disabled");

        // 6. 組裝提交參數
        let parameters = "";
        $(".update-enabled[name]").each(function () {
            const name = $(this).attr("name");
            const value = $(this).val().trim();
            //if (value) parameters += `&${name}=${encodeURIComponent(value)}`;
            if (value) parameters += `&${name}=${value}`;
        });

        // 7. 根據 mode (0: 新增, 1: 更新) 處理不同的提交邏輯
        switch (mode) {
            case 0: // 新增模式
                parameters = `action=ajaxUpdateItem&companyseq=0${parameters}`;
                console.log("新增模式，參數: " + parameters);
                $.getJSON(`${servlet}`, parameters, callback);
                $("#add-button").html("新增 <i class=\"ft-plus-circle position-right\"></i>");
                break;

            case 1: // 更新模式
                const serialNo = $("#serialNoTitle").text().trim();
                if (!serialNo) {
                    alert("無法取得序列號，無法進行更新。");
                    return;
                }
                parameters = `action=ajaxUpdateItem&companyseq=${serialNo}${parameters}`;
                console.log("@更新模式，參數: " + parameters);
                $.getJSON(`${servlet}`, parameters, callback);
                $("#update-button").html("更新 <i class=\"ft-refresh-cw position-right\"></i>");
                break;

            default:
                console.error("未知的模式，無法執行操作。");
                alert("提交模式有誤，請檢查程式碼設定！");
                return;
        }
    }
