package programmers.level3;

/**
 * 2019-10-05 blog upload
 * */
public class Solution_UP_네트워크 {
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		int answer = solution(n, computers);
		System.out.println(answer);
	} // end of main

	static int[] p, rank; // p : 노드의 부모 인덱스, rank : 같은 집합으로 만들때, 두 노드의 랭크 중 큰 곳으로 붙임 -> 탐색 시간을 줄여줌

	public static int solution(int n, int[][] computers) {
		int answer = n; // 최초에는 n개만큼 네트워크가 존재
		make(n); // p와 rank 배열 생성

		// 각 노드의 연결 정보를 토대로 탐색한다.
		for (int i = 0; i < computers.length; i++) {
			for (int j = i + 1; j < computers[i].length; j++) { // 대각선으로 대칭이므로 i+1부터 탐색해도됨
				if (computers[i][j] == 0) { // 서로 연결돼있지 않다면,
					continue; // 재탐색
				}

				// 서로 연결돼있다면

				int p1 = find(i); // i의 부모를 찾음
				int p2 = find(j); // j의 부모를 찾음

				if (p1 != p2) { // i와 j의 부모가 다르다면, 두 컴퓨터를 같은 집합으로 만들어줌
					union(i, j); // 같은 집합으로 합치기
					answer--; // 같은 네트워크를 공유하므로 answer를 감소시켜줌
				}
			}
		}
		return answer; // 정답 반환
	} // end of func(solution)

	private static void make(int n) {
		p = new int[n]; // 컴퓨터 수만큼 부모 배열 생성
		rank = new int[n]; // 컴퓨터 수만큼 rank 배열 생성
		for (int i = 0; i < p.length; i++) {
			p[i] = i; // 최초에는 자기 자신이 부모
		}
		return;
	} // end of func(make)

	private static int find(int node) {
		if (node == p[node]) { // 현재 노드의 부모가 자기 자신이라면
			return p[node]; // 부모 반환
		} else { // 현재 노드의 부모 노드가 자신이 아니라면, 재귀 호출
			return p[node] = find(p[node]); // Path Compression : 탐색 시간을 줄여줌
		}
	} // end of func(find)

	private static void union(int node1, int node2) {
		int p1 = find(node1); // node1의 부모를 찾음
		int p2 = find(node2); // node2의 부모를 찾음
		
		if (p1 == p2) { // 부모가 같다면,
			return;
		}
		if (rank[p1] < rank[p2]) { // node2 집합의 rank가 더 높다면
			p[p1] = p2; // node1을 node2 집합으로 편입
		} else { // rank가 같거나 작다면, 
			if (rank[p1] == rank[p2]) { // 두 개의 rank가 같을때만 rank를 올려줌
				rank[p1]++; 
			}
			p[p2] = p1; // node2를 node1의 집합으로 편입
		}
	} // end of func(union)
} // end of class
