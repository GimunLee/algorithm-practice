package boj.afgraph1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Main_2252_줄세우기_queue {
	private static ArrayList<Integer>[] list; // 노드 간 연결돼있는지, 저장하는 배열 리스트 변수
	private static boolean[] visited; // 줄을 세웠는지 여부를 저장하는 변수 
	private static int[] indegree; // 해당 사람보다 키가 작은 사람의 수 (들어오는 간선)

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
			if (list[A] == null) { // 해당 리스트 배열이 생성되지않은 경우
				list[A] = new ArrayList<Integer>(); // 리스트 생성
			}
			list[A].add(B); // A의 키 < B의 키
			indegree[B]++; // B한테 들어오는 간선 증가
		} // end of for(input)

		StringBuilder sb = new StringBuilder(); // 시간의 효율을 위해 StringBuilder에 저장했다가 한번에 출력
		int cnt = N; // 줄을 세우지 않은 남은 사람 수 

		while (cnt != 0) { // 줄을 세우지 않은 사람이 있으면 반복
			for (int i = 1; i <= N; i++) { // 첫번째 사람부터 탐색하면서
				if (indegree[i] == 0 && !visited[i]) { // 나보다 작은 사람이 없고, 줄을 세우지 않았다면
					visited[i] = true; // 줄을 세웠음을 표시
					sb.append(i).append(" "); // 줄 세우기
					cnt--; // 줄을 세웠으므로, 남은 사람 수를 감소

					if (list[i] == null) { // 연결된 간선이 없는 경우
						continue;
					} else { // 연결된 간선이 있는 경우
						for (int j = 0; j < list[i].size(); j++) {
							indegree[list[i].get(j)]--; // 해당 인원은 줄을 세웠으므로, 연결된 사람들의 들어오는 간선을 줄여줌
						}
					}
				}
			} 
		} // end of while(solve)
		System.out.println(sb.toString()); // 정답 출력
	} // end of main
} // end of class
