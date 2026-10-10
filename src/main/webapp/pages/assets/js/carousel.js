/**
 * Created by luckf on 2022/9/8.
 */
$(function () {
    var counter = 0;
    var randomIndex = 1000;

    var template1 = `<div class="carousel-item  {0}">` +
        `<img src="http:\\\\picsum.photos\\300\\200?random={1}"   class="img-fluid rounded-start upload-img" alt="...">` +
        `</div>`;
    var template2 = `<div class="carousel-item  {0}" noimage>` +
        `<img src="./image/noimage.jpg"   class="img-fluid rounded-start upload-img" alt="...">` +
        `</div>`;

    $("#add-carousel-page").click(function () {
        var appendHtml = "";
        var imageSrc = "";
        randomIndex++;

        //如果只有一張page 且 有noimage屬性,則將圖片至於此page,並且移除noimage屬性

        if ($(".carousel-inner .carousel-item").length == 1 && $(".carousel-inner .carousel-item")[0].hasAttribute("noimage")) {

            //1.移除掉noimage屬性
            $(".carousel-inner .carousel-item")[0].removeAttribute("noimage");
            //2.插入圖片
            imageSrc = String.format(`http:\\\\picsum.photos\\300\\200?random={0}`, randomIndex);
            $(".carousel-inner .carousel-item img")[0].setAttribute("src", imageSrc);
        } else {
            //正常狀況,直接加入page
            appendHtml = String.format(template1, "active", randomIndex);
            $(appendHtml).appendTo(".carousel-inner");
            counter++;
        }
    });
//            移除所有頁面,保留一個no image頁面
    $("#remove-carousel-page").click(function () {
        var appendHtml = "";
        $(".carousel-item").remove();

        appendHtml = String.format(template2, `active`);
        $(appendHtml).appendTo(".carousel-inner");
    });
});
