/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visual;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author admin
 */
public class CreateImage 
{
    String inpath;
    String outpath;
    String ms;
    public CreateImage(String in1,String out1,String m )
    {
        inpath=in1;
        outpath=out1;
        ms=m;
    }
    public boolean create()
    {
        boolean bool=true;
        try
        {
            BufferedImage image = ImageIO.read(new File(inpath));
            int h=image.getHeight();
            int w=image.getWidth();
		
            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
            Graphics2D g2d = img.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.setPaint(Color.red);
            g2d.setFont(new Font("Serif", Font.BOLD, 25));
            
            FontMetrics fm = g2d.getFontMetrics();
            int x = img.getWidth() - fm.stringWidth(ms) - 5;
            int y = fm.getHeight();
            g2d.setColor(Color.white);
            g2d.drawString(ms, x, y);
            g2d.dispose();
		
            //ImageIO.write(img, "png", new File(outpath));
            ImageIO.write(img, "jpg", new File(outpath));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            bool=false;
        }
        return bool;
    }
}
