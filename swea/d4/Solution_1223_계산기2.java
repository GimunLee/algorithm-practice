package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [S/W 문제해결 기본] 6일차 - 계산기2
 */
public class Solution_1223_계산기2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int cnt = Integer.parseInt(br.readLine());
			String[] tmp = br.readLine().split("");

			String[] stack = new String[cnt];
			int top = -1;

			for (int i = 0; i < tmp.length; i++) {
				stack[++top] = tmp[i];
				if (stack[top].equals("*")) {
					int A = Integer.parseInt(stack[--top]);
					int B = Integer.parseInt(tmp[i + 1]);
					stack[top] = (A * B) + "";
					i++;
				}
			}

			for (int i = top; i > -1; i--) {
				if (stack[i].equals("+")) {
					int A = Integer.valueOf(stack[i + 1]);
					int B = Integer.valueOf(stack[i - 1]);
					top -= 2;
					stack[top] = (A + B) + "";
				}
			}
			System.out.printf("#%d %s\n", tc, stack[0]);
		}
	} // end of main
} // end of class
