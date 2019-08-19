package swea.d3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution_1208_Flatten {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) {
			int dump_n = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			int[] height = new int[input.length];

			for (int j = 0; j < input.length; j++)
				height[j] = Integer.parseInt(input[j]);

			dump_func(dump_n, height, i);
		}
	}

	private static void dump_func(int dump_n, int[] height, int i) throws IOException {
		Arrays.sort(height);
		if (dump_n == 0) {
			System.out.println("#" + i + " " + (height[99] - height[0]));
		} else {
			// 제일 높은 수를 빼고 제일 낮은수를 더한다.
			height[99]--;
			height[0]++;
			dump_n--;
			dump_func(dump_n, height, i);
		}
	}
}
