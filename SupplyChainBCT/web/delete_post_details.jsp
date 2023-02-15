<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="pack.DBConnection"%>
<%
    try {
        String id = request.getParameter("id");
        String sql = "delete from tbl_posts WHERE post_id='" + id + "'";
        DBConnection con = new DBConnection();
        int row_affected = con.update(sql);

        if (row_affected > 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Post Deleted!');");
            out.println("location='farmer_posts.jsp';");
            out.println("</script>");
        } else {
           out.println("<script type=\"text/javascript\">");
            out.println("alert('Post Deleting Failed!');");
            out.println("location='farmer_posts.jsp';");
            out.println("</script>");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>