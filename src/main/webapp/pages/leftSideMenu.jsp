<%@ page pageEncoding="UTF-8" %>
<jsp:include page="../../common/head.jsp" />
<div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow" data-scroll-to-active="true" data-img="<%= app_assetsPath %>/images/backgrounds/04.jpg">
    <div class="navbar-header">
        <ul class="nav navbar-nav flex-row position-relative">
            <li class="nav-item mr-auto"><a class="navbar-brand" href=""><img class="brand-logo" alt="Chameleon admin logo" src="<%= app_assetsPath %>/images/logo/logo.png"/>
                <h3 class="brand-text">Chameleon</h3></a></li>
            <li class="nav-item d-none d-md-block nav-toggle"><a class="nav-link modern-nav-toggle pr-0" data-toggle="collapse"><i class="toggle-icon ft-disc font-medium-3" data-ticon="ft-disc"></i></a></li>
            <li class="nav-item d-md-none"><a class="nav-link close-navbar"><i class="ft-x"></i></a></li>
        </ul>
    </div>
    <div class="main-menu-content">
        <ul class="navigation navigation-main" id="main-menu-navigation" data-menu="menu-navigation">
            <li class=" nav-item" id="dashboard"><a href="<%= request.getContextPath() %>/pages/layout/dashboard/dashboard-ecommerce.jsp"><i class="ft-home"></i><span class="menu-title" data-i18n="">簡報</span><span class="badge badge badge-info badge-pill float-right mr-2">3</span></a>
                <ul class="menu-content">
                    <li id="dashboard-ecommerce" ><a class="menu-item" href="<%= request.getContextPath() %>/pages/layout/dashboard/dashboard-ecommerce.jsp">統計</a></li>
                    <li id="dashboard-analytics" ><a class="menu-item" href="<%= request.getContextPath() %>/pages/layout/dashboard/dashboard-analytics.jsp">分析</a></li>
                    <li id="dashboard-test2" ><a class="menu-item" href="<%= request.getContextPath() %>/demo/uploader/ImagePreviewManagerNew_demo.jsp">測試2</a></li>
                    <li id="dashboard-test3" ><a class="menu-item" href="<%= request.getContextPath() %>/demo/cloudinary/ImageMetadata_demo.jsp">測試3</a></li>
                    <li id="dashboard-test4" ><a class="menu-item" href="<%= request.getContextPath() %>/demo/cloudinary/CloudinaryPreviewManager_demo.jsp">測試4</a></li>
                </ul>
            </li>
            <!--//start of apps-->
            <li class="nav-item" id="apps" ><a href="/pages/layout/apps/email-application.jsp"><i class="ft-layers"></i><span class="menu-title" data-i18n="">查詢</span></a>
                <ul class="menu-content">
                    <li id="email-application"><a class="menu-item" href="/pages/layout/apps/email-application.jsp">強制險查詢</a></li>
                    <li id="vehicle-Inspection" ><a class="menu-item" href="/pages/layout/apps/vehicle-Inspection.jsp">驗車日期查詢</a></li>
                    <li id="vehicleStatus"><a class="menu-item" href="/pages/layout/apps/vehicleStatus.jsp">車輛現況查詢</a></li>
                    <li id="lineUser-profile"><a class="menu-item" href="<%= request.getContextPath() %>/pages/layout/apps/LineUser-profile.jsp">Line使用者查詢</a></li>
                    <li id="users-contacts"><a class="menu-item" href="/pages/layout/apps/user-contacts.jsp">Contact List</a></li>
                </ul>
            </li>
            <!--//end of apps-->
            <li id="form" class=" nav-item"><a href="/pages/layout/form/form-inputs.jsp"><i class="ft-edit"></i><span class="menu-title" data-i18n="">基本建檔</span></a>
                <ul class="menu-content">
                    <li id="createDriverDB"><a class="menu-item" href="driverServlet?action=getLastDriverDTO">駕駛員建檔</a>
                    </li>
                    <li id="createFixDB"><a class="menu-item" href="<%= request.getContextPath() %>/pages/layout/form/createFixDB.jsp">車輛維修建檔</a>
                    </li>
                    <li id="createCarDB"><a class="menu-item" href="carServlet?action=getLastCarDTO">車輛資料建檔</a>
                    </li>
                    <li id="createFineDB"><a class="menu-item" href="fineServlet?action=getLastFineDTO">罰單資料建檔</a>
                    </li>
                    <li id="createCompanyDB"><a class="menu-item" href="companyServlet?action=getLastCompany">公司資料建檔</a>
                    </li>
                </ul>
            </li>
            <li id="fine" class=" nav-item"><a href="#"><i class="ft-grid"></i><span class="menu-title" data-i18n="">罰單作業</span></a>
                <ul class="menu-content">
                    <li id="fineRule"><a class="menu-item" href="fineRuleServlet?action=getLastFineRule">罰單條文建檔</a></li>
                    <li><a class="menu-item" href="">罰單建檔</a>
                    </li>
                    <li><a class="menu-item" href="">罰單查詢</a>
                    </li>
                    <li
                    ><a class="menu-item" href="#">DataTables</a>
                        <ul class="menu-content">
                            <li><a class="menu-item" href="dt-basic-initialization.html">Basic Initialisation</a>
                            </li>
                            <li><a class="menu-item" href="dt-styling.html">Styling</a>
                            </li>
                            <li><a class="menu-item" href="dt-data-sources.html">Data Sources</a>
                            </li>
                            <li><a class="menu-item" href="dt-advanced-initialization.html">Advanced initialisation</a>
                            </li>
                            <li><a class="menu-item" href="dt-api.html">API</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li id="accounting" class=" nav-item"><a href="#"><i class="ft-bar-chart-2"></i><span class="menu-title" data-i18n="">收付款作業</span></a>
                <ul class="menu-content">
                    <li id="accountingOperation"><a class="menu-item" href="situationServlet?action=getLastSituation">駕駛員收款單</a>
                    </li>
                    <li><a class="menu-item" href="chartjs-charts.html">駕駛員總帳查詢</a>
                    </li>
                    <li><a class="menu-item" href="#">Maps</a>
                        <ul class="menu-content">
                            <li><a class="menu-item" href="google-maps.html">Google Maps</a>
                            </li>
                            <li><a class="menu-item" href="jvector-maps.html">jVector Map</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class=" nav-item"><a href="#"><i class="ft-sidebar"></i><span class="menu-title" data-i18n="">Modals</span></a>
                <ul class="menu-content">
                    <li><a class="menu-item" href="<%= request.getContextPath() %>/pages/layout/form/ModalDemo0.jsp">Modal0</a>
                    </li>
                    <li><a class="menu-item" href="<%= request.getContextPath() %>/pages/layout/form/ModalDemo1.jsp">Modal1</a>
                    </li>
                </ul>
            </li>
            <li class=" nav-item"><a href="#"><i class="ft-sidebar"></i><span class="menu-title" data-i18n="">PreviewManager</span></a>
                <ul class="menu-content">
                    <li><a class="menu-item" href="<%= request.getContextPath() %>/demo/uploader/ImagePreviewManagerNew_demo.jsp">ImagePreviewManager</a>
                    </li>
                    <li><a class="menu-item" href="<%= request.getContextPath() %>/demo/cloudinary/CloudinaryPreviewManager_demo.jsp">CloudinaryPreviewManager</a>
                    </li>
                </ul>
            </li>
            <li class=" nav-item"><a href="#"><i class="ft-sidebar"></i><span class="menu-title" data-i18n="">Pages</span></a>
                <ul class="menu-content">
                    <li><a class="menu-item" href="gallery-grid.html">Gallery</a>
                    </li>
                    <li><a class="menu-item" href="search.html">Search</a>
                    </li>
                    <li><a class="menu-item" href="#">Authentication</a>
                        <ul class="menu-content">
                            <li><a class="menu-item" href="login.html">Login</a>
                            </li>
                            <li><a class="menu-item" href="register.html">Register</a>
                            </li>
                            <li><a class="menu-item" href="unlock-user.html">Unlock User</a>
                            </li>
                            <li><a class="menu-item" href="recover-password.html">Recover password</a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="menu-item" href="#">Error</a>
                        <ul class="menu-content">
                            <li><a class="menu-item" href="error-400.html">Error 400</a>
                            </li>
                            <li><a class="menu-item" href="error-401.html">Error 401</a>
                            </li>
                            <li><a class="menu-item" href="error-403.html">Error 403</a>
                            </li>
                            <li><a class="menu-item" href="error-404.html">Error 404</a>
                            </li>
                            <li><a class="menu-item" href="error-500.html">Error 500</a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="menu-item" href="coming-soon.html">Coming Soon</a>
                    </li>
                    <li><a class="menu-item" href="under-maintenance.html">Maintenance</a>
                    </li>
                </ul>
            </li>
            <li class=" nav-item"><a href="changelog.html"><i class="ft-file"></i><span class="menu-title" data-i18n="">Changelog</span><span class="badge badge badge-pill badge-warning float-right">1.0</span></a>
            </li>
            <li class=" nav-item"><a href="#"><i class="ft-server"></i><span class="menu-title" data-i18n="">Menu levels</span></a>
                <ul class="menu-content">
                    <li><a class="menu-item" href="#">Second level</a>
                    </li>
                    <li><a class="menu-item" href="#">Second level child</a>
                        <ul class="menu-content">
                            <li><a class="menu-item" href="#">Third level</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class=" nav-item"><a href="https://themeselection.com/support"><i class="ft-life-buoy"></i><span class="menu-title" data-i18n="">Raise Support</span></a>
            </li>
            <li class=" nav-item"><a href="https://themeselection.com/demo/chameleon-admin-template/documentation"><i class="ft-book"></i><span class="menu-title" data-i18n="">Documentation</span></a>
            </li>
        </ul>
    </div>
    <div class="navigation-background"></div>
</div>