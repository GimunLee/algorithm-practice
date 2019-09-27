package boj.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main_11650_좌표정렬하기_middle_quicksort {
	private static int N;
	private static int[][] array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			array[i][0] = x;
			array[i][1] = y;
		}

		quickSort(0, N - 1);

		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(array[r]));
		}
	} // end of main

	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}
		int mid = (first + last) / 2;
		int pivot_x = array[mid][0];
		int pivot_y = array[mid][1];

		int i = first - 1;
		int j = last + 1;

		while (true) {
			while (array[++i][0] < pivot_x
					|| ((array[i][0] == pivot_x) && array[i][1] < pivot_y)) {
			}
			while (array[--j][0] > pivot_x || ((array[j][0] == pivot_x) && array[j][1] > pivot_y)) {
			}

			if (i >= j) {
				break;
			}

			int tmp_x = array[i][0];
			int tmp_y = array[i][1];

			array[i][0] = array[j][0];
			array[i][1] = array[j][1];

			array[j][0] = tmp_x;
			array[j][1] = tmp_y;
		}

		quickSort(first, i - 1);
		quickSort(j + 1, last);
		return;
	}

} // end of Class