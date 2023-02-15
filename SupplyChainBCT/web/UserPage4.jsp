<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.text.SimpleDateFormat" %>
<%
    HttpSession sn=request.getSession();
    String uname=sn.getAttribute("PRN").toString();
    String pass=sn.getAttribute("UserPass").toString();
    String img1=uname+"cs.jpg";
    
%>

<!DOCTYPE HTML>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <!---strat-slider---->
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <link href="css/jquery-ui.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <link href="css/owl.carousel.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <!---//-slider---->

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords"
              content="Baby Care Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
        <script type="application/x-javascript">

            addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }

        </script>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/jquery-ui.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <link href="css/owl.carousel.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <!--JS for animate-->
        <link href="css/animate.css" rel="stylesheet" type="text/css"
              media="all">
        <script src="js/wow.min.js"></script>
        <script>
            new WOW().init();
        </script>

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

            fieldset.scheduler-border {
                border: 2px groove black !important;
                padding: 2em 2em 2em 2em !important;
                margin: 0em 6em 6em 6em !important;
                -webkit-box-shadow:  0px 0px 0px 0px #000;
                box-shadow:  0px 0px 0px 0px #000;
            }

            table {
                table-layout:fixed;
                margin:auto;
                alignment-adjust: central;
            }
            th, td {
                padding:10px 10px;
            }
            tr {
                display:table;
                width:100%;
                table-layout:fixed;
            }
        </style>
        <script>
            function validation() {
                var prn = document.getElementById("shpass").value;
                
                
                if (prn === "") {
                    alert("Please Enter OTP");
                    document.getElementById("shpass").focus();
                    return false;
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
        <!-- banner -->
        <div class="">
            <div class="container">
                <section class="slider">
                    <div class="flexslider">
                        <center><br><br><br><br>
                            <h2>Enter Password</h2><br><br><br>
                            <form action="ValidatePass" name="ulogin" method="post" class="mod2" onsubmit="return validation()">
                                <fieldset class="scheduler-border">  
                                      <div class="col-md-12" id="name" style="padding-bottom: 10px;">
                                        <div class="col-sm-5">
                                            <div class="form-group" style="text-align: left">
                                                Captcha :  
                                            </div>
                                        </div>
                                        <div class="col-sm-7">
                                            <div class="form-group">
                                              <img src="password/<%= img1%>" width="100" height="50"> 
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-12" id="name" style="padding-bottom: 10px;">
                                        <div class="col-sm-5">
                                            <div class="form-group" style="text-align: left">
                                                OTP :  
                                            </div>
                                        </div>
                                        <div class="col-sm-7">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="shpass" name="shpass"/>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <input type="hidden" name="hid" value="'<script>'$this.val();'</script>'">
                                    <input type="submit" value="Validate" style="height: 50px; width: 150px; background-color:blue"/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="reset" value="Reset" style="height: 50px; width: 150px; background-color:red"/>
                                </fieldset>
                            </form>
                        </center>
                    </div>
                </section>
            </div>

            <!-- FlexSlider -->
            <script type="text/javascript" src="js/jquery.min"></script>

            <script>
                                $(document).on(
                                        'change',
                                        '.college_name',
                                        function() {
                                            var id = $(this).val();
                                            //var election_id = $('#item').val();
                                            var item = $(this);
                                            var url1 = 'fetch_college.jsp?q=' + id;
                                            $.ajax({
                                                url: url1,
                                                dataType: 'json',
                                                success: function(result) {

                                                    item.parents('#item').siblings('#Unit')
                                                            .find(".cid").val(
                                                            $.trim(result.cid));
//                                                    alert('College Id = ' + result.cid);

                                                    item.parents('#item').siblings('#Unit')
                                                            .find(".city").val(
                                                            $.trim(result.city));
//                                                    alert('College City = ' + result.city);

                                                }
                                            });
                                        });

            </script>

            <script defer src="js/jquery.flexslider.js"></script>
            <script type="text/javascript">
                                $(window).load(function() {
                                    $('.flexslider').flexslider({
                                        animation: "slide",
                                        start: function(slider) {
                                            $('body').removeClass('loading');
                                        }
                                    });
                                });</script>
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
                                $(document).ready(function() {

                                    var defaults = {
                                        containerID: 'toTop', // fading element id
                                        containerHoverID: 'toTopHover', // fading element hover id
                                        scrollSpeed: 1200,
                                        easingType: 'linear'
                                    };
                                    $().UItoTop({easingType: 'easeOutQuart'});
                                });</script>
        <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
        <!--end scroll_top_btn -->
        <!-- for bootstrap working -->
        <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
        <!-- //for bootstrap working -->
    </body>
</html>