package programmers;

public class Solution_예산 {
	public static void main(String[] args) {
		int[] input = { 130, 130, 130, 130 };
		int M = 485;

		System.out.println(solution(input, M));

	}

	public static int solution(int[] budgets, int M) {
		int answer = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < budgets.length; i++) {
			max = Math.max(budgets[i], max);
		} // 최소 최대 뽑기

		int start = 0;
		int end = max;

		while (true) {
			if (start > end) {
				break;
			}

			int mid = (start + end) / 2;

			long tmpSum = 0;
			for (int i = 0; i < budgets.length; i++) {
				if (budgets[i] > mid) {
					tmpSum += mid;
				} else {
					tmpSum += budgets[i];
				}
			}

			if (tmpSum > M) {
				end = mid - 1;
			} else {
				start = mid + 1;
				answer = Math.max(answer, mid);
			}
		}
		return answer;
	} // end of func(solution)
}
