package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4012_¿ä¸®»ç {
	private static int N;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N + 1][N + 1];

			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 1; c <= N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			set = new int[N];
			set2 = new int[2];
			visited = new boolean[N + 1];
			ANS = Integer.MAX_VALUE;
			solve(0);
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int[] set;
	private static boolean[] visited;
	private static int ANS;

	private static void solve(int len) {
		if (len == N) {
			int[] inputSet = new int[N / 2];
			for (int i = 0; i < N / 2; i++) {
				inputSet[i] = set[i];
			}
			value = 0;
			solve2(inputSet, 0, 0);
			int a = value;
			for (int i = N / 2; i < N; i++) {
				inputSet[i - N / 2] = set[i];
			}
			value = 0;
			solve2(inputSet, 0, 0);
			int b = value;
			int tmp = Math.abs(a - b);
			ANS = ANS > tmp ? tmp : ANS;
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				set[len] = i;
				solve(len + 1);
				visited[i] = false;
			}
		}
	} // end of func(solve)

	private static int[] set2;
	private static int value;

	private static void solve2(int[] inputSet, int idx, int len) {
		if (len == 2) {
			value += map[set2[0]][set2[1]] + map[set2[1]][set2[0]];
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			set2[len] = inputSet[i];
			solve2(inputSet, i + 1, len + 1);
		}
	}
} // end of class
