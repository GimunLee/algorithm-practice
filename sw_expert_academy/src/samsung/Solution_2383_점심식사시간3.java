package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간3 {
	private static ArrayList<Pair> people;
	private static Stair[] stairs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

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
			ans = Integer.MAX_VALUE;
			PI(0);

			System.out.println("#" + tc + " " + ans);

		} // end of for TestCase
	} // end of main

	private static int[] set;
	private static int ans;

	private static void PI(int len) {
		if (len == set.length) {
//			System.out.println(Arrays.toString(set));
			int tmp = solve(); // 최대값 return
			if (ans > tmp) {
				ans = tmp;
			}
//			System.out.println(ans);
			return;
		}

		for (int i = 0; i < 2; i++) {
			set[len] = i;
			PI(len + 1);
		}
	}

	private static int solve() {
		ArrayList<Integer> timeFirst = new ArrayList<>();
		ArrayList<Integer> timeSecond = new ArrayList<>();

		for (int i = 0; i < set.length; i++) {
			int pR = people.get(i).r;
			int pC = people.get(i).c;

			int sR = stairs[set[i]].r;
			int sC = stairs[set[i]].c;

			int dTime = Math.abs(pR - sR) + Math.abs(pC - sC);
//			System.out.println(i+"번째 사람이 " + set[i] + "번째 계단을 갈 때, 걸리는 시간은 "+ dTime);
			if (set[i] == 0) {
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
		System.out.println(max_first + ", " + max_second);
		return Math.max(max_first, max_second);
	}

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of stair

	private static class Stair {
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
