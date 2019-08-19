package swea.d6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_1263_사람네트워크2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());

			long[][] map = new long[N + 1][N + 1];

			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (value != 0) {
						map[i][j] = 1;
					} else if (i == j) {
						map[i][j] = 0;
					} else {
						map[i][j] = Integer.MAX_VALUE;
					}
				}
			}

			for (int k = 1; k < map.length; k++) {
				for (int i = 1; i < map.length; i++) {
					if (k == i) {
						continue;
					}
					for (int j = 1; j < map.length; j++) {
						if (j == k && j == i) {
							continue;
						}
						map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
					}
				}
			}
			ArrayList<Long> list = new ArrayList<Long>();
			for (int i = 1; i < map.length; i++) {
				long sum = 0;
				for (int j = 1; j < map.length; j++) {
					sum += map[i][j];
				}
				list.add(sum);
			}
			Collections.sort(list);
			System.out.println("#" + tc +" " + list.get(0));
		}
	}
}
