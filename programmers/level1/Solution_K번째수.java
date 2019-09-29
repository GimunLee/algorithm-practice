package programmers.level1;

import java.util.Arrays;

public class Solution_K¹øÂ°¼ö {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
		int[] answer = solution(array, commands);
		for (int i = 0; i < commands.length; i++) {
			System.out.println(answer[i]);
		}
	}

	private static int[] sortArray;

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		sortArray = new int[array.length];

		for (int i = 0; i < commands.length; i++) {
			for (int j = 0; j < sortArray.length; j++) {
				sortArray[j] = array[j];
			}
			int first = commands[i][0] - 1;
			int last = commands[i][1] - 1;
			quickSort(first, last);
			int k = first + commands[i][2] - 1;
			answer[i] = sortArray[k];
		}
		return answer;
	}

	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}

		int pivot = sortArray[(first + last) / 2];
		int i = first - 1;
		int j = last + 1;

		while (true) {
			while (sortArray[++i] < pivot) {
			}
			while (sortArray[--j] > pivot) {
			}

			if (i >= j) {
				break;
			}

			int tmp = sortArray[i];
			sortArray[i] = sortArray[j];
			sortArray[j] = tmp;
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);
	}
}
