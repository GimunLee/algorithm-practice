package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기 {
	static int[][] map;
	static ArrayList<Core> coreList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			maxCoreCnt = Integer.MIN_VALUE;
			minCoreLen = Integer.MAX_VALUE;

			int N = Integer.parseInt(br.readLine().trim()); // N*N

			map = new int[N][N];
			coreList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1 && r != 0 && c != 0 && r != N - 1 && c != N - 1) {
						coreList.add(new Core(r, c));
					}
				}
			}

			dfs(0, 0, 0); // idx, coreCnt, len

			sb.append('#').append(tc).append(' ').append(minCoreLen).append('\n');
		} // end of for of testCase
		System.out.println(sb.toString());

	} // end of main

	static int maxCoreCnt = Integer.MIN_VALUE;
	static int minCoreLen = Integer.MAX_VALUE;

	private static void dfs(int idx, int coreCnt, int len) {
//		System.out.println("-=-====-=-=-=-=-=-=--=");
//		for (int i = 0; i < dc.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		if (idx == coreList.size()) {
			// 기존 연결 코어보다 현재 연결 코어가 더 많다면,
			if (coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minCoreLen = len;
			}
			// 기존 연결 코어와 현재 연결 코어 개수가 같다면,
			else if (coreCnt == maxCoreCnt) {
				minCoreLen = Math.min(minCoreLen, len); // 갱신
			}
			return;
		}

		// 4방향을 탐색
		Core core = coreList.get(idx); // 현재 탐색할 Core를 가저온다.

		for (int i = 0; i < dc.length; i++) {
			int ret = setLine(core, i);
			if (ret == -1) { // 실패하는 경우
				dfs(idx + 1, coreCnt, len);
			} else { // 성공하는 경우
				dfs(idx + 1, coreCnt + 1, len + ret);

				int nR = core.r;
				int nC = core.c;

				while (true) {
					nR += dr[i];
					nC += dc[i];

					if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map.length - 1) {
						break;
					}

					map[nR][nC] = 0;
				}
			}
		}

	} // end of dfs()

	private static int setLine(Core core, int dir) {
		int len = 0;
		int r = core.r;
		int c = core.c;

		while (true) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];

			if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map.length - 1) {
				break;
			}

			if (map[nR][nC] != 0) {
				return -1;
			}

			len++;
			r = nR;
			c = nC;
		}

		r = core.r;
		c = core.c;

		for (int i = 0; i < len; i++) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];
			map[nR][nC] = 1;
			r = nR;
			c = nC;
		}
		return len;
	}
} // end of class

class Core {
	int r;
	int c;

	Core(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
