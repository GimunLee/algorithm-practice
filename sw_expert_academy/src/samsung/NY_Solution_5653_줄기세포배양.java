package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NY_Solution_5653_줄기세포배양 {

	static int[][] map;
	static int[][] timeCheck; // 한번 방문한 곳은 방문 할 수 없음을 체크할 변수
	static boolean[][] isDie;
	static Queue<Cell> q;
	static int K;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken()); // R : 세로크기
			int M = Integer.parseInt(st.nextToken()); // C : 가로크기
			K = Integer.parseInt(st.nextToken()); // K : 시간

			map = new int[N + 2 * K][M + 2 * K];
			timeCheck = new int[N + 2 * K][M + 2 * K];
			isDie = new boolean[N + 2 * K][M + 2 * K];
			q = new LinkedList<Cell>();

			int CenterN = (N + 2 * K) / 2;
			int CenterM = (M + 2 * K) / 2;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[CenterN + r][CenterM + c] = Integer.parseInt(st.nextToken());
					timeCheck[CenterN + r][CenterM + c] = map[CenterN+r][CenterM + c];
					if (map[CenterN + r][CenterM + c] != 0) {
						q.add(new Cell(CenterN + r, CenterM + c)); // 번식할 세포를 Queue에 넣어줌
					}
				}
			}

			// 세포 증식 시작
			int Ans = bfs();

			sb.append('#').append(tc).append(' ').append(Ans).append('\n');
		} // end of for of TestCase
		System.out.println(sb.toString());
	} // end of main

	private static int bfs() {
		int time = 0;
		int totalCnt = 0;
		int dieCnt = 0;

		while (!q.isEmpty()) {
			if (time == K) { // 정해진 시간이 됐을때,
				// 살아있는 줄기세포(비활성상태, 활성상태)의 개수 저장
				return totalCnt - dieCnt;
			}
			time++;

			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Cell c = q.poll();

				// 활성화가 안됐으면
				timeCheck[c.r][c.c] -= 1;
				if (timeCheck[c.r][c.c] != 0) {
					q.add(new Cell(c.r, c.c));
					continue;
				}

				if (timeCheck[c.r][c.c] == (-1) * map[c.r][c.c]) {
					dieCnt++;
					isDie[c.r][c.c] = true;
					continue;
				}

				for (int d = 0; d < dc.length; d++) {
					int nR = c.r + dr[d];
					int nC = c.c + dc[d];

					// 범위를 벗어나는지
					if (nR < 0 || nC < 0 || nR > map.length || nC > map[0].length - 1) {
						continue;
					}

					// 이미 죽은 세포면
					if (isDie[nR][nC]) {
						continue;
					}

					// 같은 Time에 같은 자리를 배양하려고 하는지,
					if (map[nR][nC] != 0) {
						// 넣으려고 하는 것과 이미 있는 것과 비교하여 큰 것을 저장
						if (map[nR][nC] < map[c.r][c.c]) {
							map[nR][nC] = map[c.r][c.c];
							timeCheck[nR][nC] = map[c.r][c.c];
						}
					}

					// 아무도 배양하지 않았다면 자신을 배양
					if (map[nR][nC] == 0) {
						map[nR][nC] = map[c.r][c.c];
						timeCheck[nR][nC] = map[c.r][c.c];
						q.add(new Cell(nR, nC));
						totalCnt++;
					}

					q.add(new Cell(c.r, c.c)); // 죽이기 위함
				}
			}
		}

		return -1;
	} // end of bfs()

} // end of class

class Cell {
	int r; // 행
	int c; // 열

	Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
