package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1865_동철이의일분배_memo {
	public static int N;
	public static int p[][];
	public static double memo[] = new double[1 << 16];

	public static void main(String[] ar) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		long time = System.currentTimeMillis();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(in.readLine());
			p = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Arrays.fill(memo, -1); // 객체를 새로 생성하는 것보다 40ms 빠름
			double res = f(0, 0);
			System.out.printf("#%d %.6f\n", testCase, res / Math.pow(100, N - 1));
		} // end of for testCase
		System.out.println(System.currentTimeMillis() - time + " ms");
	} // end of main

	/**
	 * step : N개 순열에서 하나씩 선택해가는 단계 flag : 이미 선택한 정점들을 비트로 표시한 값 이미 선택한 정점들중에서 순서를
	 * 바꾸어 최대로 만들수 있는 값을 memo 배열에 저장함 12345 와 21345 의 순열을 만들어가는 과정에서 2번째 step 까지
	 * 선택했을 경우 최대값이 필요하므로 12 와 21 중 답이 될수 있는 큰값만 저장한다. 1번 정점, 2번 정점을 선택하는 경우의 최대값을
	 * 1,2 번을 비트마스킹 한 flag를 index로 사용하여 memo[flag] 에 저장
	 */
	public static double f(int step, int flag) {
		if (step == N) {
			return 1;
		}
		if (memo[flag] >= 0) {
			return memo[flag];
		}
		double max = 0;
		for (int i = 0; i < N; i++) {
			int bitMask = 1 << i;
			if ((flag & bitMask) == 0) { // 사용하지 않은 숫자라면
				double next = f(step + 1, flag | bitMask) * p[step][i];
				if (max < next) {
					max = next;
				}
			}
		}
		memo[flag] = max;
		return max;
	}
} // end of class