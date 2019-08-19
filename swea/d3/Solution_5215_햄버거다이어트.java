package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트 {
	static int N, L; // N : 재료의 수, L : 제한 칼로리
	static int[] score_arr, calory_arr; // score : 점수, calory : 칼로리
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim()); // Test Case

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // 재료 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			score_arr = new int[N];
			calory_arr = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				score_arr[i] = Integer.parseInt(st.nextToken());
				calory_arr[i] = Integer.parseInt(st.nextToken());
			}

			maxScore = 0;

			search(0, 0, 0); // index, score, calory
			System.out.println("#" + tc + " " + maxScore);
		}
	}

	private static void search(int idx, int score, int calory) {
		if (calory > L ) { // 재료의 총 칼로리 > limit
			return;
		}
		if (idx == N) {
			System.out.println(maxScore);
			maxScore = Math.max(maxScore, score);
			return;
		}
		search(idx + 1, score + score_arr[idx], calory + calory_arr[idx]);
		search(idx + 1, score, calory);
	}
}
