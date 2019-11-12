package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2019-11-12
 * */
public class Main_17822_원판돌리기 {
	private static final int[][] dir = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } };
	private static int N, M, T, totalSum, totalCnt;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 원판의 수
		M = Integer.parseInt(st.nextToken()); // 숫자의 수
		T = Integer.parseInt(st.nextToken()); // 회전 수

		map = new int[N + 1][M];
		totalSum = 0;
		totalCnt = 0;

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				totalSum += map[n][m];
				totalCnt++;
			}
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			rotate(x, d, k);
			if (!delete()) {
				calibration();
			}
		}
		System.out.println(totalSum);
	} // end of func(main)

	private static void rotate(int x, int d, int k) {

		int tmp = map[0].length - k;
		if (tmp < k) {
			if (d == 0) {
				d = 1;
			} else {
				d = 0;
			}
			k = tmp;
		}

		for (int n = 1; n < map.length; n++) {
			if (n % x == 0) {
				if (d == 0) { // 시계 방향
					rotateRight(n, k);
				} else { // 반시계방향
					rotateLeft(n, k);
				}
			}
		}
	}

	private static void rotateRight(int n, int k) {
		for (int i = 0; i < k; i++) {
			int tmp = map[n][map[0].length - 1];
			for (int c = map[0].length - 1; c > 0; c--) {
				map[n][c] = map[n][c - 1];
			}
			map[n][0] = tmp;
		}
	}

	private static void rotateLeft(int n, int k) {
		for (int i = 0; i < k; i++) {
			int tmp = map[n][0];
			for (int c = 0; c < map[0].length - 1; c++) {
				map[n][c] = map[n][c + 1];
			}
			map[n][map[0].length - 1] = tmp;
		}
	}

	private static boolean delete() {
		boolean ret = false;

		Queue<Pair> queue = new LinkedList<>();

		for (int r = 1; r < map.length; r++) {
			for (int c = 0; c < map[0].length; c++) {
				int num = map[r][c];
				if (num == 0) {
					continue;
				}
				queue.add(new Pair(r, c));

				while (!queue.isEmpty()) {
					Pair pair = queue.poll();

					for (int i = 0; i < dir[0].length; i++) {
						int nR = pair.r + dir[0][i];
						int nC = pair.c + dir[1][i];

						if (nR <= 0 || nR >= map.length) {
							continue;
						}

						if (nC < 0) {
							nC = map[0].length - 1;
						} else if (nC >= map[0].length) {
							nC = 0;
						}

						if (map[nR][nC] == 0) {
							continue;
						}

						if (num == map[nR][nC]) {
							queue.add(new Pair(nR, nC));
							totalSum -= num;
							totalCnt--;
							map[nR][nC] = 0;
							ret = true;
						}
					}
				}
			}
		}
		return ret;
	}

	private static void calibration() {
		double avg = (double) totalSum / (double) totalCnt;
		for (int r = 1; r < map.length; r++) {
			for (int c = 0; c < map[0].length; c++) {
				if (map[r][c] == 0) {
					continue;
				}
				if (map[r][c] > avg) {
					map[r][c]--;
					totalSum--;
				} else if (map[r][c] < avg) {
					map[r][c]++;
					totalSum++;
				}
			}
		}
	}

	private static void print() {
		System.out.println();
		for (int r = 1; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	} // end of func(print)

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

} // end of Class
