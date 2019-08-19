package boj.acdp1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11052 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		long[][] d = new long[91][2];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		d[1][1] = 1;
		d[1][0] = 0;

		d[2][0] = 1;
		d[2][1] = 0;
		for (int i = 3; i <= n; i++) {
			d[i][0] = d[i - 1][0] + d[i - 1][1];
			d[i][1] = d[i - 1][0];
		}
		System.out.println(d[n][0] + d[n][1]);
	}
}
