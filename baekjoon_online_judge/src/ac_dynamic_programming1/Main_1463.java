package ac_dynamic_programming1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1로 만들기
 */
public class Main_1463 {
	static int[] d = new int[1000001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine().trim());

		System.out.println(go(num)); // top-down

//		bottom-up
		d[1] = 0;
		for (int i = 2; i <= num; i++) {
			d[i] = d[i - 1] + 1;

			if (i % 2 == 0 && d[i] > d[i / 2] + 1) {
				d[i] = d[i / 2] + 1;
			}
			if (i % 3 == 0 && d[i] > d[i / 3] + 1) {
				d[i] = d[i / 3] + 1;
			}
		}
		System.out.println(d[num]);
	}

//	top-down
	public static int go(int n) {
		if (n == 1) { // 종료 조건
			return 0;
		}
		if (d[n] > 0)
			return d[n]; // 종료 조건
		d[n] = go(n - 1) + 1;
		if (n % 2 == 0) {
			int temp = go(n / 2) + 1;
			if (d[n] > temp)
				d[n] = temp;
		}
		if (n % 3 == 0) {
			int temp = go(n / 3) + 1;
			if (d[n] > temp)
				d[n] = temp;
		}
		return d[n];
	}
}
