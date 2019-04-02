package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 지렁이 개수 짝수개, 둘씩 짝을 지어줌 짝을 지어주는 모든 경우의 수를 생각해보고, 각각의 짝이 연결되는 벡터를 구해서, 벡터들의 전체
 * 합을 구한뒤, 백뒤 전체합이 크기를 구한다. 이렇게 만든 벡터 전체합의 크기의 최소값을 찾는 문제
 * 
 * Tip : 벡터상의 출발위치에 있는 지렁이 좌표의 합 - 백터상의 도착위치에 있는 지렁이 좌표의 합 = 벡터 전체의 합 그러므로 출발위치
 * 지렁이 끼리는 변경되더라도 백터 전체의 합은 동일하다. 도착위치 지정이 끼리는 변경되더라도 벡터 전체의 합은 동일하다. 결국, 출발 위치의
 * 그룹, 도작 위치의 그룹으로 분할하는 문제
 * 
 * 1번 방법 : 순수 조합문제로 조합이 완성되었을 때, 좌표들의 합을 구해서, 벡터크기의 최소값을 업데이트한다. 196ms 2번 방법 :
 * 조합을 재귀호출시 지렁이의 선택여부에 따라서 좌표들의 합을 매개변수로 전달한다. (효율적) 163ms
 */

public class Solution_1494_사랑의카운슬러_Prof2 {
	static int sumX = 0, sumY = 0; // 전체 X, Y의 합
	static int[][] m; // 지렁이의 위치
	static long min; // 전체 벡터합의 크기 중 최소값, 좌표의 제곱이라서 int형을 넘어갈 수 있다.

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // 혹시 한 줄안에 공백이 들어있을 경우 에러 발생, 공백 제거

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 지렁이의 개수, 2 <= N <= 20, N은 짝수

			m = new int[N][2]; // 0 : x, 1 : y
			sumX = 0;
			sumY = 0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken()); // -100,000 <= x, y <= 100,000
				int y = Integer.parseInt(st.nextToken()); // -100,000 <= x, y <= 100,000
				m[i][0] = x;
				m[i][1] = y;

				sumX += x;
				sumY += y;
			}

			min = Long.MAX_VALUE; // 전체 벡터합의 크기
			combi(N, N / 2, 0, 0); // N개 중에 N/2개 뽑는 조합

			System.out.println("#" + tc + " " + min);
		} // end of for testCase

	} // end of main

	private static void combi(int n, int r, int sX, int sY) {
		if (n < r) {
			return;
		} else if (r == 0) { // 하나의 조합이 완성됨
			int sumUnselectX = sumX - sX;
			int sumUnselectY = sumY - sY;

			long vX = sX - sumUnselectX;
			long vY = sY - sumUnselectY;
			long v = vX * vX + vY * vY; // 벡터 전체 합의 크기
			if (min > v) {
				min = v;
			}
		} else {
			combi(n - 1, r - 1, sX + m[n - 1][0], sY + m[n - 1][1]); // 선택한 경우
			combi(n - 1, r, sX, sY); // 선택하지 않은 경우
		}
	}
} // end of class
