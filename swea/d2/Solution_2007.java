package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 패턴 마디의 길이 
 */
public class Solution_2007 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= C; tc++) {
			String input = br.readLine();
			String repeat = "";
			String temp = "" + input.charAt(0);
			for (int j = 1; j < 10; j++) { // 최대 10마디
				temp += input.charAt(j);
				String temp2 = "";
				for (int k = 1; k <= j+1; k++) { // 검사
					temp2 += input.charAt(j+k);
				}
				if(temp.equals(temp2)) {
					repeat = temp;
					break;
				}
			}
			
			System.out.println("#"+ tc + " " + repeat.length());
			
			
		}
	}
}
