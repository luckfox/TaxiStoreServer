 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
  <meta charset="UTF-8">
  <title>Dropzone + Carousel Demo</title>
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
<script type="text/javascript" src="<%= assetsPath %>/js/hashUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/CloudinaryPreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/fineImageUtils/fineImageUtils.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImagePreviewManager.js?<%= version %>"></script>
<script type="text/javascript" src="<%= assetsPath %>/js/ImageUploader.js?<%= version %>"></script>
<script>
  /**
   * 初始化 dropzone 功能，包含拖曳、選擇、預覽、hash 比對
   */
  $(function () {
    const CLOUD_NAME = "dzfzvyno4";
    const UPLOAD_PRESET = "taxiStore_preset";
    let manager = null;
    let uploadedMetaList = [];

    const $uploadBtn = $("#uploadBtn");
    const $postLineBtn = $("#postLineBtn");
    const $dropZone = $("#previewArea");
    const $fileInput = $("#fileInput");

    async function init() {
      manager = await CloudinaryPreviewManager.create({
        previewTag: "#previewArea",
        carouselTag: "#carouselPreview",
        carouselModalTag: "#imageCarouselModal",
        modalContainer: "#modalContainer",
        modalContentPath: "/pages/layout/modals/ImageCarousel.html",
        cloudName: CLOUD_NAME,
        uploadPreset: UPLOAD_PRESET,
        callbacks: {
          onInitialized:()=>{
            console.log(`Initizlized OK!`);
          },
          onInitializeFail:()=>{
            console.log(`Initizlized fail!`);
          },
          onDelete:(hash)=>{
            console.log(`delete hash=\${hash} `);
          },
          onMetadataSaved:(hash, metadata)=>{
            console.log(`Saved hash=\${hash}`);
          },
          onProgress:(hash, percent)=>{
            console.log(`progress hash=\${hash} \${percent}%`);
          },
          onComplete: (metaList) => {
            uploadedMetaList = metaList;
            console.log("✅ All uploads complete, metadata collected:", uploadedMetaList);
          }
        }
      });
    }

    init(); // 初始化 manager

    // 上傳全部
    $uploadBtn.on("click", function() {
      if (manager) {
        manager.uploadAll();
      }
    });

    // 拖曳區域
    $dropZone.on("dragover", function(e) {
      e.preventDefault();
      $(this).css("background-color", "#eef");
    }).on("dragleave", function() {
      $(this).css("background-color", "");
    }).on("drop", async function(e) {
      e.preventDefault();
      $(this).css("background-color", "");
      const files = e.originalEvent.dataTransfer.files;
      for (let file of files) {
        await manager.addImagePreview(file);
      }
    });

    // 選擇檔案
    $fileInput.on("change", async function() {
      const files = this.files;
      for (let file of files) {
        await manager.addImagePreview(file);
      }
    });
    // 切換頁面
    $("#switchBtn").on("click", function () {
      window.location.href = "<%= request.getContextPath() %>/demo/cloudinary/cloudinaryManager.jsp";
    });
    // 發送到 LINE
    $postLineBtn.on("click", function () {
      const userId = $('#lineID').val();
      const message = $('#message').val();

      if (!uploadedMetaList || uploadedMetaList.length === 0) {
        alert("請先上傳圖片！");
        return;
      }

      // 限制最多 4 張
      const limitedMetaList = uploadedMetaList.slice(0, 4);

      pushMultiImageMessage(userId, message, limitedMetaList);
    });

    /**
     * 呼叫後端發送 LINE
     */
    function pushMultiImageMessage(userId, message, metas) {
      const payload = {
        userId: userId,
        message: message,
        imagePaths: metas
      };

      fetch('lineUserServlet?action=ajaxPushMultiImageMessageByCloudinaryMetadata', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      }).then(response => response.json())
              .then(result => {
                if (result.success) {
                  alert("訊息發送成功！");
                } else {
                  alert("發送失敗：" + result.errorMessage);
                }
              }).catch(error => {
        console.error("發送錯誤:", error);
        alert("發送失敗，請稍後再試！");
      });
    }
  });

</script>
</body>
</html>
