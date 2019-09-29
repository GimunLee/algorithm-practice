package programmers.level2;

import java.util.Arrays;

public class Solution_ÃÖ¼Ò°ö {
	public static void main(String[] args) {
		int[] A = { 1, 4, 2 };
		int[] B = { 5, 4, 4 };
		System.out.println(solution(A, B));
	}

	public static int solution(int[] A, int[] B) {
		int answer = 0;

		Arrays.sort(A);
		Arrays.sort(B);

		for (int i = 0; i < B.length; i++) {
			answer += A[i] * B[B.length - 1 - i];
		}

		return answer;
	}
}
