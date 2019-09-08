package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3197_백조의호수 {
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };
	private static char[][] map;
	private static int R, C;
	private static int maxMeltTime;
	private static int[] anotherBird = { 0, 2, 1 };
	private static ArrayList<Pair> birdList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		birdList = new ArrayList<Pair>();

		map = new char[R][C];

		Queue<Pair> waterQueue = new LinkedList<>();
		int idx = 1;
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == '.' || map[r][c] == 'L') {
					waterQueue.add(new Pair(r, c));
				}
				if (map[r][c] == 'L') {
					birdList.add(new Pair(r, c, idx++));
				}
			}
		} // end of for(Input)

		// 빙하의 녹는 날짜를 계산하기
		maxMeltTime = Integer.MIN_VALUE;
		int[][] iceMap = bfs(waterQueue); // 녹는 시간을 저장
		// 이분 탐색으로 백조를 탐색시킨다.
		ANSER = Integer.MAX_VALUE;
		binarySearch(iceMap, 0, maxMeltTime);
		System.out.println(ANSER);

	} // end of main

	private static int ANSER;

	private static void binarySearch(int[][] iceMap, int start, int end) {
		if(start > end) {
			return;
		}
		
		int mid = (start + end) / 2;
		// mid 값을 탐색보냄
		Queue<Pair> birdQueue = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
			Pair pair = birdList.get(i);
			birdQueue.add(new Pair(pair.r, pair.c, pair.num));
		}
		boolean flag = goBird(iceMap, birdQueue, mid);
		if (flag) {
			ANSER = ANSER > mid ? mid : ANSER;
		}
		if (flag) { // 만난 것, 더 적게 보내보기
			binarySearch(iceMap, start, mid - 1);
		} else { // 못 만난 것, 시간 늘리기
			binarySearch(iceMap, mid + 1, end);
		}
	}

	private static boolean goBird(int[][] iceMap, Queue<Pair> birdQueue, int mid) {
		int[][] visited = new int[R][C];

		while (!birdQueue.isEmpty()) {
			Pair pair = birdQueue.poll();
			int r = pair.r;
			int c = pair.c;
			int num = pair.num;

			visited[r][c] = num;

			for (int dir = 0; dir < dr.length; dir++) {
				int nR = r + dr[dir];
				int nC = c + dc[dir];

				if (nR < 0 || nC < 0 || nR >= R || nC >= C) {
					continue;
				}

				if (visited[nR][nC] == num) {
					continue;
				}

				if (visited[nR][nC] == anotherBird[num]) {
					return true;
				}

				if (mid >= iceMap[nR][nC]) {
					birdQueue.add(new Pair(nR, nC, num));
					visited[nR][nC] = num;
				}
			}
		}
		return false;
	}

	private static int[][] bfs(Queue<Pair> waterQueue) {
		int[][] iceMap = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		int time = 1;
		while (!waterQueue.isEmpty()) {
			int size = waterQueue.size();
			for (int i = 0; i < size; i++) {
				Pair pair = waterQueue.poll();
				int r = pair.r;
				int c = pair.c;
				visited[r][c] = true;
				for (int dir = 0; dir < dr.length; dir++) {
					int nR = r + dr[dir];
					int nC = c + dc[dir];

					if (nR < 0 || nC < 0 || nR >= R || nC >= C) {
						continue;
					}

					if (visited[nR][nC]) {
						continue;
					}

					if (map[nR][nC] == 'X') {
						waterQueue.add(new Pair(nR, nC));
						iceMap[nR][nC] = time;
						maxMeltTime = maxMeltTime < time ? time : maxMeltTime;
						visited[nR][nC] = true;
					}
				}
			}
			time++;
		}
		return iceMap;
	}

	private static class Pair {
		int r, c, num;

		public Pair(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
} // end of class
