package programmers.level3;

public class Solution_UP_예산 {
	public static void main(String[] args) {
		int[] input = { 130, 130, 130, 130 };
		int M = 485;
		int answer = solution(input, M);
		System.out.println(answer);

	}

	public static int solution(int[] budgets, int M) {
		int answer = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < budgets.length; i++) {
			max = Math.max(budgets[i], max);
		} // 최댓값 뽑기

		int start = 0; // 처음 start를 0으로 둠
		int end = max; // 처음 end를 요청한 예산 중 가장 큰 값으로 저장

		// 이진 탐색 시작
		while (true) {
			if (start > end) { // start가 end를 넘어간다면, 탐색 종료
				break;
			}

			int mid = (start + end) / 2; // 배정할 예산을 정해줌

			long tmpSum = 0; // mid대로 예산을 정해줬을때, 조건에 부합하는지 확인하기 위한 합 변수
			for (int i = 0; i < budgets.length; i++) {
				if (budgets[i] > mid) { // 요청한 예산이 설정한 예산보다 큰 경우, 
					tmpSum += mid; // 설정한 예산으로 더해줌
				} else { // 요청한 예산으로 가능할 때,
					tmpSum += budgets[i]; // 요청한 예산을 더해줌
				}
			}

			if (tmpSum > M) { // 주어진 M보다 크다면, 더 적게 예산을 배정함
				end = mid - 1;
			} else { // 주어진 M보다 작다면, 더 많이 예산을 배정함
				start = mid + 1;
				answer = Math.max(answer, mid); // 정답 갱신
			}
		}
		return answer; // 정답 반환
	} // end of func(solution)
}
