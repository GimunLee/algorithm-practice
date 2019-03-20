package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3074_입국심사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 10^5, 심사대 개수
			int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 10^9, 통과할 인원수
			int[] t = new int[N];
			for (int i = 0; i < N; i++) {
				t[i] = Integer.parseInt(br.readLine().trim()); // 1 <= tk <= 10^9
			}
			long start = 0;
			long end = 1000000000L * M;
			long min = Long.MAX_VALUE; // time 최소값
			while(start <= end) {
				long mid = (start + end) / 2;
				long cnt = 0; // 정해진 mid 시간안에 통과할 수 있는 인원수
				for (int i = 0; i < t.length; i++) {
					cnt += mid / t[i];
				}
				if (cnt >= M && min > mid) {
					min = mid;
				}
			
				if (cnt < M) {
					start = mid + 1;
				} else if (M <= cnt) {
					end = mid - 1;
				}
			} // end of while
			System.out.println("#"+testCase+" "+min);
		} // end of for testCase
	}
}



