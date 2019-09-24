package programmers;

public class Solution_숫자야구 {
	public static void main(String[] args) {
		int[][] input = { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } };
		System.out.println(solution(input));

	} // end of main

	public static int solution(int[][] baseball) {
		int answer = 0;

		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				if (i == j) {
					continue;
				}
				for (int k = 1; k <= 9; k++) {
					if (i == j || j == k || i == k) {
						continue;
					}
					// solve
					String guessNum = (i * 100 + j * 10 + k) + "";
					int tmpCnt = 0;
					for (int b = 0; b < baseball.length; b++) {
						String tmp = baseball[b][0] + "";
						int strik = 0;
						int ball = 0;
						for (int l = 0; l < 3; l++) {
							for (int e = 0; e < 3; e++) {
								if (guessNum.charAt(l) == tmp.charAt(e)) {
									if (l == e) {
										strik++;
									} else {
										ball++;
									}
								}
							}
						}
						if (strik == baseball[b][1] && ball == baseball[b][2]) {
							tmpCnt++;
						}
					}
					if (tmpCnt == baseball.length) {
						answer++;
					}
				}
			}
		}
		return answer;
	}
} // end of class
