import java.util.Arrays;

public class Four_Integer {
	    public static int[] FourInteger(int A, int B, int C, int D) {
	        int[] rvalue = new int[4];
	        rvalue[0] = A;
	        rvalue[1] = B;
	        rvalue[2] = C;
	        rvalue[3] = D;
	        Arrays.sort(rvalue);   //remember
	        swap(rvalue, 0, 1);
	        swap(rvalue, 2, 3);
	        swap(rvalue, 0, 3);
	        return rvalue;
	    }
	    private static void swap(int[] array, int i, int j) {
	        array[i] ^= array[j];
	        array[j] ^= array[i];
	        array[i] ^= array[j];
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int[] res = FourInteger(a, b, c, d);
        for(int n : res) {
        	System.out.println(n);
        }
	}

}
