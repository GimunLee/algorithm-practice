package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3234_준환이의양팔저울_Prof {
	private static int[] w;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 9

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = new int[N]; // 무게, 1 <= w <= 999

			for (int i = 0; i < w.length; i++) {
				w[i] = Integer.parseInt(st.nextToken());
			}
			cnt = 0;
			p(0, 0, 0);
			System.out.println("#" + testCase + " " + cnt);
		}
	}

	public static void p(int step, int l, int r) {
		if (w.length == step) {
			cnt++; // 순열이 완성되면, 카운팅

		} else {
			for (int i = step; i < w.length; i++) {
				swap(step, i);
				p(step + 1, l + w[step], r);

				if (l >= r + w[step]) { // 더한후 왼쪽이 더 커야 함
					p(step + 1, l, r + w[step]);
				}

				swap(step, i);
			}
		}
	}

	public static void swap(int i, int j) {
		int temp = w[i];
		w[i] = w[j];
		w[j] = temp;
	}
}
