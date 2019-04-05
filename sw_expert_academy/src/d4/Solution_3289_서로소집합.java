package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 상호베타 집합 => MST (크루스칼 알고리즘에 활용) / 프림 < 크루스칼 makeSet(int x) 멤버 x를 포함하는 새로운 집합을
 * 생성 findSet(int x) 멤버 x를 포함하는 집합의 대표자를 리턴 union(int x, int y) 멤버 x,y 의 대표자를
 * 구해서 두 집합을 통합 link(int px, int py) x의 대표자의 집합과 y의 대표자의 집합을 합침
 */
public class Solution_3289_서로소집합 {
	static int[] p; // 부모를 저장할 배열
	static int[] rank; // 랭크를 저장할 배열, 높이나 레벨이라고 하기엔 애매함

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			p = new int[n + 1];
			rank = new int[n + 1];

			for (int j = 1; j <= n; j++) {
				makeSet(j);
			}

			String ans = "";
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int chk = Integer.parseInt(st.nextToken());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());

				if (chk == 0) { // 합집합
					unionSet(num1, num2);
				} else { // 검사
					if (findSet(num1) == findSet(num2)) {
						ans += "1";
					} else {
						ans += "0";
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.print(sb.toString());
	} // end of main

	/** p 배열 출력 */
	public static void printSet() {
		System.out.println();
		System.out.print("index : ");
		for (int i = 0; i < p.length; i++) {
			System.out.printf("%2d ", i);
		}
		System.out.print("\nparent: ");
		for (int i = 0; i < p.length; i++) {
			System.out.printf("%2d ", p[i]);
		}
		System.out.println();
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

} // end of class
