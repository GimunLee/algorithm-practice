package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Solution_1767_프로세서연결하기 {
	private static int[][] map; // 입력을 저장할 2차원 정수 배열
	private static ArrayList<Core> coreList; // 코어를 저장할 ArrayList 변수
	private static int[] dr = { -1, 1, 0, 0 }; // row : 상 하 좌 우
	private static int[] dc = { 0, 0, -1, 1 }; // column : 상 하 좌 우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		StringBuilder sb = new StringBuilder(); // 출력 시, 시간을 줄이기 위한 StringBuilder 변수

		for (int tc = 1; tc <= TC; tc++) {
			maxCoreCnt = Integer.MIN_VALUE; // 최대로 연결되는 코어의 개수 변수
			minCoreLen = Integer.MAX_VALUE; // 최소로 연결되는 전선의 길이 변수

			int N = Integer.parseInt(br.readLine().trim()); // map의 크기
			map = new int[N][N];
			coreList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					// 외곽은 이미 연결되어 있기 때문에, 탐색에서 제외합니다.
					if (map[r][c] == 1 && r != 0 && c != 0 && r != N - 1 && c != N - 1) {
						coreList.add(new Core(r, c)); // 탐색할 코어를 저장합니다.
					}
				}
			} // end of for input

			dfs(0, 0, 0); // (코어의 인덱스:idx, 연결된 코어의 개수:coreCnt, 전선의 길이:len)

			sb.append('#').append(tc).append(' ').append(minCoreLen).append('\n'); // 한번에 출력하기 위한 처리입니다.
		} // end of for of testCase
		System.out.println(sb.toString()); // 정답 출력

	} // end of main

	static int maxCoreCnt = Integer.MIN_VALUE; // 최대로 연결되는 코어의 개수 변수
	static int minCoreLen = Integer.MAX_VALUE; // 최소로 연결되는 전선의 길이 변수

	/**
	 * core와 dir을 받아 전선을 설치해봅니다. 전선 설치에 실패하는 경우, -1을 반환합니다.
	 * 
	 * @param core : 현재 탐색하는 코어
	 * @param dir : 현재 방향
	 * */
	private static int setLine(Core core, int dir) {
		int len = 0; // 전선의 길이
		int r = core.r; // 현재 코어의 위치 r
		int c = core.c; // 현재 코어의 위치 c

		while (true) {
			int nR = r + dr[dir]; // 전선을 설치할 위치 r
			int nC = c + dc[dir]; // 전선을 설치할 위치 c

			// 범위를 벗어나면 반복문을 중단합니다.
			if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map.length - 1) {
				break;
			}

			// 이미 설치된 전선이 있는 경우, 해당 코어는 해당 방향으로 설치하지 못하므로 -1을 반환해줍니다.
			if (map[nR][nC] != 0) {
				return -1;
			}

			len++; // 조건에 부합하므로, 전선의 길이를 올립니다.
			r = nR; // 다음 전선을 설치할 다음 위치로 이동합니다.
			c = nC; // 다음 전선을 설치할 다음 위치로 이동합니다.
		}

		// 전선을 설치할 수 있는 경우이므로, 현재 코어의 위치를 다시 갱신해줍니다.
		r = core.r; 
		c = core.c;

		// 전선을 실제로 설치합니다.
		for (int i = 0; i < len; i++) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];
			map[nR][nC] = 1;
			r = nR;
			c = nC;
		}
		return len; // 전선의 최소 길이를 갱신해줘야하므로, 전선의 길이를 반환해줍니다.
	} // end of setLine()
	
	/**
	 * 하나의 코어를 상하좌우로 연결해보고, idx를 올려 다음 코어도 연결해봅니다.
	 * 하나의 조합이 완성되는 경우, coreCnt와 len을 이용해 정답을 갱신해줍니다.
	 * 
	 * @param idx     : 코어의 인덱스
	 * @param coreCnt : 코어의 연결 개수
	 * @param len     : 코어를 연결하는 전선의 길이
	 */
	private static void dfs(int idx, int coreCnt, int len) {

		if (idx == coreList.size()) { // 종료 조건 : 모든 코어를 탐색한 경우,
			if (coreCnt > maxCoreCnt) { // 기존 연결 코어보다 현재 연결 코어가 더 많은 경우, 정답을 갱신해줍니다.  
				maxCoreCnt = coreCnt; 
				minCoreLen = len;
			}
			else if (coreCnt == maxCoreCnt) { // 기존 연결 코어의 개수와 현재 연결 코어 개수가 같다면,
				minCoreLen = Math.min(minCoreLen, len); // 전선의 길이만 갱신해줍니다.
			}
			return;
		}

		Core core = coreList.get(idx); // 현재 탐색할 Core를 가져옵니다.
		
		// 4방향(상하좌우)를 탐색합니다.
		for (int i = 0; i < dr.length; i++) {
			int ret = setLine(core, i); // 해당 코어가 전선을 설치할 수 있는지 확인합니다.

			if (ret == -1) { // 전선 설치에 실패하는 경우,
				dfs(idx + 1, coreCnt, len); // 다음 코어를 탐색합니다.

			} else { // 전선 설치에 성공하는 경우,
				// 다음 코어를 탐색하고, 코어 설치에 성공했으므로 코어의 개수를 올려주고, 전선의 길이를 갱신해줍니다.
				dfs(idx + 1, coreCnt + 1, len + ret);

				// 원상 복귀 : 해당 코어의 설치가 성공했어도, 그 경우가 최선이 아닐 수 있으므로 다시 복귀시켜줍니다.
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
	private static class Core {
		int r;
		int c;

		Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Core
} // end of class

