<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
  <head>
      <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords" content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">
<%--    <script type="text/javascript" src="./assets/js/jquery-3.4.1.min.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <%@ include file="./common/head.jsp"%>
    <%@ include file="./common/front.jsp"%>

    <title>管理人員登錄</title>
    <script type="text/javascript">
    $(function()
    {
        $('#loginForm').on('submit', function (event) {
            // 阻止表单默认提交行为
            event.preventDefault();

            // 获取输入框中的值
            const userNo = $('#userNo').val();
            const password = $('#password').val();

            if (userNo && password)
            {
                // 发送数据到后端
                console.log(`userServlet;action=login,userNo=\${userNo},password=\${password}`);
                $.getJSON('userServlet', { action: 'login', userNo: userNo, password: password })
                    .done(function (result)
                    {
                        console.log(`Response:\${result}`);
                        // 处理后端返回的数据
                        if (result.success===true)
                        {
                            //alert('登录成功！');
                            window.location.href = "<%= request.getContextPath() %>/pages/login_success.jsp";
                            console.log("login success! href="+window.location.href);
                        } else
                        {
                            alert(`登录失败，错误信息: \${result.errorMessage}`);
                        }
                    })
                    .fail(function (jqXHR, textStatus, errorThrown)
                    {
                        console.error('Error during login:', textStatus, errorThrown);
                        alert('系统错误，请稍后再试！');
                    });
            } else
            {
                alert('请输入用户名和密码！');
            }
        });

        $("#userNo").on("blur", function ()
        {
            const userNo = $(this).val();
            if (userNo)
            {
                console.log("userServlet;action=getUserName,userNo="+userNo);
                $.getJSON("userServlet", { action: "ajaxGetUserName", userNo: userNo })
                    .done(function (response)
                    {
                        if(response.success===true)
                        {
                            console.log(response.data);
                            let data = response.data;
                            if (data && data.userName)
                            {
                                console.log("userName=" + data.userName);
                                $('#userName').val(data.userName);

                            } else {
                                $("#userName").text("N/A");
                            }
                        }
                    })
                    .fail(function (error)
                    {
                        console.error("Error fetching user name:", error);
                    });
            }
         });

    });
    function togglePassword(inputId, show) {
        const passwordInput = document.getElementById(inputId);
        if (passwordInput) {
            passwordInput.type = show ? 'text' : 'password';
        }
    }
    </script>

      <style>

          .password-toggle {
              border: none;
              background: transparent;
              cursor: pointer;
              padding: 0;
          }
          .password-toggle i {
              font-size: 1.2rem;
              color: #6c757d;
          }

      .bg-full-screen-image1
      {
          background:url("./app-assets/images/backgrounds/bg-17.jpg") center center no-repeat fixed;
          -webkit-background-size:cover;
          background-size:cover
      }
      body {
          background-color: lightblue;
      }
      </style>
  </head>
  <body class="vertical-layout vertical-menu-modern 1-column  bg-full-screen-image _menu-expanded blank-page _blank-page" data-open="click" data-menu="vertical-menu-modern" data-color="bg-gradient-x-purple-red" data-col="1-column">
    <!-- ////////////////////////////////////////////////////////////////////////////-->
    <div class="app-content content">
        <div class="content-wrapper">
            <div class="content-wrapper-before">
            </div>
            <div class="content-header row">
            </div>
            <div class="content-body">
                <section class="flexbox-container">
                    <div class="col-12 d-flex align-items-center justify-content-center">
                        <div class="col-md-4 col-10 box-shadow-2 p-0">
                            <div class="card border-grey border-lighten-3 px-1 py-1 m-0">
                                <div class="card-header border-0">
                                    <div class="text-center mb-1">
                                        <img src="<%= request.getContextPath() %>/pages/app-assets/images/logo/logo.png" alt="branding logo">
                                    </div>
                                    <div class="font-large-1  text-center">
                                        管理人員登錄
                                    </div>
                                </div>
                                <div class="card-content">
                                <!--start of card-body-->
                                <div class="card-body">
                                    <form id="loginForm" class="form-horizontal" action="/userServlet" method="post" novalidate>
                                        <input type=hidden name="action" value="login"/>
                                        <fieldset class="form-group position-relative has-icon-left">
                                            <input type="text" class="form-control round" id="userNo" name="userNo" placeholder=" 2" required>
                                            <div class="form-control-position">
                                                <i class="ft-user"></i>
                                            </div>
                                        </fieldset>
                                        <fieldset class="form-group position-relative has-icon-left">
<%--                                            <label id="userName" class="form-label">User Name: N/A</label>--%>

                                            <input type="text" class="form-control round" disabled="disabled" id="userName" placeholder="Your alias">

                                            <div class="form-control-position">
                                                <i class="ft-user"></i>
                                            </div>
                                        </fieldset>
                                        <fieldset class="form-group position-relative has-icon-left">
                                            <input type="password" class="form-control round" id="password" name="password" placeholder="輸入密碼" required>
                                            <div class="form-control-position">
                                                <i class="ft-lock"></i>
                                            </div>
                                            <button type="button" class="btn btn-light password-toggle"
                                                    onmousedown="togglePassword('password', true)"
                                                    onmouseup="togglePassword('password', false)"
                                                    onmouseleave="togglePassword('password', false)"
                                                    style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%);">
                                                <i class="ft-eye"></i>
                                            </button>
                                        </fieldset>
                                         <%--<div class="form-group row">--%>
                                            <%--<div class="col-md-6 col-12 text-center text-sm-left">--%>
                                            <%--</div>--%>
                                            <%--<div class="col-md-6 col-12 float-sm-left text-center text-sm-right"><a href="recover-password.html" class="card-link">Forgot Password?</a>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="form-group text-center">
                                            <button type="submit" class="btn round btn-block btn-glow btn-bg-gradient-x-purple-blue col-12 mr-1 mb-1">登錄</button>
                                        </div>
                                    </form>
                                </div>
                                <!--end of card-body-->
<%--                                <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2 my-2 "><span>OR Sign Up Using</span></p>--%>
<%--                                <div class="text-center">--%>
<%--                                    <a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-facebook"><span class="ft-facebook"></span></a>--%>
<%--                                    <a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-twitter"><span class="ft-twitter"></span></a>--%>
<%--                                    <a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-instagram"><span class="ft-instagram"></span></a>--%>
<%--                                </div>--%>
                                    <p class="card-subtitle line-on-side text-muted text-center mx-2 my-2 ">
                                        <span id="errorMsg" class="errorMsg">12345${requestScope.message}</span>
                                    </p>
                                <%--<p class=" card-subtitle text-muted text-right mx-2 my-1" ><span style="font-size:25">新增帳號 <a href="/pages/register.jsp" class="card-link">註冊</a></span></p>--%>
                                    <h3 class=" card-subtitle text-muted text-right mx-2 my-1"><span>新增帳號 <a href="/pages/register.jsp" class="card-link">註冊</a></span></h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <%@ include file="./common/footer.jsp"%>
  </body>
</html>