package boj.mock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Main_17136_색종이붙이기 {
	final static int[] paper = { 0, 1, 2, 3, 4, 5 }; // 색종이 크기를 저장해놓은 변수
	static int[] paper_cnt = { 0, 5, 5, 5, 5, 5 }; // 정해진 색종이 개수를 저장해놓은 변수
	private static ArrayList<Pair> list; // map에서 1의 위치(색종이를 붙일 곳)을 담기 위한 list 변수
	private static int[][] map; // input을 저장할 2차원 배열
	private static boolean[][] canWork; // 색종이를 붙여보는 변수로, true인 경우 색종이를 붙일 수 있습니다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		list = new ArrayList<>();
		canWork = new boolean[10][10];

		for (int r = 0; r < 10; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 10; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) { // input에 1인 곳을 list에 저장합니다.
					list.add(new Pair(r, c));
					canWork[r][c] = true; // 색종이를 붙일 수 있다고 표기합니다.
				}
			}
		} // end of for input

		min_cnt = Integer.MAX_VALUE; // 최소의 값으로 갱신하기 위해 정수형 중 가장 큰 값을 저장합니다.

		dfs(0, 0); // 색종이를 붙일 수 있는 위치(idx), 답을 갱신하기 위한 붙인 색종이 개수(cnt)

		// 답이 한번도 갱신 되지 않은 경우는 색종이를 붙일 수 없는 경우로 -1을 출력합니다.
		min_cnt = (min_cnt == Integer.MAX_VALUE) ? -1 : min_cnt;
		System.out.println(min_cnt);
	} // end of main

	/** 색종이를 붙일 위치(idx)와 색종이의 종류(type)을 받아, 색종이를 붙일 수 있는지 확인합니다. */
	private static boolean chk(int idx, int type) {
		Pair p = list.get(idx); // 해당 idx의 row와 column 값을 가져옵니다.
		boolean flag = false; // 색종이를 붙일 수 있는지, 없는지 판별할 수 있는 변수입니다.

		if (paper_cnt[type] == 0) { // 만약 5개의 색종이를 다 썼다면, 그 색종이는 쓰지 못합니다.
			return false;
		}

		// 해당 idx의 위치부터 색종이의 크기만큼 확인합니다.
		here: for (int i = p.r; i < p.r + paper[type]; i++) {
			for (int j = p.c; j < p.c + paper[type]; j++) {
				if (i < 0 || j < 0 || i >= 10 || j >= 10) { // 범위를 벗어난 경우,
					flag = true;
					break here; // 더 확인하지 않고 이중 for문을 한번에 탈출합니다.
				}

				if (!canWork[i][j]) { // 이미 다른 색종이가 붙어있는 경우,
					flag = true;
					break here; // 더 확인하지 않고 이중 for문을 한번에 탈출합니다.
				}
			}
		}
		// flag가 true라면 해당 idx에 type의 색종이를 붙이지 못하기 때문에 false를 return 해줍니다.
		return flag ? false : true;
	} // end of chk()

	static int min_cnt; // 하나의 조합이 완성됐을 때 갱신해줄 색종이의 최소 개수

	/** 색종이를 붙일 위치(idx)와 하나의 조합이 완성됐을 경우, min_cnt를 갱신해주기 위해 cnt를 가지고 다닙니다. */
	private static void dfs(int idx, int cnt) {
		if (min_cnt < cnt) { // Backtracking : 이미 갖고 있는 최소의 개수를 넘었기 때문에 더이상 진행하지 않습니다.
			return;
		}
		if (idx == list.size()) { // 종료 조건 : map의 모든 1을 탐색 완료
			min_cnt = (min_cnt > cnt) ? cnt : min_cnt; // 최소 개수 갱신
			return;
		}

		Pair p = list.get(idx);

		// 해당 idx에 이미 다른 색종이가 붙어있는 경우, 다음 색종이를 붙일 곳을 탐색하도록 합니다.
		if (!canWork[p.r][p.c]) {
			dfs(idx + 1, cnt);
		}

		for (int type = 5; type >= 0; type--) { // 큰 색종이부터 진행하면 Backtracking 조건을 통해, 시간을 줄일 수 있습니다.
			boolean flag = chk(idx, type); // 색종이를 붙일 수 있는지 확인합니다.
			if (flag) { // 색종이를 붙일 수 있는 경우
				for (int r = p.r; r < p.r + paper[type]; r++) { // 색종이를 붙여줍니다.
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = false;
					}
				}
				// 색종이의 종류마다 5개 밖에 없으므로, 색종이의 개수를 줄여줍니다.
				paper_cnt[type]--;

				dfs(idx + 1, cnt + 1); // 색종이를 붙였으므로, 다음을 탐색하기위해 idx와 cnt에 1을 더하고 재귀호출합니다.

				// 원상복귀 : 다음 조합에서 더욱 좋은 조합이 있을 수 있으므로, 색종이를 다시 떼줍니다.
				paper_cnt[type]++;
				for (int r = p.r; r < p.r + paper[type]; r++) {
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = true;
					}
				}
			}
		}
	} // end of dfs()

	private static class Pair {
		int r; // map의 row
		int c; // map의 column

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
