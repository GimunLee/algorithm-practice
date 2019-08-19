package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 응용] 1일차 - 단순 2진 암호코드
 */
public class Solution_1240_단순2진암호코드 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		String[] decode = { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011",
				"0110111", "0001011" };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim()); // Test Case

		for (int tc = 1; tc <= C; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 배열의 세로 크기
			int M = Integer.parseInt(st.nextToken()); // 배열의 가로 크기

			int Answer = 0;
			char[][] input_arr = new char[N][M];

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					input_arr[i][j] = input.charAt(j);
				}
			}

			int[] num = new int[8];
			int index = 7;

			for (int i = 0; i < N; i++) {
				for (int j = M - 1; j >= 0; j--) {
					if (input_arr[i][j] == '1') { // 끝에서부터 탐색
						for (int k = 0; k < 8; k++) {
							String tmp = "";
							for (int l = 6; l >= 0; l--) {
								tmp += Character.toString(input_arr[i][j - l]);
							}
							for (int l = 0; l < decode.length; l++) {
								if (tmp.equals(decode[l])) {
									if (index < 0) {
										if (((num[0] + num[2] + num[4] + num[6]) * 3 + (num[1] + num[3] + num[5])
												+ num[7]) % 10 == 0) {
											int sum = 0;
											for (int l2 = 0; l2 < 8; l2++) {
												sum += num[l2];
											}
											Answer = sum;
										}
										break;
									}
									num[index--] = l;
									break;
								}
							}
							j -= 7;
						}
						break;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, Answer);
		}
	}
}
