







function remoteSync(fineNo,previewInfoMap,previewTag,carouselTag)
{
    console.log(`remoteSync=${fineNo}`);
    $.getJSON(`fineImageServlet?action=ajaxGetImageMetasByFineNo&fineNo=${fineNo}`, function (result)
    {
        console.log(`fineImageServlet?action=ajaxGetImageMetasByFineNo&fineNo=${fineNo}`);
        if (!result.success) {
            console.error('❌ 無法取得圖片 metadata');
            return;
        }
        console.log(`111111`);
        const metas = result.data;
        metas.forEach((meta, index) => {
            console.log()
            const imageId = meta.imageId;
            const hashCode = meta.hashCode;
            const thumbUrl = `fineImageServlet?action=ajaxGetImageByImageId&ImageId=${imageId}&Thumb=true`;
            const imageUrl = `fineImageServlet?action=ajaxGetImageByImageId&ImageId=${imageId}&Thumb=false`;
            //addImagePreviewWithHash({source:thumbUrl, existing:true, imageId:imageId, hashCode:hashCode},existingHashes,hashToFileMap);
            addImagePreviewWithHash2({source:imageUrl, existing:true, imageId:imageId, hashCode:hashCode},previewTag,carouselTag,previewInfoMap);
        });
    });
}


/*
 * 新增預覽圖片（含來自前端或後端來源）
 * @param {Object} param0 - 含圖片來源、是否為後端既存、影像 ID 與雜湊
 * @param {Map} previewInfoMap - 存放 hash 對應圖片資訊(hash,ImageId,File)的 Map
 */
function addImagePreviewWithHash2({ source, existing, imageId, hashCode },previewTag,carouselTag, previewInfoMap)
{
    console.log(`source=${source} hashCode=${hashCode} imageId=${imageId} existing=${existing}`);

    const url = (typeof source === 'string') ? source : URL.createObjectURL(source);
    console.log(`url=${url}`);

    /*
     * 將圖片資訊加入 Map 並更新畫面顯示
     * @param {string} hashCode - 圖片的雜湊碼
     */
    const finalizeAdd = (hashCode) => {
        const alreadyExists = previewInfoMap.has(hashCode);

        if (alreadyExists)
        {
            const info = previewInfoMap.get(hashCode);
            // 來自後端：標記為已存在
            if (existing)  info.IsExisting = true;
            // 可加強處理：視覺標示（若有需求）
            return; // 已存在則不再處理
        }

        // 加入新的項目
        previewInfoMap.set(hashCode, {hashCode, IsExisting: Boolean(existing), File: source});

        // 預覽元素與刪除按鈕
        const $preview = buildPreviewElement(hashCode, url, existing, imageId);
        bindDeleteHandler2($preview, hashCode, previewInfoMap);
        $(`${previewTag}`).append($preview);

        // 輪播元素
        const $carouselItem = buildCarouselItem(hashCode, url);
        $(`${carouselTagTag} .carousel-inner`).append($carouselItem);
    };

    if (hashCode) finalizeAdd(hashCode);
    else if (source instanceof File) quickJpegHash(source).then(finalizeAdd);


    /*
 * 建立預覽縮圖區塊 DOM（帶有刪除按鈕與點圖可瀏覽功能）
 *
 * @param {string} hash - 該圖片的 hash 值
 * @param {string} url - 圖片瀏覽 URL
 * @param {boolean} existing - 是否為後端圖片
 * @param {string} [imageId] - 若為後端圖片，對應的 imageId（用於刪除呼叫）
 * @returns {JQuery} 預覽 DOM 區塊
 */
    function buildPreviewElement(hash, url, existing, imageId) {
        const wrapper = $('<div class="preview-wrapper"></div>').attr('data-hash', hash);

        // 後端圖片需要額外標記與紀錄 imageId
        if (existing) {
            wrapper.addClass('existing');
            if (imageId) wrapper.attr('data-image-id', imageId);
        }

        // 建立圖片本體與點擊事件（點擊時會開啟 carousel modal）
        const img = $('<img>').attr('src', url).on('click', () => {
            const allItems = $('#carouselPreview .carousel-item');
            const targetIndex = allItems.index(allItems.filter(`[data-hash="${hash}"]`));

            if (targetIndex >= 0) {
                // 將 carousel 切到正確位置，並開啟 modal
                const bsCarousel = bootstrap.Carousel.getOrCreateInstance('#carouselPreview');
                bsCarousel.to(targetIndex);
                new bootstrap.Modal($('#imageCarouselModal')).show();
            }
        });
        // 加上進度條區塊
        const progress = $(`
        <div class="progress mt-0" style=" height: 5px;">
            <div class="progress-bar" role="progressbar" style="width: 0%">0%</div>
        </div>`);
        return wrapper.append(img,progress);
    }


}





/*
 * 建立 carousel 的單一 item 區塊（原圖顯示區）
 *
 * @param {string} hash - 圖片的 hash 值
 * @param {string} url - 圖片 URL
 * @returns {JQuery} Carousel 區塊
 */
function buildCarouselItem(hash, url) {
    const item = $('<div class="carousel-item"></div>').attr('data-hash', hash);

    // 若是第一張圖要加上 active 才能顯示（Bootstrap carousel 要求）
    if ($('#carouselPreview .carousel-item').length === 0) {
        item.addClass('active');
    }

    const img = $('<img>')
        .addClass('d-block w-100')
        .attr('src', url)
        .attr('alt', '原圖');

    return item.append(img);
}
/*
 * 綁定預覽圖片中的刪除按鈕行為（前端刪 DOM，後端選擇性發 AJAX）
 *
 * @param {JQuery} wrapper - 預覽圖容器元素
 * @param {string} hash - 圖片 hash 值，用於同步移除 carousel 中的對應圖片
 */
function bindDeleteHandler2(wrapper, hash,previewInfoMap) {
    const btn = $('<button>').addClass('remove-btn').text('×');

    btn.on('click', function () {
        const isExisting = wrapper.hasClass('existing');
        const imgId = wrapper.data('image-id');

        // 從畫面上移除預覽區與 carousel 區圖片
        wrapper.remove();
        $('#carouselPreview .carousel-item[data-hash="' + hash + '"]').remove();

        // 從 hashSet 移除
        previewInfoMap.delete(hash);

        // 若為後端圖片，發送刪除請求（此處僅 log，可視需求打開呼叫）
        if (isExisting && imgId) {
            console.log(`刪除後端圖片 ImageId=${imgId}`);
            // 真正刪除 AJAX 可在此加上：
            $.getJSON(`fineImageServlet?action=ajaxDeleteImageByImageId&ImageId=${imgId}`, function (result)
            {
                alert(`成功刪除!`)
            });
        }
    });

    wrapper.append(btn);
}

/*
 * 綁定預覽圖片中的刪除按鈕行為（前端刪 DOM，後端選擇性發 AJAX）
 *
 * @param {JQuery} wrapper - 預覽圖容器元素
 * @param {string} hash - 圖片 hash 值，用於同步移除 carousel 中的對應圖片
 */
function bindDeleteHandler(wrapper, hash,existingHashes) {
    const btn = $('<button>').addClass('remove-btn').text('×');

    btn.on('click', function () {
        const isExisting = wrapper.hasClass('existing');
        const imgId = wrapper.data('image-id');

        // 從畫面上移除預覽區與 carousel 區圖片
        wrapper.remove();
        $('#carouselPreview .carousel-item[data-hash="' + hash + '"]').remove();

        // 從 hashSet 移除
        existingHashes.delete(hash);

        // 若為後端圖片，發送刪除請求（此處僅 log，可視需求打開呼叫）
        if (isExisting && imgId) {
            console.log(`刪除後端圖片 ImageId=${imgId}`);
            // 真正刪除 AJAX 可在此加上：
            $.getJSON(`fineImageServlet?action=ajaxDeleteImageByImageId&ImageId=${imgId}`, function (result)
            {
                alert(`成功刪除!`)
            });
        }
    });

    wrapper.append(btn);
}
