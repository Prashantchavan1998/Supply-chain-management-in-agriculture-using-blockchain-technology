/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visual;

/**
 *
 * @author admin
 */
public class Pixel 
{
    int m_maxFoil;

	int m_maxSubpixel;

	int m_color;

	IntMatrix m_cryptMatrix;

	int m_subPixel[][];

	public Pixel(int maxFoils, int maxSubpix) {
		m_maxFoil = maxFoils;
		m_maxSubpixel = maxSubpix;

		m_subPixel = new int[m_maxFoil][m_maxSubpixel];

	}
	public void setColor(int setColor) {
		m_color = setColor;
		return;
	}
	public int getColor() {
		return m_color;
	}


	public void setMatrix(IntMatrix newMatrix) {
		m_cryptMatrix = newMatrix;
		return;
	}


	public void computeSubpixels() {
		// foils
		for (int i = 0; i < m_maxFoil; i++) {// subpixel per foil
			for (int j = 0; j < m_maxSubpixel; j++) {
				m_subPixel[i][j] = m_cryptMatrix.getElement(i, j);
			}
		}
		return;
	}


	public int getSubpixel(int foilNr, int pixelNr) {
		return m_subPixel[foilNr][pixelNr];
	}


	public boolean isInSet(int[] set, int element){
		for(int i=0; i<set.length; i++){
			if(set[i]==element)return true;
		}
		return false;
	}
}
