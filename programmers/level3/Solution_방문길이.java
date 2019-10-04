package programmers.level3;

import java.util.Arrays;

public class Solution_방문길이 {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		String dirs = "LULLLLLLU";
		System.out.println(solution(dirs));
	} // end of main

	public static int solution(String dirs) {
		int answer = 0;
		int r = 5;
		int c = 5;
		int[][][] visited = new int[R][C][4];
		for (int i = 0; i < dirs.length(); i++) {
			int nR = r + dr[getDir(dirs.charAt(i))];
			int nC = c + dc[getDir(dirs.charAt(i))];
			if (!isRange(nR, nC)) {
				continue;
			}
			if (visited[nR][nC][getDir(dirs.charAt(i))] == 0) {
				answer++;
			}
			visited[nR][nC][getDir(dirs.charAt(i))]++;
			visited[r][c][getReverseDir(getDir(dirs.charAt(i)))]++;

			r = nR;
			c = nC;
		}

		return answer;
	} // end of func(solution)

	private static final int R = 11;
	private static final int C = 11;

	private static boolean isRange(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C) {
			return false;
		}
		return true;
	} // end of func(isRange)

	private static int getReverseDir(int dir) {
		switch (dir) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return -1;
	} // end of func(getReverseDir)

	private static int getDir(char dir) {
		switch (dir) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'R':
			return 2;
		case 'L':
			return 3;
		}
		return -1;
	} // end of func(getDir)
}
