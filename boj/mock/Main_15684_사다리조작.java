package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15684_사다리조작 {
	private static int[] chkLineCnt; // 각 세로선마다 놓인 가로선의 수
	private static int N, H, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로선의 수 (column)
		M = Integer.parseInt(st.nextToken()); // 가로선의 수 (row)
		H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 수

		int[][] map = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}
		if (H == 0) {
			System.out.println(0);
			return;
		}
		ANSER = Integer.MAX_VALUE;
		solve(map, 1, 0); // 1부터 쭉 뽑아봄
		if (ANSER == Integer.MAX_VALUE || ANSER > 3) {
			System.out.println("-1");
		} else {
			System.out.println(ANSER);
		}
	} // end of main

	private static int ANSER;

	// chooseC와 chooseC+1 사이에 사다리를 놓는다. (chooseC에서 chooseC+1로 가는 길)
	private static void solve(int[][] map, int chooseC, int cnt) {
		if (cnt > 3 || chooseC == N) {
			return;
		}

		if (check(map)) {
			ANSER = ANSER > cnt ? cnt : ANSER;
			return;
		}

		// 설치할 수 있다면
		for (int r = 1; r <= H; r++) { // 한 세로선에서 우측 모든 가로를 본다.
			if (map[r][chooseC - 1] != 1 && map[r][chooseC] != 1 && map[r][chooseC + 1] != 1) { // 놓을수
				// 다리를 놓고 보낸다.
				map[r][chooseC] = 1;
				chkLineCnt[chooseC]++;
				chkLineCnt[chooseC + 1]++;
				solve(map, chooseC, cnt + 1);
				map[r][chooseC] = 0;
				chkLineCnt[chooseC]--;
				chkLineCnt[chooseC + 1]--;
			}
		}
		solve(map, chooseC + 1, cnt);
	}

	private static boolean check(int[][] map) {
		for (int c = 1; c < N; c++) {
			int r = 1; // 항상 맨위부터 시작
			int moveC = c;
			while (true) {
				if (map[r][moveC - 1] == 1) { // 왼쪽으로 갈 수 있다면
					moveC--;
				} else if (map[r][moveC] == 1) { // 오른쪽으로 갈 수 있다면
					moveC++;
				}
				if (++r == H + 1) {
					break;
				}
			}
			if (moveC != c) {
				return false;
			}
		}
		return true;
	} // end of fucn(check)

	private static void print(int[][] map) {
		System.out.println();
		for (int r = 1; r < H + 1; r++) {
			for (int c = 1; c < N; c++) {
				System.out.print(" | ");
				System.out.print(map[r][c]);
				if (c == N - 1) {
					System.out.print(" | ");
				}
			}
			System.out.println();
		}
	} // end of func(print)
} // end of class
