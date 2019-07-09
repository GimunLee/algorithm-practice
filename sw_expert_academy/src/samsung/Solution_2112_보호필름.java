package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_보호필름 {
	private static int D;
	private static int W;
	private static int K;
	private static int[][] map;
	private static int[][] tmp_map;
	private static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // 보호 필름의 두께 (3<= <=13)
			W = Integer.parseInt(st.nextToken()); // 보호 필름의 가로 크기 (1<= <=20)
			K = Integer.parseInt(st.nextToken()); // 합격 기준 (1<= <=D)

			map = new int[D][W];
			tmp_map = new int[D][W];
			set = new int[D];

			// 0 : A, 1 : B
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					tmp_map[r][c] = map[r][c];
				}
			} // end of for(input)

			ans = Integer.MAX_VALUE;

			if (checkFilm()) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}

			flag = false;
			permu(0);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString()); // 정답 출력
	} // end of main

	private static boolean checkFilm() {
		int unChecked = W;
		// 검사 먼저하기 (반복문), 모두 K개인지
		for (int c = 0; c < W; c++) {
			// 하나의 열 검사 변수
			int r = 0;

			while (r < D) {
				int each_cnt_A = 0;
				int each_cnt_B = 0;
				while (r < D && map[r][c] == 0) {
					each_cnt_A++;
					r++;
				}
				if (each_cnt_A >= K) {
					unChecked--;
					break;
				}
				while (r < D && map[r][c] == 1) {
					each_cnt_B++;
					r++;
				}
				if (each_cnt_B >= K) {
					unChecked--;
					break;
				}
			}
		} // end of for(each column check)

		if (unChecked == 0) {
			return true;
		} else {
			return false;
		}
	} // end of func(checkFilm)

	private static int[] set;
	private static boolean flag;

	private static void permu(int len) {
		if (flag && len == D) {
			int tmp_ans = 0;
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					tmp_ans++;
					if (tmp_ans >= ans) {
						return;
					}
				}
			}

			// Type 바꾸기
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					int changeType = set[i] - 1;
					for (int j = 0; j < W; j++) {
						map[i][j] = changeType;
					}
				}
			}

			if (checkFilm()) { // 답이 나온 경우
				ans = tmp_ans < ans ? tmp_ans : ans;
			}

			// 원상복귀
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					for (int j = 0; j < W; j++) {
						map[i][j] = tmp_map[i][j];
					}
				}
			}
			return;
		}

		if (len == D) {
			return;
		}

		for (int i = 0; i < 3; i++) {
			set[len] = i;
			permu(len + 1);
			flag = true;
		}
	}
} // end of class
