package af_graph1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_10451_순열사이클 {

	static boolean[] visited;
	static int answer;

	private static void dfs(ArrayList<Integer>[] edge, int node, int start) {
		visited[node] = true;

		for (int i = 0; i < edge[node].size(); i++) {
			int next = edge[node].get(i);

			if (start == next) {
				answer++;
				return;
			}

			if (!visited[next]) {
				dfs(edge, next, start);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			StringTokenizer temp = new StringTokenizer(br.readLine(), " ");

			ArrayList<Integer>[] edge = new ArrayList[N + 1];
			visited = new boolean[N + 1];
			answer = 0;

			for (int i = 1; i <= N; i++) {
				edge[i] = new ArrayList<Integer>();
				edge[i].add(Integer.parseInt(temp.nextToken()));
			}

			for (int i = 1; i < edge.length; i++) {
				if (!visited[i]) {
					dfs(edge, i, i);
				}
			}

			System.out.println(answer);
		}

	} // end of main

} // end of class
