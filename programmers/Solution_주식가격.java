package programmers;

import java.util.Arrays;

public class Solution_주식가격 {
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };
		int[] answer = solution(prices);
		for (int i = 0; i < prices.length; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		for (int i = 0; i < prices.length - 1; i++) {
			int tmp = 0;
			for (int j = i + 1; j < prices.length; j++) {
				tmp++;
				if (prices[i] > prices[j]) {
					break;
				}
			}
			answer[i] = tmp;
		}
		return answer;
	}

}
