package programmers;

import java.util.Arrays;

public class Solution_구명보트 {
	public static void main(String[] args) {
		int[] people = { 10, 20, 30, 40, 50, 60, 70, 80, 90 }; // 5
		int limit = 100;
		System.out.println(solution(people, limit));

	} // end of main

	public static int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);

		boolean[] visited = new boolean[people.length];
		int n = people.length;

		here: for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > i; j--) { // 가능한 가장 무거운 사람 같이 타기
				// 백트랙 1 : 가장 가벼운 사람과 탈 수 없다면, 그 이상의 사람과는 절대 탈 수 없음
				// 백트랙 2 : 남은 인원 수 만큼 필요함 ->
//				if (visited[j]) {
//					continue;
//				}
				n--;
				int sum = people[i] + people[j];
				if (sum <= limit) {
					visited[j] = true;
					answer++;
					continue here;
				} else {
					visited[j] = true;
					answer++;
				}
			}
			answer++;
		}
		return answer;
	}
} // end of class
