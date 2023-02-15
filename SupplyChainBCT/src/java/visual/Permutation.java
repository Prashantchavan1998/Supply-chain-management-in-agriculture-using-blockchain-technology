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
public class Permutation 
{
    private int[] a;
	  private int numLeft;
	  private int total;


	  public Permutation (int n) {
	    if (n < 1 || n>9) {
	      throw new IllegalArgumentException ("Min 1, max 9");
	    }
	    a = new int[n];
	    total = getFactorial (n);
	    reset ();
	  }


	  public int[][] getPermArray(){
	  	this.reset();
	  	int[][] result = new int[this.getTotal()][a.length];
	  	for(int i=0; i<result.length; i++){
	  		int[] temp = this.getNext();
	  		for(int j=0; j<a.length; j++){
	  			result[i][j]=temp[j];
	  		}
	  	}
	  	return result;
	  }

	  public void reset () {
	    for (int i = 0; i < a.length; i++) {
	      a[i] = i;
	    }
	    numLeft = total;//new BigInteger (total.toString ());
	  }

	  public int getNumLeft () {
	    return numLeft;
	  }


	  public int getTotal () {
	    return total;
	  }


	  public boolean hasMore () {
	    return (numLeft!=0);
	  }


	  private static int getFactorial (int n) {
	    int fact = 1;
	    for (int i = n; i > 1; i--) {
	      fact = fact*(i);
	    }
	    return fact;
	  }


	  public int[] getNext () {

	    if (numLeft==total) {
	      numLeft = numLeft-1;
	      return a;
	    }
	    int temp;

	    // Find largest index j with a[j] < a[j+1]
	    int j = a.length - 2;
	    //while (a[j].intValue() > a[j+1].intValue()){
	    while (a[j] > a[j+1]) {
	      j--;
	    }

	    // Find index k such that a[k] is smallest integer
	    // greater than a[j] to the right of a[j]
	    int k = a.length - 1;
	    while (a[j] > a[k]) {
	      k--;
	    }


	    temp = a[k];
	    a[k] = a[j];
	    a[j] = temp;

	    // Put tail end of permutation after jth position in increasing order
	    int r = a.length - 1;
	    int s = j + 1;

	    while (r > s) {
	      temp = a[s];
	      a[s] = a[r];
	      a[r] = temp;
	      r--;
	      s++;
	    }

	    numLeft = numLeft-1;
	    return a;
	  }
}
