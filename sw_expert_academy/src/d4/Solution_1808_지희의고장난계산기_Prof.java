import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1808_지희의고장난계산기_Prof {
	private static int min;
	private static boolean[] key;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {

			key = new boolean[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < key.length; i++) {
				key[i] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
			}
			int X = Integer.parseInt(br.readLine().trim()); // 1<= X <= 10^6
			min = Integer.MAX_VALUE; // 최소버튼을 누른 횟수
			go(X, 0, true, 0);
			System.out.println("#" + tc + " " + (min == Integer.MAX_VALUE ? -1 : min));
		}
	}

	/** 버튼 눌러서 만들수 잇는 모든 숫자 조합을 재귀 호출 */
	private static void go(int X, int push, boolean complete, int num) {
		if (X == 1) { // 종료조건
			// 최소값 없데이트
			if (min > push) {
				min = push;
			}
		} else if (complete) { // 반복조건
			// 숫자를 만드는 모든 경우
			for (int i = 0; i < key.length; i++) {
				if (key[i] && i != 0 && X >= i) { // 누를수 있는 버튼, 0으로 시작하면 안됨
					go(X, push + 1, false, i); // 연결할 숫자
				}
				if (key[i] && i != 0 && i != 1 && X % i == 0) { // 누를 수 있는 버튼, 0으로 시작하면 안됨,
					go(X / i, push + 2, true, 0); // 1자리 숫자 완성
				}
			}
		} else if (!complete) { // 기존 구해온 num에 누룰수 있는 숫자를 추가로 붙이기
			for (int i = 0; i < key.length; i++) {
				if (key[i] && X >= num * 10 + i) { // 연결 숫자
					go(X, push + 1, false, num * 10 + i); // 연결할 숫자
				}
				if (key[i] && X % (num * 10 + i) == 0) { // 숫자를 완성
					go(X / (num * 10 + i), push + 2, true, 0);
				}
			}
		}
	} // end of main
} // end of class
