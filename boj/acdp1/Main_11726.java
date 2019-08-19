package boj.acdp1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2xn 타일링
 */
public class Main_11726 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine().trim());

		int[] d = new int[1001];

		d[0] = 1; // 2x0 1번
		d[1] = 1;

		for (int i = 2; i <= n; i++) {
			d[i] = (d[i - 1] + d[i - 2]) % 10007;
		}

		System.out.println(d[n]);
	}
}
