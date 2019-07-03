package af_graph1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2252_줄세우기_queue {
	private static ArrayList<Integer>[] list;
	private static boolean[] visited;
	private static int[] indegree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 키 비교횟수

		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		indegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (list[A] == null) {
				list[A] = new ArrayList<Integer>();
			}
			list[A].add(B);
			indegree[B]++;
		}

		StringBuilder sb = new StringBuilder();

		int cnt = N;

		while (cnt != 0) {
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0 && !visited[i]) {
					visited[i] = true;
					sb.append(i).append(" ");
					cnt--;
					// 연결된 간선 체크
					if (list[i] == null) {
						continue;
					} else {
						for (int j = 0; j < list[i].size(); j++) {
							indegree[list[i].get(j)]--;
						}
					}
				}
			}
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
