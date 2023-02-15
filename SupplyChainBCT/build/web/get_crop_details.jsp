<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="pack.DBConnection"%>
<%
    try {
        String id = request.getParameter("id");
        String sql = "SELECT * FROM tbl_crop_details WHERE crop_name='" + id + "'";
        DBConnection con = new DBConnection();
        ResultSet rs = con.select(sql);
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        if (rs.next()) {
            json.put("crop_rate", rs.getString("crop_rate"));
        }
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
%>