package programmers.level2;

public class Solution_문자가포함돼있는지판별 {
	public static void main(String[] args) {
		String input = "a1234";
		solution(input);
	}

	public static boolean solution(String s) {
		if (s.length() == 4 || s.length() == 6) {
			try {
				int tmp = Integer.parseInt(s);
			} catch (NumberFormatException ne) {
				return false;
			}
		} else {
			return false;
		}
		return true;

	}
}
