package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	// 상하좌우로 움직이기 위한 변수
	static int[] dr = { -1, 1, 0, 0 }; // 행(상하)
	static int[] dc = { 0, 0, -1, 1 }; // 열(좌우)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 20

		int[][] map = new int[N][N]; // 격자판 변수
		int[][] visited = new int[N][N]; // 아기 상어가 방문한 곳과 이동한 시간을 저장할 변수
		int[] cntFish = new int[7];
		cntFish[0] = Integer.MAX_VALUE; // 크기별 남은 물고기의 수 (Backtracking), 0은 사용하지 않음

		int[][] queue = new int[500][2]; // Queue 변수
		int front = -1, rear = -1; // Queue를 사용하기 위한 front, rear 변수

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) { // 아기 상어인 경우
					queue[++rear][0] = r;
					queue[rear][1] = c; // Queue에 삽입
					map[r][c] = 0; // BFS 탐색시, 조건 검사를 하지 않기 위해 '0'으로 바꾸기
				} else if (map[r][c] != 0) { // 물고기인 경우
					cntFish[map[r][c]]++; // 해당 크기의 물고기 수를 증가시켜줌(Backtracking)
				}
			}
		} // end of for(input)

		int[][] availableFishArr = new int[N*N/2][2]; // 한 타임에 먹을 수 있는 물고기
		int availableFishArrIndex = 0; // 넣은 물고기의 수

		int level = 2; // 아기 상어의 크기
		int cntEat = 0; // 먹은 물고기의 수

		int ANS = 0; // 정답 변수

		int time = 1; // 시간
		total: while (front != rear) { // queue가 빌 때까지 (!isEmpty())
			int tmp = 0; 
			
			int size = rear; // 한 타임만 돌기 위한 변수
			while (front < size) {
				int r = queue[++front][0]; // queue에서 r,c 좌표를 뽑음 (poll()) 
				int c = queue[front][1]; 

				for (int i = 0; i < dr.length; i++) { // 상하좌우 확인
					int nR = r + dr[i]; // 아기 상어의 다음 행 위치
					int nC = c + dc[i]; // 아기 상어의 다음 열 위치

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // 격자판 범위 초과
						continue;
					}

					if (visited[nR][nC] != 0) { // 방문한 경우
						continue;
					}

					if (map[nR][nC] > level) { // 아기 상어가 먹을 수도 움직일 수도 없는 경우 (물고기가 더 큰 경우)
						continue;
					} else if (map[nR][nC] != 0 && map[nR][nC] < level) { // 가장 먹을 수 있는 먹이인 경우,
						// 한 타임을 탐색하고 검사할 수 있도록 배열에 저장
						availableFishArr[availableFishArrIndex][0] = nR; 
						availableFishArr[availableFishArrIndex++][1] = nC;
					} else { // 먹을 수는 없지만 움직일 수 있는 경우, (크기가 같은 경우)
						queue[++rear][0] = nR; // Queue에 삽입
						queue[rear][1] = nC;
						visited[nR][nC] = time; // 방문한 시간을 표시
					}
				} // end of for(direction)
			} // end of while(1 time)
			
			if (availableFishArrIndex != 0) { // 먹을 수 있는 물고기들이 있는 경우
				ANS = time; // 물고기를 먹은 경우, 정답 갱신
				 // 가장 가까운 물고기의 위치를 저장하기 위한 임시 변수
				int tmpR = Integer.MAX_VALUE;
				int tmpC = Integer.MAX_VALUE; 
				
				for (int j = 0; j < availableFishArrIndex; j++) {
					int rr = availableFishArr[j][0];
					int cc = availableFishArr[j][1];
					
					if (tmpR > rr) { // 더 위쪽인 경우
						tmpR = rr; tmpC = cc; // 임시 변수 갱신
					} else if (tmpR == rr) { // 같은 행(row)인 경우
						if (tmpC > cc) { // 열(column)이 더 왼쪽일 때
							tmpR = rr; tmpC = cc; // 임시 변수 갱신
						}
					}
				}
				
				cntFish[map[tmpR][tmpC]]--; // 먹은 물고기 크기의 수 감소
				map[tmpR][tmpC] = 0; // 먹은 물고기 없애기 
				cntEat++; // 먹은 물고기 증가   
				
				if (cntEat == level && level <= 6) { // level만큼 먹었다면
 					cntEat = 0; // 레벨업 했으므로, 0으로 초기화
					level++; // 레벨업
				}

				// 현재 아기 상어의 크기보다 작은 물고기가 있는지 검사
				for (int j = 1; j < level; j++) { 
					tmp += cntFish[j];
				}
				
				if (tmp == 0) { // 먹을 수 있는 물고기가 없다면,
					break total; // 탐색 종료 (Backtracking)
				}
				// -- 초기화 작업
				front = -1; rear = -1;
				visited = new int[N][N];
				visited[tmpR][tmpC] = time; // 현재 아기 상어 위치에 현재 시간 저장
				queue[++rear][0] = tmpR; // Queue에 현재 아기 상어 위치 저장
				queue[rear][1] = tmpC;
				availableFishArrIndex = 0;
			}
			time++; // 시간 증가
		} // end of while(queue)
		System.out.println(ANS); // 정답 출력 
	} // end of main
} // end of class
