/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import pack.DBConnection;
import pack.DBConnection1;
/**
 *
 * @author Dinesh
 */
public class AddImageDt extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public final void image(String mail, String img) {
        // SMTP info
        String host1 = "smtp.gmail.com";
        String port1 = "587";
        String mailFrom = "testingmyproject12@gmail.com";
        String password = "Testingmyproject@123";
        ServletContext sc = this.getServletContext();
        System.out.println("sc " + sc.getContextPath());
        System.out.println("rl " + sc.getRealPath("/"));

        String sg = sc.getRealPath("/");
        String path = sg.substring(0, sg.indexOf("build"));
        path = path + "web\\password\\";
        // message info
        String fpath = path + img;
        String mailTo = mail;
        String subject = "Message from VC_System";
        StringBuffer body = new StringBuffer("<html>This message contains share image.<br>");

        body.append("<img src=\"cid:image1\" width=\"30%\" height=\"30%\" /><br>");

        //  body.append(msg);
        body.append("</html>");

        // inline images
        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("image1", fpath);
        try {
            EmbeddedImageEmailUtil.send(host1, port1, mailFrom, password, mailTo, subject, body.toString(), inlineImages);
            System.out.println("image sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            
            HttpSession session = request.getSession();
            AES aes=new AES();
            String prn = session.getAttribute("PRN").toString();
            String clr = "Red";
            ArrayList at = new ArrayList();
            DBConnection db=new DBConnection();
            DBConnection1 db1=new DBConnection1();
            Connection con = db.con;
            Connection con1 = db1.con;
            Statement st = con.createStatement();
            Statement stn = con1.createStatement();
            ResultSet rs = st.executeQuery("select password from setpassword");
            
            while (rs.next()) {
                at.add(rs.getString(1));
            }

            String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz";
            Random random = new Random();

            StringBuilder b = new StringBuilder();

            for (int g = 0; g < 5; g++) {
                b.append(base.charAt(random.nextInt(base.length())));
            }

            while (at.contains(b.toString())) {
                b = new StringBuilder();

                for (int g = 0; g < 5; g++) {
                    b.append(base.charAt(random.nextInt(base.length())));
                }
            }
            String pass = b.toString();

            System.out.println("pasword " + pass);
            ServletContext sc = this.getServletContext();
            System.out.println("sc " + sc.getContextPath());
            System.out.println("rl " + sc.getRealPath("/"));

            String sg = sc.getRealPath("/");
            String path = sg.substring(0, sg.indexOf("build"));
            path = path + "web\\password\\";

            //String fn=pass+".png";
            String fn = pass + ".jpg";
            String fpath = path + fn;

            String in1 = "";
            String out1 = fpath;
            String ms = pass;
            int index = random.nextInt(3);
            String color[] = {"Red", "Black", "Blue"};
            clr = color[index];
            if (clr.equals("Red")) {
                in1 = path + "red.png";
            }

            if (clr.equals("Black")) {
                in1 = path + "black.png";
            }

            if (clr.equals("Blue")) {
                in1 = path + "blue.png";
            }

            CreateImage ci = new CreateImage(in1, out1, ms);
            boolean bool = ci.create();

            if (bool) {
                rs.close();
                rs = st.executeQuery("SELECT * FROM setpassword WHERE UserName='" + prn + "'");
                if (rs.next()) {
                    String filename = aes.decrypt(rs.getString("imageName"));
                    File file = new File(path + filename);
                    file.delete();
                    st.executeUpdate("DELETE FROM setpassword WHERE UserName='" + prn + "'");
                    stn.executeUpdate("DELETE FROM setpassword WHERE UserName='" + prn + "'");
                  
                }

                //insert password details in database
               // st.executeUpdate("insert into setpassword values('" + prn + "','" +aes.encrypt(fn) + "','" + aes.encrypt(pass) + "')");
                st.executeUpdate("insert into setpassword values('" + prn + "','" +aes.encrypt(fn) + "','" + pass + "')");
                stn.executeUpdate("insert into setpassword values('" + prn + "','" +aes.encrypt(fn) + "','" + pass + "')");
                String fp = path + fn;

                //create share 1 and share 2
                ImageIcon ic1 = new ImageIcon(fp);
                Image img = ic1.getImage();
                int h = img.getHeight(null);
                int w = img.getWidth(null);

                String fp2 = path + prn;

                //save share 1 and share 2 using prn number
                ImageShare enc = new ImageShare(h, w, 2);
                bool = enc.initEncrypt(img);
                System.out.println("bool : " + bool);
                enc.encrypt();
                enc.storeImage(fp2);

                rs.close();
                Statement st1 = con.createStatement();
                String sql = "SELECT * FROM tbl_users WHERE sr='" + prn + "'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    sql = "SELECT * FROM setpassword WHERE UserName='" + prn + "'";

                    String email = aes.decrypt(rs.getString("email"));
                    String imgname = prn + "sh0.jpg";
                    //send share 1 to user 
                    image(email, imgname);
                    session.setAttribute("UserPass", pass);
                    session.setAttribute("UserPassImg", fn);
                    response.sendRedirect("UserPage11.jsp");
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