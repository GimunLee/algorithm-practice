package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Solution_4013_특이한자석 {
	static int[][] magnet; // r : 톱니바퀴 인덱스, c : 각 톱니 // 톱니를 일자로 펴서 생각

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		magnet = new int[4][8]; // r : 톱니바퀴 인덱스, c : 각 톱니

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 회전시키는 횟수
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of for(input)

			for (int i = 0; i < N; i++) { // 회전시키는 횟수만큼 반복
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int magnetNum = Integer.parseInt(st.nextToken()); // 자석 번호
				int rotateDir = Integer.parseInt(st.nextToken()); // 회전 방향
				magnetNum -= 1; // 0부터 시작하므로 1씩 빼줌

				// 회전했을 때 각 극이 맞는지 안 맞는지 체크함
				if (magnetNum == 0) {
					if (magnet[magnetNum][2] != magnet[magnetNum + 1][6]) {
						if (magnet[magnetNum + 1][2] != magnet[magnetNum + 2][6]) {
							if (magnet[magnetNum + 2][2] != magnet[magnetNum + 3][6]) {
								rotate(magnetNum + 3, -rotateDir);
							}
							rotate(magnetNum + 2, rotateDir);
						}
						rotate(magnetNum + 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}

				if (magnetNum == 3) {
					if (magnet[magnetNum][6] != magnet[magnetNum - 1][2]) {
						if (magnet[magnetNum - 1][6] != magnet[magnetNum - 2][2]) {
							if (magnet[magnetNum - 2][6] != magnet[magnetNum - 3][2]) {
								rotate(magnetNum - 3, -rotateDir);
							}
							rotate(magnetNum - 2, rotateDir);
						}
						rotate(magnetNum - 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}

				if (magnetNum == 1) {
					if (magnet[magnetNum][6] != magnet[magnetNum - 1][2]) {
						rotate(magnetNum - 1, -rotateDir);
					}
					if (magnet[magnetNum][2] != magnet[magnetNum + 1][6]) {
						if (magnet[magnetNum + 1][2] != magnet[magnetNum + 2][6]) {
							rotate(magnetNum + 2, rotateDir);
						}
						rotate(magnetNum + 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}

				if (magnetNum == 2) {
					if (magnet[magnetNum][2] != magnet[magnetNum + 1][6]) {
						rotate(magnetNum + 1, -rotateDir);
					}
					if (magnet[magnetNum][6] != magnet[magnetNum - 1][2]) {
						if (magnet[magnetNum - 1][6] != magnet[magnetNum - 2][2]) {
							rotate(magnetNum - 2, rotateDir);
						}
						rotate(magnetNum - 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}
			} // end of for(EachRotate)

			int sum = 0; // 정답을 저장하기 위한 변수

			for (int i = 0; i < 4; i++) {
				if (magnet[i][0] == 1) {
					sum += (1 << i); // 점수계산을 위한 Shift 연산
				}
			}
			System.out.println("#" + tc + " " + sum); // 정답 출력
		} // end of for(TestCase)
	} // end of main

	/** idx : 회전시킬 바퀴 인덱스, dir : 방향 */
	private static void rotate(int idx, int dir) {
		int[] temp;
		switch (dir) {
		case 1: // 시계 방향 회전
			temp = new int[8];
			temp[0] = magnet[idx][7]; // 회전 시, 마지막 값을 맨 앞으로 보내기 위한 임시 변수

			for (int i = 0; i < magnet[0].length - 1; i++) {
				temp[i + 1] = magnet[idx][i]; // 한칸씩 오른쪽으로 밀어준 값을 임시 변수에 저장
			}

			for (int i = 0; i < temp.length; i++) {
				magnet[idx][i] = temp[i]; // 임시 변수에 저장된 값을 다시 원본 데이터에 저장
			}
			break;
		case -1: // 반시계 방향 회전
			temp = new int[8];
			temp[7] = magnet[idx][0]; // 회전 시, 첫번째 값을 맨 뒤로 보내기 위한 임시 변수

			for (int i = 1; i < magnet[0].length; i++) {
				temp[i - 1] = magnet[idx][i]; // 한칸씩 왼쪽으로 밀어준 값을 임시 변수에 저장
			}

			for (int i = 0; i < temp.length; i++) {
				magnet[idx][i] = temp[i]; // 임시 변수에 저장된 값을 다시 원본 데이터에 저장
			}
			break;
		}
	} // end of func(rotate)
} // end of class
