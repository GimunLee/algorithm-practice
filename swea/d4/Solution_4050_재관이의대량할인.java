package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4050_재관이의대량할인 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());

			int[] arr = new int[N];
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];

			}

			Arrays.sort(arr);
			
			int sub =0;
			int idx = arr.length-1;
			
			for (int i = 0; i < arr.length / 3; i++) {
				sub = arr[idx-2];
				sum -= sub;
				idx-=3;
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}
}
