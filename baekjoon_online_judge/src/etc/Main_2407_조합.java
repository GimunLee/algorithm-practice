package etc;

import java.math.BigInteger;
import java.util.Scanner;

public class Main_2407_а╤гу {
	static BigInteger[][] list;
	 
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BigInteger big = new BigInteger("1");
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        list = new BigInteger[1001][1001];
        list[1][0] = list[1][1] = big;
        
        for(int i=2;i<=n;i++) {
            for(int j=0;j<=i;j++) {
                if(i == j || j == 0)
                    list[i][j] = big;
                else
                    list[i][j] = list[i-1][j-1].add(list[i-1][j]);
            }
        }
    
        System.out.println(list[n][m]);
    }
}
