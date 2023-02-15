/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pack.DBConnection;
import pack.DBConnection1;

/**
 *
 * @author Dinesh
 */
public class ValidatePass extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sn = request.getSession();
            String uname = sn.getAttribute("PRN").toString();
            AES aes = new AES();
            String pass = request.getParameter("shpass");
            DBConnection db = new DBConnection();
            DBConnection1 db1 = new DBConnection1();
            Connection con = db.con;
            Connection con1 = db1.con;
            Statement st = con.createStatement();
            Statement stn = con1.createStatement();

            ResultSet rs1 = st.executeQuery("select password from setpassword where UserName='" + uname + "'");
            String pass2 = "";
            if (rs1.next()) {
                //pass2 = aes.decrypt(rs1.getString(1));
                pass2 = rs1.getString(1);

            }

            if (pass.equals(pass2)) {
                
                st = con.createStatement();
                st.executeUpdate("delete from setpassword where UserName='" + uname + "'");
                stn.executeUpdate("delete from setpassword where UserName='" + uname + "'");
                out.println("<script>");
                out.println("alert('Login Success!')");
                out.println("location='dashboard.jsp'");
                out.println("</script>");
                
            } else {
                out.println("<script>");
                out.println("alert('Invalid Password!')");
                out.println("location='UserPage4.jsp'");
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
