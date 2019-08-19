package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3234_준환이의양팔저울_차진홍2 {

	private static int[][] memo;
	private static int[] w;
	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			w = new int[N];
			int W = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				w[i] = Integer.parseInt(st.nextToken());
				W += w[i];
			}

			memo = new int[W][(1 << N)]; // left무게이고 , check 상황일때 경우의 수 저장
			for (int i = 0; i < N; i++) {
				memo[0][0] += dfs(1, w[i], 0, (1 << i));
			}

			System.out.println("#" + testcase + " " + memo[0][0]);
		}
	}

	private static int dfs(int n, int left, int right, int check) {

		if (memo[left][check] != 0) {
			return memo[left][check];
		}

		for (int i = 0; i < N; i++) {
			if ((check & (1 << i)) == 0) {

				if (n == N - 1) {
					if (left >= right + w[i]) {
						return 2;
					} else {
						return 1;
					}
				} else {
					memo[left][check] += dfs(n + 1, left + w[i], right, check | (1 << i));
					if (left >= right + w[i]) {
						memo[left][check] += dfs(n + 1, left, right + w[i], check | (1 << i));
					}
				}
			}
		}
		return memo[left][check];

	}
}
