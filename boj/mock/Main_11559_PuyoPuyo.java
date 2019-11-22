package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2019.11.14.(목) 
 * */
public class Main_11559_PuyoPuyo {
	private static final int R = 12, C = 6;
	private static char[][] map;
	private static int[][] dir = { { -1, 1, 0, 0 }, { 0, 0, -1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[R][C];
		Queue<Pair> queue = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] != '.') {
					queue.add(new Pair(r, c, map[r][c]));
				}
			}
		}

		System.out.println(solution(queue));

	} // end of func(main)

	private static int solution(Queue<Pair> queue) {
		boolean[][] visited = new boolean[R][C];
		int answer = 0;
		while (true) {
			boolean flag = false;
			while (!queue.isEmpty()) {
				Pair p = queue.poll();
				if (visited[p.r][p.c]) {
					continue;
				}
				flag |= bomb(visited, p.r, p.c);
			}
			if (!flag) {
				return answer;
			}
			answer++;
			// 내려주기
			visited = new boolean[R][C];
			down(queue);
			if (queue.isEmpty()) {
				break;
			}
		}

		return answer;
	}

	private static void down(Queue<Pair> queue) {
		for (int j = 0; j < C; j++) {
			int ti = R; // 이동해야할 위치
			for (int i = R - 1; i >= 0; i--) {
				if (map[i][j] != '.') {
					ti--;
					map[ti][j] = map[i][j];
					queue.add(new Pair(ti, j, map[ti][j]));
					if (i != ti)
						map[i][j] = '.';
				}
			}
		}
	}

	private static boolean bomb(boolean[][] visited, int r, int c) {
		boolean ret = false;
		Queue<Pair> moveQueue = new LinkedList<>();
		moveQueue.add(new Pair(r, c, map[r][c]));
		visited[r][c] = true;
		Queue<Pair> sameQueue = new LinkedList<>();
		sameQueue.add(new Pair(r, c, map[r][c]));
		while (!moveQueue.isEmpty()) {
			Pair p = moveQueue.poll();

			for (int i = 0; i < dir[0].length; i++) {
				int nR = p.r + dir[0][i];
				int nC = p.c + dir[1][i];

				if (nR < 0 || nC < 0 || nR >= R || nC >= C) {
					continue;
				}

				if (visited[nR][nC] || map[nR][nC] != p.color) {
					continue;
				}

				visited[nR][nC] = true;
				moveQueue.add(new Pair(nR, nC, p.color));
				sameQueue.add(new Pair(nR, nC, p.color));
			}
		}

		if (sameQueue.size() >= 4) {
			ret = true;
			while (!sameQueue.isEmpty()) {
				Pair p = sameQueue.poll();
				map[p.r][p.c] = '.';
			}
		}
		return ret;
	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	private static class Pair {
		int r, c;
		char color;

		public Pair(int r, int c, char color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}
	}
} // end of Class
