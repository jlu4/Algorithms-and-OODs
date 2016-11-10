package PocketGem;

public class SortColor {
	 public void sortColors(int[] A) {
	        int redpt=0;
	        int bluept=A.length-1;
	        int RED=0;
	        int BLUE=2;
	        int i=0;
	       while(i<=bluept){
	            if(A[i]==RED){
	                swapColor(A, redpt, i);
	                redpt++;
	                i++;
	            }
	            else if(A[i] == BLUE){
	                swapColor(A, bluept,i);
	                bluept--;
	            }
	            else
	                i++;
	        }
		}
		private void swapColor(int[] A, int from, int to){
		    int temp = A[from];
		    A[from] = A[to];
		    A[to] = temp;
		}                                                                            
}
