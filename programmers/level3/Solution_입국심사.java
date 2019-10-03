package programmers.level3;

import java.util.Arrays;

public class Solution_입국심사 {
	public static void main(String[] args) {
		int n = 6;
		int[] times = { 8, 7 }; // 28

		System.out.println(solution(n, times));
	}

	public static long solution(int n, int[] times) {
		long answer = 0;
		sortedTimes = times;
		quickSort(0, times.length - 1);
		System.out.println(Arrays.toString(sortedTimes));

		return answer;
	}

	private static int[] sortedTimes;

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
	}
}
