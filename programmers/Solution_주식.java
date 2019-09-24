package programmers;

import java.util.Arrays;

public class Solution_аж╫д {
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3, 1, 3, 2 };
		int[] answer = solution(prices);
		for (int i = 0; i < prices.length; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		for (int i = 0; i < answer.length; i++) {
			answer[i] = answer.length - i - 1;
		}
//		System.out.println(Arrays.toString(answer));

		int tmp = 0;
		for (int i = prices.length - 1; i > 0; i--) {
			int priceCur = prices[i];
			int priceFront = prices[i - 1];

			if (priceFront > priceCur) {
				tmp++;
				answer[i - 1] -= tmp--;
			} else {
				tmp = 0;
			}
		}

		return answer;
	}

}
