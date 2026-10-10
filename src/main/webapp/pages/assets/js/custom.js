

/*
 * 根據容器內所有 input/textarea/select 的 id，
 * 從 $selector 的 data-* 對應欄位填值。
 *
 * @param {jQuery} $selector - 被點選的選項（含 data-*）
 * @param {string|jQuery} containerSelector - 欲自動填值的區塊
 */
function autoFillFromDataAttrs2($selector, containerSelector) {
    const $container = $(containerSelector);
    console.log(`[autoFillFromDataAttrs] 開始將 data-* 填入 ${containerSelector}`);
    console.log(`[autoFillFromDataAttrs] 來看看你塞的 data-* 長怎樣：`, $selector.get(0).dataset);

    $container.find("input[id], textarea[id], select[id]").each(function () {
        const $field = $(this);
        const id = $field.attr("id");
        const dataValue = $selector.attr("data-" + id);
        console.log(`🎯 處理欄位 #${id}`, $field); // 👉👉👉 加這行！
        if (typeof dataValue !== 'undefined')
        {
            //console.log(`[autoFill] set #${id} =`, dataValue);
            if ($field.is("select")) {
                // 💥 直接清空所有 options
                $field.empty();

                // 💥 插入新的唯一選項並選取
                $field.append(`<option selected>${dataValue}</option>`);

                // 若有 select2 之類的插件，觸發 change 以更新 UI
                $field.trigger("change");
            } else {
                // input 或 textarea 設值
                $field.val(dataValue);
            }
        }else
        {
            //console.warn(`⚠️ 無法填入 #${id}，因為 data-${id} 不存在於 $selector`);
        }
    });
}

function autoFillFromDataAttrs($selector, containerSelector) {
    const $container = $(containerSelector);
    console.log(`[autoFillFromDataAttrs] 開始將 data-* 填入 ${containerSelector}`);
    console.log(`[autoFillFromDataAttrs] container 內容如下：`, $container.length, $container.get(0));
    console.log(`[autoFillFromDataAttrs] 來看看你塞的 data-* 長怎樣：`, $selector.get(0).dataset);

    const $fields = $container.find("input[id], textarea[id], select[id]");
    console.log(`[autoFillFromDataAttrs] 找到 ${$fields.length} 個可填欄位`);


    $fields.each(function () {
        const $field = $(this);
        const id = $field.attr("id");
        const tagName = $field.prop("tagName");
        const dataValue = $selector.attr("data-" + id);
        console.log(`🎯 處理欄位 #${id}`, $field);

        if (typeof dataValue !== 'undefined') {
            console.log(`[autoFillFromDataAttrs] set #${id} =`, dataValue);

            if (tagName === "SELECT") {
                const value = String(dataValue);
                const matched = $field.find(`option[value="${value}"]`);

                if (matched.length > 0) {
                    console.log(`[autoFillFromDataAttrs]✅ Select matched: setting value to ${value}`);
                    $field.val(value);
                } else {
                    console.warn(`[autoFillFromDataAttrs]❌ Select #${id} has no matching option for value=${value}`);
                    const allOptions = $field.find("option").map((i, e) => $(e).val()).get();
                    console.log("[autoFillFromDataAttrs]🧾 所有現有選項：", allOptions);
                    $field.append(`<option value="${value}" selected>（未知值：${value}）</option>`);
                    $field.val(value); // 再設定一次，保險起見
                }

                $field.trigger("change");
            } else {
                $field.val(dataValue);
            }
        } else {
            console.warn(`[autoFillFromDataAttrs]⚠️ 無法填入 #${id}，因為 data-${id} 不存在於 $selector`);
        }
    });
}

/*
 * 用途：清除所有帶有指定 class 名稱中**任一個**的元素內容。
 * 支援 input、textarea、label、select。
 *
 * @param {string[]} classList - 要清除的 class 名稱陣列，例如 ["fc-0", "fc-clear"]
 */
function AllFormControlClean2(classList) {
    console.log("AllFormControlClean 3.0 🧽🌀");

    if (!Array.isArray(classList) || classList.length === 0) {
        console.warn("AllFormControlClean：classList 必須是非空陣列！");
        return;
    }

    // 組出 jQuery 選擇器，如 ".fc-0,.fc-clear"
    const selector = classList.map(cls => `.${cls}`).join(",");

    $(selector).each(function () {
        const $el = $(this);

        if ($el.is("input") || $el.is("textarea")) {
            $el.val("");
        } else if ($el.is("label")) {
            $el.html("");
        } else if ($el.is("select")) {
           // $el.empty();
        }
    });
}
/**
 * 用途：針對具有 .update-enabled 的元素進行啟用/停用處理，
 * 並變更對應 label 或 span 的顏色，同時列出被影響的元素。
 *
 * @param {boolean} enable - 是否啟用編輯狀態。
 * @param {string|null} rootElem - 要限制操作範圍的容器選擇器 (如 "#myModal")，若為 null 則對整個畫面生效。
 */
function toggleInputs2(enable, rootElem = null) {
    const $scope = rootElem ? $(rootElem) : $("body");
    const action = enable ? "啟用" : "停用";

    console.log(`[toggleInputs] ⚙️ 狀態：${action} 編輯模式，範圍：${rootElem || "全畫面"}`);

    $scope.find(".update-enabled").each(function () {
        const $el = $(this);
        const tag = $el.prop("tagName");
        const id = $el.attr("id") || "(無 id)";
        const type = $el.attr("type") || "";

        enable ? $el.removeAttr("disabled") : $el.attr("disabled", "disabled");

        console.log(`  - ${action} .update-enabled ➜ [${tag}] #${id} ${type ? `type="${type}"` : ""}`);

        // 同時變更 label 或 span 顏色
        const $labelOrSpan = $el.parent().find("label, span");
        $labelOrSpan.css("color", enable ? "yellow" : "white");

        $labelOrSpan.each(function () {
            const labelTag = $(this).prop("tagName");
            console.log(`    ↳ 更新標籤顏色：[${labelTag}] 內容="${$(this).text().trim()}"`);
        });
    });

    $scope.find(".fc-1").each(function () {
        const $el = $(this);
        const tag = $el.prop("tagName");
        const id = $el.attr("id") || "(無 id)";
        const type = $el.attr("type") || "";

        // 注意這裡是「反向邏輯」
        const fcAction = !enable ? "啟用" : "停用";

        !enable ? $el.removeAttr("disabled") : $el.attr("disabled", "disabled");

        console.log(`  - ${fcAction} .fc-1 ➜ [${tag}] #${id} ${type ? `type="${type}"` : ""}`);
    });

    console.log(`[toggleInputs] ✅ 處理完成`);
}

/*
 * 用途：針對具有 .update-enabled 的元素進行啟用/停用處理，
 * 可限制在指定 root 元素內操作，並變更對應 label 或 span 的顏色。
 *
 * @param {boolean} enable - 是否啟用編輯狀態。
 * @param {string|null} rootElem - 要限制操作範圍的容器選擇器 (如 "#myModal")，若為 null 則對整個畫面生效。
 */
function toggleInputs3(enable, rootElem = null) {
    const $scope = rootElem ? $(rootElem) : $("body");

    $scope.find(".update-enabled").each(function () {
        enable ? $(this).removeAttr("disabled") : $(this).attr("disabled", "disabled");

        //$(this).parent().find("label, span").css("color", enable ? "yellow" : "white");
        const id = $el.attr("id");
        if (id) {
            $(`label[for="${id}"], span[for="${id}"]`).css("color", enable ? "yellow" : "white");
        }

    });

    $scope.find(".fc-1").each(function () {
        !enable ? $(this).removeAttr("disabled") : $(this).attr("disabled", "disabled");
    });
}

    /* 通用函数：設置預設按鈕如下，如enabled=true，則設置的按鈕則為disabled=true，列為exclude則排除之disabled=false
     const buttons = [ "#first-button","#previous-button","#next-button","#last-button", "#update-button", "#add-button","#delete-button", "#submit-button"];
     */
    function toggleButtons(enable, exclude = []) {
        const buttons = [ "#first-button","#previous-button","#next-button","#last-button", "#update-button", "#add-button","#delete-button", "#submit-button"];
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
/**
 * 用途：針對具有 .update-enabled 的元素進行啟用/停用處理，
 * 並精準變更對應 label 或 span 的顏色，同時列出被影響的元素。
 *
 * @param {boolean} enable - 是否啟用編輯狀態。
 * @param {string|null} rootElem - 限制操作範圍的容器選擇器，若為 null 則操作整個畫面。
 */
function toggleInputs(enable, rootElem = null) {
    const $scope = rootElem ? $(rootElem) : $("body");
    const action = enable ? "啟用" : "停用";

    console.log(`[toggleInputs] ⚙️ 狀態：${action} 編輯模式，範圍：${rootElem || "全畫面"}`);

    $scope.find(".update-enabled").each(function () {
        const $el = $(this);
        const tag = $el.prop("tagName");
        const id = $el.attr("id") || "(無 id)";
        const type = $el.attr("type") || "";

        // 切換 disabled 屬性
        enable ? $el.removeAttr("disabled") : $el.attr("disabled", "disabled");

        //console.log(`  - ${action} .update-enabled ➜ [${tag}] #${id} ${type ? `type="${type}"` : ""}`);

        // 精準找 label 或 span 並變更顏色
        if (id) {
            $(`label[for="${id}"], span[for="${id}"]`).each(function () {
                const $label = $(this);
                const labelTag = $label.prop("tagName");
                const content = $label.text().trim();

                $label.css("color", enable ? "yellow" : "white");

                //console.log(`    ↳ 標籤變色 ➜ [${labelTag}] for="${id}" 文字="${content}" → ${enable ? "yellow" : "white"}`);
            });
        }
    });

    // 特殊處理 .fc-1（邏輯反向）
    $scope.find(".fc-1").each(function () {
        const $el = $(this);
        const tag = $el.prop("tagName");
        const id = $el.attr("id") || "(無 id)";
        const type = $el.attr("type") || "";

        const fcAction = !enable ? "啟用" : "停用";

        !enable ? $el.removeAttr("disabled") : $el.attr("disabled", "disabled");
        //console.log(`  - ${fcAction} .fc-1 ➜ [${tag}] #${id} ${type ? `type="${type}"` : ""}`);
    });

    console.log(`[toggleInputs] ✅ 處理完成`);
}

/*
    從servlet收到的JSON格式資料，依照元素的name進行比對，並且填入資料 。
 */
function reflashData(data)
{
    console.log("reflashData 1.0.1");
    /*
     * 因為有些資料是null,因此根本就不會放進jsonObj,
     * 造成jsp內的某些form-control的value不會被更新
     * 所以乾脆一開始就全部清空
     */
    AllFormControlClean2(["fc-0","fc-2"]);
    //根據傳回值的Key，去尋找相對應的元素id相符者
    $.each(data, function (key, value)
    {
        //console.log(`${key}=${value}`);
        let element = $("#" + key);
        let tagName=element.prop("tagName");
        if(element.length>0 && tagName!=null)  //元素存在。
        {

            //console.log(`[${tagName}] ${key}=${value}`);
            //1.檢查此id所在的元素 是否為select
            if (tagName === "SELECT")
            {
                //let outString = key + ":<option>" + value + "</option>";
                element.html(`<option>${value}</option>`);
            }else
                //2.檢查此id所在的元素 是否為label
            if (tagName === "LABEL")
            {
                element.html(value); // 将内容修改为 value
            }
            else
            {   //other....
                element.val(value);
            }
        }
    });
}



/**
 * 自動填入表單欄位的萬用函式（含詳細 log）
 *
 * 可從 DOM 元素的 data-* 或 JSON 物件填入目標容器內所有 input/textarea/select/label。
 *
 * @param {jQuery|Object} source - 來源資料，可為含 data-* 的 jQuery DOM 或 JSON 物件
 * @param {string|jQuery} containerSelector - 欲填入值的表單容器
 * @param {Array<string>} [clearTargets=[]] - 要先清空的容器 ID 陣列
 */
function autoFillFormFields(source, containerSelector, clearTargets = []) {
    const $container = $(containerSelector);
    const isDOM = source instanceof jQuery;

    // 1. LOG: 資料來源種類
    console.log(`[autoFillFormFields] 開始填值，來源: ${isDOM ? 'DOM data-*' : 'JSON object'}`);

    // 2. LOG: 資料來源內容
    if (isDOM) {
        console.log(`[autoFillFormFields] data-* 屬性如下：`);
        const dataset = {};
        $.each(source[0].attributes, function (_, attr) {
            if (attr.name.startsWith("data-")) {
                const key = attr.name.substring(5); // 移除 "data-"
                dataset[key] = attr.value;
            }
        });
        console.log(dataset);
    } else {
        console.log(`[autoFillFormFields] JSON 資料如下：`);
        console.log(source);
    }

    // 3. 清空指定容器欄位（如果有的話）
    clearTargets.forEach(containerId => {
        console.log(`[autoFillFormFields] 清空容器 #${containerId}`);
        $(`#${containerId}`).find(".form-control").val("");
    });

    // 4. 遍歷容器欄位並依 source 填入
    $container.find("input[id], textarea[id], select[id], label[id]").each(function () {
        const $field = $(this);
        const id = $field.attr("id");
        const tagName = $field.prop("tagName");

        let value;
        if (isDOM) {
            value = source.attr("data-" + id);
        } else {
            value = source[id];
        }

        // 沒值就跳過
        if (typeof value === 'undefined') {
            console.log(`[autoFillFormFields] 跳過 #${id}（無對應值）`);
            return;
        }

        // 根據元素類型處理
        switch (tagName) {
            case "SELECT":
                $field.empty().append(`<option selected>${value}</option>`).trigger("change");
                console.log(`[autoFillFormFields] SELECT #${id} ⇨ "${value}"`);
                break;
            case "LABEL":
                $field.html(value);
                console.log(`[autoFillFormFields] LABEL #${id} ⇨ "${value}"`);
                break;
            default:
                $field.val(value);
                console.log(`[autoFillFormFields] ${tagName} #${id} ⇨ "${value}"`);
        }
    });

    console.log(`[autoFillFormFields] 填值完成 🎯`);
}


//======================== utility==========================================
function sprintf(template, ...args) {
    let i = 0;
    return template.replace(/%s/g, () => args[i++]);
}
function formatDate(dateString) {
    if (!dateString) return ""; // 如果是空值，返回空字串
    let date = new Date(dateString);
    return date.toISOString().split('T')[0]; // 轉換為 YYYY-MM-DD
}
/*
 * 將 container 裡的所有 id 做唯一性轉換，並更新相應的 label/for 等關聯屬性
 * 同時傳回 container 中 .modal 元素的新 id，供其他函數使用。
 *
 * @param {string} containerSelector - jQuery 選擇器，例如 "#modalContainer"
 * @param {string} prefix - 可選，id 的前綴（可用元件名等）
 * @returns {string} newModalId - modal 根元素的新 ID（包含 "#" 前綴）
 */
function replaceInnerIds($container, prefix = "") {
    if (!$container || $container.length === 0) {
        console.warn("⚠️ replaceInnerIds: 傳入的 $container 為空，請確認 selector 是否正確！");
        return null;
    }

    let newModalId = null;
    const $modal = $container.find(".modal").first();

    if ($modal.length === 0) {
        console.warn("⚠️ replaceInnerIds: 找不到 .modal 元素，這 container 裡真的有 modal 嗎？");
    }

    const originalModalId = $modal.attr("id");
    if (!originalModalId) {
        console.warn("⚠️ replaceInnerIds: 找到 modal 但沒有 id，這樣可能會導致無法回傳 modal selector！");
    }

    console.log("🔧 replaceInnerIds 啟動！prefix =", prefix);
    console.log("🎯 原本 modal id =", originalModalId);

    const idMap = {};

    $container.find("[id]").each(function () {
        const $el = $(this);
        const oldId = $el.attr("id");
        const newId = `${prefix}${oldId}`;

        // 防呆：如果 newId 跟原本一樣就略過（避免重複執行）
        if (oldId === newId) {
            //console.log(`⚠️ ID [${oldId}] 已經有 prefix，跳過`);
            return;
        }

        // 實際改名
        $el.attr("id", newId);
        idMap[oldId] = newId;

        // label for
        $container.find(`label[for="${oldId}"]`).attr("for", newId);

        // aria 屬性同步
        const ariaAttrs = ["aria-labelledby", "aria-describedby"];
        ariaAttrs.forEach(attr => {
            if ($el.attr(attr) === oldId) {
                $el.attr(attr, newId);
            }
        });

        // 如果是 modal 本體
        if (oldId === originalModalId) {
            newModalId = newId;
        }

        //console.log(`✅ ID 修改: [${oldId}] ➜ [${newId}]`);
    });

    if (!newModalId) {
        console.warn("❗ replaceInnerIds: 無法辨識 modal ID，回傳 null。");
        return null;
    }

    console.log("🏁 Modal ID 完成：", `#${newModalId}`);
    return `#${newModalId}`;
}




/*
 * 根據 modal 元素中的 id 屬性，將資料 source 中對應的值填入到該元素的 value 或 data-* 屬性中
 * @param {Object} source - 從 .getJSON 取得的資料
 * @param {string} modal - modal 根元素的 ID
 */
function applyDataToModalFields(source, modal)
{

    console.log('applyDataToModalFields');

    if (!source || typeof source !== 'object') {
        console.error("❌ applyDataToModalFields 錯誤：source 不是物件", source);
        return;
    }

    // 👇 DEBUG：印出 modal 整體內容
    const $modal = $(modal);
    if ($modal.length === 0) {
        console.warn(`⚠️ 找不到 modal 元素：${modal}`);
        return;
    }
    //console.log('source 內容如下');
    //console.log(source);
    //console.log('📦 modal 本尊內容如下：');
    //console.log($modal.prop('outerHTML'));  // 更完整，比 .html() 好用


    $(modal).find("[id]").each(function () {
        const $el = $(this);            //selector
        const id = $el.attr("id");
        //console.log(`id=${id}`);
        // 假設 ID 格式為 prefix_fieldName
        const idParts = id.split("_");

        if (idParts.length < 2) return; // 少於 2 段不處理

        // fieldName 為最後一段（忽略尾端 timestamp 等其他段）
        const fieldName = idParts.slice(1).join("_"); // 去掉 prefix

        const fieldValue = source[fieldName];
        //console.log(`fieldName=${fieldName} fieldValue=${fieldValue}`);
        if (fieldValue !== undefined) {
            if ($el.is("input") || $el.is("textarea") || $el.is("select")) {
                //console.log(`將欄位 [${fieldName}] 的值 "${fieldValue}" 填入元素 [${id}] (${ $el.prop('tagName') })`);
                $el.val(fieldValue);
            } else {
                //console.log(`將欄位 [${fieldName}] 的值 "${fieldValue}" 設為元素 [${id}] 的 data-${fieldName}`);
                $el.data(fieldName, fieldValue);
            }
        }
    });
}

/*
 * 從指定 modal root 裡尋找「中間包含指定純 ID」的元素。
 * 這個函數特別適用於 ID 有前綴與後綴的狀況（例如: modal1_editDriverId_1747），
 * 可以只用純 ID（如 "editDriverId"）來抓到目標元素。
 *
 * @param {string} modalRootId - Modal 的根元素 ID（建議加 #，例如 "#modal1_abc_123"）
 * @param {string} pureId - 要尋找的純 ID（不含前綴/後綴）
 * @returns {jQuery} - 找到的 jQuery 元素集合
 */
function findByPureId(modalRootId, pureId) {
    return $(modalRootId + " [id*='" + pureId + "']").filter(function () {
        const id = this.id;

        // 將完整的 id 用 "_" 分割成陣列，例如：
        // "modal1_editDriverId_1747" => ["modal1", "editDriverId", "1747"]
        const parts = id.split('_');

        // 檢查純 ID 是否恰好是中間那段（用 includes 判斷是否存在於陣列中）
        return parts.includes(pureId);
    });
}
/*
 * 從 modal 中找出目標元素的完整 ID 字串（帶前綴與後綴）
 * @param {string} modal - modal root 元素的 jQuery 選擇器字串（例如 "#myModal"）
 * @param {string} targetElem - 要找的元素關鍵字（例如 "driverSC"）
 * @returns {string|null} - 找到的完整 ID 字串，如果找不到就回傳 null
 */
function findFullElementId(modal, targetElem) {
    const $modal = $(modal);
    if ($modal.length === 0) {
        console.warn(`找不到 modal 元素：${modal}`);
        return null;
    }

    const $target = $modal.find(`[id*="_${targetElem}"]`).first(); // 找第一個符合的
    if ($target.length > 0) {
        return $target.attr("id");
    } else {
        console.warn(`在 modal ${modal} 中找不到符合 "${targetElem}" 的 id`);
        return null;
    }
}
/*
 * 圖片公用函數工具集：包含 hash 計算與存在比對功能。
 * 用於拖曳上傳與預覽圖像功能中。
 */

/*
 * 計算圖片檔案的 SHA-256 雜湊值。
 *
 * @param {File} file - 圖片檔案物件，通常來自 input[type="file"] 或拖曳事件。
 * @returns {Promise<string>} 雜湊值字串（以十六進位表示）。
 *
 * @example
 * const hash = await calculateHash(file);
 * console.log(hash); // 顯示像是 "a3f8d2..."
 */
async function calculateHash(file)
{
    // 1. 將圖片檔案轉成 ArrayBuffer（二進位陣列表示法）
    const arrayBuffer = await file.arrayBuffer();

    // 2. 使用 Web Crypto API 計算 SHA-256 雜湊值
    const hashBuffer = await crypto.subtle.digest('SHA-256', arrayBuffer);

    // 3. 將 ArrayBuffer 轉換成 byte 陣列（Uint8Array）
    const hashArray = Array.from(new Uint8Array(hashBuffer));

    // 4. 每個 byte 轉成兩位十六進位字串後合併成完整 hash 字串
    return hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
}
/*
 * 取得指定圖片的雜湊值，並與伺服器上的雜湊清單比對，看是否已存在。
 *
 * @param {File} file - 要上傳的圖片檔案。
 * @param {string[]} existingHashes - 從伺服器取得的已存在 hash 值陣列。
 * @returns {Promise<{hash: string, exists: boolean}>} 包含圖片 hash 值與是否已存在的結果物件。
 *
 * @example
 * const result = await checkImageHashExists(file, ['abcd1234...']);
 * if (result.exists) console.log('圖片重複');
 */
async function checkImageHashExists(file, existingHashes) {
    // 計算圖片檔案的 hash
    const hash = await calculateHash(file);

    // 比對是否已存在於伺服器回傳的 hash 陣列中（不分大小寫比對）
    const exists = existingHashes.some(h => h.toLowerCase() === hash.toLowerCase());

    return { hash, exists };
}

// 匯出公用函數
//export { calculateHash, checkImageHashExists };