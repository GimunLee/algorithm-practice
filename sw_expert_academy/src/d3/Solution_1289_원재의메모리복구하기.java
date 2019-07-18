package d3;

import java.util.Scanner;

public class Solution_1289_원재의메모리복구하기 {
	final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int i = 1; i <= tc; i++) {
			String input = sc.next();
			int Answer = 0;
			char p = '0';

			for (int j = 0; j < input.length(); j++) {
				if (input.charAt(j) != p) {
					if (input.charAt(j) == '0')
						p = '0';
					else
						p = '1';
					Answer++;
				}
			}
			System.out.printf("#%d %d\n", i, Answer);
		}
	}
}
