package programmers;

import java.util.Arrays;

public class Solution_소수만들기 {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 7, 6, 4 };
		System.out.println(solution(nums));
	}

	public static int solution(int[] nums) {
		answer = 0;
		numbers = nums;
		combi(0, 0);

		return answer;
	}

	private static int[] set = new int[3];
	private static int[] numbers;
	private static int answer = 0;

	private static void combi(int idx, int len) {
		if (len == set.length) {
			int sum = 0;
			for (int i = 0; i < set.length; i++) {
				sum += set[i];
			}
			boolean flag = false;
			for (int i = 2; i < sum; i++) {
				if (sum % i == 0) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				answer++;
			}

			return;
		}

		for (int i = idx; i < numbers.length; i++) {
			set[len] = numbers[i];
			combi(i + 1, len + 1);
		}
	}
}
