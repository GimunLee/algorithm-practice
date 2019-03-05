import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1863_Á¾±³_DisjointSet {
	static int[] p;
	static int[] rank;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		rank = new int[p.length];

		ans = N;

		for (int i = 1; i < N + 1; i++) {
			makeSet(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			unionSet(start, end);
		}

		System.out.println(ans);
	} // end of main

	public static void makeSet(int x) {
		p[x] = x;
	}

	public static int findSet(int x) {
		if (x == p[x]) {
			return x;
		} else {
			p[x] = findSet(p[x]);
			return p[x];
		}
	}

	public static void unionSet(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px != py) {
			link(px, py);
			ans--;
		}
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px;
		} else {
			p[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
} // end of class
