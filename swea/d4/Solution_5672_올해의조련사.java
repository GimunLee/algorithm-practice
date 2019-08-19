package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * Upload 
 * */

public class Solution_5672_올해의조련사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			sb.append('#').append(tc).append(' ');

			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 2000
			char[] input = new char[N];
			int front = -1, rear = -1;

			for (int i = 0; i < N; i++) {
				input[++rear] = br.readLine().charAt(0);
			}
//			System.out.println(Arrays.toString(input));

			++front;
			while (front != rear) {
//				System.out.println(front + ", " + rear);
//				System.out.println(sb.toString());
				char first = input[front];
				char last = input[rear];

				if (first < last) {
					sb.append(first);
					front++;
				} else if (first > last) {
					sb.append(last);
					rear--;
				} else {
					boolean[] visited = new boolean[input.length];
					visited[front] = true;
					visited[rear] = true;
					int sub = 0;
					while (true) {
						sub++;
						char pre = input[front+sub];
						char post = input[rear-sub];
						
						if(visited[front+sub] || visited[rear-sub] ) {
							sb.append(input[front++]);
							break;
						}
						
						if (pre < post) {
							sb.append(input[front++]);
							break;
						} else if (pre > post) {
							sb.append(input[rear--]);
							break;
						} 
					}
				}
			}
			sb.append(input[front]);
			sb.append('\n');
		} // end of for TestCase
		System.out.print(sb.toString());
	} // end of main
} // end of class
