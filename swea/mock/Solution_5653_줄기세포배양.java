package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5653_줄기세포배양 {
	private static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 }; // 상하좌우
	private static final int SIZE = 1200; // map의 최댓값
	private static Cell[][] map; // 줄기세포 배양판
	private static int N, M, K; //
	private static int[][] isExist;
	private static boolean[][] notYet;
	private static Queue<Pair> queue;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 초기 상태의 세로크기
			M = Integer.parseInt(st.nextToken()); // 초기 상태의 가로크기
			K = Integer.parseInt(st.nextToken()); // 배양 시간
			map = new Cell[SIZE][SIZE];
			isExist = new int[SIZE][SIZE];
			notYet = new boolean[SIZE][SIZE];
			queue = new LinkedList<Pair>();
			int answer = 0;

			for (int r = SIZE / 2; r < SIZE / 2 + N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = SIZE / 2; c < SIZE / 2 + M; c++) {
					int x = Integer.parseInt(st.nextToken());
					if (x != 0) {
						map[r][c] = new Cell(x); // (750, 750) -> 시작점
						queue.add(new Pair(r, c));
						answer++;
						isExist[r][c] = 1; // 초기에는 이미 번식 1 -> 0을 의미
					}
				}
			}
			// 1. 초기에는 비활성 상태, 생명력 수치가 X시간일때,
			// - X시간동안 비활성 / X시간이 지나는 순간 활성상태
			// 2. 활성 후, X시간동안만 살아있고, X시간이 지나면 죽게됨
			// - 첫 1시간동안 상,하,좌,우 네 방향 동시 번식 -> 번식된 줄기세포는 비활성
			// -> 같은 시간에 번식하는 경우 생명력이 더 큰 줄기세포가 번식
			// 3. 죽어도 남아있음

			for (int k = 1; k <= K; k++) {
//				print(k);
				int queueSize = queue.size();
				for (int i = 0; i < queueSize; i++) {
					Pair p = queue.poll();
					if (notYet[p.r][p.c]) {
						notYet[p.r][p.c] = false;
						answer++;
					} else {
						map[p.r][p.c].z--;
					}
					if (-1 * map[p.r][p.c].z >= map[p.r][p.c].x) { // 죽은 상태
						answer--;
						continue;
					}
					queue.add(new Pair(p.r, p.c));
					if (map[p.r][p.c].z <= 0) { // 활성화 시작 -> 4방향 탐색
						for (int d = 0; d < dr.length; d++) {
							int nR = p.r + dr[d];
							int nC = p.c + dc[d];

							if (isExist[nR][nC] != 0 && (isExist[nR][nC] - 1) < k) { // 죽은 세포나 이미 전 시간에 심어진 것이 있는 경우
								continue;
							}

							if (map[nR][nC] == null) { // 없는 경우, 그냥 심기
								map[nR][nC] = new Cell(map[p.r][p.c].x);
								queue.add(new Pair(nR, nC));
								isExist[nR][nC] = k + 1; // 심어진 시간
								notYet[nR][nC] = true;
							} else { // 같은 시간에 이미 다른 세포가 있는 경우
								map[nR][nC].x = Math.max(map[nR][nC].x, map[p.r][p.c].x);
								map[nR][nC].z = Math.max(map[nR][nC].x, map[p.r][p.c].x);
							}
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static void print(int k) {
		System.out.println(k + "시간");
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (map[r][c] == null) {
					System.out.print("0 ");
					continue;
				}
				System.out.print(map[r][c].x + " ");
			}
			System.out.println();
		}
	}

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static class Cell {
		int x; // 생명력
		int z; // 시간에 따라 깎아볼 생명력 (활성화되는지 안되는지 체크), 0보다 크다면 비활성화, 0이면 활성화됨, 절댓값(z)가 x면 죽음

		public Cell(int x) {
			this.x = x;
			this.z = x;
		}
	} // end of Cell
} // end of Class
