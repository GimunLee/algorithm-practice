package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	static int[] dr = { -1, 0, 0, 1 }; // 상좌우하
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 20

		int[][] map = new int[N][N];
		int[][] visited = new int[N][N];
		int[] cntFish = new int[7]; // 0은 안씀
		cntFish[0] = Integer.MAX_VALUE;
		int[][] queue = new int[200][2];
		int front = -1, rear = -1;

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) { // 아기 상어
					queue[++rear][0] = r;
					queue[rear][1] = c;
					map[r][c] = 0;
				} else if (map[r][c] != 0) {
					cntFish[map[r][c]]++;
				}
			}
		} // end of for(input)

		int time = 1;
		int cntEat = 0;
		int level = 2;

		int ans = 0;

		int[][] tmpArr = new int[4][2];
		int tmpArrIndex = 0;
		there: while (rear != front) {

			int tmp = 0;
			int size = rear;
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(visited[i]) + " | " + Arrays.toString(map[i]));
//			}
//			System.out.println("=============================");

			here: while (front < size) {
				int r = queue[++front][0];
				int c = queue[front][1];

				for (int i = 0; i < dr.length; i++) { // 상 좌 우 하 확인
					int nR = r + dr[i];
					int nC = c + dc[i];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // 범위 초과
						continue;
					}

					if (visited[nR][nC] != 0) {
						continue;
					}

					if (map[nR][nC] > level) { // 아기 상어가 먹을 수 없는 경우
						continue;
					} else if (map[nR][nC] != 0 && map[nR][nC] < level) { // 가장 가까운 먹을 수 있는 먹이인 경우,
						tmpArr[tmpArrIndex][0] = nR;
						tmpArr[tmpArrIndex++][1] = nC;
					} else {
						queue[++rear][0] = nR;
						queue[rear][1] = nC;
						visited[nR][nC] = time;
					}

				}
				if (tmpArrIndex != 0) {
					int tmpR = Integer.MAX_VALUE;
					int tmpC = Integer.MAX_VALUE;
					for (int j = 0; j < tmpArrIndex; j++) {
						int rr = tmpArr[j][0];
						int cc = tmpArr[j][1];
						if (tmpR > rr) {
							tmpR = rr;
							tmpC = cc;
						} else if (tmpR == rr) {
							if (tmpC > cc) {
								tmpR = rr;
								tmpC = cc;
							}
						}
					}
					cntEat++;
					if (cntEat == level && level <= 6) {
						level++; // 레벨업
						cntEat = 0;
					}
					cntFish[map[tmpR][tmpC]]--;
					map[tmpR][tmpC] = 0;

					for (int j = 1; j < level; j++) {
						tmp += cntFish[j];
					}
					if (tmp == 0) {
						ans = time;
						break there;
					}
					// -- 초기화 작업
					front = -1;
					rear = -1;
					visited = new int[N][N];
					visited[tmpR][tmpC] = time;
					queue[++rear][0] = tmpR;
					queue[rear][1] = tmpC;

					tmpArrIndex = 0;
					break;
				}
			} // end of for(direction)
			time++;
		} // end of while(queue)
		System.out.println(ans);
	}
}
