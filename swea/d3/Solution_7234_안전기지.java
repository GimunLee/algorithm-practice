package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_7234_안전기지 {
	static int[][] map;
	static int[][] safe = { { -2, 0 }, { -1, 0 }, { 1, 0 }, { 2, 0 }, { 0, -1 }, { 0, -2 }, { 0, 1 }, { 0, 2 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken()); // 격자구역 크기
			int M = Integer.parseInt(st.nextToken()); // 안전기지의 수

			map = new int[N + 1][N + 1];

			int ans = 0;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] += 1;
				for (int j = 0; j < safe.length; j++) {
					if (inRange(r + safe[j][0], c + safe[j][1])) {
						map[r + safe[j][0]][c + safe[j][1]] += 1;
						ans = Math.max(map[r + safe[j][0]][c + safe[j][1]], ans);
					}
				}
			}

//			for (int i = 0; i < N + 1; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	private static boolean inRange(int r, int c) {
		if (r < 1 || c < 1 || r > map.length - 1 || c > map.length - 1) {
			return false;
		}
		return true;
	}
}
