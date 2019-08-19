package swea.d4;

import java.util.Scanner;

public class Solution_4796_의석이의우뚝선산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			int N = sc.nextInt();
			int[] m = new int[N];
			
			for (int i = 0; i < N; i++) {
				m[i] = sc.nextInt();
			}
			
			int ans = 0;
			
			for (int i = 0; i < N; i++) {
				int j = i + 1;
				
				if (j + 1 < m.length && m[i] < m[j]) { // 답일 수 있는 경우,
					int tmp = 1; // 해당 봉우리까지 작은 봉우리의 개수
					while (j + 1 < m.length && m[j] < m[j + 1]) { // 큰 봉우리가 나올때까지 오른쪽 밀기
						tmp++;
						j++;
					}
					while (j + 1 < m.length && m[j] > m[j + 1]) { // 높은 봉우리에서 작은 봉우리 나오는 동안 오른쪽 밀기
						ans += tmp;
						j++;
					}
					i = j - 1; // 한 묶음 넘어가기
				}
			}
			System.out.println("#" + tc + " " + ans);
		} // end of for TestCase
	} // end of main
} // end of class
