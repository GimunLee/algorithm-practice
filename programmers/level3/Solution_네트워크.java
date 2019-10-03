package programmers.level3;

import java.util.Arrays;

public class Solution_네트워크 {
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

		int answer = solution(n, computers);
		System.out.println(answer);
	}

	static int[] p, rank;

	public static int solution(int n, int[][] computers) {
		int answer = n;
		make(n);

		boolean[] visited = new boolean[n];

		for (int i = 0; i < computers.length; i++) {
			for (int j = i + 1; j < computers[i].length; j++) {
				if (computers[i][j] == 0) {
					continue;
				}
				int p1 = find(i);
				int p2 = find(j);

				if (p1 != p2) {
					union(i, j);
					answer--;
				}
			}
		}
		return answer;
	}

	private static void make(int n) {
		p = new int[n];
		rank = new int[n];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
		return;
	}

	private static int find(int node) {
		if (node == p[node]) {
			return p[node];
		} else {
			return p[node] = find(p[node]);
		}
	}

	private static void union(int node1, int node2) {
		int p1 = find(node1);
		int p2 = find(node2);
		if (p1 == p2) {
			return;
		}
		if (rank[p1] < rank[p2]) {
			p[p1] = p2;
		} else {
			if (rank[p1] == rank[p2]) {
				rank[p1]++;
			}
			p[p2] = p1;
		}
	}
}
