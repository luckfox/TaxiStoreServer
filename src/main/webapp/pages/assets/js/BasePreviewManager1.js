class BasePreviewManager {

    // ===============================================
    // 私有欄位（全部透過建構子注入設定）
    // ===============================================
    #previewTag;        // ✅ 預覽圖片區塊的容器 selector（例如："#preview"）
    #carouselTag;       // ✅ Carousel 元件的根 selector（例如："#carouselImages"）
    #imageRotation;     // ✅ 後端圖片旋轉 API 設定物件：{ servlet, action, parameter }
    #carouselModalTag;  // ✅ 包住 Carousel 的 Modal selector（例如："#imageModal"）
    #modalContentPath;  // ✅ Modal HTML 片段路徑（由 loadModal() 動態載入）
    #$modalContainer;   // ✅ jQuery 物件，放置 Modal HTML 的容器（.load() 會塞進去）
    #imageData;         // ✅ 後端取圖 API 設定：{ servlet, action }

    // Map: key（可能是 temp-UUID 或後端正式 imageId）
    //   -> { file, hash, existing, extraId }
    //   file:   使用者剛選的檔案物件（本地）
    //   hash:   內容雜湊（用於避免重複圖）
    //   existing: 是否已在遠端存在（true 表示後端已有，false 表示本地暫存）
    //   extraId: 後端返回的正式 ID（只在 existing=true、或上傳完成後才會有）
    #previewInfoMap;

    // ✅ 事件回呼集合，用 callCallback(name, ...args) 觸發
    #callbacks;

    constructor(config) {
        console.log(`BasePreviewManager version 1.0.0`);
        this.#previewTag = config.previewTag;
        this.#carouselTag = config.carouselTag;
        this.#imageRotation = config.imageRotation;
        this.#imageData = config.imageData;
        this.#carouselModalTag = config.carouselModalTag;
        this.#modalContentPath = config.modalContentPath;
        this.#$modalContainer = (config.modalContainer instanceof jQuery)
            ? config.modalContainer : $(config.modalContainer);
        this.#previewInfoMap = new Map();
        this.#callbacks = config.callbacks || {};
    }


    /*
     * 動態載入 Modal HTML，並綁定「旋轉」按鈕事件。
     * 注意：這顆旋轉按鈕是全域（放在 modal header），
     *       觸發時會抓 carousel 目前 .active 的那張，
     *       取得對應的 data-id（=我們的 key）後呼叫 #rotateImage(key)。
     */
    async loadModal() {
        return new Promise((resolve, reject) => {
            if (!this.#$modalContainer || this.#$modalContainer.length === 0) {
                reject("Modal container not found");
                return;
            }
            this.#$modalContainer.load(this.#modalContentPath, (response, status) =>
            {
                if (status === "error") reject("Modal load failed");
                else
                {
                    this.callCallback("onModalLoaded");
                    resolve();
                }
            });
        });
    }
    /*
     * 清空所有預覽與 carousel 項目，並關閉 Modal。
     */
    releaseImagePreview() {
        $(this.#previewTag).empty();
        $(`${this.#carouselTag} .carousel-inner`).empty();
        this.#previewInfoMap.clear();

        // 取出 Modal 實例並關閉（注意要傳 DOM element 而非 jQuery 物件）
        const modal = bootstrap.Modal.getInstance($(this.#carouselModalTag)[0]);
        if (modal) modal.hide();
        this.callCallback("onReleasePreview");
    }

    /**
     * 新增一張本地圖片到預覽與 carousel。
     * - 會先產生內容雜湊避免重複
     * - 使用 temp-UUID 當 key（上傳成功後再用 markUploaded 轉正）
     */
    async addImage(file) {
        console.log(`addImage file=${file}`);
        const hash = await quickJpegHash(file);
        for (let info of this.#previewInfoMap.values()) {
            if (info.hash === hash) {
                alertBox.show("此圖已存在預覽");
                return;
            }
        }

        const tempId = "temp-" + crypto.randomUUID();
        this.#previewInfoMap.set(tempId, { file, hash, existing: false, tempId });

        const url = await this.#fileToDataUrl(file);

        // 建立預覽與 carousel 項目（key 先用 tempId）
        this.buildPreviewElement(tempId, url, false);
        this.buildCarouselItem(tempId, url);
    }

    /**
     * 🔧 後端旋轉流程（僅能旋轉已存在後端的圖片）：
     * 1) 取出 key 對應 info
     * 2) 若沒有 info.extraId（=尚未上傳/轉正），直接 return（不旋轉）
     * 3) 呼叫後端旋轉 API，成功後更新圖片 src（加時間戳避免 cache）
     * 4) 觸發 onImageRotated 回呼
     *
     * ⚠️ 若要支援「尚未上傳的本地圖」也能旋轉，建議：
     *    A) 在這裡加上 client-side CSS transform（僅視覺旋轉），或
     *    B) 禁用 rotate 按鈕直到 markUploaded() 後再啟用。
     */
    async #rotateImage(key) {
        key = key.toString();
        // 除錯：列出目前 Map 內容
        for (let [theKey, info] of this.#previewInfoMap) {
            console.log(`🔑 key = ${theKey}`, info);
        }
        const info = this.#previewInfoMap.get(key);
        console.log("image rotation info =", info);
        // 👉 沒有 extraId 表示還沒上傳到後端，無法執行後端旋轉
        if (!info || !info.extraId) return;

        try {
            const url=`${this.#imageRotation.servlet}?action=${this.#imageRotation.action}&${this.#imageRotation.parameter}=${info.extraId}`;
            console.log(`rotateImage:url=${url}`);
            const result = await $.getJSON(url);
            if (!result.success) throw new Error("後台旋轉失敗");
            // 後端回來的新雜湊（若有提供）
            info.hash = result.data.hashCode;
            // 取出目前 preview 與 carousel 內的對應 <img>
            const $previewImg = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"] img`);
            const $carouselImg = $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item[data-id="${key}"] img`);
            console.log("[🔍 Preview]", $previewImg.attr("src"));
            console.log("[🔍 Carousel]", $carouselImg.attr("src"));

            // 更新 preview 圖片
            $previewImg.attr("src", `${this.#imageData.servlet}?action=${this.#imageData.action}&ImageId=${info.extraId}&Thumb=false&t=${Date.now()}`);

            // 更新 carousel 圖片
            $carouselImg.attr("src", `${this.#imageData.servlet}?action=${this.#imageData.action}&ImageId=${info.extraId}&Thumb=false&t=${Date.now()}`);

            // 呼叫 callback，如果有需要通知其他邏輯（像 UI reset）
            this.callCallback("onImageRotated", key, info.hash);
        } catch (e) {
            alertBox.show("圖片旋轉失敗 ☠️");
            console.error(e);
        }
    }
    /**
     * （目前未被直接呼叫）若有需要外部強制換圖可用。
     */
    #updateCarouselImage(key, newUrl) {
        $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item[data-id="${key}"] img`).attr("src", `${newUrl}?t=${Date.now()}`);
    }
    /** 將 File 轉為 DataURL（用於本地預覽） */
    #fileToDataUrl(file) {
        return new Promise(resolve => {
            const reader = new FileReader();
            reader.onload = e => resolve(e.target.result);
            reader.readAsDataURL(file);
        });
    }
    /**
     * 建立單一張圖片的「預覽區塊」DOM
     * - wrapper: .preview-wrapper[data-id="${key}"]
     * - img:     .preview
     * - progress: 進度條
     * - remove-btn: 刪除按鈕（點擊後呼叫 handleDeleteRequest(key)）
     * - 點擊圖片：開啟 carousel（#openCarousel(key)）
     */
    buildPreviewElement(key, url, existing = false) {
        const wrapper = $(`<div class="preview-wrapper" data-id="${key}"></div>`);
        const img = $('<img class="preview">').attr("src", url);
        const progress = $(`<div class="progress"><div class="progress-bar" style="width:0%">0%</div></div>`);
        // const delBtn = $('<button class="remove-btn">×</button>').on("click", () =>
        // {
        //     this.handleDeleteRequest(key);
        // });
        const delBtn = $('<button class="remove-btn">×</button>');
        delBtn.on("click", (e) => {
            const actualKey = $(e.currentTarget).closest(".preview-wrapper").data("id").toString();
            this.handleDeleteRequest(actualKey);
        });
        wrapper.append(img, progress, delBtn);
        $(this.#previewTag).append(wrapper);

        if (existing) {
            // 已存在遠端的特別標註
            this.updateProgress(key, 100, true);
        }

        this.callCallback("onBuildPreview", key);
        img.on("click", () => this.#openCarousel(key));
    }
    /**
     * 只做 UI 刪除：
     * - 從 Map 移除該 key
     * - 刪掉對應 preview-wrapper 與 carousel-item
     *
     * ⚠️ 真正刪除後端資料由子類（例如 CloudinaryPreviewManager.handleDeleteRequest）接手。
     */
    deleteFromUI(key) {
        console.log(`deleteFromUI key=${key}`);
        if (!this.#previewInfoMap.has(key)) {
            console.warn(`⚠️ deleteFromUI: 找不到 key=${key}，UI 不會動手刪光光啦～`);
            return;
        }

        // 刪掉 Map 紀錄
        this.#previewInfoMap.delete(key);
        // 精準移除 preview 區塊
        $(`${this.#previewTag} .preview-wrapper[data-id="${key}"]`).remove();

        // 找到 carousel item
        const $carouselItem = $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item[data-id="${key}"]`);
        if ($carouselItem.hasClass("active"))
        {
            const $next = $carouselItem.next(".carousel-item");
            const $prev = $carouselItem.prev(".carousel-item");

            if ($next.length)
            {
                $next.addClass("active");
            } else if ($prev.length)
            {
                $prev.addClass("active");
            }
        }
        // 移除 carousel item
        $carouselItem.remove();
    }

    /**
     * 留給子類覆寫：
     * 例如：CloudinaryPreviewManager 會在這裡決定是呼叫後端刪除，或只刪 UI。
     */
    handleDeleteRequest(key)
    {
        this.callCallback("onDeleteRequested", key);
    }
    /**
     * 建立 carousel 的單一項目。
     * - 會在 .carousel-inner 內插入 <div class="carousel-item" data-id="key">...
     * - 第一張會自動加上 .active
     */
    buildCarouselItem(key, url) {
        const $carouselInner = $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-inner`);
        const item = $(`<div class="carousel-item" data-id="${key}"></div>`);
        if ($carouselInner.children().length === 0) item.addClass("active");
        item.append($('<img class="d-block w-100">').attr("src", url));
        this.callCallback("onBuildCarouselItem", key);
        $carouselInner.append(item);
    }

    /**
     * 打開 Modal，並將 carousel 移到與 key 對應的索引。
     * - 這裡必須把 jQuery 物件轉 DOM element 給 bootstrap API 使用。
     */

    #openCarousel(key) {
        const $items = $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item`);
        const $target = $items.filter(`[data-id="${key}"]`);
        const index = $items.index($target);

        if (index >= 0) {
            // 取 DOM element 而不是 jQuery object
            const modal = bootstrap.Modal.getOrCreateInstance($(this.#carouselModalTag)[0]);
            modal.show();

            modal._element.addEventListener("shown.bs.modal", () => {
                bootstrap.Carousel.getOrCreateInstance($(this.#carouselTag)[0]).to(index);
            }, { once: true });
        }
    }
    /**
     * 更新進度條 UI。
     * @param {string} key - 圖片 key
     * @param {number} percent - 0~100
     * @param {boolean} isUploaded - true 表示已上傳完成（會顯示「已上傳」）
     */
    updateProgress(key, percent, isUploaded = false) {
        console.log(`updateProgress key=${key}`);
        const $bar = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"] .progress-bar`);
        $bar.css("width", `${percent}%`).text(isUploaded ? "已上傳" : `${percent}%`);
        if (isUploaded) $bar.addClass("bg-danger");
    }
    /**
     * 上傳完成後「轉正」流程：
     * - 若 key 是 temp- 開頭，會把 Map/DOM 的 data-id 換成後端回來的 extraId
     * - existing 設為 true，並記住 extraId
     * - 進度條顯示 100%
     */
    markUploaded(key, extraId)
    {
        console.log(`markUploaded key=${key} extraId=${extraId}`);
        if (key.startsWith("temp-"))
        {
            const info = this.#previewInfoMap.get(key);
            if (!info)
            {
                console.log(`can't get key=${key} from previewInfoMap !!!`);
                return;
            } // 保險防爆

            // Map：以正式 ID 為新 key，保留原本資訊
            this.#previewInfoMap.set(extraId.toString(), { ...info, extraId });
            this.#previewInfoMap.delete(key);

            // DOM：同步把 data-id 換成正式 ID
            const $wrapper = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"]`);
            $wrapper.attr("data-id", extraId);
            // 重新綁定刪除按鈕（確保用新 key）
            $wrapper.find(".remove-btn").off("click").on("click", () => {
                this.handleDeleteRequest(extraId); // ✅ 綁定新 key
            });


            const $carouselItem = $(`${this.#carouselModalTag}`).find(this.#carouselTag)
                .find(`.carousel-item[data-id="${key}"]`);

            if ($carouselItem.length) {
                $carouselItem.attr("data-id", extraId);
            }

            this.callCallback("onMarkUploaded", key,extraId);
            key = extraId; // 改 key 後續要繼續使用
        }

        // 設定為已存在遠端
        // 設定 existing 與樣式
        const info = this.#previewInfoMap.get(key);
        if (info) {
            info.existing = true;
            info.extraId = extraId;
        }
        // 樣式與進度
        const $wrapper = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"]`);
        $wrapper.addClass("existing");

        this.updateProgress(key, 100, true);

    }

    /**
     * 清空所有狀態與 UI（與 releaseImagePreview 類似，不處理 Modal 顯示狀態）。
     */
    clear() {
        this.#previewInfoMap.clear();
        $(this.#previewTag).empty();
        $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-inner`).empty();
    }
    /**
     * 觸發外部定義的回呼，若沒有該方法則忽略。
     */
    callCallback(name, ...args) {
        if (typeof this.#callbacks[name] === "function") {
            this.#callbacks[name](...args);
        }
    }
    /**
     * 取得內部的 Map（若外部要進行更細的查詢/除錯用）。
     */
    getPreviewInfoMap() {
        return this.#previewInfoMap;
    }
}