<%--
  Created by IntelliJ IDEA.
  User: 張峻銘
  Date: 2025/5/11
  Time: 上午 02:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>jQuery Load Modal Example</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <!-- Bootstrap (可選，如果你想用 modal 效果) -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="p-3">

<h2>主頁面</h2>
<button id="loadModalBtn" class="btn btn-primary">載入模組內容</button>

<!-- 放 modal 內容的容器 -->
<div id="modalContainer"></div>

<script>
  $("#loadModalBtn").on("click", function () {
    //let contentPath="/pages/layout/modals/userInfoEditorModal.html";
    let contentPath = "/pages/layout/modals/LineUserInfoEditor.html?_="+ new Date().getTime();
    $("#modalContainer").load(contentPath, function () {
      // 載入完畢後顯示 modal（如果有 bootstrap modal）
      $("#editModal").modal("show");
    });
  });
</script>

</body>
</html>
