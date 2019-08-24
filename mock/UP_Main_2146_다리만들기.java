package mock;
/* -- 반례
10
1 1 1 0 0 0 0 0 0 0
1 1 1 1 0 0 0 0 0 0
1 0 1 1 0 0 0 0 0 0
0 0 1 1 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0


11
1 1 1 1 1 0 0 0 0 0 0 
1 0 0 0 1 0 0 0 0 0 1
1 1 1 0 1 0 0 0 0 0 0
1 0 0 0 1 0 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0

5
1 0 0 0 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 0 0 1
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UP_Main_2146_다리만들기 {
	static int[] dr = { -1, 1, 0, 0 }; // 다리를 설치할 때 쓸 변수 (상하)
	static int[] dc = { 0, 0, -1, 1 }; // 다리를 설치할 때 쓸 변수 (좌우)
	private static int[][] map; // input을 저장할 변수
	private static int N; 
	private static boolean[][] visited; // 다리를 설치한 곳인지 확인하는 변수
	private static Queue<Pair> island_queue; // 섬의 외곽을 저장할 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // map의 크기 (N*N)
		map = new int[N][N]; // map 생성
		visited = new boolean[N][N]; // visit 생성

		for (int r = 0; r < N; r++) { 
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		island_queue = new LinkedList<>(); // queue 생성

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					checkSide(r, c);
					idx++;
				}
			}
		} // end of for(섬의 외곽 정보 저장)

		int ans = Integer.MAX_VALUE; // 가장 짧은 다리 길이 (정답)
		int[][] length = new int[N][N]; // 설치한 다리 길이를 저장할 변수
		boolean flag = false; // 다리끼리 만난 경우, 한 타임의 다리를 모두 설치하고 종료하기 위한 변수

		while (!island_queue.isEmpty()) { // queue가 빌 때가지
			int size = island_queue.size(); // 한 타임씩 다리를 설치하기 위한 변수
			for (int i = 0; i < size; i++) { // 한 타임동안
				Pair p = island_queue.poll();
				
				for (int j = 0; j < dr.length; j++) { // 섬의 외곽에서 상하좌우 다리 설치 (조건에 맞는 경우)
					int nR = p.r + dr[j]; 
					int nC = p.c + dc[j];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // 범위를 벗어날 경우
						continue;
					}

					if (visited[nR][nC]) { // 섬인 경우
						continue;
					} else if (map[nR][nC] != 0) { // 바다지만, 다리가 이미 설치된 경우
						if (map[nR][nC] != p.num) { // 현재 다리를 설치하고 있는 섬이 아닌 경우
							// 새로 설치한 다리 길이가 원래 저장된 답보다 짧은 경우 답 갱신
							ans = ans > length[nR][nC] + length[p.r][p.c] ? length[nR][nC] + length[p.r][p.c] : ans;
							flag = true; // 다리를 설치했기 때문에 한 타임이 끝나고 탐색 종료
						}
					} else {
						island_queue.add(new Pair(nR, nC, p.num)); // 다음에 확장할 다리
						map[nR][nC] = p.num; // 다리 설치
						length[nR][nC] = length[p.r][p.c] + 1; // 다리 길이 업데이트
					}
				}
			}
			if (flag) {
				break;
			}
		} // end of while(setBridge)
		System.out.println(ans); // 정답 출력
	} // end of main

	private static int idx = 1; // 몇번째 섬인지 저장할 변수

	/** 섬의 외곽 체크 */
	private static void checkSide(int r, int c) {
		visited[r][c] = true; 
		map[r][c] = idx; // map에 몇번째 섬인지 체크

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i]; 
			int nC = c + dc[i];

			if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // 외곽인 경우
				continue;
			}

			if (visited[nR][nC]) { // 방문한 경우
				continue;
			}

			if (map[nR][nC] == 0) { // 상하좌우 중 한 곳이라도 물이라면 섬의 외곽입니다.
				island_queue.add(new Pair(r, c, idx)); // 섬의 외곽을 queue에 저장하여, 다리 설치시 사용
			} else {
				checkSide(nR, nC); // 이어져있는 경우, 계속 탐색
			}
		}
		return;
	} // end of func(checkSide)

	private static class Pair {
		int r;
		int c;
		int num; // 몇번째 섬인지 저장

		Pair(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	} // end of Pair
} // end of class
