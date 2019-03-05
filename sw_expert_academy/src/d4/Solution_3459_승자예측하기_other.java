package d4;

import java.util.Scanner;

/**
 * A : 4xb + (0~3) 
 * B : xb
 */

public class Solution_3459_승자예측하기_other {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int t=1; t<=tc; t++) {
			long num = sc.nextLong();

			while(num >= 2) {
				num -= 2;
				num /= 4;
			}
			
			String result = num == 1 ? "Bob" : "Alice";
			System.out.println("#" + t + " " + result);
		}
		
	}

}
