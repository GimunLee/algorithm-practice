package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {
	private static final int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 }; // 상하좌우
	private static int[][] dirByType = { { 0, 0, 0, 0 }, // 빈칸
			{ 1, 2, 3, 4 }, // 1
			{ 1, 2, 0, 0 }, // 2 상하 가능
			{ 3, 4, 0, 0 }, // 3 좌우 가능
			{ 1, 4, 0, 0 }, // 4 상우 가능
			{ 2, 4, 0, 0 }, // 5 하우 가능
			{ 2, 3, 0, 0 }, // 6 하좌 가능
			{ 1, 3, 0, 0 } // 7 상좌 가능
	};

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Queue<Pair> queue = new LinkedList<>();
			int N = Integer.parseInt(st.nextToken()); // 세로 크기
			int M = Integer.parseInt(st.nextToken()); // 가로 크기
			int R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 r
			int C = Integer.parseInt(st.nextToken()); // 맨홀 뚜겅 c
			int L = Integer.parseInt(st.nextToken()); // 소요된 시간
			int[][] map = new int[N][M];
			queue.add(new Pair(R, C));

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 1;
			int time = 1;
			boolean[][] visited = new boolean[N][M];

			while (!queue.isEmpty()) {
				if (time++ == L) {
					break;
				}
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					Pair p = queue.poll();
					int curType = map[p.r][p.c]; // 현재 있는 곳의 하수구 type
					visited[p.r][p.c] = true;

					for (int d = 0; d < dirByType[curType].length; d++) {
						int dir = dirByType[curType][d];
						if (dir == 0) { // 갈 수 없는 경우가 나오면
							break;
						}
						int nR = p.r + dr[dir];
						int nC = p.c + dc[dir];

						if (nR < 0 || nC < 0 || nR >= N || nC >= M) { // 범위를 벗어날 때,
							continue;
						}
						if (visited[nR][nC]) {
							continue;
						}
						// 갈 수 있는지 확인
						if (canGo(map[nR][nC], dir)) {
							answer++;
							queue.add(new Pair(nR, nC));
							visited[nR][nC] = true;
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		} // end of for(testCase)
		System.out.println(sb.toString());
	} // end of main

	/** 다음 칸에 설치된 하수구에 해당 dir로 갈때 갈 수 있는지 체크 */
	private static boolean canGo(int type, int dir) {
		boolean flag = false;
		switch (type) {
		case 1: // 모두 가능
			flag = true;
			break;
		case 2: // 상하 가능
			if (dir == 1 || dir == 2)
				flag = true;
			break;
		case 3: // 좌우 가능
			if (dir == 3 || dir == 4)
				flag = true;
			break;
		case 4: // 하좌 가능
			if (dir == 2 || dir == 3)
				flag = true;
			break;
		case 5: // 상좌 가능
			if (dir == 1 || dir == 3)
				flag = true;
			break;
		case 6: // 상우 가능
			if (dir == 1 || dir == 4)
				flag = true;
			break;
		case 7: // 하우 가능
			if (dir == 2 || dir == 4)
				flag = true;
			break;
		}
		return flag;
	}

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
} // end of class
