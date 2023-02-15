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
    String email = session.getAttribute("email").toString();
    AES aes=new AES();
    DBConnection db = new DBConnection();
    String sql = "SELECT * FROM tbl_bank_details WHERE user_id='" + aes.encrypt(email) + "'";
    ResultSet rs = db.select(sql);
    

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
                                <li><span class="bread-blod">Bank Details</span>
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
                <div class="sparkline13-list shadow-reset">
                    <div class="sparkline13-hd">
                        <div class="main-sparkline13-hd">

                            <h1> <span class="table-project-n">Bank Details</span> </h1>

                        </div>

                    </div>
                    <div class="sparkline13-graph">
                        <div class="pull-left">
                            <%if (rs.next()) {
                                } else {%>

                            <form action="addbank.jsp" method="POST">
                                <button class="btn btn-sm btn-primary login-submit-cs"  type="submit">Add Bank Details</button>
                            </form>
                            <%
                                }
                            %>
                        </div>
                        <div class="datatable-dashv1-list custom-datatable-overright">

                            <table id="table" data-toggle="table" data-pagination="true" data-search="true" data-show-columns="false" data-show-pagination-switch="false" data-show-refresh="false" data-key-events="false" data-show-toggle="false" data-resizable="false" data-cookie="true" data-cookie-id-table="saveId" data-show-export="false" data-click-to-select="false" data-toolbar="#toolbar">
                                <thead>
                                    <tr>

                                        <th data-field="id">ID</th>
                                        <th data-field="name">Bank Name</th>
                                        <th data-field="rate">Account No</th>
                                        <th data-field="quantity">IFSC</th>
                                        <th data-field="amount">Bank Address</th>
                                        <th data-field="postedon">Balance</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 1;
                                        rs.close();
                                        rs=db.select(sql);
                                        if (rs.next()) {

                                    %>
                                    <tr>

                                        <td><%=i%></td>
                                        <td><%=aes.decrypt(rs.getString("bank_name"))%></td>
                                        <td><%=aes.decrypt(rs.getString("account_no"))%></td>
                                        <td><%=aes.decrypt(rs.getString("ifsc_code"))%></td>
                                        <td><%=aes.decrypt(rs.getString("bank_address"))%></td>
                                        <td><%=rs.getString("balance")%></td>
                                       

                                    </tr>
                                    <%
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>