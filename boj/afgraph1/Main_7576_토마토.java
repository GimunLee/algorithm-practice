package boj.afgraph1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	static boolean[][] visit;
	static int[][] map;
	static int[][] queue;
	static int front = -1;
	static int rear = -1;
	static int[][] tomato;
	static int index = 0;
	static HashSet<Integer> day;
	static int[][] direction = { // 상 하 좌 우
			{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static boolean inRange(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
			return false;
		}
		return true;
	}

	private static int bfs() {
		for (int i = 0; i < index; i++) {
			++rear;
			queue[rear][0] = tomato[i][0];
			queue[rear][1] = tomato[i][1];
			visit[tomato[i][0]][tomato[i][1]] = true;
		}

		while (front != rear) {
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]) + " ");
//			}
//			System.out.println("\n");

			++front;
			int x = queue[front][0];
			int y = queue[front][1];

			for (int i = 0; i < direction.length; i++) {
				int tx = x + direction[i][0];
				int ty = y + direction[i][1];

				if (inRange(tx, ty) && map[tx][ty] == 0) {
					map[tx][ty] = map[x][y] + 1;
					++rear;
					queue[rear][0] = tx;
					queue[rear][1] = ty;
				}
			}
		}

		int day = 0;
		boolean flag = false;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 0) { // 끝까지 탐색했는데 안 익은 토마토가 있는 경우
					flag = true;
				}
				if (map[i][j] > day) {
					day = map[i][j];
				}
			}
		}
		return flag ? -1 : day - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int M = Integer.parseInt(st.nextToken()); // 가로 칸
		int N = Integer.parseInt(st.nextToken()); // 세로 칸

		map = new int[N][M];
		visit = new boolean[N][M];
		queue = new int[N * M][2];
		tomato = new int[N * M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					tomato[index][0] = i;
					tomato[index][1] = j;
					index++;
				}
			}
		}

		int Answer = bfs(); // 0일부터 시작

		System.out.println(Answer);
	}
}
