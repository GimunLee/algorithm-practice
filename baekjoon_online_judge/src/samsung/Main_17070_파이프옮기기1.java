package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
	static int[] dr = { 0, 1, 1 }; // 우, 하, 대각선
	static int[] dc = { 1, 0, 1 };
	private static int[][] map;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 방의 크기
		map = new int[N + 1][N + 1]; // 외곽은 안쓴다.

		for (int i = 0; i < map.length; i++) {
			map[i][N] = 1;
		}
		for (int i = 0; i < map[0].length; i++) {
			map[N][i] = 1;
		}

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); // 맵 채우기
			}
		}

		// 시작 위치 (1,1) (1,2) 가로

		dfs(0, 1, 0, 0);
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		System.out.println(ans);

	} // end of main

	private static int ans = 0;

	/** r, c, pos : 현재 위치 (0:가로, 1:세로, 2:대각), dir : 갈 방향(0:우,1:하,2:대각) */
	private static void dfs(int r, int c, int pos, int dir) {
		map[r][c] = 5;
//		System.out.println("r : " + r + ", " + "c : " + c);

		if (r == N-1 && c == N-1) { // 도착지점에 도달했을때, 답인 경우
//			System.out.println("*\t\t r : " + r + ", " + "c : " + c + ",  " + pos + ", " + dir);
			ans++;
			return;
		}

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			if (map[nR][nC] == 1) {
				continue;
			}
			
			// 대각선일 때, 
			if (i == 2 && (map[nR - 1][nC] == 1 || map[nR][nC - 1] == 1)) {
				continue;
			}
			
			if(pos== 0 && i == 1) { // 세로일 때, 가로는 안됨
				continue;
			}
			if(pos== 1 && i == 0) { // 가로일 때, 세로는 안됨
				continue;
			}

			dfs(nR, nC, i, i);
		}

	} // end of main
} // end of class
