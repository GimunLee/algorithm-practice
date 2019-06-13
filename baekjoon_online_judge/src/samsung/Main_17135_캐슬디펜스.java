package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
	private static int N;
	private static int M;
	private static int D;
	private static int[][] map;
	private static ArrayList<Enermy> list;
	private static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한

		map = new int[N][M];
		list = new ArrayList<>();
		cnt = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					list.add(new Enermy(r, c));
					cnt++;
				}
			}
		}

		setDiffenser(0, 0);
		System.out.println(ans);

	} // end of main

	private static int[] set = new int[3];

	private static void setDiffenser(int idx, int len) {
		if (len == 3) {
			playGame(set);
			return;
		}

		for (int i = idx; i < M; i++) {
			set[len] = i;
			setDiffenser(i + 1, len + 1);
		}
	} // end of setDiffenser()

	private static int ans = Integer.MIN_VALUE;

	// (M,set)
	private static void playGame(int[] set) {
		int temp_cnt = cnt;
		int temp_ans = 0;
		ArrayList<Enermy> tlist = new ArrayList<>();
		tlist.addAll(list);
		
		while (temp_cnt > 0) {
			System.out.println(Arrays.toString(set));
			System.out.println(temp_cnt);
			for (int i = 0; i < set.length; i++) {
				for (int j = 0; j < tlist.size(); j++) {
					Enermy e = tlist.get(j);
					e.dis[i] = Math.abs(e.r - N) + Math.abs(e.c - set[i]);
					e.turn = i;
				}

				Collections.sort(tlist);

				here: for (int j = 0; j < tlist.size(); j++) {
					Enermy e = tlist.get(j);
					if (!e.isDie) { // 아직 적이 죽지 않았으면
						if (e.dis[i] <= D) { // 궁수의 사정거리 안이면
							e.shooted++;
							break here;
						}
					}
				}
			}

			for (int j = 0; j < tlist.size(); j++) {
				Enermy e = tlist.get(j);
				if (!e.isDie) {
					if (e.shooted > 0) { // 화살에 맞았거나
						e.isDie = true;
						temp_ans++;
						temp_cnt--;
					}
				}
				e.r++;
				if (!e.isDie) {
					if (e.r >= N) {
						temp_cnt--;
					}

				}
			}
		}

		ans = (ans < temp_ans) ? temp_ans : ans;
		return;
	} // end of playGame()

	private static class Enermy implements Comparable<Enermy> {
		int r;
		int c;
		int turn;
		int[] dis;
		int shooted;
		boolean isDie;

		public Enermy(int r, int c) {
			this.r = r;
			this.c = c;
			dis = new int[3];
		}

		@Override
		public int compareTo(Enermy o) {
			int temp = this.dis[this.turn] - o.dis[this.turn];

			if (temp == 0) {
				return this.c - o.c;
			}
			return temp;
		}
	} // end of Enermy
} // end of class
