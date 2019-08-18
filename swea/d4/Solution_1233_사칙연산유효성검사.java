package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
 */
public class Solution_1233_사칙연산유효성검사 {
	static int Answer = 1;
	static int chk_index = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			Answer = 1;
			int N = Integer.parseInt(br.readLine().trim()); // 노드의 개수
			String[] tree = new String[201];
			StringTokenizer st;

			for (int i = 1; i <= N / 2; i++) { // 자식값
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken(); // 하나 버리기
//				숫자든 문자든 정점에 넣기
				tree[i] = st.nextToken(); // 연산자 or 숫자
				st.nextToken(); // 버리기
				// 짝수일 때 마지막까지오면 자식이 한 개이므로 안 버린다.
				if (i == N / 2 && N % 2 == 0)
					break;
				st.nextToken(); // 버리기
			}

			for (int i = N / 2 + 1; i <= N; i++) { // 정점에 해당하는 값
				st = new StringTokenizer(br.readLine(), " ");
				tree[i] = st.nextToken(); // 값을 넣기 i 정점에 들어온 값
			}

//			루트가 연산자가 아닐 때
			if (!checkOpe(tree[1])) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}

			for (int i = 1; i <= N / 2; i++) { // 위부터 탐색
				// 자기가 연산자일 때,
				if (checkOpe(tree[i])) {

				} else { // 자기가 숫자일 때,
					if (tree[i * 2] != null) {
						if (!checkOpe(tree[i * 2])) { // 왼쪽 자식이 숫자이면
							Answer = 0;
							break;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + Answer);
		}
		br.close();
	}

	// 연산자인지 판별
	public static boolean checkOpe(String input) {
		if (input.equals("*") || input.equals("-") || input.equals("/") || input.equals("+")) {
			return true;
		} else {
			return false;
		}
	}
}
