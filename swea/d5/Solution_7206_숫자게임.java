package swea.d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 1 2 3 4 5 쪼갤지, 안 쪼갤지 (쪼갤 포인트 4개를 잡는다.)
 * 
 * 바이너리 카운팅으로 숫자를 쪼갤 포인트의 경우의 수를 나눠본다.
 *  매번 재귀호출하면 중복이 많이 발생한다. 700ms
 *  저장해서 사용(메모이제이션)              10ms
 */
public class Solution_7206_숫자게임 {
	public static HashMap<Integer, Integer> hm;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		long time = System.currentTimeMillis(); // 시간 재기
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			hm = new HashMap<>();
			System.out.println("#" + tc + " " + f(N));
		}

		System.out.println((System.currentTimeMillis() - time) + "ms"); // 시간 재기

	} // end of main

	/** 턴이 왔을 경우, 숫자를 쪼개는 모든 경우를 구해서, 그 중 최대값 찾아서 리턴 */
	private static int f(int N) {
		if (N < 10) { // 쪼갤 수 없는 경우
			return 0;
		}
//		(int)Math.log10(N); 몇자리인지 구하기
		String s = "" + N;
		int len = s.length() - 1; // 최대 쪼갤 수 있는 지점의 개수
		int maxCnt = 0;
		for (int i = 1; i < 1 << len; i++) { // 쪼갤 수 있는 모든 경우를 나누자, 바이너리 카운팅을 이용
//			System.out.printf("%4s : ",Integer.toBinaryString(i));
			int mul = 1; // 곱셈값을 저장할 변수, 곱셈연산자에 대한 항등원 1로 초기화
			int num = s.charAt(0) - '0'; // 0번째 문자를 숫자로 변경
			for (int j = 0; j < len; j++) { // 비트마스킹, 해당 비트가 1이면 쪼갬, 0이면 이어붙임
				if ((i & 1 << j) == 0) { // 안쪼갬
					num = num * 10 + s.charAt(j + 1) - '0';
				} else { // 쪼갬
					mul *= num;
//					System.out.print(num + ",");
					num = s.charAt(j + 1) - '0'; // 새로운 문자를 저장
				}
			}
			mul *= num;
//			System.out.println(num + " : " + mul);

//			int cnt = f(mul); // 많은 중복 발생
			int cnt;
			
			if(hm.containsKey(mul)) { // 이미 호출한 기록이 있으면, 재활용
				cnt = hm.get(mul);
			} else {
				cnt = f(mul);
				hm.put(mul, cnt);
			}

			if (maxCnt < cnt) {
				maxCnt = cnt;
			}
		}
		return maxCnt + 1;
	}
} // end of class
