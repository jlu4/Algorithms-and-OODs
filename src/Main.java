import java.util.*;    
public class Main {
    public static void main(String[] args) {  
       Scanner sc=new Scanner(System.in);
       System.out.println("输入一个正整数T(1 <= T <= 10)");
       int a=sc.nextInt();
       String[] buff=new String[a*2];
       int i=0;
       sc.nextLine(); 
       System.out.println("请输入测试数据：");
       while(true)
       {
           buff[i]=sc.nextLine();
           i+=1;
           if(a*2==i) break;
       }
       System.out.println("Output");
       for(int j=0;j<a;j++)
       {
           System.out.println(GetMinExpenses(buff[j*2],buff[j*2+1]));
       }


    }  

       public static int DEL = -1;  

       public static int ORIGAL = 0;  

       public static int ADD = 1;  

       public static int getAddCount(int f, int type) {  
           int minCost;  
           if (type == ADD) {  
               minCost = f + 1;  
           } else {  
               minCost = f + 3;  
           }  
           return minCost;  
       }  

       public static int getDelCount(int f, int type) {  
           int minCost = 0;  
           if (type == DEL) {  
               minCost = f + 0;  
           } else {  
               minCost = f + 2;  
           }  
           return minCost;  
       }  

       public static int getMin(int a, int b) {  
           return a < b ? a : b;  
       }  

       public static int GetMinExpenses(String aString, String bString) {  
           int[][] f = new int[aString.length() + 1][bString.length() + 1]; 
           f[0][0] = 0;  
           int operator[][] = new int[aString.length() + 1][bString.length() + 1]; 

           for (int i = 1; i < aString.length() + 1; i++) {  
               f[i][0] = 2;  
               operator[i][0] = DEL;  
           }  
           for (int i = 1; i < bString.length() + 1; i++) {  
               f[0][i] = 2 + i;  
               operator[0][i] = ADD;  
           }  
           int type = ORIGAL;  
           for (int i = 1; i < aString.length() + 1; i++) {  
               for (int j = 1; j < bString.length() + 1; j++) {  
                   int tempType;  
                   int cost = 0;  
                   if (aString.charAt(i - 1) != bString.charAt(j - 1))  
                   {  
                       cost = 5;  
                   }  
                   int minCost;  
                   int delCount = getDelCount(f[i - 1][j], operator[i - 1][j]);  


                   int addCount = getAddCount(f[i][j - 1], operator[i][j - 1]);  
  

                   if (delCount >= addCount)  
                   {  
                       operator[i][j] = ADD;  
                       minCost = addCount;  
                   }  
                   else  
                   {  
                       operator[i][j] = DEL;  
                       minCost = delCount;  
                   }  
                   if (minCost > f[i - 1][j - 1] + cost)  
                   {  
                       operator[i][j] = ORIGAL;  
                       minCost = f[i - 1][j - 1] + cost;  
                   }  
                   f[i][j] = minCost;  
               }  
           }  
           return f[aString.length()][bString.length()];  
       }  


    }