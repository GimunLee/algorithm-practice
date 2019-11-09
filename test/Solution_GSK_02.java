package test;

public class Solution_GSK_02 {
	public static void main(String[] args) {

		int n = 5;
		System.out.println(solution(n));
	}

	private static int limit, cnt;
	private static int[] set;

	// 1박 2박 할인
	private static int solution(int n) {
		cnt = 0;
		limit = n - 1;
		set = new int[limit];
		dfs(0, 0);
		return cnt;
	}

	private static void dfs(int len, int total) {
		if (total == limit) {
			cnt++;
			print(len);
			return;
		}
		for (int i = 1; i <= 2; i++) {
			int sum = total + i;

			if (sum <= limit) {
				set[len] = i;
				dfs(len + 1, total + i);
			}
		}
	}

	private static void print(int len) {
		for (int i = 0; i < len; i++) {
			System.out.print(set[i] + " ");
		}
		System.out.println();
	}

}
