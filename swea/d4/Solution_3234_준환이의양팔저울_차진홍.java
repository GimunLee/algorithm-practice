package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3234_준환이의양팔저울_차진홍 {
	private static boolean[] permu;
	private static int[] w;
	private static int N;
	private static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			w = new int[N];
			permu = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				w[i] = Integer.parseInt(st.nextToken());
			}

			result = 0;
			for (int i = 0; i < N; i++) {
				permu[i] = true;
				dfs(1, w[i], 0);
				permu[i] = false;
			}

			System.out.println("#" + testcase + " " + result);
		}
	}

	private static void dfs(int n, int left, int right) {
		for (int i = 0; i < N; i++) {
			if (!permu[i]) {

				if (n == N - 1) {
					if (left >= right + w[i]) {
						result += 2;
					} else {
						result++;
					}
					return;
				} else {
					permu[i] = true;
					dfs(n + 1, left + w[i], right);
					if (left >= right + w[i]) {
						dfs(n + 1, left, right + w[i]);
					}
					permu[i] = false;
				}
			}
		}

	}
}
