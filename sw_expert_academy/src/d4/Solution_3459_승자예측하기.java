package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 마지막 A턴 : A는 작은수 2x, B는 큰수 2x+1
 * 마지막 B턴 : A는 큰수 2x+1, B는 작은수 2x
 */

public class Solution_3459_승자예측하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			long N = Long.parseLong(br.readLine().trim()); // N

			System.out.print("#" + tc);

			long A = 0;
			long B = 1;
			while (true) {
				A = (A * 2 + 1) * 2; // A 값 이상이면 Alice 승
//				System.out.println(A);
				if (N < A) {
					System.out.println(" Bob");
					break;
				}

				B = (B * 2 + 1) * 2; // B 값 이상이면 Bob 승
				if (N < B) {
					System.out.println(" Alice");
					break;
				}
//				System.out.println(B);
			}
		} // end of for of TestCase
	} // end of main
} // end of class
