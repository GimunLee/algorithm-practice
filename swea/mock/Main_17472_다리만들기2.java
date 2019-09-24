package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Not use Libarary Kruscal (24%) why... 물일때만 length를 증가시켰는데 틀림... 뭐가 다른거지?
 */
public class Main_17472_다리만들기2 {
	private static int N, M;
	private static int[][] map;

	private static int[][] connection;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		visited = new boolean[N][M];
		queue = new int[N * M + 1][3]; // 0 : r, 1 : c, 2 : idx
		front = -1;
		rear = -1; // queue 초기화

		int idx = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					findSideBydfs(idx, r, c);
					idx++;
				}
			}
		} // end of for(findSide)

		// 외곽에서 출발하며 다리 세우기 (정보 저장)
		connection = new int[idx][idx]; // 0은 안씀, list에 중복으로 저장 안하기 위함
		edgeList = new ArrayList<>();
		bridgeIdx = new int[N][M];
		setBridge();

		// mst 시작
		for (int r = 1; r < idx; r++) {
			for (int c = r + 1; c < idx; c++) {
				if (connection[r][c] != 0) {
					edgeList.add(new Edge(r, c, connection[r][c]));
				}
			}
		}
		quickSort(0, edgeList.size() - 1);
//		Collections.sort(edgeList);
		makeSet(idx);
		int answer = mst(idx - 1);
		System.out.println(answer);

	} // end of main

	private static int mst(int islandCnt) {
		int answer = 0;
		int remainIslandCnt = islandCnt - 1;

//		debug();

		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			int p1 = find(edge.start);
			int p2 = find(edge.end);

			if (p1 == p2) {
				continue;
			} else {
				union(p1, p2);
				answer += edge.weight;
				remainIslandCnt--;
				if (remainIslandCnt == 0) {
					break;
				}
			}
		}

		if (remainIslandCnt != 0) {
			answer = -1;
		}
		return answer;
	}

	private static ArrayList<Edge> edgeList;
	private static int[][] bridgeIdx;

	private static void setBridge() {
		while (front != rear) {
			int r = queue[++front][0];
			int c = queue[front][1];
			int idx = queue[front][2];

			for (int i = 0; i < dir.length; i++) {
				int nR = r + dir[i][0];
				int nC = c + dir[i][1];

				if (!isRange(nR, nC)) {
					continue;
				}

				if (map[nR][nC] == 0) { // 물인 경우 그 방향으로 쭉 보냄
					int length = 1;
					bridgeIdx[nR][nC] = idx;
					int nR2 = nR;
					int nC2 = nC;
					while (true) {
						nR2 += dir[i][0];
						nC2 += dir[i][1];

						if (!isRange(nR2, nC2)) {
							break;
						}

						if (map[nR2][nC2] != 0 && map[nR2][nC2] != idx) { // 다른 섬을 만난 경우
							if (length >= 2) {
								if (connection[idx][map[nR2][nC2]] == 0) {
									connection[idx][map[nR2][nC2]] = length;
									connection[map[nR2][nC2]][idx] = length;
								} else {
									int minLength = Math.min(length, connection[idx][map[nR2][nC2]]);
									connection[idx][map[nR2][nC2]] = minLength;
								}
							}
							break;
						}
						/**
						 * if(map[nR2][nC2] == 0){ // 물일 때만 보냈는데 틀림; length++; bridgeIdx[nR2][nC2] =
						 * idx; }
						 */
						if (map[nR2][nC2] != 0 && map[nR2][nC2] == idx) {
							break;
						}
						length++;
						bridgeIdx[nR2][nC2] = idx;
					}
				}
			}
		}
	} // end of func(setBridge)

	private static boolean[][] visited;
	private static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[][] queue;
	private static int front, rear;

	// 섬 외곽 찾기
	private static void findSideBydfs(int idx, int r, int c) {
		visited[r][c] = true;
		map[r][c] = idx;

		for (int i = 0; i < dir.length; i++) {
			int nR = r + dir[i][0];
			int nC = c + dir[i][1];

			if (!isRange(nR, nC)) {
				continue;
			}

			if (visited[nR][nC]) {
				continue;
			}

			if (map[nR][nC] == 0) {
				queue[++rear][0] = r;
				queue[rear][1] = c;
				queue[rear][2] = idx;
				map[r][c] = idx;
			}

			if (map[nR][nC] != 0) {
				findSideBydfs(idx, nR, nC);
			}
		}
		return;
	} // end of func(findSideBydfs)

	private static boolean isRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M) {
			return false;
		} else {
			return true;
		}
	} // end of func(isRange)

	// -- disjoint-set
	private static int[] p, rank;

	private static void makeSet(int n) {
		p = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
	} // end of func(makeSet)

	private static int find(int node) {
		if (p[node] == node) {
			return p[node];
		} else {
			return p[node] = find(p[node]);
		}
	} // end of func(find)

	private static void union(int node1, int node2) {
		int p1 = find(node1);
		int p2 = find(node2);

		if (p1 == p2) {
			return;
		} else {
			if (rank[p1] < rank[p2]) {
				p[p1] = p2;
			} else {
				p[p2] = p1;
				if (rank[p1] == rank[p2]) {
					rank[p2]++;
				}
			}
		}
	} // end of func(union)
		// -- end of disjoint-set

	private static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	} // end of Edge

	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}
		int i = first - 1;
		int j = last + 1;
		Edge pivot = edgeList.get((first + last) / 2);
		while (true) {
			while (edgeList.get(++i).weight < pivot.weight) {
			}
			while (edgeList.get(--j).weight > pivot.weight) {
			}
			if (i >= j) {
				break;
			}

			Edge tmp = edgeList.get(i);
			edgeList.set(i, edgeList.get(j));
			edgeList.set(j, tmp);
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);
	} // end of func(quickSort)

	private static void debug() {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
		System.out.println();
		System.out.println(rear);
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(bridgeIdx[r]));
		}
		System.out.println();
		for (int i = 0; i < connection.length; i++) {
			System.out.println(Arrays.toString(connection[i]));
		}
		System.out.println();
		for (int r = 0; r < edgeList.size(); r++) {
			System.out
					.print(edgeList.get(r).start + ", " + edgeList.get(r).end + ", " + edgeList.get(r).weight + " | ");
		}
		System.out.println();
	} // end of func(debug)
} // end of class
