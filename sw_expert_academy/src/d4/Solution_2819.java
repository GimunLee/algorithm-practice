package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2819 {
	static int ans;
	static char[][] input;
	static ArrayList<String> memo;
	static String temp = "";
	static int[][] direction = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상 좌 우 하

	static boolean inRange(int mx, int my) { // move한 곳이 범위를 벗어나는지 체크
		if (mx < 0 || mx > 3 || my < 0 || my > 3) {
			return false;
		}
		return true;
	}

	private static void search(int x, int y, int count) {
		temp += input[x][y];
		count++;

		if (count == 7) {
			boolean chk = false;
			System.out.println(temp);
			for (int i = 0; i < memo.size(); i++) {
				if (temp.equals(memo.get(i))) {
					chk = true;
					break;
				}
			}
			if (!chk) {
				memo.add(temp);
				ans++;
			}
			count = 0;
			temp = "";
			return;
		} else {
			for (int i = 0; i < direction.length; i++) {
				int nx = x + direction[i][0];
				int ny = y + direction[i][1];
				if (inRange(nx, ny)) {
					search(nx, ny, count);
				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= C; tc++) {
			ans = 0;
			memo = new ArrayList<String>();
			input = new char[4][4];

			// -- input -- //
			for (int x = 0; x < 3; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int y = 0;
				while (st.hasMoreTokens()) {
					input[x][y++] = st.nextToken().charAt(0);
				}
			}

			// -- function -- //
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					search(x, y, 0);
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}
}
