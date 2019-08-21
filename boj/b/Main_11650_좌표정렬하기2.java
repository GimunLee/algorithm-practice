package boj.b;

import java.util.Scanner;

class Main_11650_좌표정렬하기2 {
	private static Pair input[];
	private static int num;
	
	public static void main(String arg[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		input = new Pair[num];

		for (int i = 0; i < num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			input[i] = new Pair(x, y);
		}

		quickSort(0, num - 1);
		printResult();
		sc.close();
	}
	
	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void quickSort(int first, int last) {
		Pair temp;
		if (first < last) {
			int pivot = first;
			int i = first;
			int j = last;

			while (i < j) {
				while (input[i].x <= input[pivot].x && i < last) {
					if (input[i].x == input[pivot].x) {
						if (input[i].y > input[pivot].y) {
							break;
						}
					}
					i++;
				}
				while (input[j].x >= input[pivot].x) {
					if (input[j].x == input[pivot].x) {
						if (input[j].y <= input[pivot].y) {
							break;
						}
					}
					j--;
				}
				if (i < j) {
					temp = input[i];
					input[i] = input[j];
					input[j] = temp;
				}
			}

			temp = input[pivot];
			input[pivot] = input[j];
			input[j] = temp;

			quickSort(first, j - 1);
			quickSort(j + 1, last);
		}
	}

	static void printResult() {
		for (int i = 0; i < num; ++i) {
			System.out.print(input[i].x + " " + input[i].y);
			System.out.println();
		}
		System.out.println();
	}

}