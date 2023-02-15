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
import pack.DBConnection;
import pack.DBConnection1;
import pack.MailUtil;
import visual.AES;

/**
 *
 * @author Dinesh
 */
public class Registration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            AES aes = new AES();
            String name = aes.encrypt(request.getParameter("name"));
            String address = aes.encrypt(request.getParameter("address"));
            String email = aes.encrypt(request.getParameter("email"));
            String mobile = aes.encrypt(request.getParameter("mobile"));
            String password = aes.encrypt(request.getParameter("password"));
            String usertype = aes.encrypt(request.getParameter("utype"));
            DBConnection con = new DBConnection();
            DBConnection1 con1 = new DBConnection1();
            String sql = "SELECT * FROM tbl_users WHERE email='" + email + "'";
            ResultSet rs = con.select(sql);

            if (rs.next()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email Already in used!');");
                out.println("location='register.jsp';");
                out.println("</script>");
            } else {
                sql = "INSERT INTO tbl_users(usertype,email,password,address,mobile,name) VALUES('" + usertype + "','" + email + "','" + password + "','" + address + "','" + mobile + "','" + name + "')";
                int row_affected = con.update(sql);
                row_affected = con1.update(sql);
                if (row_affected > 0) {

                    //send password email to state government
                    String emails[] = {email};
                    String subject = "Account Details";
                    String msg = "Dear Bank Manager, \n Your account has been activated by Central Govt. \n"
                            + "Login Details as Username= " + aes.decrypt(email) + " \n password= " + aes.decrypt(password) + " \n Thank you.";
                    MailUtil mail = new MailUtil();
//                        Uncomment the below line to send account details on mail
//                        mail.sendMail(emails, emails, subject, msg);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Registration Success!');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Registration Failed!');");
                    out.println("location='register.jsp';");
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
