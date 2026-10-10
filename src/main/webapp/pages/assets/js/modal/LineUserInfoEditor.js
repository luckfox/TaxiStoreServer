

/*
* 點擊某個user的edit按鈕。
* @param userId
* @param
*/
function LineUserInfoEditor(serialID,triggerButtonElement,$modalContainer)
{
    console.log(`LineUserInfoEditor v 1.0.0`);
    let modal1_PreFix=new Date().getTime()+"_";
    function collectFormData(modalHelper) {
        return {
            serialID: modalHelper.getValue('serialID'),
            userId: modalHelper.getValue('userId'),
            displayName: modalHelper.getValue('displayName'),
            driverSeq: modalHelper.getValue('driverSeq'),
            driverPortable: modalHelper.getValue('driverPortable'),
            driverName: modalHelper.getValue('driverName'),
            driverSC: modalHelper.getValue('driverSC'),
            driverId: modalHelper.getValue('driverId'),
            tag: modalHelper.getValue('tag')
        };
    }
    async function updateLineUserBinding(formData)
    {

        const url = `lineUserServlet?action=ajaxUpdateByUserId&userId=${formData.userId}`;
        try {
            const result = await $.getJSON(url,
                {
                    serialID: formData.serialID,
                    userId: formData.userId,
                    displayName: formData.displayName,
                    driverseq: formData.driverSeq,
                    tag: formData.tag
                });

            console.log("✅ 後端回傳結果：", result);

            if (!result.success || result.data < 1) {
                return { success: false, message: "更新失敗！請再試一次" };
            }

            return { success: true };
        } catch (err) {
            console.error("🚨 AJAX 發生錯誤：", err);
            return { success: false, message: "伺服器錯誤，請稍後再試。" };
        }
    }


    const $triggerButton = $(triggerButtonElement);
    let url=`lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=`+serialID;
    console.log("editUser："+url);
    $.getJSON(url, function(result)
    {
        if (!result.success || result.data===null)
        {

            alert(result.errorMessage);

        }
            let user = result.data;

            //動態載入modal
            let contentPath = "/pages/layout/modals/LineUserInfoEditor.html?_="+ new Date().getTime();//避免cache問題。
            console.log(`Modal(userInfoEditor)  Path=${contentPath}`);

            $modalContainer.load(contentPath, function ()
            {

                console.log("🧱 Modal Container HTML 內容：");
                console.log($modalContainer.html()); // 或者 .get(0).outerHTML 也行
                // 1. 更換modal內的所有id加上前後綴詞，避免modal 的id與本體的id發生重複，並且傳回modal root的元素id名稱。
                userInfoEditorModal_ID=replaceInnerIds($modalContainer,modal1_PreFix);
                console.log(`@@userInfoEditorModal_ID=`,$(userInfoEditorModal_ID).html());

                // 2. 建立 modal helper 實例
                const modalHelper = new DynamicModalHelper(userInfoEditorModal_ID);

                // 3. 套用資料到欄位。從.getJSON取的到的資料，根據modal id映射到modal中。
                applyDataToModalFields(result.data, userInfoEditorModal_ID);
                console.log(`userInfoEditorModal_ID=`,userInfoEditorModal_ID);

                // 4. 綁定儲存按鈕 click
                modalHelper.onClick('btnSubmit', async function (e)
                {
                    console.log(`btnSubmit clicked!`);
                    e.preventDefault(); // 防止預設的表單提交
                    const formData = collectFormData(modalHelper);
                    console.log("🔘 儲存按鈕被點擊！送出的資料：", formData);
                    try {
                        if (typeof updateLineUserBinding !== 'function')
                        {
                            throw new Error("😱 updateLineUserBinding is not defined!");
                        }
                        //進行後端資料庫更新，將LineID和driverId進行綁定。
                        //如果成功綁定，則更新到table中原本的row。
                        const { success, message }  = await updateLineUserBinding(formData);
                        if (!success) {
                            alert(message);
                            return;
                        }

                        console.log(`$$$$$`);
                        //將formData中的 driverName、driverPortable、driverSC、driverId、tag寫回table中，根據userId
                        const table = $('#VI_table').DataTable(); // 🔍 拿到 DataTable 的實體
                        const row = table.rows().nodes().to$().filter(function ()
                        {
                            return $(this).data('user-id') == formData.userId;
                        });

                        if (row.length)
                        {
                            console.log(`@@@@@`);
                            // ✨ 直接更新欄位內容（注意 index，要對應 columns 順序）
                            const rowIndex = table.row(row).index();
                            const rowData = table.row(rowIndex).data();

                            // 👨‍🔧 更新要改的欄位
                            rowData.driverName = formData.driverName;
                            rowData.driverPortable = formData.driverPortable;
                            rowData.driverSC = formData.driverSC;
                            rowData.driverId = formData.driverId;
                            rowData.tag = formData.tag;

                            // 🧼 套回去 DataTable
                            table.row(rowIndex).data(rowData).invalidate().draw(false);
                        } else
                        {
                            console.warn("😬 找不到 userId 對應的 row，無法更新！");
                        }

                        console.log("✅ LineUserbinder 執行成功");
                        document.activeElement.blur();
                        modalHelper.hide();
                    } catch (err) {
                        console.error("🚨 發生錯誤：", err);
                        alert("哎呀！資料處理失敗，請檢查 console 看發生什麼事。");
                    }
                });

                // 5. 綁定關閉按鈕
                modalHelper.find('[data-dismiss="modal"]').on("click", function ()
                {
                    console.log("❌ 使用者點了關閉 modal！");
                });

                // 6. 顯示與隱藏事件
                modalHelper.getRoot().on('hidden.bs.modal', function ()
                {
                    if ($triggerButton && $triggerButton.length > 0)
                    {
                        console.warn('return trigger focus');
                        $triggerButton.focus(); // 🚀 焦點回來了！
                    } else
                    {
                        console.warn('找不到開啟 modal 的按鈕，無法回傳焦點 😅');
                    }

                }).on('shown.bs.modal', function ()
                {
                    $(this).removeAttr('inert');
                    originalData =
                        {
                            userId: modalHelper.getValue('userId'),
                            displayName: modalHelper.getValue('displayName'),
                            driverSeq: modalHelper.getValue('driverSeq'),
                            tag: modalHelper.getValue('tag')
                        };
                    console.log("originalData:", originalData);
                });

                //處理dropdown
                //  1.1 取得來源的元素所在。
                const Input_driverSC = findFullElementId(modalHelper.getRoot(), "driverSC");
                console.log(`Input_driverSC=`,Input_driverSC);
                const Input_driverId = findFullElementId(modalHelper.getRoot(), "driverId");
                //  1.2 取得顯示dropdown的元素所在。
                const dropdown_driverId = findFullElementId(modalHelper.getRoot(), "autocomplete-list-driverId");
                const dropdown_driverSC = findFullElementId(modalHelper.getRoot(), "autocomplete-list-driverSC");
                console.log(`Input_driverSC=${Input_driverSC} Input_driverId=${Input_driverId}`);
                console.log(`dropdown_driverId=${dropdown_driverId} dropdown_driverSC=${dropdown_driverSC}`);
                // 2.1 參數設置，目前總共有兩個地方需要dropdown list。
                const inputMappings = [
                    {
                        input: $("#"+Input_driverSC),
                        dropdown: $("#"+dropdown_driverSC),
                        api: 'driverServlet',
                        action: 'ajaxGetDriversBySpecialCode',
                        modal_Prefix:modal1_PreFix
                    },
                    {
                        input: $("#"+Input_driverId),
                        dropdown: $("#"+dropdown_driverId),
                        api: 'driverServlet',
                        action: 'ajaxGetDriversByDriverId',
                        modal_Prefix:modal1_PreFix
                    }
                ];
                // 2.2 對inputMappings中的每筆資料做映射。
                inputMappings.forEach(({ input, dropdown, api, action, modal_Prefix }) => {
                    input.on('input keydown', (e) => input_handler(e, input, dropdown, api, action, modal_Prefix));
                });
                //console.log("🧩 新 modal HTML：", $(userInfoEditorModal_ID).prop("outerHTML"));
                toggleInputs(true,userInfoEditorModal_ID);
                $(userInfoEditorModal_ID).modal('show');
            });
            toggleInputs(true);

    });
}