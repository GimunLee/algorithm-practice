package programmers;

import java.util.Arrays;

class Solution_Ã¼À°º¹ {

	public static void main(String[] args) {
		int[] lost = { 1, 3, 5 };
		int[] reserve = { 1, 4 };
		int ans = solution(5, lost, reserve);
		System.out.println(ans);

	}

	public static int solution(int n, int[] lost, int[] reserve) {
		Arrays.sort(lost);
		Arrays.sort(reserve);
		int answer = n;
		int[] cnt = new int[n + 2];

		for (int i = 1, j = 0, k = 0; i <= n; i++) {
			cnt[i] = 1;
			if (j < lost.length && i == lost[j]) {
				cnt[i]--;
				j++;
			}
			if (k < reserve.length && i == reserve[k]) {
				cnt[i]++;
				k++;
			}
		}
		for (int i = 0; i < lost.length; i++) {
			int cur = lost[i];
			if (cnt[cur] == 1) {
				continue;
			} else if (cnt[cur - 1] >= 2) {
				cnt[cur - 1]--;
				continue;
			} else if (cnt[cur + 1] >= 2) {
				cnt[cur + 1]--;
				continue;
			} else {
				answer--;
			}
		}
		return answer;
	}
}
