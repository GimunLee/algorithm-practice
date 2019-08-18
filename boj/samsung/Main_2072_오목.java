package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2072_ø¿∏Ò {
	// 1 2 3
	// 4 5 6
	// 7 8 9
	private static final int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		Info[][] map = new Info[20][20];

		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				map[r][c] = new Info(0);
			}
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()); // x ¡¬«•
			int c = Integer.parseInt(st.nextToken()); // y ¡¬«•
			// 1: »Ê, 2: πÈ

			map[r][c].idx = i;
			if (i % 2 != 0) { // »¶ºˆπ¯¬∞
				map[r][c].color = 1;
			} else { // ¬¶ºˆπ¯¬∞
				map[r][c].color = 2;
			}

			System.out.println(r + ", " + c);
			calculate(map, r, c);

		} // end of for(input)

		for (int r = 1; r < map.length; r++) {
			for (int c = 1; c < map.length; c++) {
				System.out.print(map[r][c].color + " ");
			}
			System.out.println();
		}

		for (int r = 1; r < map.length; r++) {
			for (int c = 1; c < map.length; c++) {
//				System.out.print(map[r][c].color + " ");
			}
//			System.out.println();
		}
		System.out.println("* " + map[2][3].count_white[1][0]);
	} // end of main

	/** ∏ ø° «ÿ¥Á«œ¥¬ ¡§∫∏ √§øÏ¥¬ «‘ºˆ */
	private static void calculate(Info[][] map, int r, int c) {

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			int rX = 1 + dr[i];
			int rY = 1 + dc[i];

			if (nR <= 0 || nC <= 0 || nR >= map.length || nC >= map.length) {
				continue;
			}

			if (map[nR][nC].color == 1) { // »Ê¿œ ∂ß
				map[r][c].count_black[rX][rY] += (map[nR][nC].count_black[rX][rY] + 1);
				System.out.println(rX + ", " + rY + ", B : " + map[r][c].count_black[rX][rY]);
				System.out.println(map[nR][nC].count_black[rX][rY]);
			} else if (map[nR][nC].color == 2) { // πÈ¿œ ∂ß
				map[r][c].count_white[rX][rY] += (map[nR][nC].count_white[rX][rY] + 1);
				System.out.println(rX + ", " + rY + ", W : " + map[r][c].count_white[rX][rY]);
				System.out.println(map[nR][nC].count_white[rX][rY]);
			}
		}
		return;
	}

	private static class Info {
		int idx;
		int color;
		int[][] count_white;
		int[][] count_black;

		public Info(int color) {
			this.color = color;
			count_white = new int[3][3];
			count_black = new int[3][3];
		}
	} // end of Info
} // end of class
