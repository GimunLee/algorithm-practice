package ac_dynamic_programming1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 쉬운 계단수 
 */
public class Main_10844 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// N은 100까지 올 수 있고, 뒤에 올 수 있는 수는 0~9
		int[][] d = new int[101][10]; 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		
		for (int l = 1; l <=9; l++) {
			d[1][l] = 1;
		}
		
		
		for(int i = 2; i<=N; i++) {
			for (int j = 0; j <= 9; j++) {
				d[i][j] = 0;
				if(j-1 >= 0) d[i][j] += d[i-1][j-1];
				if(j+1 <= 9) d[i][j] += d[i-1][j+1];
				d[i][j] %= 1000000000;
			}
		}
		long Answer = 0;
		for (int l = 0; l < 10; l++) {
			Answer += d[N][l] ;
		}
		System.out.println(Answer%=1000000000);
	}
}
