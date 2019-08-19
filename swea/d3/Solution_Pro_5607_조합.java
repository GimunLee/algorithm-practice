package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * [Professional] 조합
 */
public class Solution_Pro_5607_조합 {
	static long P = 1234567891;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine().trim());

		long[] fac = new long[1000001];
		long[] inv = new long[1000001];

		for (int i = 1; i <= tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long N = Integer.parseInt(st.nextToken());
			long R = Integer.parseInt(st.nextToken());

			long Answer;

			fac[0] = 1;

			for (int j = 1; j <= 1000000; j++) {
				fac[j] = (fac[j - 1] * j) % P;
			}

			inv[1000000] = pow(fac[1000000], P - 2);

			for (int j = 1000000 - 1; j >= 0; j--) {
				inv[j] = (inv[j + 1] * (j + 1)) % P;
			}

			Answer = (fac[(int) N] * inv[(int) (N - R)]) % P;
			Answer = (Answer * inv[(int) R]) % P;

			System.out.println("#" + i + " " + Answer);
		}
	}

	static long pow(long x, long y) {
		long ret = 1;
		while (y > 0) {
			if (y % 2 != 0) {
				ret *= x;
				ret %= P;
			}
			x *= x;
			x %= P;
			y /= 2;
		}
		return ret;
	}
}
