package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 최적화 Memo
 */

public class Solution_UP_2383_점심식사시간_memo {
	private static ArrayList<Pair> people; // 사람의 위치 정보를 담을 변수
	private static Stair[] stairs; // 계단의 위치와 길이를 담을 변수
	private static int[][] memo; // [n번째 계단][n번째 사람] : n번째 사람이 n번째 계단에 도착할 때의 시간을 메모

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		StringBuilder sb = new StringBuilder(); // 출력 시간을 줄이기 위한 StringBuilder

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 방의 크기
			int[][] map = new int[N][N]; // 격자판 생성
			people = new ArrayList<Pair>(); // 사람의 위치 정보를 담을 변수
			stairs = new Stair[2]; // 계단의 위치와 길이를 담을 변수
			int idx_stair = 0; // 배열에 저장할 때 필요한 index 변수

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) { // 사람일 때
						people.add(new Pair(r, c)); // 사람 정보 List에 추가
					} else if (map[r][c] >= 2) { // 계단일 때
						stairs[idx_stair++] = new Stair(r, c, map[r][c]); // 계단 정보 배열에 추가
					}
				}
			} // end of for(input)

			set = new int[people.size()]; // 사람들이 이용할 계단의 정보를 중복순열로 저장함
			memo = new int[2][people.size()]; // [n번째 계단][n번째 사람] : n번째 사람이 n번째 계단에 도착할 때의 시간을 메모
			ans = Integer.MAX_VALUE; // 정답 변수
			setStairs(0); // 사람들이 이용할 계단을 저장
			sb.append('#').append(tc).append(' ').append(ans).append('\n'); // 한번에 출력하기 위해 StringBuilder에 저장
		} // end of for TestCase
		System.out.print(sb.toString()); // StringBuilder에 저장해놓은 정답을 한번에 출력
	} // end of main

	private static int[] set; // 중복순열을 저장할 배열
	private static int ans; // 정답 변수

	/** DFS를 이용한 중복순열로 사람들이 이용할 계단을 뽑아줌 */
	private static void setStairs(int len) {
		if (len == set.length) { // 사람 인원수 만큼 계단을 설정해줬다면
			int tmp = solve(); // 해당 사건에서 걸리는 값 반환
			ans = (ans > tmp) ? tmp : ans; // 그 중 최소값 저장
			return;
		}
		for (int i = 0; i < 2; i++) {
			set[len] = i;
			setStairs(len + 1); // 재귀 호출
		}
	} // end of func(setStairs)

	/** 중복순열의 데이터로 해당 중복순열 값에서 모든 사람이 내려가는 시간을 반환해줌 */
	private static int solve() {
		ArrayList<Integer> timeFirst = new ArrayList<>(); // 첫번째 계단을 이용하는 사람
		ArrayList<Integer> timeSecond = new ArrayList<>(); // 두번째 계단을 이용하는 사람

		// 각 사람들이 설정된 계단까지 가는 거리를 계산하여 List에 저장
		for (int idx_people = 0; idx_people < set.length; idx_people++) {
			int dTime = 0; // 계단까지 가는 시간
			int stairNum = set[idx_people]; // 현재 사람이 이용하는 계단

			if (memo[stairNum][idx_people] == 0) { // 이전에 저장해놓은 값이 없는 경우
				// 계단까지 가는 시간 계산
				dTime = Math.abs(people.get(idx_people).r - stairs[stairNum].r)
						+ Math.abs(people.get(idx_people).c - stairs[stairNum].c);
				memo[stairNum][idx_people] = dTime; // 추후 사용될 수 있으므로 메모
			} else { // 이전에 메모 돼있는 경우
				dTime = memo[stairNum][idx_people]; // 메모된 시간을 그대로 가져옴
			}

			if (stairNum == 0) { // 첫번째 계단을 이용할 때
				timeFirst.add(dTime); // 첫번째 계단까지 걸리는 시간을 List에 추가
			} else { // 두번째 계단을 이용할 때
				timeSecond.add(dTime); // 두번째 계단까지 걸리는 시간을 List에 추가
			}
		}

		if (timeFirst.size() != 0) {
			Collections.sort(timeFirst); // 각 시간을 기준으로 오름차순 정렬
		}
		if (timeSecond.size() != 0) {
			Collections.sort(timeSecond); // 각 시간을 기준으로 오름차순 정렬
		}

		// 모든 사람이 내려가야하는 경우이므로, 최대값을 구함
		int max_first = Integer.MIN_VALUE; // 첫번째 계단 이용을 완료할 때까지 걸리는 시간
		int max_second = Integer.MIN_VALUE; // 두번째 계단 이용을 완료할 때까지 걸리는 시간

		if (timeFirst.size() > 0) { // 첫번째 계단을 이용하는 사람이 있는 경우
			for (int i = 0; i < timeFirst.size(); i++) { // 첫번째 계단을 사용하는 사람 수 만큼 반복
				if (i < 3) { // 3명까지는 기다리지 않아도 되므로, 계단까지 걸리는 시간과 계단을 내려가는 시간을 더해서 저장
					timeFirst.set(i, timeFirst.get(i) + stairs[0].len + 1);
					continue;
				}

				// 3명 이상부터는 대기해야함
				if (i - 3 >= 0) {
					// 계단까지 가는 시간동안 그 전 사람이 다 내려간다면 기다리지 않아도됨
					if (timeFirst.get(i) >= timeFirst.get(i - 3)) {
						timeFirst.set(i, timeFirst.get(i) + stairs[0].len + 1);
					} else { // 기다리는 경우
						// 계단을 내려가고 있는 사람이 다 내려가는 값과 해당 사람이 계단을 내려가는 값이랑 더해줌
						timeFirst.set(i, timeFirst.get(i - 3) + stairs[0].len);
					}
				}
			}
		}

		// 첫번째 계단 로직과 똑같음
		if (timeSecond.size() > 0) {
			for (int i = 0; i < timeSecond.size(); i++) {
				if (i < 3) {
					timeSecond.set(i, timeSecond.get(i) + stairs[1].len + 1);
					continue;
				}
				if (i - 3 >= 0) {
					if (timeSecond.get(i) >= timeSecond.get(i - 3)) {
						timeSecond.set(i, timeSecond.get(i) + stairs[1].len + 1);
					} else {
						timeSecond.set(i, timeSecond.get(i - 3) + stairs[1].len);
					}
				}
			}
		}

		// 누적 값을 저장했기 때문에 마지막 인덱스 값이 해당 계단을 모두 이용했을 때의 시간임
		if (timeFirst.size() != 0) {
			max_first = timeFirst.get(timeFirst.size() - 1);
		}
		if (timeSecond.size() != 0) {
			max_second = timeSecond.get(timeSecond.size() - 1);
		}
		return Math.max(max_first, max_second); // 모든 사람이 이용을 완료해야하므로, 두 계단 중 가장 큰 값을 반환함
	} // end of func(solve)

	private static class Pair { // 사람의 정보
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair

	private static class Stair { // 계단의 정보
		int r;
		int c;
		int len; // 계단의 길이

		Stair(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	} // end of Stair
} // end of class
