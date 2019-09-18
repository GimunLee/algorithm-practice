package practiceb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_Day03_4038_단어가등장하는횟수 {
	private static final int HASH_VALUE = 17;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			int ANSER = 0;
			String B = br.readLine();
			String S = br.readLine();

			int key = getKey(S);

			// B를 S의 Length만큼 잘라서 하나 만든 다음

			int idx = 0;
			while (idx < B.length() - S.length() + 1) {
				if (B.charAt(idx) != S.charAt(0)) {
					idx++;
					continue;
				}

				String subB = B.substring(idx, idx + S.length());
				int compareKey = getKey(subB);

				if (key == compareKey && S.equals(subB)) {
					ANSER++;
				}
				idx++;
			}
			sb.append("#").append(tc).append(" ").append(ANSER).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int getKey(String S) {
		int key = 0;

		for (int i = 0; i < S.length(); i++) {
			key = key * HASH_VALUE + S.charAt(i);
		}

		if (key < 0) {
			key = -key;
		}

		return key % 21;
	}
}
