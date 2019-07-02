package af_graph1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2252_줄세우기 {
	private static boolean[] visited;
	private static Stack<Integer> stack;
	private static ArrayList<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 키 비교횟수

		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		stack = new Stack<Integer>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (list[A] == null) {
				list[A] = new ArrayList<Integer>();
			}
			list[A].add(B);
		}

		ArrayList<Integer> tlist = new ArrayList<Integer>();

		for (int i = 1; i <= N; i++) {
			if (list[i] != null && !visited[i]) { // 나가는 간선있을 때
				dfs(i);
			}
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				sb.append(i).append(" ");
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString());
	} // end of main

	private static void dfs(int student) {
		visited[student] = true;

		if (list[student] == null) {
			stack.add(student);
			return;
		}

		for (int i = 0; i < list[student].size(); i++) {
			int next = list[student].get(i);
			if (visited[next]) {
				continue;
			}
			dfs(next);

		}
		stack.add(student);
	} // end of func(dfs)
} // end of class
