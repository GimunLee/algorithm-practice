package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Main_17144_미세먼지안녕 {
	private static final int[] dr = { -1, 0, 1, 0 }; // 미세먼지 확산 방향 (상하)
	private static final int[] dc = { 0, 1, 0, -1 }; // 미세먼지 확산 방향 (우좌)
	// 공기청정기로 회전시키는 방향
	// 0 : 위쪽 공기청정기(반시계) / 1 : 아래쪽 공기청정기(시계)
	private static final int[][][] move = { 
			{ { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } },
			{ { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } } }; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken()); // map의 행
		int C = Integer.parseInt(st.nextToken()); // map의 열
		int T = Integer.parseInt(st.nextToken()); // T초간 진행

		int[][] map = new int[R][C]; // map 생성
		int[] airCleaner = new int[2]; // 공기청정기 row 좌표 / col은 0으로 고정
		int airCleaner_idx = 0; // 공기청정기 최초 저장하기 위한 index
		int dustSum = 0; // 정답을 한번에 내기 위한 미세먼지 총량

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); // 맵 저장
				if (map[r][c] == -1) { // 공기청정기일 때
					airCleaner[airCleaner_idx++] = r;
				} else if (map[r][c] != 0) { // 미세먼지일 때
					dustSum += map[r][c]; // 미세먼지 총량
				}
			}
		} // end of for(input)

		int[][] map_temp; // 미세먼지를 확장하거나 회전시킬때 저장한 임시 map
		
		for (int t = 1; t <= T; t++) { // T초만큼 실행
			map_temp = new int[R][C]; // 매초마다 map_temp 재생성
			map_temp[airCleaner[0]][0] = -1; // map_temp에 공기청정기 위치 저장
			map_temp[airCleaner[1]][0] = -1; // map_temp에 공기청정기 위치 저장
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
							dustSum += spread_mass; // 확산된 만큼 dustSum에 더하기
							map_temp[nR][nC] += spread_mass; // map_temp에 표시
						}
						// 확산되고 남은 양 : map[r][c] - (map[r][c]/5)*(확산된 방향수)
						map_temp[r][c] += (map[r][c] - (spread_cnt * spread_mass));
						dustSum -= (spread_cnt * spread_mass);
					}
				}
			} // end of for(spreading)

			// 확산된 정보를 갖고있는 map_temp를 map에 복사
			for (int i = 0; i < R; i++) {
				map[i] = Arrays.copyOf(map_temp[i], C);
			}

			// 공기청정 시작
			for (int i = 0; i < 2; i++) { // 위, 아래 공기청정 
				int cR = airCleaner[i]; // 공기청정기 위치부터 회전 시작
				int cC = 0;
				
				for (int j = 0; j < 4; j++) { // 4방향으로 회전
					while (true) {
						int nR = cR + move[i][j][0]; 
						int nC = cC + move[i][j][1];

						if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // 범위를 벗어나면 다른 방향으로 재시작
							break;
						}

						if (map[nR][nC] == -1) { // 공기청정기면 dustSum에서 제거
							dustSum -= map_temp[cR][cC];
							break;
						}

						if (map[cR][cC] == -1) { // 공기청정기에서 시작하는 것은 0으로 확산
							map[nR][nC] = 0;
						} else { // map_temp의 현재 위치 값을 map 다음 값에 저장
							map[nR][nC] = map_temp[cR][cC]; 
						}
						// 회전시키기
						cR = nR; 
						cC = nC;
					}
				}
			} // end of cleaning for(one time)
		} // end of for(time)
		System.out.println(dustSum); // 답 출력
	} // end of main
} // end of class
