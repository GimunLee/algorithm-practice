package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4408_자기방으로돌아가기_Prof {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] way = new int[201];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");

				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				if (start > end) {
					int temp = start;
					start = end;
					end = temp;
				}

				start = (start % 2 == 0) ? start / 2 - 1 : start / 2;
				end = (end % 2 == 0) ? end / 2 - 1 : end / 2;

				for (int j = start; j <= end; j++) {
					way[j]++;
				}
			}
			Arrays.sort(way);
			System.out.println("#" + tc + " " + way[200]);
		}
	}
}
