<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/10/7
  Time: 下午12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../common/head.jsp"%>
    <link href="<%= assetsPath %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%= assetsPath %>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%= assetsPath %>/bootstrap/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
    <h1>Hello!</h1>
    <div class="input-group col-md-4 mb-1">
        <label class="input-group-text ">*特別碼*</label>
        <input type="text" class="form-control update-enabled" placeholder="特別碼"
               id="specialcode" name="specialcode" aria-describedby="basic-addon1"
               value="${requestScope.company.specialcode}" required disabled="disabled">
        <input type="text" class="" placeholder="特別碼"
               id="specialcodeSearch" name="specialcodeSearch" aria-describedby="basic-addon1">
        <!-- List container for search results -->

    </div>
    <div class="mb-2 col-lg-3">
        <label class="form-label" for="email">email :*</label>
        <input type="text" class="form-control"   id="email" name="email" value="" pattern="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$" required >
    </div>
    <div class="mb-2 col-lg-3">
        <label class="form-label" for="phone">phone :*</label>
        <input type="text" class="form-control"   id="phone" name="phone" value="" pattern="[0-9]{3}" required >
    </div>

    <div class="mb-3 col-lg-3">
        <label for="exampleFormControlInput1" class="form-label">Email address</label>
        <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
    </div>
    <div class="mb-3 col-lg-3">
        <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
    </div>
    <div class="mb-3 col-lg-3">
        <label for="exampleFormDate" class="form-label">Example textarea</label>
        <input type="date" class="form-control" id="exampleFormDate" />
    </div>
    <div class="mb-3 col-lg-3">
        <label class="form-label">company</label>
        <input class="form-control" autocomplete="off" id="companySerch" />
    </div>
    <div class="mb-3 col-lg-3 position-relative">
        <label for="specialCode" class="form-label">Company</label>
        <input type="text" class="form-control dropdown-toggle" id="specialCodeInput" data-bs-toggle="dropdown" autocomplete="off" />
        <ul class="dropdown-menu w-100" id="autocomplete-list">
            <!-- Suggestions will be dynamically inserted here -->
        </ul>
    </div>
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-primary" id="btn1" >Left</button>
        <button type="button" class="btn btn-primary" id="btn2">Middle</button>
        <button type="button" class="btn btn-primary" id="btn3">Right</button>
    </div>

    <script type="text/javascript" src="<%= assetsPath %>/js/custom.js"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"> </script>
    <script type="text/javascript">
    $(function()
    {

        const $dropdown = $('#autocomplete-list');
        let listWindow;
        $("#btn1").click(function()
        {
                console.log("Date="+$("#exampleFormDate").val());
        });
        $('#specialcodeSearch').on("keydown", function (event)
        {
            //
            //
            if (event.key !== "Enter") return;
            event.preventDefault(); // 阻止默认行为
            let scSearch = $(this).val().trim();

            // 檢查是否為空值
            if (!scSearch)
            {
                return; // 不處理空值
            }

            const offset = $(this).offset();
            const inputHeight = $(this).outerHeight();
            const inputWidth = $(this).outerWidth();

            // 动态创stWindow) {建 listWindow
            if(!listWindow) {
                listWindow = $("<div>", {class: "list-window list-group"}).appendTo("body");
            }

            // listWindow.css({
            //     top: offset.top + inputHeight + "px",
            //     left: offset.left + "px",
            //     width: inputWidth + "px"
            // }).empty().hide(); // 清空并隐藏
            listWindow.css({
                top: offset.top +"px",
                left: offset.left + "px",
                width: inputWidth + "px"
            }).empty().hide(); // 清空并隐藏

            $.getJSON("companyServlet", {
                action: "ajaxGetItemBySpecialCode",
                SpecialCode: scSearch
            })
                .done(function (result) {
                    if (result.success)
                    {
                        const companies = result.data;
                        console.log(companies);
                        if (companies.length === 1) {
                            // 若只有一個值，直接處理
                            companyData(companies[0]);
                        } else if (companies.length > 1) {

                            companies.forEach(function (item)
                            {
                                $("<a>", {
                                    class: "list-group-item list-group-item-action",
                                    text: item.companyname,
                                    href: "#",
                                }).data("company", item)
                                    .appendTo(listWindow)
                                    .on("click", function (e)
                                    {
                                        e.preventDefault();
                                        const selectedData = $(this).data("company");
                                        ajaxCallBack(selectedData); // 回调处理选中数据
                                        listWindow.hide(); // 隐藏 listWindow
                                    });
                            });
                            listWindow.show(); // 显示 listWindow
                            // 若有多個值，顯示列表
                            //showListWindow(companies);
                        }
                    } else {
                        console.error("Error fetching companies:", response.message || "Unknown error");
                    }
                })
                .fail(function (xhr, status, error) {
                    console.error("AJAX request failed:", error);
                });


        });
        function showListWindow(companies)
        {
            let $rc=$('.listWindow');
            $rc.empty().show(); // 清空並顯示結果容器
            console.log("showListWindow");
            companies.forEach(function (company)
            {
                console.log(company);
                const $item = $("<div>")
                    .text(company.companyname) // 假設 CompanyDTO 有 `name` 屬性
                    .css({
                        padding: "5px",
                        cursor: "pointer"
                    })
                    //針對list內的每個Item項目設置onClick事件。
                    .on("click", function ()
                    {
                        companyData(company);
                        $rc.hide(); // 隱藏結果容器
                    });

                $rc.append($item);
            });

            // 讓結果容器出現在輸入框下方
            // const $inputField = $("#specialcodeSearch");
            // const offset = $inputField.offset();
            // $rc.css({
            //       position: 'absolute',
            //      top: offset.top + $inputField.outerHeight(),
            //     left: offset.left,
            //     width: $inputField.outerWidth()
            //  });
        }
        $(document).on("click.listWindow", function (e) {
            if (!$(e.target).closest(".listWindow").length && !$(e.target).is("#specialcodeSearch")) {
                hideListWindow();
            }
        });
        function hideListWindow() {
            $listWindow.hide();
            $(document).off(".listWindow"); // 移除事件绑定
        }

        $("#specialCodeInput").on('input', function ()
        {

            const specialCode = $(this).val().trim();

            console.log("specialCode="+specialCode);
            if (specialCode.length > 0) {
                // 發送請求到後端
                $.getJSON('companyServlet', {
                    action: 'ajaxGetItemBySpecialCode',
                    SpecialCode: specialCode
                }, function (result) {
                    // 處理後端返回的資料
                    if (result.success)
                    {
                        const companies = result.data;
                        console.log("item count="+companies.length);
                        let items = '';
                        companies.forEach(item =>
                        {
                            console.log("item="+item.companyname);
                            items += `<li><button type="button" class="dropdown-item">`+item.companyname+`</button></li>`;
                        });
                        $dropdown.html(items).addClass('show');
                    } else {
                        $dropdown.removeClass('show');
                    }
                });
            } else {
                $dropdown.removeClass('show');

            }
        });
        // 點擊選項填充輸入框
        $dropdown.on('click', '.dropdown-item', function ()
        {
            $("#specialCodeInput").val($(this).text());
            $dropdown.removeClass('show');
        });

        // 點擊其他地方隱藏下拉
        $(document).on('click', function (e) {
            if (!$(e.target).closest($("#specialCodeInput")).length && !$(e.target).closest($dropdown).length) {
                $dropdown.removeClass('show');
            }
        });


    });
    </script>
</body>
</html>
