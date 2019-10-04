package programmers.level3;

/** 최소 신장 트리 (크루스칼) */
public class Solution_UP_섬연결하기 {
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		System.out.println(solution(n, costs));
	}

	private static int[] p, rank; // p : 각 노드의 부모 배열, rank : 각 노드의 rank를 저장
	private static Edge[] edgeArray; // 각 연결선을 저장함 (크루스칼)

	public static int solution(int n, int[][] costs) {
		int answer = 0; // 정답 변수
		makeSet(n); // 각 p와 rank를 초기화

		// costs = {start, end, cost}
		edgeArray = new Edge[costs.length]; // 간선들을 저장할 배열 생성

		for (int r = 0; r < costs.length; r++) {
			int start = costs[r][0];
			int end = costs[r][1];
			int cost = costs[r][2];
			edgeArray[r] = new Edge(start, end, cost);
		} // end of for(connection)

		quickSort(0, edgeArray.length - 1); // 퀵소트 함수 연습
		// Arrays.sort(edgeArray); // 라이브러리 사용

		int count = n - 1; // 연결해야될 섬들의 갯수
		for (int i = 0; i < costs.length; i++) {
			Edge edge = edgeArray[i]; // 가장 cost가 작은 간선을 뽑음

			int p1 = find(edge.start); // 그 간선에 연결된 첫 노드의 부모를 찾음
			int p2 = find(edge.end); // 그 간선에 연결된 두번째 노드의 부모를 찾음

			if (p1 == p2) { // 부모가 같다면, 서로 이미 같은 집합이다. -> 최적으로 연결돼있음을 의미함
				continue;
			} else if (p1 != p2) { // 부모가 다르다면,
				union(edge.start, edge.end); // 서로 같은 집합으로 만들기 -> 섬 연결하기
				count--; // 섬을 연결했으므로 count 감소
				answer += edge.cost; // 비용을 answer에 합함
				if (count == 0) { // 모든 선이 연결됐다면, 탐색 종료
					break;
				}
			}
		}
		return answer; // 정답 반환
	} // end of func(solution)

	/** 각 부모 배열과 rank 배열을 생성함 */
	private static void makeSet(int n) {
		p = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i; // 최초에는 자신의 부모는 자신으로 저장
		}
		return;
	} // end of func(makeSet)

	// node의 부모 찾기
	private static int find(int node) {
		if (p[node] == node) {
			return p[node];
		} else {
			return p[node] = find(p[node]); // Path Compression
		}
	} // end of func(find)

	// 두 node를 한 집합으로 합치기
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
		return;
	} // end of func(union)

	/** 정렬 라이브러리를 사용할 때 쓰기 위한 Comparable 구현 */
	private static class Edge implements Comparable<Edge> {
		int start, end, cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	} // end of class(Edge)

	// 퀵정렬 연습
	private static void quickSort(int start, int end) {
		if (start >= end) {
			return;
		}
		int i = start - 1;
		int j = end + 1;
		Edge pivot = edgeArray[(start + end) / 2];
		while (true) {
			while (edgeArray[++i].cost < pivot.cost) {
			}
			while (edgeArray[--j].cost > pivot.cost) {
			}
			if (i >= j) {
				break;
			}
			// swap
			Edge tmp = edgeArray[i];
			edgeArray[i] = edgeArray[j];
			edgeArray[j] = tmp;
		}
		quickSort(start, i - 1);
		quickSort(j + 1, end);
		return;
	} // end of func(quickSort)
} // end of class
