package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취 {
	private static int[][] map;
	private static int C;
	private static int M;
	private static int N;
	private static boolean[][] isChoice;
	private static ArrayList<Pair> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N:벌통 크기
			M = Integer.parseInt(st.nextToken()); // M:선택할 수 있는 벌통의 개수
			C = Integer.parseInt(st.nextToken()); // C:꿀을 채취할 수 있는 최대양

			map = new int[N][N];
			list = new ArrayList<>();
			comb_bucket = new int[2][M];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					list.add(new Pair(r, c));
				}
			}

			isChoice = new boolean[N][N];

			ans = Integer.MIN_VALUE;
			chooceBucket(0, 0);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.println(sb.toString());
	} // end of main

	private static int tmp;
	private static int ans;
	private static int[][] comb_bucket;

	private static void chooceBucket(int idx, int len) {
		if (len == 2) {
			tmp = 0;
			dfs(comb_bucket[0], 0, 0, 0); // 뽑을통, 뽑은개수, 채취한 용량, 수익
			dfs(comb_bucket[1], 0, 0, tmp); // 뽑을통, 뽑은개수, 채취한 용량, 수익
			if (ans < tmp) {
				ans = tmp;
			}
			return;
		}

		here: for (int i = idx; i < N * N; i++) {
			Pair p = list.get(i);
			int r = p.r;
			int c = p.c;
			// 2라인 체크
			for (int j = 0; j < M; j++) {
				if (c + j >= N) {
					continue here;
				}

				if (isChoice[r][c + j]) {
					continue here;
				}
			}
			// 조합 채우기
			for (int j = 0; j < M; j++) {
				comb_bucket[len][j] = i + j;
				isChoice[r][c + j] = true;
			}
			chooceBucket(i + M, len + 1);
			for (int j = 0; j < M; j++) {
				isChoice[r][c + j] = false;
			}
		}
	} // end of dfs()

	private static void dfs(int[] each_bucket, int idx, int honey, int benefit) {
		if (tmp < benefit) {
			tmp = benefit;
		}
		if (idx == M) { // 끝까지 채취했을때,
			return;
		}

		int r = list.get(each_bucket[idx]).r;
		int c = list.get(each_bucket[idx]).c;

		if (map[r][c] > C || honey + map[r][c] > C) { // 용량이 넘쳤을때
			return;
		}
		
		dfs(each_bucket, idx + 1, honey + map[r][c], benefit + (int) Math.pow(map[r][c], 2)); // 뽑은 경우
		dfs(each_bucket, idx + 1, honey, benefit); // 안 뽑은 경우
	}

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
