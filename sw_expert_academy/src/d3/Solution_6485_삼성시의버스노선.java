package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6485_삼성시의버스노선 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());

			int[] way = new int[5001];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				for (int j = A; j <= B; j++) {
					way[j]++;
				}
			}

			int P = Integer.parseInt(br.readLine().trim());

			System.out.print("#" + tc + " ");
			for (int i = 0; i < P; i++) {
				int C = Integer.parseInt(br.readLine().trim());
				System.out.print(way[C] + " ");
			}
			System.out.println();
		}
	}
}
