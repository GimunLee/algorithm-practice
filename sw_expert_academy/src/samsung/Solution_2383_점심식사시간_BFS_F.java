package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS 실패
 */

public class Solution_2383_점심식사시간_BFS_F {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 방의 크기
			int[][] map = new int[N][N];

			Queue<Pair> q = new LinkedList<>();
			ArrayList<Stair> stairs = new ArrayList<>();
			ArrayList<Integer>[] wait = new ArrayList[2];

			wait[0] = new ArrayList<Integer>();
			wait[1] = new ArrayList<Integer>();

			int idx = 0;
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						q.add(new Pair(r, c, idx++));
					} else if (map[r][c] >= 2) {
						stairs.add(new Stair(r, c, map[r][c]));
					}
				}
			}
			boolean[][][] visited = new boolean[idx][N][N];
			boolean[] isOut = new boolean[idx];
			boolean[] isWait = new boolean[idx];

			here: while (!q.isEmpty()) {

				int time = 0;
				for (int i = 0; i < q.size(); i++) {
					Pair p = q.poll();
//					System.out.println(p.idx);
					if (isOut[p.idx]) { // 이미 빠져나간 사람인 경우, 탐색 중지
						continue;
					}

					visited[p.idx][p.r][p.c] = true;

					time++;
					for (int j = 0; j < dr.length; j++) {
						int nR = p.r + dr[j];
						int nC = p.c + dc[j];

						if (nR >= N || nC >= N || nR < 0 || nC < 0) {
							continue;
						}

						if (visited[p.idx][nR][nC]) {
							continue;
						}

						if (map[nR][nC] >= 2) { // 계단에 도착
							for (int k = 0; k < 2; k++) {
								if (stairs.get(k).len == map[nR][nC]) { // 해당 계단의 정보 가져오기
									boolean canGo = false;
									int isP_idx = 0;
									int stair_idx = 0;

									for (int s = 0; s < 3; s++) {
										if (stairs.get(k).isP[0][s] == -1) { // 갈 수 있는 경우
											canGo = true;
											isP_idx = s; // 대기열 인덱스
											break;
										}
									}
									if (canGo) { // 갈 수 있는 경우
										stairs.get(k).isP[0][isP_idx] = -1; // 시간 저장
										stairs.get(k).isP[1][isP_idx] = p.idx; // 사람 저장

									} else { // 갈 수 없는 경우, 대기열 추가
										wait[stair_idx].add(p.idx);
									}
									break;
								}

							}
						} else { // 계단이 아닌 경우, 계속 보냄
							visited[p.idx][nR][nC] = true;
							q.add(new Pair(nR, nC, p.idx));
						}

					} // end of for Move
				} // end of for Time

				for (int i = 0; i < isOut.length; i++) {
					if (!isOut[i]) { // 빠져나간 사람이 없는 경우, 계속 돌아야함
						break;
					} else if (i == isOut.length - 1) { // 모두 빠져나간 경우,
						System.out.println("#" + tc + " " + time);
						break here;
					}
				}

				// 대기열 초기화
				for (int i = 0; i < 2; i++) {
					Stair s = stairs.get(i);
					for (int k = 0; k < 3; k++) {
						if (s.isP[0][k] != -2) { // 계단이 차있으면
							s.isP[0][k] += 1;
							if (s.isP[0][k] == s.len) { // 해당 길이만큼 됐으면, 대기열 빼기
								s.isP[0][k] = -2;
								System.out.println(s.isP[1][k]);
								isOut[s.isP[1][k]] = true;
								s.isP[1][k] = 0;
							}
						} else { // 계단이 비었는데, wait가 있으면
							if (wait[i].size() != 0) {
								s.isP[0][k] = -1;
								s.isP[1][k] = wait[i].get(0);
								wait[i].remove(0);
							}
						}
					}
				}
			}
		} // end of for TestCase
	} // end of main

	private static class Pair {
		int r;
		int c;
		int idx;

		Pair(int r, int c, int idx) {
			this.r = r;
			this.c = c;
			this.idx = idx;
		}
	} // end of stair

	private static class Stair {
		int r;
		int c;
		int len; // 계단의 길이
		int[][] isP;

		Stair(int r, int c, int len) {
			isP = new int[2][3];
			this.r = r;
			this.c = c;
			this.len = len;
		}
	} // end of stair
} // end of class
