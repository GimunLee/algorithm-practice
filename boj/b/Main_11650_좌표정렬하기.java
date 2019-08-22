package boj.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** Remind */
public class Main_11650_��ǥ�����ϱ� {
	private static int N;
	private static Pair[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 100,000
		input = new Pair[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			input[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		} // end of for(input)
//		quickSort(0, N - 1);
		sort(input,0,N-1);
		
		System.out.println(Arrays.toString(input));

	} // end of main

	private static void quickSort(int first, int last) {

		if (first >= last) {
			return;
		}

		int pivot = (first + last) / 2;
		Pair pivotValue = input[pivot];
		int i = first;
		int j = last;

		while (i < j) { // Partition
			System.out.println(i + ", " + j);
			System.out.println(Arrays.toString(input));
			while (input[i].x <= pivotValue.x) {
				i++;
			}
			while (input[j].x > pivotValue.x) {
				j--;
			}

			if (i <= j) {
				Pair temp = input[i];
				input[i] = input[j];
				input[j] = temp;
				i++;
				j--;
			}
		} // end of Partition
		pivot = i;
		// i�� ����
		quickSort(first, pivot - 1);
		quickSort(pivot + 1, last);
	}
	
	public static void sort(Pair[] data, int l, int r) {
        int left = l;
        int right = r;
        Pair pivot = data[(l + r) / 2]; // pivot ��� ���� (�־��� ��� ����)
 
        do {
            while (data[left].x < pivot.x)
                left++;
            while (data[right].x > pivot.x)
                right--;
 
            if (left <= right) {
                System.out.println("change");
                Pair temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
            System.out.println(Arrays.toString(data)+"  "+pivot);
            System.out.println("left : " + left + " right : " + right);
        } while (left <= right);
 
        if (l < right) {//���������� �� �̻� ���� ���� ���� ��� üũ (���� ������ �� �ִ��� üũ)
            System.out.println("l : " + l + " end: " + right);
            sort(data, l, right);
        }
        if (r > left) {//���������� �� �̻� ���� ���� ���� ��� üũ (������ ������ �� �ִ��� üũ)
            System.out.println("l : " + left + " end: " + r);
            sort(data, left, r);
 
        }
    }

	private static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}

	} // end of Pair
} // end of class
