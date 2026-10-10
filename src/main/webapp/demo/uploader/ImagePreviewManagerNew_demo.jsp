<%--
  Created by IntelliJ IDEA.
  User: luckf
  Date: 2025/6/23
  Time: 上午 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../../pages/common/head.jsp" %>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Dropzone -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.9.3/dropzone.min.css" rel="stylesheet">
    <style>
        #dropzoneLabel {
            border: 2px dashed #0d6efd;
            padding: 40px;
            text-align: center;
            color: #6c757d;
            cursor: pointer;
        }
        .preview-wrapper {
            position: relative;
            display: inline-block;
        }
        /*.preview-wrapper img {*/
        /*  width: 100px;*/
        /*  height: 100px;*/
        /*  object-fit: cover;*/
        /*  border: 3px solid transparent;*/
        /*}*/
        .preview-wrapper img {
            width: 100px;
            height: 100px;
            border: 3px solid white; /* 你剛剛加了 width，繼續保留！ */
            border-radius: 6px;
            margin: 2px;
            /*max-width: 100px;*/
            /*max-height: 100px;*/
        }
        .preview-wrapper.existing img {
            width: 100px;
            height: 100px;
            border: 3px solid red; /* 你剛剛加了 width，繼續保留！ */
            border-radius: 6px;
            margin: 2px;
            /*max-width: 100px;*/
            /*max-height: 100px;*/
        }
        .remove-btn {
            position: absolute;
            top: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.6);
            color: white;
            border: none;
            border-radius: 50%;
            width: 24px;
            height: 24px;
            cursor: pointer;
        }

        /*#imageCarouselModal .modal-dialog {*/
        /*  max-width: 800px;       !* 調整寬度 *!*/
        /*  max-height: 600px;      !* 調整高度 *!*/
        /*}*/

        /*#imageCarouselModal .modal-body {*/
        /*  padding: 0;*/
        /*  display: flex;*/
        /*  justify-content: center;*/
        /*  align-items: center;*/
        /*  max-height: 500px;     !* 防止圖片太高溢出 *!*/
        /*  overflow: hidden;*/
        /*}*/

        #imageCarouselModal .carousel-inner {
            width: 100%;
            text-align: center;
        }

        #imageCarouselModal .carousel-item img {
        //max-height: 480px;    /* ✅ 限制圖片最大高度 */
            width: auto;
            margin: 0 auto;
            object-fit: contain;  /* 確保圖片保持比例不變形 */
        }
    </style>
</head>
<body class="p-4">

<!-- Drop 區域 -->
<label id="dropzoneLabel" class="btn btn-primary w-100 py-3 fw-bold text-center">
  拖曳圖片到這裡，或點我選擇檔案 😎
  <input id="fileInput" type="file" accept="image/*" hidden multiple>
</label>
<button id="switchBtn" class="btn btn-primary mt-2">管理頁面</button>
<button id="uploadBtn" class="btn btn-primary mt-2">上傳圖片</button>
<button id="postLineBtn" class="btn btn-primary mt-2">傳送到LINE</button>
<button id="syncBtn" class="btn btn-primary mt-2">遠端同步</button>
<button id="clearBtn" class="btn btn-primary mt-2">重置預覽</button>
<label id="fineNo_label" for="fineNo" >罰單編號</label>
<input id="fineNo" type="text" value="AG123456">
<label id="lineID_label" for="lineID" >LineID</label>
<input id="lineID" type="text" value="Uc5f2f40eebcbff5ad54e21a6f411a97e">
<label id="message_label" for="message">message</label>
<input id="message" type="text" value="Hello!這是測試訊息">


<div id="previewArea" class="mt-3 d-flex flex-wrap gap-2"></div>

<div id="modalContainer"></div>
<!-- JS 依賴 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.9.3/dropzone.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/pica@8.0.0/dist/pica.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-sha256@0.9.0/build/sha256.min.js"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/alert/autoAlert.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/hashUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/LinePushMessage.js?<%= version %>"></script>
<%--<script type="text/javascript" src="<%= assetsPath %>/js/CloudinaryPreviewManager.js?<%= version %>"></script>--%>
<script type="text/javascript" src="<%= assetsPath %>/js/fineImageUtils/fineImageUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/CarouselRotationManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/BasePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImagePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImageUploader.js?<%= version %>"></script>
<script>
    $(function () {
        const $fineNo=$(`#fineNo`);
        let $uploadBtn=$(`#uploadBtn`);
        let $syncBtn=$(`#syncBtn`);
        let $clearBtn=$(`#clearBtn`);
        const $postLineBtn=$(`#postLineBtn`);

        const previewTag="#previewArea";
        const carouselTag="#carouselPreview";
        const carouselModalTag="#imageCarouselModal";
        const carouselModalHTML="/pages/layout/modals/ImageCarousel.html?_="+ new Date().getTime();
        const $module_container=$("#modalContainer");
        let _metaMap=new Map();
        (async () => {
            const config = {
                previewTag: previewTag,               // 預覽區容器
                carouselTag: carouselTag,          // Carousel 內部
                carouselModalTag: carouselModalTag,  // Modal 外層
                modalContentPath: carouselModalHTML, // Modal HTML路徑
                modalContainer: $("#modalContainer"),     // Modal掛載容器
                imagesMeta:{servlet:"fineImageServlet",action:"ajaxGetImageMetasByFineNo",parameter:"fineNo"},  //取得imagesMeta
                imageData:{servlet:"fineImageServlet",action:"ajaxGetImageByImageId",parameter:"ImageId"},      //取得image圖像資料
                imageDelete:{servlet:"fineImageServlet",action:"ajaxDeleteImageByImageId",parameter:"ImageId"},
                imageUpload:{servlet:"fineImageServlet",action:"ajaxUploadFineImage",parameter:"fineNo"},
                imageRotation:{servlet:"fineImageServlet",action:"ajaxRotationFineImageByImageId",parameter:"ImageId",parameter1:"angle"},

                callbacks: {
                    onProgress:(hash,percent)=>{
                        console.log(`event:onProgress hash=\${hash} percent=\${percent}`);
                    },
                    onBuildPreview: (hash)=>{
                        console.log(`event:onBuildPreview hash=\${hash}`);
                    },
                    onBuildCarouselItem:(hash)=>{
                        console.log(`event:onBuildCarouselItem hash=\${hash}`);
                    },
                    onUploadSuccess:(hash)=>{
                        console.log(`event:onUploadSuccess hash=\${hash}`);
                    },
                    onUploadAllSuccess:()=>{
                        console.log(`event:onUploadAllSuccess `);
                    },
                    onRemoteSync:(fineNo,metaMap)=>{
                        console.log(`event:onRemoteSync fineNo=\${fineNo} hash=\${metaMap}`,metaMap);
                        _metaMap=metaMap;
                    },
                    onReleasePreview:()=>{
                        console.log(`event:onReleasePreview `);
                    },
                    onModalLoaded:()=>{
                        console.log(`event:onModalLoaded `);
                    },
                    onRemoteDeleteSuccess:(hash)=>{
                        console.log(`event:onRemoteDeleteSuccess hash=\${hash}`);
                    },
                    onDeleteFailed:( hash, err)=>
                    {
                        console.log(`event:onDeleteFailed  \${err}`);
                    },
                    onLocalDelete:(hash)=>
                    {
                        console.log(`event:onLocalDelete  \${hash}`);
                    }
                }
            };

            const manager = new ImagePreviewManager(config);
            await manager.loadModal();

            // 你可以呼叫 remoteSync 去同步現有資料
            manager.remoteSync($fineNo.val());  // 填入你的 fineNo 罰單編號

            // 假設你有 <input type="file" multiple id="fileInput">
            $("#fileInput").on("change", async function () {
                for (const file of this.files) {
                    await manager.addImage(file);
                }
            });

            $("#uploadBtn").on("click", async () => {
                let fineNo=$("#fineNo").val();
                await manager.uploadAll(fineNo);
                alert("✅ 全部上傳完成！");
            });
            $('#syncBtn').on('click',function()
            {
                let fineNo=$(`#fineNo`).val();
                console.log(`sync button click fineNo=\${fineNo}`);
                manager.remoteSync(fineNo);
            });
            $('#clearBtn').on('click',function()
            {
                manager.releaseImagePreview();
                console.log('✅ 圖片清除完畢');
            });
            $('#postLineBtn').on('click',function(){
                console.log(`postLineBtn click`);
                let userId=$('#lineID').val();
                let message=$('#message').val();

                if (_metaMap instanceof Map && _metaMap.size > 0) {
                    // 限制最多傳送 4 筆
                    const limitedMetaMap = new Map([..._metaMap.entries()].slice(0, 4));

                    pushMultiImageMessageUnified(userId, message, limitedMetaMap, false);
                } else {
                    alert("目前沒有可用的圖片可以推送！");
                }
            });
            // 處理拖曳檔案
            $('#dropzoneLabel').on('dragover', function (e)
            {
                e.preventDefault();
                $(this).addClass('bg-light');
            }).on('dragleave drop', function (e)
            {
                e.preventDefault();
                $(this).removeClass('bg-light');
            }).on('drop', async function (e)
            {
                const dt = e.originalEvent.dataTransfer;
                const files = dt.files;

                for (const file of files)
                {
                    await manager.addImage(file);

                }
            });
        })();

        function lockUploadButtons() {
            $uploadBtn.prop('disabled', true);
            $syncBtn.prop('disabled', true);
            $clearBtn.prop('disabled', true);
        }
        function resetUploadUI()
        {
            $uploadBtn.prop('disabled', false);
            $syncBtn.prop('disabled', false);
            $clearBtn.prop('disabled', false);

            setTimeout(() => {
            }, 800);
        }
    });
</script>
</body>
</html>
