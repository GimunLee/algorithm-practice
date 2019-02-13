package af_graph1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_11724 {
	static LinkedList<Integer>[] a;
	static boolean[] check;

	static void dfs(int node) {
		check[node] = true;

		if(a[node] == null) {
			return;
		}
		
		for (int i = 0; i < a[node].size(); i++) {
			int next = a[node].get(i);
			if (!check[next]) {
				dfs(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수

		a = new LinkedList[1001];
		check = new boolean[1001];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (a[start] == null) {
				a[start] = new LinkedList<Integer>();
			}

			if (a[end] == null) {
				a[end] = new LinkedList<Integer>();
			}

			a[start].add(end); // 간선 저장
			a[end].add(start); // 간선 저장
		}

		int Answer = 0;

		for (int i = 1; i <= n; i++) {
			if (!check[i]) {
				dfs(i);
				Answer++;
			}
		}

		System.out.println(Answer);
	} // end of main
} // end of class
