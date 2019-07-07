package af_graph1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 예정_Main_10026_적록색약_BFS {
	static char[][] map;
	static boolean[][] visited;
	static int ans_normal;
	static int ans_special;
	static int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우

	static int[][] queue;
	static int front = -1, rear = -1;

	private static boolean inRange(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= map.length || nc >= map.length) {
			return false;
		}
		return true;
	}

	private static void bfs(int r, int c) {
		int front = -1, rear = -1;
		rear++;
		queue[rear][0] = r;
		queue[rear][1] = c;
		visited[r][c] = true;

		while (front != rear) {
			++front;
			int cr = queue[front][0];
			int cc = queue[front][1];

			for (int i = 0; i < direction.length; i++) {
				int nr = cr + direction[i][0];
				int nc = cc + direction[i][1];

				if (!inRange(nr, nc)) {
					continue;
				}
				
				if (!visited[nr][nc] && map[nr][nc] == map[r][c]) {
					visited[nr][nc] = true;
					++rear;
					queue[rear][0] = nr;
					queue[rear][1] = nc;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // N x N 행렬

		map = new char[N][N];
		visited = new boolean[N][N];
		queue = new int[N*N][2];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					ans_normal++;
				}
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
				visited[i][j] = false;
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					ans_special++;
				}
			}
		}

		System.out.println(ans_normal + " " + ans_special);

	}
}
