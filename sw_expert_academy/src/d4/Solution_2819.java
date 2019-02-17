package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_2819 {
	static char[][] input;
	static HashSet<String> answer;
	static int[][] direction = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상 좌 우 하

	static boolean inRange(int mx, int my) { // move한 곳이 범위를 벗어나는지 체크
		if (mx < 0 || mx > 3 || my < 0 || my > 3) {
			return false;
		}
		return true;
	}

	private static void search(int x, int y, String temp) {
		temp += input[x][y];

		if (temp.length() == 7) {
			answer.add(temp);
			return;
		} else {
			for (int i = 0; i < direction.length; i++) {
				int nx = x + direction[i][0];
				int ny = y + direction[i][1];

				if (inRange(nx, ny)) {
					search(nx, ny, temp);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= C; tc++) {
			answer = new HashSet<String>();
			input = new char[4][4];

			// -- input -- //
			for (int x = 0; x < 4; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int y = 0;
				while (st.hasMoreTokens()) {
					input[x][y++] = st.nextToken().charAt(0);
				}
			}

			// -- function -- //
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					search(x, y, "");
				}
			}

			System.out.println("#" + tc + " " + answer.size());
		}
	}
}
