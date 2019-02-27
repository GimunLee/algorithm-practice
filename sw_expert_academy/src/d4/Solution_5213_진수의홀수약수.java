package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5213_진수의홀수약수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] dp = new long[1000001];

		for (int i = 1; i < 1000001; i += 2) {
			for (int j = i; j < 1000001; j += i) {
				dp[j] += i;
			}
		}

		long[] sum = new long[1000001];
		
		for(int i=1; i<1000001; i++) {
			sum[i] = dp[i] + sum[i-1];
		}
		
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long ans = sum[R] - sum[L - 1];

			System.out.println("#" + tc + " " + ans);
		}
	}
}
