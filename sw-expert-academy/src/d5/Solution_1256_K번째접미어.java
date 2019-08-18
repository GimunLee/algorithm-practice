package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1256_K번째접미어 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int i = 1; i <= C; i++) {

			int ans_index = Integer.parseInt(br.readLine().trim());

			String input = br.readLine();
			String[] input_arr = new String[input.length()];

			for (int j = 0; j < input.length(); j++) {
				input_arr[j] = input.substring(j, input.length());
			}

			Arrays.sort(input_arr);

			if (input_arr[ans_index] == null) {
				System.out.println("#" + " none");
				continue;
			}
			System.out.println("#" + i + " " + input_arr[ans_index - 1]);
		}
	}
}
