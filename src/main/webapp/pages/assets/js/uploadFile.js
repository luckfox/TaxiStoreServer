/**
 * Created by luckf on 2022/9/8.
 */
$(function ()
{
    const MaxFileLength=4;
    console.log("his part is using to load files and transfer to server.");
    //Browser files that we went to upload
    $(".browserFile").change(function ()
    {
        console.log("browserFile");
        var ParentRoot;
        var counter = 0;
        var template1 = `<div class="carousel-item  {0}">` +
            `<img src={1}   class="img-fluid rounded-start upload-img" alt="...">` +
            `</div>`;

        var files = this.files;
        ParentRoot=$(this).parent().parent();//where here is .Card
        //如果沒有選到任何檔案則結束
        if (files.length === 0) return;
        else if(files.length >MaxFileLength)
        {
            alert("檔案最多為四個!")
            $(ParentRoot.find(".browserFile")[0]).val("");
            return ;
        }
        console.log("file length=",files.length);
        //清空progress bar
        //移動到上兩層的父節點,再往下找.progress-bar

        var progressBar = ParentRoot.find(".progress-bar")[0];
        progressBar.style.width = "0%";

        //讀取第一個檔案當作preview
        const reader = new FileReader();
        reader.addEventListener("loadend", function ()
        {

            console.log("counter="+counter+"  file length="+files.length);
            console.log("finished files="+files[counter].name);
            counter++;
            if(counter<files.length) reader.readAsDataURL(files[counter]);
        });
        reader.addEventListener("load", function () {
            var appendHtml = "";
            if (counter === 0) {   //顯示為第一筆,所以在第一筆上加上active

                appendHtml = String.format(template1, `active`, this.result);
            } else appendHtml = String.format(template1, ``, this.result);

            $(appendHtml).appendTo(ParentRoot.find(".carousel-inner"));

        });
        //獎選取到的檔案丟到reader
        //step 1.移除所有carousel-item
        ParentRoot.find(".carousel-item").remove();
        //step 2.將選到的檔案讀入reader
        reader.readAsDataURL(files[counter]);


    });
    $(".clearFile2").click(function()
    {
        console.log(".clearFile click");
        var parentRoot=$(this).parent().parent();
        parentRoot.find(".browserFile").val(null);

        var progressBar =parentRoot.find(".progress-bar")[0];
        console.log(progressBar);
        progressBar.style.width = "0%";
        progressBar.innerHTML = "0 % complete";

    });
    //upload files which we went to select
    var parentRoot=null;
    $(".uploadFile").click(function ()
    {
        console.log(".uploadFile click");

        parentRoot=$(this).parent().parent();
        console.log(parentRoot);
        //1. 根據目前的Object,找到第一個.browserFile calls,並取得其files
        var files = parentRoot.find(".browserFile")[0].files;
        console.log("upload files count="+files.length);
        console.log(files);
        totalFileCount = files.length;               //上傳檔案的個數
        totalUploadedSize = 0;                       //已經上傳的資料總數
        totalFileSize = 0;                           //全部檔案資料的總數
        //compute total size of all files
        for (var i = 0; i < totalFileCount; i++) {
            totalFileSize += files[i].size;
            console.log(files[i].name);
        }
        console.log("totalFileSize=" + totalFileSize);
        fileUploadedCount = 0;

        if (fileUploadedCount < totalFileCount) Upload();

    });

    var totalFileSize, totalUploadedSize, totalFileCount, fileUploadedCount;

    function Upload()
    {

        var files = parentRoot.find(".browserFile")[0].files;
        console.log("fileUploadedCount="+fileUploadedCount);
        console.log(files);
        var file = files[fileUploadedCount];

        var xhr = new XMLHttpRequest();
        var fd = new FormData();
        fd.append("multipartFile", file);
        xhr.upload.addEventListener("progress", onUploadProgress, false);
        xhr.addEventListener("load", onUploadComplete, false);
        xhr.addEventListener("error", onUploadError, false);
        xhr.open("POST", "fileUploadServlet2");
        xhr.send(fd);


    }

    var onUploadProgress=function (e)
    {
        if (e.lengthComputable) {
            //目前讀取的進度
            var percentComplete = parseInt((e.loaded + totalUploadedSize) * 100 / totalFileSize);

            var progressBar =parentRoot.find(".progress-bar")[0];
            progressBar.style.width = percentComplete + "%";
            progressBar.innerHTML = percentComplete + " % complete";

            console.log(percentComplete + "% complete");
        } else {
            alert('unable to compute');
        }
    }

    function onUploadComplete(e) {

        totalUploadedSize += parentRoot.find(".browserFile")[0].files[fileUploadedCount].size;


        fileUploadedCount++;
        if (fileUploadedCount < totalFileCount) Upload();
        else {
            var progressBar =  parentRoot.find(".progress-bar")[0];

            progressBar.style.width = "100%";
            progressBar.innerHTML = "100% complete";
            //清空input file 的內容值
            $(parentRoot.find(".browserFile")[0]).val("");

        }
    }

    /* This function will call when an error come while uploading */
    function onUploadError(e) {
        alert("上傳資料發生錯誤!");
    }
});

