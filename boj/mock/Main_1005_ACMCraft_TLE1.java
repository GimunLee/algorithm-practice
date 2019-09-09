package boj.mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간 초과
 */

public class Main_1005_ACMCraft_TLE1 {
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
			boolean[] visited = new boolean[N + 1];

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
			int[] tmp_D = new int[N + 1];

			here: while (true) {
				for (int i = 1; i <= N; i++) {
					if (indegree[i] == 0) { // 0이면 시작
						if (i == W) { // 정답인 경우
							break here;
						}

						if (list[i] == null) { // 정답이 아니고 다음으로 지어야하는 건물이 없는 경우,
							continue;
						}

						for (int j = 0; j < list[i].size(); j++) {
							int next = list[i].get(j); // 다음 지어야하는 건물
							indegree[next] -= 1; // 들어오는 간선 줄이기
							// tmp_D : 들어오는 간선 중 가장 큰 값

							if(visited[next]) {
								continue;
							}
							
							tmp_D[next] = tmp_D[next] < D[i] ? D[i] : tmp_D[next];
							if (indegree[next] == 0) { // 들어오는 간선에 대한 처리가 끝났을시,
								D[next] = tmp_D[next] + D[next];
								visited[next] = true;
								if (next == W) { // 해당 건물을 만났을 경우
									break here;
								}
							}
						}
					}
				}
			}
			sb.append(D[W]).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main()
} // end of class
