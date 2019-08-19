package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Solution_2117_홈방범서비스 {
	private static int[] dr = { -1, 1, 0, 0 }; private static int[] dc = { 0, 0, -1, 1 };
	private static int N, M; // N : 도시의 크기, M : 하나의 집이 지불할 수 있는 비용
	private static int homeTotalCnt; // 집의 총 갯수
	private static int ans;
	private static int[][] queue; // 홈 방법을 시작하는 위치

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 정답을 한번에 출력하기 위한 변수
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 도시의 크기 (5 <= N <= 20)
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용 (1 <= M <= 10)

			int[][] map = new int[N][N]; // 맵 생성 
			homeTotalCnt = 0; // 집의 총 갯수를 저장할 변수
			ans = Integer.MIN_VALUE; // 정답 변수 (손해보지 않고 서비스 가능한 집의 수)
			queue = new int[500][2]; // 홈 방법을 시작하는 위치 변수 생성

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); 
					if (map[r][c] == 1) { // 집인 경우,
						homeTotalCnt++; // 집의 총 갯수를 증가시켜줌
					}
				}
			} // end of for(input)

			// 각 위치마다 홈 방법을 시작해봄
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					 // 마름모 모양으로 체크해봄
					solve(map, new boolean[N][N], r, c);
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
	
	/**
	 * 보안회사의 운영 비용 계산
	 * */
	private static int getCostByK(int K) {
		return K * K + (K - 1) * (K - 1);
	} // end of func(getCostByK)

	/**
	 * 보안회사의 이익을 계산
	 * */
	private static int getBenefit(int homeCnt, int k) {
		return (homeCnt * M) - k;
	} // end of func(getBenefit)

	/** 
	 * 마름모 모양으로 홈 방법을 했을때, 집의 갯수를 세봄 
	 * */
	private static void solve(int[][] map, boolean[][] visited, int r, int c) {
		int front = -1;
		int rear = -1;

		queue[++rear][0] = r;
		queue[rear][1] = c;

		int homeCnt = 0; // 해당 r,c 에서 홈 방범 서비스를 할 때, 범위 안에 있는 집의 갯수
		int tmpK = 1; // 보안회사의 운영 범위
		
		while (front != rear) {
			int size = rear; // 처음 운영 범위에서 서비스를 했을 때,
			while (front != size) {
				int rr = queue[++front][0];
				int cc = queue[front][1];
				visited[rr][cc] = true;

				if (map[rr][cc] == 1) { // 집인 경우
					homeCnt++;
				}

				for (int j = 0; j < dr.length; j++) {
					int nR = rr + dr[j];
					int nC = cc + dc[j];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // 범위를 벗어난 경우
						continue;
					}
					
					if (visited[nR][nC]) { // 이미 방문한 경우
						continue;
					}

					visited[nR][nC] = true;
					queue[++rear][0] = nR;
					queue[rear][1] = nC;
				}
			} // end of while(one K)
			int ansTemp = getBenefit(homeCnt, getCostByK(tmpK));
			if (ansTemp >= 0) { // 손해를 보지 않는 경우이므로, '0'도 포함시켜줘야함
				ans = ans < homeCnt ? homeCnt : ans; // 정답 갱신
			}
			 // 맵의 끝에서 끝까지 간 경우 또는 모든 집을 서비스 해줄때
			if ((tmpK > 2 * N - 1) || (homeCnt == homeTotalCnt)) { 
				return;
			}
			tmpK++; // 운영 범위를 늘려줌
		} // end of while(Queue)
	} // end of func(solve)
} // end of class
