package boj.afgraph1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class UP_Main_2252_줄세우기_dfs {
	private static ArrayList<Integer>[] list; // 노드 간 연결돼있는지, 저장하는 배열 리스트 변수
	private static boolean[] visited; // 줄을 세웠는지 여부를 저장하는 변수 
	private static Stack<Integer> stack; // 후입선출(FILO)을 위한 Stack 변수

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
			if (list[A] == null) { // 해당 리스트 배열이 생성되지않은 경우
				list[A] = new ArrayList<Integer>(); // 리스트 생성
			}
			list[A].add(B); // A의 키 < B의 키
		} // end of for(input)

		for (int i = 1; i <= N; i++) {
			if (list[i] != null && !visited[i]) { // 나보다 큰 사람이 있고, 줄을 세우지 않은 경우 
				dfs(i);
			}
		} // end of for(dfs)
		
		StringBuilder sb = new StringBuilder(); // 시간의 효율을 위해 StringBuilder에 저장했다가 한번에 출력
 		
		for (int i = 1; i <= N; i++) { // 줄을 세우고 남은 인원들을 줄 세우기
			if (!visited[i]) { // 줄을 세우지 않은 경우
				sb.append(i).append(" "); // 줄 세우기
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString()); // 정답 출력
	} // end of main

	private static void dfs(int student) {
		visited[student] = true; // 줄을 세웠음으로 표시

		if (list[student] == null) { // 자기보다 큰 사람이 없으므로 
			stack.add(student); // 줄 세우기
			return;
		}

		for (int i = 0; i < list[student].size(); i++) { // 나보다 큰 사람들을 방문
			int next = list[student].get(i); // 나보다 큰 사람
			if (visited[next]) { // 나보다 큰 사람이 줄 세워진 경우
				continue;
			}
			dfs(next); // 나보다 큰 사람을 재귀호출
		}
		// 위의 반복문이 끝나면, 나보다 큰 사람이 없는 경우
		stack.add(student); // 큰 사람이 없으므로 줄 세우기
	} // end of func(dfs)
} // end of class
