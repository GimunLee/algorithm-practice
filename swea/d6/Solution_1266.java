package d6;

import java.util.Scanner;

public class Solution_1266 {
	public static double comb(double a, double b) {
		double num = 1d;
		for (int i = 1; i <= b; i++) {
			num *= a - i + 1;
			num /= i;
		}
		return num;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int tc = 1; tc <= C; tc++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			double c = a / 100d;
			double d = b / 100d;

			int[] np = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 };

			double cs = 0;
			double ds = 0;

			for (int i = 0; i < 12; i++) {
				cs += Math.pow(c, np[i]) * Math.pow(1d - c, 18 - np[i]) * comb(18, np[i]);
				ds += Math.pow(d, np[i]) * Math.pow(1d - d, 18 - np[i]) * comb(18, np[i]);
			}

			double p;
			
			if (cs == 0d && ds == 0d)
				p = 1d;
			else if (cs == 0d)
				p = ds;
			else if (ds == 0d)
				p = cs;
			else
				p = cs * ds;
			System.out.printf("#%d %.6f\n", tc, (1 - p));
		}
	}
}