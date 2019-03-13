package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree> {
	int r;
	int c;
	int age;

	Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public int compareTo(Tree arg0) {
		return this.age - arg0.age;
	}
}

public class Main_16235_나무재테크 {
	static int N; // N*N의 크기
	static int M; // 구매하는 묘목
	static int K; // 지나는 년수
	static int[][] dxy = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()) + 1; // N*N의 크기
		M = Integer.parseInt(st.nextToken()); // 구매하는 묘목
		K = Integer.parseInt(st.nextToken()); // 지나는 년수

		ArrayList<Tree>[][] map = new ArrayList[N][N]; // 묘목판
		int[][] A = new int[N][N]; // 위치의 양분 정보를 저장할 배열

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				A[i][j] = 5;
			}
		}

		int A_value = Integer.parseInt(br.readLine().trim()); // 양분의 양

		boolean[][] visited = new boolean[N][N]; // 방문 check

		ArrayList<Tree> tp_list = new ArrayList<Tree>(); // 묘목이 심어진 곳
		ArrayList<Tree> dead_list = new ArrayList<Tree>(); // 죽은 나무들을 저장

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());

			if (map[r][c] == null) {
				map[r][c] = new ArrayList<Tree>();
			}

			map[r][c].add(new Tree(r, c, age));
			tp_list.add(new Tree(r, c, age));
		}

		for (int i = 0; i < K; i++) {
			// 봄 : 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 여러 개의 나무가 있을시, 나이가 어린 나무가 먹고 큰다.
			for (int j = 0; j < tp_list.size(); j++) {
				Tree tp = tp_list.get(j);
				if (map[tp.r][tp.c].size() > 1) {
					Collections.sort(map[tp.r][tp.c]); // 나이가 어린 우선
					for (int k = 0; k < map[tp.r][tp.c].size(); k++) {
						System.out.println("** " + k);
						System.out.println(tp.r + ", " + tp.c + " | " + map[tp.r][tp.c].get(k).age);
					}
				}
				Tree t = map[tp.r][tp.c].get(0);
				if (t.age > A[t.r][t.c]) { // 즉사
					dead_list.add(t);
					map[tp.r][tp.c].remove(t);
					tp_list.remove(t);

					System.out.println(tp_list.size());

				} else { // 영양분 먹기
					A[t.r][t.c] -= t.age++;
					visited[t.r][t.c] = true;
					tp_list.remove(t);
				}
			} // end of for of spring

			// 여름 : 죽은 나무의 나이의 나누기 2
			for (int j = 0; j < dead_list.size(); j++) {
				Tree dt = dead_list.get(j);
				A[dt.r][dt.c] += (dt.age / 2);
			} // end of for of summer

			// 가을 : 나무가 번식한다. 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생김
			for (int j = 0; j < tp_list.size(); j++) {
				Tree t = tp_list.get(j);
				if (t.age % 5 == 0) {
					for (int k = 0; k < dxy.length; k++) {
						int next_r = t.r + dxy[k][0];
						int next_c = t.c + dxy[k][0];
						if (inRange(next_r, next_c)) {
							if (map[next_r][next_c] == null) {
								map[next_r][next_c] = new ArrayList<Tree>();
							}
							map[next_r][next_c].add(new Tree(next_r, next_c, 1)); // 추가
							tp_list.add(new Tree(next_r, next_c, 1));
						}
					}
				}
			} // end of for of fall

			// 겨울 : S2D2가 양분을 추가한다.
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					A[j][k] += A_value;
				}
			} // end of for of winter
		} // end of for of Year

		System.out.println(tp_list.size());
	} // end of main

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return false;
		}
		return true;
	}
} // end of class
