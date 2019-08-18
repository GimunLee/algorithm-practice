package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최소 스패닝 트리
 */
public class Solution_3124_최소스패닝트리 {
	static long Ans;
	static int[] p; // 부모를 저장할 배열
	static int[] rank; // 랭크를 저장할 배열, 높이나 레벨이라고 하기엔 애매함

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			Ans = 0;

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			p = new int[V + 1];
			rank = new int[V + 1];

			Edge[] ed = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				ed[i] = new Edge(start, end, weight);
			}

			Arrays.sort(ed);

			for (int i = 0; i < p.length; i++) {
				makeSet(i);
			}

			for (int i = 0; i < ed.length; i++) { // 가중치가 낮은 간선부터 선택하기
				Edge e = ed[i];

				if (findSet(e.a) != findSet(e.b)) { // 서로 다른 집합일 경우만 합치기
					unionSet(e.a, e.b);
					Ans += e.val;
				}
			}

			System.out.println("#" + tc + " " + Ans);
		}
	}

	/** 멤버 x를 포함하는 새로운 집합을 생성 */
	public static void makeSet(int x) {
		p[x] = x; // 부모 : 자신의 index로 표시 or -1
//		rank[x] = 0; // 초기값 0임 // 생략 가능
	}

	/** 멤버 x를 포함하는 집합의 대표자를 리턴 */
	public static int findSet(int x) {
		if (x == p[x]) {
			return x;
		} else {
			p[x] = findSet(p[x]); // Path Compression
//			rank[x] = 1; // 할 필요가 없다. 대표자의 깊이(랭크)만 알면 된다.
			return p[x];
		}
	}

	/** 멤버 x,y의 대표자를 구해서 두 집합을 통합 */
	public static void unionSet(int x, int y) {
		int px = findSet(x); // 대표자 알아오기
		int py = findSet(y);

		if (px != py) { // 서로 다른 집합일 경우만 합쳐야한다. 무한루프가 돔
//			p[py] = px; // 두 집합의 대표자를 합치기
			link(px, py);
		}
	}

	/** x의 대표자의 집합과 y의 대표자의 집합을 합침, rank도 맞춤 */
	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px; // 작은 쪽을 큰 쪽에 붙인다
		} else {
			p[px] = py;
			if (rank[px] == rank[py]) { // 같은 경우는 rank 값이 증가한다.
				rank[py]++; // 집합의 대표자 랭크가 증가됨
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int a; // 정점1
		int b; // 정점2
		int val; // 가중치

		public Edge(int a, int b, int val) {
			this.a = a;
			this.b = b;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) { // 비교 기준
			return this.val - o.val;
		}
	}
} // end of class
