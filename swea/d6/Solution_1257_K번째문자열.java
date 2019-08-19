package swea.d6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Solution_1257_K번째문자열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			int ans_index = Integer.parseInt(br.readLine());
			String input = br.readLine();
			HashSet<String> input_set = new HashSet<String>();

			for (int i = 0; i < input.length(); i++) {
				for (int j = i + 1; j <= input.length(); j++) {
					input_set.add(input.substring(i, j));
				}
			}

			List input_list = new ArrayList(input_set);

			Collections.sort(input_list);

			if (input_list.size() < (ans_index - 1)) {
				System.out.println("#" + tc + " " + "none");
				continue;
			}
			System.out.println("#" + tc + " " + input_list.get(ans_index - 1));

		}

	}
}
