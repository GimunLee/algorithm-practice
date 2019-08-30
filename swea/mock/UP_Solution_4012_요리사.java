package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2019.08.27.(화)
 * 2019.08.27.(화) Upload
 */
public class UP_Solution_4012_요리사 {
	private static int N; // 음식의 정보가 담겨있는 맵의 행(row)과 열(column) 제한 변수
	private static int[][] map; // 음식의 정보를 저장할 배열

	private static int[] aSet; // a 음식의 식재료 인덱스를 저장할 배열 (조합)
	private static int[] bSet; // b 음식의 식재료 인덱스를 저장할 배열 (조합)
	private static boolean[] visited; // a 음식의 식재료로 뽑혔는지 저장하는 배열

	private static int[] combSet; // aSet과 bSet의 인덱스를 2개씩 뽑아 음식의 맛을 결정할 때 참고할 배열 (조합)
	private static int value; // a,b 각 음식의 맛을 저장할 임시 변수

	private static int ANS; // 정답 변수

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // 정답을 한번에 출력하기 위한 StringBuilder
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase 수

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // 행(row)와 열(column) 제한 수
			map = new int[N + 1][N + 1]; // 맵 생성 (문제의 식재료 맵과 꼴이 같게 하기 위해 N+1로 생성)

			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 1; c <= N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // end of for(Input)

			aSet = new int[N / 2]; // a 음식의 식재료 인덱스를 저장할 배열 생성
			bSet = new int[N / 2]; // b 음식의 식재료 인덱스를 저장할 배열 생성
			visited = new boolean[N + 1]; // a 음식의 식재료로 뽑혔는지 저장하는 배열 생성
			ANS = Integer.MAX_VALUE; // 정답 변수 초기화
			combSet = new int[2]; // 각 음식의 맛을 결정할 때 쓰일 배열 생성

			solve(0, 1); // 우선 a 음식의 식재료 인덱스와 b 음식의 식재료 인덱스를 뽑음

			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	/**
	 * a 음식을 만들때 쓰일 N/2만큼 인덱스를 뽑고, 나머지를 b 음식의 인덱스로 쓴다. len : 현재까지 뽑은 인덱스 수 idx :
	 * 조합에서 앞으로 뽑아야하는 수의 인덱스
	 */
	private static void solve(int len, int idx) {
		if (len == N / 2) { // 총 식재료의 절반만큼 뽑았으면,
			int tmpIdx = 0; // b 음식을 저장하기 위한 임시 인덱스 변수
			for (int i = 1; i < visited.length; i++) {
				if (!visited[i]) { // visited의 값이 false라면 b 음식에서 사용할 수 있음
					bSet[tmpIdx++] = i;
				}
			}
			value = 0; // a 음식의 맛을 저장하기 위한 변수
			comb(aSet, 0, 0); // a 음식의 전체 인덱스 중에 2개씩 조합을 만듬
			int a = value; // a 음식의 맛을 저장
			value = 0; // b 음식의 맛을 저장하기 위한 변수
			comb(bSet, 0, 0); // b 음식의 전체 인덱스 중에 2개씩 조합을 만듬
			int b = value; // b 음식의 맛을 저장
			int tmp = Math.abs(a - b); // 최종적인 맛을 계산
			ANS = ANS > tmp ? tmp : ANS; // 정답과 비교하여 작은 수를 갱신
			return;
		}

		for (int i = idx; i <= N; i++) {
			aSet[len] = i; // 인덱스를 a 음식 조합 배열에 저장
			if (aSet[0] != 1) { // a 음식과 b 음식의 맛과 b 음식과 a 음식의 맛을 계산하는 것은 똑같으므로, 그것을 걸러주기 위한 처리
				return;
			}
			visited[i] = true; // a 음식에 쓰였으므로, b 음식에는 사용하지 못함
			solve(len + 1, i + 1); // 재귀 호출
			visited[i] = false;
		}
	} // end of func(solve)

	/**
	 * a 음식의 식재료들 중 2개씩 뽑아, 맛을 계산한다. ex) aSet = 1,2,3 -> (1,2) (2,3) (1,3)
	 * inputSolveSet : 각 음식에 쓰이는 식재료 조합 배열 len : 현재까지 뽑은 조합의 수 idx : 앞으로 뽑아야하는 조합의
	 * 인덱스
	 */
	private static void comb(int[] inputSolveSet, int len, int idx) {
		if (len == 2) { // 2개만큼 뽑았다면
			value += map[combSet[0]][combSet[1]] + map[combSet[1]][combSet[0]]; // 맛 저장
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			combSet[len] = inputSolveSet[i];
			comb(inputSolveSet, len + 1, i + 1);
		}
	} // end of func(comb)
} // end of class
