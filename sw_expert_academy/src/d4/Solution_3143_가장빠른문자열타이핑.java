package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3143_가장빠른문자열타이핑 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String A = st.nextToken();
			String B = st.nextToken();

			int ANS = 0;
			for (int i = 0; i < A.length(); i++) {
				String temp = "";
				if (i + B.length() <= A.length()) {
					temp = A.substring(i, i + B.length());
				}

				if (temp.equals(B)) {
					i += B.length() - 1;
					ANS++;
				} else {
					ANS++;
				}
			}

			sb.append('#').append(tc).append(' ').append(ANS).append('\n');
		} // end of TestCase
		System.out.println(sb.toString());
	} // end of main
} // end of class
