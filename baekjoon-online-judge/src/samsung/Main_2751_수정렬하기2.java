package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2751_수정렬하기2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine().trim());
		boolean[] arr = new boolean[2000001];

		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			val += 1000000;
			arr[val] = true;
		}

		int temp = 0;
		for (int i = 0; i <= 2000000; i++) {
			if (arr[i]) {
				sb.append(i-1000000).append("\n");
				temp++;

				if (temp == N) {
					break;
				}
			}
		}
		System.out.println(sb.toString());
	}
}
