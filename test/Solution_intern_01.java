package test;

import java.util.Arrays;

public class Solution_intern_01 {
	public static void main(String[] args) {
		int[][] land = { { 1, 4, 8, 10 }, { 5, 5, 5, 5 }, { 10, 10, 10, 10 }, { 10, 10, 10, 20 } };
		int height = 3;

		System.out.println(solution(land, height));
	}

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int height;
	private static int[][] map, group, edge;
	private static boolean[][] visited;

	public static int solution(int[][] land, int theight) {
		int answer = 0;
		map = land;
		visited = new boolean[land.length][land[0].length];
		group = new int[land.length][land[0].length];
		height = theight;

		int idx = 0;
		for (int r = 0; r < land.length; r++) {
			for (int c = 0; c < land[0].length; c++) {
				if (!visited[r][c]) {
					dfs(idx, r, c);
					idx++;
				}
			}
		}
		edge = new int[idx][idx];
		bfs();
		// mst
		return kruscal();
	}

	private static int kruscal() {
		Edge[] weight = new Edge[edge.length];
		for (int r = 0; r < edge.length; r++) {
			for (int c = 0; c < edge[0].length; c++) {
				if (edge[r][c] != 0) {
					weight[r] = new Edge(r, c, edge[r][c]);
				}
			}
		}
		Arrays.sort(weight);
		p = new int[edge.length];
		rank = new int[edge.length];

		for (int i = 0; i < edge.length; i++) {
			p[i] = i; // 최초엔 자기 자신이 부모
		}
		int ANSER = 0;
		int cnt = edge.length - 1;
		for (int i = 0; i < weight.length; i++) {
			Edge edge = weight[i];
			int node1 = edge.s;
			int node2 = edge.e;

			int p1 = find(node1);
			int p2 = find(node2);
			if (p1 != p2) {
				union(p1, p2);
				ANSER += edge.c;
				if (--cnt == 0) {
					return ANSER;
				}
			}
		}
		return ANSER;
	}

	private static int[] p, rank;

	/** 속한 그룹 찾기 */
	private static int find(int num) {
		if (p[num] == num) {
			return p[num];
		} else {
			return p[num] = find(p[num]);
		}
	}

	private static void union(int num1, int num2) {
		int p1 = find(num1);
		int p2 = find(num2);
		if (p1 == p2) {
			return;
		}
		if (rank[p1] < rank[p2]) {
			p[p1] = p2;
		} else {
			if (rank[p2] == rank[p1]) {
				rank[p1]++;
			}
			p[p2] = p1;
		}
		return;
	}

	private static class Edge implements Comparable<Edge> {
		int s, e, c;

		public Edge(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
		}
	}

	public static void dfs(int idx, int r, int c) {
		visited[r][c] = true;
		group[r][c] = idx;

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			if (!inRange(nR, nC)) {
				continue;
			}

			if (visited[nR][nC]) {
				continue;
			}

			if (Math.abs(map[r][c] - map[nR][nC]) <= height) {
				dfs(idx, nR, nC);
			}
		}
	}

	public static void bfs() {
		int[][] queue = new int[10000][2];
		int front = -1, rear = -1;
		visited = new boolean[map.length][map[0].length];
		queue[++rear][0] = 0;
		queue[rear][1] = 0;

		while (front != rear) {
			int r = queue[++front][0];
			int c = queue[front][1];
			int curGroup = group[r][c];
			visited[r][c] = true;

			for (int i = 0; i < dr.length; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					continue;
				}

				if (visited[nR][nC]) {
					continue;
				}

				queue[++rear][0] = nR;
				queue[rear][1] = nC;

				int nextGroup = group[nR][nC];
				if (curGroup != nextGroup) {
					int sub = Math.abs(map[nR][nC] - map[r][c]);
					if (edge[curGroup][nextGroup] == 0) {
						edge[curGroup][nextGroup] = Integer.MAX_VALUE;
						edge[nextGroup][curGroup] = Integer.MAX_VALUE;
					}
					int value = Math.min(edge[curGroup][nextGroup], sub);
					edge[curGroup][nextGroup] = value;
					edge[nextGroup][curGroup] = value;
				}
			}
		}
	}

	private static boolean inRange(int r, int c) {
		if (r >= map.length || c >= map[0].length || r < 0 || c < 0) {
			return false;
		}
		return true;
	}
}
