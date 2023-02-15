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
import visual.AES;

/**
 *
 * @author Dinesh
 */
public class Login extends HttpServlet {

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
            AES aes = new AES();
            String username = request.getParameter("email");
            String password = request.getParameter("password");
            String utype = request.getParameter("utype");

            if (username.equals("admin@gmail.com") && password.equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("usertype", "admin");
                session.setAttribute("email", username);
                session.setAttribute("name", "Admin");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login Success!');");
                out.println("location='admin_home.jsp';");
                out.println("</script>");
            } else {
                String sql = "SELECT * FROM tbl_users WHERE email='" + aes.encrypt(username) + "' and password='" + aes.encrypt(password) + "' and usertype='" + aes.encrypt(utype) + "'";

                DBConnection con = new DBConnection();
                ResultSet rs = con.select(sql);

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usertype", utype);
                    session.setAttribute("email", username);
                    session.setAttribute("name", aes.decrypt(rs.getString("name")));
                    session.setAttribute("mobile", aes.decrypt(rs.getString("mobile")));
                    session.setAttribute("PRN", rs.getString("sr"));
                    out.println("<script type=\"text/javascript\">");
                    out.println("location='AddImageDt';");
                    out.println("</script>");
//                    out.println("<script type=\"text/javascript\">");
//                    out.println("alert('Login Success!');");
//                    out.println("location='dashboard.jsp';");
//                    out.println("</script>");

                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Login Failed!');");
                    out.println("location='index.html';");
                    out.println("</script>");

                }
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
