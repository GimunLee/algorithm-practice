package programmers;

import java.util.Stack;

class Solution_¿Ã¹Ù¸¥°ýÈ£ {

	public static void main(String[] args) {

		String s = ")(";
		System.out.println(solution(s));

	}

	static boolean solution(String s) {
		boolean answer = true;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.push(s.charAt(i));
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}
		if (stack.size() != 0) {
			answer = false;
		}
		return answer;
	}
}
