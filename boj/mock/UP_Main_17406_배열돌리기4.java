package boj.mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2019.08.17.(토)
 * 2019.08.25.(SUN) Upload
 */
public class UP_Main_17406_배열돌리기4 {
	// 반시계 방향 (하우상좌)
	private static int[] dr = { 1, 0, -1, 0 }; 
	private static int[] dc = { 0, 1, 0, -1 };
	
	private static int[][] map, mapClone; // 원본 map과 한번의 순열마다 변화시킬 mapClone 변수
	private static int N, M, K; // 주어진 제한을 저장할 변수
	private static Pair[] set; // 회전 연산을 순열로 뽑아 저장할 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행(row) 제한
		M = Integer.parseInt(st.nextToken()); // 열(column) 제한
		K = Integer.parseInt(st.nextToken()); // 연산 횟수
		map = new int[N + 1][M + 1]; // 원본 격자판 변수
		mapClone = new int[N + 1][M + 1]; // 한번의 순열당 회전시키며 변화시킬 복사 격자판 변수
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				mapClone[r][c] = map[r][c];
			}
		}		
		set = new Pair[K]; // 연산의 순서를 순열로 뽑아 저장할 변수
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			set[i] = new Pair(
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		// -- end of for(Input)

		ANS = Integer.MAX_VALUE; // 정답 변수
		visited = new boolean[K]; // 순열을 뽑을 때, 참고할 변수 (뽑은 수인지)
		rotateOrder = new int[K]; // 연산의 순서를 순열로 저장할 변수
		
		solve(0); // 연산의 순서를 순열로 뽑고, 회전시키기
		
		System.out.println(ANS); // 정답 출력
	} // end of main

	private static int ANS;
	private static boolean[] visited; 
	private static int[] rotateOrder;

	private static void solve(int len) {
		if (len == K) { // 주어진 연산 횟수만큼 뽑았다면,
			for (int i = 0; i < K; i++) { // 하나씩 회전시켜본다.
				rotate(set[rotateOrder[i]]); // 회전시키기
			}
			int tmp = getMinSum(); // 회전 후, 가장 작은 행의 합 저장
			ANS = ANS > tmp ? tmp : ANS; // 정답 갱신

			// 더욱 최적의 해를 찾기 위해서 맵을 원상 복귀 시킨다.
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

	/** 주어진 연산으로 회전시키기 */
	private static void rotate(Pair pair) {
		for (int s = 1; s <= pair.s; s++) { // 주어진 사이즈만큼 회전 시키기
			int r = pair.r - s; // 시작 행
			int c = pair.c - s; // 시작 열

			int tmp = mapClone[r][c]; // 시계 방향으로 회전 시 시작점 바로 오른쪽 위치에 해당 값을 저장하기 위해 임시 저장
			int dir = 0; // 반시계 방향으로 탐색
			while (dir < 4) { // 4방향을 모두 돌았다면
				int nR = r + dr[dir];
				int nC = c + dc[dir];

				if (nR <= pair.r + s && nC <= pair.c + s && nR >= pair.r - s && nC >= pair.c - s) { 
					// 한 방향으로 탐색할 수 있는 경우
					mapClone[r][c] = mapClone[nR][nC]; // 반시계 방향으로 최신화
					r = nR;
					c = nC;
				} else { // 한 방향으로 탐색이 끝난 경우 
					dir++; // 방향 바꾸기
				}
			}
			mapClone[pair.r - s][pair.c - s + 1] = tmp; // 회전을 모두 끝냈으므로, 시작값을 바로 오른쪽에 저장한다.
		}
	}

	/** 격자판에서 행의 합이 가장 작은 수를 뽑는 함수 */
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

	/** 연산을 저장할 클래스 변수 */
	private static class Pair {
		int r, c, s; 

		public Pair(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	} // end of Pair
} // end of class
