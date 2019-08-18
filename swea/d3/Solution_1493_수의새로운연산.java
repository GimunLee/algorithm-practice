package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_1493_수의새로운연산 {
	static Position[] arr = new Position[50100];
	static boolean check = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			if(!check) {
				fillData();
				check = true;
			}
			
			int n_p = arr[p].x + arr[q].x;
			int n_q = arr[p].y + arr[q].y;

			System.out.print("#" + tc);
			for (int i = 1; i <= 50000; i++) {
				if(arr[i].x == n_p && arr[i].y == n_q) {
					System.out.println(" " + i);
					break;
				}
			}
		} // end of for of TestCase

	} // end of main
	
	private static void fillData() {
		int idx_pq = 1;
		int y;

		for (int range = 1; idx_pq <= 50000; range++) {
			y = range;
			for (int x = 1; x <= range; x++) {
//				System.out.println(x + "," + y);
				arr[idx_pq++] = new Position(x, y);
				y--;
			}
		}
	}

} // end of class
