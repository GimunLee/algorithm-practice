package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1863_Á¾±³ {
	static ArrayList<Integer>[] alist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		alist = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			alist[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			alist[start].add(end);
			alist[end].add(start);
		}

		boolean[] visited = new boolean[N + 1];

		int ans = 0;

		for (int i = 1; i < N + 1; i++) {
			if (!visited[i]) {
				dfs(visited, i);
				ans++;
			}
		}

		System.out.println(ans);
	}

	static void dfs(boolean[] visited, int node) {
		visited[node] = true;

		if (alist[node] == null) {
			return;
		}

		for (int i = 0; i < alist[node].size(); i++) {
			int next = alist[node].get(i);
			if (!visited[next]) {
				dfs(visited, next);
			}
		}
	}
}
