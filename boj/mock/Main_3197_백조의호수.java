package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3197_백조의호수 {
	static final int[] dr = { -1, 1, 0, 0 }; // 빙하와 새를 탐색할 때 쓰일 방향 배열 (상하)
	static final int[] dc = { 0, 0, -1, 1 }; // 빙하와 새를 탐색할 때 쓰일 방향 배열 (좌우)
	private static char[][] map; // 맵의 원본 상태를 저장할 배열
	private static int R, C; // R : 세로 제한, C : 가로 제한
	private static int maxMeltTime; // 빙하가 모두 녹는데 걸리는 최대 시간
	private static int[] anotherBird = { 0, 2, 1 }; // 백조끼리 만나는지 확인할 때 쓰일 변수
	private static ArrayList<Pair> birdList; // 백조의 최초 위치를 저장해놓을 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 맵의 세로 제한
		C = Integer.parseInt(st.nextToken()); // 맵의 가로 제한
		birdList = new ArrayList<Pair>(); // 백조의 최초 위치를 저장하기 위한 변수 생성
		map = new char[R][C]; // 맵 생성
		Queue<Pair> waterQueue = new LinkedList<>(); // 물을 저장해놓고, 빙하를 녹일 때 사용하는 큐
		int idx = 1; // 백조의 인덱스

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == '.') { // 물일 때,
					waterQueue.add(new Pair(r, c));
				}
				if (map[r][c] == 'L') { // 백조일 때,
					birdList.add(new Pair(r, c, idx++)); // 백조의 최초 위치를 저장
					waterQueue.add(new Pair(r, c)); // 백조가 있는 곳도 물이므로, waterQueue에 저장
				}
			}
		} // end of for(Input)

		maxMeltTime = Integer.MIN_VALUE; // 빙하가 녹는데 걸리는 최대 시간을 구하기 위해, 가장 작은 값을 저장해놓음
		int[][] iceMeltMap = checkIceMeltTime(waterQueue); // 빙하가 녹는 시간을 BFS 탐색을 통해 구하여 저장
		ANSER = Integer.MAX_VALUE; // 정답 변수 초기화
		binarySearch(iceMeltMap, 0, maxMeltTime); // iceMeltMap을 이분 탐색을 통해 백조가 만나는 최소 시간 구하기
		System.out.println(ANSER); // 정답 출력
	} // end of main

	private static int ANSER; // 정답 변수

	/** 빙하가 녹는 시간을 구하여 iceMeltMap에 저장할 수 있도록 반환하는 함수 */
	private static int[][] checkIceMeltTime(Queue<Pair> waterQueue) {
		int[][] iceMeltMap = new int[R][C]; // 반환할 맵 변수
		boolean[][] visited = new boolean[R][C]; // 방문했는지 확인하는 변수
		int time = 1; // 빙하가 녹는 시간은 최초에 1

		while (!waterQueue.isEmpty()) { // 모든 물을 탐색할 때까지
			
			int size = waterQueue.size(); // 우선 한 time동안 탐색

			for (int i = 0; i < size; i++) {
				Pair pair = waterQueue.poll(); // 현재 물의 정보를 뺌
				int r = pair.r;
				int c = pair.c;
				visited[r][c] = true; // 현재 위치 방문 표시
				
				for (int dir = 0; dir < dr.length; dir++) { // 물의 상하좌우를 탐색
					int nR = r + dr[dir];
					int nC = c + dc[dir];

					if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // 맵을 벗어난다면
						continue;
					}

					if (visited[nR][nC]) { // 이미 방문한 곳이라면
						continue;
					}

					if (map[nR][nC] == 'X') { // 다음 위치가 빙하라면
						iceMeltMap[nR][nC] = time; // iceMeltMap에 시간을 녹는 시간을 저장
						waterQueue.add(new Pair(nR, nC)); // 녹였으므로, waterQueue에 저장
						maxMeltTime = maxMeltTime < time ? time : maxMeltTime; // 최대 녹는 시간을 저장하기 위해 
						visited[nR][nC] = true; // 다음 위치는 탐색했으므로 방문 표시
					}
				}
			} // end of for(1 Time)
			time++; // 시간 증가
		} // end of while(queue)
		return iceMeltMap; // 빙하가 녹는 시간을 담은 맵을 반환
	} // end of func(checkIceMeltTime)

	/** 백조가 N초에 만나지 못한다면, N초 전에는 절대 만날 수 없음을 이용함 */
	private static void binarySearch(int[][] iceMeltMap, int start, int end) {
		if (start > end) { //모두 탐색했다면,
			return;
		}

		int mid = (start + end) / 2; // 중간값을 뽑음

		Queue<Pair> birdQueue = new LinkedList<>(); // 백조를 움직여볼 Queue를 생성
		for (int i = 0; i < 2; i++) { // 백조의 최초 위치를 Queue에 넣음
			Pair pair = birdList.get(i);
			birdQueue.add(new Pair(pair.r, pair.c, pair.birdIndex));
		}
		
		boolean flag = goBird(iceMeltMap, birdQueue, mid); // 해당 mid시간에 백조가 만날 수 있는지 보내본다.
		
		if (flag) { // 만약 만났다면, 
			ANSER = ANSER > mid ? mid : ANSER; // 만날 수 있는 시간 중 최소 시간을 저장
		}
		if (flag) { // 만났다 하더라도, 그 전에 만날 수 도 있으므로 더 작게 보내봄
			binarySearch(iceMeltMap, start, mid - 1);
		} else { // 못 만났으므로, 시간을 더 늘려서 보내봄
			binarySearch(iceMeltMap, mid + 1, end);
		}
	}

	/** 백조를 보내보고 만난다면 true, 못 만난다면 false 반환 */
	private static boolean goBird(int[][] iceMeltMap, Queue<Pair> birdQueue, int mid) {
		int[][] visited = new int[R][C]; // 몇번째 백조가 방문한 곳인지 체크하기 위해 int 형으로 생성

		while (!birdQueue.isEmpty()) { // 백조가 이동할 수 있을때 까지
			Pair pair = birdQueue.poll(); // 현재 백조의 정보를 뻄
			int r = pair.r; 
			int c = pair.c;
			int birdIndex = pair.birdIndex; // 몇번째 백조인지 

			visited[r][c] = birdIndex; // 해당 백조가 방문했음을 표시

			for (int dir = 0; dir < dr.length; dir++) { // 상하좌우로 탐색
				int nR = r + dr[dir];
				int nC = c + dc[dir];

				if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // 맵을 벗어난 경우
					continue;
				}

				if (visited[nR][nC] == birdIndex) { // 이미 해당 백조가 방문한 곳이라면,
					continue;
				}

				if (visited[nR][nC] == anotherBird[birdIndex]) { // 다음 위치에 다른 백조가 방문한 경우
					return true; // 두 백조는 만났으므로 true 반환
				}

				if (mid >= iceMeltMap[nR][nC]) { // 빙하가 녹는 시간보다 현재 시간이 크거나 같은 경우
					birdQueue.add(new Pair(nR, nC, birdIndex)); // 백조는 이동할 수 있으므로 Queue에 저장
					visited[nR][nC] = birdIndex; // 그 위치는 방문했으므로, 방문 표시
				}
			}
		} // end of while(Queue)
		// Queue를 모두 탐색할 동안 true가 반환되지 않았다면, 두 백조는 해당 시간에 만날 수 없으므로 false 반환
		return false; 
	}

	private static class Pair {
		int r, c, birdIndex;

		public Pair(int r, int c, int birdIndex) {
			this.r = r;
			this.c = c;
			this.birdIndex = birdIndex;
		}

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
