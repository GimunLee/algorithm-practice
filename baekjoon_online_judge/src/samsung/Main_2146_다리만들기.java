package samsung;

/* -- 반례
10
1 1 1 0 0 0 0 0 0 0
1 1 1 1 0 0 0 0 0 0
1 0 1 1 0 0 0 0 0 0
0 0 1 1 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0


11
1 1 1 1 1 0 0 0 0 0 0 
1 0 0 0 1 0 0 0 0 0 1
1 1 1 0 1 0 0 0 0 0 0
1 0 0 0 1 0 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0

5
1 0 0 0 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 0 0 1
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146_다리만들기 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int[][] map;
	private static int N;
	private static boolean[][] visited;
	private static Queue<Pair> island_queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // map의 크기
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		island_queue = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					checkSide(r, c);
					idx++;
				}
			}
		}

		int ans = Integer.MAX_VALUE;
		int[][] length = new int[N][N];
		boolean flag = false;

		while (!island_queue.isEmpty()) {
			int size = island_queue.size();
			for (int i = 0; i < size; i++) {
				Pair p = island_queue.poll();
				for (int j = 0; j < dr.length; j++) {
					int nR = p.r + dr[j];
					int nC = p.c + dc[j];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) {
						continue;
					}

					if (visited[nR][nC]) { // 섬인 경우
						continue;
					} else if (map[nR][nC] != 0) { // 바다지만, 다리가 이미 설치된 경우
						if (map[nR][nC] != p.num) {
							ans = ans > length[nR][nC] + length[p.r][p.c] ? length[nR][nC] + length[p.r][p.c] : ans;
							flag = true;
						}
					} else {
						island_queue.add(new Pair(nR, nC, p.num)); // 다음에 확장할 다리
						map[nR][nC] = p.num; // 다리 설치
						length[nR][nC] = length[p.r][p.c] + 1;
					}
				}
			}
			if (flag) {
				break;
			}
		}
		System.out.println(ans);
	} // end of main

	private static int idx = 1;

	private static void checkSide(int r, int c) {
		visited[r][c] = true;
		map[r][c] = idx;

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			if (nR < 0 || nC < 0 || nR >= N || nC >= N) {
				continue;
			}

			if (visited[nR][nC]) {
				continue;
			}

			if (map[nR][nC] == 0) {
				island_queue.add(new Pair(r, c, idx));
			} else {
				checkSide(nR, nC);
			}
		}
		return;
	}

	private static class Pair {
		int r;
		int c;
		int num;

		Pair(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	} // end of Pair
} // end of class
