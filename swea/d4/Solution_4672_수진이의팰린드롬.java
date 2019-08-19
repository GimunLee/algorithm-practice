package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 틀림! 문제 잘 이해하자  
 */

public class Solution_4672_수진이의팰린드롬 {
	private static String str;
	private static String rStr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder output = new StringBuilder();
		
		for (int tc = 1; tc <= TC; tc++) {
			str = br.readLine();
			rStr = "";

			ans = str.length();
			set = new char[str.length()];
			visited = new boolean[str.length()];

			remake(0, 0);

			if (!rStr.equals("")) {
				for (int i = 0; i < rStr.length(); i++) {
					StringBuilder sb2 = new StringBuilder();
					String tmp2 = "";
					tmp2 += rStr.charAt(i);
					sb2.append(rStr.charAt(i));
					for (int j = i + 1; j < rStr.length(); j++) {
						tmp2 += rStr.charAt(j);
						sb2.append(rStr.charAt(j));
						sb2.reverse();
						if (tmp2.equals(sb2.toString())) {
							ans++;
						}
						sb2.reverse();
					}
				}
			}
			System.out.println("#" + tc + " "  + ans);
//			output.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
//		System.out.println(output.toString());
	} // end of main

	private static int ans = 0;

	private static char[] set;
	private static boolean[] visited;

	private static void remake(int idx, int size) {
		if (size == str.length()) {
			String tmp = new String(set);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < set.length; i++) {
				sb.append(set[i]);
			}
			sb.reverse();

			if (tmp.equals(sb.toString())) {
				rStr = tmp;
			}
			return;
		}

		if (idx == str.length()) {
			return;
		}

		for (int i = 0; i < set.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				set[size] = str.charAt(idx);
				remake(i + 1, size + 1);
				visited[i] = false;
			}
		}
	}

} // end of class
