<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="../../common/head.jsp"%>
    <link href="/pages/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/pages/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div>
        <h3><span>流水編號:</span><span id="serialNoTitle">${requestScope.company.serialNo}</span></h3>
    </div>
    <div class="form-group col-md-6 mb-2">
        <label class="text-white" for="companyName">公司名稱 :*</label>
        <input type="text" class="form-control" placeholder="公司名稱" disabled="disabled" id="companyName" name="companyName" value="${requestScope.company.companyName}" required>
    </div>
    <div class="form-group col-md-4 mb-2">
        <label class="text-white" for="companySimpleName">公司簡稱 :*</label>
        <input type="text" class="form-control" placeholder="公司簡稱" disabled="disabled" id="companySimpleName" name="companySimpleName" value="${requestScope.company.companySimpleName}">
    </div>
    <span></span>
    <button type="button" id="previous-button" class="btn btn-warning">上一筆<i class="ft-file-plus position-right"></i></button>
    <button type="button" id="next-button" class="btn btn-warning">下一筆<i class="ft-file-plus position-right"></i></button>

    <button type="button" id="add-button" class="btn btn-warning">新增<i class="ft-file-plus position-right"></i></button>
    <button type="button" id="delete-button" class="btn btn-danger">刪除 <i class="ft-file-text position-right"></i></button>
    <button type="button" id="update-button" class="btn btn-secondary">更新 <i class="ft-refresh-cw position-right"></i></button>
    <button type="button" id="submit-button" class="btn btn-primary">送出 <i class="ft-thumbs-up position-right"></i></button>
    <br>
    <div>
        <h2>  </h2>
    </div>
    <div>
        <button type="button" id="attrib-button" class="btn btn-warning">attrib</button>

        <button type="button" id="property-button" class="btn btn-warning">property</button>

    </div>
</div>

<script type="text/javascript" src="/pages/assets/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/pages/assets/js/loadHTML.js"> </script>
<script type="text/javascript">
$(function()
{

    let append = false;
    let update = false;

    var tmp_serialNo;
    var tmp_companyName;
    var tmp_companySimpleName;

    $("#submit-button").attr("disabled","disabled");
    $("#previous-button").click(function()
    {
        console.log("previous-button click,serialNo="+ $("#serialNoTitle").text());
        $.getJSON(
            "<%=basePath%>companyServlet",
            "action=ajaxGetPreviousCompany&serialNo="+$("#serialNoTitle").text(),
            ajaxCallBack);

    });
    $("#next-button").click(function()
    {
        console.log("next-button click,serialNo="+ $("#serialNoTitle").text());
        $.getJSON(
            "<%=basePath%>companyServlet",
            "action=ajaxGetNextCompany&serialNo="+$("#serialNoTitle").text(),
            ajaxCallBack);

    });
    $("#update-button").click(function()
    {
        if(update===false)
        {   //進行update作業
            //step1.移除所有input 的disable
            $(".form-control").each(function ()
            {
                $(this).removeAttr("disabled");
            });
            //step2.禁能相關按鈕,致能submit-button
            $("#delete-button").attr("disabled", "disabled");
            $("#next-button").attr("disabled", "disabled");
            $("#previous-button").attr("disabled", "disabled");
            $("#add-button").attr("disabled", "disabled");
            $("#submit-button").removeAttr("disabled");
            //step3.保留目前資料於暫存區
            tmp_companyName = $("#companyName").val();
            console.log("save companyName=" + tmp_companyName);
            tmp_companySimpleName = $("#companySimpleName").val();
            //step4.更新==>復原
            $("#update-button").text("復原");
            update=true;
        }else
        {
            //更新作業取消
            //step1.增加input的disabled屬性,禁止欄位編輯
            $(".form-control").each(function()
            {
                $(this).attr("disabled","disabled");
            });
            //step2.致能其他按鈕
            $("#delete-button").removeAttr("disabled");
            $("#next-button").removeAttr("disabled");
            $("#previous-button").removeAttr("disabled");
            $("#add-button").removeAttr("disabled");
            $("#submit-button").attr("disabled","disabled");
            //step3.由暫存區取回資料
            //$("#companyName").attr("value",tmp_companyName);
            $("#companyName").val(tmp_companyName);
            console.log("restore companyName="+$("#companyName").val());
            //$("#companySimpleName").attr("value",tmp_companySimpleName);
            $("#companySimpleName").val(tmp_companySimpleName);
            //step3.新增==>取消
            $("#update-button").text("更新");
            update=false;
        }

    });
    //append record
    $("#add-button").click(function()
    {
        if(append===false)
        {

            //進行新增作業
            //step1.將所有.form-control的value清除,以及移除input的disabled屬性
            $(".form-control").each(function()
            {   console.log("#add-button step1");
                $(this).attr("value","");
                $(this).val("");
                $(this).removeAttr("disabled");
            });
            //step2.禁能其他按鈕
            $("#delete-button").attr("disabled", "disabled");
            $("#next-button").attr("disabled", "disabled");
            $("#previous-button").attr("disabled","disabled");
            $("#update-button").attr("disabled","disabled");
            $("#submit-button").removeAttr("disabled");
            //保存現有資料
            tmp_serialNo=$("#serialNoTitle").text();
            //step3.新增==>取消
            $("#add-button").text("取消");

            append=true;
        }else
        {
            //讀取保存紀錄serialNo
            $(".form-control").each(function()
            {
                $(this).attr("disabled","disabled");
            });
            //取消
            //step2.禁能其他按鈕
            $("#delete-button").removeAttr("disabled");
            $("#next-button").removeAttr("disabled");
            $("#previous-button").removeAttr("disabled");
            $("#update-button").removeAttr("disabled");
            //step3.根據serialNo去讀取資料
            //step4.新增==>取消
            $.getJSON(
            "<%=basePath%>companyServlet",
            "action=ajaxGetCompany&serialNo="+tmp_serialNo,
                ajaxCallBack);

            $("#add-button").text("新增");
            append=false;
        }
    });
    $("#delete-button").click(function()
    {
        if(true==confirm("確定刪除嗎?"))
        {
            console.log("delete record!");
            $.getJSON(
                "<%=basePath%>companyServlet",
                "action=ajaxDeleteCompany&serialNo=" +$("#serialNoTitle").text(),
                ajaxCallBack);
        }

    });
    $("#submit-button").click(function()
    {
        //step1.disable所有的input field
        $(".form-control").each(function()
        {
            $(this).attr("disabled","disabled");
        });
        //step2.致能其他按鈕,並且禁能sumbit-button

        $("#next-button").removeAttr("disabled");
        $("#previous-button").removeAttr("disabled");
        $("#update-button").removeAttr("disabled");
        $("#add-button").removeAttr("disabled");
        $("#delete-button").removeAttr("disabled");

        $("#submit-button").attr("disabled","disabled");
        //如果serialNo有值,則代表更新,否則為新增
        if(append==true)
        {
            $.getJSON(
                "<%=basePath%>companyServlet",
                "action=ajaxUpdateCompany&serialNo="+0+
                                        "&companyName="+ $("#companyName").val()+
                                        "&companySimpleName="+$("#companySimpleName").val(),
                ajaxCallBack);
            $("#add-button").text("新增");
            append=false;
        }
        if(update==true)
        {
            console.log("sumbit update="+$("#serialNoTitle").text());
            $.getJSON(
                "<%=basePath%>companyServlet",
                "action=ajaxUpdateCompany&serialNo="+$("#serialNoTitle").text()+
                                        "&companyName="+ $("#companyName").val()+
                                        "&companySimpleName="+$("#companySimpleName").val(),
                ajaxCallBack);
            $("#update-button").text("更新");
            update=false;
        }

    });
    var ajaxCallBack=function(jsonObj)
    {
        if (jsonObj != null)
        {
            console.log(jsonObj);
            $("#serialNoTitle").text(jsonObj.serialNo);
            //$("#companyName").attr("value", jsonObj.companyName);
            $("#companyName").val(jsonObj.companyName);
            //$("#companySimpleName").attr("value", jsonObj.companySimpleName);
            $("#companySimpleName").val(jsonObj.companySimpleName);
        }else
        {
            alert("原有資料已不存在!")
        }
    };
    $("#attrib-button").click(function()
    {
        $("#companyName").attr("value","12345");
    });
    $("#property-button").click(function()
    {
        $("#companyName").val("54321");
    });

});

</script>
</body>
</html>