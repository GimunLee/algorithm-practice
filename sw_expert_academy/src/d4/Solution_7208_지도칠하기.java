import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7208_지도칠하기 {
	static int N;
	static int[] color;
	static int[][] m;
	private static int min;
	private static int[] nc;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // 3 <= N <= 8
			color = new int[N]; // 나라별 초기 색상

			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < color.length; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}

			m = new int[N][N]; // 인접행렬
			for (int i = 0; i < m.length; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < m.length; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			for (int i = 0; i < m.length; i++) {
//				System.out.println(Arrays.toString(m[i]));
//			}
			min = Integer.MAX_VALUE; // 최소 변경량
			nc = new int[N];
			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(min).append('\n');
		} // end of for of TestCase
		System.out.println(sb.toString());
	} // end of main

	/** 현재 step의 나라 색상을 채우고, 재귀호출, step:단계, cnt: 현 단계에서 색깔을 바꿀 나라의수 */
	private static void dfs(int step, int cnt) {
		if (step == N) {
			// 원래 지정된 색깔과 내가 칠한 색깔이 다른 나라가 몇개인지 세기 => min에 업데이트
			if (min > cnt) {
				min = cnt;
			}
		} else { // 반복조건
			// 현재 step 나라를 모든 색깔로 칠해보고 재귀호출
			go: for (int i = 1; i <= 4; i++) {
				nc[step] = i;
				// 인접한 국가와 색상이 다른 경우만 진행
				for (int j = 0; j < step; j++) { // 현재 국가 이전에 칠한 색상과 비교
					if (m[step][j] == 1 && nc[j] == i) {
						continue go;
					}
				}
				if (color[step] != nc[step]) {
					if (min > cnt + 1) { // 가지치기
						dfs(step + 1, cnt + 1);
					}
				} else {
					dfs(step + 1, cnt);
				}
			}
		}
	}
} // end of class
