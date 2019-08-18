import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1082_»≠ø∞ø°º≠≈ª√‚ {
	static char[][] map;
	static int[][] visited;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static boolean[][] visited_fire;

	static int[][] queue_fire;
	static int front_f = -1;
	static int rear_f = -1;

	static int[][] queue;
	static int front = -1;
	static int rear = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int R = Integer.parseInt(st.nextToken()); // row
		int C = Integer.parseInt(st.nextToken()); // column

		map = new char[R][C];

		visited = new int[R][C];
		visited_fire = new boolean[R][C];

		queue = new int[10000][2];
		queue_fire = new int[10000][2];

		int dr = 0;
		int dc = 0;

		for (int r = 0; r < R; r++) {
			String input = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == '*') {
					queue_fire[++rear_f][0] = r;
					queue_fire[rear_f][1] = c;
				}

				if (map[r][c] == 'S') {
					queue[++rear][0] = r;
					queue[rear][1] = c;
				}
				if (map[r][c] == 'D') {
					dr = r;
					dc = c;
				}
			}
		}

		bfs(queue[++front][0], queue[front][1]);

		if (visited[dr][dc] == 0) {
			System.out.println("impossible");
			return;
		}
		System.out.println(visited[dr][dc]);
	}

	private static void bfs(int r, int c) {
		visited[r][c]++;
		front--;

		while (true) {
			if (front == rear) {
				return;
			}

			// -- fire
			int temp_rear_fire = rear_f;
			while (front_f != rear_f) {
				int cur_r_fire = queue_fire[++front_f][0];
				int cur_c_fire = queue_fire[front_f][1];

				for (int i = 0; i < dir.length; i++) {
					int next_r_fire = cur_r_fire + dir[i][0];
					int next_c_fire = cur_c_fire + dir[i][1];
					if (inRange(next_r_fire, next_c_fire)) { // π¸¿ß æ»¿Ã∞Ì,
						if (visited_fire[next_r_fire][next_c_fire]) {
							continue;
						}
						if (map[next_r_fire][next_c_fire] == '.') { // ∫Ûƒ≠¿Ã∂Û∏È,
							queue_fire[++rear_f][0] = next_r_fire;
							queue_fire[rear_f][1] = next_c_fire;
							map[next_r_fire][next_c_fire] = '*';
							visited_fire[next_r_fire][next_c_fire] = true;
						}
					}
				}
				if (temp_rear_fire == front_f) {
					break;
				}
			}

			int temp_rear = rear;
			while (front != rear) {
				// -- human
				int cur_r = queue[++front][0];
				int cur_c = queue[front][1];

				for (int i = 0; i < dir.length; i++) {
					int next_r = cur_r + dir[i][0];
					int next_c = cur_c + dir[i][1];

					if (inRange(next_r, next_c)) { // π¸¿ß æ»¿Ã∞Ì,
						if (visited[next_r][next_c] != 0) {
							continue;
						}

						if (map[next_r][next_c] == '.') { // ∫Ûƒ≠¿Ã∂Û∏È,
							queue[++rear][0] = next_r;
							queue[rear][1] = next_c;
							visited[next_r][next_c] = visited[cur_r][cur_c] + 1;
						}
						if (map[next_r][next_c] == 'D') {
							visited[next_r][next_c] = visited[cur_r][cur_c];
							return;
						}
					}
				}

				if (temp_rear == front) {
					break;
				}
			}
		}
	}

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r > map.length - 1 || c > map[0].length - 1) {
			return false;
		}
		return true;
	}
}
