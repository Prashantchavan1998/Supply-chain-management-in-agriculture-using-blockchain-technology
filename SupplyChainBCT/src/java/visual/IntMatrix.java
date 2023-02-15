/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visual;
import java.util.Vector;
import java.util.Enumeration;
/**
 *
 * @author admin
 */
public class IntMatrix 
{
    int m_n, m_m;

    int m_matrix[][]; // matrix values


    public IntMatrix(int rows, int columns)
    {
	m_n = rows;
	m_m = columns;
	m_matrix = new int[m_n][m_m];
    }


    public IntMatrix(IntMatrix mat)
    {
	int i, j;
	m_n = mat.getRows();
	m_m = mat.getColumns();
	m_matrix = new int[m_n][m_m];
	for (i = 0; i < m_n; i++)
        {
            for (j = 0; j < m_m; j++)
            {
		m_matrix[i][j] = mat.getElement(i, j);
            }
	}
    }

    public int getRows()
    {
	return m_n;
    }


    public int getColumns()
    {
	return m_m;
    }

    public int getElement(int row, int column)
    {
        return m_matrix[row][column];
    }

    public void setElement(int row, int column, int value)
    {
	//System.out.println(" ---> "+column);
	m_matrix[row][column] = value;
	return;
    }


    public void setElements(int[][] src)
    {
	for(int i=0; i<src.length; i++)
        {
            for(int j=0; j<src[i].length; j++)
            {
		this.setElement(i,j,src[i][j]);
            }
	}
    }

	public String toString()
        {
		StringBuffer result = new StringBuffer();
		int i, j;
		for (i = 0; i < m_n; i++)
                {//rows
			for (j = 0; j < m_m; j++)
                        {//columns
				result.append((m_matrix[i][j] + "\t"));
			}
			result.append("\n");
		}
		return result.toString();
	}


	public void exchangeRows(int row1, int row2) {
		int i;
		int temp;
		for (i = 0; i < m_m; i++) {
			temp = m_matrix[row1][i];
			m_matrix[row1][i] = m_matrix[row2][i];
			m_matrix[row2][i] = temp;
		}
		return;
	}

	public void exchangeColumns(int col1, int col2)
        {
		int i;
		int temp;
		for (i = 0; i < m_n; i++) {
			temp = m_matrix[i][col1];
			m_matrix[i][col1] = m_matrix[i][col2];
			m_matrix[i][col2] = temp;
		}
	}

	public int[] getColumn (int nrColumn)
        {
		int[] result = new int[this.getRows()];
		for(int i=0; i<result.length; i++){
			result[i]= this.getElement(i,nrColumn);
		}
		return result;
	}

	public int[] getRow (int row){
		return m_matrix[row];
	}

	public IntMatrix reorderColumns(int[] order){
		int[][] result = new int[order.length][];
		for(int i=0; i<order.length; i++){
			result[i] = this.getColumn(order[i]);
		}
		result = this.transposeMatrix(result);

		IntMatrix temp = new IntMatrix(result.length, result[0].length);
		temp.setElements(result);
		return temp;
	}

	public int[][] transposeMatrix(int[][] src){
		int[][] res = new int[src[0].length][src.length];
		for(int i=0; i<src.length; i++){
			for(int j=0; j<src[i].length; j++){
				res[j][i]=src[i][j];
			}
		}
		return res;
	}

	public Vector getPermMatrixVector(Vector v){
		Vector result = new Vector();
		Enumeration en = v.elements();
		while(en.hasMoreElements()){
			int[] order = (int[])en.nextElement();
			result.add(this.reorderColumns(order));
		}
		return result;
	}

	public Vector getPermMatrixVector(int[][] orderArray){
		Vector result = new Vector();
		//get the int[] for permutation
		for(int i=0; i<orderArray.length; i++){
				result.add(this.reorderColumns(orderArray[i]));
		}

		return result;
	}
}
