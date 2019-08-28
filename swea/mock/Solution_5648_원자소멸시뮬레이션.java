package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 2019.08.28.(수)
 * */
public class Solution_5648_원자소멸시뮬레이션 {
	private static int[] dy = { -1, 1, 0, 0 }; // 상, 하 (row)
	private static int[] dx = { 0, 0, -1, 1 }; // 좌, 우 (column)

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		Ball[] originArray = new Ball[1001]; // 움직이고 고정된 위치의 배열
		Ball[] moveArray = new Ball[1001]; // 움직일 때 쓰는 배열
		Ball[] tmpArray = new Ball[1001]; // 움직일 때 원소끼리 충돌하는지 체크하는 배열

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			Ball[][] map = new Ball[2001][2001];

			int originIndex = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = 1000 + Integer.parseInt(st.nextToken());
				int y = 1000 - Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				Ball ball = new Ball(x, y, dir, k);
				map[y][x] = ball;
				originArray[originIndex++] = ball;
			} // end of for(Input)

			int ANS = 0;
			int moveIndex = 0;

			while (originIndex != 0) {
				moveIndex = 0;
				for (int i = 0; i < originIndex; i++) { // 움직여보는중
					Ball ball = originArray[i];

					if (map[ball.y][ball.x] == null) { // 한 타임이 지나고 없어졌다는 것
						continue;
					}

					int nX = ball.x + dx[ball.dir];
					int nY = ball.y + dy[ball.dir];

					if (nY > 2000 || nX > 2000 || nX < 0 || nY < 0) { // 맵을 벗어나는 경우
						map[ball.y][ball.x] = null;
						continue;
					}

					// 맵이 이미 차있고, 다음 원소의 방향이 내쪽으로 오는 경우 ( 0.5초에 만나는 경우)
					if (map[nY][nX] != null && map[nY][nX].dir == nextBallDir(ball.dir)) {
						ANS += (map[nY][nX].k + ball.k);
						map[nY][nX] = null;
						map[ball.y][ball.x] = null;
						continue;
					}

					// 서로 이동중에 충돌하지 않는 한 움직일 수 있음
					map[ball.y][ball.x] = null;
					ball.x = nX;
					ball.y = nY;
					moveArray[moveIndex++] = ball;
				} // 움직일 때, 만나는 경우

				originIndex = 0;
				int tmpIndex = 0;
				// 움직여보이니까 부딪히는 경우
				for (int i = 0; i < moveIndex; i++) {
					Ball ball = moveArray[i];
					if (map[ball.y][ball.x] == null) {
						map[ball.y][ball.x] = ball;
						originArray[originIndex++] = ball;
					} else { // 원자가 같은 시간대에 충돌하는 경우
						tmpArray[tmpIndex++] = ball;
						ANS += (map[ball.y][ball.x].k + ball.k);
						map[ball.y][ball.x].k = 0; // 다른게 와서 충돌할 수 있으므로 남겨놔야함 (3개 충돌시)
					}
				}

				// 충돌난 것 없애주기
				for (int i = 0; i < tmpIndex; i++) {
					Ball ball = tmpArray[i];
					map[ball.y][ball.x] = null;
				}
			}
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	private static int nextBallDir(int dir) {
		if (dir == 0) {
			return 1;
		} else if (dir == 1) {
			return 0;
		} else if (dir == 2) {
			return 3;
		} else {
			return 2;
		}
	} // end of func(nextBallDir)

	private static class Ball {
		int x, y, dir, k;

		public Ball(int x, int y, int dir, int k) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	} // end of Ball
} // end of class
