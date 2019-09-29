package programmers.level2;

public class Solution_k개수제거 {
	public static void main(String[] args) {
		String number = "4177252841";
		// 47725--
		int k = 4;
		System.out.println(solution(number, k));
	}

	public static String solution(String number, int k) {
		String answer = "";
		StringBuilder sb = new StringBuilder(number);
		// 다음 수가 더 크다면 삭제한다.
		for (int i = 0; i < sb.length() - 1; i++) {
			int num1 = sb.charAt(i);
			int num2 = sb.charAt(i + 1);
			if (num1 < num2) {
				sb.deleteCharAt(i);
				i = -1;
				k--;
				if (k == 0) {
					break;
				}
			}
		}
		if (k != 0) {
			sb.delete(sb.length() - k, sb.length());
		}
		return sb.toString();
	}
}
