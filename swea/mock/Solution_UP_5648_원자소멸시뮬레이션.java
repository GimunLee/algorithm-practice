package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.08.28.(수)
 * 2019.09.02.(월) Upload
 */
public class Solution_UP_5648_원자소멸시뮬레이션 {
	private static int[] dy = { -1, 1, 0, 0 }; // 상, 하 (row)
	private static int[] dx = { 0, 0, -1, 1 }; // 좌, 우 (column)

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // 정답을 한번에 출력하기 위한 변수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		Ball[] fixedArray = new Ball[1001]; // 원자들의 최종 위치를 저장할 배열
		Ball[] moveArray = new Ball[1001]; // 원자들이 움직일 때 쓰는 배열
		Ball[] crushArray = new Ball[1001]; // 원자 보들이 움직일 때 충돌하는지 체크하는 배열

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 원자들의 개수
			Ball[][] map = new Ball[2001][2001]; // 격자판 생성

			int fixedIndex = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = 1000 + Integer.parseInt(st.nextToken()); // 음수를 저장하기 위한 처리
				int y = 1000 - Integer.parseInt(st.nextToken());
				// 수학적 좌표 y의 경우, 컴퓨터에서는 row입니다. 하지만, 수학 좌표 y와 컴퓨터 row의 증감은 반대이므로 역으로 빼줍니다.

				int dir = Integer.parseInt(st.nextToken()); // 방향 저장
				int k = Integer.parseInt(st.nextToken()); // 보유 에너지 저장
				Ball ball = new Ball(x, y, dir, k); // 원자 생성
				map[y][x] = ball; // 원자의 정보를 격자판에 저장
				fixedArray[fixedIndex++] = ball; // 최초에는 고정된 위치이므로 fixedArray에 저장
			} // end of for(Input)

			int ANS = 0; // 정답 저장 변수

			while (fixedIndex != 0) {
				int moveIndex = 0; // 원자들을 움직이고 저장하기 위한 인덱스 초기화
				for (int i = 0; i < fixedIndex; i++) { // 저장된 원자들을 움직여보기
					Ball ball = fixedArray[i]; // 현재 원자
					if (map[ball.y][ball.x] == null) { // fixedArray에는 있는데 map이 null이라는 건 소멸된 원자라는 것
						continue;
					}
					int nX = ball.x + dx[ball.dir]; // 다음 위치 (column)
					int nY = ball.y + dy[ball.dir]; // 다음 위치 (row)

					if (nY > 2000 || nX > 2000 || nX < 0 || nY < 0) { // 맵을 벗어나는 경우
						map[ball.y][ball.x] = null; // 최초 위치를 null로 만들어줌
						continue;
					}

					// 다음 위치에 이미 원소가 있고, 다음 원소의 방향이 현재 원자 쪽으로 오는 경우 (0.5초에 만나는 경우)
					if (map[nY][nX] != null && map[nY][nX].dir == nextBallDir(ball.dir)) {
						ANS += (map[nY][nX].k + ball.k); // 원자 소멸하면서 에너지 증가
						map[nY][nX] = null; // 다음 위치를 null
						map[ball.y][ball.x] = null; // 현재 위치도 null
						continue;
					}

					// 서로 이동 중에 충돌하지 않는 한 움직일 수 있음
					// 이동한다고 해당 map을 원자로 채우지 않음 (움직이고 충돌날 수 있으므로 최종 위치가 아님)
					map[ball.y][ball.x] = null; // 그전 위치는 null로 함
					ball.x = nX;
					ball.y = nY;
					moveArray[moveIndex++] = ball;
				}

				// 원자들을 움직여봄
				fixedIndex = 0; // 최종 위치 초기화
				int crushIndex = 0; // 움직이고 나서 충돌하는 원소를 저장하기 위한 초기화
				// 움직여보이니까 부딪히는 경우
				for (int i = 0; i < moveIndex; i++) { // 움직인 원자들을 탐색
					Ball ball = moveArray[i]; // 현재 움직일 원자
					if (map[ball.y][ball.x] == null) { // 움직인 위치가 null이라면
						map[ball.y][ball.x] = ball; // 해당 원자를 저장
						fixedArray[fixedIndex++] = ball; // 고정된 자리로 표시 (해당 시간에 충돌나서 없어지더라도, null인지 체크하기 떄문에 상관없음)
					} else { // 원자가 같은 시간대에 충돌하는 경우
						crushArray[crushIndex++] = ball; // 충돌된 배열에 저장
						ANS += (map[ball.y][ball.x].k + ball.k); // 에너지 증가 (그 자리에 있는 원자와 지금 원자)
						map[ball.y][ball.x].k = 0; // 다른게 원자가 와서 충돌할 수 있으므로 null로 표시하면 안됨 (3개 충돌시)
					}
				} //

				// 충돌난 것 원자들을 한번에 없애주기
				for (int i = 0; i < crushIndex; i++) {
					Ball ball = crushArray[i];
					map[ball.y][ball.x] = null;
				}
			} // end of while
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	/** 원자가 현재 내 방향으로 오는지 반환하는 ㅎ마수 */
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
