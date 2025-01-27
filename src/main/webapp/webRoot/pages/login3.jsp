<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <link rel="stylesheet" href="styles/login.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function () {
      console.log("jQuery ready");

      // Handle form submission
      $("#loginForm").on("submit", function (event) {
        event.preventDefault(); // Prevent default form submission
        const userNo = $("#userNo").val();
        const password = $("#password").val();

        $.getJSON("userServlet", { action: "login", userNo: userNo, password: password })
                .done(function (data) {
                  if (data.success=="true")
                  {
                    window.location.href = "<%= request.getContextPath() %>/webRoot/pages/login_success.jsp";
                  } else
                  {
                    alert("Login failed: " + data.message);
                  }
                })
                .fail(function (error) {
                  console.error("Error during login:", error);
                });
      });

      // Handle language switching
      $("#langSwitchEn").on("click", function () {
        switchLanguage("en");
      });

      $("#langSwitchZh").on("click", function () {
        switchLanguage("zh");
      });

      function switchLanguage(lang) {
        $.getJSON("config", { action: "getLanguage", language: lang })
                .done(function (data) {
                  $("#loginTitle").text(data["login.title"]);
                  $("#userNoLabel").text(data["login.username"]);
                  $("#passwordLabel").text(data["login.password"]);
                  $("#submitButton").text(data["login.submit"]);
                })
                .fail(function (error) {
                  console.error("Error switching language:", error);
                });
      }
    });
  </script>
</head>
<body>
<div class="login-container">
  <h1 id="loginTitle" class="text-center mb-4">Login Page</h1>
  <form id="loginForm">
    <div class="mb-3">
      <label id="userNoLabel" for="userNo" class="form-label">User No:</label>
      <input type="text" id="userNo" name="userNo" class="form-control" required>
    </div>
    <div class="mb-3">
      <label id="userNameLabel" class="form-label">User Name: N/A</label>
    </div>
    <div class="mb-3">
      <label id="passwordLabel" for="password" class="form-label">Password:</label>
      <input type="password" id="password" name="password" class="form-control" required>
    </div>
    <button id="submitButton" type="submit" class="btn btn-dark w-100">Submit</button>
  </form>
  <div class="mt-3 text-center">
    <button id="langSwitchEn" class="btn btn-sm btn-dark">English</button>
    <button id="langSwitchZh" class="btn btn-sm btn-dark">中文</button>
  </div>
</div>
</body>
</html>
