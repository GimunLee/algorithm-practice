package programmers;

import java.util.Arrays;
import java.util.HashSet;

public class Solution_°ÝÀÚÆÇ {
	public static void main(String[] args) {
		int brown = 24;
		int red = 24;
		int[] answer = solution(brown, red);
		System.out.println(answer[0] + ", " + answer[1]);

	} // end of main

	public static int[] solution(int brown, int red) {
		int[] answer = new int[2];
		int sum = brown + red;

		for (int i = 1; i < sum / 2; i++) {
			if (sum % i == 0) {
				int num1 = i;
				int num2 = sum / i;
				int tmp = 2 * num1 + 2 * num2 - 4;
				if (tmp == brown) {
					answer[0] = Math.max(num1, num2);
					answer[1] = Math.min(num1, num2);
					break;
				}
			}
		}

		return answer;
	}

	private static class Pair {
		int num1, num2;

		public Pair(int num1, int num2) {
			this.num1 = num1;
			this.num2 = num2;
		}
	}
} // end of class
