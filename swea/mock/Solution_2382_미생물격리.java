package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2382_미생물격리 {
	private static final int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 };
	private static int N, M, K;
	private static int[][] map;
	private static Cell[] cellInfo;
	private static int[][] queue = new int[1_000_000][3];
	private static int[][] moveQueue = new int[1_001][3];
	private static int front, rear, mFront, mRear;
	private static int sum;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); //
			M = Integer.parseInt(st.nextToken()); //
			K = Integer.parseInt(st.nextToken()); //
			map = new int[N][N]; // 0, N -> 칠해져있는 경우
			cellInfo = new Cell[K + 1];

			front = -1;
			rear = -1;
			sum = 0;
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				int idx = k + 1;
				cellInfo[idx] = new Cell(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				map[cellInfo[idx].r][cellInfo[idx].c] = idx;
				sum += cellInfo[idx].cnt;
				queue[++rear][0] = cellInfo[idx].r;
				queue[rear][1] = cellInfo[idx].c;
				queue[rear][2] = idx;
			}
			sb.append("#").append(tc).append(" ").append(solution()).append("\n");
		} // end o f TestCase
		System.out.println(sb.toString());
	} // end of main

	// 3. M시간 이후 남아있는 미생물의 수 총합을 구하라.
	private static int solution() {
		int answer = sum;
		int time = 0;
		while (front != rear) {
			if (time++ == M) {
				break;
			}
			mFront = -1;
			mRear = -1;
			int[] tmpSum = new int[K + 1];
			int size = rear - front;
			for (int i = 0; i < size; i++) {
				int r = queue[++front][0];
				int c = queue[front][1];
				int idx = queue[front][2];

				map[r][c] = 0;

				int nR = r + dr[cellInfo[idx].dir];
				int nC = c + dc[cellInfo[idx].dir];

				if (nR <= 0 || nC <= 0 || nR >= N - 1 || nC >= N - 1) { // 시약을 만남
					// 1. 범위를 벗어나면 미생물의 절반이 죽고, 방향이 반대로 바뀜
					cellInfo[idx].dir = changeDir(cellInfo[idx].dir);
					int tmp = cellInfo[idx].cnt;
					cellInfo[idx].cnt /= 2;
					answer -= (tmp - cellInfo[idx].cnt);
					if (cellInfo[idx].cnt == 0) {
						cellInfo[idx] = null;
						continue;
					}
				}
				moveQueue[++mRear][0] = nR;
				moveQueue[mRear][1] = nC;
				moveQueue[mRear][2] = idx;
			} // end of for(time)

			while (mFront != mRear) {
				int r = moveQueue[++mFront][0];
				int c = moveQueue[mFront][1];
				int idx = moveQueue[mFront][2];
				// 2. 두 개이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 됨
				// (방향은 미생물의 수가 더 많았던 군집의 방향)
				if (map[r][c] != 0) { // 다른 미생물이 있는 경우
					int cellIndex = map[r][c];
					tmpSum[cellIndex] += cellInfo[idx].cnt;
					if (cellInfo[cellIndex].cnt < cellInfo[idx].cnt) {
						cellInfo[cellIndex] = cellInfo[idx];
					} else if (cellInfo[cellIndex].cnt > cellInfo[idx].cnt) {
						cellInfo[idx] = cellInfo[cellIndex];
					}
				} else {
					map[r][c] = idx;
					queue[++rear][0] = r;
					queue[rear][1] = c;
					queue[rear][2] = idx;
					tmpSum[idx] += cellInfo[idx].cnt;
				}
			}

			for (int i = 1; i <= K; i++) {
				if (tmpSum[i] != 0 && tmpSum[i] != cellInfo[i].cnt) {
					cellInfo[i].cnt = tmpSum[i];
				}
			}
		} // end of while(queue)
		return answer;
	} // end of func(solution)

	private static int changeDir(int dir) {
		switch (dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	private static class Cell {
		int r, c, cnt, dir;

		public Cell(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	} // end of Cell
} // end of class
