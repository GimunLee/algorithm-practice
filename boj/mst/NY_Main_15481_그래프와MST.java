package boj.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시간 줄이기 생각
 * */
public class NY_Main_15481_그래프와MST {
	private static int N, M;
	private static int[] p, rank;
	private static Edge[] connectionArray;
	private static Edge[] connectionSortedArray;
	private static long[] answerArray;

	// memo
	private static Edge[] queue;
	private static int front, rear;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 정점의 수
		M = Integer.parseInt(st.nextToken()); // 간선의 수

		connectionArray = new Edge[M];
		connectionSortedArray = new Edge[M];
		answerArray = new long[M];
		queue = new Edge[M + 1];
		int idx = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			connectionArray[i] = new Edge(idx, u - 1, v - 1, w);
			connectionSortedArray[i] = new Edge(idx++, u - 1, v - 1, w);
		} // end of for(input)

		// solution
		// quickSort(0, M - 1);
		makeSet();
		Arrays.sort(connectionSortedArray);
		for (int i = 0; i < connectionArray.length; i++) {
			long answer = 0;
			if (answerArray[connectionArray[i].idx] != 0) {
				answer = answerArray[connectionArray[i].idx];
			} else {
				answer = solution(connectionArray[i]);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	/**
	 * @param edge : 반드시 포함해야하는 Edge
	 */
	private static long solution(Edge fixedEdge) {
		long answer = fixedEdge.w;

		union(find(fixedEdge.u), find(fixedEdge.v));

		front = -1;
		rear = -1; // 큐 초기화
		queue[++rear] = fixedEdge;
		// 고정 엣지 고정
		int remainNodeCnt = N - 2; // 최대 연결 노드와 고정 노드를 제외

		for (int i = 0; i < connectionSortedArray.length; i++) {
			Edge edge = connectionSortedArray[i];
			int p1 = find(edge.u);
			int p2 = find(edge.v);
			if (p1 == p2) {
				continue;
			} else if (p1 != p2) {
				answer += edge.w;
				if(answerArray[edge.idx] == 0 || answer > answerArray[edge.idx]) {
					queue[++rear] = edge;
				}
				union(edge.u, edge.v);
				remainNodeCnt--;
				if (remainNodeCnt == 0) {
					break;
				}
			}
		}

		while (front != rear) {
			Edge edge = queue[++front];
			p[edge.u] = edge.u;
			p[edge.v] = edge.v;
			if (answerArray[edge.idx] != 0) {
				answerArray[edge.idx] = Math.min(answerArray[edge.idx], answer);
//				answerArray[edge.idx] = answer;
				continue;
			}
		}

		return answer;
	}

	private static void makeSet() {
		p = new int[N];
		rank = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
		return;
	}

	private static int find(int node) {
		if (p[node] == node) {
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
		} else if (p1 != p2) {
			if (rank[p1] < rank[p2]) {
				p[p1] = p2;
			} else {
				p[p2] = p1;
				if (rank[p1] == rank[p2]) {
					rank[p1]++;
				}
			}
		}
		return;
	}

	private static void print() {
		for (int i = 0; i < connectionSortedArray.length; i++) {
			System.out.print(connectionSortedArray[i].w + " ");
		}
		System.out.println();
	}

	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}

		int i = first - 1;
		int j = last + 1;
		Edge pivot = connectionSortedArray[(first + last) / 2];

		while (true) {
			while (connectionSortedArray[++i].w < pivot.w) {
			}
			while (connectionSortedArray[--j].w > pivot.w) {
			}
			if (i >= j) {
				break;
			}

			Edge tmp = connectionSortedArray[i];
			connectionSortedArray[i] = connectionSortedArray[j];
			connectionSortedArray[j] = tmp;
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);

	}

	private static class Edge implements Comparable<Edge> {
		int idx, u, v, w;

		public Edge(int idx, int u, int v, int w) {
			this.idx = idx;
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}
