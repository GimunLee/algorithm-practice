package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1868_파핑파핑지뢰찾기 {
	public static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 }; // 행
	public static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 }; // 열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 1 <= N <= 300
			char[][] m = new char[N + 2][N + 2]; // 외곽 한줄씩 빈공간을 여분으로둠
			for (int i = 1; i <= N; i++) {
				String s = br.readLine();
				for (int j = 1; j <= N; j++) {
					m[i][j] = s.charAt(j - 1);
				}
			}

			int[][] cnt = new int[N + 2][N + 2]; // 폭탄의 개수, 외곽 한줄씩 빈공간을 여분으로둠
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (m[i][j] == '*') { // 폭탄 자리이면, 카운팅 하지않고, 표시해두기
						cnt[i][j] = -9; // 폭탄으로 지정, 사용하지 않는 숫자로 표시
						continue; // 폭탄 자리는 주변을 카운팅 하지 않는다
					}

					// 인접한 8칸에 폭탄이 몇개 들었는지 카운팅
					for (int k = 0; k < dy.length; k++) {
						if (m[i + dy[k]][j + dx[k]] == '*') {
							cnt[i][j]++;
						}
					}
				}
			}

			boolean[][] visited = new boolean[N + 2][N + 2]; // 외곽 한줄은 사용하지 않음
			// 외곽의 칸들을 방문 한 것으로 처리하자
			for (int i = 0; i < visited.length; i++) {
				visited[0][i] = true;
				visited[N + 1][i] = true;
				visited[i][0] = true;
				visited[i][N + 1] = true;
			}

			int result = 0; // 몇번 클릭해야 모두 열수 있는지 카운팅할 변수
			// cnt[][] 에서 0인 칸을 찾아서 BFS 로 탐색
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i][j] == false && cnt[i][j] == 0) { // 방문하지 않은 칸이고, 0이면
						bfs(visited, cnt, i, j); // 인접한 0 무리를 방문, 여기에 인접한 0이 아닌 숫자칸 1꺼플 방문
						result++;
					}
				}
			}
			// 0에 인접하지는 않았지만, 숫자인 칸을 체크하기
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i][j] == false && cnt[i][j] > 0) {
						result++;
					}
				}
			}

			System.out.println("#" + testCase + " " + result);
		} // end of for testCase
	} // end of main

	public static int[][] q = new int[900000][2]; // 큐를 밖에 한번만 생성

	public static void bfs(boolean[][] visited, int[][] cnt, int r, int c) {
		int front = -1; // 큐 초기화
		int rear = -1;
		// 시작 정점을 지정후 큐에 넣고 BFS시작
		q[++rear][0] = r;
		q[rear][1] = c;
		visited[r][c] = true;
		while (front != rear) { // 큐에 데이터가 있으면 반복
			r = q[++front][0]; // 큐에서 1개 꺼내기
			c = q[front][1];
			// 방문작업
			for (int i = 0; i < dy.length; i++) { // 방문하지않았고, 인접한 칸이 숫자이면
				if (visited[r + dy[i]][c + dx[i]] == false && cnt[r + dy[i]][c + dx[i]] > 0) {
					visited[r + dy[i]][c + dx[i]] = true;
				}
			}

			// 방문하지 않았고, 인접한 칸이면 큐에 넣기
			for (int i = 0; i < dy.length; i++) {
				if (visited[r + dy[i]][c + dx[i]] == false) {
					q[++rear][0] = r + dy[i];
					q[rear][1] = c + dx[i];
					visited[r + dy[i]][c + dx[i]] = true; // 방문한 것으로 표시
				}
			}

		}
	} // end of bfs()
} // end of class
