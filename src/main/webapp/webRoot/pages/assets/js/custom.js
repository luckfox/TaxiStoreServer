
function AllFormControlClean()
{
    console.log("AllFormControlClean 1.00");
    $(".fc-0").each(function()
    {
        //console.log("hello");
        if($(this).prop("tagName")==='INPUT')
        {
            //console.log("AllFormControlClean ;INPUT; #"+$(this).prop("id"))
            $(this).val("");
        }else if($(this).prop("tagName")==='LABEL')
        {
            //console.log("AllFormControlClean ;LABEL; #"+$(this).prop("id"));
            $(this).html("");
        }
    });
    $(".form-select").empty();
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
        $(".fc-1").each(function()
        {
            !enable?$(this).removeAttr("disabled"):$(this).attr("disabled","disabled");
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
