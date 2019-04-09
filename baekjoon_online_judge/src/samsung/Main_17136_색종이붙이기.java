package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17136_색종이붙이기 {
	final static int[] paper = { 0, 1, 2, 3, 4, 5 }; // 색종이 크기
	static int[] paper_cnt = { 0, 5, 5, 5, 5, 5 }; // 색종이 개수
	private static ArrayList<Pair> list;
	private static int[][] map;
	private static boolean[][] canWork;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		list = new ArrayList<>();
		canWork = new boolean[10][10];

		for (int r = 0; r < 10; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 10; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					list.add(new Pair(r, c));
					canWork[r][c] = true;
				}
			}
		} // end of for input

		min_cnt = Integer.MAX_VALUE;

		dfs(0, 0); // 색종이를 붙일 수 있는 idx, 최소 개수 cnt

		min_cnt = (min_cnt == Integer.MAX_VALUE) ? -1 : min_cnt;
		System.out.println(min_cnt);
	}

	static int min_cnt;

	private static void dfs(int idx, int cnt) {
		if (min_cnt < cnt) { // 답이 될 수 없음
			return;
		}

		if (idx == list.size()) { // 종료 조건
			min_cnt = (min_cnt > cnt) ? cnt : min_cnt; // 갱신
			return;
		}

		Pair p = list.get(idx);

		if (!canWork[p.r][p.c]) {
			dfs(idx + 1, cnt);
		}

		for (int type = 5; type >= 0; type--) { // 큰 색종이부터
			boolean flag = chk(idx, type); // 붙일수 있는지 확인

			if (flag) { // 성공하는 경우

				for (int r = p.r; r < p.r + paper[type]; r++) {
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = false;
					}
				}
				paper_cnt[type]--;
				dfs(idx + 1, cnt + 1);
				paper_cnt[type]++;
				for (int r = p.r; r < p.r + paper[type]; r++) {
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = true;
					}
				}
			}
		}
	} // end of dfs()

	private static boolean chk(int idx, int type) {
		Pair p = list.get(idx);
		boolean flag = false;

		if (paper_cnt[type] == 0) {
			return false;
		}

		here: for (int i = p.r; i < p.r + paper[type]; i++) {
			for (int j = p.c; j < p.c + paper[type]; j++) {
				if (i < 0 || j < 0 || i >= 10 || j >= 10) {
					flag = true;
					break here;
				}

				if (!canWork[i][j]) {
					flag = true;
					break here;
				}
			}
		}
		return flag ? false : true;
	} // end of chk()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
