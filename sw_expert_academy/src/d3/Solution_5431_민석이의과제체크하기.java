package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5431 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine()); // test case
		
		for (int i = 1; i <= tc; i++) {
			String[] input1 = br.readLine().split(" "); // 0 : 수강생 수, 1 : 과제 제출한 사람의 수
			int student_n = Integer.parseInt(input1[0]);
			int submit_n = Integer.parseInt(input1[1]);

			int[] cnt = new int[student_n+1]; 
			
			String[] input2 = br.readLine().split(" ");
			
			for (int j = 0; j < input2.length; j++) {
				cnt[Integer.parseInt(input2[j])] = 1; 
			}
			
			System.out.printf("#%d ",i);
			for (int j = 1; j < cnt.length; j++)
				if(cnt[j] != 1)	System.out.print(j + " ");
			System.out.println();
		}
	}
}
