package programmers.level2;

public class Solution_탑 {
	public static void main(String[] args) {
		int[] heights = { 1, 5, 3, 6, 7, 6, 5 };
		int[] answer = solution(heights);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	public static int[] solution(int[] heights) {
		int[] answer = new int[heights.length];

		for (int i = heights.length - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (heights[i] < heights[j]) { // 수신 가능
					answer[i] = j + 1;
					break;
				}
			}
		}

		return answer;
	}

}
