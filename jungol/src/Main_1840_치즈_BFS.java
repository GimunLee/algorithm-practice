import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1840_치즈_BFS {
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pair2> q;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		q = new LinkedList<Pair2>();
		int cnt = 0;

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cnt++;
				}
			}
		}

		int time = 0;
		int curCnt = 0;

		while (true) {
			time++;
			visited = new boolean[R][C];
			q.add(new Pair2(0, 0));

			curCnt = cnt;

			while (!q.isEmpty()) {
				Pair2 air = q.poll();
				int cR = air.r;
				int cC = air.c;

				for (int i = 0; i < dr.length; i++) {
					int nR = cR + dr[i];
					int nC = cC + dc[i];

					if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map[0].length - 1) {
						continue;
					}
					if (visited[nR][nC]) {
						continue;
					}

					if (map[nR][nC] == 1) { // 치즈 녹이기
						map[nR][nC] = 0;
						cnt--;
					} else {
						q.add(new Pair2(nR, nC));
					}
					visited[nR][nC] = true;
				}
			}

			if (cnt == 0) {
				System.out.println(time);
				System.out.println(curCnt);
				return;
			}
		}
	} // end of main
} // end of class

class Pair2 {
	int r;
	int c;

	Pair2(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
