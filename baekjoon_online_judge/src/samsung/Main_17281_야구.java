package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281_야구 {
	private static int N;
	private static int[][] hitter;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine().trim()); // 이닝 수
		hitter = new int[N][9];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < hitter[i].length; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				hitter[i][j] = tmp;
			}
		} // end of input

		set[3] = 0;
		setHitter(0);
		System.out.println(ans);

	} // end of main

	private static int[] set = new int[9];
	private static boolean[] isSet = new boolean[9];
	private static int ans = 0;

	private static void setHitter(int len) {
		if (len == 3) {
			setHitter(len + 1);
			return;
		}
		
		if (len == 9) {
			int tmp_ans = 0; // 임시 점수 
			int nHitter = 0; // 다음 타자

			// 게임 진행
			here: for (int i = 0; i < N; i++) {
				int out_cnt = 0;

				boolean[] base = new boolean[4]; // 1: 1루, 2: 2루, 3: 3루
				while (true) {
					for (int j = nHitter; j < set.length; j++) {
						nHitter = (j + 1 == 9) ? 0 : j + 1;
						int state = hitter[i][set[j]];
						if (state == 4) { // 홈런
							tmp_ans++;
						}

						if (state != 0) { // 타자가 칠 수 있다면,
							for (int k = base.length - 1; k > 0; k--) {
								if (base[k]) { // 베이스에 주자가 있다면,
									if (k + state >= 4) { // 득점
										tmp_ans++;
										base[k] = false;
									} else {
										base[k + state] = true;
										base[k] = false;
									}
								}
							}
							if (state < 4) {
								base[state] = true;
							}
						} else {
							out_cnt++;
							if (out_cnt >= 3) {
								continue here;
							}
						}
					}
				}
			}
			ans = tmp_ans > ans ? tmp_ans : ans;
			return;
		}
		
		for (int i = 1; i < set.length; i++) {
			if (!isSet[i]) {
				set[len] = i;
				isSet[i] = true;
				setHitter(len + 1);
				isSet[i] = false;
			}
		}
	} // end of setHitter
} // end of class
