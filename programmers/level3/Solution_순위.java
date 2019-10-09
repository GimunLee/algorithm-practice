package programmers.level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_순위 {
	public static void main(String[] args) {
		int n = 8;
		int[][] results = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 6, 7 }, { 7, 8 }, { 4, 5 } }; // 8
//		int n = 5;
//		int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } }; // 2
		int answer = solution(n, results);
		System.out.println(answer);
	}

	public static int solution(int n, int[][] results) {
		int answer = 0;
		int[][] edgeArray = new int[n + 1][n + 1];
		for (int i = 0; i < results.length; i++) {
			int winner = results[i][0];
			int loser = results[i][1];
			edgeArray[winner][loser] = 1;
		}

		Queue<Integer> winQueue = new LinkedList<>();
		Queue<Integer> loseQueue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			int cnt = 0;
			winQueue.add(i);
			loseQueue.add(i);
			boolean[] visited = new boolean[n + 1];
			while (!winQueue.isEmpty()) {
				int people = winQueue.poll();
				// 이긴거
				for (int j = 1; j < edgeArray[people].length; j++) {
					if (edgeArray[people][j] == 1 && !visited[j]) {
						winQueue.add(j);
						visited[j] = true;
						cnt++;
					}
				}
			}
			// 진거
			while (!loseQueue.isEmpty()) {
				int people = loseQueue.poll();
				for (int j = 1; j < edgeArray[people].length; j++) {
					if (edgeArray[j][people] == 1 && !visited[j]) {
						loseQueue.add(j);
						visited[j] = true;
						cnt++;
					}
				}
			}
			if (cnt == n - 1) {
				answer++;
			}
		}
		return answer;
	}

}
