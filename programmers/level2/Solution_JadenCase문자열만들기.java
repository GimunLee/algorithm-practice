package programmers.level2;

public class Solution_JadenCase문자열만들기 {
	public static void main(String[] args) {
		String s = "az1      1 1 by cx dw ev fu gt hs ir jq kp lo mn nm ol pk qj ri sh tg uf ve wd xc yb   za  ";
		System.out.println(solution(s));

	} // end of main

	private static String solution(String s) {
		String answer = "";
		s = s.toLowerCase();
		// a : 97 122
		answer += s.charAt(0);
		answer = answer.toUpperCase();
		for (int i = 1; i < s.length(); i++) {
			int c = (int) s.charAt(i);
			if (s.charAt(i - 1) == ' ') {
				String tmp = "";
				tmp += s.charAt(i);
				answer += tmp.toUpperCase();
			} else {
				answer += s.charAt(i);
			}
		}
		return answer;
	}
} // end of class
