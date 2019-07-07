package af_graph1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 예정_Main_10026_적록색약_DFS {
	static char[][] map;
	static boolean[][] visited;
	static int ans_normal;
	static int ans_special;
	static int[][] direction= {{-1,0},{1,0},{0,-1},{0,1}}; // 상 하 좌 우
	
	private static boolean inRange(int nr, int nc) {
		if(nr < 0 || nc <0 || nr >= map.length || nc >= map.length) {
			return false;
		}
		return true;
	}
	
	private static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int i = 0; i < direction.length; i++) {
			int nr = r + direction[i][0];
			int nc = c + direction[i][1];
			
			if(!inRange(nr,nc) || visited[nr][nc] || map[r][c] != map[nr][nc]) {
				continue;
			}
			
			visited[nr][nc] = true;
			dfs(nr,nc);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // N x N 행렬

		map = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// 1. 일반인 탐색
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(!visited[i][j]) {
					dfs(i,j);
					ans_normal++;
				}
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				visited[i][j] = false;
				if(map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}
		
		// 2. 색맹 탐색
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(!visited[i][j]) {
					dfs(i,j);
					ans_special++;
				}
			}
		}

		System.out.println(ans_normal + " " + ans_special);

	} // end of main
} // end of class
