package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [S/W 문제해결 기본] 3일차 - String
 */
public class Solution_1213_String {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) {
			int tc = Integer.parseInt(br.readLine().trim());
			String findWord = br.readLine();
			String input = br.readLine();
			int ans = 0;

			for (int j = 0; j < input.length(); j++) {
				if (input.charAt(j) == findWord.charAt(0)) {
					String temp = "";
					if(j+findWord.length() > input.length()) {
						break;
					}
					for (int k = j; k < j+findWord.length(); k++) {
						temp += input.charAt(k);
						
					}
					if (temp.equals(findWord)) {
						ans++;
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}

	}
}
