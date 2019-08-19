package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트_DP {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // N, L

			int N = Integer.parseInt(st.nextToken()); // 햄버거 재료 수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			int[][] set = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int score = Integer.parseInt(st.nextToken()); // 점수
				int calory = Integer.parseInt(st.nextToken()); // 칼로리

				set[i][0] = score; // 점수
				set[i][1] = calory; // 칼로리
			}

			int[] dp = new int[L + 1];

			for (int i = 0; i < set.length; i++) {
				for (int j = L; j >= set[i][1]; j--) {
					dp[j] = Math.max(dp[j], dp[j - set[i][1]] + set[i][0]);
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[L]).append('\n');
		}
		System.out.println(sb.toString());
	}
}
