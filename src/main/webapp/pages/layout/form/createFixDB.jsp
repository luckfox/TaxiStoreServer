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

    <jsp:include page="../../common/head.jsp" />


    <title>Dashboard eCommerce - Chameleon Admin - Modern Bootstrap 4 WebApp & Dashboard HTML Template + UI Kit</title>
    <link rel="apple-touch-icon" href="<%= app_assetsPath %>/images/ico/apple-icon-120.png">
    <link rel="shortcut icon" type="image/x-icon" href="<%= app_assetsPath %>/images/ico/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i%7CComfortaa:300,400,700" rel="stylesheet">
    <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->


    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/vendors.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/charts/chartist-plugin-tooltip.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/file-uploaders/dropzone.min.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/forms/icheck/icheck.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/forms/icheck/custom.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/vendors/css/timeline/vertical-timeline.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN CHAMELEON  CSS-->
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/app.css">
    <!-- END CHAMELEON  CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/menu/menu-types/vertical-menu-modern.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/fonts/simple-line-icons/style.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/core/colors/palette-gradient.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/timeline.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/plugins/file-uploaders/dropzone.css">
    <link rel="stylesheet" type="text/css" href="<%= app_assetsPath %>/css/pages/dashboard-ecommerce.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="<%= assetsPath %>/css/style.css">
    <!-- END Custom CSS-->
    <style>
      .main-menu-content li
      {
        Font-size:25px;
      }
      .menu-content li
      {
        Font-size:18px;
      }
      .input-group-text, .form-control
      {
        padding-left:10px;
        padding-right:10px;
      }
    </style>





  </head>
  <body class="bg-dark vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu-modern" data-color="bg-chartbg" data-col="2-columns">

    <!--//start of navBar-->
    <%@ include file="../../pages/topNavBar.jsp" %>
    <!--//end of navBar-->
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!--//start of left-side menu-->
    <%@ include file="../../leftSideMenu.jsp" %>
    <!--//end of left-side menu-->


    <!-- ////////////////////////////////////////////////////////////////////////////-->
    <div class="app-content content">
      <div class="content-wrapper">
<%--        <div class="content-wrapper-before"></div>--%>
        <div class="content-header row">
          <div class="content-header-left col-md-8 col-12 mb-0 breadcrumb-new">
            <h3 class="content-header-title mb-0 d-inline-block">車輛資料建檔</h3>
            <div class="row d-inline-block">
              <div class=" mr-1">
                <ol>
                  <li><h3 style="color:white" ><span>流水編號:</span><span id="serialNoTitle">${requestScope.company.serialNo}</span></h3>
                  </li>
                </ol>
              </div>
            </div>
          </div>
          <div class="content-body"><!-- Form control repeater section start -->
            <section id="form-control-repeater">
              <div class="row">
                <div class="col-xl-12 col-lg-12">
                  <div class="card bg-dark text-white">
                    <div class="card-content collapse show">
                      <div class="card-body">

                        <div  class="row">
                          <input id="form-action" type=hidden name="action" value="createCompany"/>
                          <input id="serialNo" type=hidden name="serialNo" value="${requestScope.company.serialNo}"/>

                          <div class="input-group col-md-12 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >原牌照號碼</span>
                            <input type="text" class="form-control" placeholder="原牌照號碼" disabled="disabled" id="orgLicence" name="orgLicence" aria-label="orgLicence" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>

                          <div class="input-group col-md-4 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >車號</span>
                            <input type="text" class="form-control" placeholder="車號" disabled="disabled" id="carno" name="carno" aria-label="carno" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>


                          <div class="input-group col-md-4 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >廠牌</span>
                            <input type="text" class="form-control" placeholder="廠牌" disabled="disabled" id="carBrand" name="carBrand" aria-label="carBrand" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                            <span class="input-group-text bg-grey-blue text-white" >國瑞</span>
                          </div>
                          <div class="input-group col-md-4 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >出廠年月</span>
                            <input type="text" class="form-control" placeholder="出廠年月" disabled="disabled" id="caryear" name="caryear" aria-label="caryear" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >顏色</span>
                            <input type="text" class="form-control" placeholder="顏色" disabled="disabled" id="carColor" name="carColor" aria-label="carColor" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >排氣量</span>
                            <input type="text" class="form-control" placeholder="排氣量" disabled="disabled" id="pipeAmount" name="pipeAmount" aria-label="pipeAmount" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >引擎號碼</span>
                            <input type="text" class="form-control" placeholder="引擎號碼" disabled="disabled" id="mechanicss" name="mechanicss" aria-label="mechanicss" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >原發照日期</span>
                            <input type="text" class="form-control" placeholder="原發照日期" disabled="disabled" id="shingledat" name="shingledat" aria-label="shingledat" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >繳銷日期</span>
                            <input type="text" class="form-control" placeholder="繳銷日期" disabled="disabled" id="canceldate" name="canceldate" aria-label="canceldate" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >車身號碼</span>
                            <input type="text" class="form-control" placeholder="車身號碼" disabled="disabled" id="carlineno" name="carlineno" aria-label="carlineno" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >下次驗表日</span>
                            <input type="text" class="form-control" placeholder="下次驗表日" disabled="disabled" id="cpdate" name="cpdate" aria-label="cpdate" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-3 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >已付保證金</span>
                            <input type="text" class="form-control" placeholder="已付保證金" disabled="disabled" id="paid_security_deposit" name="paid_security_deposit" aria-label="paid_security_deposit" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>

                          <div class="input-group col-md-4 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >預收保證金</span>
                            <input type="text" class="form-control" placeholder="預收保證金" disabled="disabled" id="pre_security_deposit" name="pre_security_deposit" aria-label="pre_security_deposit" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>
                          <div class="input-group col-md-4 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >指定驗車日</span>
                            <input type="text" class="form-control" placeholder="指定驗車日" disabled="disabled" id="chkec_date" name="chkec_date" aria-label="chkec_date" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>

                          <div class="input-group col-md-4 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >上次驗車月</span>
                            <input type="text" class="form-control" placeholder="指定驗車日" disabled="disabled" id="okcar" name="okcar" aria-label="okcar" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                          </div>

                          <div class="input-group col-md-12 mb-1">
                            <span class="input-group-text bg-grey-blue text-white" >備註</span>
                            <textarea rows="2" class="form-control" placeholder="備註" disabled="disabled" id="remark" name="remark" aria-label="remark"  required>${requestScope.company.companyName}</textarea>
                          </div>

                          <div class="col-lg-12 col-md-6">
                            <div id="collapse3" class="card-accordion">
                              <div class="card bg-dark collapse-icon accordion-icon-rotate">
                                <div class="card bg-dark ">
                                  <div class="card-header bg-dark" style="padding:5px" id="headingCOne">
                                    <label class="btn collapsed mb-0" data-toggle="collapse" data-target="#collapseC1" aria-expanded="false" aria-controls="collapseC1">
                                      <h4><span class=" text-white bg-dark " >駕駛員相關資料</span></h4>
                                    </label>
                                  </div>
                                  <div id="collapseC1" class="collapse" aria-labelledby="headingCOne">
                                    <div class="card bg-dark">
                                      <div  class="row">
                                        <div class="input-group col-md-4  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >司機姓名</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="pre_security_deposit" name="pre_security_deposit" aria-label="pre_security_deposit" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="customSelect">
                                            <option selected>Open this select menu</option>
                                            <option value="1">張育瑋</option>
                                            <option value="2">劉國泰</option>
                                            <option value="3">張黃阿甘</option>
                                          </select>
                                        </div>
                                        <div class="input-group col-md-4  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >身分證號</span>
                                          <input type="text" class="form-control" placeholder="身分證號" disabled="disabled" id="driver_id" name="driver_id" aria-label="driver_id" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-4  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >出生日期</span>
                                          <input type="text" class="form-control" placeholder="出生日期" disabled="disabled" id="driver_birthday" name="driver_birthday" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                      <div class="row">
                                        <div class="input-group col-md-4 mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >車行</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="pre_security_deposit" name="pre_security_deposit" aria-label="pre_security_deposit" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="">
                                            <option selected>Open this select menu</option>
                                            <option value="1">勝順</option>
                                            <option value="2">大中原</option>
                                            <option value="3">祥德</option>
                                          </select>
                                        </div>
                                        <div class="input-group col-md-4 mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >車主電話</span>
                                          <input type="text" class="form-control" placeholder="車主電話" disabled="disabled" id="chkec_date" name="chkec_date" aria-label="chkec_date" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                      <div class="row">
                                        <div class="input-group col-md-4 mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >車行</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="pre_security_deposit" name="pre_security_deposit" aria-label="pre_security_deposit" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="">
                                            <option selected>Open this select menu</option>
                                            <option value="1">勝順</option>
                                            <option value="2">大中原</option>
                                            <option value="3">祥德</option>
                                          </select>
                                        </div>
                                        <div class="input-group col-md-4 mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >車主電話</span>
                                          <input type="text" class="form-control" placeholder="車主電話" disabled="disabled" id="chkec_date" name="chkec_date" aria-label="chkec_date" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div class="card">
                                  <div class="card-header bg-dark" style="padding:5px" id="headingCTwo">
                                    <label class="btn collapsed mb-0" data-toggle="collapse" data-target="#collapseC2" aria-expanded="false" aria-controls="collapseC2">
                                      <h4><span class=" text-white bg-dark " >貸款業務</span></h4>
                                    </label>
                                  </div>
                                  <div id="collapseC2" class="collapse" aria-labelledby="headingCTwo">
                                    <div class="card bg-dark">
                                      <div  class="row">
                                        <div class="input-group col-md-4  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white pl-1 pr-1" >貸款公司</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="">
                                            <option selected>Open this select menu</option>
                                            <option value="1">和潤</option>
                                            <option value="2">中租</option>
                                            <option value="3">國泰</option>
                                          </select>
                                        </div>
                                        <div class="input-group col-md-4  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white pl-1 pr-1" >貸款起始日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-4  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white pl-1 pr-1" >貸款終止日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                      <div  class="row">
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >期數</span>
                                          <input type="text" class="form-control" placeholder="期數" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >每期繳費</span>
                                          <input type="text" class="form-control" placeholder="每期繳費" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-3  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >繳款總額</span>
                                          <input type="text" class="form-control" placeholder="繳款總額" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-3  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >貸款總額</span>
                                          <input type="text" class="form-control" placeholder="貸款車價" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white " >毛利</span>
                                          <input type="text" class="form-control" placeholder="毛利" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div class="card">
                                  <div class="card-header bg-dark" style="padding:5px" id="headingCThree">

                                    <label class="btn collapsed mb-0" data-toggle="collapse" data-target="#collapseC3" aria-expanded="false" aria-controls="collapseC3">
                                      <h4><span class=" text-white bg-dark " >保險業務</span></h4>
                                    </label>

                                  </div>
                                  <div id="collapseC3" class="collapse" aria-labelledby="headingCThree">
                                    <div class="card bg-dark">
                                      <div  class="row">
                                        <div class="input-group col-md-3  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >強制險公司</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="">
                                            <option selected>Open this select menu</option>
                                            <option value="1">和潤</option>
                                            <option value="2">中租</option>
                                            <option value="3">國泰</option>
                                          </select>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >卡號</span>
                                          <input type="text" class="form-control" placeholder="卡號" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >起始日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >終止日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >年費</span>
                                          <input type="text" class="form-control" placeholder="金額" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                      <div  class="row">
                                        <div class="input-group col-md-3  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >任意險公司</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="">
                                            <option selected>Open this select menu</option>
                                            <option value="1">和潤</option>
                                            <option value="2">中租</option>
                                            <option value="3">國泰</option>
                                          </select>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >卡號</span>
                                          <input type="text" class="form-control" placeholder="卡號" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >起始日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >終止日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >年費</span>
                                          <input type="text" class="form-control" placeholder="金額" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                      <div  class="row">
                                        <div class="input-group col-md-3  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >聯保公司</span>
                                          <input type="text" class="form-control" placeholder="特別碼" disabled="disabled" id="" name="" aria-label="" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                          <select class=" input-group-text custom-select" id="">
                                            <option selected>Open this select menu</option>
                                            <option value="1">和潤</option>
                                            <option value="2">中租</option>
                                            <option value="3">國泰</option>
                                          </select>
                                        </div>

                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >起始日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >終止日</span>
                                          <input type="text" class="form-control" placeholder="日期" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                        <div class="input-group col-md-2  mt-1">
                                          <span class="input-group-text bg-grey-blue text-white" >月費</span>
                                          <input type="text" class="form-control" placeholder="金額" disabled="disabled" id="" name="" aria-label="driver_birthday" aria-describedby="basic-addon1" value="${requestScope.company.companyName}" required>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>

                          <div class="form-actions col-md-12">
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
                      </div>
                    </div>
                  </div>
                </div>


              </div>
            </section>
            <!-- // Form control repeater section end -->

          </div>
        </div>
      </div>
    </div>
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!--//start of left-side menu-->
    <%@ include file="../../footer.jsp" %>.
    <!--//end of left-side menu-->


    <!-- BEGIN VENDOR JS-->
    <script src="<%= app_assetsPath %>/vendors/js/vendors.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="<%= app_assetsPath %>/vendors/js/charts/chartist.min.js" type="text/javascript"></script>
    <script src="<%= app_assetsPath %>/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>
    <script src="<%= app_assetsPath %>/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>
    <script src="<%= app_assetsPath %>/vendors/js/extensions/dropzone.min.js" type="text/javascript"></script>
    <script src="<%= app_assetsPath %>/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN CHAMELEON  JS-->
    <script src="<%= app_assetsPath %>/js/core/app-menu.js" type="text/javascript"></script>
    <script src="<%= app_assetsPath %>/js/core/app.js" type="text/javascript"></script>
    <!-- END CHAMELEON  JS-->
    <!-- BEGIN PAGE LEVEL JS-->

    <!-- END PAGE LEVEL JS-->
    <!-- BEGIN CUSTOM LEVEL JS-->
    <script type="text/javascript" src="<%= assetsPath %>/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= assetsPath %>/js/loadHTML.js"> </script>
    <script type="text/javascript">
    $(function()
    {
        console.log("createCarDB.jsp");
        $("#form").attr("class","open");
        $("#createCarDB").attr("class","active");

    });
    </script>
    <!-- END CUSTOM LEVEL JS-->


  </body>
</html>