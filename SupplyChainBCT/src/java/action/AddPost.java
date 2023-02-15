/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pack.DBConnection;
import pack.DBConnection1;
import visual.AES;

/**
 *
 * @author PhoenixZone
 */
public class AddPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            DBConnection db = new DBConnection();
            DBConnection1 db1 = new DBConnection1();
            AES aes=new AES();
            String crop_name = aes.encrypt(request.getParameter("crop_name"));
            String crop_rate = request.getParameter("crop_rate");
            String quantity = request.getParameter("quantity");
            String total_amount = request.getParameter("total_amount");

            HttpSession session = request.getSession(true);
            String farmer_email = aes.encrypt(session.getAttribute("email").toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String timeStamp = aes.encrypt(simpleDateFormat.format(date));
            String sql = "INSERT INTO tbl_posts(farmer_id,crop_name,crop_rate,crop_quantity,total_amount,posted_on)"
                    + " VALUES('" + farmer_email + "','" + crop_name + "'," + crop_rate + "," + quantity + "," + total_amount + ",'" + timeStamp + "')";
            int row_affected = db.update(sql);
            row_affected = db1.update(sql);
            if (row_affected > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Post Added Successfully!');");
                out.println("location='farmer_posts.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Post Fialed !');");
                out.println("location='newpost.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
