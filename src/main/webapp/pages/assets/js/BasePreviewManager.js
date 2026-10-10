 // import {AutoAlert} from "./alert/autoAlert"

class BasePreviewManager {

    // ===============================================
    // 私有欄位（透過建構子注入）
    // ===============================================
    #previewTag;        // 預覽區容器 selector
    #carouselTag;       // Carousel 元件 selector
    #imageRotation;     // 後端圖片旋轉 API 設定 { servlet, action, parameter }
    #carouselModalTag;  // 包住 Carousel 的 Modal selector
    #modalContentPath;  // Modal HTML 片段路徑（用 loadModal() 動態載入）
    #$modalContainer;   // jQuery Modal 容器
    #imageData;         // 後端取圖 API 設定 { servlet, action }
    #previewInfoMap;    // Map<key, { file, hash, existing, extraId }>
    #callbacks;         // 事件回呼集合
    #alertBox;
    constructor(config) {
        console.log(`BasePreviewManager version 1.0.1`);
        this.#alertBox = new AutoAlert();
        this.#previewTag       = config.previewTag;
        this.#carouselTag      = config.carouselTag;
        this.#imageRotation    = config.imageRotation;
        this.#imageData        = config.imageData;
        this.#carouselModalTag = config.carouselModalTag;
        this.#modalContentPath = config.modalContentPath;
        this.#$modalContainer  = (config.modalContainer instanceof jQuery)
            ? config.modalContainer : $(config.modalContainer);

        this.#previewInfoMap = new Map();
        this.#callbacks      = config.callbacks || {};

        // ===========================================
        // 📌 統一事件委派：delete 與 preview 點擊
        // ===========================================
        $(document)
            // 刪除按鈕
            .on("click", `${this.#previewTag} .remove-btn`, (e) => {
                const key = $(e.currentTarget).closest(".preview-wrapper").data("id").toString();
                this.handleDeleteRequest(key);
            })
            // 點擊預覽圖片 → 開 carousel
            .on("click", `${this.#previewTag} img.preview`, (e) => {
                const key = $(e.currentTarget).closest(".preview-wrapper").data("id").toString();
                this._showCarousel(key);
            });
    }

    /**
     * 動態載入 Modal HTML。
     * 👉 旋轉按鈕的 click 綁定，請在子類或外部呼叫 `#rotateImage` / 加 CSS rotate。
     */
    async loadModal() {
        console.log('BasePreviewManager loadModal..=',this.#modalContentPath);
        return new Promise((resolve, reject) => {
            if (!this.#$modalContainer || this.#$modalContainer.length === 0) {
                reject("Modal container not found");
                return;
            }
            this.#$modalContainer.load(this.#modalContentPath, (response, status) => {
                if (status === "error") reject("Modal load failed");
                else {
                    this.callCallback("onModalLoaded");
                    resolve();
                }
            });
        });
    }

    /** 清空所有 preview / carousel 並關閉 Modal */
    releaseImagePreview() {
        $(this.#previewTag).empty();
        $(`${this.#carouselTag} .carousel-inner`).empty();
        this.#previewInfoMap.clear();

        const modal = bootstrap.Modal.getInstance($(this.#carouselModalTag)[0]);
        if (modal) modal.hide();
        this.callCallback("onReleasePreview");
    }

    /** 新增一張本地圖片 */
    async addImage(file) {
        const hash = await quickJpegHash(file);
        console.log(`addImage file=${file}`);
        for (let info of this.#previewInfoMap.values()) {
            if (info.hash === hash) {
                this.#alertBox.show("此圖已存在預覽");
                return;
            }
        }
        //產生一個臨時的key
        const tempId = "temp-" + crypto.randomUUID();
        this.#previewInfoMap.set(tempId, { file, hash, existing: false, tempId, rotation: 0 });


        const url = await this.#fileToDataUrl(file);
        this.buildPreviewElement(tempId, url, false);
        this.buildCarouselItem(tempId, url);
        this.#debugLogMap("addImage");
    }



    /** File → DataURL */
    #fileToDataUrl(file) {
        return new Promise(resolve => {
            const reader = new FileReader();
            reader.onload = e => resolve(e.target.result);
            reader.readAsDataURL(file);
        });
    }

    /** 建立預覽 DOM */
    buildPreviewElement(key, url, existing = false) {
        const wrapper = $(`<div class="preview-wrapper" data-id="${key}"></div>`);
        const img     = $('<img class="preview">').attr("src", url);
        const progress= $(`<div class="progress"><div class="progress-bar" style="width:0%">0%</div></div>`);
        const delBtn  = $('<button class="remove-btn">×</button>');

        wrapper.append(img, progress, delBtn);
        $(this.#previewTag).append(wrapper);

        if (existing) this.updateProgress(key, 100, true);

        this.callCallback("onBuildPreview", key);
    }

    /** 只刪 UI，不處理後端 */
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

        this.#debugLogMap("deleteFromUI");
    }

    /** 留給子類覆寫：例如 Cloudinary 要呼叫後端刪除 */
    handleDeleteRequest(key) {
        this.callCallback("onDeleteRequested", key);
    }

    /** 建立 carousel item */
    buildCarouselItem(key, url) {
        const $carouselInner = $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-inner`);
        const item = $(`<div class="carousel-item" data-id="${key}"></div>`);
        if ($carouselInner.children().length === 0) item.addClass("active");
        item.append($('<img class="d-block w-100">').attr("src", url));
        $carouselInner.append(item);
        this.callCallback("onBuildCarouselItem", key);
    }

    // 對外 API：開啟並同步角度
    _showCarousel(key) {
        const $wrapper = $(this.#previewTag).find(`.preview-wrapper[data-id="${key}"]`);
        if (!$wrapper.length)
        {
            console.log(`[_showCarousel] $wrapper.length=0`)
            return;
        }

        const actualKey = $wrapper.data("id").toString(); // 讀最新 data-id
        const info = this.#previewInfoMap.get(actualKey);
        if (!info)
        {
            console.log( this.#previewInfoMap);
            console.log(`[_showCarousel] can't get previewInfoMap where key=${actualKey}`);
            return;
        }

        // 套用旋轉角度
        const $carouselItem = $(`${this.#carouselModalTag} ${this.#carouselTag}`)
            .find(`.carousel-item[data-id="${actualKey}"]`);
        if (!$carouselItem.length) return;

        $carouselItem.find("img").css("transform", `rotate(${info.rotation || 0}deg)`);

        // 開啟 carousel
        this.#openCarousel($carouselItem.index());
        this.#debugLogMap("_showCarousel");
    }

// 對內方法：單純 UI 開啟 & 定位
    #openCarousel(index) {
        const modalEl = document.querySelector(this.#carouselModalTag);
        const carouselEl = modalEl.querySelector(this.#carouselTag);

        const modal = bootstrap.Modal.getOrCreateInstance(modalEl);
        modal.show();

        modalEl.addEventListener("shown.bs.modal", () => {
            bootstrap.Carousel.getOrCreateInstance(carouselEl).to(index);
        }, { once: true });
    }
    /** 更新進度條 */
    updateProgress(key, percent, isUploaded = false) {
        const $bar = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"] .progress-bar`);
        $bar.css("width", `${percent}%`).text(isUploaded ? "已上傳" : `${percent}%`);
        if (isUploaded) $bar.addClass("bg-success");
    }

    /** 上傳完成 → 正式 ID */
    markUploaded(key, extraId)
    {
        console.log(`markUploaded key=${key} extraId=${extraId}`);
        if (key.startsWith("temp-"))
        {
            const info = this.#previewInfoMap.get(key);
            if (!info) return;
            //因為key為String型態，所以要記得轉換

            
            this.#previewInfoMap.set(extraId.toString(), { ...info, extraId });
            this.#previewInfoMap.delete(key);

            // ✅ 更新 DOM data-id
            $(`${this.#previewTag} .preview-wrapper[data-id="${key}"]`).attr("data-id", extraId);
            $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item[data-id="${key}"]`).attr("data-id", extraId);

            this.callCallback("onMarkUploaded", key, extraId);
            key = extraId; // 更新 key，讓下面進度條更新正確
        }

        // ✅ 取最新 DOM data-id
        const $wrapper = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"]`);
        const actualKey = $wrapper.length ? $wrapper.data("id").toString() : key;

        const info = this.#previewInfoMap.get(actualKey);
        if (info) {
            info.existing = true;
            info.extraId = extraId;
        }

        $(`${this.#previewTag} .preview-wrapper[data-id="${actualKey}"]`).addClass("existing");
        this.updateProgress(actualKey, 100, true);
        this.#debugLogMap("markUploaded");
    }

    // ✅ 公用旋轉方法（每次旋轉 90 度）
    rotate(key) {
        const info = this.#previewInfoMap.get(key);
        if (!info) return;

        // 累加角度
        info.rotation = ((info.rotation || 0) + 90) % 360;

        // 套用到 preview item
        const $wrapper = $(`${this.#previewTag} .preview-wrapper[data-id="${key}"] img`);
        $wrapper.css("transform", `rotate(${info.rotation}deg)`);

        // 套用到 carousel item
        const $carouselItem = $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-item[data-id="${key}"] img`);
        $carouselItem.css("transform", `rotate(${info.rotation}deg)`);

        console.log(`🔄 rotated preview-item=${key}, rotation=${info.rotation}`);
    }

    // ✅ 取得旋轉角度（給子類上傳更新用）
    getRotation(key) {
        const info = this.#previewInfoMap.get(key);
        return info ? info.rotation || 0 : 0;
    }


    /** 清空狀態 */
    clear() {
        this.#previewInfoMap.clear();
        $(this.#previewTag).empty();
        $(`${this.#carouselModalTag} ${this.#carouselTag} .carousel-inner`).empty();
    }

    /** 觸發外部 callback */
    callCallback(name, ...args) {
        if (typeof this.#callbacks[name] === "function") {
            this.#callbacks[name](...args);
        }
    }

    /** 取出 Map（除錯用） */
    getPreviewInfoMap() {
        return this.#previewInfoMap;
    }

    /** 🕵️‍♂️ Debug 用：印出目前 Map 狀態 */
    #debugLogMap(action) {
        const snapshot = Array.from(this.#previewInfoMap.entries())
            .map(([k, v]) => ({ key: k, ...v }));
        console.log(`📌 [${action}] previewInfoMap =`, snapshot);
    }
}
