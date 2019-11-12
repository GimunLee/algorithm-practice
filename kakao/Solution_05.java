package kakao;

public class Solution_05 {
	public static void main(String[] args) {
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int k = 3;
		System.out.println(solution(stones, k));

	}

	public static int solution(int[] stones, int k) {
		int answer = 0;
		int friend_cnt = 200_000_000;
		here: for (int j = 0; j <= friend_cnt; j++) {
			for (int i = 0; i < stones.length; i++) {
				if (stones[i] > 0) {
					stones[i]--;
				} else {
					int zero_length = 1;
					i++;
					while (i < stones.length && stones[i] == 0) {
						zero_length++;
						if (zero_length == k) {
							break;
						}
						i++;
					}
					i--;
					if (zero_length >= k) {
						break here;
					}
				}
			}
			answer++;
		}
		return answer;
	} // end of func(solution)
} // end of Solution
