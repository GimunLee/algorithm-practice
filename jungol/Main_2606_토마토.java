import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int r;
	int c;
	int h;

	Pair(int r, int c, int h) {
		this.r = r;
		this.c = c;
		this.h = h;
	}
}

public class Main_2606_토마토 {
	static int[][][] map;
	static int[][][] visited;
	static Queue<Pair> q;
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int C = Integer.parseInt(st.nextToken()); // 열
		int R = Integer.parseInt(st.nextToken()); // 행
		int H = Integer.parseInt(st.nextToken()); // 높이

		map = new int[R][C][H];
		visited = new int[R][C][H];

		q = new LinkedList<Pair>();

		int check = 0;

		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < C; c++) {
					map[r][c][h] = Integer.parseInt(st.nextToken());
					if (map[r][c][h] == 1) {
						q.add(new Pair(r, c, h));
						visited[r][c][h] = 1;
					}

					if (map[r][c][h] != 0) {
						check++;
					}
				}
			}
		}

		if (check == R * C * H) {
			System.out.println(0);
			return;
		}

		bfs();

//		System.out.println("================================");
//		for (int i = 0; i < map[0][0].length; i++) {
//			for (int j = 0; j < map.length; j++) {
//				for (int j2 = 0; j2 < map[0].length; j2++) {
//					System.out.print((map[j][j2][i]) + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
//		System.out.println("================================");

		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c][h] == 0) {
						System.out.println(-1);
						return;
					}

					if (ans < visited[r][c][h]) {
						ans = visited[r][c][h];
					}
				}
			}
		}

		System.out.println(ans - 1);

	} // end of main

	private static void bfs() {
		while (!q.isEmpty()) {
			Pair tomato = q.poll();
			int r = tomato.r;
			int c = tomato.c;
			int h = tomato.h;
			map[r][c][h] = 1;

			for (int i = 0; i < dr.length; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];
				int nH = h + dh[i];

				if (!inRange(nR, nC, nH)) {
					continue;
				}

				if (visited[nR][nC][nH] == 0 && map[nR][nC][nH] == 0) {
					visited[nR][nC][nH] = visited[r][c][h] + 1;

					q.add(new Pair(nR, nC, nH));
				}
			}

//			for (int i = 0; i < map[0][0].length; i++) {
//				for (int j = 0; j < map.length; j++) {
//					for (int j2 = 0; j2 < map[0].length; j2++) {
//						System.out.print((visited[j][j2][i]) + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
//			System.out.println("================================");
		}
	}

	private static boolean inRange(int r, int c, int h) {
		if (r < 0 || c < 0 || h < 0 || r > map.length - 1 || c > map[0].length - 1 || h > map[0][0].length - 1) {
			return false;
		}
		return true;
	}

} // end of class
