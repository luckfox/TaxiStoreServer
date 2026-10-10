<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>攝像頭拍照與上傳</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            font-size: 2rem;
            margin-bottom: 30px;
        }
        #video {
            width: 100%;
            height: auto;
            border: 2px solid #ddd;
            border-radius: 8px;
        }
        #canvas {
            display: none;
            width: 100%;
            height: auto;
            border: 2px solid #ddd;
            margin-top: 20px;
            border-radius: 8px;
        }
        .photo-preview {
            text-align: center;
            margin-top: 20px;
        }
        #photo {
            max-width: 100%;
            max-height: 300px;
            border-radius: 8px;
            margin-top: 10px;
        }
        .btn {
            font-size: 1.2rem;
            margin-top: 20px;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        .alert {
            display: none;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>攝像頭拍照與上傳</h1>

    <!-- 輸入 userId -->
    <div class="mb-3">
        <label for="userId" class="form-label">請輸入 User ID：</label>
        <input type="text" id="userId" class="form-control" placeholder="請輸入 User ID">
    </div>

    <!-- 顯示攝像頭畫面 -->
    <div class="mb-3">
        <video id="video" autoplay></video>
    </div>

    <!-- 拍照按鈕 -->
    <button id="captureButton" class="btn btn-primary">拍照</button>

    <!-- 顯示拍照後的圖片 -->
    <div class="photo-preview">
        <canvas id="canvas"></canvas>
        <img id="photo" alt="拍照結果">
    </div>

    <!-- 上傳按鈕 -->
    <button id="uploadButton" class="btn btn-secondary">上傳圖片</button>

    <!-- 顯示上傳結果 -->
    <div id="uploadAlert" class="alert alert-success">
        圖片已成功上傳！
    </div>

    <!-- 顯示錯誤信息 -->
    <div id="errorAlert" class="alert alert-danger">
        上傳圖片失敗，請稍後再試！
    </div>
</div>

<script>
    // 1. 開啟攝像頭
    const video = document.getElementById('video');
    const canvas = document.getElementById('canvas');
    const photo = document.getElementById('photo');
    const captureButton = document.getElementById('captureButton');
    const uploadButton = document.getElementById('uploadButton');
    const uploadAlert = document.getElementById('uploadAlert');
    const errorAlert = document.getElementById('errorAlert');
    const userIdInput = document.getElementById('userId');

    // 取得攝像頭影像
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(stream => {
            video.srcObject = stream;
        })
        .catch(error => {
            console.error('無法取得攝像頭:', error);
        });

    // 2. 拍照功能
    captureButton.addEventListener('click', function() {
        const context = canvas.getContext('2d');
        const width = video.videoWidth;
        const height = video.videoHeight;

        // 設定canvas的尺寸
        canvas.width = width;
        canvas.height = height;

        // 將攝像頭影像畫到canvas上
        context.drawImage(video, 0, 0, width, height);

        // 顯示拍照後的圖片
        const imageUrl = canvas.toDataURL('image/jpeg');
        photo.src = imageUrl;
        photo.style.display = 'block'; // 顯示圖片
    });

    // 3. 上傳圖片到後端
    uploadButton.addEventListener('click', function() {
        const userId = userIdInput.value.trim();
        if (!userId) {
            alert("請輸入 User ID！");
            return;
        }

        const imageUrl = canvas.toDataURL('image/jpeg');
        const base64Image = imageUrl.split(',')[1]; // 只取 base64 部分

        // 用 jQuery AJAX 傳送
        $.ajax({
            url: '/UploadImageServlet?action=ajaxUploadToImgur',
            type: 'POST',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify({
                userId: userId,
                image: base64Image
            }),
            dataType: 'json',
            success: function(response) {
                console.log('圖片上傳成功:', response);
                uploadAlert.style.display = 'block'; // 顯示成功訊息
                errorAlert.style.display = 'none';  // 隱藏錯誤訊息
            },
            error: function(error) {
                console.error('上傳圖片失敗:', error);
                uploadAlert.style.display = 'none';  // 隱藏成功訊息
                errorAlert.style.display = 'block'; // 顯示錯誤訊息
            }
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
