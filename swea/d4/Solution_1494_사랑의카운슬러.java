package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_1494_»ç¶ûÀÇÄ«¿î½½·¯ {
	static long sum;
	static int[][] position;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ¼ö

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // Áö··ÀÌ ¼ö
			
			position = new int[N][2];
			sum = Long.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				position[i][0] = Integer.parseInt(st.nextToken()); // x ÁÂÇ¥
				position[i][1] = Integer.parseInt(st.nextToken()); // y ÁÂÇ¥
			}

			// -- function

			plus_minus = new boolean[N];
			
			combi(0, 0);
			
			
			System.out.println("#" + tc + " " + sum);
		} // end of for test case
	} // end of main
	
	public static ArrayList<Integer> aList = new ArrayList<Integer>();
	static boolean[] plus_minus;

	private static void combi(int cnt, int t) {
		if(cnt == N/2) {
			int[][] temp = new int[1][2];
			for (int i = 0; i < N; i++) {
				if(plus_minus[i]) {
					temp[0][0] -= position[i][0];
					temp[0][1] -= position[i][1];
				}else {
					temp[0][0] += position[i][0];
					temp[0][1] += position[i][1];
				}
			}
			
			long v_tmp = V(temp[0][0], temp[0][1]);
			
			sum = Math.min(v_tmp, sum);
		}
		
		for (int i = t; i < N; i++) {
			aList.add(i);
			plus_minus[i] = true;
			combi(cnt+1,i+1);
			plus_minus[i] = false;
		}
	}
	
	// ¦¢V¦¢=¦¢(x, y)¦¢= x * x + y * y
	private static long V(long x, long y) {
		return (long) (x * x + y * y);
	}
} // end of class
