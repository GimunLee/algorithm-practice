package boj.etc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1726 {
	static int ans;
	static int[][] map;
	static Position[] queue = new Position[40000]; // 4 * 100 * 100
	static int front = -1;
	static int rear = -1;
	static boolean[][][] visit;
	static int[] changeDir = { 0, 4, 1, 3, 2 };

	static boolean inRange(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
			return false;
		}
		return true;
	}

	static boolean goK(Position cp, int k) {
		int temp_x = cp.x;
		int temp_y = cp.y;

		switch (cp.dir) {
		case 1: // 동
			cp.y += k;
			break;
		case 2: // 서
			cp.y -= k;
			break;
		case 3: // 남
			cp.x += k;
			break;
		case 4: // 북
			cp.x -= k;
			break;
		}
		// 범위를 벗어났거나, 벽이이거나,
		if (!inRange(cp.x, cp.y) || map[cp.x][cp.y] == 1) {
			cp.x = temp_x;
			cp.y = temp_y;
			return false;
		}
		return true;
	}

	/** 0: 왼쪽, 1:오른쪽 */
	static int turnDir(int cur_dir, int dir) {
		int index = 0;
		if (dir == 0) { // 왼쪽
			index = (--cur_dir < 0) ? 4 : cur_dir;
			return changeDir[index];
		} else if (dir == 1) { // 오른쪽
			index = (++cur_dir > 3) ? 1 : cur_dir;
			return changeDir[index];
		}
		return -1;
	}

	static void search(Position sp, Position dp) {
		queue[++rear] = sp;
		visit[sp.x][sp.y][sp.dir] = true;

		while (front != rear) {
			Position cp = queue[++front];

			if (cp.x == dp.x && cp.y == dp.y && cp.dir == dp.dir) { // 정답일 경우
				return;
			}

			for (int i = 0; i <= 1; i++) {
				if (visit[cp.x][cp.y][i])
					continue;
				else {
					turnDir(cp.dir, i);
					ans++;
					if (!visit[cp.x][cp.y][cp.dir]) {
						visit[cp.x][cp.y][cp.dir] = true;
						queue[++rear] = cp;
					}
				}
			}

			for (int i = 1; i <= 3; i++) { // 최대 3칸
				if (!goK(cp, i))
					break;
				else if (visit[cp.x][cp.y][cp.dir]) { // 벽이거나, 범위를 벗어났으면
					continue;
				}
				visit[cp.x][cp.y][cp.dir] = true;
				queue[++rear] = cp;
				ans++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visit = new boolean[N + 1][M + 1][5];
		ans = 0;

		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int y = 0; y < M; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		Position sp = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine(), " ");
		Position dp = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));

		search(sp, dp);

		System.out.println(ans);
	}
}

class Position {
	int x;
	int y;
	int dir;

	Position(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
