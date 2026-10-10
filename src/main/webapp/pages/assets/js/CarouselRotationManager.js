class CarouselRotationManager {
    /**
     * @param {Object} opt
     * @param {string} opt.modalSelector         - Modal 的 selector
     * @param {string} opt.carouselSelector      - Carousel 的 selector
     * @param {string} [opt.rotateBtnSelector]   - 旋轉按鈕 selector
     * @param {Map} opt.previewInfoMap            - 由 ImagePreviewManager 傳入的 previewInfoMap
     * @param {(key:string, angle:number, imgEl:HTMLImageElement)=>void} opt.onRotationChanged - 通知外層更新 rotation
     */
    constructor({ modalSelector,
                    carouselSelector,
                    rotateBtnSelector = "#rotateImageBtn",
                    updateBtnSelector="#updateImageBtn",
                    previewInfoMap,
                    onRotationChanged,
                    onUpdateImage })
    {
        console.log(`CarouselRotationManager constructor`);
        this.modalSelector = modalSelector;
        this.carouselSelector = carouselSelector;
        this.rotateBtnSelector = rotateBtnSelector;
        this.updateBtnSelector = updateBtnSelector;
        this.previewInfoMap = previewInfoMap;
        this.onRotationChanged = typeof onRotationChanged === 'function' ? onRotationChanged : () => {};
        this.onUpdateImage = typeof onUpdateImage === 'function' ? onUpdateImage : () => {};

        // 🔗 事件委派：避免動態載入造成綁定失效
        $(document)
            .off('click.rotate.carousel', `${this.modalSelector} ${this.rotateBtnSelector}`)
            .on('click.rotate.carousel', `${this.modalSelector} ${this.rotateBtnSelector}`, (e) => {
                const { key, imgEl } = this.getActiveItem();
                if (!key || !imgEl)
                {
                    console.log(`carousel rotate fail ,key=${key} `);
                    return;
                }

                // 1️⃣ 從 previewInfoMap 取得目前旋轉角度
                const info = this.previewInfoMap.get(key) || {};
                const current = info.rotation || 0;

                // 2️⃣ 計算下一角度
                const next = (current + 90) % 360;

                // 3️⃣ 套用 CSS
                imgEl.style.transform = `rotate(${next}deg)`;
                console.log(`CSS rotate key=${key}, angle=${next}`);

                // 4️⃣ 通知上層更新 previewInfoMap
                this.onRotationChanged(key, next, imgEl);
            });
        // 🔗 更新按鈕事件委派
        $(document)
            .off('click.update.carousel', `${this.modalSelector} ${this.updateBtnSelector}`)
            .on('click.update.carousel', `${this.modalSelector} ${this.updateBtnSelector}`, (e) => {
                const { key, imgEl } = this.getActiveItem();
                if (!key || !imgEl)
                {
                    console.log(`carousel update fail ,key=${key} `);
                    return;
                }

                const info = this.previewInfoMap.get(key) || {};
                const current = info.rotation || 0;

                if (current !== 0) {
                    console.log(`onUpdateImage key=${key}, angle=${current}`);
                    this.onUpdateImage(key, current, imgEl);
                }
            });
    }

    /**
     * 取得目前 carousel 的 active 項目之 key 與 img element。
     */
    getActiveItem() {
        const $active = $(`${this.modalSelector} ${this.carouselSelector} .carousel-item.active`);
        const key = $active.data('id') ?? null;
        const imgEl = $active.find('img')[0] || null;
        return { key: key !== null ? String(key) : null, imgEl };
    }
}
