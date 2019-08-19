package boj.etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_9207_페크솔리테어 {
	static char[][] map;
	static int R = 5;
	static int C = 9;
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	static ArrayList<Pair> pinList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 100

		for (int tc = 1; tc <= TC; tc++) {
			map = new char[5][9];
			minPinCntAns = Integer.MAX_VALUE;
			minMovePinCntAns = Integer.MAX_VALUE;
			pinList = new ArrayList<>();
			int pinCnt = 0;
			for (int r = 0; r < R; r++) {
				String str = br.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = str.charAt(c);
					if (map[r][c] == 'o') {
						pinCnt++;
						pinList.add(new Pair(r, c));
					}
				}
			}
			if (tc < TC)
				br.readLine();
			// -- end of for(input)

			solve(0, pinCnt, 0); // idx, 핀 개수, 최소이동횟수
			sb.append(minPinCntAns).append(" ").append(minMovePinCntAns).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of class

	static int minPinCntAns;
	static int minMovePinCntAns;

	private static void solve(int idx, int pinCnt, int movePinCnt) {
		if (idx == pinList.size()) { // 모든 핀 탐색을 했을 경우,
			if (minPinCntAns > pinCnt) {
				minPinCntAns = pinCnt;
				minMovePinCntAns = movePinCnt;
			}
			if (minPinCntAns == pinCnt) {
				if (minMovePinCntAns > movePinCnt) {
					minPinCntAns = pinCnt;
					minMovePinCntAns = movePinCnt;
				}
			}
			return;
		}

		int r = pinList.get(idx).r;
		int c = pinList.get(idx).c;

		if (map[r][c] != 'o' || pinList.get(idx).isDie) {
			solve(idx + 1, pinCnt, movePinCnt);
			return;
		}

		for (int j = 0; j < dr.length; j++) {
			int nR = r + dr[j];
			int nC = c + dc[j];

			if (nR < 0 || nC < 0 || nR >= R || nC >= C)
				continue;
			if (nR + dr[j] < 0 || nC + dc[j] < 0 || nR + dr[j] >= R || nC + dc[j] >= C)
				continue;
			if (map[nR + dr[j]][nC + dc[j]] != '.')
				continue;

			// -- 점프할 수 있는 경우
			if (map[nR][nC] == 'o') { // 인접한 곳에 있다면, 점프
				map[r][c] = '.';
				map[nR][nC] = '.';
				map[nR + dr[j]][nC + dc[j]] = 'o';
				pinList.get(idx).r = nR + dr[j];
				pinList.get(idx).c = nC + dc[j];

				pinList.get(idx).isDie = true;
				solve(idx + 1, pinCnt - 1, movePinCnt + 1);
				pinList.get(idx).isDie = false;
				solve(0, pinCnt - 1, movePinCnt + 1);

				// 되돌리기
				map[r][c] = 'o';
				map[nR][nC] = 'o';
				map[nR + dr[j]][nC + dc[j]] = '.';
				pinList.get(idx).r = r;
				pinList.get(idx).c = c;
			}
		}
		solve(idx + 1, pinCnt, movePinCnt);
	} // end of main

	private static class Pair {
		int r, c;
		boolean isDie;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
			isDie = false;
		}
	} // end of Pair

} // end of class