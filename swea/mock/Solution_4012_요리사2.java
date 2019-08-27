package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.08.27.(화)
 * */
public class Solution_4012_요리사2 {
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
			solveSet1 = new int[N / 2];
			solveSet2 = new int[N / 2];
			visited = new boolean[N + 1];
			ANS = Integer.MAX_VALUE;
			combSet = new int[N / 2];
			solve(0, 1);
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int[] solveSet1;
	private static int[] solveSet2;
	private static boolean[] visited;
	private static int ANS;

	private static void solve(int len, int idx) {
		if (len == N / 2) {
			int tmpIdx = 0;
			for (int i = 1; i < visited.length; i++) {
				if (!visited[i]) {
					solveSet2[tmpIdx++] = i;
				}
			}
			value = 0;
			comb(solveSet1, 0, 0);
			int a = value;
			value = 0;
			comb(solveSet2, 0, 0);
			int b = value;
			int tmp = Math.abs(a - b);
			ANS = ANS > tmp ? tmp : ANS;
			return;
		}

		for (int i = idx; i <= N; i++) {
			solveSet1[len] = i;
			if (solveSet1[0] != 1) {
				return;
			}
			visited[i] = true;
			solve(len + 1, i + 1);

			visited[i] = false;
		}
	} // end of func(solve)

	private static int[] combSet;
	private static int value;

	private static void comb(int[] inputSolveSet, int idx, int len) {
		if (len == 2) {
			value += map[combSet[0]][combSet[1]] + map[combSet[1]][combSet[0]];
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			combSet[len] = inputSolveSet[i];
			comb(inputSolveSet, i + 1, len + 1);
		}
	}
} // end of class
