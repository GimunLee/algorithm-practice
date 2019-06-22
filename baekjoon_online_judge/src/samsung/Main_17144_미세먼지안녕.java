package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕 {
	private static final int[] dr = { -1, 0, 1, 0 };
	private static final int[] dc = { 0, 1, 0, -1 };
	private static final int[][][] move = { { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } },
			{ { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken()); // 행
		int C = Integer.parseInt(st.nextToken()); // 열
		int T = Integer.parseInt(st.nextToken()); // T초

		int[][] map = new int[R][C];
		int[] airCleaner = new int[2];
		int airCleaner_idx = 0;
		int dustSum = 0;

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) { // 공기청정기
					airCleaner[airCleaner_idx++] = r;
				} else if (map[r][c] != 0) { // 미세먼지
					dustSum += map[r][c]; // 현재 미세먼지 총량
				}
			}
		} // end of for(input)

		int[][] map_temp;
		
		for (int t = 1; t <= T; t++) {
			map_temp = new int[R][C];
			map_temp[airCleaner[0]][0] = -1;
			map_temp[airCleaner[1]][0] = -1;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) { // 미세먼지일 때,
						int spread_cnt = 0; // 확산된 방향수
						int spread_mass = 0; // 확산되는 양
						for (int i = 0; i < dr.length; i++) { // 4방향으로 미세먼지 확산
							int nR = r + dr[i];
							int nC = c + dc[i];

							if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // 범위를 벗어날 때,
								continue;
							}
							if (map[nR][nC] == -1) { // 확산될 수 없는 곳 (공기청정기)
								continue;
							}
							spread_cnt++; // 확산된 방향수
							spread_mass = map[r][c] / 5; // 확산되는 양 : map[r][c]/5
							dustSum += spread_mass;
							map_temp[nR][nC] += spread_mass;
						}
						// 확산되고 남은 양 : map[r][c] - (map[r][c]/5)*(확산된 방향수)
						map_temp[r][c] += (map[r][c] - (spread_cnt * spread_mass));
						dustSum -= (spread_cnt * spread_mass);
					}
				}
			} // end of for(spreading)

			for (int i = 0; i < R; i++) {
				map[i] = Arrays.copyOf(map_temp[i], C);
			}

			// 공기청정 시작
			for (int i = 0; i < 2; i++) {
				int cR = airCleaner[i];
				int cC = 0;
				for (int j = 0; j < 4; j++) { // 4방향으로 회전
					while (true) {
						int nR = cR + move[i][j][0];
						int nC = cC + move[i][j][1];

						if (nR < 0 || nC < 0 || nR >= R || nC >= C) {
							break;
						}

						if (map[nR][nC] == -1) {
							dustSum -= map_temp[cR][cC];
							break;
						}

						if (map[cR][cC] == -1) { // 공기청정기에서 시작
							map[nR][nC] = 0;
						} else {
							map[nR][nC] = map_temp[cR][cC];
						}
						cR = nR;
						cC = nC;
					}
				}
			} // end of cleaning for 1 time
		} // end of for(time)
		System.out.println(dustSum);
	} // end of main
} // end of class
