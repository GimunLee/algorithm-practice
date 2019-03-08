package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {
	static int[][] mg; // r : 톱니바퀴, c : 각 톱니

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		mg = new int[4][8]; // r : 톱니바퀴, c : 각 톱니1

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N

			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					mg[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int mgNum = Integer.parseInt(st.nextToken()); // 자석 번호
				int turnDir = Integer.parseInt(st.nextToken()); // 회전 방향

				mgNum -= 1;
				// 기준 인덱스 : 2, 같으면 안돌고, 다르면 서로 다른 방향으로 돈다.
				if (mgNum == 0) {
					if (mg[mgNum][2] != mg[mgNum+1][6]) {
						if (mg[mgNum+1][2] != mg[mgNum+2][6]) {
							if (mg[mgNum+2][2] != mg[mgNum+3][6]) {
								turn(mgNum+3, -turnDir);
							}
							turn(mgNum+2, turnDir); // 서로 다른 방향으로 돌린다.
						}
						turn(mgNum+1, -turnDir); // 서로 다른 방향으로 돌린다.
					}
					turn(mgNum, turnDir);
				}

				if (mgNum == 3) {
					if (mg[mgNum][6] != mg[mgNum-1][2]) {
						if (mg[mgNum-1][6] != mg[mgNum-2][2]) {
							if (mg[mgNum-2][6] != mg[mgNum-3][2]) {
								turn(mgNum-3, -turnDir);
							}
							turn(mgNum-2, turnDir); // 서로 다른 방향으로 돌린다.
						}
						turn(mgNum-1, -turnDir); // 서로 다른 방향으로 돌린다.
					}
					turn(mgNum, turnDir);
				}

				if (mgNum == 1) {
					if (mg[mgNum][6] != mg[mgNum - 1][2]) {
						turn(mgNum - 1, -turnDir);
					}
					if (mg[mgNum][2] != mg[mgNum + 1][6]) {
						if (mg[mgNum + 1][2] != mg[mgNum + 2][6]) {
							turn(mgNum + 2, turnDir);
						}
						turn(mgNum + 1, -turnDir);
					}
					turn(mgNum, turnDir);
				}

				if (mgNum == 2) {
					if (mg[mgNum][2] != mg[mgNum + 1][6]) {
						turn(mgNum + 1, -turnDir);
					}
					if (mg[mgNum][6] != mg[mgNum - 1][2]) {
						if (mg[mgNum - 1][6] != mg[mgNum - 2][2]) {
							turn(mgNum - 2, turnDir);
						}
						turn(mgNum - 1, -turnDir);
					}
					turn(mgNum, turnDir);
				}
//				System.out.println(mgNum+1 + ", "+ turnDir); 
//				System.out.println("============" + cnt +"==============");
//				for (int j = 0; j < 4; j++) {
//					System.out.println(Arrays.toString(mg[j]));
//				}
				
				cnt++;
			} // end of for of input
			
			int sum = 0;
			// N극 : 0
			for (int i = 0; i < 4; i++) {
				if(mg[i][0] == 1) {
					sum += (1 << i);
				} 
			}
			
			System.out.println("#"+ tc + " " + sum);
		}
	}

	/** mg : 돌릴 기준 바퀴, d : 방향 */
	private static void turn(int idx, int d) {
		switch (d) {
		case 1: // 시계방향
			int[] temp = new int[8];
			temp[0] = mg[idx][7]; // 마지막 값 저장, 맨 앞으로 보내기 위함.

			for (int i = 0; i < mg[0].length - 1; i++) {
				temp[i + 1] = mg[idx][i];
			}

			for (int i = 0; i < temp.length; i++) {
				mg[idx][i] = temp[i];
			}
			break;
		case -1: // 반시계방향
			int[] temp2 = new int[8];
			temp2[7] = mg[idx][0]; // 마지막 값 저장, 맨 앞으로 보내기 위함.

			for (int i = 1; i < mg[0].length; i++) {
				temp2[i - 1] = mg[idx][i];
			}

			for (int i = 0; i < temp2.length; i++) {
				mg[idx][i] = temp2[i];
			}
			break;
		}
	}
}
