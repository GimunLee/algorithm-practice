package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4261_빠른휴대전화키패드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String S = st.nextToken(); // 1<= N <= 1000
			int N = Integer.parseInt(st.nextToken()); //

			st = new StringTokenizer(br.readLine(), " ");

			String[] arr = new String[N];

			int ans = 0;
			for (int i = 0; i < N; i++) {
				arr[i] = st.nextToken();
				String temp = "";

				for (int j = 0; j < arr[i].length(); j++) {
					temp += alphaToString(arr[i].charAt(j));
				}

				if (temp.equals(S)) {
					ans++;
				}
			}
			System.out.println("#" + tc + " " + ans);
		} // end of for TestCase

	} // end of main

	private static String alphaToString(char c) {
		if (c == 'a' || c == 'b' || c == 'c')
			return "2";
		if (c == 'd' || c == 'e' || c == 'f')
			return "3";
		if (c == 'g' || c == 'h' || c == 'i')
			return "4";
		if (c == 'j' || c == 'k' || c == 'l')
			return "5";
		if (c == 'm' || c == 'n' || c == 'o')
			return "6";
		if (c == 'p' || c == 'q' || c == 'r' || c == 's')
			return "7";
		if (c == 't' || c == 'u' || c == 'v')
			return "8";
		if (c == 'w' || c == 'x' || c == 'y' || c == 'z')
			return "9";
		return "0";
	}
} // end of class
