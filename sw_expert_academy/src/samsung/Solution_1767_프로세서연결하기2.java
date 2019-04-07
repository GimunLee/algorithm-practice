package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기2 {
	private static int[][] map;
	private static ArrayList<Core> list_core;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 12
			map = new int[N][N];
			list_core = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); // 현재 회로판 정보 저장 변수
					if (map[r][c] == 1) { // 코어일 때,
						if (r != 0 && c != 0 && r != N - 1 && c != N - 1) { // 외곽은 탐색하지 않는다.
							list_core.add(new Core(r, c));
						}
					}
				}
			} // end of input

			for (int i = 0; i < list_core.size(); i++) {
				System.out.println(list_core.get(i).r + ", " + list_core.get(i).c);
			}

			ans_core = 0;
			ans_len = 0;
			// 1. 코어마다 4방향으로 보내본다.
			dfs(0, 0, 0); // 코어 인덱스, 연결된 코어 개수, 전선의 길이

		} // end of for TestCase

	} // end of main

	private static int ans_core;
	private static int ans_len;

	private static void dfs(int idx, int cnt_core, int len) {
		if (ans_len < len) { // 답이 될 수 없음
			return;
		}

		if (idx == list_core.size()) { // 모든 코어의 탐색을 마쳤을때,
			if (cnt_core > ans_core) { // 코어가 가장 많이 연결되야함
				ans_len = ans_len > len ? len : ans_len; // 전선 길이 갱신
			}
		}

		int r = list_core.get(idx).r;
		int c = list_core.get(idx).c;

		for (int i = 0; i < dr.length; i++) { // 상, 하, 좌, 우
			int nR = r + dr[i];
			int nC = c + dc[i];

			// 설치 할 수 있는지 확인, 설치하고 dfs 보내고, 다시 원상복귀

			
			// 설치 못하면 그대로 보내보기
			

		}
	} // end of dfs()

	private static class Core {
		int r;
		int c;

		Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

} // end of class
