package programmers.level2;

import java.util.Arrays;

public class Solution_H_Index {
	public static void main(String[] args) {
		int[] citations = { 3, 0, 6, 1, 5 };
		System.out.println(solution(citations));

	}

	static int[] sorteCitations;

	public static int solution(int[] citations) {
		int answer = 0;
		sorteCitations = citations;
		quickSort(0, citations.length - 1);
//		Arrays.sort(citations);

		int start = 0;
		int end = citations[citations.length - 1];
		while (true) {
			if (start > end) {
				break;
			}
			int hIdx = (start + end) / 2;

			int up = 0;
			int down = 0;
			for (int i = citations.length - 1; i >= 0; i--) {
				if (citations[i] >= hIdx) {
					up++;
					continue;
				}
				if (citations[i] <= hIdx) {
					down++;
				}
			}

			if (up >= hIdx && down <= hIdx) { // 되는 경우
				answer = hIdx;
				start = hIdx + 1;
			} else {
				end = hIdx - 1;
			}
		}
		return answer;
	}

	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}

		int i = first - 1;
		int j = last + 1;
		int pivotValue = sorteCitations[(first + last) / 2];

		while (true) {
			while (sorteCitations[++i] < pivotValue) {
			}
			while (sorteCitations[--j] > pivotValue) {
			}

			if (i >= j) {
				break;
			}

			int tmp = sorteCitations[i];
			sorteCitations[i] = sorteCitations[j];
			sorteCitations[j] = tmp;
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);
	}
}
