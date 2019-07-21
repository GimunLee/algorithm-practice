package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모이제이션 쓰기
 * */

public class Main_1005_ACMCraft {
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
			int time = 0;
			Queue<Integer> queue = new LinkedList<>();

			int tmp_time = Integer.MIN_VALUE;
			here: while (true) {
				for (int i = 1; i <= N; i++) {
					if (indegree[i] == 0) { // 0이면 시작
						if (i == W) { // 정답인 경우
							sb.append(time).append("\n");
							break here;
						}

						if (list[i] == null) {
							D[i] = 0;
							continue;
						}
						
						// 시간 더하기 전에 어떤 짓을 해야함
						if(D[i] > tmp_time) {
							time += D[i]; // 건물 짓기
						}

						for (int j = 0; j < list[i].size(); j++) {
							int next = list[i].get(j); // 다음 지어야하는 건물
							indegree[next] -= 1; // 들어오는 간선 줄이기

							// 다음 지어야하는 건물들 중, 최대 시간 (동시 건설 가능)
							if (tmp_time < D[next]) {
								tmp_time = D[next];
							}
						}
					}
				}
			}
			System.out.println(sb.toString());
		} // end of for(TestCase)
	} // end of main()
} // end of class
