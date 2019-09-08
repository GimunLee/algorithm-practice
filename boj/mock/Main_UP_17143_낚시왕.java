package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.08.26.(MON) 
 * 2019.08.26.(MON) Upload
 * */

public class Main_UP_17143_낚시왕 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int R = Integer.parseInt(st.nextToken()); // 격자판의 행(row) 크기
		int C = Integer.parseInt(st.nextToken()); // 격자판의 열(column) 크기
		int M = Integer.parseInt(st.nextToken()); // 상어의 수

		Shark[][] map = new Shark[R + 1][C + 1]; // 격자판 생성 (문제의 좌표는 (1,1) 부터 시작하므로, 하나 더 크게 생성)
		Shark[] beforeArr = new Shark[M + 1]; // 상어가 움직이기 전, 상어를 저장하는 배열 변수
		int beforeArrSize = 0; // 상어의 수

		int nearSharkC = Integer.MAX_VALUE; // 해당 시간에 가장 가까운 상어의 C 좌표를 저장할 변수

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), ", ");
			int r = Integer.parseInt(st.nextToken()); // 상어의 r 좌표
			int c = Integer.parseInt(st.nextToken()); // 상어의 c 좌표
			int s = Integer.parseInt(st.nextToken()); // 상어의 속도
			int d = Integer.parseInt(st.nextToken()); // 상어의 방향 (1:위, 2: 아래, 3:오른쪽, 4:왼쪽)
			int z = Integer.parseInt(st.nextToken()); // 상어의 크기
			Shark shark = new Shark(r, c, s, d, z); // 상어 생성
			map[shark.r][shark.c] = shark; // 격자판에 상어 저장
			beforeArr[beforeArrSize++] = shark; // 상어의 정보를 배열에 별도로 저장
		} // end of for(Input)

		for (int i = 1; i <= R; i++) { // 열이 1일 때 가장 가까운 행 좌표를 저장
			if (map[i][1] != null) {
				nearSharkC = i;
				break;
			}
		}

		int ANS = 0; // 정답 변수

		Shark[] moveArr = new Shark[beforeArrSize + 1]; // 상어가 움직였을때 변한 위치와 방향을 저장할 변수
		int moveArrSize = 0; // 움직인 상어 배열의 인덱스 변수

		// 한 타임마다 이동시키면서 낚시하고, 상어를 주어진 조건에 맞춰 움직임
		for (int time = 1; time <= C; time++) {
			moveArrSize = 0; // 한 타임마다 움직여야하므로 배열 초기화

			if (nearSharkC != Integer.MAX_VALUE) { // nearSharkC의 값이 존재한다면, 그 열에는 상어가 적어도 한마리가 있음
				ANS += map[nearSharkC][time].z; // 가까운 상어의 크기를 더함
				map[nearSharkC][time] = null; // 격자판에서 해당 상어를 제거
				nearSharkC = Integer.MAX_VALUE; // 초기화
			}

			if (time == C) { // 만약 낚시꾼이 끝까지 갔다면, 상어를 움직일 필요가 없음
				break;
			}

			for (int i = 0; i < beforeArrSize; i++) { // 움직이기 전 상어의 배열만큼 탐색
				int r = beforeArr[i].r; // 움직일 상어의 행(row) 좌표
				int c = beforeArr[i].c; // 움직일 상어의 열(column) 좌표
				
				if (map[r][c] != null) { // 상어의 위치가 null이 아니라면 
					Shark shark = map[r][c]; // 상어의 정보를 받아옴
					map[shark.r][shark.c] = null; // 해당 위치를 null로 바꿔줌 (이동할 것이기 때문에)
					
					int tempS = shark.s; // 상어가 이동할 수 있는 칸의 수
					
					while (true) {
						if (tempS == 0) { // 이동할 수 있는 칸의 수를 다 썼다면 break
							break;
						}
						if (shark.d == 1) { // 위
							int remain = shark.r - 1; // 위쪽으로 남은 칸을 계산함
							if (remain >= tempS) { // 위쪽으로 남은 칸이 현재 상어가 방향을 바꾸지 않고, 이동할 수 있는 거리라면 
								shark.r -= tempS; // 그대로 빼줌
								break; // 더이상 볼 필요가 없음
							} else { // 위쪽으로 남은 칸보다 더 많이 남았다면, 방향을 전환해야하는 경우임
								tempS -= remain + 1; // 튕겨져나온 것까지 계산하여 빼줌
								shark.r = 2; // 튕겨져 나온 위치
								shark.d = 2; // 방향 전환
							}
						} else if (shark.d == 2) { // 아래 ('위'의 로직과 동일)
							int remain = R - shark.r;
							if (remain >= tempS) {
								shark.r += tempS;
								break;
							} else { 
								tempS -= remain + 1;
								shark.r = R - 1;
								shark.d = 1;
							}
						} else if (shark.d == 3) { // 오른쪽 ('위'의 로직과 동일)
							int remain = C - shark.c;
							if (remain >= tempS) {
								shark.c += tempS;
								break;
							} else {
								tempS -= remain + 1;
								shark.c = C - 1;
								shark.d = 4; 
							}
						} else if (shark.d == 4) { // 왼쪽 ('위'의 로직과 동일)
							int remain = shark.c - 1;
							if (remain >= tempS) {
								shark.c -= tempS;
								break;
							} else {
								tempS -= remain + 1;
								shark.c = 2;
								shark.d = 3; 
							}
						}
					}
					moveArr[moveArrSize++] = shark; // 최종적인 상어의 위치를 moveArr에 넣어줌
				}
			} // end of for(MoveAllShark)
			
			beforeArrSize = 0; // 배열 초기화 (다시 담아줘야함)
			for (int i = 0; i < moveArrSize; i++) {
				Shark shark = moveArr[i]; // 움직인 상어를 받아옴
				if (map[shark.r][shark.c] == null) { // 해당 위치의 격자판이 비어있다면, 
					map[shark.r][shark.c] = shark; // 상어를 그대로 덮어씀
					beforeArr[beforeArrSize++] = shark; // 상어를 배열에 저장
				} else { // 해당 위치에 상어가 있다면, 
					if (map[shark.r][shark.c].z < shark.z) { // 상어의 크기를 비교함
						map[shark.r][shark.c] = shark; // 상어의 크기가 큰 것을 격자판에 저장
						beforeArr[beforeArrSize++] = shark; // 상어를 배열에 저장
					}
				}
				if (shark.c == time + 1) { // 움직인 상어의 최종 열 위치가 다음 낚시꾼이 탐색할 위치라면
					if (nearSharkC > shark.r) { // 가까운 상어의 위치를 갱신해줌
						nearSharkC = shark.r;
					}
				}
			}
		} // end of for(Time)
		System.out.println(ANS); // 정답 출력
	} // end of main

	private static class Shark {
		int r, c;
		int s; // 상어의 속도
		int d; // 상어의 방향
		int z; // 상어의 크기

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	} // end of Shark
} // end of class
