package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.08.25.(SUN)
 * 2019.08.25.(SUN) Upload
 * */
public class UP_Main_1726_로봇 {
	// 동서남북 (0은 사용하지 않음 / 우좌하상)
	private static int[] dr = { 0, 0, 0, 1, -1 };
	private static int[] dc = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken()); // 행(row) 제한
		int N = Integer.parseInt(st.nextToken()); // 열(column) 제한
		int[][] map = new int[M][N];
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][][] visited = new boolean[M][N][5]; // 이미 방문한 위치인지 확인하기 위한 변수 (r,c,방향)
		Pair[] queue = new Pair[M * N * 4 * 3]; // Queue 생성
		int front = -1, rear = -1;

		// 처음 위치 QUEUE에 삽입
		st = new StringTokenizer(br.readLine(), " ");
		queue[++rear] = new Pair(
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()), 0);

		// 도착 위치
		st = new StringTokenizer(br.readLine(), " ");
		Pair end = new Pair(
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()), 0);

		int ANS = Integer.MAX_VALUE; // 정답 변수

		while (front != rear) {
			Pair p = queue[++front]; // queue.poll()
			if (p.r == end.r && p.c == end.c && p.dir == end.dir) { // 끝지점인 경우
				ANS = ANS > p.cnt ? p.cnt : ANS; // 정답 갱신
				break; // bfs인 경우, 먼저 도착한 경우가 최선의 경우이므로 break
			}

			// 1 ~ 3칸 이동하기
			for (int i = 1; i <= 3; i++) {
				int nR = p.r + dr[p.dir] * i;
				int nC = p.c + dc[p.dir] * i;

				if (nR >= M || nC >= N || nR < 0 || nC < 0) { // 범위 초과
					break;
				}
				if (map[nR][nC] == 1) { // 벽인 경우
					break;
				}
				if (visited[nR][nC][p.dir]) { // 해당 위치와 방향으로 이미 방문한 것이 있다면, 최선의 경우를 이미 탐색한 경우임
					continue;
				}
				visited[nR][nC][p.dir] = true; // 방문 표시
				queue[++rear] = new Pair(nR, nC, p.dir, p.cnt + 1); // queue에 이동한 지점 삽입, 명령어 +1
			}

			// 회전시키는 경우
			for (int i = 1; i < dr.length; i++) { // 동서남북
				if (i == isImpossible(p.dir)) { // 뒤로 가는 경우
					continue;
				}
				if (p.dir == i) { // 자기가 바로 보고 있는 경우, 회전시키지 않아도 됨 
					continue; 
				}
				if (visited[p.r][p.c][i]) { // 해당 점에서 이미 회전이 일어난 경우, 
					continue;
				}
				visited[p.r][p.c][i] = true; // 해당 점에서 회전했음을 표시
				queue[++rear] = new Pair(p.r, p.c, i, p.cnt + 1); // queue에 현재점, 방향, 명령어 +1 하여 삽입
			}
		} // end of while(queue)
		System.out.println(ANS);
	} // end of main

	/** 뒤로 갈 수 없는 경우를 확인 */
	private static int isImpossible(int dir) {
		int tmp = 0;
		switch (dir) {
		case 1: // 동
			tmp = 2; // 서쪽을 못감
			break;
		case 2: // 서
			tmp = 1; // 동쪽을 못감
			break;
		case 3: // 남
			tmp = 4; // 북쪽을 못감
			break;
		case 4: // 북
			tmp = 3; // 남쪽을 못감
			break;
		}
		return tmp;
	} // end of func(isImpossible)

	private static class Pair {
		int r, c, dir, cnt; // r(행), c(열), dir(방향), cnt(명령 횟수)

		Pair(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	} // end of Pair
} // end of class
