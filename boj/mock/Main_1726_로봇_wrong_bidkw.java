package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1726_로봇_wrong_bidkw {
	// 동서남북
	private static int[] dr = { 0, 0, 0, 1, -1 };
	private static int[] dc = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][][][] visited = new boolean[M][N][5][4];

		Pair[] queue = new Pair[M * N * 4 * 3];
		int front = -1, rear = -1;
		st = new StringTokenizer(br.readLine(), " ");
		queue[++rear] = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()), 0);
		st = new StringTokenizer(br.readLine(), " ");
		Pair end = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()), 0);

		int ANS = Integer.MAX_VALUE;

		while (front != rear) {
			Pair p = queue[++front];
			if (p.r == end.r && p.c == end.c && p.dir == end.dir) {
				ANS = ANS > p.cnt ? p.cnt : ANS;
			}

			DIR: for (int dir = 0; dir < dr.length; dir++) {
				for (int k = 0; k <= 3; k++) { // 회전만 하는 경우 체크
					if (dir == isImpossible(p.dir)) { // 뒤로 가는 경우 체크
						continue DIR;
					}

					int nR = p.r + dr[dir] * k;
					int nC = p.c + dc[dir] * k;

					if (nR >= M || nC >= N || nR < 0 || nC < 0) { // 범위초과
						continue DIR;
					}

					if (visited[p.r][p.c][dir][k]) {
						continue;
					}
					if (map[nR][nC] == 1) {
						continue DIR;
					}	

					int cnt = p.cnt;
					if (p.dir != dir) { // 회전하는 경우
						cnt += 1;
					}
					if (k != 0) {
						cnt += 1;
					}
					visited[p.r][p.c][dir][k] = true;
					queue[++rear] = new Pair(nR, nC, dir, cnt);
				}
			}
		} // end of while(queue)
		System.out.println(ANS);
	} // end of main

	private static int isImpossible(int dir) {
		int tmp = 0;
		switch (dir) {
		case 1:
			tmp = 2;
			break;
		case 2:
			tmp = 1;
			break;
		case 3:
			tmp = 4;
			break;
		case 4:
			tmp = 3;
			break;
		}
		return tmp;
	}

	private static class Pair {
		int r, c, dir, cnt;

		Pair(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	} // end of Pair
} // end of class
