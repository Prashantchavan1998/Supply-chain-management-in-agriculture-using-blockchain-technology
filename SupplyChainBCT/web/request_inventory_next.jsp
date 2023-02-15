<%@page import="visual.AES"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="pack.DBConnection"%>
<%
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    httpResponse.setHeader("Pragma", "no-cache");
    httpResponse.setDateHeader("Expires", 0);
    if (session.getAttribute("usertype") == null || session.getAttribute("email") == null) {
        response.sendRedirect("logout.jsp");
        return;
    }
    AES aes=new AES();
   String id = request.getParameter("id");
   String agent_id = aes.encrypt(request.getParameter("agent_id"));
   
  DBConnection db = new DBConnection();
    String query = "SELECT * FROM tbl_users WHERE email='" + agent_id+"'";
    String agent_name = "", agent_mobile = "", agent_email = "", crop_name = "", rate = "", quantity = "", amount = "", posted_on = "", accepted_on = "";
    ResultSet rs1 = db.select(query);
    if (rs1.next()) {
        agent_name = aes.decrypt(rs1.getString("name"));
        agent_mobile = aes.decrypt(rs1.getString("mobile"));
        agent_email = aes.decrypt(rs1.getString("email"));
       
    }
   
%>
<jsp:include page="header.jsp"/>
<!-- Header top area start-->

<!-- Header top area end-->
<!-- Breadcome start-->
<div class="breadcome-area mg-b-30 small-dn">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcome-list map-mg-t-40-gl shadow-reset">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="breadcome-heading">

                            </div>
                        </div>
                        <div class="col-lg-6">
                            <ul class="breadcome-menu">
                                <li><a href="dashboard.jsp">Home</a> <span class="bread-slash">/</span>
                                </li>
                                <li><span class="bread-blod">My Posts</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcome End-->

<!-- Breadcome start-->
<div class="breadcome-area des-none mg-b-30">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="breadcome-list map-mg-t-40-gl shadow-reset">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="breadcome-heading">
                                <form role="search" class="">
                                    <input type="text" placeholder="Search..." class="form-control">
                                    <a href=""><i class="fa fa-search"></i></a>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <ul class="breadcome-menu">
                                <li><a href="admin_home.jsp">Home</a> <span class="bread-slash">/</span>
                                </li>
                                <li><span class="bread-blod">Customers</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="data-table-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset mg-t-30">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd">
                            <h1>Post Details</h1>

                        </div>
                    </div>
                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-10">
                                    <div class="all-form-element-inner">
                                        <form action="confirm_request_inventory.jsp" method="POST">
                                        <div class="form-group-inner">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Agent Name</label>
                                                </div>
                                                <div class="col-lg-9">
                                                    <div class="form-select-list">
                                                        <input type="text" class="form-control"  value='<%=agent_name%>' readonly=""/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group-inner">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Contact Number</label>
                                                </div>
                                                <div class="col-lg-9">
                                                    <div class="form-select-list">
                                                        <input type="text" class="form-control"  value='<%=agent_mobile%>' readonly=""/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group-inner">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Agent Email</label>
                                                </div>
                                                <div class="col-lg-9">
                                                    <div class="form-select-list">
                                                        <input type="text" class="form-control" value='<%=agent_email%>' readonly=""/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group-inner">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Crop Name</label>
                                                </div>
                                                <div class="col-lg-9">
                                                    <div class="form-select-list">
                                                        <input type="text" class="form-control"  value='<%=id%>' readonly=""/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                  
                                        <div class="form-group-inner">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <label class="login2 pull-right pull-right-pro">Quantity(KG)</label>
                                                </div>
                                                <div class="col-lg-9">
                                                    <input type="number" class="form-control"   name='quantity' id='quantity' required=""/>
                                                    <input type="hidden"  class="form-control"   name='id' id='id' value='<%=id%>'/>
                                                    <input type="hidden"  class="form-control"   name='agent_id' id='agent_id' value='<%=agent_id%>'/>
                                                </div>
                                            </div>
                                        </div>
                                       
                                            <div class="form-group-inner">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-left">
                                                                <button class="btn btn-white" type="reset">Cancel</button>
                                                                <input class="btn btn-sm btn-primary login-submit-cs" type="submit" value="Send Request">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>




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
<jsp:include page="footer.jsp"/>