package programmers.level3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_가장먼노드 {
	public static void main(String[] args) {
		int n = 6;
		int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		int answer = solution(n, edge);
		System.out.println(answer);
	} // end of main

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		ArrayList<Integer>[] edgeList = new ArrayList[n + 1];
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> que = new LinkedList<>();

		for (int i = 0; i < edge.length; i++) {
			if (edgeList[edge[i][0]] == null) {
				edgeList[edge[i][0]] = new ArrayList<Integer>();
			}
			if (edgeList[edge[i][1]] == null) {
				edgeList[edge[i][1]] = new ArrayList<Integer>();
			}

			edgeList[edge[i][0]].add(edge[i][1]);
			edgeList[edge[i][1]].add(edge[i][0]);

			if (edge[i][0] == 1 || edge[i][1] == 1) {
				que.add(1);
			}
		}
		visited[1] = true;
		int preCnt = 0;
		while (!que.isEmpty()) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				int node = que.poll();
				preCnt++;
				for (int j = 0; j < edgeList[node].size(); j++) {
					if (!visited[edgeList[node].get(j)]) {
						visited[edgeList[node].get(j)] = true;
						que.add(edgeList[node].get(j));
					}
				}
			}
			if (que.size() == 0) {
				answer = preCnt;
				break;
			}
			preCnt = 0;
		}
		return answer;
	} // end of func(solution)
}
