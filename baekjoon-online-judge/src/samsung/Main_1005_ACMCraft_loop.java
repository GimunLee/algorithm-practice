package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모이제이션 쓰기
 */

public class Main_1005_ACMCraft_loop {
	private static int[] D;
	private static ArrayList<Integer>[] list;
	private static int[] indegree;
	private static int W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 건물 개수
			int K = Integer.parseInt(st.nextToken()); // 건물 순서 규칙

			D = new int[N + 1]; // 건물 짓는 시간
			D[0] = Integer.MAX_VALUE; // 사용 안함
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken()); // 건물 짓는 시간
			}

			list = new ArrayList[N + 1];
			indegree = new int[N + 1]; // 들어오는 간선

			// 건물 짓는 순서 규칙
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				if (list[s] == null) {
					list[s] = new ArrayList<Integer>();
				}
				list[s].add(e);
				indegree[e]++;
			}
			W = Integer.parseInt(br.readLine().trim()); // 건물 번호
			// -- end of input

			Queue<Integer> queue = new LinkedList<>();

			for (int i = 0; i < indegree.length; i++) {
				if (indegree[i] == 0) {
					queue.add(i);
				}
			}

			int[] tmp_D = new int[N + 1];

			while (!queue.isEmpty()) {
				int cur = queue.poll();

				if (list[cur] == null) { // 정답이 아니고 다음으로 지어야하는 건물이 없는 경우,
					continue;
				}

				for (int j = 0; j < list[cur].size(); j++) {
					int next = list[cur].get(j); // 다음 지어야하는 건물

					if (--indegree[next] == 0) {
						queue.add(next);
					}

					// tmp_D : 들어오는 간선 중 가장 큰 값

					tmp_D[next] = tmp_D[next] < D[cur] ? D[cur] : tmp_D[next];
					
					if (indegree[next] == 0) { // 들어오는 간선에 대한 처리가 끝났을시,
						D[next] = tmp_D[next] + D[next];
					}
				}
			}
			sb.append(D[W]).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main()
} // end of class
