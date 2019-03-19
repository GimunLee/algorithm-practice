package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3074_입국심사 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 심사대개수 1<=N<=10^5
			int M = Integer.parseInt(st.nextToken()); // 통과할인원수 1<=M<=10^9
			int[] t = new int[N];
			for (int i = 0; i < t.length; i++) {
				t[i] = Integer.parseInt(br.readLine().trim());
			}
			long start = 0;
			long end = 1000000000L * M;
			long min = Long.MAX_VALUE; // time 최소값
			while (start <= end) {
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
			}
			System.out.println(min);
		}
	}// end of main
}// end of class