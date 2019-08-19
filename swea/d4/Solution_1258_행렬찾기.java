package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Box implements Comparable<Box>{
	int w;
	int h;
	int size;

	Box(int w, int h, int size) {
		this.w = w;
		this.h = h;
		this.size = size;
	}

	@Override
	public int compareTo(Box arg0) {
		return this.size - arg0.size;
	}
}

public class Solution_1258_행렬찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N*N

			int[][] map = new int[N][N];
			boolean[][] visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			ArrayList<Box> bList = new ArrayList<Box>();

			int tmp_r = 0;
			int tmp_c = 0;

			int tmp_w = 0;
			int tmp_h = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 1. 0이 아닌 수를 만나면 0을 만날때까지 오른쪽으로
					if (map[r][c] != 0 && !visited[r][c]) {
						tmp_r = r;
						tmp_c = c;

						tmp_w = 0;
						tmp_h = 0;

						while (c < N && !visited[r][c] && map[r][c] != 0) {
							tmp_w++;
							c++;
						}
						c--;

						while (r < N && !visited[r][c] && map[r][c] != 0) {
							tmp_h++;
							r++;
						}

						r -= tmp_h;

						bList.add(new Box(tmp_w, tmp_h, tmp_w * tmp_h));

						for (int i = tmp_r; i < tmp_r + tmp_h; i++) {
							for (int j = tmp_c; j < tmp_c + tmp_w; j++) {
								visited[i][j] = true;
							}
						}
					}
				}
			}

			Collections.sort(bList, new Comparator<Box>() {
				@Override
				public int compare(Box o1, Box o2) {
					return o1.h - o2.h;
				}
			});
			
			Collections.sort(bList);
			
			System.out.print("#" + tc + " " + bList.size());
			for (int i = 0; i < bList.size(); i++) {
				System.out.print(" " + bList.get(i).h + " " + bList.get(i).w);
			}
			System.out.println();

		} // end of for of TestCase

	} // end of main
} // end of class
