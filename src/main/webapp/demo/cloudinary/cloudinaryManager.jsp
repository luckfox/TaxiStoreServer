<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>圖片管理</title>
    <%@ include file="../../pages/common/head.jsp" %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>圖片管理後台</h2>

<button id="switch3Btn" style="margin-top: 20px;">切換到測試頁面3</button>
<button id="switch4Btn" style="margin-top: 20px;">切換到測試頁面4</button>
<button id="deleteSelectedBtn" style="margin-top: 20px;">刪除選取項目</button>

<table border="1" cellpadding="5" id="imageTable">
    <thead>
    <tr>
        <th><input type="checkbox" id="selectAll"></th>
        <th>縮圖</th>
        <th>hashCode</th>
        <th>Cloudinary URL</th>
        <th>建立時間</th>
        <th>bytes</th>
        <th>刪除</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
    $(function () {
        // 切換頁面按鈕
        $("#switc3Btn").on("click", function () {
            window.location.href = "<%= request.getContextPath() %>/demo/cloudinary/ImageMetadata_demo.jsp";
        });
        $("#switch4Btn").on("click", function () {
            window.location.href = "<%= request.getContextPath() %>/demo/cloudinary/CloudinaryPreviewManager_demo.jsp";
        });

        // 讀取所有圖片
        function loadImages() {
            $.getJSON("imageMetadataServlet?action=getAll", function (response) {
                if (response.success) {
                    let html = "";
                    response.data.forEach(function (item) {
                        html += "<tr>";
                        html += "<td><input type='checkbox' class='selectItem' data-id='" + item.imageId + "' data-pid='" + item.cloudinaryPublicId + "'></td>";
                        html += "<td><img src='" + item.cloudinarySecureUrl + "' width='100'></td>";
                        html += "<td>" + item.hashCode + "</td>";
                        html += "<td><a href='" + item.cloudinarySecureUrl + "' target='_blank'>Link</a></td>";
                        html += "<td>" + (item.createTime || "N/A") + "</td>";
                        html += "<td>" + item.bytes + "</td>";
                        html += "<td><button class='deleteBtn' data-id='" + item.imageId + "' data-pid='" + item.cloudinaryPublicId + "'>刪除</button></td>";
                        html += "</tr>";
                    });
                    $("#imageTable tbody").html(html);
                }
            });
        }

        // 單筆刪除
        $("#imageTable").on("click", ".deleteBtn", function () {
            const imageId = $(this).data("id");
            const publicId = $(this).data("pid");

            if (!confirm("確定要刪除？")) return;

            $.post("imageMetadataServlet?action=deleteByImageId", {imageId: imageId, publicId: publicId}, function (resp) {
                if (resp.success) {
                    alert("刪除成功！");
                    loadImages();
                } else {
                    alert("刪除失敗！");
                }
            }, "json");
        });

        // 批次刪除
        $("#deleteSelectedBtn").on("click", function () {
            const selected = $(".selectItem:checked");
            if (selected.length === 0) {
                alert("請先選擇要刪除的項目！");
                return;
            }

            if (!confirm("確定要刪除選取的 " + selected.length + " 筆資料？")) return;

            // 逐筆刪除 (你也可以設計後端支援批次刪除更有效率)
            selected.each(function () {
                const imageId = $(this).data("id");
                const publicId = $(this).data("pid");

                $.post("imageMetadataServlet?action=deleteByImageId", {imageId: imageId, publicId: publicId}, function (resp) {
                    // 可以做單筆刪除成功提示，也可以只等全部執行後 reload
                    loadImages();
                }, "json");
            });
        });

        // 全選功能
        $("#selectAll").on("change", function () {
            $(".selectItem").prop("checked", this.checked);
        });

        // 頁面載入自動呼叫
        loadImages();
    });
</script>
</body>
</html>
