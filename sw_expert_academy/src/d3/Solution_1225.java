package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 기본] 7일차 - 암호생성기 
 */
public class Solution_1225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) { // 10번
			br.readLine(); // 안씀
			
			int[] q = new int[8];
			int front = -1; 
			int rear = -1;

			int sub = 1;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < 8; i++) {
				q[++rear] = Integer.parseInt(st.nextToken()); 
			}
			
			int tmp = 99;
			while(tmp != 0 ) { // 5까지 작아지고, 숫자가 감소할때 0보다 작아지는 경우
//				System.out.println(Arrays.toString(q));
				if(sub > 5)	sub = 1; 
				tmp = q[0] - sub;
				for (int i = 0; i < q.length-1; i++) {
					q[i] = q[i+1];
				}
				if(tmp < 0) {
					tmp = 0;
				}
				q[7] = tmp;
				sub++;
			}
			System.out.print("#" + tc);
			for(int f : q) {
				System.out.print(" " + f);	
			}
			System.out.println();
			
		}
	}// end of main
}// end of class
