package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_�丮�� {
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
			permuSet1 = new int[N / 2];
			permuSet2 = new int[N / 2];

			combSet = new int[2];
			visited = new boolean[N + 1];
			ANS = Integer.MAX_VALUE;
			permu(0);
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int[] permuSet1;
	private static int[] permuSet2;
	private static boolean[] visited;
	private static int ANS;

	private static void permu(int len) {
		if (len == N) {
			value = 0;
			comb(permuSet1, 0, 0);
			int a = value;
			value = 0;
			comb(permuSet2, 0, 0);
			int b = value;
			int tmp = Math.abs(a - b);
			ANS = ANS > tmp ? tmp : ANS;
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (len < N / 2) {
					permuSet1[len] = i;
				} else {
					permuSet2[len - N / 2] = i;
				}
				permu(len + 1);
				visited[i] = false;
			}
		}
	} // end of func(permu)

	private static int[] combSet;
	private static int value;

	private static void comb(int[] inputpermuSet1, int idx, int len) {
		if (len == 2) {
			value += map[combSet[0]][combSet[1]] + map[combSet[1]][combSet[0]];
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			combSet[len] = inputpermuSet1[i];
			comb(inputpermuSet1, i + 1, len + 1);
		}
	}
} // end of class
