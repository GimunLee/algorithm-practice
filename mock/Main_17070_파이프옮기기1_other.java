package mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ :: 17070 파이프 옮기기1

public class Main_17070_파이프옮기기1_other {
	static int N;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0;
		dfs(0, 1, 1);

		System.out.println(cnt);

	} // end of main

	static int cnt;
	static int[] dy = { 1, 0, 1 };
	static int[] dx = { 0, 1, 1 }; // 아래 오른쪽 대각선

	public static void dfs(int y, int x, int type) {
		// type
		// 0 : 세로
		// 1 : 가로
		// 2 : 대각선

		// visit[y][x]=true;
		System.out.println(y + "," + x);
		if (y == N - 1 && x == N - 1) { // 도착
			System.out.println("도착");
			cnt++;
			return;
		}

		int[] Dir = getDir(type);

		for (int i = 0; i < Dir.length; i++) {

			int ny = y + dy[Dir[i]];
			int nx = x + dx[Dir[i]];

			if (ny < 0 || ny > N - 1 || nx < 0 || nx > N - 1 || map[ny][nx] != 0)
				continue;
			// 대각선으로 이동시 주변 4칸이 확보되어 있어야 한다.
			if (Dir[i] == 2 && (map[y][x + 1] != 0 || map[y + 1][x] != 0))
				continue;

			dfs(ny, nx, Dir[i]);
		}
	}

	public static int[] getDir(int type) {

		// type
		// 0 : 세로
		// 1 : 가로
		// 2 : 대각선

		// 아래 오른쪽 대각선
		if (type == 0) { // 세로
			int[] ret = { 0, 2 };
			return ret;
		}
		if (type == 1) { // 가로
			int[] ret = { 1, 2 };
			return ret;
		}
		if (type == 2) {
			int[] ret = { 0, 1, 2 };
			return ret;
		}
		return null;
	}

}