package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_7236_저수지의물의총깊이구하기 {
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0, -1, 1, 1, -1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());

			map = new char[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = st.nextToken().charAt(0);
				}
			}

			int depth = 1;

			for (int r = 1; r < N - 1; r++) {
				for (int c = 1; c < N - 1; c++) {

					int tmpDepth = 0;
					for (int i = 0; i < dr.length; i++) {

						int aR = r + dr[i];
						int aC = c + dc[i];

						if (map[aR][aC] == 'W') {
							tmpDepth++;
						}
					}
					if (depth < tmpDepth) {
						depth = tmpDepth;
					}
				}
			}
			System.out.println("#" + tc + " " + depth);
		}
	}
}
