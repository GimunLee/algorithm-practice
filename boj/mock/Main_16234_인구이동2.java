package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구이동2 {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N, L, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // N x N 땅
		// 국경선을 공유하는 두 나라의 인구의 차이 L명 이상 R명 이하
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < map.length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(Input)

		int ANSER = solve(map);
		System.out.println(ANSER);
	} // end of main

	private static int solve(int[][] map) {
		int ANSER = 0;

		boolean flag = false;
		while (true) {
			boolean[][] visited = new boolean[N][N];
			Queue<Pair> queue = new LinkedList<>();
			Queue<Pair> moveQueue = new LinkedList<>();
			int islandNum = 1;

			int[] sum = new int[5000];
			int[] cnt = new int[5000];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) {
						continue;
					}

					queue.add(new Pair(r, c));

					while (!queue.isEmpty()) {
						Pair pair = queue.poll();
						int cR = pair.r;
						int cC = pair.c;

						moveQueue.add(new Pair(cR, cC, islandNum));

						sum[islandNum] += map[cR][cC];
						cnt[islandNum]++;
						if (cnt[islandNum] >= 2) {
							flag = true;
						}
						visited[cR][cC] = true;

						for (int dir = 0; dir < dr.length; dir++) {
							int nR = cR + dr[dir];
							int nC = cC + dc[dir];

							if (nR < 0 || nC < 0 || nR >= N || nC >= N) {
								continue;
							}

							if (visited[nR][nC]) {
								continue;
							}

							int sub = Math.abs(map[nR][nC] - map[cR][cC]);
							if (L <= sub && sub <= R) { // 같은 연합
								queue.add(new Pair(nR, nC));
								visited[nR][nC] = true;
							}
						}
					}
					islandNum++;
				}
			} // 인구 이동 끝

			if (!flag) {
				break;
			}
			flag = false;
			ANSER++;

			while (!moveQueue.isEmpty()) {
				Pair pair = moveQueue.poll();
				int r = pair.r;
				int c = pair.c;
				int tIslandNum = pair.islandNum;

				if (cnt[tIslandNum] <= 1) {
					continue;
				}
				int tmp = sum[tIslandNum] / cnt[tIslandNum];
				map[r][c] = tmp;
			}
		}
		return ANSER;
	}

	private static class Pair {
		int r, c, islandNum;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Pair(int r, int c, int islandNum) {
			this.r = r;
			this.c = c;
			this.islandNum = islandNum;
		}
	}
} // end of class
