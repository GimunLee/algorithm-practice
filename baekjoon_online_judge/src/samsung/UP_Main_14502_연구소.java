package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Main_14502_연구소 {
	static int[][] move = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 바이러스 퍼지는 방향(상하좌우)
	static int[][] map; // 연구소 map
	static int[][] map_temp; // 테스트할 map의 임시 변수

	static boolean[][] visited; // 바이러스를 퍼트렸는지 알기 위한 변수
	static int[][] q = new int[1000][2]; // queue 변수 / [0] : row, [1] : column
	static int front = -1; // queue에서 사용할 front 변수
	static int rear = -1; // queue에서 사용할 rear 변수

	static int ans = 0; // 정답 변수

	static int size_virus;
	static int[] r_combi;
	static int[] c_combi;

	static int N; // map의 행 크기
	static int M; // map의 열 크기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map의 행 크기
		M = Integer.parseInt(st.nextToken()); // map의 열 크기
		map = new int[N][M]; // map 생성

		// 빈칸을 담을 배열 (벽을 세우기 위한 변수)
		r_combi = new int[N * M]; // 비어있는 행을 저장할 변수
		c_combi = new int[N * M]; // 비어있는 열을 저장할 변수
		int size_combi = 0; // nCr에서 n에 해당하는 변수 (벽을 세울 수 있는 총 개수)

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) { // 바이러스라면
					// queue와 동일하게 작동
					++rear;
					q[rear][0] = r;
					q[rear][1] = c;
					size_virus++; // 바이러스 개수 증가
				} else if (map[r][c] == 0) { // 빈칸이라면
					r_combi[size_combi] = r;
					c_combi[size_combi] = c;
					size_combi++;
				}
			}
		} // end of for(input)
		setWall(size_combi, 0, 0); // 벽 세우기 + 확산
		System.out.println(ans); // 답 출력
	} // end of main

	/** r과 c를 받아 map의 크기를 벗어나지 않는지 확인합니다. */
	public static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r > (map.length - 1) || c > (map[0].length - 1)) {
			return false;
		}
		return true;
	} // end of func(inRange)

	static ArrayList<Integer> rList = new ArrayList<Integer>(); // 벽을 세울 행 값을 저장할 변수
	static ArrayList<Integer> cList = new ArrayList<Integer>(); // 벽을 세울 열 값을 저장할 변수

	/**
	 * 벽을 세울 수 있는 개수(size_combi)에서 뽑은 개수(cnt)를 뽑아서 벽을 세운다. t : combination에서 index
	 * 변수
	 */
	static void setWall(int size_combi, int cnt, int t) {
		if (cnt == 3) { // 3개를 뽑았다면
			map_temp = new int[N][M];
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[0].length; c++) {
					map_temp[r][c] = map[r][c];
				}
			} // end of for(copy map)

			for (int i = 0; i < rList.size(); i++) {
				map_temp[rList.get(i)][cList.get(i)] = 1;
			} // 벽세우기

			visited = new boolean[N][M]; // 바이러스가 퍼진 곳인지 알기 위한 변수 생성
			front = -1;
			rear = size_virus - 1;

			// 바이러스 퍼트리기
			for (int i = 0; i < size_virus; i++) {
				int r_v = q[i][0]; // virus의 행 좌표
				int c_v = q[i][1]; // virus의 열 좌표

				if (!visited[r_v][c_v]) { // 바이러스가 퍼지지 않은 곳이라면
					spreadVirus(r_v, c_v); // 바이러스 퍼트리기
				}
			} // end of for(spread virus)

			int temp = 0; // 안전구역의 넓이를 저장할 임시 변수

			for (int i = 0; i < map_temp.length; i++) {
				for (int j = 0; j < map_temp[0].length; j++) {
					if (map_temp[i][j] == 0) {
						temp++;
					}
				}
			} // end of for(count safety)

			ans = Math.max(temp, ans); // 답 갱신
			return;
		}

		// 벽을 세울 곳 구하기 (조합 사용)
		for (int i = t; i < size_combi; i++) {
			rList.add(r_combi[i]); // 뽑은 행 좌표
			cList.add(c_combi[i]); // 뽑은 열 좌표
			setWall(size_combi, cnt + 1, i + 1); // 3개를 뽑을 때까지 재귀 호출
			// 해당 좌표들은 사용했기 때문에 다시 제거
			rList.remove(rList.size() - 1);
			cList.remove(cList.size() - 1);
		}
	} // end of func(setWall)

	/** 바이러스 퍼트리기 (BFS) */
	private static void spreadVirus(int r, int c) {
		visited[r][c] = true;

		while (front != rear) { // queue가 빌 때까지
			++front;
			r = q[front][0]; // 현 위치
			c = q[front][1];

			for (int i = 0; i < 4; i++) { // 방향
				int r_n = r + move[i][0];
				int c_n = c + move[i][1];

				if (inRange(r_n, c_n)) {
					if (!visited[r_n][c_n] && map_temp[r_n][c_n] == 0) { // 빈칸이면,
						visited[r_n][c_n] = true;
						map_temp[r_n][c_n] = 2;
						++rear;
						q[rear][0] = r_n;
						q[rear][1] = c_n;
					}
				}
			}
		}
	} // end of func(spreadVirus)
} // end of class
