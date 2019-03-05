package etc;

import java.math.BigInteger;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_2407_조합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		BigInteger up = new BigInteger("1");
		for (int i = n; i >= n-m+1  ; i--) {
			up = up.multiply(BigInteger.valueOf(i));
		}
		
		BigInteger down = new BigInteger("1");
		for (int i = 1; i <= m; i++) {
			down = down.multiply(BigInteger.valueOf(i));
		}
		
		System.out.println(up.divide(down));
	}
}

//public class Main_2407_조합 {
//	static BigInteger[][] list;
//	 
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        BigInteger big = new BigInteger("1");
//        
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        list = new BigInteger[1001][1001];
//        list[1][0] = list[1][1] = big;
//        
//        for(int i=2;i<=n;i++) {
//            for(int j=0;j<=i;j++) {
//                if(i == j || j == 0)
//                    list[i][j] = big;
//                else
//                    list[i][j] = list[i-1][j-1].add(list[i-1][j]);
//            }
//        }
//    
//        System.out.println(list[n][m]);
//    }
//}
