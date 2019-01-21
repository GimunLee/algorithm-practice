package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1224. [S/W 문제해결 기본] 6일차 - 계산기3 
 */
public class Solution_1224 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc =1; tc <= 10; tc++) {
			int cnt = Integer.parseInt(br.readLine());
			String[] tmp = br.readLine().split("");
			
			String[] array = new String[cnt];
			int index = 0;
			
			String[] stack = new String[cnt];
			int top = -1;
			
			for (int i = 0; i < tmp.length; i++) {
				String s = tmp[i];
				switch(s) {
				case "(":
					stack[++top] = tmp[i];
					break;
				case "*": // 우선순위 2
					// 스택에 나보다 작은 것이 남아있을 때까지 꺼내서 출력
					while (top > -1 && stack[top].equals("*")) {
						array[index++] = stack[top];
						top--;
					}
					stack[++top] = s;
					break;
				case "+":
					// 스택에 나보다 작은 것이 남아있을 때까지 꺼내서 출력
					while (top > -1 && (stack[top].equals("*") || stack[top].equals("+"))) {
						array[index++] = stack[top];
						top--;
					}
					stack[++top] = s;
					break;
				case ")":
					// '(' 나올 때까지 스택에서 꺼내서 출력
					while (top > -1 && !stack[top].equals("(")) {
						array[index++] = stack[top];
						top--;
					}

					if (stack[top].equals("(")) {
						top--;
					}
					break;
				default: // 연산자가 아닌 경우 = 숫자(피연산자)인 경우
					array[index++] = tmp[i];
					break;
				}
			}

//			for (int i = 0; i < index; i++) {
//				System.out.print(array[i] + " ");
//			}
//			System.out.println();
			
			int[] stack2 = new int[cnt];
			int top2 = -1;
			
			for (int i = 0; i < index; i++) {
				String c  = array[i];
				int num1, num2;
				switch(c) {
				case "+":
					num2 = stack2[top2--];
					num1 = stack2[top2--];
					stack2[++top2] = num1 + num2;
					break;
				case "*":
					num2 = stack2[top2--];
					num1 = stack2[top2--];
					stack2[++top2] = num1 * num2;
					break;
				default:
					stack2[++top2] = Integer.parseInt(array[i]);
					break;
				}
			}
			System.out.printf("#%d %s\n", tc, stack2[0]);	
		}
	} // end of main
} // end of class
