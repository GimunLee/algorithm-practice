package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3197_백조의호수_djs {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static char[][] map;
	private static int R, C;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		int birdIdx = 0;
		int[][] birdLoc = new int[2][2];
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'L') {
					birdLoc[birdIdx][0] = r;
					birdLoc[birdIdx++][1] = c;
				}
			}
		}
		int idx = 1;
		waterMap = new int[R][C];
		front = -1;
		rear = -1;
		queue = new int[R * C + 1][2];
		isInsert = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (waterMap[r][c] == 0 && (map[r][c] == '.' || map[r][c] == 'L')) {
					dfs(r, c, idx);
					idx++;
					// 백조가 이미 같은 공간에 있다면
					if (waterMap[birdLoc[0][0]][birdLoc[0][1]] != 0
							&& (waterMap[birdLoc[0][0]][birdLoc[0][1]] == waterMap[birdLoc[1][0]][birdLoc[1][1]])) {
						System.out.println(0);
						return;
					}
				}
			}
		}
		makeSet(idx);
		int ANSER = bfs(birdLoc);
		System.out.println(ANSER);

	} // end of main

	private static int bfs(int[][] birdLoc) {
		int time = 1;
		int firstBird = waterMap[birdLoc[0][0]][birdLoc[0][1]];
		int secondBird = waterMap[birdLoc[1][0]][birdLoc[1][1]];

		while (front != rear) {
			int size = rear;
			for (int i = front; i < size; i++) {
				int r = queue[++front][0];
				int c = queue[front][1];

				for (int dir = 0; dir < dr.length; dir++) {
					int nR = r + dr[dir];
					int nC = c + dc[dir];

					if (!inRange(nR, nC)) {
						continue;
					}
					
					if(p[waterMap[r][c]] == p[waterMap[nR][nC]]) {
						continue;
					}

					if (waterMap[nR][nC] == 0) {
						waterMap[nR][nC] = waterMap[r][c];
						queue[++rear][0] = nR;
						queue[rear][1] = nC;
						continue;
					}

					if (p[waterMap[r][c]] != p[waterMap[nR][nC]]) { // 부모가 다른지 확인해준다.
						union(waterMap[r][c], waterMap[nR][nC]); // 집합을 합쳐준다.
					}
				}
			} // end of for(1 Time)
			if (find(firstBird) == find(secondBird)) { // 합쳤을때, 새들의 위치 집합과 같다면
				return time;
			}
			time++;
		}
		return time;
	}

	private static void makeSet(int idx) {
		p = new int[idx];
		rank = new int[idx];
		for (int i = 1; i < p.length; i++) {
			p[i] = i; // 자기자신이 부모
		}
	}

	private static int find(int num) {
		if (p[num] == num) {
			return p[num];
		}
		return p[num] = find(p[num]);
	}

	private static int[] rank;

	private static void union(int num1, int num2) {
		int p1 = find(num1);
		int p2 = find(num2);

		if (p1 == p2) {
			return;
		}
		if (rank[p1] < rank[p2]) {
			p[p1] = p2;
			return;
		} else {
			if (rank[p1] == rank[p2]) {
				rank[p1]++;
			}
			p[p2] = p1;
		}
		return;
	}

	private static int[][] waterMap;
	private static int[][] queue;
	private static int front, rear;

	private static boolean[][] isInsert;

	private static void dfs(int r, int c, int idx) {
		waterMap[r][c] = idx;
		for (int dir = 0; dir < dr.length; dir++) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];

			if (!inRange(nR, nC)) {
				continue;
			}

			if (waterMap[nR][nC] != 0) {
				continue;
			}

			if (map[nR][nC] == '.' || map[nR][nC] == 'L') {
				dfs(nR, nC, idx);
			}

			if (!isInsert[r][c] && map[nR][nC] == 'X') {
				isInsert[r][c] = true;
				queue[++rear][0] = r;
				queue[rear][1] = c;
			}
		}
	}

	private static boolean inRange(int r, int c) {
		if (r >= R || c >= C || r < 0 || c < 0) {
			return false;
		}
		return true;
	}

	private static void print(int[][] input) {
		System.out.println("-------------------------------------------------");
		for (int r = 0; r < input.length; r++) {
			System.out.println(Arrays.toString(input[r]));
		}
		return;
	}
} // end of Class
