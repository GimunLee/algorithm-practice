package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4613_러시아국기같은깃발_Prof {
	private static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 행, 3 <= N <= 50
			int M = Integer.parseInt(st.nextToken()); // 열, 3 <= M <= 50
			arr = new char[N][3]; // W:0 B:1 R:2
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < s.length(); j++) {
					if (s.charAt(j) == 'W')
						arr[i][0]++;
					else if (s.charAt(j) == 'B')
						arr[i][1]++;
					else if (s.charAt(j) == 'R')
						arr[i][2]++;
				}
			}
			int minCnt = Integer.MAX_VALUE;
			for (int i = 1; i <= N - 2; i++) {
				int w = cntChar(0, i, 'W');
				for (int j = 1; i + j <= N - 1; j++) {
					int b = cntChar(i, i + j, 'B');
					int r = cntChar(i + j, N, 'R');
					if (minCnt > w + b + r)
						minCnt = w + b + r;
				}
			}
			System.out.println("#" + testCase + " " + minCnt);
		} // end of for testCase
	}// end of main

	public static int cntChar(int s, int e, char c) {
		int cnt = 0;
		for (int i = s; i < e; i++) { // W:0 B:1 R:2
			if (c == 'W')
				cnt += arr[i][1] + arr[i][2];
			else if (c == 'B')
				cnt += arr[i][0] + arr[i][2];
			else if (c == 'R')
				cnt += arr[i][0] + arr[i][1];
		}
		return cnt;
	}
}// end of class