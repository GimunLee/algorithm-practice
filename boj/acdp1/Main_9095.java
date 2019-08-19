package boj.acdp1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9095 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] d = new int[11];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		d[0] = 1;
		d[1] = 1; // 1
		d[2] = 2; // 1 1 / 2
		d[3] = 4; // 1 1 1 / 1 2 / 3 / 2 1

		int tc = Integer.parseInt(br.readLine().trim());
		for (int tc_n = 0; tc_n < tc; tc_n++) {
			int n = Integer.parseInt(br.readLine().trim());
			for (int i = 4; i <= n; i++) {
				d[i] = d[i - 1] + d[i - 2] + d[i - 3];
			}
			System.out.println(d[n]);
		}
	}
}
