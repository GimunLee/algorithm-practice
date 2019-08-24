package mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NY_Main_3197_백조의호수 {
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];

		Queue<Pair> q_water = new LinkedList<>();
		Queue<Pair> q_bird = new LinkedList<>();

		boolean[][] visited = new boolean[R][C];
		int[][] map_ice = new int[R][C];

		int idx = 1;
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] != 'X') {
					q_water.add(new Pair(0, r, c));
				}else {
					map_ice[r][c] = 10000;
				}
				if (map[r][c] == 'L') {
					q_bird.add(new Pair(idx++, r, c));
				}
			}
		}
		
		// 빙하 녹는 시점 계산하기
		while (!q_water.isEmpty()) {
			for (int t = 0; t < q_water.size(); t++) {
				Pair p = q_water.poll();
				int r = p.r;
				int c = p.c;
				visited[r][c] = true;

				for (int i = 0; i < dr.length; i++) {
					int nR = r + dr[i];
					int nC = c + dc[i];

					if (nR >= R || nC >= C || nR < 0 || nC < 0) {
						continue;
					}

					if (visited[nR][nC]) {
						continue;
					}

					if (map[nR][nC] == 'X') {
						map_ice[nR][nC] = map_ice[r][c] + 1 > map_ice[nR][nC] ? map_ice[nR][nC] : map_ice[r][c] + 1;
						map[nR][nC] = '.';
						q_water.add(new Pair(0, nR, nC));
					}
				}
			}
		} // end of 빙하녹이기

		for (int i = 0; i < map_ice.length; i++) {
			System.out.println(Arrays.toString(map_ice[i]));
		}

		// 백조 보내보기
		int time = 0;
		int[][] birdCanGo = new int[R][C];
		visited = new boolean[R][C];

		here: while (!q_bird.isEmpty()) {
			System.out.println(time);
			for (int i = 0; i < birdCanGo.length; i++) {
				System.out.println(Arrays.toString(birdCanGo[i]));
			}

			for (int t = 0; t < q_bird.size(); t++) {
				Pair p = q_bird.poll();
				idx = p.idx;
				int r = p.r;
				int c = p.c;
				visited[r][c] = true;

				for (int i = 0; i < dr.length; i++) {
					int nR = r + dr[i];
					int nC = c + dc[i];

					if (nR >= R || nC >= C || nR < 0 || nC < 0) {
						continue;
					}

					if (visited[nR][nC]) {
						continue;
					}

					if (map_ice[nR][nC] <= time) { // 갈 수 있음
						if (idx == 1) {
							if (birdCanGo[nR][nC] == 2) {
								birdCanGo[nR][nC] = 3;
								break here;
							}
							birdCanGo[nR][nC] = 1;
						} else {
							if (birdCanGo[nR][nC] == 1) {
								birdCanGo[nR][nC] = 3;
								break here;
							}
							birdCanGo[nR][nC] = 2;
						}
						q_bird.add(new Pair(idx, nR, nC));
					}
				}
			}
			time++;
		}
		System.out.println(time);

	} // end of main

	private static class Pair {
		int idx;
		int r;
		int c;

		Pair(int idx, int r, int c) {
			this.idx = idx;
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
