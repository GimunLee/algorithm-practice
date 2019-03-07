import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1840_치즈_DFS {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] map;
	static boolean[][] visited;

	static int cnt = 0;
	static int deleteCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cnt++;
				}
			}
		}

		// 시작은 고정 0,0
		int time = 0;
		while (true) {
			time++;
			visited = new boolean[R][C];

//			System.out.println("===================================");
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			deleteCnt = 0;
			
			dfs(0, 0); // 한 텀에 치즈를 지우고 나온다.

			
			if (cnt == 0) {
				System.out.println(time);
				System.out.println(deleteCnt);
				break;
			}
		}

	}

	private static void dfs(int r, int c) {
		if (r < 0 || c < 0 || r > map.length - 1 || c > map[0].length - 1) {
			return;
		}

		if (visited[r][c]) {
			return;
		}

		visited[r][c] = true;

		if (map[r][c] == 1) {
			map[r][c] = 0; // 치즈 녹이기
			deleteCnt++;
			cnt--;
			return;
		}

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			dfs(nR, nC);
		}

	}
}
