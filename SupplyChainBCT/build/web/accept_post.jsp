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
        String id = request.getParameter("id");
        String email = session.getAttribute("email").toString();
        email=aes.encrypt(email);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeStamp = format.format(date);
        String sql = "UPDATE tbl_posts SET agent_id='" + email + "',accepted_on='" + aes.encrypt(timeStamp) + "',post_status='Accepted' WHERE post_id=" + id;
        DBConnection con = new DBConnection();
        int row_affected = con.update(sql);

        if (row_affected > 0) {
            //transfer the amount to the farmers account

            sql = "SELECT * FROM tbl_posts WHERE post_id=" + id;
            ResultSet rs = con.select(sql);
            if (rs.next()) {
                double quantity = rs.getDouble("crop_quantity");
                double rate = rs.getDouble("crop_rate");
                double amount = quantity * rate;
                String crop_name = rs.getString("crop_name");
                String farmer_id = rs.getString("farmer_id");
                rs.close();
                sql = "SELECT * FROM tbl_invertory_details WHERE agent_id='" + email + "' AND inventory_name='" + crop_name + "'";
                rs = con.select(sql);
                if (rs.next()) {
                    sql = "UPDATE tbl_invertory_details SET quantity=quantity+" + quantity + " WHERE agent_id='" + email + "' AND inventory_name='" + crop_name + "'";
                    row_affected = con.update(sql);
                    if (row_affected > 0) {
                        String args[] = {farmer_id, email, crop_name, "" + quantity, "" + rate, "" + amount};
                        blockchain.BlockChain.insertBlock(args);
                    }

                } else {
                    sql = "INSERT INTO tbl_invertory_details(agent_id,inventory_name,quantity,inventory_rate) VALUES('" + email + "','" + crop_name + "'," + quantity + "," + rate + ")";
                    row_affected = con.update(sql);
                    if (row_affected > 0) {
                        String args[] = {farmer_id, email, crop_name, "" + quantity, "" + rate, "" + amount};
                        blockchain.BlockChain.insertBlock(args);
                    }
                }
            }
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Post Accepted!');");
            out.println("location='view_farmer_posts.jsp';");
            out.println("</script>");
        } else {
            //dont transfer the amount as post not accpeted
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Post Not Accepted!');");
            out.println("location='view_farmer_posts.jsp';");
            out.println("</script>");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>