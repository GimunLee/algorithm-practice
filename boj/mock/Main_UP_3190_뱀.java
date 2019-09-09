package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_UP_3190_뱀 {
	private static int[] dr = { -1, 0, 1, 0 }; // 상우하좌 (시계방향)
	private static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 맵의 크기 제한 (N x N)
		int K = Integer.parseInt(br.readLine()); // 사과의 갯수 
		int[][] map = new int[N + 1][N + 1]; // 맵 생성
		
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 5; // 맵에 사과를 저장
		}

		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 명령어 수 
		int[][] commandArray = new int[L][2]; // 뱀의 방향 변환 명령어를 저장할 변수
		
		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken()); // 초
			int C = st.nextToken().charAt(0) == 'L' ? -1 : 1; // 방향
			commandArray[l][0] = X; // 시간 (X초를 마치고 방향 전환)
			commandArray[l][1] = C; // 방향 (왼쪽 : -1, 오른쪽 : 1)
		}
		// -- end of input

		int r = 1; int c = 1; // 뱀 머리의 첫 위치
		int curDir = 1; // 현재 방향 (오른쪽)
		
		int[][] queue = new int[10001][2]; // 뱀을 머리부터 꼬리까지 저장할 Queue 생성
		int front = -1, rear = -1; // Queue를 제어할 변수
		
		queue[++rear][0] = 1; queue[rear][1] = 1; // 뱀 머리의 최초 위치 넣기
		int commandIndex = 0; // 현재까지 수행한 명령어 index
		map[1][1] = 9; // 맵에 뱀의 머리 표시
		
		int time = 1; // 1초부터 시작
		while (true) {
			int nR = r + dr[curDir]; // 다음 뱀의 머리가 갈 위치
			int nC = c + dc[curDir];

			if (nR > 0 && nC > 0 && nR <= N && nC <= N) { // 맵을 벗어나지 않을때
				if (map[nR][nC] == 9) { // 다음에 뱀을 만난다면, 게임 종료
					break;
				} else if (map[nR][nC] == 5) { // 사과일 때, 그 위치를 뱀으로 바꾸기
					map[nR][nC] = 9;
				} else if (map[nR][nC] == 0) { // 빈칸이라면, 꼬리 당기기
					map[nR][nC] = 9;
					// Queue의 맨 앞에는 항상 꼬리가 저장돼있기 때문에 해당 위치를 0으로 바꿔줌
					int tR = queue[++front][0]; 
					int tC = queue[front][1];
					map[tR][tC] = 0;
				}
				
				queue[++rear][0] = nR; // 뱀의 머리부분을 Queue에 넣어줌
				queue[rear][1] = nC;

				if (commandIndex < L && commandArray[commandIndex][0] == time) { // 현재 시간이 명령어에 저장된 시간이라면
					int D = commandArray[commandIndex][1]; // 방향 전환 뽑기
					int turnDir = curDir + D;
					if (turnDir > 3) { // 좌에서 오른쪽이면 '상'으로 가야하므로 0으로 바꿔줌
						turnDir = 0;
					} else if (turnDir < 0) { // 상에서 왼쪽이면 '좌'로 가야하므로 3으로 바꿔줌
						turnDir = 3;
					}
					curDir = turnDir; // 바꾼 뱀의 진행 방향을 저장
					commandIndex++; // 다음 명령어를 탐색하도록 인덱스 증가
				}
				time++; // 시간 증가 
				r = nR; // 이동한 뱀의 머리로 r과 c를 바꿔줌
				c = nC;
			} else { // 맵을 벗어난다면 
				break; // 게임 종료
			}
		} // end of while
		System.out.println(time); // 게임 종료 시간 출력
	} // end of main
} // end of class
