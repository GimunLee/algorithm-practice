package programmers.level3;

public class Solution_UP_입국심사 {
	public static void main(String[] args) {
		int n = 6;
		int[] times = { 7, 10 }; // 28
		System.out.println(solution(n, times));
	} // end of main

	private static int[] sortedTimes; // times를 정렬할 배열
	
	public static long solution(int n, int[] times) {
		long answer = 0; // 최초에는 0
		sortedTimes = times; // times를 정렬하기 위해 sortedTimes에 저장
		quickSort(0, times.length - 1); // quickSort 직접 구현 
		// Arrays.sort(times); // 라이브러리 사용
		
		long start = 0; // 처음 start는 0
		long end = Long.MAX_VALUE; // 처음 end는 가장 큰 값으로 설정

		while (true) {
			if (start > end) { // start가 end보다 커진다면, 이진 탐색을 모두 마친 경우
				break;
			}
			
			long mid = (start + end) / 2; // start와 end를 더하고 2로 나눈 값을 정답이라고 생각하여 품

			long sum = 0; // mid만큼의 시간이 주어졌을때, mide를 각 입국 심사대의 시간으로 나눈 수용가능한 인원을 더하기 위한 변수
			for (int i = 0; i < sortedTimes.length; i++) { // 각 시간을 탐색하면서
				sum += mid / sortedTimes[i]; // 해당 mid시간동안 처리할 수 있는 인원을 누적합
				if (sum >= n) { // sum이 이미 n명을 처리할 수 있다면 더 더해볼 필요가 없음
					break;
				}
			}
			if (sum < n) { // 해당 mid 시간에 n명을 처리못한다면
				start = mid + 1; // 시간을 더 키워봄
			} else { // 해당 mid시간에 n명을 처리할 수 있다면,
				end = mid - 1; // 최적의 답을 찾기 위해 값을 더 작게 해봄
				answer = mid; // 정답으로 저장
			}
		}
		return answer; // 정답 반환
	} // end of func(solution)

	/** 연습용 퀵소트 함수 */
	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}

		int pivot = sortedTimes[(first + last) / 2];
		int i = first - 1;
		int j = last + 1;

		while (true) {
			while (sortedTimes[++i] < pivot) {
			}
			while (sortedTimes[--j] > pivot) {
			}

			if (i >= j) {
				break;
			}
			int tmp = sortedTimes[i];
			sortedTimes[i] = sortedTimes[j];
			sortedTimes[j] = tmp;
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);
		return;
	} // end of func(quickSort)
}
