package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.08.17.(토)
 */
public class Main_17406_배열돌리기4 {
	private static int[][] map, mapRotate, mapClone;
	private static int N, M, K;
	private static Pair[] set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 연산 횟수
		map = new int[N + 1][M + 1];
		mapRotate = new int[N + 1][M + 1];
		mapClone = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				mapRotate[r][c] = map[r][c];
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
				rotate(set[rotateOrder[i]]);
			}
			int tmp = getMinSum();
			ANS = ANS > tmp ? tmp : ANS;

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					mapClone[r][c] = map[r][c];
					mapRotate[r][c] = map[r][c];
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

	private static void rotate(Pair pair) {
		int startR = pair.r - pair.s;
		int endR = pair.r + pair.s;
		int startC = pair.c - pair.s;
		int endC = pair.c + pair.s;
		
		while (true) {
			if (startR == endR || startC == endC) {
				break;
			}
			for (int c = startC + 1; c <= endC; c++) {
				mapRotate[startR][c] = mapClone[startR][c - 1];
			}
			for (int r = startR + 1; r <= endR; r++) {
				mapRotate[r][endC] = mapClone[r - 1][endC];
			}
			for (int c = endC - 1; c >= startC; c--) {
				mapRotate[endR][c] = mapClone[endR][c + 1];
			}
			for (int r = endR - 1; r >= startR; r--) {
				mapRotate[r][startC] = mapClone[r + 1][startC];
			}
			startR++; endR--;
			startC++; endC--;
		}
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				mapClone[r][c] = mapRotate[r][c];
			}
		}
		return;
	} // end of func(rotate)

	private static int getMinSum() {
		int min = Integer.MAX_VALUE;
		for (int r = 1; r <= N; r++) {
			int tmp = 0;
			for (int c = 1; c <= M; c++) {
				tmp += mapClone[r][c];
				if (tmp > min) {
					break;
				}
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
