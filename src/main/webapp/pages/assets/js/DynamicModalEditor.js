//
//使用到的外部函數 replaceInnerIds、applyDataToModalFields、toggleInputs

class DynamicModalEditor {
    constructor(triggerButtonElement, $modalContainer, contentPath,callbacks = {}) {
        this.$triggerButton = $(triggerButtonElement);
        this.$modalContainer = $modalContainer;
        this.contentPath=contentPath;
        this.modalPrefix = new Date().getTime() + "_";
        this.callbacks = {
            onSuccess: callbacks.onSuccess || function () { },
            onLoaded: callbacks.onLoaded || function () { },
            onFail: callbacks.onFail || function () { },
            onClose: callbacks.onClose || function () { },
            onUpdate: callbacks.onUpdate || function () { },
            onUpdateTableRow: callbacks.onUpdateTableRow || function () { },
            onBindDropdown: callbacks.onBindDropdown || function () { },
            // 🆕 新增：全域按鈕點擊事件
            onBtnClick: callbacks.onBtnClick || function () { },
            // 新增：允許外部提供 API 請求資訊
            getOpenRequest: callbacks.getOpenRequest || null,

        };
    }

    async open() {

        // 使用 callback 取得 URL 與參數
        if (!this.callbacks.getOpenRequest)
        {
            console.log("getOpenRequest not define! ");
            return;
        }
        const req = this.callbacks.getOpenRequest();

        //console.log("🚀 Loading user data from: ", req.url);

        try {
            const result = await $.getJSON(req.url);
            if (!result.success || result.data === null) {
                alert(result.errorMessage || "讀取失敗");
                this.callbacks.onFail("讀取使用者資料失敗");
                return;
            }

            //console.log(`📦 Modal path: ${this.contentPath}`);
            this.$modalContainer.load(this.contentPath, (responseText, textStatus, jqXHR ) =>
            {
                if (textStatus === "success") {
                    //console.log(`🧱 Modal Container HTML length=${this.$modalContainer.html().length}`);
                    //console.log(this.$modalContainer.html()); // 或者 .get(0).outerHTML 也行
                    this._onModalLoaded(result.data);
                }else
                {
                    console.error("載入 modal 內容失敗：", textStatus, jqXHR.status, jqXHR.statusText);
                    this.callbacks.onFail("載入時發生錯誤");
                }
            });
        } catch (err) {
            console.error("⚠️ AJAX 錯誤：", err);
            this.callbacks.onFail("載入時發生錯誤");
        }
    }

    _onModalLoaded(userData) {
        //console.log(`modalContainer=${this.$modalContainer.html()}`);
        this.modalId = replaceInnerIds(this.$modalContainer, this.modalPrefix);
        console.log("🔧 Modal ID:", this.modalId);

        this.modalHelper = new DynamicModalHelper(this.modalId);
        applyDataToModalFields(userData, this.modalId);

        this._setupEvents();
        toggleInputs(true, this.modalId);
        $(this.modalId).modal("show");
    }
    /*
    設定各項modal的事件。
    1.btnSumbit onClick：會收集modal中所有
     */
    _setupEvents() {
        const helper = this.modalHelper;

        helper.onClick("btnSubmit", async (e) =>
        {
            e.preventDefault();
            const formData = this._collectFormData();
            console.log("📤 Submit clicked with data:", formData);

            try {
                const result = await this._updateUser(formData);
                if (!result.success) {
                    alert(result.message);
                    this.callbacks.onFail(result.message);
                    return;
                }

                this._updateTableRow(formData);
                this.callbacks.onSuccess("更新成功！");
                helper.hide();
            } catch (err) {
                console.error("😵 更新過程出錯：", err);
                alert("哎呀，發生錯誤！");
                this.callbacks.onFail("更新過程出錯！");
            }
        });

        helper.find('[data-dismiss="modal"]').on("click", () => {
            console.log("❌ Modal 被關閉了");
            this.callbacks.onClose();
        });

        helper.getRoot().on("hidden.bs.modal", () => {
            if (this.$triggerButton && this.$triggerButton.length) {
                this.$triggerButton.focus();
            }
        });
        // 🆕 綁定所有按鈕 click，觸發 onBtnClick callback
        helper.getRoot().find("button").on("click", (e) => {
            const $btn = $(e.currentTarget);
            this.callbacks.onBtnClick({
                id: $btn.attr("id") || null,
                name: $btn.attr("name") || null,
                text: $btn.text().trim(),
                classList: $btn.attr("class") || "",
                element: $btn
            });
        });
        // dropdown 綁定
        this._bindDropdown();
    }
    _collectFormData() {
        const h = this.modalHelper;
        const $root = h.getRoot();
        const data = {};

        $root.find('input, select, textarea').each((i, el) => {
            const $el = $(el);
            let key = $el.attr('id') || $el.attr('name');
            if (!key) return; // 沒有 id/name 就跳過

            // 把 modalPrefix 去掉，讓 key 比較乾淨
            if (key.startsWith(this.modalPrefix)) {
                key = key.substring(this.modalPrefix.length);
            }

            // 判斷不同類型的欄位
            if ($el.is(':checkbox')) {
                data[key] = $el.is(':checked');
            } else if ($el.is(':radio')) {
                if ($el.is(':checked')) {
                    data[key] = $el.val();
                }
            } else {
                data[key] = $el.val();
            }
        });

        console.log("🗂️ 收集到的 form data：", data);
        return data;
    }

    async _updateUser(formData) {
        if (typeof this.callbacks.onUpdate !== "function") {
            throw new Error("onUpdate callback is not defined!");
        }

        // 取得 URL 與選擇性的 payload mapping
        const updateConfig = this.callbacks.onUpdate(formData);

        if (!updateConfig || !updateConfig.url) {
            throw new Error("onUpdate must return an object with url property");
        }

        const payload = (typeof updateConfig.mapPayload === "function")
            ? updateConfig.mapPayload(formData)
            : formData; // 預設直接用原始 formData

        // 發送請求
        try {
            const result = await $.getJSON(updateConfig.url, payload);
            if (!result.success || result.data < 1) {
                return { success: false, message: result.message || "更新失敗！請再試一次" };
            }
            return { success: true };
        } catch (err) {
            console.error("🚨 AJAX 發生錯誤：", err);
            return { success: false, message: "伺服器錯誤，請稍後再試。" };
        }
    }


    /*
    * 將更新後的資料，寫回到原始的table欄位中。
    * */
    _updateTableRow(formData) {
        if (typeof this.callbacks.onUpdateTableRow === "function") {
            try {
                this.callbacks.onUpdateTableRow(formData);
            } catch (err) {
                console.error("onUpdateTableRow callback 發生錯誤：", err);
            }
        } else {
            console.log("沒有定義 onUpdateTableRow，略過表格更新。");
        }
    }
    _bindDropdown() {
        if (typeof this.callbacks.onBindDropdown === "function") {
            try {
                this.callbacks.onBindDropdown(this.modalHelper, this.modalPrefix);
            } catch (err) {
                console.error("onBindDropdown callback 發生錯誤：", err);
            }
        } else {
            console.log("沒有定義 onBindDropdown，略過 dropdown 綁定。");
        }
    }
}