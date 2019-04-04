package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 최적화 Memo
 */

public class Solution_2383_점심식사시간_memo {
	private static ArrayList<Pair> people;
	private static Stair[] stairs;
	private static int[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 방의 크기
			int[][] map = new int[N][N];
			people = new ArrayList<Pair>();
			stairs = new Stair[2];

			int s_idx = 0;
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) { // 사람 저장
						people.add(new Pair(r, c));
					} else if (map[r][c] >= 2) { // 계단 저장
						stairs[s_idx++] = new Stair(r, c, map[r][c]);
					}
				}
			} // end of for input
			set = new int[people.size()];
			memo = new int[2][people.size()];
			ans = Integer.MAX_VALUE;
			PI(0);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.print(sb.toString());
	} // end of main

	private static int[] set; // 순열을 저장할 배열
	private static int ans; // 정답

	private static void PI(int len) {
		if (len == set.length) {
			int tmp = solve(); // 해당 사건에서 최대값 return
			ans = (ans > tmp) ? tmp : ans; // 그 중 최소값 저장
			return;
		}
		for (int i = 0; i < 2; i++) {
			set[len] = i;
			PI(len + 1);
		}
	} // end of PI()

	private static int solve() {
		ArrayList<Integer> timeFirst = new ArrayList<>(); // 첫번째 계단 이용 사람
		ArrayList<Integer> timeSecond = new ArrayList<>(); // 두번째 계단 이용 사람

		for (int p_idx = 0; p_idx < set.length; p_idx++) {
			int dTime = 0;
			if (memo[set[p_idx]][p_idx] == 0) {
				dTime = Math.abs(people.get(p_idx).r - stairs[set[p_idx]].r)
						+ Math.abs(people.get(p_idx).c - stairs[set[p_idx]].c);
				memo[set[p_idx]][p_idx] = dTime;
			} else {
				dTime = memo[set[p_idx]][p_idx];
			}

			if (set[p_idx] == 0) {
				timeFirst.add(dTime);
			} else {
				timeSecond.add(dTime);
			}
		}

		if (timeFirst.size() != 0) {
			Collections.sort(timeFirst);
		}
		if (timeSecond.size() != 0) {
			Collections.sort(timeSecond);
		}
		int max_first = Integer.MIN_VALUE;
		int max_second = Integer.MIN_VALUE;

		if (timeFirst.size() > 0) {
			for (int i = 0; i < timeFirst.size(); i++) {
				if (i < 3) {
					timeFirst.set(i, timeFirst.get(i) + stairs[0].len + 1);
					continue;
				}
				if (i - 3 >= 0) {
					if (timeFirst.get(i) >= timeFirst.get(i - 3)) { // 안 기다려도됨
						timeFirst.set(i, timeFirst.get(i) + stairs[0].len + 1);
					} else {
						timeFirst.set(i, timeFirst.get(i - 3) + stairs[0].len);
					}
				}
			}
		}

		if (timeSecond.size() > 0) {
			for (int i = 0; i < timeSecond.size(); i++) {
				if (i < 3) {
					timeSecond.set(i, timeSecond.get(i) + stairs[1].len + 1);
					continue;
				}
				if (i - 3 >= 0) {
					if (timeSecond.get(i) >= timeSecond.get(i - 3)) { // 안 기다려도됨
						timeSecond.set(i, timeSecond.get(i) + stairs[1].len + 1);
					} else {
						timeSecond.set(i, timeSecond.get(i - 3) + stairs[1].len);
					}
				}
			}
		}

		if (timeFirst.size() != 0) {
			max_first = timeFirst.get(timeFirst.size() - 1);
		}
		if (timeSecond.size() != 0) {
			max_second = timeSecond.get(timeSecond.size() - 1);
		}
		return Math.max(max_first, max_second);
	} // end of solve()

	private static class Pair { // 사람
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of stair

	private static class Stair { // 계단
		int r;
		int c;
		int len; // 계단의 길이

		Stair(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	} // end of stair
} // end of class
