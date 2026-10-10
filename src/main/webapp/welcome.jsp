<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome Page 1.0</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- Bootstrap (可選，如果你想用 modal 效果) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    $(function () {
        $("#userServletBtn").click(function () {

            $.getJSON('userServlet', {action: 'login', userNo: "0000", password: "3795"})
                .done(function (data) {
                    console.log('Response:', data);
                    // 处理后端返回的数据
                    if (data.success == "true") {
                        alert('登录成功！');
                        //window.location.href = "<%= request.getContextPath() %>/pages/login_success.jsp";
                        //console.log("login success! href="+window.location.href);
                    } else {
                        alert('登录失败，错误信息: ' + data.message);
                    }
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    console.error('Error during login:', textStatus, errorThrown);
                    alert('系统错误，请稍后再试！');
                });
        });

        $("#lineUserServletBtn").click(function () {
            let testPayload = {
                "events": [
                    {
                        "type": "message",
                        "replyToken": "test-reply-token",
                        "source": {
                            "userId": "Uc5f2f40eebcbff5ad54e21a6f411a97e",
                            "type": "user"
                        },
                        "timestamp": new Date().getTime(),
                        "message": {
                            "id": "test-message-id",
                            "type": "text",
                            "text": "這是一個測試訊息"
                        }
                    }
                ]
            };

            $.ajax({
                url: "webhook",  // 你的 Webhook Servlet 路徑
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(testPayload),
                dataType: "json",
                success: function (response) {
                    console.log("Webhook 回應：", response);
                    alert("Webhook 測試成功：" + JSON.stringify(response));
                },
                error: function (xhr, status, error) {
                    console.error("Webhook 請求失敗：", error);
                    alert("Webhook 測試失敗：" + error);
                }
            });
        });
        // 綁定按鈕點擊事件
        $("#welcomeServletBtn").click(function () {
            // 使用jQuery的ajax方法發送請求
            $.getJSON("welcomeServlet",
                {
                    action: "ajaxGetMessage"
                })
                .done(function (response) {
                    if (response.success == "true") {
                        $("#welcomeMessage").text(response.message);
                    }
                    //$("#welcomeMessage").text(response);

                })
                .fail(function (error) {
                    $("#welcomeMessage").text("無法獲取歡迎訊息，請稍後再試！");
                });

        });
        $("#loadModalBtn").on("click", function () {
            let contentPath = "/pages/layout/modals/LineUserInfoEditor.html?_="+ new Date().getTime();
            $("#modalContainer").load(contentPath, function () {
                // 載入完畢後顯示 modal（如果有 bootstrap modal）
                $("#editModal").modal("show");
            });
        });
    });
    </script>
</head>
<body>
<h1>Welcome Page 1.04</h1>
<div id="welcomeMessage">請按下按鈕以獲取歡迎訊息</div>
<button id="welcomeServletBtn">獲取welcomeServlet訊息</button>
<button id="lineUserServletBtn">獲取lineUserServlet訊息</button>
<button id="userServletBtn">獲取userServlet訊息</button>
<button id="loadModalBtn" class="btn btn-primary">載入模組內容</button>

<!-- 放 modal 內容的容器 -->
<div id="modalContainer"></div>
</body>

</html>
