package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NY_Solution_2117_홈방범서비스 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // Test Case 수
			int N = Integer.parseInt(st.nextToken()); // 5 <= N <= 20
			int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 10

			int[][] map = new int[N][N];
			Queue<Pair> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N]; 

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						q.add(new Pair(r, c));
					}
				}
			}

			while (!q.isEmpty()) {
				Pair p = q.poll();
				int r = p.r;
				int c = p.c;

				for (int i = 0; i < dr.length; i++) {
					int nR = r + dr[i];
					int nC = c + dc[i];
					
					if(nR < 0 || nC < 0 || nR >= N || nC >= N) {
						
					}
					
					
					
				}

			}

		}

	} // end of main

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
} // end of class
