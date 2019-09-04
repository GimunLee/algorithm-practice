package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_UP_15684_사다리조작 {
	private static int N, H, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로선의 수 (Column)
		M = Integer.parseInt(st.nextToken()); // 가로선의 갯수
		H = Integer.parseInt(st.nextToken()); // 가로선의 수 (Row)

		int[][] map = new int[H + 1][N + 1]; // 사다리를 저장할 맵 변수 (0은 사용하지 않음)

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 가로선의 위치
			int b = Integer.parseInt(st.nextToken()); // 세로선 b와 b+1이 연결돼있음
			map[a][b] = 1; // 사다리가 연결돼있음을 표시
		} // end of for(Input)

		if (H == 0) { // 가로선이 없다면, 이미 정답인 경우임
			System.out.println(0); // 0을 출력 후 종료
			return;
		}

		ANSER = Integer.MAX_VALUE; // 정답 변수
		solve(map, 1, 0); // solve(맵, 뽑을 가로선의 인덱스, 뽑은 가로선의 갯수)

		if (ANSER == Integer.MAX_VALUE || ANSER > 3) { // 정답이 나올 수 없는 경우이거나, 가로선의 갯수가 3을 넘으면
			System.out.println("-1"); // 불가능함을 출력
		} else { // 정답이 있다면
			System.out.println(ANSER); // 정답 출력
		}
	} // end of main

	private static int ANSER; // 정답 변수

	/** map을 받아서 정답인 경우인지 체크하는 함수 */
	private static boolean check(int[][] map) {
		// for문으로 안되는 경우를 체크한다.
		for (int c = 1; c < N; c++) { // 각 세로선을 검사
			int r = 1; // 가로선의 위치 -> 각 세로선마다 항상 최상단부터 시작
			int moveC = c; // 세로선을 가로선의 상태에 따라 움직여볼 변수

			while (true) {
				if (map[r][moveC - 1] == 1) { // 왼쪽으로 갈 수 있다면
					moveC--; // 왼쪽으로 보내기
				} else if (map[r][moveC] == 1) { // 오른쪽으로 갈 수 있다면
					moveC++; // 오른쪽으로 보내기
				}
				// 그 외에는 0인 경우이므로 아래로 이동
				if (++r == H + 1) { // 만약 최하단까지 도착했다면, while 탈출
					break;
				}
			} // end of while

			if (moveC != c) { // 최하단까지 왔을때, 움직여본 moveC가 현재 시작한 c와 같지 않다면
				return false; // 정답이 될 수 없음
			}
		} // end of for

		return true; // for문에서 return이 일어나지 않았다면, 무조건 정답인 경우이므로 true 반환
	} // end of fucn(check)

	/** chooseC와 chooseC+1 사이에 조건이 된다면 모든 가로 사다리를 설치해본다. (chooseC에서 chooseC+1로 가는 길) */
	private static void solve(int[][] map, int chooseC, int cnt) {
		if (cnt > 3 || chooseC == N) { // 설치한 가로선의 수가 3을 넘거나, 모든 세로선을 탐색한 경우
			return;
		}

		if (check(map)) { // 하나의 가로선을 설치하면, 정답인지 확인해봄
			ANSER = ANSER > cnt ? cnt : ANSER; // 정답인 경우라면 정답 갱신
			return;
		}

		// 현재 세로선 기준 모든 우측 가로선을 설치해봄
		for (int r = 1; r <= H; r++) {
			// 가로선은 연속될 수 없으므로, 현재 설치할 세로선에서 3경우(현재 세로선, 앞 세로선, 뒤 세로선)에 사다리가 설치돼있는지 확인한다.  
			if (map[r][chooseC - 1] != 1 && map[r][chooseC] != 1 && map[r][chooseC + 1] != 1) { 
				map[r][chooseC] = 1; // 가능하다면, 가로선을 설치한다.
				solve(map, chooseC, cnt + 1); // 현재 세로선에서 갯수만 추가해서 보낸다.
				map[r][chooseC] = 0; // 최적의 해가 나올 수 있으므로, 재귀 return시, 설치한 가로선을 다시 해제한다.
			}
		}
		// for문을 나왔다는 것은 한 세로선에 대해 모든 가로선을 보았다는 의미이다. 따라서 다음 세로선을 탐색하도록 한다.
		solve(map, chooseC + 1, cnt);
	}

	/** 디버깅용 print 함수 */
	private static void print(int[][] map) {
		System.out.println();
		for (int r = 1; r < H + 1; r++) {
			for (int c = 1; c < N; c++) {
				System.out.print(" | ");
				System.out.print(map[r][c]);
				if (c == N - 1) {
					System.out.print(" | ");
				}
			}
			System.out.println();
		}
	} // end of func(print)
} // end of class
