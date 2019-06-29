package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_17140_이차원배열과연산 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// A[r][c] = k
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] A = new int[100][100];

		for (int rr = 0; rr < 3; rr++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int cc = 0; cc < 3; cc++) {
				A[rr][cc] = Integer.parseInt(st.nextToken());
			}
		}

		int size_r = 3;
		int size_c = 3;

		for (int time = 0; time <= 100; time++) { // 100시간을 넘어가면 -1

			if (A[r-1][c-1] == k) {
				System.out.println(time);
				return;
			}
			
			int size = Integer.MIN_VALUE;
			int[][] A_tmp;
			if (size_r >= size_c) { // R 연산
				A_tmp = new int[size_r][size_c * 2];
				for (int rr = 0; rr < size_r; rr++) {
					ArrayList<Pair> list = new ArrayList<>();
					int[] chk = new int[101];
					int idx = 1;
					for (int cc = 0; cc < size_c; cc++) {
						int val = A[rr][cc];
						if (val == 0) {
							continue;
						}
						if (chk[val] == 0) { // 처음 들어온 수
							list.add(new Pair(val, 1));
							chk[val] = idx++;
							size = size < (idx - 1) ? idx - 1 : size;
						} else { // 기존에 있는 수
							list.get(chk[val] - 1).cnt++;
						}
					}
					Collections.sort(list); // 개수로 정렬
					int temp_c = 0;
					for (int i = 0; i < list.size(); i++) {
						A_tmp[rr][temp_c++] = list.get(i).val;
						A_tmp[rr][temp_c++] = list.get(i).cnt;
					}
				}
				size_c = size * 2;
			} // end of if(R연산)
			else { // C 연산
				A_tmp = new int[size_r * 2][size_c];
				for (int cc = 0; cc < size_c; cc++) {
					ArrayList<Pair> list = new ArrayList<>();
					int[] chk = new int[101];
					int idx = 1;
					for (int rr = 0; rr < size_r; rr++) {
						int val = A[rr][cc];
						if (val == 0) {
							continue;
						}
						if (chk[val] == 0) { // 처음 들어온 수
							list.add(new Pair(val, 1));
							chk[val] = idx++;
							size = size < (idx - 1) ? idx - 1 : size;
						} else { // 기존에 있는 수
							list.get(chk[val] - 1).cnt++;
						}
					}
					Collections.sort(list); // 개수로 정렬

					int temp_r = 0;
					for (int i = 0; i < list.size(); i++) {
						A_tmp[temp_r++][cc] = list.get(i).val;
						A_tmp[temp_r++][cc] = list.get(i).cnt;
					}
				}
				size_r = size * 2;
			}
			for (int rr = 0; rr < A_tmp.length; rr++) {
				for (int cc = 0; cc < A_tmp[rr].length; cc++) {
					A[rr][cc] = A_tmp[rr][cc];
				}
			}

//			for (int i = 0; i < size_r; i++) {
//				for (int j = 0; j < size_c; j++) {
//					System.out.print(A[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();


		}
		System.out.println("-1");

	} // end of main

	private static class Pair implements Comparable<Pair> {
		int val;
		int cnt;

		Pair(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair o) {
			int tmp = this.cnt - o.cnt;
			if (tmp == 0) {
				tmp = this.val - o.val;
			}
			return tmp;
		}
	} // end of Pair
} // end of class
