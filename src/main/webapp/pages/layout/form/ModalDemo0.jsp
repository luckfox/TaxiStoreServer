  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="loading" lang="zh-tw" data-textdirection="ltr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Chameleon Admin is a modern Bootstrap 4 webapp &amp; admin dashboard html template with a large number of components, elegant design, clean and organized code.">
    <meta name="keywords" content="admin template, Chameleon admin template, dashboard template, gradient admin template, responsive admin template, webapp, eCommerce dashboard, analytic dashboard">
    <meta name="author" content="ThemeSelect">

    <%@ include file="../../common/head.jsp"%>

    <title>Dashboard eCommerce - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%= app_assetsPath %>/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700" rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
<%--    <!-- BEGIN VENDOR CSS-->--%>
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/vendors.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist-plugin-tooltip.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/file-uploaders/dropzone.min.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/forms/icheck/icheck.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/forms/icheck/custom.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/timeline/vertical-timeline.css">
<%--    <!-- END VENDOR CSS-->--%>
<%--    <!-- BEGIN CHAMELEON  CSS-->--%>
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/app.css">
<%--    <!-- END CHAMELEON  CSS-->--%>
<%--    <!-- BEGIN Page Level CSS-->--%>
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/menu/menu-types/vertical-menu-modern.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/fonts/simple-line-icons/style.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">
<%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/timeline.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/plugins/file-uploaders/dropzone.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/dashboard-ecommerce.css">--%>
<%--    <!-- END Page Level CSS-->--%>
<%--    <!-- BEGIN Custom CSS-->--%>
      <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css?<%= version %>">
      <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/custom.css?<%= version %>">
    <!-- END Custom CSS-->


  </head>
  <body class="bg-dark vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu-modern" data-color="bg-chartbg" data-col="2-columns">

    <!--//start of navBar-->
    <%@ include file="../../topNavBar.jsp" %>.
    <!--//end of navBar-->
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!--//start of left-side menu-->
    <%@ include file="../../leftSideMenu.jsp" %>.
    <!--//end of left-side menu-->
    <!--//start of body-->

    <div >
        <input type="hidden" id="serialID" value="12">
    </div>
    <div class="container mt-2">
        <button id="btnA" class="btn btn-primary">Line通訊錄</button>
        <button id="btnB" class="btn btn-success">掛牌車輛訊息</button>
        <button id="btnC" class="btn btn-danger">車輛訊息</button>
        <button id="btnD" class="btn btn-secondary">駕駛員訊息</button>
        <button id="btnE" class="btn btn-dark">公司訊息</button>
        <button id="btnF" class="btn btn-grey-blue">Line訊息發送</button>
        <button id="btnG" class="btn btn-grey-blue">autoAlert</button>
    </div>
    <div id="modalContainer"></div>
    <!--//end of body-->
    <!--//start of footer menu-->
    <%@ include file="../../footer.jsp" %>.
    <!--//end of footer menu-->

    <!-- BEGIN VENDOR JS-->
    <script src="<%= app_assetsPath %>/vendors/js/vendors.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="<%= app_assetsPath %>/vendors/js/charts/chartist.min.js" type="text/javascript"></script>
    <script src="/pages/app-assets/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>
    <script src="/pages/app-assets/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>
    <script src="/pages/app-assets/vendors/js/extensions/dropzone.min.js" type="text/javascript"></script>
    <script src="/pages/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="<%= app_assetsPath %>/js/core/app-menu.js" type="text/javascript"></script>
    <script src="<%= app_assetsPath %>/js/core/app.js" type="text/javascript"></script>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <%--<script src="<%= app_assetsPath %>/js/scripts/pages/dashboard-ecommerce.js" type="text/javascript"></script>--%>
    <!-- END PAGE LEVEL JS-->
    <!-- BEGIN CUSTOM LEVEL JS-->
    <script type="text/javascript" src="<%= assetsPath %>/js/alert/autoAlert.js?<%= version %>"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/dropdown_mapping.js?<%= version %>"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/dropdown_handler.js?<%= version %>"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/DynamicModalEditor.js?<%= version %>"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/DynamicModalHelper.js?<%= version %>"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/custom.js?<%= version %>"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"> </script>
    <script type="text/javascript">

       $(function()
       {
           console.log("ModalDemo.jsp");
           const alertBox=new AutoAlert();
           alertBox.show("hello!");
           $("#dashboard").attr("class","open");
           console.log("set #dashboard class=open");
           $("#dashboard-analytics").attr("class","active");
           const $modalContainer=$("#modalContainer");
           const $serialID=$("#serialID");

           const htmlPosition = "${applicationScope.config.get('site.name')}";
           console.log(htmlPosition);
           // 新增按鈕事件
           $("#btnA").click(function () {

               const editor=lineUserEditorHandler(this,$serialID.val());
               editor.open();
           });

           $("#btnB").click(function () {
                const editor=SituationEditorHandler(this,"1024");
                editor.open();
           });

           $("#btnC").click(function () {
               const editor=CarInfoEditorHandler(this,"1585");
               editor.open();
           });
           $("#btnD").click(function () {
               const editor=DriverEditorHandler(this,"1585");
               editor.open();

           });
           $("#btnE").click(function () {
               const editor=CompanyEditorHandler(this,"10");
               editor.open();
           });
           $("#btnF").click(function () {
               const editor=LineMessageHandler(this,$serialID.val());
               editor.open();
           });
           $("#btnG").click(function () {
               alertBox.show("hello!",3);
           });
           function stripPrefix(id, prefix) {
               if (!id) return null;
               return id.startsWith(prefix) ? id.substring(prefix.length) : id;
           }
           function LineMessageHandler($btn,id)
           {

               const initUrl = `lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=\${id}`;
               const modalPath=${applicationScope.config.get('modal.lineMessageEditor.html.path')}+"?_="+ new Date().getTime();
               //const modalPath="/pages/layout/modals/LineUserInfoEditor.html?_="+ new Date().getTime();
               const editor= new DynamicModalEditor($btn, $modalContainer,modalPath,
                   {
                       getOpenRequest: () => (
                           {
                               url: initUrl
                           }
                       ),
                       onLoaded:()=>{

                       },
                       onBtnClick:(btnInfo)=>{
                           console.log(`btn.id=\${btnInfo.id}`);

                           const cleanId = stripPrefix(btnInfo.id, editor.modalPrefix);

                           console.log(`btn.id (with prefix) = \${btnInfo.id}`);
                           console.log(`btn.id (clean)       = \${cleanId}`);

                           // 判斷是哪個按鈕
                           switch (cleanId) {
                               case "syncBtn":
                                   console.log("👉 使用者按了【同步】按鈕");
                                   break;
                               case "clearBtn":
                                   console.log("👉 使用者按了【關閉】按鈕");
                                   break;
                               case "uploadBtn":
                                   console.log("👉 使用者按了【關閉】按鈕");
                                   break;

                               default:
                                   console.log(`🤔 未知的按鈕: ${cleanId}`);
                           }
                       }
                   });
               return editor;
           }
           function CarInfoEditorHandler($btn,id)
           {
               const initUrl = `carServlet?action=ajaxGetCarDTO&serialNo=\${id}`;
               const modalPath=${applicationScope.config.get('modal.car.html.path')}+"?_="+ new Date().getTime();
               return new DynamicModalEditor($btn, $modalContainer,modalPath,
                   {
                       getOpenRequest: () => (
                           {
                               url: initUrl
                           }
                       ),
                   });
           }
           function SituationEditorHandler($btn,id)
           {
                const initUrl = `situationServlet?action=ajaxGetSituation&serialNo=\${id}`;
                const modalPath="/pages/layout/modals/SituationEditor.html?_="+ new Date().getTime();
                return new DynamicModalEditor($btn, $modalContainer,modalPath,
                    {
                        getOpenRequest: () => (
                            {
                                url: initUrl
                            }
                        ),
                    });
           }
           function DriverEditorHandler($btn,id)
           {
               const initUrl = `driverServlet?action=ajaxGetDriverDTO&serialNo=\${id}`;
               const modalPath=${applicationScope.config.get('modal.driver.html.path')}+"?_="+ new Date().getTime();
               return new DynamicModalEditor($btn, $modalContainer,modalPath,
                   {
                       getOpenRequest: () => (
                           {
                               url: initUrl
                           }
                       ),
                   });
           }
           function CompanyEditorHandler($btn,id)
           {
               const initUrl = `companyServlet?action=ajaxGetCompany&serialNo=\${id}`;
               const modalPath=${applicationScope.config.get('modal.company.html.path')}+"?_="+ new Date().getTime();
               return new DynamicModalEditor($btn, $modalContainer,modalPath,
                   {
                       getOpenRequest: () => (
                           {
                               url: initUrl
                           }
                       ),
                   });
           }
           function lineUserEditorHandler($btn,id)
           {
               const initUrl = `lineUserServlet?action=ajaxGetUserWithDriverBySerialId&serialId=\${id}`;

               const modalPath="/pages/layout/modals/LineUserInfoEditor.html?_="+ new Date().getTime();
               return new DynamicModalEditor($btn, $modalContainer,modalPath,
                   {
                       getOpenRequest: () => (
                           {
                               url: initUrl
                           }
                       ),
                       onSuccess: (msg) => {
                           console.log("✅ 成功啦！", msg);
                           alert("資料更新成功！");
                       },
                       onUpdate: (formData) => ({
                           url: `lineUserServlet?action=ajaxUpdateByUserId&userId=\${formData.userId}`
                       }),
                       onUpdateTableRow: (formData) => {
                           // const table = $('#VI_table').DataTable();
                           // const row = table.rows().nodes().to$().filter(function () {
                           //     return $(this).data('user-id') == formData.userId;
                           // });
                           //
                           // if (row.length) {
                           //     const rowIndex = table.row(row).index();
                           //     const rowData = table.row(rowIndex).data();
                           //
                           //     // 自訂更新欄位
                           //     rowData.driverName = formData.driverName;
                           //     rowData.tag = formData.tag;
                           //
                           //     table.row(rowIndex).data(rowData).invalidate().draw(false);
                           // }
                       },
                       onBindDropdown: (helper, prefix) => {
                           const $root = helper.getRoot();

                           const $input_driverSC = $("#"+findFullElementId($root, "driverSC"));
                           const $input_driverId = $("#"+findFullElementId($root, "driverId"));
                           const $dropdown_driverId = $("#"+findFullElementId($root, "autocomplete-list-driverId"));
                           const $dropdown_driverSC = $("#"+findFullElementId($root, "autocomplete-list-driverSC"));

                           const root_id="#"+$root.attr(`id`);
                           console.log(`root id=\${root_id}`);
                           const inputMappings = [
                               {
                                   input:$input_driverSC,
                                   dropdown: $dropdown_driverSC,
                                   api: 'driverServlet',
                                   action: 'ajaxGetDriversBySpecialCode',
                                   keyLength:3,
                                   keyName: 'driverSC',
                                   dataMapper: item => {
                                       return autoMapItemToList(item,['drivername','id'],['*'],[],$root);// 顯示用的欄位
                                   },
                                   onSelect: ($btn) => {
                                       $("#driverName").val($btn.attr("data-drivername"));
                                       $("#driverId").val($btn.attr("data-id"));
                                       $("#portable").val($btn.attr("data-portable"));
                                       $("#specialCode").val($btn.attr("data-specialcode"));
                                   }
                               },
                               {
                                   input:$input_driverId,
                                   dropdown: $dropdown_driverId,
                                   api: 'driverServlet',
                                   action: 'ajaxGetDriversByDriverId',
                                   keyLength:3,
                                   keyName: 'driverId',
                                   dataMapper: item => {
                                       return autoMapItemToList(item,['drivername','id'],['*'],[],$root);// 顯示用的欄位
                                   },
                                   onSelect: ($btn) => {
                                       $("#driverName").val($btn.attr("data-drivername"));
                                       $("#driverId").val($btn.attr("data-id"));
                                       $("#portable").val($btn.attr("data-portable"));
                                       $("#specialCode").val($btn.attr("data-specialcode"));
                                   }
                               }
                           ];
                           inputMappings.forEach(({input, dropdown, api, action, keyLength, keyName, dataMapper, onSelect}) => {
                               input.on('input keydown', (e) => {
                                   handleAutoCompleteDropdown(e, input, dropdown, {
                                       apiUrl: api, action, keyName, keyLength, dataMapper, onSelect
                                   });
                               });
                           });
                       },
                       onFail: (err) => {
                           console.error("😡 錯誤發生：", err);
                           alert("發生錯誤！");
                       },
                       onClose: () => {
                           console.log("👋 Modal 關起來囉！");
                       }
                   });
           }
       });
    </script>
    <!-- END CUSTOM LEVEL JS-->


  </body>
</html>