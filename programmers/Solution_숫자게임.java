package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_숫자게임 {

	public static void main(String[] args) {
		int[] A = { 1, 1, 2, 3 };
		int[] B = { 1, 2, 2, 3 };
		System.out.println(solution(A, B));
	}

	public static int solution(int[] A, int[] B) {
		int answer = 0;
		Arrays.sort(A);
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < B.length; i++) {
			queue.add(B[i]);
		}

		for (int i = 0; i < A.length; i++) {
			int a = A[i];
			if (queue.isEmpty()) {
				break;
			}
			int b = queue.poll();
			if (a < b) {
				answer++;
			} else {
				i--;
			}

		}
		return answer;
	}
}
