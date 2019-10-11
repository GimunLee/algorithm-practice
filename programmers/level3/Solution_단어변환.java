package programmers.level3;

public class Solution_단어변환 {
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		int answer = solution(begin, target, words);
		System.out.println(answer);

	}

	public static int solution(String begin, String target, String[] words) {
		boolean isExist = false;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(target)) {
				isExist = true;
			}
		}
		if (!isExist) {
			return 0;
		} // 존재하지 않는 경우
		boolean[] isChoose = new boolean[words.length];
		answer = 51;
		dfs(begin, target, words, isChoose, 0);
		return answer;
	}

	static int answer;

	private static void dfs(String begin, String target, String[] words, boolean[] isChoose, int cnt) {
		if (begin.equals(target)) {
			answer = answer > cnt ? cnt : answer;
			return;
		}

		for (int i = 0; i < words.length; i++) {
			if (!isChoose[i]) {
				int tmp = 0;
				for (int j = 0; j < words[i].length(); j++) { // 한글자만 다른지 체크
					if (words[i].charAt(j) != begin.charAt(j)) {
						tmp++;
					}
				}

				if (tmp == 1) {
					isChoose[i] = true;
					dfs(words[i], target, words, isChoose, cnt + 1);
					isChoose[i] = false;
				}
			}
		}
	}

}
