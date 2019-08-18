package ac_dynamic_programming1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11057 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dn = new int[1001][10]; // N일 때, 오르막수의 개수를 저장
		int div = 10007;
		
		int N = Integer.parseInt(br.readLine().trim());
		
		for (int i = 0; i <= 9; i++) {
			dn[0][i] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				dn[i][j] += dn[i-1][j];
			}
		}
		
		int Answer = 0;
		
		for (int i = 0; i <= 9; i++) {
			Answer += dn[N][i];
		}
		
		System.out.println(Answer);
	}
}
