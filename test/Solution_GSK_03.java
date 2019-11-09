package test;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_GSK_03 {
	public static void main(String[] args) {
//		int[] input = { 58, 70, 54, 85, 99, 125, 100, 75, 76, 80, 88, 75 };
//		int weight = 300;
//		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 9999, 10000 };
//		int weight = 10001;
//		int[] input = { 1, 1, 3, 3 };
//		int weight = 4;

		Scanner sc = new Scanner(System.in);
		int weight = sc.nextInt();
		int count = sc.nextInt();
		int[] input = new int[count];
		for (int i = 0; i < input.length; i++) {
			input[i] = sc.nextInt();
			if (input[i] > weight) {
				System.out.println("-1");
				return;
			}
		}

		System.out.println(solution(input, weight));

	} // end of main

	public static int solution(int[] input, int weight) {
		int answer = 0;
		Arrays.sort(input);

		int right = input.length - 1;
		int left = 0;

		// left가 right 넘어갈 때 그만!
		while (left <= right) {
			// 기본값 저장
			int sum = input[right];

			// 같을 때 제외
			for (int l = left; l < right; l++) {
				if (sum + input[l] > weight) {
					break;
				}
				// left는 항상 증가
				sum += input[l];
				left++;
			}

			// right 줄이기.
			right--;
			answer++;
		}

		return answer;
	}

} // end of class
