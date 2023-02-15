/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visual;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
/**
 *
 * @author admin
 */
public class ImageShare extends JPanel
{
    Pixel m_pixels[][];
    Foil m_sourceFoil;
    int m_wSrc;
    int m_hSrc;
    final int WHITEPIXEL = (255 << 24) | (255 << 16) | (255 << 8) | 255;
    final int BLACKPIXEL = (255 << 24);
        
    final int THRESHOLD = 128;
    IntMatrix m_Cblack[], m_Cwhite[]; 
    Random m_rnd = new SecureRandom();
    ArrayList m_sequence;
    int m_maxPerm;
    Vector m_foils;

    int m_maxFoil;
        
    int m_hEnc;

    int m_wEnc;

    Permutation m_permutation;
    IntMatrix m_initMatrixC0, m_initMatrixC1;

    int m_maxSubpixel;
    

    public ImageShare(int height, int width, int maxFoil)
    {

        
        m_maxFoil = maxFoil;
	
	m_hSrc = height;
	m_wSrc = width;

        m_hEnc =  m_hSrc;
	m_wEnc =  m_wSrc;


	m_foils = new Vector();
	m_sequence = new ArrayList();
	m_maxSubpixel =4;
	m_permutation = this.getPermutationInstance();
	m_maxPerm = m_permutation.getTotal();

        m_initMatrixC0 = new IntMatrix(m_maxFoil, m_maxSubpixel);
        m_initMatrixC1 = new IntMatrix(m_maxFoil, m_maxSubpixel);
	m_initMatrixC0.setElement(0, 0, WHITEPIXEL);
	m_initMatrixC0.setElement(0, 1, WHITEPIXEL);
	m_initMatrixC0.setElement(0, 2, BLACKPIXEL);
	m_initMatrixC0.setElement(0, 3, BLACKPIXEL);
	m_initMatrixC0.setElement(1, 0, WHITEPIXEL);
	m_initMatrixC0.setElement(1, 1, WHITEPIXEL);
	m_initMatrixC0.setElement(1, 2, BLACKPIXEL);
	m_initMatrixC0.setElement(1, 3, BLACKPIXEL);

	m_initMatrixC1.setElement(0, 0, WHITEPIXEL);
	m_initMatrixC1.setElement(0, 1, WHITEPIXEL);
	m_initMatrixC1.setElement(0, 2, BLACKPIXEL);
	m_initMatrixC1.setElement(0, 3, BLACKPIXEL);
	m_initMatrixC1.setElement(1, 0, BLACKPIXEL);
	m_initMatrixC1.setElement(1, 1, BLACKPIXEL);
	m_initMatrixC1.setElement(1, 2, WHITEPIXEL);
	m_initMatrixC1.setElement(1, 3, WHITEPIXEL);	

    }

    public Permutation getPermutationInstance()
    {
	return new Permutation(m_maxSubpixel);
    }

    public boolean initEncrypt(Image newImage)
    {
	doPermutation();
	setImage(newImage);
	return true;
    }
    public void doPermutation()
    {
	m_Cwhite = new IntMatrix[m_maxPerm];
	m_Cblack = new IntMatrix[m_maxPerm];

	int[][] orderArray = m_permutation.getPermArray();
	Vector c0 = m_initMatrixC0.getPermMatrixVector(orderArray);
	Vector c1 = m_initMatrixC1.getPermMatrixVector(orderArray);
	for(int i=0; i<m_maxPerm; i++)
        {
            m_Cwhite[i] = (IntMatrix)c0.get(i);
            m_Cblack[i] = (IntMatrix)c1.get(i);
	}
    }

    public void setImage(Image img)
    {
        int[] tempPix = new int[m_hSrc * m_wSrc];
        grabImage(img, tempPix,m_wSrc,m_hSrc);
        m_pixels = new Pixel[m_hSrc][m_wSrc];
       
        m_sequence = new ArrayList();

        System.out.println("h and w"+m_hSrc+" : "+m_wSrc);
        System.out.println(" " +m_maxFoil+" : "+ m_maxSubpixel);

        
	for (int y = 0; y < m_hSrc; y++)
        {
            for (int x = 0; x < m_wSrc; x++)
            {
                m_pixels[y][x] = new Pixel(m_maxFoil, m_maxSubpixel);
            }
	}
        m_sourceFoil = new Foil(tempPix,m_wSrc,m_hSrc);
    }


    public void grabImage(Image newImage, int[] tempPix, int width, int height)
    {
        System.out.println("   Encryptor: grabbing image");
        PixelGrabber pixelGrabber = new PixelGrabber(newImage, 0, 0, width, height,tempPix, 0, width);
	try
        {
        
            pixelGrabber.grabPixels();
	 }
         catch (InterruptedException e)
         {
            System.err.println("   Error: interrupted waiting for pixels");
	 }
	 if ((pixelGrabber.getStatus() & ImageObserver.ABORT) != 0)
         {
            System.err.println("   Error: image fetch aborted or errored");
	 }

    }

    public void encrypt()
     {
//		System.out.println("   Encryptor: encrypting");
		int[] tempPix = m_sourceFoil.getGrabbedImage();
		setMatrixToPixel(tempPix);
		computeSubpixel();
		m_foils.clear();
		m_foils = getEncryptedFoils();
                System.out.println("m_foils :"+m_foils.size());
	}
    


            public void setMatrixToPixel(int[] tempPix){
		// store grabbed image for encryption
//		System.out.println("   Encryptor: setting matrix to each Pixel");
		for (int y = 0; y < m_hSrc; y++)
                {
			for (int x = 0; x < m_wSrc; x++)
                        {
				int pixel = tempPix[y + x * m_hSrc];
                                m_pixels[y][x].setColor(pixel);

                                /*int pixel = tempPix[x + y * m_hSrc];
                                m_pixels[x][y].setColor(pixel);*/

				int red   = (pixel >> 16) & 0xff;
		        int green = (pixel >>  8) & 0xff;
		        int blue  = (pixel      ) & 0xff;
		        int factor = (int) (red * 0.299 + green * 0.587 + blue * 0.114);

				if (factor >THRESHOLD) {
					//m_pixels[x][y].setMatrix(m_Cwhite[this.getRandom()]);
                                        m_pixels[y][x].setMatrix(m_Cwhite[this.getRandom()]);
				} else {
					//m_pixels[x][y].setMatrix(m_Cblack[this.getRandom()]);
                                        m_pixels[y][x].setMatrix(m_Cblack[this.getRandom()]);
				}
			}
		}//end for - store
	}//end prepareMatrix


            public void computeSubpixel(){
		for (int y = 0; y < m_hSrc; y++) {
			for (int x = 0; x < m_wSrc; x++) {
				m_pixels[y][x].computeSubpixels();
                                //m_pixels[x][y].computeSubpixels();
			}
		}
	}

            public int getRandom() {
		int random = java.lang.Math.abs(m_rnd.nextInt()) % m_maxPerm;
		m_sequence.add(new Integer(random));
		return random;
	}

        private Vector getEncryptedFoils()
        {
            Vector v = new Vector();
		for(int i = 0; i<m_maxFoil; i++){
			v.add(i,getFoil(i));
		}
		return v;
	}

        public Foil getFoil(int foilNr){
		int index;
		int tempPix[] = new int[m_hEnc * m_wEnc]; // array for grabbing pic

		// copy encrypted pic to array
		for (int y = 0; y < m_hSrc; y++) {
			for (int x = 0; x < m_wSrc; x++)
                        {
				/*index = 2 * (x + y * m_hEnc);

				tempPix[index] = m_pixels[x][y].getSubpixel(foilNr, 0);
				tempPix[index + 1] = m_pixels[x][y].getSubpixel(foilNr, 1);
				tempPix[index + m_hEnc] = m_pixels[x][y].getSubpixel(foilNr, 2);
				tempPix[index + m_hEnc + 1] = m_pixels[x][y].getSubpixel(foilNr, 3);*/
                                
                                //index = 2 * (y + x * m_hEnc);
                                index = (y + x * m_hEnc);
                                tempPix[index] = m_pixels[y][x].getSubpixel(foilNr, 0);
				/*tempPix[index + 1] = m_pixels[y][x].getSubpixel(foilNr, 1);

                                tempPix[m_hEnc] = m_pixels[y][x].getSubpixel(foilNr, 2);
				tempPix[m_hEnc + 1] = m_pixels[y][x].getSubpixel(foilNr, 3);
                            */
				/*tempPix[index + m_hEnc] = m_pixels[y][x].getSubpixel(foilNr, 2);
				tempPix[index + m_hEnc + 1] = m_pixels[y][x].getSubpixel(foilNr, 3);*/
			}
		}

		//System.out.println("foilNr: "+foilNr);
		return new Foil(tempPix,m_wEnc, m_hEnc);
	}

        public void storeImage(String path)
        {
            try
            {
                Foil result = null;

                Foil foil = (Foil)m_foils.get(0);
                
                if(result==null)
                {
                    System.out.println("result is null");

                    result = foil;

                    Foil m_resultFoil = result;

                    ImageProducer imgpro=m_resultFoil.getImage();
                    imgpro=m_resultFoil.getImage();
                    Image iim=createImage(imgpro);
               
                    int h1=iim.getHeight(null);
                    int w1=iim.getWidth(null);
                    BufferedImage bi1=new BufferedImage(w1,h1,10);
                    Graphics g = bi1.createGraphics();
                    g.drawImage(iim, 0, 0, null);
                    g.dispose();
				
		    ImageIO.write(bi1,"jpg",new File(path+"sh0.jpg"));
		}
                else
                {
                    System.out.println("result is not null");
                    result = result.computeOverlayOfTwoFoils(foil);
                    Foil m_resultFoil = result;
                    ImageProducer imgpro=m_resultFoil.getImage();
                    imgpro=m_resultFoil.getImage();
                    Image iim=createImage(imgpro);
                
		}
                
                Foil result1=null;
                Foil foil1 = (Foil)m_foils.get(1);
               
                if(result1==null)
                {
                    System.out.println("result is null");

                    result1 = foil1;

                    Foil m_resultFoil1 = result1;

                    ImageProducer imgpro1=m_resultFoil1.getImage();
                    imgpro1=m_resultFoil1.getImage();
                    Image iim=createImage(imgpro1);
                
                  int h1=iim.getHeight(null);
                    int w1=iim.getWidth(null);
                    BufferedImage bi1=new BufferedImage(w1,h1,10);
                    Graphics g = bi1.createGraphics();
                    g.drawImage(iim, 0, 0, null);
                    g.dispose();
				
		    ImageIO.write(bi1,"jpg",new File(path+"sh1.jpg"));
		}
                else
                {
                    System.out.println("result is not null");
                    result1 = result1.computeOverlayOfTwoFoils(foil1);

                    Foil m_resultFoil1 = result1;

                ImageProducer imgpro1=m_resultFoil1.getImage();
                    imgpro1=m_resultFoil1.getImage();
                Image iim=createImage(imgpro1);
               
		}
                

            if(result==null)
            {
                System.out.println("null result");

		result = new Foil(new int[m_wEnc*m_hEnc], m_wEnc, m_hEnc);
	     }
		
        }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
}
