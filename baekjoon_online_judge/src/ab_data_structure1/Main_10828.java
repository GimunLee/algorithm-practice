package ab_data_structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_10828 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			String command = st.nextToken();
			
			if(command.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				stack.push(num);
			}
			
			if(command.equals("pop")) {
				if(stack.isEmpty()) {
					System.out.println("-1");
				}else {
					System.out.println(stack.pop());
				}
			}
			
			if(command.equals("size")) {
				System.out.println(stack.size());
			}
			
			if(command.equals("empty")) {
				if(stack.isEmpty()) {
					System.out.println("1");
				}else {
					System.out.println("0");
				}
			}
			
			if(command.equals("top")) {
				if(stack.isEmpty()) {
					System.out.println("-1");
				}else {
					System.out.println(stack.lastElement());
				}
			}
		}
	}
}
