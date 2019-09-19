package programmers;

public class Solution_배열중가장작은수제거 {
	public static void main(String[] args) {
		int[] input = { 4, 3, 2, 1 };
		solution(input);
	}

	public static int[] solution(int[] arr) {
		if (arr.length == 1) {
			int[] answer = { -1 };
			return answer;
		}
		int[] answer = new int[arr.length - 1];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == min) {
				continue;
			}
			answer[idx++] = arr[i];
		}

		return answer;
	}
}
