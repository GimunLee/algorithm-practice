package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백만 장자 프로젝트
 */
public class Solution_1859 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int tc_n = 1; tc_n <= tc; tc_n++) {
			int N = Integer.parseInt(br.readLine().trim()); // 2 <= N <= 1,000,000
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			long benefit = 0; // 마진의 누적합을 저장할 변수, long type 변수를 써라 (100억이므로, int는 21억)
			int max = 0; // 매매가의 최대값을 저장할 변수
			// 뒤에서부터 탐색해라
			for (int i = N - 1; i >= 0; i--) { // 0 <= 매매가 <= 10,000
				if (max < input[i]) {
					max = input[i]; // 구매하지 않는 것이 이득
				} else { // 최대값이 아닌 경우
					int num = max - input[i]; // 현재의 마진
					benefit += num; // 누적
				}
			}
			System.out.println("#" + tc_n + " " + benefit);
		} // end of testCase
	} // end of main
} // end of class
