package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algo2_서울_05_이기문 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		for (int tc = 1; tc <= TC; tc++) {
			int W = Integer.parseInt(br.readLine().trim()); // 밀가루 무게
			int N = Integer.parseInt(br.readLine().trim()); // 봉지 종류 N가지

			int[] dp = new int[W + 1];
			dp[0] = 1;

			int[] arr_n = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr_n[i] = Integer.parseInt(st.nextToken()); // 각 봉지 용량
			}

			Arrays.sort(arr_n);

			if (N == 2) {
				dp[arr_n[0]] = 1;
				for (int i = arr_n[0]; i < W + 1; i += arr_n[0]) {
					dp[i] = i / arr_n[0];
				}

				dp[arr_n[1]] = 1;
				dp[1]=1;
				for (int i = arr_n[1] + 1; i < W + 1; i++) {
					if (dp[i - arr_n[1]] == 0 || dp[i - arr_n[1]] == -1) {
						dp[i] = -1;
					} else {
						dp[i] = dp[i - arr_n[1]] + 1;
					}
				}
			}

			if (N == 3) {
				dp[arr_n[0]] = 1;
				for (int i = arr_n[0]; i < W + 1; i += arr_n[0]) {
					dp[i] = i / arr_n[0];
				}

				dp[1] = 1;
				dp[arr_n[1]] = 1;
				for (int i = arr_n[1] + 1; i < W + 1; i++) {
					if (dp[i - arr_n[1]] == 0 || dp[i - arr_n[1]] == -1) {
						dp[i] = -1;
					} else {
						dp[i] = dp[i - arr_n[1]] + 1;
					}
				}
				dp[arr_n[2]] = 1;
				for (int i = arr_n[2] + 1; i < W + 1; i++) {
					if (dp[i - arr_n[1]] == 0 || dp[i - arr_n[1]] == -1) {
						dp[i] = -1;
					} else {
						dp[i] = Math.min(dp[i], dp[i - arr_n[2]] + 1);
					}
				}
			}

			if (dp[W] == 0) {
				dp[W] = -1;
			}
			System.out.println("#" + tc + " " + dp[W]);
		} // end of for of Test Case

	} // end of main
} // end of class
