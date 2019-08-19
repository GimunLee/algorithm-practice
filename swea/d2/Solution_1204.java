package swea.d2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_1204 {
	static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int i = 1; i <= tc; i++) {
			int tc_num = sc.nextInt();
			int frequent = 0;
			int index = 0;
			int[] score = new int[101];
			int[] arr = new int[1000];

			for (int j = 0; j < arr.length; j++) {
				arr[j] = sc.nextInt();
				score[arr[j]]++;
			}

			for (int j = score.length - 1; j >= 0; j--) {
				if (frequent < score[j]) {
					frequent = score[j];
					index = j;
				}
			}
			System.out.printf("#%d %d \n", tc_num, index);
		}
	}
}
