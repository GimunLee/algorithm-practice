package swea.d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7206_숫자게임_dfs {

	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int testcase = 1; testcase <= T; testcase++) {
			String N = br.readLine().trim();
			max = 0;
			split(1, Integer.parseInt(N), 0);
			System.out.println("#" + testcase + " " + max);
		}

	}

	static void split(int calc, int num, int cnt) {

		int div = 10;
		while (num / div > 0) {
			int temp1 = num / div;
			int temp2 = num % div;

			dfs(calc * temp1 * temp2, cnt + 1);

			if (temp2 >= 10) { // 1자리수 아니라면 한번 더 쪼개기
				split(calc * temp1, temp2, cnt);
			}

			div *= 10;
		}
	}

	private static void dfs(int num, int cnt) { //
		if (num < 10) {
			max = Math.max(max, cnt);
			return;
		}
		split(1, num, cnt);
	}
}
