<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords" content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">

      <%@ include file="./common/head.jsp"%>

    <title>Register with Background Color - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
    <link rel="apple-touch-icon" href="/pages/app-assets/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="/pages/app-assets/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700" rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
    <link herf="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/vendors.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON  CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/app.css">
    <!-- END CHAMELEON  CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="/pages/app-assets/css/pages/login-register.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="/pages/assets/css/style.css">
    <!-- END Custom CSS-->
    <script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
    <style>
        input[type="file"]
        {
            display:none;
        }
        label
        {
            color:white;
            height:30px;
            width:180px;
            background-color:#f5af09;
            /*position:absolute;*/
            margin:auto;
            top:0;
            bottom:0;
            left:0;
            right:0;
            font-size:14px;
            display:flex;
            justify-content:center;
            align-items:center;
            font-family:"Montserrat",sans-serif;
        }
        #header-preview
        {
            border-radius: 5px;
            padding: 10px;
            width:180px;
            height:180px;
            border:solid;
            border-style: dashed;
        }

    </style>
   <script type="text/javascript">
   $(function()
   {
       //Browser files that we went to upload
       $("#Photofile").change(function ()
       {
           var files = this.files;

            console.log(files);
           //讀取第一個檔案當作preview
           const reader = new FileReader();
           reader.addEventListener("loadend", function ()
           {
           });
           reader.addEventListener("load", function ()
           {
               $("#header-preview")[0].setAttribute("src",this.result);
           });
           //step 2.將選到的檔案讀入reader
           reader.readAsDataURL(files[0]);
       });


       $("#user-name").blur(function ()
       {

           //1.獲取用戶名稱
           var username=this.value;

           if(username==="") return;
           //2.以getJSON形式取得回傳資料,目前userServlet無法攔截,待查
           $.getJSON(
               "http://localhost:8080/userServlet",
               "action=ajaxExistsUsername&username="+username,
               function (data)
               {
                   if(data.existsUsername)
                   {
                       console.log("account["+username+"] be created!");
                       $("#errorMsg").text("用戶名已經存在");
                   }else
                   {
                       console.log("account["+username+"] not found!");
                       $("#errorMsg").text("用戶名可用");

                   }
                   console.log(data);
               });
               //var $ObjFunction = function ()
               $("#submit").click(function()
               {
                   var usernamePatt=/^\w{5,12}$/;
                   var passwordPatt=/^\w{5,12}$/;
                   var emailPatt=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

                   //註冊動作
                   //1.註冊帳號:必須由英文和數字或底線的組合,長度必須在5~12字母
                   //alert($("#user-name").val());
                   var name=$("#user-name").val();
                   var password=$("#user-password").val();
                   var repassword=$("#user-repassword").val();
                   var email=$("#user-email").val();
                   if(!usernamePatt.test(name))
                   {
                       $("#errorMsg").text("帳號格式不正確");
                       return false;
                   }
                   //2.用戶密碼:必須由英文和數字或底線的組合,長度必須在5~12字母
                   if(!passwordPatt.test(password))
                   {
                       $("#errorMsg").text("密碼格式不正確");
                       return false;
                   }
                   //3.確認密碼:必須要和用戶密碼一致
                   if(password!=repassword)
                   {
                       $("#errorMsg").text("密碼不一致");
                       return false;
                   }
                   //4.電子郵件:符合郵件的規則
                   if(!emailPatt.test(email))
                   {
                       $("#errorMsg").text("email格式不正確");
                       return false;
                   }

                   $("#errorMsg").text("");
                   return true;
               });
       });


   });

   </script>

  </head>
  <body class="vertical-layout vertical-menu-modern 1-column  bg-full-screen-image menu-expanded blank-page blank-page" data-open="click" data-menu="vertical-menu-modern" data-color="bg-gradient-x-purple-red" data-col="1-column">
    <!-- ////////////////////////////////////////////////////////////////////////////-->
    <div class="app-content content">
      <div class="content-wrapper">
        <div class="content-wrapper-before"></div>
        <div class="content-header row">
        </div>
        <div class="content-body"><section class="flexbox-container">
    <div class="col-12 d-flex align-items-center justify-content-center">
        <div class="col-md-4 col-10 box-shadow-2 p-0">
            <div class="card border-grey border-lighten-3 px-1 py-1 m-0">
                <div class="card-header border-0">
                    <div class="text-center mb-6" >
                        <div>
                            <img id="header-preview" src="/pages/app-assets/images/logo/logo.png"  alt="branding logo">
                        </div>
                        <input type="file" id="Photofile" accept="image/*">
                        <label for="Photofile"><i class="material-icons">add_photo_alternate</i>photo</label>

                    </div>
                    <div class="tit font-large-1 text-center">
                        <h1>管理人員註冊</h1>
                    </div>
                    <!--<div class="font-large-1  text-center">-->
                        <!--註冊管理人員 <span id="errorMsg" class="errorMsg">${requestScope.message}</span>-->
                    <!--</div>-->
                </div>
                <div class="card-content">

                    <div class="card-body">
                        <form class="form-horizontal" action="/userServlet" method="post" novalidate>
                            <input type=hidden name="action" value="register"/>
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="text" class="form-control round" id="user-name" name="username" placeholder="管理員帳號" required>
                                <div class="form-control-position">
                                    <i class="ft-user"></i>
                                </div>
                            </fieldset>
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="email" class="form-control round" id="user-email" name="email" placeholder="Email 位址" required>
                                <div class="form-control-position">
                                    <i class="ft-mail"></i>
                                </div>
                            </fieldset>
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="password" class="form-control round" id="user-password" name="password" placeholder="輸入密碼" required>
                                <div class="form-control-position">
                                    <i class="ft-lock"></i>
                                </div>
                            </fieldset>
                            <fieldset class="form-group position-relative has-icon-left">
                                <input type="password" class="form-control round" id="user-repassword" placeholder="確認密碼" required>
                                <div class="form-control-position">
                                    <i class="ft-check-circle"></i>
                                </div>
                            </fieldset>
                            <div class="form-group text-center">
                                <button id="submit" type="submit" class="btn round btn-block btn-glow btn-bg-gradient-x-purple-blue col-12 mr-1 mb-1">Register</button>
                            </div>

                        </form>
                    </div>
                    <p class="card-subtitle line-on-side text-muted text-center mx-2 my-2 ">
                        <span id="errorMsg" class="errorMsg">${requestScope.message}</span>
                    </p>
                    <!--<div class="text-center">-->
                        <!--<a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-facebook">-->
                            <!--<span class="ft-facebook"></span>-->
                        <!--</a>-->
                        <!--<a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-twitter">-->
                            <!--<span class="ft-twitter"></span>-->
                        <!--</a>-->
                        <!--<a href="#" class="btn btn-social-icon round mr-1 mb-1 btn-instagram">-->
                            <!--<span class="ft-instagram"></span>-->
                        <!--</a>-->
                    <!--</div>-->

                    <p class="card-subtitle text-muted text-right font-small-3 mx-2 my-1">
                        <span>Already a member ?
                            <a href="login.jsp" class="card-link">Sign In</a>
                        </span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>
        </div>
      </div>
    </div>
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!-- BEGIN VENDOR JS-->
    <script src="/pages/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="/pages/app-assets/vendors/js/forms/validation/jqBootstrapValidation.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
    <script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <script src="/pages/app-assets/js/scripts/forms/form-login-register.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS-->
  </body>
</html>