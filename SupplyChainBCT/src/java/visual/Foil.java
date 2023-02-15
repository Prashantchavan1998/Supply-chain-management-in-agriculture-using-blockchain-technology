/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visual;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
/**
 *
 * @author admin
 */
public class Foil 
{
    int[] m_imageData;

    int m_width;

    int m_height;

    Foil(int[] tempPix, int w, int h)
    {
    	m_width = w;
	m_height = h;
	m_imageData = tempPix;
    }

    public int[] getGrabbedImage()
    {
	return m_imageData;
    }

    public ImageProducer getImage()
    {
         System.out.println("image data : "+m_imageData.length);

          ImageProducer ip= new MemoryImageSource(m_width, m_height, m_imageData, 0, m_width);
          
          // now modified
        //ImageProducer ip= new MemoryImageSource(m_height, m_width, m_imageData, 0, m_width);


         //Image im=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(m_width, m_height, m_imageData, 0, m_width));

          return ip;
           // return new MemoryImageSource(m_width, m_height, m_imageData, 0, m_width);
    }

    public Foil computeOverlayOfTwoFoils(Foil foil)
    {
    	int[] pic1 = foil.getGrabbedImage();
	int[] pic2 = this.getGrabbedImage();
	int[] result = new int[this.m_imageData.length];
	for(int i=0; i<pic1.length; i++)
        {
            result[i] = (~pic1[i])|(~pic2[i]);
            result[i] = (~result[i]);
	}
	return new Foil(result, this.getWidth(), this.getHeight());
    }

    public Foil computeOverlayOfFoils(Foil foil, boolean[] set)
    {
	int[] pic1 = foil.getGrabbedImage();
	int[] pic2 = this.getGrabbedImage();
	int[] result = new int[this.m_imageData.length];

	if(set[0]&&set[1])
        {
            for(int i=0; i<pic1.length; i++)
            {
		result[i] = (~pic1[i])|(~pic2[i]);
		result[i] = (~result[i]);
            }
	}
        else if(set[0] && !set[1])
        {
            result = pic1;
	}
        else if(!set[0] && set[1])
        {
            result = pic2;
	}

	return new Foil(result, this.getWidth(), this.getHeight());
    }


	public int getHeight()
        {
		return m_height;
	}

	public int getWidth()
        {
		return m_width;
	}

	public String toString()
        {
            StringBuffer sb = new StringBuffer();

            for(int i=0; i<m_imageData.length; i++)
            {
            	sb.append(m_imageData[i]+" ");
            }
            return sb.toString();
	}
}
