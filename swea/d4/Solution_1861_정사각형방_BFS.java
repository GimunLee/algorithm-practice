package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1861_정사각형방_BFS {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 1~1000

			int[][] map = new int[N + 2][N + 2];

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 1~N^2
				}
			}

			int ans = 0;
			int start = Integer.MAX_VALUE;
			int tmpStart = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					Queue<Pair> q = new LinkedList<>();
					q.add(new Pair(i, j, 1));

					while (!q.isEmpty()) {
						Pair p = q.poll();
						int x = p.x;
						int y = p.y;
						int cnt = p.cnt;
						int num = map[x][y];

						if (cnt > ans) { // 갱신이면
							ans = cnt;
							start = map[i][j];
						}

						if (cnt == ans) { // ans와 cnt 동일하면 = 갱신 X
							tmpStart = map[i][j];
							if (start > tmpStart) { // start만 갱신
								start = tmpStart;
							}
						}

						for (int k = 0; k < 4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];

							if (map[nx][ny] == 0)
								continue; // index 넘어감
							if (map[nx][ny] == num + 1) {
								q.add(new Pair(nx, ny, cnt + 1));
							}
						}
					}
				}
			}
			sb.append("#" + tc + " " + start + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	static class Pair {
		int x;
		int y;
		int cnt;

		public Pair(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}