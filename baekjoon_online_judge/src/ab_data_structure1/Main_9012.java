package ab_data_structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9012 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= tc; i++) {
			char[] stack = new char[100];
			int top = -1;
			
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				char tmp = str.charAt(j);
			
				switch(tmp) {
				case '(':
				case ')':
					stack[++top] = tmp;
					break;
				}
				if(stack[top] == ')') {
					if(top > 0 && stack[top-1] == '(') {
						top-=2;
					}
				}
			}
			if(top < 0) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
}
