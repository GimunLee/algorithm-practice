package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1077_배낭채우기1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 보석의 가지수
		int W = Integer.parseInt(st.nextToken()); // 배낭의 용량

		int[][] arr = new int[N][2];
		int[] dp = new int[W + 1]; // 0:보석의 무게, 1: 값어치

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			arr[i][0] = weight;
			arr[i][1] = value;
		}

		for (int i = 0; i < arr.length; i++) {
			int tmp = 1;
			for (int j = arr[i][0]; j < dp.length; j += arr[i][0]) {
				int tmp_value = arr[i][1] * tmp++;
				if (dp[j] == 0) {
					dp[j] = tmp_value;
				} else {
					dp[j] = Math.max(tmp_value, dp[j]);
				}
			}
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i > arr[j][0]) {
					dp[i] = Math.max(dp[i], dp[i - arr[j][0]] + arr[j][1]);
				}
			}
		}
		System.out.println(Arrays.toString(dp));

		System.out.println(dp[W]);

	} // end of main

} // end of class
