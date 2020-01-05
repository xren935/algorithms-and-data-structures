package Assign4; 
import java.util.*;
import java.io.*;
//created by Xingya Ren

public class Multiply{

    private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }
    
    public static int[] naive(int size, int x, int y) {
    	
        //first cut x and y according to the size(different from what we've seen in class)  
        x = x % (int)Math.pow(2,  size);
        y = y % (int)Math.pow(2,  size);
        int toReturn[] = new int[2];

        if(size == 1) {
            toReturn[0]=(x*y); //multiply 
            toReturn[1]=1; //cost is 1 
            return toReturn;
        }else {
        	//following the pseudocode for this multiplication algorithm seen in class... 
            int m = (int)Math.ceil(size/2.0);  //needs to divide by a double 
            int a =(int)Math.floor(x/(int)Math.pow(2,m));
            int b = x % (int)Math.pow(2,m);
            int c =(int)Math.floor(y/(int)Math.pow(2,m));
            int d = y % (int)Math.pow(2,m);
            int e[] = naive(m,a,c);
            int f[] = naive(m,b,d);
            int g[] = naive(m,b,c);
            int h[] = naive(m,a,d);
            toReturn[0] = (((int)Math.pow(2, (2*m))*e[0])+(int)Math.pow(2, m)*(g[0]+h[0])+f[0]);
            toReturn[1] = (int)(e[1]+f[1]+g[1]+h[1]+3*m);
        }
        return toReturn;
            
      }

      public static int[] karatsuba(int size, int x, int y) {
        //cut x and y accordingly 
        x = x % (int)Math.pow(2,  size);
        y = y % (int)Math.pow(2,  size);  
        int[] karaReturn = new int[2]; 
        int[] e = new int[2];
        int[] f = new int[2];
        int[] g = new int[2];
        if (size == 1) { //base case; cost is one; return x*y 
        		karaReturn[0] = x*y;
        		karaReturn[1] = 1;
            return karaReturn;
    
        }else {
        
        		//following the psuedocode for Karatsuba seen in class... 
            int m = (int)Math.ceil(size / 2.0);
            int a = (int)Math.floor(x/(int)Math.pow(2,m));
            int b = x % (int)Math.pow(2,m);
            int c = (int)Math.floor(y/(int)Math.pow(2,m));
            int d = y % (int)Math.pow(2,m);
            //recursive calls 
            e = karatsuba(m,a,c);
            f = karatsuba(m,b,d);
            g = karatsuba(m,a-b,c-d);
            karaReturn[0] = ((int)(Math.pow(2,(2*m)))) * e[0] + ((int)(Math.pow(2,m))) * (e[0]+f[0]-g[0]) + f[0];
            karaReturn[1] = (e[1]+f[1]+g[1])+6*m; //update the cost 
            return karaReturn;
        }
      }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;
                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);
                    int y = randomInt(size);
                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);
            
                    if (resNaive[0] != resKaratsuba[0]) {
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];
                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;
                System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}

