package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_4613_러시아국기같은깃발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken()); // 3<= N,M <= 50
			int M = Integer.parseInt(st.nextToken()); // 3<= N,M <= 50

			char[][] map = new char[N][M];
			Pair2[][] color = new Pair2[N][3];

			for (int i = 0; i < color.length; i++) {
				for (int j = 0; j < color[0].length; j++) {
					color[i][j] = new Pair2(0, 0);
				}
			}

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] != 'W') {
						color[i][0].paint++;
						color[i][0].color = 0;
					}
					if (map[i][j] != 'B') {
						color[i][1].paint++;
						color[i][1].color = 1;
					}
					if (map[i][j] != 'R') {
						color[i][2].paint++;
						color[i][2].color = 2;
					}
				}
			}

			for (int i = 0; i < color.length; i++) {
				for (int j = 0; j < color[0].length; j++) {
					System.out.print("( " + color[i][j].paint + ", " + color[i][j].color + " )");
				}
				System.out.println();
			}

			int ans = 0;
			int color_idx = 0;

			for (int i = 1; i < color.length; i++) {
				Arrays.sort(color[i]);
				for (int j = 0; j < color[0].length; j++) {
//					System.out.println(color[i][j].paint + ", " + color[i][j].color);
					
					
				}
			}

		} // end of for TestCase
	} // end of main
} // end of class

/* 0:W, 1:B, 2:R */
class Pair2 implements Comparable<Pair2> {
	int paint;
	int color;

	Pair2(int paint, int color) {
		this.paint = paint;
		this.color = color;
	}

	@Override
	public int compareTo(Pair2 o1) {
		return this.paint - o1.paint;
	}
}
