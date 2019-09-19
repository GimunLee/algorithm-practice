package programmers;

class Solution_¡∂¿ÃΩ∫∆Ω {

	public static void main(String[] args) {
		String answer = "BBAAAAAAABB";
		// A:65, Z: 90
		System.out.println(solution(answer));
	}

	public static int solution(String name) {
		// a b c d e f g h i j k l m n o p q r s t u v w x y x
		int answer = 0;
		int mid = 0;
		if (name.length() % 2 != 0) {
			mid = name.length() / 2;
		} else {
			mid = name.length() / 2 - 1;
		}

		int left = 0, right = 0;
		int cnt = 0;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != 'A') {
				cnt++;
				if (i <= mid) {
					left++;
				} else {
					right++;
				}
			}
		}
		if (cnt == 0) {
			return 0;
		}
		if (name.charAt(0) != 'A') {
			int tmp = name.charAt(0) - 'A';
			int tmp2 = 'Z' - name.charAt(0) + 1;
			answer += Math.min(tmp, tmp2);
			cnt--;
		}
		if (left > right) {
			for (int i = 1; i < name.length(); i++) {
				if (cnt <= 0) {
					break;
				}
				answer++;
				char c = name.charAt(i);
				if (c != 'A') {
					int tmp = c - 'A';
					int tmp2 = 'Z' - c + 1;
					answer += Math.min(tmp, tmp2);
					cnt--;
				}
			}
		} else {
			for (int i = name.length() - 1; i > 0; i--) {
				if (cnt <= 0) {
					break;
				}
				answer++;
				char c = name.charAt(i);
				if (c != 'A') {
					int tmp = c - 'A';
					int tmp2 = 'Z' - c + 1;
					answer += Math.min(tmp, tmp2);
					cnt--;
				}
			}
		}
		return answer;
	}

}
