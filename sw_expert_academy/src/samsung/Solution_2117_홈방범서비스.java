package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2117_홈방범서비스 {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N;
	private static int M;
	private static int homeTotalCnt;
	private static int[][] queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 도시의 크기 (5 <= N <= 20)
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용 (1 <= M <= 10)

			int[][] map = new int[N][N];
			homeTotalCnt = 0;
			ans = Integer.MIN_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); // 1은 집이 위치한 곳
					if (map[r][c] == 1) {
						homeTotalCnt++;
					}
				}
			} // end of for(input)


			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					checkRhombus(map, new boolean[N][N], r, c);
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());

	} // end of main

	private static int ans;

	/** 마름모 확인 */
	private static void checkRhombus(int[][] map, boolean[][] visited, int r, int c) {
		queue = new int[3000][2];
		int front = -1;
		int rear = -1;

		queue[++rear][0] = r;
		queue[rear][1] = c;

		int homeCnt = 0;

		int tmpK = 1;
		while (front != rear) {
			int size = rear;

			while (front != size) {
				int rr = queue[++front][0];
				int cc = queue[front][1];
				visited[rr][cc] = true;

				if (map[rr][cc] == 1) {
					homeCnt++;
				}

				for (int j = 0; j < dr.length; j++) {
					int nR = rr + dr[j];
					int nC = cc + dc[j];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) {
						continue;
					}
					if (visited[nR][nC]) {
						continue;
					}

					visited[nR][nC] = true;
					queue[++rear][0] = nR;
					queue[rear][1] = nC;
				}
			} // end of while(one K)
			int ansTemp = getBenefit(homeCnt, getCostByK(tmpK));
			ans = ans < ansTemp ? ansTemp : ans;

			if (homeCnt == homeTotalCnt) { // 이미 해당 점에서 모든 점을 봤을때,
				return;
			}

			tmpK++;
		}
	}

	private static int getCostByK(int K) {
		return K * K + (K - 1) * (K - 1);
	}

	private static int getBenefit(int homeCnt, int tK) {
		return (homeCnt * M) - tK;
	}
} // end of class
