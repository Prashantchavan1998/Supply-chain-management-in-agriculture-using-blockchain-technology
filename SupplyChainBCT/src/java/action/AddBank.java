/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
public class AddBank extends HttpServlet {

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
            AES aes=new AES();
            HttpSession session=request.getSession();
            String user_id=aes.encrypt(session.getAttribute("email").toString());
            String bank_name = aes.encrypt(request.getParameter("bank_name"));
            String office_address = aes.encrypt(request.getParameter("address"));
            String bank_account = aes.encrypt(request.getParameter("account_no"));
            String bank_ifsc = aes.encrypt(request.getParameter("ifsc"));
          
            DBConnection con = new DBConnection();
            DBConnection1 con1 = new DBConnection1();
            String sql = "SELECT * FROM tbl_bank_details WHERE user_id='" + user_id + "'";
            ResultSet rs = con.select(sql);
            String id = request.getParameter("productId");
//            if (id.equals("")) {
                if (rs.next()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Bank Details Already Added!');");
                    out.println("location='banks.jsp';");
                    out.println("</script>");
                } else {
                    sql = "INSERT INTO tbl_bank_details(user_id,bank_name,account_no,ifsc_code,bank_address) VALUES('"+user_id+"','" + bank_name + "','" + bank_account+ "','" + bank_ifsc + "','" +office_address + "')";
                    int row_affected = con.update(sql);
                    row_affected = con1.update(sql);
                    if (row_affected > 0) {

                    
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Bank Added Successfully!');");
                        out.println("location='bank_details.jsp';");
                        out.println("</script>");
                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Bank Not Added!');");
                        out.println("location='bank_details.jsp';");
                        out.println("</script>");
                    }
                }
            

            con.close();

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
