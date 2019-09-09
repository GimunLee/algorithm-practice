package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1005_ACMCraft {
	private static int[] D; // 각 건물의 건설 시간을 저장할 배열
	private static ArrayList<Integer>[] list; // 인접 노드 리스트 ( list[1]에 저장된 리스트는 '1'을 짓고 지을 수 있는 건물을 저장하고 있음) 
	private static int[] indegree; // 각 건물별 들어오는 간선의 갯수를 저장할 배열
	private static int W; // 건설해야하는 건물의 번호

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // 정답을 한번에 출력하기 위한 변수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 건물 개수
			int K = Integer.parseInt(st.nextToken()); // 건물 순서 규칙

			D = new int[N + 1]; // 해당 건물을 짓는데 걸리는 시간을 저장할 변수 생성
			D[0] = Integer.MAX_VALUE; // 0은 사용하지 않음
			
			st = new StringTokenizer(br.readLine(), " "); 
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken()); 
			}

			list = new ArrayList[N + 1]; // 인접 노드 리스트 생성
			indegree = new int[N + 1]; // 들어오는 간선의 수를 저장할 변수 생성

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken()); 
				int e = Integer.parseInt(st.nextToken()); // s를 지어야 e를 지을 수 있음
				if (list[s] == null) {
					list[s] = new ArrayList<Integer>();
				}
				list[s].add(e); // s를 짓기 전에 지어야하는 e를 리스트에 저장
				indegree[e]++; // e의 간선의 수를 증가시켜줌
			}
			W = Integer.parseInt(br.readLine().trim()); // 최종적으로 지어야하는 건물 번호
			// -- end of input

			Queue<Integer> queue = new LinkedList<>(); // BFS로 건물을 탐색하기 위한 Queue

			for (int i = 0; i < indegree.length; i++) {
				if (indegree[i] == 0) { // 들어오는 간선이 없는 것이 시작점
					queue.add(i); 
				}
			}

			int[] tmp_D = new int[N + 1]; // 들어오는 간선 중 가장 큰 값을 임시로 저장할 변수

			while (!queue.isEmpty()) { // 간선이 없는 건물부터 짓기 시작
				int cur = queue.poll(); // 현재 탐색할 건물의 번호

				if (list[cur] == null) { // 해당 건물 다음으로 지어야하는 건물이 없는 경우,
					continue;
				}

				for (int j = 0; j < list[cur].size(); j++) {
					int next = list[cur].get(j); // 다음 지어야하는 건물

					 // 해당 건물을 지었으므로, 다음으로 지어야하는 건물의 들어오는 간선을 줄여줌
					if (--indegree[next] == 0) { // 줄였을때, 0이라면 지을 수 있는 건물이므로 Queue에 삽입
						queue.add(next); 
					}

					// 다음 건물을 짓기 위해 걸리는 최대 시간을 갱신
					tmp_D[next] = tmp_D[next] < D[cur] ? D[cur] : tmp_D[next]; 

					if (indegree[next] == 0) { // 다음 건물이 지을 수 있는 상태라면, (들어오는 간선이 없다면)
						// 다음 건물을 짓는데 걸리는 시간과 다음 건물을 짓기 위해 지어야 했던 건물들의 총 시간을 더해서 저장
						D[next] = tmp_D[next] + D[next]; // 따라서 D[next]에는 next의 건물 짓기를 완료할 때까지의 시간이 저장됨
					}
				}
			} // end of while(Queue)
			sb.append(D[W]).append("\n"); // D[W]에는 W 건물을 짓기 위한 시간이 저장돼있으므로, 배열을 참조하여 출력
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main()
} // end of class
