package programmers.level3;

public class Solution_섬연결하기 {
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };

		System.out.println(solution(n, costs));

	}

	private static int[] p;
	private static int[] rank;

	private static void makeSet(int n) {
		p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
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

	// 집합 합치기
	private static void union(int node1, int node2) {
		int p1 = find(node1);
		int p2 = find(node2);

		if (p1 != p2) { // 다른 부모라면
			if (rank[p1] < rank[p2]) { // p2가 더 depth가 크다면
				p[p1] = p2;
			} else { // p1이 같거나 작다면
				if (rank[p1] == rank[p2]) {
					rank[p1]++;
				}
				p[p2] = p1;
			}
		}
		return;
	} // end of func(union)

	public static int solution(int n, int[][] costs) {
		makeSet(n);
		rank = new int[n];

		int answer = 0;

		// costs = {start, end, cost}
		edgeArray = new Edge[costs.length];

		for (int r = 0; r < costs.length; r++) {
			int start = costs[r][0];
			int end = costs[r][1];
			int cost = costs[r][2];

			edgeArray[r] = new Edge(start, end, cost);
		} // end of for(connection)

		// Arrays.sort(edgeArray);
		quickSort(0, edgeArray.length - 1);

		int count = n - 1;

		for (int i = 0; i < costs.length; i++) {
			Edge edge = edgeArray[i];

			int p1 = find(edge.start);
			int p2 = find(edge.end);

			if (p1 == p2) { // 부모가 같다면, 서로 이미 같은 집합이다.
				continue;
			} else if (p1 != p2) { // 부모가 다르고,
				union(edge.start, edge.end); // 같은 집합으로 만들기
				count--;
				answer += edge.cost;
				if (count == 0) {
					break;
				}
			}
		}
		return answer;
	} // end of func(solution)

	private static Edge[] edgeArray;

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
} // end of class
