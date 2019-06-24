package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Main_17070_파이프옮기기 {
	static final int[] dr = { 0, 1, 1 }; // 행(우, 하, 대각선)
	static final int[] dc = { 1, 0, 1 }; // 열(우, 하, 대각선)
	private static int[][] map; // 격자판 2차원 배열 변수
	private static int N; // 격자판 크기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 격자판 크기
		map = new int[N + 1][N + 1]; // 외곽은 안쓰기 때문에 빈칸으로 설정

		for (int i = 0; i < map.length; i++) {
			map[i][N] = 1;
			map[N][i] = 1;
		}

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		// 파이프 시작 위치 (1,1) (1,2) 가로 방향
		dfs(0, 1, 0, 0); // 배열은 0부터 시작하기 때문에 문제에서 주어진 것보다 1씩 빼서 진행
		System.out.println(ans); // 정답 출력
	} // end of main

	private static int ans = 0; // 정답을 저장할 변수

	/** r:행 좌표, c:열좌표, pos:현재 위치(0:가로, 1:세로, 2:대각), dir : 파이프가 갈 방향(0:우,1:하,2:대각) */
	private static void dfs(int r, int c, int pos, int dir) {
		map[r][c] = 5;

		if (r == N - 1 && c == N - 1) { // 도착지점에 도달했을때
			ans++; // 정답 증가
			return;
		}

		// 3방향 탐색 시작
		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			// 벽인 경우
			if (map[nR][nC] == 1) {
				continue;
			}

			// 대각선일 때,
			if (i == 2 && (map[nR - 1][nC] == 1 || map[nR][nC - 1] == 1)) {
				continue;
			}

			// 파이프 방향이 세로일 때, 가로는 안됨
			if (pos == 0 && i == 1) {
				continue;
			}
			// 파이프 방향이 가로일 때, 세로는 안됨
			if (pos == 1 && i == 0) {
				continue;
			}

			// 모든 조건에 부합하는 경우, 깊이우선탐색
			dfs(nR, nC, i, i);
		} // end of func(dfs)
	} // end of main
} // end of class
