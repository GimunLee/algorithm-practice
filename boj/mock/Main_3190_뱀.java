package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3190_뱀 {
	private static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	private static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 보드의 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 갯수
		int[][] map = new int[N + 1][N + 1];
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 5;
		}

		int L = Integer.parseInt(br.readLine()); // 뱀의 이동 변환 (시간, 방향)
		int[][] commandArray = new int[L][2];
		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken()); // 초후
			int D = st.nextToken().charAt(0) == 'L' ? -1 : 1; // 방향
			commandArray[l][0] = X;
			commandArray[l][1] = D;
		}
		// -- end of input

		int r = 1; // 첫 위치
		int c = 1;
		int curDir = 1;
		int[][] queue = new int[10001][2];
		int front = -1, rear = -1;
		queue[++rear][0] = 1;
		queue[rear][1] = 1;

		int commandIndex = 0;
		map[1][1] = 9;
		int time = 1;
		while (true) {
			int nR = r + dr[curDir]; // 머리가 갈 위치
			int nC = c + dc[curDir];

			if (nR > 0 && nC > 0 && nR <= N && nC <= N) {
				if (map[nR][nC] == 9) {
					break;
				} else if (map[nR][nC] == 5) { // 사과일 때,
					map[nR][nC] = 9;
				} else if (map[nR][nC] == 0) {
					map[nR][nC] = 9;
					// 꼬리 떙기기
					int tR = queue[++front][0];
					int tC = queue[front][1];
					map[tR][tC] = 0;
				}
				queue[++rear][0] = nR;
				queue[rear][1] = nC;

				if (commandIndex < L && commandArray[commandIndex][0] == time) {
					int D = commandArray[commandIndex][1];
					int turnDir = curDir + D;
					if (turnDir > 3) {
						turnDir = 0;
					} else if (turnDir < 0) {
						turnDir = 3;
					}
					curDir = turnDir;
					commandIndex++;
				}
				time++;
				r = nR;
				c = nC;
			} else {
				break;
			}
		}
		System.out.println(time);
	}
}
