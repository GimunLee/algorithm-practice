package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4301_Äá¸¹ÀÌ½É±â {
	private static int N;
	private static int M;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ¼ö

		map = new int[1001][1001];
		long[][] dp = new long[1001][1001];
		int ans = 0;

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (map[r][c] == 0) {
					map[r][c] = 1;
					check(r, c);
					ans++;
				}
				dp[r][c] = ans;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				dp[j][i] = dp[i][j];
			}
		}

		for (int r = 0; r <= 1000; r++) {
			for (int c = 4; c <= 1000; c++) {
				dp[r][c] = dp[r][3] + dp[r][c - 4];
				dp[c][r] = dp[r][3] + dp[r][c - 4];
			}
		}

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // Column
			M = Integer.parseInt(st.nextToken()); // Row

			System.out.println("#" + tc + " " + dp[M - 1][N - 1]);
		}
	} // end of main

	// ¿ÏÀüÅ½»ö
	private static void check(int r, int c) {
		for (int i = r; i < 4; i++) {
			for (int j = c; j < 4; j++) {
				if (map[i][j] == -1 || map[i][j] == 1) {
					continue;
				}
				double tmp = Math.sqrt((Math.pow((r - i), 2) + Math.pow((c - j), 2)));
				if (tmp == 2.0) {
					map[i][j] = -1;
				}
			}
		}
	}
} // end of class
