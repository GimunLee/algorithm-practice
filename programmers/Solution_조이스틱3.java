package programmers;

class Solution_¡∂¿ÃΩ∫∆Ω3 {

	public static void main(String[] args) {
		String answer = "AAABAAAAB";
		char c = 'A';
		// A:65, Z: 90
		System.out.println(solution(answer));
	}

	public static int solution(String name) {
		int answer = 0;
		int exp = name.length() - 1;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			int subA = name.charAt(i) - 'A';
			int Zsub = 'Z' - name.charAt(i) + 1;
			answer += Math.min(subA, Zsub);
			if (c == 'A') {
				int nextIdx = i + 1;
				int countA = 0;
				while (nextIdx < name.length() && name.charAt(nextIdx) == 'A') {
					countA++;
					nextIdx++;
				}
				int tmp = (i - 1) * 2 + (name.length() - 1 - i - countA);
				exp = Math.min(exp, tmp);
			}
		}

		answer += exp;
		return answer;
	}

}
