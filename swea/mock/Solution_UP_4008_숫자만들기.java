package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.09.02.(월) Upload
 * */
public class Solution_UP_4008_숫자만들기 {
	private static int[] op = new int[4]; // 연산자 (0 : '+' , 1 : '-', 2 : '*', 3 : '/')
	private static int[] numArr; // 숫자를 저장하는 배열

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // 한번에 출력하기 위해 저장하는 변수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 변수
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N : 숫자의 갯수
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(st.nextToken()); // 연산자 갯수 저장
			}

			numArr = new int[N]; // 숫자를 저장할 변수 생성

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numArr[i] = Integer.parseInt(st.nextToken()); // 각 숫자 저장
			}
			// -- end of Input

			maxValue = Integer.MIN_VALUE; // 나올 수 있는 경우 중 최댓값을 저장할 변수
			minValue = Integer.MAX_VALUE; // 나올 수 있는 경우 중 최솟값을 저장할 변수
			// 사용할 연산자를 뽑고 바로 계산하며 진행
			solve(1, 0, numArr[0]); // 2개씩 계산하며 진행됨 (오른쪽 수의 인덱스, 뽑은 연산자의 수, 왼쪽과 오른쪽 계산한 결과 값)
			sb.append("#").append(tc).append(" ").append(maxValue - minValue).append("\n"); // 나온 정답 저장
		} // end of for(TestCase)
		System.out.println(sb.toString()); // 정답 출력
	} // end of main

	private static int maxValue; // 최댓값
	private static int minValue; // 최솟값

	/**
	 * numArrIdx : 오른쪽 숫자의 인덱스, opLen : 뽑은 연산자의 수, value : 현재까지 누적된 값과 오른쪽 수를 계산하여
	 * 저장하는 변수 (누적)
	 */
	private static void solve(int numArrIdx, int opLen, int value) {
		if (opLen == numArr.length - 1) { // 연산자를 끝까지 봤다면,
			// value를 기반으로 각 값 갱신
			if (value > maxValue) {
				maxValue = value;
			}
			if (value < minValue) {
				minValue = value;
			}
			return;
		}

		for (int i = 0; i < op.length; i++) {
			if (op[i] > 0) { // 뽑고자 하는 연산자의 인덱스가 아직 쓸 수 있다면
				op[i]--; // 연산자 사용
				int rightNum = numArr[numArrIdx]; // 오른쪽 수
				int tmpValue = value; // 재귀 함수가 return 되었을 때, 그 연산을 하기 전으로 돌려주기 위한 임시 변수
				value = calculate(i, value, rightNum); // 각 값 계산
				solve(numArrIdx + 1, opLen + 1, value); // 다음을 재귀 호출
				value = tmpValue; // 값 원상복귀 (모든 경우를 해보기 위함)
				op[i]++; // 연산자의 개수를 다시 올려줌
			}
		}
	} // end of func(solve)

	/** 연산자 인덱스로 연산하는 함수 */
	private static int calculate(int i, int value, int rightNum) {
		switch (i) {
		case 0: // '+'
			value += rightNum;
			break;
		case 1: // '-'
			value -= rightNum;
			break;
		case 2: // '*'
			value *= rightNum;
			break;
		case 3: // '/'
			value /= rightNum;
			break;
		}
		return value;
	}
} // end of class
