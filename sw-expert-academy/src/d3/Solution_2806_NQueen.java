package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2806_NQueen {
	static int[][] map;
	static int[][] visited;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ¼ö

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N*N°ú ÄýÀÇ ¼ö
			ans = 0;

			map = new int[N][N];
			visited = new int[N][N];
			
			for (int i = 0; i < args.length; i++) {
				for (int j = 0; j < args.length; j++) {
					dfs(i, j);
				}
			}

			System.out.println("#" + tc + " ");
		}
	}

	public static void dfs(int r, int c) {
		while(true) {
			
			
			
		}
		
	}

}
