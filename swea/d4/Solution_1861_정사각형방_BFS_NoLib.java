package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Solution_1861_정사각형방_BFS_NoLib {
	static int[][] map;
	static boolean[][] visited;

	static int[][] queue;
	static int front = -1;
	static int rear = -1;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N*N
			map = new int[N][N];
			start = Integer.MAX_VALUE;
			ans = Integer.MIN_VALUE;

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[N][N];
			queue = new int[5000][2];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					front = -1;
					rear = -1;

					queue[++rear][0] = r;
					queue[rear][1] = c;

					bfs(r, c);
				}
			}

			System.out.println("#" + tc + " " + start + " " + ans);

		} // end of for of TestCase
	} // end of main

	static int start = Integer.MAX_VALUE;
	static int ans = Integer.MIN_VALUE;

	private static void bfs(int r, int c) {
		int tmpAns = 0;
		int tmpStart = map[r][c];

		while (front != rear) {
			int cR = queue[++front][0];
			int cC = queue[front][1];
			tmpAns++;

			int temp = rear - front;
			for (int i = 0; i < dr.length; i++) {
				int nR = cR + dr[i];
				int nC = cC + dc[i];

				if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map[0].length - 1) {
					continue;
				}

				if (map[cR][cC] + 1 == map[nR][nC]) { // 현재보다 다음이 정확히 1보다 크다면,
					queue[++rear][0] = nR;
					queue[rear][1] = nC;
				}
			}

			if (temp == (rear - front)) {
				break;
			}
		}

		if (ans < tmpAns) {
			start = tmpStart;
			ans = tmpAns;
		}

		if (ans == tmpAns) {
			if (tmpStart < start) {
				start = tmpStart;
			}
		}
		return;
	} // end of bfs()
} // end of class
