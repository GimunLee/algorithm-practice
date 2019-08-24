package mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2019.08.17.(토)
 */
public class Main_17406_배열돌리기4 {
	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };
	private static int[][] map, mapClone;
	private static int N, M, K;
	private static Pair[] set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 연산 횟수
		map = new int[N + 1][M + 1];
		mapClone = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				mapClone[r][c] = map[r][c];
			}
		}
		set = new Pair[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			set[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		// -- end of Input

		ANS = Integer.MAX_VALUE;
		visited = new boolean[K];
		rotateOrder = new int[K];
		solve(0);
		System.out.println(ANS);
	} // end of main

	private static int ANS;
	private static boolean[] visited;
	private static int[] rotateOrder;

	private static void solve(int len) {
		if (len == K) {
			for (int i = 0; i < K; i++) {
				rotateNew(set[rotateOrder[i]]);
			}
			int tmp = getMinSum();
			ANS = ANS > tmp ? tmp : ANS;

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					mapClone[r][c] = map[r][c];
				}
			}
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				rotateOrder[len] = i;
				solve(len + 1);
				visited[i] = false;
			}
		}
	} // end of func(solve)

	private static void rotateNew(Pair pair) {
		for (int s = 1; s <= pair.s; s++) {
			int r = pair.r - s;
			int c = pair.c - s;

			int tmp = mapClone[r][c];
			int dir = 0;
			while (dir < 4) {
				int nR = r + dr[dir];
				int nC = c + dc[dir];

				if (nR <= pair.r + s && nC <= pair.c + s && nR >= pair.r - s && nC >= pair.c - s) {
					mapClone[r][c] = mapClone[nR][nC];
					r = nR;
					c = nC;
				} else {
					dir++;
				}
			}
			mapClone[pair.r - s][pair.c - s + 1] = tmp;
		}
	}

	private static int getMinSum() {
		int min = Integer.MAX_VALUE;
		for (int r = 1; r <= N; r++) {
			int tmp = 0;
			for (int c = 1; c <= M; c++) {
				tmp += mapClone[r][c];
			}
			min = tmp < min ? tmp : min;
		}
		return min;
	} // end of func(getMinSum)

	private static class Pair {
		int r, c, s;

		public Pair(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	} // end of Pair
} // end of class
