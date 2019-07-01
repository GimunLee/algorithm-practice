package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Solution_1949_등산로조성 {
	private final static int[] dr = { -1, 1, 0, 0 }; // 행(상하)
	private final static int[] dc = { 0, 0, -1, 1 }; // 열(좌우)
	private static int[][] map; // 격자판
	private static boolean[][] visited; // 탐색했는지 확인하는 변수
	private static int N; // 격자판 행과 열 크기
	private static int K; // 지형을 깍을 수 있는 최대 깊이 
	private static int max; // 가장 높은 봉우리를 저장할 변수
	private static int ans; // 정답 변수

	private static boolean flag; // 한번 깎았는지 학인하는 변수

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) { // Test Case 수 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N : 맵 크기
			K = Integer.parseInt(st.nextToken()); // K : 공사 가능 깊이
			map = new int[N][N]; // 격자판 생성
			max = Integer.MIN_VALUE; // 최대 봉우리 저장 변수

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (max < map[r][c]) { 
						max = map[r][c];
					}
				}
			} // end of for(input)

			ans = Integer.MIN_VALUE; // 정답 변수

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == max) { // 최대 봉우리라면
						visited = new boolean[N][N]; 
						flag = false; // 첫 탐색에는 깎지 않은 상태
						dfs(r, c, 1); // 깊이우선탐색을 한다.
					}
				}
			} // end of for(Test)
			sb.append("#"+tc + " " + ans + "\n"); // 한번에 출력하기 위함 (실행시간 단축)
		} // end of for TestCase
		System.out.println(sb.toString());
	} // end of main

	/** len : 등산로의 길이 */
	private static void dfs(int r, int c, int len) {
		if (visited[r][c]) { // 종료 조건
			return;
		}

		ans = (ans < len)? len:ans; // 정답 갱신
		visited[r][c] = true;

		for (int i = 0; i < dr.length; i++) { // 4방향으로 탐색
			int nR = r + dr[i]; 
			int nC = c + dc[i];

			// 범위를 벗어났거나, 방문한 곳이면 다른 방향 탐색
			if (nR < 0 || nC < 0 || nR >= N || nC >= N || visited[nR][nC]) {
				continue;
			}

			if (map[r][c] <= map[nR][nC]) { // 깎아야 되는 경우,
				if (!flag) { // 한번도 깎지 않은 경우
					for (int j = 1; j <= K; j++) { // 1부터 K까지 깎은 모든 경우를 탐색함 
						int temp = map[nR][nC]; // 깎기 전 높이 저장
						map[nR][nC] -= j;
						if (map[r][c] > map[nR][nC]) { // 다음 위치의 높이가 더 작다면
							flag = true; // 깎았다는 것을 표기
							dfs(nR, nC, len + 1); // 다음 위치로 탐색 시작
							flag = false; // 최적의 경우가 아닐 수 있으므로 안 깎았다고 표기
						}
						map[nR][nC] = temp; // 봉우리 높이 원상 복귀
					}
				}
			} else { // 깎지 않아도 될 때,
				dfs(nR, nC, len + 1);
			}
		} 
		visited[r][c] = false;
	}
} // end of class
