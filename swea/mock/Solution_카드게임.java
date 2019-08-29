package swea.mock;

import java.util.Arrays;

public class Solution_카드게임 {
	private static int[] input = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private static int[] input2;
	private static int N = input.length;

	public static void main(String[] args) {
		input2 = new int[N];

		for (int i = 0; i < input.length; i++) {
			if (i < N / 2) {
				input2[i] = input[N / 2 + i];
			} else {
				input2[i] = input[i - N / 2];
			}
		}

		System.out.println(Arrays.toString(input));
		for (int i = 0; i < N; i++) {
			int[] output = suffle(i); // x 받기
			System.out.println("x = " + i + " | " + Arrays.toString(output));
		}
	}

	private static int[] suffle(int x) {
		int[] array = new int[N];
		if (x == 0) {
			array = input;
		} else if (x == N - 1) {
			array = input2;
		} else if (x <= N / 2) {
			int leftIdx = N / 2 - x;
			int rightIdx = N / 2;
			for (int i = 0; i < input.length; i++) {
				if (i >= (N / 2 - x) && i < (N / 2 + x)) { // 영향을 받는 구역
					array[i] = input[rightIdx++];
					array[i + 1] = input[leftIdx++];
					i++;
				} else {
					array[i] = input[i];
				}
			}
		} else if (x < N - 1) {
			x -= N / 2;
			int leftIdx = N / 2 - x;
			int rightIdx = N / 2;
			for (int i = 0; i < input.length; i++) {
				if (i >= (N / 2 - x) && i < (N / 2 + x)) { // 영향을 받는 구역
					array[i] = input2[rightIdx++];
					array[i + 1] = input2[leftIdx++];
					i++;
				} else {
					array[i] = input2[i];
				}
			}
		}
		return array;
	} // end of func(suffle)
}
