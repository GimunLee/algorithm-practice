package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
	int r;
	int c;

	Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Algo1_서울_05_이기문 {
	static char[][] map;
	static ArrayList<Pair> pList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N*N

			map = new char[N][N];
			pList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = st.nextToken().charAt(0);

					if (map[r][c] == 'A' || map[r][c] == 'B' || map[r][c] == 'C') {
						pList.add(new Pair(r, c));
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < pList.size(); i++) {
				int r = pList.get(i).r;
				int c = pList.get(i).c;

				if (map[r][c] == 'A') {
					while (r - 1 >= 0 && map[r - 1][c] == 'S') {
						r--;
						cnt++;
					}
				}

				if (map[r][c] == 'B') {
					int cTmp = c;
					while (cTmp - 1 >= 0 && map[r][cTmp - 1] == 'S') {
						cTmp--;
						cnt++;
					}
					cTmp = c;
					while (cTmp + 1 < N && map[r][cTmp + 1] == 'S') {
						cTmp++;
						cnt++;
					}
				}

				if (map[r][c] == 'C') {
					int rTmp = r;
					int cTmp = c;
					while (cTmp - 1 >= 0 && map[r][cTmp - 1] == 'S') {
						cTmp--;
						cnt++;
					}
					rTmp = r;
					cTmp = c;
					while (cTmp + 1 < N && map[r][cTmp + 1] == 'S') {
						cTmp++;
						cnt++;
					}
					rTmp = r;
					cTmp = c;
					while (rTmp - 1 >= 0 && map[rTmp - 1][c] == 'S') {
						rTmp--;
						cnt++;
					}
					rTmp = r;
					cTmp = c;
					while (rTmp + 1 < N && map[rTmp + 1][c] == 'S') {
						rTmp++;
						cnt++;
					}
				}
			}

			System.out.println("#" + tc + " " + cnt);
		} // end of for of TestCase

	} // end of main
} // end of class
