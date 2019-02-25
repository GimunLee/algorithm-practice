package af_graph1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10451_순열사이클_BJ {
	static boolean[] visited;
	static int[] a;

	private static void dfs(int node) {
		if(visited[node]) {
			return;
		}
		visited[node] = true;
		dfs(a[node]);
		

	} // end of dfs

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			int N = Integer.parseInt(br.readLine().trim());

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			a = new int[N + 1];
			visited = new boolean[N + 1];
			int ans = 0;

			for (int i = 1; i < a.length; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i < a.length; i++) {
				if (!visited[i]) {
					dfs(a[i]);
					ans++;
				}
			}
			System.out.println(ans);
		}
	} // end of main
} // end of class
