package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
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
		System.out.println(solve(map));
	} // end of main

	private static int solve(int[][] map) {
		int ANSER = 0;
		int[][] queue = new int[2500][2];
		int[][] moveQueue = new int[2500][2];
		boolean flag;
		while (true) {
			boolean[][] visited = new boolean[N][N];
			flag = false;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) {
						continue;
					}

					int front = -1, rear = -1;
					int mFront = -1, mRear = -1;

					queue[++rear][0] = r;
					queue[rear][1] = c;

					int sum = 0, cnt = 0;

					while (front != rear) {
						int cR = queue[++front][0];
						int cC = queue[front][1];

						moveQueue[++mRear][0] = cR;
						moveQueue[mRear][1] = cC;

						sum += map[cR][cC];
						cnt++;

						visited[cR][cC] = true;

						for (int dir = 0; dir < dr.length; dir++) {
							int nR = cR + dr[dir];
							int nC = cC + dc[dir];

							if (nR >= 0 && nC >= 0 && nR < N && nC < N) {
								if (visited[nR][nC]) {
									continue;
								}

								int sub = Math.abs(map[nR][nC] - map[cR][cC]);
								if (L <= sub && sub <= R) { // 같은 연합
									queue[++rear][0] = nR;
									queue[rear][1] = nC;
									visited[nR][nC] = true;
								}
							}
						}
					}

					if (cnt <= 1) {
						continue;
					}
					flag = true;
					while (mFront != mRear) {
						map[moveQueue[++mFront][0]][moveQueue[mFront][1]] = sum / cnt;
					}
				}
			} // 인구 이동 끝

			if (!flag) {
				break;
			}
			ANSER++;
		}
		return ANSER;
	}
} // end of class
