<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/10/14
  Time: 下午6:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../common/head.jsp"%>
    <link href="/pages/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/pages/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <title>Title</title>

</head>
<body>
<div class="container">
    <div class="form-group col-md-6 mb-2">
        <label class="text-black" for="email"><span class="label-contain">email :*</span><span class="notice" hidden>格式不正確</span></label>
        <input type="text" class="form-control"   id="email" name="email" placeholder="abc@company.com" value="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required >
    </div>
    <div class="form-group col-md-6 mb-2">
        <label class="text-black" for="email2"><span class="label-contain">email :*</span><span class="notice" hidden>格式不正確</span></label>
        <input type="text" class="form-control"   id="email2" name="email2" value="" pattern="[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$" required >
    </div>
    <div class="form-group col-md-6 mb-2">
        <label class="text-black" for="phone"><span class="label-contain">phone :*</span><span class="notice" hidden>格式不正確</span></label>
        <input type="text" class="form-control"   id="phone" name="phone" placeholder="09XX-XXX-XXX" value="" pattern="09\d{2}\-?\d{3}\-?\d{3}$" required >
    </div>
    <div class="form-group col-md-6 mb-2">
        <label class="text-black" for="rule1"><span class="label-contain">條款 :*</span><span class="notice" hidden>格式不符合 </span></label>
        <input type="text" class="form-control"   id="rule1" maxlength="5" name="rule1" value="" pattern="[0-9]{1,2}\-?[0-9]{1}$" required >
    </div>



    <div class="form-actions">
        <div class="text-center">
            <button type="button" id="previous-button" class="btn btn-warning">上一筆<i class="ft-file-plus position-right"></i></button>
            <button type="button" id="next-button" class="btn btn-warning">下一筆<i class="ft-file-plus position-right"></i></button>

            <button type="button" id="add-button" class="btn btn-warning">新增<i class="ft-file-plus position-right"></i></button>
            <button type="button" id="delete-button" class="btn btn-danger">刪除 <i class="ft-file-text position-right"></i></button>
            <button type="button" id="update-button" class="btn btn-secondary">更新 <i class="ft-refresh-cw position-right"></i></button>
            <button type="button" id="submit-button" class="btn btn-primary">送出 <i class="ft-thumbs-up position-right"></i></button>
        </div>
    </div>
</div>

<script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/pages/assets/js/loadHTML.js"> </script>
<script type="text/javascript">
    $(function()
    {
        $("#previous-button").click(function()
        {
            var rule1=$("#email").attr("pattern");
            var regex2=new RegExp($("#rule1").attr("pattern"));
            var regex=new RegExp(/^[0-9]{1,2}\-?[0-9]{1}$/);
            console.log("regex="+regex);
            console.log("regex2="+regex2);
        });

        console.log("JQuery start!");
        $("#submit-button").click(function()
        {
            $("input[pattern]").each(function()
            {
                var value=$(this).val();
                var pattern=$(this).attr("pattern");
                var regex=new RegExp(pattern);
                if(!regex.test(value))
                {
                    $(this).parent().find("span").eq(1).removeAttr("hidden");
                   //console.log("error in "+ $(this).parent().find("label").text());
                    console.log($(this).parent().find(".label-contain").text()+"submit incorrect !")
                }else
                {
                    $(this).parent().find("span").eq(1).attr("hidden","");
                    console.log($(this).parent().find(".label-contain").text()+"submit correct !")
                }
            });

        });
        $("input[pattern]").change(function()
        {
            var value=$(this).val();
            var pattern=$(this).attr("pattern");
            var regex = new RegExp(pattern);
            if(!regex.test(value))
            {
                $(this).parent().find("span").eq(1).removeAttr("hidden");
                //console.log("欄位 [ "+$(this).parent().find("label")+"] 格式不正確");
                console.log($(this).parent().find(".label-contain").text()+" change incorrect !")
            }else
            {
                $(this).parent().find("span").eq(1).attr("hidden","");
                console.log($(this).parent().find(".label-contain").text()+" change correct !")
            }
        });

    });
</script>
</body>
</html>
