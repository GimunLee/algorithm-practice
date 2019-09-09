package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_UP_16234_인구이동 {
	private static int[] dr = { -1, 1, 0, 0 }; // 상하좌우 (인접한 곳을 탐색하기 위한 방향 변수)
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N, L, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 맵 제한 (N x N)
		// 국경선을 공유하는 두 나라의 인구의 차이 L명 이상 R명 이하
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N]; // 맵 생성

		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < map.length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(Input)

		int ANSER = solve(map); // 입력으로 주어진 map 변수를 문제 조건에 맞게 탐색
		System.out.println(ANSER); // 정답 출력
	} // end of main

	/** 맵을 BFS로 탐색하면서 인구이동을 할 수 있다면 인구이동을 시켜보고 정답 반환 */
	private static int solve(int[][] map) {
		int ANSER = 0; // 정답 변수
		int[][] queue = new int[2500][2]; // 한 연합으로 묶기 위해 인접한 땅을 저장할 Queue
		int[][] moveQueue = new int[2500][2]; // 한번의 BFS가 끝나고 인구를 이동시켜볼 맵의 좌표를 저장할 Queue

		boolean flag; // 탐색을 끝낼지 여부를 판별하는 변수, false면 모든 인구이동을 해본 경우

		while (true) { // 인구 이동이 불가능할 때까지 반복
			boolean[][] visited = new boolean[N][N]; // BFS 탐색시, 방문한 곳인지 판별하는 변수
			flag = false; // 인구 이동이 일어난다면, true로 바꿔서 탐색을 종료함

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) { // 이미 탐색이 된 곳이라면
						continue;
					}

					int front = -1, rear = -1; // Queue 초기화
					int mFront = -1, mRear = -1; // moveQueue 초기화

					queue[++rear][0] = r;
					queue[rear][1] = c; // 탐색을 시작할 위치를 Queue에 저장

					int sum = 0, cnt = 0; // 한 땅에서 인접한 땅들의 합과 갯수

					while (front != rear) { // 한 땅에서 인접한 땅에 대한 탐색이 끝날 때까지
						int cR = queue[++front][0]; // Queue에서 현재 땅의 좌표를 가져옴
						int cC = queue[front][1];

						moveQueue[++mRear][0] = cR; // 인구 이동이 일어날 수 있으므로 moveQueue에 현재 땅 좌표 저장
						moveQueue[mRear][1] = cC;

						sum += map[cR][cC]; // 현재 땅의 인구수를 sum에 저장
						cnt++; // 갯수 증가

						visited[cR][cC] = true; // 방문 표시

						for (int dir = 0; dir < dr.length; dir++) { // 인접한 4곳을 탐색함
							int nR = cR + dr[dir]; // 인접한 땅의 다음 위치
							int nC = cC + dc[dir];

							if (nR >= 0 && nC >= 0 && nR < N && nC < N) { // 맵을 벗어나지 않는다면,
								if (visited[nR][nC]) { // 다음 위치가 방문한 곳이라면
									continue;
								}

								// 인접한 땅이 같은 연합이 될 수 있는지 확인
								int sub = Math.abs(map[nR][nC] - map[cR][cC]);
								if (L <= sub && sub <= R) { // 두 땅의 차이가 L 이상 R 이하라면
									queue[++rear][0] = nR; // 인접한 곳이므로, Queue에 저장 후 탐색시킴
									queue[rear][1] = nC;
									visited[nR][nC] = true;
								}
							}
						} // end of for(Direction)
					} // end of while(한 땅에 대한 인접한 땅 묶기)

					if (cnt <= 1) { // 인접한 나라가 자기 자신 밖에 없다면, 다음 좌표를 탐색
						continue;
					}

					flag = true; // 이번 탐색에서 인구 이동이 가능했으므로, flag를 true로 바꿔줌

					while (mFront != mRear) { // 인구 이동 대상 땅들을 인구 이동 시킴
						map[moveQueue[++mFront][0]][moveQueue[mFront][1]] = sum / cnt;
					}
				}
			} // end of for(모든 좌표 탐색)

			if (!flag) { // 인구 이동이 일어나지 않았다면,
				break; // 앞으로도 일어날 수 없으므로 종료
			}
			ANSER++; // 인구 이동 횟수 증가
		}
		return ANSER; // 인구 이동 횟수 반환
	} // end of func(solve)
} // end of class
