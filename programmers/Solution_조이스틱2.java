package programmers;

class Solution_¡∂¿ÃΩ∫∆Ω2 {

	public static void main(String[] args) {
		String answer = "AAABAAAAB";
		char c = 'A';
		// A:65, Z: 90
		System.out.println(solution(answer));
	}

	public static int solution(String name) {
		int answer = 0;
		int cnt = 0;

		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != 'A') {
				cnt++;
			}
		}
		int idx = 0;
		while (true) {
			char c = name.charAt(idx);

			int tmp1 = c - 'A';
			int tmp2 = 'Z' - c + 1;
			answer += Math.min(tmp1, tmp2);
			if (c != 'A') {
				cnt--;
			}
			if (cnt == 0) {
				break;
			}

			int left = idx, right = idx;

			while (true) {
				answer++;
				left -= 1;
				if (left < 0) {
					left = name.length() - 1;
				}
				right += 1;
				if (right > name.length() - 1) {
					right = 0;
				}
				if (name.charAt(left) != 'A') {
					idx = left;
					break;
				}
				if (name.charAt(right) != 'A') {
					idx = right;
					break;
				}
			}

		}
		return answer;
	}

}
