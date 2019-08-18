package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 밀가루 옮기기 BruteForce : 답은 정확히 나옴, 시간초과 불가능 Greedy : 기존 현실에서 사용하는 가설로는 불가능, 큰
 * 숫자의 일부를 큰 단위 봉지로 나누어 몫을 저장, 나머지 짜투리는 미리 구해놓고, 더한다. DP : 빠르다, 동적계획법, 작은해부터 구해서
 * 큰 해를 찾음
 * 
 */

public class Algo2_서울_05_이기문_Prof {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력할 문자열을 저장할 변수 
		
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int W = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());

			int[] pack = new int[N]; // 봉지 용량의 종류를 저장할 배열
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < pack.length; i++) {
				pack[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[W + 1];
			final int M = Integer.MAX_VALUE; // 불가능

			for (int i = 0; i < dp.length; i++) { // 첫번째 종류의 봉투
				if (i % pack[0] == 0) {
					dp[i] = i / pack[0];
				} else {
					dp[i] = M;
				}
			}

			// 두번째 이후 봉투는 한번에 묶어서 처리
			for (int i = 1; i < pack.length; i++) { // 봉투 종류
				for (int j = pack[i]; j < dp.length; j++) {
					if (dp[j - pack[i]] != M && dp[j] > dp[j - pack[i]] + 1) {
						dp[j] = dp[j - pack[i]] + 1; // 점화식
					}
				}
			}

//			System.out.println("#" + tc + " " + (dp[W] == M ? -1 : dp[W]));
			sb.append('#').append(tc).append(' ').append(dp[W] == M ? -1 : dp[W]).append('\n');
		} // end of testCase

		System.out.print(sb.toString());
	} // end of main
} // end of class
