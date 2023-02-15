<%@page import="visual.AES"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="pack.DBConnection"%>
<%
    try {
        AES aes=new AES();
        String email =aes.encrypt( session.getAttribute("email").toString());
        String id = aes.encrypt(request.getParameter("id"));
        String agent_id = request.getParameter("agent_id");
        String quantity=request.getParameter("quantity");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeStamp = aes.encrypt(simpleDateFormat.format(date));
        String sql = "INSERT INTO tbl_inventory_request(requested_id,receiver_id,inventory_name,request_on,request_quantity) VALUES("
                + "'" + email + "','" + agent_id + "','" + id + "','" + timeStamp + "',"+quantity+")";

        DBConnection con = new DBConnection();
        int row_affected = con.update(sql);

        if (row_affected > 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invetory Requested Successfully!');");
            out.println("location='view_agent_post.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invetory Request Failed!');");
            out.println("location='view_agent_post.jsp';");
            out.println("</script>");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>