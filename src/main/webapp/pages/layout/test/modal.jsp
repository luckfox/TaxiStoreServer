<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luckf
  Date: 2022/10/10
  Time: 上午 04:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/head.jsp" %>
<!-- BEGIN VENDOR CSS-->
<link rel="stylesheet" type="text/css" href="/pages/app-assets/css/vendors.css">
<link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/forms/selects/select2.min.css">
<link rel="stylesheet" type="text/css" href="/pages/app-assets/vendors/css/ui/prism.min.css">
<!-- END VENDOR CSS-->
<!-- BEGIN CHAMELEON CSS-->
<link rel="stylesheet" type="text/css" href="/pages/app-assets/css/app.css">
<!-- END CHAMELEON CSS-->
<!-- BEGIN Page Level CSS-->
<link rel="stylesheet" type="text/css"
      href="/pages/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
<link rel="stylesheet" type="text/css" href="/pages/app-assets/css/core/colors/palette-gradient.css">
<link rel="stylesheet" type="text/css" href="/pages/app-assets/css/plugins/animate/animate.css">
<!-- END Page Level CSS-->
<!-- BEGIN Custom CSS-->
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<!-- END Custom CSS-->
<style>
    tr.focus {
        background-color: #eee;
    }
</style>
<link href="/pages/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/pages/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">

    <section id="basic-modals">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Basic Modals</h4>
                        <a class="heading-elements-toggle"><i class="la la-ellipsis font-medium-3"></i></a>
                        <div class="heading-elements">
                            <ul class="list-inline mb-0">
                                <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                <li><a data-action="close"><i class="ft-x"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-content collapse show">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-4 col-md-6 col-sm-12 my-1">
                                    <div class="form-group">
                                        <h5>Default Modal</h5>
                                        <p>Toggle a modal via JavaScript by clicking the button above.</p>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary " data-toggle="modal"
                                                data-target="#default">
                                            Launch Demo Modal
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade  text-center" id="default" tabindex="-1" role="dialog"
                                             aria-labelledby="basicModalLabel1" aria-hidden="true">
                                            <div class="modal-dialog" role="document" style="display: inline-block; width: 900px;">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="basicModalLabel1">Basic Modal</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Woohoo, you're reading this text in a modal!</p>
                                                        <table class="table table-bordered scroll-vertical">
                                                            <thead>
                                                            <tr>
                                                                <th>序號</th>
                                                                <th>公司名稱</th>
                                                                <th>負責人</th>
                                                                <th>電子郵件</th>
                                                                <th>統一編號</th>
                                                                <th>電話1</th>
                                                                <th>傳真1</th>
                                                                <th>住址1</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${requestScope.Companies}" var="company">
                                                                <tr>
                                                                    <td>${company.serialNo}</td>
                                                                    <td>${company.companyName}</td>
                                                                    <td>${company.principal}</td>
                                                                    <td>${company.email}</td>
                                                                    <td>${company.uniformNo}</td>
                                                                    <td>${company.phone1}</td>
                                                                    <td>${company.fax1}</td>
                                                                    <td>${company.address1}</td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                            <tfoot>
                                                            <tr>
                                                                <th>序號</th>
                                                                <th>公司名稱</th>
                                                                <th>負責人</th>
                                                                <th>電子郵件</th>
                                                                <th>統一編號</th>
                                                                <th>電話1</th>
                                                                <th>傳真1</th>
                                                                <th>住址1</th>
                                                            </tr>
                                                            </tfoot>
                                                        </table>

                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn grey btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-danger">Save</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-6 col-sm-12 my-1">
                                    <div class="form-group">
                                        <h5>Scrolling long content</h5>
                                        <p> When modals become too long for the user’s viewport or device, they scroll
                                            independent of the page itself.</p>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary " data-toggle="modal"
                                                data-target="#iconModal">
                                            Launch Demo Modal
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade text-left" id="iconModal" tabindex="-1" role="dialog"
                                             aria-labelledby="basicModalLabel2" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="basicModalLabel2"><i
                                                                class="la la-road2"></i> Basic Modal</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                        <p>Praesent commodo cursus magna, vel scelerisque nisl
                                                            consectetur et. Vivamus sagittis lacus vel augue laoreet
                                                            rutrum faucibus dolor auctor.</p>
                                                        <p>Aenean lacinia bibendum nulla sed consectetur. Praesent
                                                            commodo cursus magna, vel scelerisque nisl consectetur et.
                                                            Donec sed odio dui. Donec ullamcorper nulla non metus auctor
                                                            fringilla.</p>
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                        <p>Praesent commodo cursus magna, vel scelerisque nisl
                                                            consectetur et. Vivamus sagittis lacus vel augue laoreet
                                                            rutrum faucibus dolor auctor.</p>
                                                        <p>Aenean lacinia bibendum nulla sed consectetur. Praesent
                                                            commodo cursus magna, vel scelerisque nisl consectetur et.
                                                            Donec sed odio dui. Donec ullamcorper nulla non metus auctor
                                                            fringilla.</p>
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                        <p>Praesent commodo cursus magna, vel scelerisque nisl
                                                            consectetur et. Vivamus sagittis lacus vel augue laoreet
                                                            rutrum faucibus dolor auctor.</p>
                                                        <p>Aenean lacinia bibendum nulla sed consectetur. Praesent
                                                            commodo cursus magna, vel scelerisque nisl consectetur et.
                                                            Donec sed odio dui. Donec ullamcorper nulla non metus auctor
                                                            fringilla.</p>
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                        <p>Praesent commodo cursus magna, vel scelerisque nisl
                                                            consectetur et. Vivamus sagittis lacus vel augue laoreet
                                                            rutrum faucibus dolor auctor.</p>
                                                        <p>Aenean lacinia bibendum nulla sed consectetur. Praesent
                                                            commodo cursus magna, vel scelerisque nisl consectetur et.
                                                            Donec sed odio dui. Donec ullamcorper nulla non metus auctor
                                                            fringilla.</p>
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                        <p>Praesent commodo cursus magna, vel scelerisque nisl
                                                            consectetur et. Vivamus sagittis lacus vel augue laoreet
                                                            rutrum faucibus dolor auctor.</p>
                                                        <p>Aenean lacinia bibendum nulla sed consectetur. Praesent
                                                            commodo cursus magna, vel scelerisque nisl consectetur et.
                                                            Donec sed odio dui. Donec ullamcorper nulla non metus auctor
                                                            fringilla.</p>
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                        <p>Praesent commodo cursus magna, vel scelerisque nisl
                                                            consectetur et. Vivamus sagittis lacus vel augue laoreet
                                                            rutrum faucibus dolor auctor.</p>
                                                        <p>Aenean lacinia bibendum nulla sed consectetur. Praesent
                                                            commodo cursus magna, vel scelerisque nisl consectetur et.
                                                            Donec sed odio dui. Donec ullamcorper nulla non metus auctor
                                                            fringilla.</p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn grey btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-danger">Save</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-6 col-sm-12 my-1">
                                    <div class="form-group">
                                        <h5>Vertically centered</h5>
                                        <p> Add <code>.modal-dialog-centered</code> to <code>.modal-dialog</code> to
                                            vertically center the modal.</p>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary " data-toggle="modal"
                                                data-keyboard="false" data-target="#keyboard1">
                                            Launch Demo Modal
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade text-left" id="keyboard1" tabindex="-1" role="dialog"
                                             aria-labelledby="basicModalLabel3" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="basicModalLabel3">Basic Modal</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Cras mattis consectetur purus sit amet fermentum. Cras justo
                                                            odio, dapibus ac facilisis in, egestas eget quam. Morbi leo
                                                            risus, porta ac consectetur ac, vestibulum at eros.</p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn grey btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-danger">Save</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-6 col-sm-12 my-1">
                                    <div class="form-group">
                                        <h5>Using the grid</h5>
                                        <p>Utilize the Bootstrap grid system within a modal by nesting <code>.container-fluid</code>
                                            within the <code>.modal-body.</code></p>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary " data-toggle="modal"
                                                data-backdrop="false" data-target="#grid-system">
                                            Launch Demo Modal
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade text-left" id="grid-system" tabindex="-1" role="dialog"
                                             aria-labelledby="basicModalLabel4" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="basicModalLabel4">Basic Modal</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="container-fluid bd-example-row">
                                                            <div class="row">
                                                                <div class="col-md-4">.col-md-4</div>
                                                                <div class="col-md-4 ml-auto">.col-md-4 .ml-auto</div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-3 ml-auto">.col-md-3 .ml-auto</div>
                                                                <div class="col-md-2 ml-auto">.col-md-2 .ml-auto</div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6 ml-auto">.col-md-6 .ml-auto</div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-9">
                                                                    Level 1: .col-sm-9
                                                                    <div class="row">
                                                                        <div class="col-8 col-sm-6">
                                                                            Level 2: .col-8 .col-sm-6
                                                                        </div>
                                                                        <div class="col-4 col-sm-6">
                                                                            Level 2: .col-4 .col-sm-6
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn grey btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-danger">Save</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-6 col-sm-12 my-1">
                                    <div class="form-group">
                                        <h5>Disable Animation</h5>
                                        <p>For modals that simply appear rather than fade in to view, remove the .fade
                                            class from your modal markup.</p>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary " data-toggle="modal"
                                                data-target="#animation">
                                            Launch Demo Modal
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal text-left" id="animation" tabindex="-1" role="dialog"
                                             aria-labelledby="myModalLabel6" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="myModalLabel6"><i
                                                                class="la la-tree"></i> Basic Modal</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <h5><i class="la la-arrow-right"></i> Check First Paragraph</h5>
                                                        <p>Oat cake ice cream candy chocolate cake chocolate cake cotton
                                                            candy dragée apple pie. Brownie carrot cake candy canes
                                                            bonbon fruitcake topping halvah. Cake sweet roll cake
                                                            cheesecake cookie chocolate cake liquorice. Apple pie sugar
                                                            plum powder donut soufflé.</p>
                                                        <p>Sweet roll biscuit donut cake gingerbread. Chocolate cupcake
                                                            chocolate bar ice cream. Danish candy cake.</p>
                                                        <hr>
                                                        <h5><i class="la la-lightbulb-o"></i> Some More Text</h5>
                                                        <p>Cupcake sugar plum dessert tart powder chocolate fruitcake
                                                            jelly. Tootsie roll bonbon toffee danish. Biscuit sweet cake
                                                            gummies danish. Tootsie roll cotton candy tiramisu lollipop
                                                            candy cookie biscuit pie.</p>
                                                        <div class="alert alert-success" role="alert">
                                                            <span class="text-bold-600">Well done!</span> You
                                                            successfully read this important alert message.
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn grey btn-secondary"
                                                                data-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-danger">Save</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<!-- BEGIN VENDOR JS-->
<script src="/pages/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<script src="/pages/app-assets/vendors/js/forms/select/select2.full.min.js" type="text/javascript"></script>
<script src="/pages/app-assets/vendors/js/ui/prism.min.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<!-- BEGIN CHAMELEON  JS-->
<script src="/pages/app-assets/js/core/app-menu.js" type="text/javascript"></script>
<script src="/pages/app-assets/js/core/app.js" type="text/javascript"></script>
<!-- END CHAMELEON  JS-->
<!-- BEGIN PAGE LEVEL JS-->
<script src="/pages/app-assets/js/scripts/forms/select/form-select2.js" type="text/javascript"></script>
<script src="/pages/app-assets/js/scripts/modal/components-modal.js" type="text/javascript"></script>
<!-- END PAGE LEVEL JS-->
<script type="text/javascript">
    $(function () {
        console.log("start script");
        $("tbody >tr").on("click", function () {
            console.log("select =" + $(this).find("td").eq(0).text());
        });
        $("tbody > tr").hover(function () {
            $(this).parent().find("tr.focus").toggleClass("focus");
            $(this).toggleClass("focus");
        });
    });
</script>
</body>
</html>
