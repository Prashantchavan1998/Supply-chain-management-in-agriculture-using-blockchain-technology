/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

/**
 *
 * @author Dinesh
 */
public class CombineShare extends HttpServlet {

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
             HttpSession sn=request.getSession();
            String uname=sn.getAttribute("PRN").toString();
            String share0 = sn.getAttribute("ShareImage").toString();
            ServletContext sc = this.getServletContext();
            String sg=sc.getRealPath("/");
            String path=sg.substring(0,sg.indexOf("build"));
            //String fp=path+"web\\image\\"+uname;
            String sh1=path+"web\\shares\\"+share0;
            String sh2=path+"web\\password\\"+uname+"sh1.jpg";
            
            ImageIcon ic1=new ImageIcon(sh1);
            Image img1=ic1.getImage();
            int h2=ic1.getIconHeight();
            int w2=ic1.getIconWidth();

            ImageIcon ic2=new ImageIcon(sh2);
            Image img2=ic2.getImage();
            int h3=ic2.getIconHeight();
            int w3=ic2.getIconWidth();
            
            int pel2[]=new int[h2*w2];
            int pel3[]=new int[h2*w2];
            
            PixelGrabber pg2 = new PixelGrabber(img1,0,0,w2,h2,pel2,0,w2);
            pg2.grabPixels();

            PixelGrabber pg3 = new PixelGrabber(img2,0,0,w3,h3,pel3,0,w3);
            pg3.grabPixels();

             int newpix[]=new int[h2*w2];
            int g1=0;
            for(int i=0;i<pel2.length;i++)
            {
               //newpix8[g1] =pc2[i] | pc1[i];
                newpix[g1] =pel2[i] & pel3[i];
               g1++;
            }

            ImageProducer ip6= new MemoryImageSource(w2, h2, newpix, 0, w2);
            Image iim=Toolkit.getDefaultToolkit().createImage(ip6);
            
            //int h1=iim.getHeight(null);
            //int w1=iim.getWidth(null);
            BufferedImage bi1=new BufferedImage(w2,h2,10);
            Graphics g = bi1.createGraphics();
            g.drawImage(iim, 0, 0, null);
            g.dispose();
				
            
            String fp=path+"web\\password\\"+uname;
                
            ImageIO.write(bi1,"jpg",new File(fp+"cs.jpg"));
            response.sendRedirect("UserPage4.jsp");
        } catch (Exception e) {
            e.printStackTrace();
              response.sendRedirect("UserPage2.jsp");
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
