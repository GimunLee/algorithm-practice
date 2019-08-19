package boj.acdp1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11727 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] d = new int[1001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		d[0] = 1;
		d[1] = 1;

		for (int i = 2; i <= n; i++) {
			d[i] += (d[i - 1] + 2 * d[i - 2]) % 10007;
		}

		System.out.println(d[n]);
	}
}
