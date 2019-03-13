package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 버스가 장애물을 피해 운행하여 목적지까지 도착할 수 있는 경로의 수를 구하라. 지도는 NxN 크기이며, 버스는 (0,0)에서 출발하여
 * (N-1, N-1)에 도착해야한다. 버스가 운행할 수 있는 방향은 다음과 같은 세가지다. 목적지로부터 멀이지는 방향은 없다. 또한 운행하기
 * 위해서는 아래처럼 버스가 점유해야하는 길에 장애물이 없어야한다. 버스가 진행할때는 점진적으로 방향을 바꿔야한다. (바로 못 바꾼다)
 * 마지막으로 출발과 도착지점에서 버스는 가로 혹은 세로방향이어야한다.
 *
 * 시간복잡도 : 선택하는경우(3) * 선택하는경우(3).... 30번 / 3^30정도 (조금 더 적지만)
 * 
 * DFS (스택) - 반복문으로하면 백트랙킹이 어렵다. BFS (큐) - 콜트리를 다 가지고 있어야함. 스택 vs 큐 - 큐가 메모리에
 * 취약하다.
 * -------------------------------------------------------------------------------------
 * 시뮬레이션 DFS (반복 : 속도는 빠르지만 백트랙킹하기 어렵다, 재귀 : 속도는 느리지만 백트랙킹하기 쉽다)
 */

public class 삼성문제복기_1번_버스1 {
	private static int cnt;
	private static int[][] m;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			m = new int[N + 1][N + 1]; // 연산시간 이득보기위함

			for (int i = 0; i < m.length; i++) {
				m[i][N] = 1;
				m[N][i] = 1;
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 쪼갤때는 trim() 안해도됨
				for (int j = 0; j < N; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			cnt = 0; // 도착지점까지 갈 수 있는 경로의 수
			if (m[0][0] == 0 && m[0][1] == 0) { // 가로로 버스를 놓을 수 있는 경우
				dfs(0, 0, 0);
			}
			if (m[0][0] == 0 && m[1][0] == 0) { // 세로로 버스를 놓을 수 있는 경우
				dfs(0, 0, 2);
			}
			System.out.println("#" + tc + " " + cnt);
		} // end of for TestCase
	} // end of main

	/** r행 c열 mode : 방향, 0:가로, 1:대각, 2:세로 (r,c) 버스의 꼬리 좌표 */
	public static void dfs(int r, int c, int mode) {
		if (mode == 0) {// 가로
			if (r == N - 1 && c + 1 == N - 1) {// 도착위치인지 체크
				cnt++;
			} else {// 도착이 아니면 한칸 전진 : 가로 / 대각
				if (m[r][c + 2] == 0) {
					dfs(r, c + 1, 0); // 가로
				}

				if (m[r][c + 2] == 0 && m[r + 1][c + 2] == 0 && m[r + 1][c + 1] == 0) {
					dfs(r, c + 1, 1); // 대각
				}
			}
		} else if (mode == 1) {// 대각
			// 도착이 아니므로 한칸 전진 : 가로/ 대각/ 세로
			if (m[r + 1][c + 2] == 0) {
				dfs(r + 1, c + 1, 0); // 가로
			}
			if (m[r + 1][c + 2] == 0 && m[r + 2][c + 1] == 0 && m[r + 2][c + 2] == 0) {
				dfs(r + 1, c + 1, 1); // 대각
			}
			if (m[r + 2][c + 1] == 0) {
				dfs(r + 1, c + 1, 2); // 세로
			}
		} else {// 세로
			if (r + 1 == N - 1 && c == N - 1) {// 도착위치인지 체크
				cnt++;
			} else {// 도착이 아니면 한칸 전진 : 대각/세로
				if (m[r + 2][c] == 0) {
					dfs(r + 1, c, 2); // 세로
				}

				if (m[r + 1][c + 1] == 0 && m[r + 2][c] == 0 && m[r + 2][c + 1] == 0) {
					dfs(r + 1, c, 1); // 대각
				}

			}
		}
	}
} // end of class
