package new_mind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1535_¾È³ç {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // »ç¶÷ÀÇ ¼ö
		int[] L = new int[N];
		int[] J = new int[N];

		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		int[] dp = new int[101];

		for (int i = 0; i < N; i++) {
			L[i] = Integer.parseInt(st1.nextToken()); // Ã¼·Â
			J[i] = Integer.parseInt(st2.nextToken()); // ±â»Ý
			dp[L[i]] = J[i];
		}

		System.out.println(Arrays.toString(dp));

		for (int i = 0; i < N; i++) {
			for (int j = 99; j >= L[i]; j--) {
				dp[j] = Math.max(dp[j - L[i]] + J[i], dp[j]);
			}
		}
		System.out.println(Arrays.toString(dp));

		System.out.println(dp[99]);

	}
}
