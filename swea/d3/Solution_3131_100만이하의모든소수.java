package d3;

import java.util.Arrays;

/**
 * 에라토스테네스의 체 3131. 100만 이하의 모든 소수
 */
public class Solution_3131_100만이하의모든소수 {
	public static void main(String[] args) {
// 에라토스테네스의 체
		StringBuilder sb = new StringBuilder(); // 단일 쓰레드에서만 쓸 수 있고, 가장 빠르다.
		boolean[] isHapSungSu = new boolean[1000001];
		for (int i = 2; i < isHapSungSu.length; i++) {
			if (!isHapSungSu[i]) { // 처음 들어왔으면 false
				sb.append(i).append(' '); // append(i+ " ") 문자열의 연산을 줄여라.
				// i의 배수들은 소수 아니므로 true로 설정
				for (int j = i + i; j < isHapSungSu.length; j += i) { // 곱셈을 줄여라.
					isHapSungSu[j] = true;
				}
			}

		}
		System.out.print(sb);

// 에라토스테네스의 체 Time Out
//		int N = 1000;
//		boolean[] result = new boolean[1000000];
//		int[] answer = new int[1000000];
//		for (int i = 2; i < N; i++) {
//			boolean chk = false;
//			for (int j = 2; j < i; j++) {
//				if(i % j == 0) {
//					chk = true;
//					break;
//				}
//			}
//			if(!chk) {
//				for (int j = 2; j < N; j++) {
//					if((i * j) < N) {
//						result[i * j] = true;	
//					}
//				}
//			}
//		}
//		int index = 0;
//		for (int i = 2; i < 1000000; i++) {
//			if(!result[i]) {
//				answer[index++] = i;
//			}
//		}
//		System.out.println(Arrays.toString(answer));

// Time Out		
//		int N = 1000;
//		int[] Answer = new int[N];
//		int index = 0;
//		// 2 3 5 7 11 13 17 19 23
//		for (int i = 2; i < N; i++) {
//			boolean chk = false;
//			for (int j = 2; j < i; j++) {
//				if(i % j == 0) {
//					chk = true;
//					break;
//				}
//			}
//			if(!chk) {
//				Answer[index++] = i;	
//			}
//		}
//		for (int i = 0; i < index; i++) {
//			System.out.print(Answer[i] + " ");	
//		}
	}
}
