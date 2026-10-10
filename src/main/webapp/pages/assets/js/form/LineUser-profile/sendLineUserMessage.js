function sendLineUserMessage(serialID, triggerButtonElement,$modalContainer) {
    let modal1_PreFix=new Date().getTime()+"_";
    let modalID;
    function sendMessageHandler(modalHelper)
    {
        let formData = {
            userId: modalHelper.getValue("userId"),
            message: modalHelper.getValue("message")
        };
        console.log(`sendMessage=${formData}`);
        $.getJSON("lineUserServlet",
            {
                action: "ajaxPushMessage",
                userId: formData.userId,
                message: formData.message,

            }, function (response) {
                console.log("response：", response);
                if (response.success === true) {
                    alert("發送成功！");
                    $("#sendMessageModal").modal("hide");


                } else {
                    alert("發送失敗：" + response.message);
                }
            }).fail(function (xhr, status, error) {
            console.error("提交失敗:", error);
            alert("發生錯誤，請稍後再試！");
        });
    }

    console.log("sendMessage=" + serialID);
    let url = `lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=` + serialID;
    $.getJSON(url, function (result)
    {
        if (result.success) {
            let user = result.data;

            //console.log("driverId="+user.driverId+":driverSC="+user.driverSC);
            //將回傳的資料填入編輯視窗
            let contentPath = "/pages/layout/modals/LineMessageEditor.html?_=" + new Date().getTime();
            console.log(`sendMessageModal Path=${contentPath}`);
            $modalContainer.load(contentPath, function ()
            {
                let manager = null;
                modalID = replaceInnerIds($modalContainer, modal1_PreFix);
                const modalHelper = new DynamicModalHelper(modalID);
                //將從.getJSON取的到的資料，根據modal id映射到modal中。
                applyDataToModalFields(result.data, modalID);


                //因為是動態載入，所以必須在此設定事件。[id*='btnSubmit']`表示id內有包含`btnSumbit`。
                $(modalID + ` [id*='btnSubmit']`).on("click", function (e) {
                    console.log(`btnSubmit click`);
                    e.preventDefault();
                    sendMessageHandler(modalHelper);
                });


                $(modalID).modal('show'); // 打開編輯視窗
            });
            toggleInputs(true);
        } else {
            alert(result.errorMessage);
        }
    });
}