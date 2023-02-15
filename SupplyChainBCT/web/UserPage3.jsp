<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<%
    HttpSession sn = request.getSession();
    String uname = sn.getAttribute("PRN").toString();
   
    String share0 = sn.getAttribute("ShareImage").toString();
    String img1 = uname + "sh0.jpg";
    String img2 = uname + "sh1.jpg";
%>
<html>
    <head>
        <title>SCM Using BCT</title>
        <!-- for-mobile-apps -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Election Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href='//fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
        <!---strat-slider---->
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <!---//-slider---->
        <style>
            body {
                background:url(../images/banner.jpg) no-repeat 0px 0px;
                background-size: cover;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                -ms-background-size: cover;
                min-height: 800px;
            }
            table {

                table-layout:fixed;
                margin: 0em 0em 12em 0em !important;
            }
            th, td {
                padding:10px 10px;
                border:1px solid;
                text-align: center;
                border-color: #080808;
            }

            tbody {
                overflow:auto;
                overflow-x:hidden;
                display:block;
                width:100%;
            }
            tbody tr {
                display:table;
                width:100%;
                table-layout:fixed;
            }

        </style>
        <script>
            function validation()
            {
                var filename = document.share1.photoname.value;
                if (filename == 0)
                {
                    alert("Please enter image name");
                    document.share1.photoname.focus();
                    return  false;
                }
                return true;
            }
        </script>
    </head>

    <body>
        <!-- header -->
        <div class="header_bg">
            <div class="container">
                <!-----start-header----->
                <div class="header">				
                    <nav class="navbar navbar-default">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav">
                               
                            </ul>
                        </div><!-- /.navbar-collapse -->	

                    </nav>
                </div>
            </div>
        </div>
        <div class="header_bottom">
        </div>
        <!-- //end-header -->
        <%

        %>
        <!-- banner -->
        <div class="">
            <div class="container">
                <section class="slider">
                    <div class="flexslider">
                        <center><br><br><br><br>
                            <h2>Combine Share</h2><br><br><br>
                            <form name="share1" action="./CombineShare" method="post" enctype="multipart/form-data">
                                <img src="shares/<%= share0%>" width="100" height="50"> &nbsp;

                                <img src="password/<%= img2%>" width="100" height="50">

                                <br><br>
                                <input type="submit" value="Get Password">
                                 <br><br>
                                  <br><br>
                            </form>
                        </center>
                    </div>
                </section>
            </div>
            <!-- FlexSlider -->
            <script defer src="js/jquery.flexslider.js"></script>
            <script type="text/javascript">
            $(window).load(function () {
                $('.flexslider').flexslider({
                    animation: "slide",
                    start: function (slider) {
                        $('body').removeClass('loading');
                    }
                });
            });
            </script>
            <!-- //FlexSlider -->
        </div>
        <!-- //banner -->


        <!-- footer -->
        <div class="footer">
            <div style="text-align: center;">
                <p></p>
            </div>
        </div>
        <!-- //footer -->
        <!-- scroll_top_btn -->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                var defaults = {
                    containerID: 'toTop', // fading element id
                    containerHoverID: 'toTopHover', // fading element hover id
                    scrollSpeed: 1200,
                    easingType: 'linear'
                };


                $().UItoTop({easingType: 'easeOutQuart'});

            });
        </script>
        <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
        <!--end scroll_top_btn -->
        <!-- for bootstrap working -->
        <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
        <!-- //for bootstrap working -->
    </body>

</html>