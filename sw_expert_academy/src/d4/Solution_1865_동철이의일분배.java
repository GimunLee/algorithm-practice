package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 안끝남 : dfs 200ms 16*16 : dfs 가지치기 메모이제이션 : 즉시
 */
public class Solution_1865_동철이의일분배 {
	private static double[][] p;
	private static double max;
	private static int N;
	private static int[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		long time = System.currentTimeMillis();
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine()); // 1 <= N <= 16

			p = new double[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken()) / 100D; // 1 <= Pij <= 100
				}
			}
			select = new int[N];
			for (int i = 0; i < N; i++) {
				select[i] = i;
			}
			max = 0;
			f(0, 100d);

			System.out.printf("#%d %.6f\n", testCase, max); // 반올림 소수점 아래 6째자리까지 출력
		} // end of for testCase
		System.out.println(System.currentTimeMillis() - time + " ms");
	} // end of main

	public static void f(int step, double pp) {
		if (step == N) {
			if (max < pp) {
				max = pp;
			}
		} else {
			for (int i = step; i < N; i++) {
				swap(step, i);
				if (max < pp * p[step][select[step]]) {
					f(step + 1, pp * p[step][select[step]]);
				}
				swap(step, i);
			}
		}
	}

	public static void swap(int i, int j) {
		int temp = select[i];
		select[i] = select[j];
		select[j] = temp;
	}
} // end of class
