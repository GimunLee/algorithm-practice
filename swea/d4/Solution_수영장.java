package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_수영장 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			int[] price = new int[5]; // 0: 안 씀 / 1 : 1일 / 2 : 1달 / 3 : 3달 / 4 : 1년
			int[] use = new int[13]; // 0: 안 씀

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 1; i < 5; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < 13; i++) {
				use[i] = Integer.parseInt(st.nextToken());
			}

			int[][] dp = new int[5][13];

			for (int i = 1; i < 13; i++) {
				if (use[i] != 0) { // 이용 날짜가 있을때
					dp[1][i] = (((dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1]) < dp[3][i - 1])
							? (dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1])
							: dp[3][i - 1]) + use[i] * price[1]; // 1일
					dp[2][i] = (((dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1]) < dp[3][i - 1])
							? (dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1])
							: dp[3][i - 1]) + price[2]; // 1달 이용권

					if (dp[3][i] == 0) {
						int temp = (((dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1]) < dp[3][i - 1])
								? (dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1])
								: dp[3][i - 1]); // 3달 이용권

						for (int j = 0; j < 3; j++) {
							if (i + j > 12) {
								break;
							}
							dp[3][i + j] = temp + price[3]; // 3달 이용권 채우기
						}
					}

					dp[4][i] = price[4]; // 1년 이용권

				} else { // 이용 날짜가 없으므로 전거 그대로 가져오기
					dp[1][i] = dp[1][i - 1];
					dp[2][i] = dp[2][i - 1];
					dp[3][i] = dp[3][i - 1];
					dp[4][i] = dp[4][i - 1];
				}
			}

			int min = Integer.MAX_VALUE;

			for (int i = 1; i < 5; i++) {
				if (min > dp[i][12]) {
					min = dp[i][12];
				}
			}
			System.out.println("# " + tc + " " + min);

		} // end of testcase
	} // end of main
} // end of class
