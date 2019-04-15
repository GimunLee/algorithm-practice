package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Solution_2115_벌꿀채취 {
	private static int N; // 채취할 벌통 크기
	private static int M; // 선택할 수 있는 벌통의 개수
	private static int C; // 일꾼당 꿀을 채취할 수 있는 최대 양
	private static int[][] map; // 입력으로 받는 벌통을 저장할 2차원 정수 배열
	private static boolean[][] isChoice; // 일꾼이 이미 선택했는지 판별하는 변수
	private static ArrayList<Pair> list; // map을 indexing해서 일꾼별 조합을 뽑기 용이하게 함 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 채취할 벌통 크기
			M = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통의 개수
			C = Integer.parseInt(st.nextToken()); // 일꾼당 꿀을 채취할 수 있는 최대 양

			map = new int[N][N];
			list = new ArrayList<>();
			comb_bucket = new int[2][M]; // 각 일꾼이 선택한 벌통의 index를 저장하는 변수입니다.

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					list.add(new Pair(r, c)); // 모든 map을 list에 저장합니다.
				}
			} // end of for input

			isChoice = new boolean[N][N];
			ans = Integer.MIN_VALUE; // 최대 수익을 저장할 변수입니다.
			chooceBucket(0, 0); // idx : 일꾼이 벌통을 고를 인자, len : 일꾼이 벌통을 골랐는지 판별하는 인자

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.println(sb.toString()); // 정답 출력
	} // end of main

	private static int tmp; // 첫번째 일꾼이 꿀을 채취했을 때 최대 수익을 저장하는 변수
	private static int ans; // 두번째 일꾼까지 꿀을 채취 완료했을때 최대 수익을 저장하는 변수
	private static int[][] comb_bucket; // 각 일꾼이 꿀을 채취할 index를 저장하는 변수

	/**
	 * 각 일꾼이 조건에 맞게 꿀을 채취할 통을 고릅니다.
	 * 
	 * @param idx : 일꾼이 뽑을 벌통 인덱스
	 * @param len : 모든 일꿀이 벌통을 다 뽑았는지 판단할 변수 
	 * */
	private static void chooceBucket(int idx, int len) {
		if (len == 2) { // 일꾼 2명 모두 채취할 통을 골랐을 때,
			tmp = 0; // 조합마다 첫번째 일꾼의 최대 수익을 갱신해줍니다.
			
			// 두 일꾼 모두 같은 함수를 사용합니다. 단, 일꿀 별 채취할 통을 인자로 넘깁니다.
			dfs(comb_bucket[0], 0, 0, 0); // 첫번째 일꾼이 꿀을 채취할 통, 채취한 벌통 개수, 채취한 꿀 용량, 최대 수익
			// 첫번째 일꾼의 최대 수익이 나야, 정답이 나올 수 있으므로 두번째 일꾼은 첫번째 일꾼이 일한 최대 수익에서 계산을 시작합니다.
			dfs(comb_bucket[1], 0, 0, tmp); // 두번째 일꾼이 꿀을 채취할 통, 채취한 벌통 개수, 채취한 꿀 용량, 최대 수익
			ans = (ans<tmp)? tmp : ans; // 두 일꾼 모두 채취한 후, 수익을 갱신해줍니다.
			return; 
		}

		here: for (int i = idx; i < N * N; i++) {
			Pair p = list.get(i);
			int r = p.r;
			int c = p.c;
			
			// 가로로 M번째까지 일꾼이 벌통을 선택할 수 있는지 확인합니다.
			for (int j = 0; j < M; j++) {
				if (c + j >= N) { // 범위를 넘어간 경우, 다음 index부터 확인합니다.
					continue here;
				}

				if (isChoice[r][c + j]) { // 이미 다른 일꿀이 선택한 벌통인 경우, 다음 index부터 확인합니다.
					continue here;
				}
			}
			
			// 조건에 부합하므로, 해당 index를 isChoice 배열을 채워줍니다.
			for (int j = 0; j < M; j++) {
				comb_bucket[len][j] = i + j;
				isChoice[r][c + j] = true;
			}
			
			// 성공한 경우, M이후 부터 idx를 탐색해도 되므로 i+M을 넘겨주고, 한 일꿀이 일할 벌통을 골랐으므로 len+1 해줍니다. 
			chooceBucket(i + M, len + 1); 
			
			// 원상복귀 : 이후 최적의 해가 나올 수 있으므로, 다시 원상복귀 해줍니다.
			for (int j = 0; j < M; j++) {
				isChoice[r][c + j] = false;
			}
		}
	} // end of chooceBucket()

	
	/**
	 * 한 일꾼이 채취할 벌통에서 조건에 맞게 꿀을 채취하며 최대 수익을 계산합니다.
	 * 
	 *  @param each_bucket : 일꾼이 채취할 벌통 배열
	 *  @param idx : 일꾼이 채취할 벌통의 인덱스
	 *  @param honey : 일꾼이 현재까지 채취한 꿀
	 *  @param benefit : 일꾼이 꿀을 채취했을때의 수익
	 * */
	private static void dfs(int[] each_bucket, int idx, int honey, int benefit) {
		if (tmp < benefit) { // 기존에 저장되어있는 수익보다 현재 수익이 크다면 갱신해줍니다.
			tmp = benefit;
		}
		if (idx == M) { // 일꾼이 벌통을 끝까지 채취했을때,
			return;
		}

		int r = list.get(each_bucket[idx]).r; // 현재 일할 벌통의 r 좌표를 가져옵니다.
		int c = list.get(each_bucket[idx]).c; // 현재 일할 벌통의 c 좌표를 가져옵니다.

		if (map[r][c] > C || honey + map[r][c] > C) { // 일꾼의 꿀통 용량이 넘쳤을때,
			return;
		}

		dfs(each_bucket, idx + 1, honey + map[r][c], benefit + (int) Math.pow(map[r][c], 2)); // 꿀을 채취한 경우,
		dfs(each_bucket, idx + 1, honey, benefit); // 꿀을 채취하지 않은 경우,
	} // end of dfs()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
