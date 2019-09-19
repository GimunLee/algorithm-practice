package programmers;

class Solution_조이스틱2 {

	public static void main(String[] args) {
		String answer = "BBAAAAABB";
		char c = 'A';
		// A:65, Z: 90
		System.out.println(solution(answer));
	}

	public static int solution(String name) {
		int answer = 0;
		int idx = 0;
		int cnt = 0;

		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != 'A') {
				cnt++;
			}
		}
		while (true) {
			if (cnt == 0) {
				break;
			}
			char c = name.charAt(idx);
			if (c == 'A') { // 더 가까운 쪽으로 가야함
				
				
				
			}
			int tmp1 = c - 'A';
			int tmp2 = 'Z' - c + 1;
			answer += Math.min(tmp1, tmp2);
			
			
			
			

		}
		return answer;
	}

}
