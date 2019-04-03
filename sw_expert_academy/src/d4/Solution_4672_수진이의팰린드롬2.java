package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution_4672_수진이의팰린드롬2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			String str = br.readLine();
			int[] a = new int[26];

			for (int i = 0; i < str.length(); i++) {
				int idx = str.charAt(i) - 'a';
				a[idx]++;
			}

			int ans = 0;

			for (int i = 0; i < a.length; i++) {
				ans += a[i] * (a[i] + 1) / 2;
			}
			
			System.out.println("#" + tc + " " + ans);
		} // end of for TestCase
	} // end of main
} // end of class
