<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Supply Chain in BCT</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- favicon
                    ============================================ -->
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <!-- Google Fonts
                    ============================================ -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i,800" rel="stylesheet">
        <!-- Bootstrap CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Bootstrap CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <!-- adminpro icon CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/adminpro-custon-icon.css">
        <!-- meanmenu icon CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/meanmenu.min.css">
        <!-- mCustomScrollbar CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- animate CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- jvectormap CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/jvectormap/jquery-jvectormap-2.0.3.css">
        <!-- normalize CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/data-table/bootstrap-table.css">
        <link rel="stylesheet" href="css/data-table/bootstrap-editable.css">
        <!-- normalize CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/normalize.css">
        <!-- charts CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/c3.min.css">
        <!-- style CSS
                    ============================================ -->
        <link rel="stylesheet" href="style.css">
        <!-- responsive CSS
                    ============================================ -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- modernizr JS
                    ============================================ -->
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    </head>
    <%
        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();
        String usertype = session.getAttribute("usertype").toString();
    %>
    <body class="materialdesign">
        <!--[if lt IE 8]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
            <![endif]-->
        <!-- Header top area start-->
        <div class="wrapper-pro">
            <div class="left-sidebar-pro">
                <nav id="sidebar">
                    <div class="sidebar-header">
                        <a href="#"><img src="img/message/1.jpg" alt="" />
                        </a>
                        <h3><%=name%></h3>
                        <strong>SCB</strong>
                    </div>
                    <div class="left-custom-menu-adp-wrap">
                        <ul class="nav navbar-nav left-sidebar-menu-pro">
                            <%
                                if (usertype.equals("admin")) {
                            %>
                            <li class="nav-item">
                                <a href="admin_home.jsp" class="dropdown-item"><i class="fa big-icon fa-home"></i> <span class="mini-dn">Home</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="agents.jsp" class="dropdown-item"><i class="fa big-icon fa-user-secret"></i> <span class="mini-dn">Agents</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="customers.jsp" class="dropdown-item"><i class="fa big-icon fa-user"></i> <span class="mini-dn">Farmers</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="transactions.jsp" class="dropdown-item"><i class="fa big-icon fa-rupee"></i> <span class="mini-dn">Transactions</span> </a>
                            </li>
                            <%
                            } else if (usertype.equals("1")) {
                            %>

                            <li class="nav-item">
                                <a href="dashboard.jsp" class="dropdown-item"><i class="fa big-icon fa-home"></i> <span class="mini-dn">Home</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="view_farmer_posts.jsp" class="dropdown-item"><i class="fa big-icon fa-file"></i> <span class="mini-dn">View Farmer Posts</span> </a>
                            </li>

                            <li class="nav-item">
                                <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
                                    <i class="fa big-icon fa-inbox"></i> <span class="mini-dn">Inventory</span> <span class="indicator-right-menu mini-dn"><i class="fa indicator-mn fa-angle-left"></i></span></a>
                                <div role="menu" class="dropdown-menu left-menu-dropdown animated flipInX">
                                    <a href="view_agent_post.jsp" class="dropdown-item">My Inventory</a>
                                    <a href="my_inventory_requests.jsp" class="dropdown-item">My Inventory Request</a>
                                    <a href="agent_request_inventory.jsp" class="dropdown-item">Inventory Request</a>
                                   
                                </div>
                            </li>
                            <li class="nav-item">
                                <a href="bank_details.jsp" class="dropdown-item"><i class="fa big-icon fa-bank"></i> <span class="mini-dn">Bank Details</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="agent_transactions.jsp" class="dropdown-item"><i class="fa big-icon fa-rupee"></i> <span class="mini-dn">Transactions</span> </a>
                            </li>
                            <%
                            } else {
                            %>
                            <li class="nav-item">
                                <a href="dashboard.jsp" class="dropdown-item"><i class="fa big-icon fa-home"></i> <span class="mini-dn">Home</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="farmer_posts.jsp" class="dropdown-item"><i class="fa big-icon fa-user-secret"></i> <span class="mini-dn">My Post</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="bank_details.jsp" class="dropdown-item"><i class="fa big-icon fa-bank"></i> <span class="mini-dn">Bank Details</span> </a>
                            </li>
                            <li class="nav-item">
                                <a href="farmer_transactions.jsp" class="dropdown-item"><i class="fa big-icon fa-user"></i> <span class="mini-dn">Transactions</span> </a>
                            </li>

                            <%
                                }
                            %>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="content-inner-all">
                <div class="header-top-area">
                    <div class="fixed-header-top">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-1 col-md-6 col-sm-6 col-xs-12">

                                    <div class="admin-logo logo-wrap-pro">
                                        <a href="#"><img src="img/logo/log.png" alt="" />
                                        </a>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-1 col-sm-1 col-xs-12">
                                    <div class="header-top-menu tabl-d-n">

                                    </div>
                                </div>
                                <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">
                                    <div class="header-right-info">
                                        <ul class="nav navbar-nav mai-top-nav header-right-menu">


                                            <li class="nav-item">
                                                <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
                                                    <span class="adminpro-icon adminpro-user-rounded header-riht-inf"></span>
                                                    <span class="admin-name"><%=name%></span>
                                                    <span class="author-project-icon adminpro-icon adminpro-down-arrow"></span>
                                                </a>
                                                <ul role="menu" class="dropdown-header-top author-log dropdown-menu animated flipInX">



                                                    <li><a href="logout.jsp"><span class="adminpro-icon adminpro-locked author-log-ic"></span>Log Out</a>
                                                    </li>
                                                </ul>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Mobile Menu start -->
                <div class="mobile-menu-area">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="mobile-menu">
                                    <nav id="dropdown">
                                        <ul class="mobile-menu-nav">
                                            <li class="nav-item">
                                                <a href="admin_home.jsp" class="dropdown-item"><i class="fa big-icon fa-home"></i> <span class="mini-dn">Home</span> </a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="agents.jsp" class="dropdown-item"><i class="fa big-icon fa-user-secret"></i> <span class="mini-dn">Agents</span> </a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="customers.jsp" class="dropdown-item"><i class="fa big-icon fa-user"></i> <span class="mini-dn">Farmers</span> </a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="transactions.jsp" class="dropdown-item"><i class="fa big-icon fa-rupee"></i> <span class="mini-dn">Transactions</span> </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Mobile Menu end -->