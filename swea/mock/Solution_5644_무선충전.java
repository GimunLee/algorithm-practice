package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {
	private static int[] dr = { 0, -1, 0, 1, 0 }; // 이동하지않음,상,우,하,좌
	private static int[] dc = { 0, 0, 1, 0, -1 };
	private static final int X = 10, Y = 10;
	private static int M, A;
	private static int[][] map, userRoute;
	private static BC[] bcArray;
	private static ArrayList<Integer>[] bc_list = new ArrayList[2];
	private static int[][] que = new int[101][2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 사용자 이동 시간
			A = Integer.parseInt(st.nextToken()); // 충전기 갯수
			// 사용자 이동 경로
			userRoute = new int[2][M];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					userRoute[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// Battery Charger
			map = new int[Y + 1][X + 1];
			bcArray = new BC[A];
			int idx = 0;
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				bcArray[i] = new BC(idx++, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				chargeRange(bcArray[i]);
			}
			// -- end of input
//			print();
			int answer = solution();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int solution() {
		int answer = 0;
		int[][] userPos = { { 1, 1 }, { 10, 10 } }; // 최초 시작 위치
		int[] bcCnt = new int[A];
		// 탐색 시작
		for (int i = 0; i <= M; i++) {
			bc_list[0] = new ArrayList<>();
			bc_list[1] = new ArrayList<>();
			// 1. 충전 범위가 겹치는지 확인
			for (int j = 0; j < 2; j++) {
				int x = userPos[j][0];
				int y = userPos[j][1];
				if (map[y][x] == 0) {
					continue;
				}
				for (int k = 0; k < A; k++) {
					if ((map[y][x] & (1 << k)) != 0) {
						bc_list[j].add(k);
					}
				}
			}
			int max = 0;
			if (bc_list[0].size() >= 1 && bc_list[1].size() >= 1) {
				for (int j = 0; j < bc_list[0].size(); j++) {
					bcCnt[bc_list[0].get(j)]++;
					for (int k = 0; k < bc_list[1].size(); k++) {
						bcCnt[bc_list[1].get(k)]++;
						int sum = bcArray[bc_list[0].get(j)].p / bcCnt[bc_list[0].get(j)]
								+ bcArray[bc_list[1].get(k)].p / bcCnt[bc_list[1].get(k)];
						max = Math.max(sum, max);
						bcCnt[bc_list[1].get(k)]--;

					}
					bcCnt[bc_list[0].get(j)]--;
				}
			} else {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < bc_list[j].size(); k++) {
						max = Math.max(max, bcArray[bc_list[j].get(k)].p);
					}
				}
			}
			answer += max;
			// 이동 하기
			if (i != M) {
				for (int j = 0; j < 2; j++) {
					userPos[j][0] += dc[userRoute[j][i]];
					userPos[j][1] += dr[userRoute[j][i]];
				}
			}
		}
		return answer;
	} // end of func(solution)

	private static void chargeRange(BC bc) {
		boolean[][] visited = new boolean[Y + 1][X + 1];
		int front = -1, rear = -1;
		que[++rear][0] = bc.locX;
		que[rear][1] = bc.locY;
		visited[bc.locY][bc.locX] = true;
		map[bc.locY][bc.locX] |= 1 << bc.idx;
		int range = 0;
		while (front != rear) {
			int size = rear - front;
			for (int k = 0; k < size; k++) {
				int x = que[++front][0];
				int y = que[front][1];
				for (int i = 1; i < dc.length; i++) {
					int nX = x + dc[i];
					int nY = y + dr[i];
					if (nX <= 0 || nY <= 0 || nX > X || nY > Y) {
						continue;
					}
					if (visited[nY][nX]) {
						continue;
					}
					que[++rear][0] = nX;
					que[rear][1] = nY;
					visited[nY][nX] = true;
					map[nY][nX] |= 1 << bc.idx;
				}
			}
			if (++range >= bc.c) {
				break;
			}
		}
	} // end of func(chargeRange)

	private static class BC {
		int idx; // 충전기 인덱스
		int locX, locY; // 위치
		int c, p; // 충전 범위, 성능

		public BC(int idx, int locX, int locY, int c, int p) {
			this.idx = idx;
			this.locX = locX;
			this.locY = locY;
			this.c = c;
			this.p = p;
		}
	} // end of BC

	private static void print() {
		System.out.println("==============================");
		for (int y = 1; y <= Y; y++) {
			for (int x = 1; x <= X; x++) {
				System.out.print(map[y][x] + "  ");
			}
			System.out.println();
		}
	} // end of func(print)
} // end of class
