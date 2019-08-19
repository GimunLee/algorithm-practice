package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [S/W 문제해결 기본] 4일차 - 괄호 짝짓기
 */
public class Solution_1218_괄호짝짓기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) { // Test Case 횟수
			int tc_len = Integer.parseInt(br.readLine()); // Test Case 길이
			String[] stack = new String[100]; // 스택 저장
			int top = 0; // 스택 높이
			int j;
			String input = br.readLine();
			String[] array = input.split(""); // Test Case 배열

			for (j = 0; j < array.length; j++) {

				if (array[j].equals("(") || array[j].equals("[") || array[j].equals("{") || array[j].equals("<")) {
					stack[top++] = array[j];
				} else {
					String tmp = stack[--top];

					if (array[j] == null)
						break;

					if (array[j].equals(")") && tmp.equals("("))
						;
					else if (array[j].equals("]") && tmp.equals("["))
						;
					else if (array[j].equals("}") && tmp.equals("{"))
						;
					else if (array[j].equals(">") && tmp.equals("<"))
						;
					else
						break;
				}
			}
			if (top == 0 && j == array.length) {
				System.out.println("#" + i + " 1");
			} else {
				System.out.println("#" + i + " 0");
			}
		}
	}
}
