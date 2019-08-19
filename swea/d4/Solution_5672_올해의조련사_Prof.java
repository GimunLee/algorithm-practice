package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 교수님꺼 그대로 했지만, 틀림!
 */

public class Solution_5672_올해의조련사_Prof {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 2000
			char[] c = new char[N]; // 앵무새 이름 저장할 배열
			for (int i = 0; i < c.length; i++) {
				c[i] = br.readLine().charAt(0); // 대문자 한글자만
			}
			char[] result = new char[N];

			int s = -1; // 앞쪽에서 글자를 취할 수 있도록 index
			int e = N; // 뒤쪽에서 가리키는 index

			for (int i = 0; i < result.length; i++) { // 한 단계에서 글자 하나씩 취하기
				if (c[s + 1] < c[e - 1]) {
					result[i] = c[++s];
				} else if (c[s + 1] > c[e - 1]) {
					result[i] = c[--e];
				} else { // 두 글자가 서로 같다면, 다음 글자로 비교해서 결정
					int j;
					for (j = 1; s + j < e - j && c[s + j] == c[e - j]; j++) {
						if (c[s + j] < c[e - j]) {
							result[i] = c[++s];
						} else if (c[s + j] > c[e - j]) {
							result[i] = c[--e];
						} else { // 같은 값이니까 아무거나 넣음
							result[i] = c[++s];
						}
					}
				}
			}
			System.out.println("#" + tc + " " + new String(result));
		} // end of for testCase
	} // end of main
} // end of class
