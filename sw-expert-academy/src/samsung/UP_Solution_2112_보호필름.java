package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Solution_2112_보호필름 {
	private static int D; // 보호 필름의 두께 (3<= D <=13)
	private static int W; // 보호 필름의 가로 크기 (1<= D <=20)
	private static int K; // 합격 기준 (1<= K <=D)

	private static int[][] map; // 보호 필름
	private static int[][] tmp_map; // 보호 필름의 원본 데이터를 저장해둘 변수

	private static int[] set; // 약품을 투입할 행(row)를 중복 순열로 뽑아 저장할 변수
	private static int change; // 약품을 처리해야하는 행(row)의 개수 (백트랙킹을 위한 변수)
	private static int ans; // 정답 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력 시간을 아끼기 위해 정답을 저장해둘 변수
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // 보호 필름의 두께 (3<= D <=13)
			W = Integer.parseInt(st.nextToken()); // 보호 필름의 가로 크기 (1<= W <=20)
			K = Integer.parseInt(st.nextToken()); // 합격 기준 (1<= K <=D)

			map = new int[D][W]; // 보호 필름 변수 생성
			tmp_map = new int[D][W]; // 원본을 저장할 보호 필름 변수 생성
			set = new int[D]; // 약품을 투입할 행(row)를 중복 순열로 뽑아 저장할 변수

			// 0 : A, 1 : B
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					tmp_map[r][c] = map[r][c]; // 원본 데이터 저장
				}
			} // end of for(input)

			ans = Integer.MAX_VALUE; // 정답 변수 : 최솟값을 구해야하므로 가장 큰 값으로 초기화

			// 약품을 투여하지 않고 조건에 만족한다면, 정답 0 저장 후 재반복
			if (checkFilm()) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}

			change = 0;
			permu(0); // 약품을 투여할 행(row)를 중복 순열로 뽑아준다.
			sb.append("#").append(tc).append(" ").append(ans).append("\n"); // 정답 저장
		} // end of for(TestCase)
		System.out.println(sb.toString()); // 정답 출력
	} // end of main

	/**
	 * 현재 보호 필름이 조건에 부합하는지 검사 후 boolean 값 반환
	 */
	private static boolean checkFilm() {

		for (int c = 0; c < W; c++) { // 열(column) 단위로 반복함
			int r = 0;
			boolean flag = false;

			while (r < D) { // 두께만큼 반복
				int each_cnt_A = 0; // 한 열(column)에서 A가 연속되는지 판별할 변수
				int each_cnt_B = 0; // 한 열(column)에서 B가 연속되는지 판별할 변수

				// 연속되는 A 검사
				while (r < D && map[r][c] == 0) { // 행(row)가 D보다 작고, A인 동안 반복
					each_cnt_A++; // 연속되기 때문에 해당 변수를 증가시켜줌
					r++;
				}
				if (each_cnt_A >= K) { // 반복문이 끝났을 때, 연속된 A의 수가 조건에 부합하면
					flag = true;
					break; // break 하는 이유는 한 행(row)에서 하나만 만족해도 되기 때문에
				}

				// 연속되는 B 검사
				while (r < D && map[r][c] == 1) {
					each_cnt_B++; // 연속되기 때문에 해당 변수를 증가시켜줌
					r++;
				}
				if (each_cnt_B >= K) { // 반복문이 끝났을 때, 연속된 A의 수가 조건에 부합하면
					flag = true;
					break;
				}
			}
			if (!flag) {
				return false; // 다음을 볼 필요가 없음
			}
		} // end of for(each column check)
		return true;
	} // end of func(checkFilm)

	/**
	 * 약품을 투여할 행(row)를 고름 ex) set = { 0 , 1 , 0 , 2 , 2 } 일 때, 1번째 행은 A로 3,4번째 행은 B로
	 * 약품 투여 len : 현재까지 뽑은 중복 순열의 길이
	 */
	private static void permu(int len) {
		if (len == D) { // 중복 순열을 모두 뽑았을 때,
			if (change >= ans) { // 이미 현재 저장된 정답보다 약품을 처리해야하는 행(row) 개수가 많을때, 검사하지 않는다. (백트랙킹)
				return;
			}

			// 약품 투여해서 Type 바꾸기
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					int changeType = set[i] - 1; // 문제상에서는 0 : A, 1 : B 이므로 하나씩 빼줘서 바꾸기
					for (int j = 0; j < W; j++) { // 열(column)만큼 반복
						map[i][j] = changeType;
					}
				}
			}

			// 약품을 바꾼 후, 검사해보기
			if (checkFilm()) { // 답이 나온 경우, 정답을 갱신해줌
				ans = change < ans ? change : ans;
			}

			// 다른 경우를 확인해야하므로, 보호 필름의 상태를 원상복귀시킴
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					for (int j = 0; j < W; j++) {
						map[i][j] = tmp_map[i][j];
					}
				}
			}
			return;
		}

		// 0 : 약품 투여 안함, 1 : A를 투여함, 2 : B를 투여함
		for (int i = 0; i < 3; i++) {
			if (i != 0 && set[len] == 0) { // 해당 행(row)에 약품을 투여해야 하고, 해당 행(row)가 약품을 처리하지 않았던 경우 
				change++; // 약품을 처리해야줘야하는 행(row) 개수를 증가시켜줌
			}
			if (i == 0 && set[len] != 0) { // 해당 행(row)에 약품을 투여하지 않고, 해당 행(row)가 약품을 처리했던 경우
				change--; // 약품을 처리해야줘야하는 행(row) 개수를 감소시켜줌
			}

			set[len] = i; // 중복 순열 저장
			permu(len + 1); // 재귀 호출
		}
	}
} // end of class
