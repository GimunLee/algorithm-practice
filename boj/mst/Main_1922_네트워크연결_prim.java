package boj.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Prim : 340ms
 * kruscal : 540ms (height 추가)
 */
public class Main_1922_네트워크연결_prim {
	private static int N;
	private static int M;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 컴퓨터의 수
		M = Integer.parseInt(br.readLine().trim()); // 연결할 수 있는 선의 수

		map = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken()); // 연결 컴퓨터 (s <-> e)
			int c = Integer.parseInt(st.nextToken()); // 비용

			map[s][e] = c;
			map[e][s] = c;
		}

		visited = new boolean[N + 1];
		distance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		mst(1, N - 1);
		System.out.println(ANSER);
	} // end of main

	private static boolean[] visited;
	private static int[] distance;
	private static int ANSER;

	private static void mst(int idx, int cnt) {
		if (cnt == 0) {
			return;
		}
		visited[idx] = true;

		for (int i = 1; i <= N; i++) {
			if (map[idx][i] != 0) {
				distance[i] = Math.min(distance[i], map[idx][i]);
			}
		}

		int minIdx = 0;
		int minValue = Integer.MAX_VALUE;

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				if (minValue >= distance[i]) {
					minIdx = i;
					minValue = distance[i];
				}
			}
		}
		// 만약 minValue가 무한대면, 연결 안되있는 경우
		ANSER += minValue;
		mst(minIdx, cnt - 1);
	}
} // end of Class
