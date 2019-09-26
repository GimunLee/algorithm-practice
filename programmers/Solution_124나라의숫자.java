package programmers;

public class Solution_124나라의숫자 {
	public static void main(String[] args) {
		int n = 10;
		System.out.println(solution(n));
	}

	public static String solution(int n) {
		String answer = "";
		int[] role = { 4, 1, 2 };

		while (n > 0) {
			int mod = n % 3;
			n /= 3;
			if (mod == 0) {
				n--;
			}
			answer = role[mod] + answer;
		}

		return answer;
	}
}
