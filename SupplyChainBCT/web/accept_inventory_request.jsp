<%@page import="visual.AES"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="pack.DBConnection"%>
<%
    try {
        String id = request.getParameter("id");
        AES aes = new AES();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeStamp = format.format(date);
        String sql = "SELECT * FROM tbl_inventory_request WHERE idtbl_inventory_request=" + id;
        DBConnection db = new DBConnection();

        ResultSet rs = db.select(sql);
        if (rs.next()) {
            String request_id = rs.getString("requested_id");
            String receiver_id = rs.getString("receiver_id");
            double quantity = rs.getDouble("request_quantity");
            String crop_name = rs.getString("inventory_name");
            sql = "UPDATE tbl_invertory_details SET quantity=quantity+" + quantity + " WHERE inventory_name='" + crop_name + "' AND agent_id='" + request_id + "'";
            int row_affected = db.update(sql);
            if (row_affected > 0) {
                sql = "UPDATE tbl_invertory_details SET quantity=quantity-" + quantity + " WHERE inventory_name='" + crop_name + "' AND agent_id='" + receiver_id + "'";
                row_affected = db.update(sql);
                if (row_affected > 0) {
                    sql = "UPDATE tbl_inventory_request SET request_status='Completed' WHERE idtbl_inventory_request=" + id;
                    row_affected = db.update(sql);
                    if (row_affected > 0) {
                        rs.close();
                        sql = "SELECT * FROM tbl_crop_details WHERE crop_name='" + aes.decrypt(crop_name) + "'";
                        rs = db.select(sql);
                        double rate = 0;
                        if (rs.next()) {
                            rate = rs.getDouble("crop_rate");
                            double rate_new = (rate / 100) * 5;
                            rate_new = rate + rate_new;
                            double amount = quantity * rate_new;
                            String data[] = {receiver_id, request_id, crop_name, "" + quantity, "" + rate_new, "" + amount};
                            boolean falg = blockchain.BlockChain.insertBlock(data);
                            if (falg) {
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Inventory Transfer!');");
                                out.println("location='agent_request_inventory.jsp';");
                                out.println("</script>");
                            } else {
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Inventory failed to Transfer!');");
                                out.println("location='agent_request_inventory.jsp';");
                                out.println("</script>");
                            }
                        }
                    }
                }
            }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Inventory failed to Transfer!');");
            out.println("location='agent_request_inventory.jsp';");
            out.println("</script>");
        }
        //dont transfer the amount as post not accpeted

    } catch (Exception e) {
        e.printStackTrace();
    }
%>