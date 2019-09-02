package swea.mock0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기 {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N x N
			map = new int[N][N];
			ArrayList<Core> coreList = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1 && r != N && c != N && r != 0 && c != 0) { // Core인 경우
						coreList.add(new Core(r, c));
					}
				}
			} // end of for(input)

			accessCoreCntANS = Integer.MIN_VALUE;
			accessCoreLenANS = Integer.MAX_VALUE;
			chooseCore(coreList, 0, 0, 0);
			sb.append("#").append(tc).append(" ").append(accessCoreLenANS).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int accessCoreCntANS;
	private static int accessCoreLenANS;

	/** 전선을 설치할 Core를 골라서 Core를 설치해본다. */
	private static void chooseCore(ArrayList<Core> coreList, int idx, int accessCoreCnt, int accessCoreLen) {

		if (idx == coreList.size()) { // 다 탐색해봤으면
			if (accessCoreCntANS < accessCoreCnt) {
				accessCoreCntANS = accessCoreCnt; // 코어 개수
				accessCoreLenANS = accessCoreLen; // 전선 길이
			} else if (accessCoreCntANS == accessCoreCnt) {
				accessCoreLenANS = accessCoreLenANS > accessCoreLen ? accessCoreLen : accessCoreLenANS; // 전선 길이
			}
			return;
		}

		// 코어 연결할 수 있는지 확인, 연결할 수 있으면 연결하기
		Core core = coreList.get(idx);
		for (int dir = 0; dir < dr.length; dir++) {
			int result = setCable(core, dir);
			if (result != -1) {
				chooseCore(coreList, idx + 1, accessCoreCnt + 1, accessCoreLen + result);
				int r = core.r;
				int c = core.c;
				for (int i = 0; i < result; i++) {
					int nR = r + dr[dir];
					int nC = c + dc[dir];
					map[nR][nC] = 0;
					r = nR;
					c = nC;
				}
			}
		}
		chooseCore(coreList, idx + 1, accessCoreCnt, accessCoreLen);
	}

	// 전선 설치하기
	private static int setCable(Core core, int dir) {
		boolean canSetCable = false;
		int coreLen = 0;
		int r = core.r;
		int c = core.c;
		while (true) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];

			if (nR < 0 || nC < 0 || nR >= map.length || nC >= map.length) { // 설치 가능
				canSetCable = true;
				break;
			}

			if (map[nR][nC] != 0) {
				break;
			}
			r = nR;
			c = nC;
		}
		r = core.r;
		c = core.c;
		if (canSetCable) { // 해당 방향은 전선 설치 가능
			while (true) {
				int nR = r + dr[dir];
				int nC = c + dc[dir];
				if (nR < 0 || nC < 0 || nR >= map.length || nC >= map.length) { // 설치 가능
					break;
				}
				coreLen++;
				map[nR][nC] = 2;
				r = nR;
				c = nC;
			}
			return coreLen;
		} else {
			return -1;
		}
	}

	private static class Core {
		int r, c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
} // end of Class
