package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5650_핀볼게임2 {
	static int[] dr = { -1, 0, 1, 0 }; // row : 상, 우, 하, 좌
	static int[] dc = { 0, 1, 0, -1 }; // column : 상, 우, 하, 좌
	private static int[][] map; // 입력 받는 값을 저장하기 위한 2차원 배열 변수
	private static int N; // map의 크기 (row, column)
	private static ArrayList<Pair>[] wHole; // 웜홀의 위치를 담기 위한 list 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // map의 크기 
			map = new int[N][N]; // map의 크기만큼 2차원 배열을 생성합니다.
			Queue<Pair> q = new LinkedList<>(); // 핀볼의 시작 위치를 선입선출의 자료구조인 큐에 저장합니다.
			wHole = new ArrayList[5]; // 쌍으로 구성된 웜홀의 위치를 쉽게 받아오기 위해, ArrayList 배열에 저장합니다. 

			for (int i = 0; i < wHole.length; i++) {
				wHole[i] = new ArrayList<>(); // 각 웜홀 index별 ArrayList를 생성합니다.
			}

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 0) { // 0인 경우, 핀볼을 놓을 수 있으므로 큐에 저장하여 확인합니다.
						q.add(new Pair(r, c));
					}
					if (map[r][c] >= 6 && map[r][c] <= 10) { // 웜홀인 경우,
						wHole[map[r][c] - 6].add(new Pair(r, c)); // idx는 0부터 시작하므로 -6을 하여 저장합니다.
					}
				}
			} // end of for Input

			int max = Integer.MIN_VALUE; // 정답으로 출력할 변수

			start: while (!q.isEmpty()) { // 탐색하지 않은 시작점이 있을때까지 반복합니다.
				Pair p = q.poll(); // 탐색할 시작점
				int sR = p.r; // 종료하기 위한 시작점 sR, sC
				int sC = p.c;

				dir: for (int i = 0; i < dr.length; i++) { // 핀볼을 4방향(상우하좌)로 탐색합니다.
					int next_dir = i; // 블록을 만났을 경우, 방향을 갱신해주기 위한 변수
					int r = p.r; // 핀볼의 시작점 R
					int c = p.c; // 핀볼의 시작점 C
					int cnt = 0; // 벽이나 블록에 부딪힌 경우, 점수를 올려주기 위한 변수
					
					here: while (true) { // 핀볼의 시작점이나, 블랙홀을 만날때까지 반복합니다.
						int nR = r + dr[next_dir]; // 현재 방향으로 진행했을 경우, 다음 row 위치
						int nC = c + dc[next_dir]; // 현재 방향으로 진행했을 경우, 다음 column 위치

						boolean isWall = inRange(nR, nC); // 다음 위치가 벽인지 확인합니다.
						
						// 핀볼의 시작점이거나 블랙홀이면, 정답 변수를 갱신해주고 핀볼의 시작점에서 다음 방향을 탐색합니다.
						if (isWall && ((nR == sR && nC == sC) || map[nR][nC] == -1)) { 
							max = (max < cnt) ? cnt : max;
							continue dir;
						}

						// 벽이면, 5번 블록과 같습니다.
						if (!isWall) { 
							next_dir = block_dir[4][next_dir]; // 벽에 부딪혔으므로, 방향을 갱신합니다. 
							r = nR; // continue 하기 전, 다음 위치로 갱신합니다.
							c = nC; 
							cnt++; // 벽에 부딪혔으므로, 점수를 갱신합니다.
							continue here; // 바꾼 방향으로 다시 탐색하기 위해 continue
						}

						// 블록이라면, 각 블록에 맞게 방향을 갱신합니다.
						if (map[nR][nC] >= 1 && map[nR][nC] <= 5) {
							next_dir = block_dir[map[nR][nC] - 1][next_dir];
							r = nR;
							c = nC;
							cnt++;
							continue here;
						}

						// 웜홀이라면, 매칭되는 웜홀의 위치로 갱신합니다.
						if (map[nR][nC] >= 6 && map[nR][nC] <= 10) { 
							Pair wp = get_wHole(nR, nC);
							r = wp.r;
							c = wp.c;
							continue here;
						}
						
						// 위의 조건에 해당하지 않는다면 그대로 다음 위치로 이동합니다.
						r = nR;
						c = nC;

					} // end of here:while
				} // end of dir:while
			} // end of start:while
			System.out.println("#" + tc + " " + max);
		} // end of for TestCase
	} // end of main

	// 0:상, 1:우, 2:하, 3:좌
	/** 현재 방향에 N번째 블록의 각 상우하좌에 맞았을 경우, 바뀌는 방향을 저장합니다. */
	private static int[][] block_dir = { 
			{ 2, 3, 1, 0 }, // 1번 블록
			{ 1, 3, 0, 2 }, // 2번 블록
			{ 3, 2, 0, 1 }, // 3번 블록
			{ 2, 0, 3, 1 }, // 4번 블록
			{ 2, 3, 0, 1 } // 5번 블록
	};
	
	/** 웜홀에 들어오면, 같은 쌍의 다른 웜홀 좌표를 반환합니다. */
	private static Pair get_wHole(int r, int c) {
		int idx = map[r][c] - 6;
		if (wHole[idx].get(0).r == r && wHole[idx].get(0).c == c) {
			return new Pair(wHole[idx].get(1).r, wHole[idx].get(1).c);
		} else {
			return new Pair(wHole[idx].get(0).r, wHole[idx].get(0).c);
		}
	} // end of get_wHole()

	/** 외곽으로 벗어나는지, 즉 벽인지 확인하고 벽이라면 false, 벽이 아니라면 true */
	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return false;
		}
		return true;
	} // end of inRange()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
