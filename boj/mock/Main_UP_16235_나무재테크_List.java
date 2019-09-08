package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 2019.09.01.(일)
 */
public class Main_UP_16235_나무재테크_List {
	private static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 맵의 크기
		int M = Integer.parseInt(st.nextToken()); // 나무의 수
		int K = Integer.parseInt(st.nextToken()); // K년 후

		int[][] map = new int[N + 1][N + 1];
		int[][] A = new int[N + 1][N + 1];

		ArrayList<Tree>[][] treeMap = new ArrayList[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = 5; // 최초의 양분은 5
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int treeCnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeMap[r][c] = new ArrayList<Tree>();
			treeMap[r][c].add(new Tree(r, c, age));
			treeCnt++;
		}
		// -- end of input

		int ANSER = solve(map, A, K, treeCnt, treeMap);

		System.out.println(ANSER);
	} // end of main

	private static int solve(int[][] map, int[][] A, int K, int treeCnt, ArrayList<Tree>[][] treeMap) {
		for (int k = 0; k < K; k++) {
			// 봄 (양분 먹기)

			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					if (treeMap[r][c] != null) { // 나무가 있다. 어린 나무부터 먹기
						if (treeMap[r][c].size() != 1) { // 여러나무가 있는 경우
							Collections.sort(treeMap[r][c]);
						}
						int energy = map[r][c];
						for (int i = 0; i < treeMap[r][c].size(); i++) {
							if (energy < treeMap[r][c].get(i).age) { // 나무 나이보다 양분이 적은 경우
								treeCnt--;
								map[r][c] += (treeMap[r][c].get(i).age / 2);
								treeMap[r][c].remove(treeMap[r][c].get(i));
								if (treeMap[r][c].size() == 0) {
									treeMap[r][c] = null;
									break;
								}
								i--;
							} else {
								map[r][c] -= treeMap[r][c].get(i).age;
								energy -= treeMap[r][c].get(i).age;
								treeMap[r][c].get(i).age += 1; // 나이먹기
							}
						}
					}
				}
			}
			if (treeCnt == 0) {
				return 0;
			}

			// 가을 (나무 번식함 5의 배수인 경우에만)
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					map[r][c] += A[r][c]; // (겨울) 양분 추가
					if (treeMap[r][c] != null) { // 나무가 있는 경우
						for (int i = 0; i < treeMap[r][c].size(); i++) {
							if (treeMap[r][c].get(i).age % 5 == 0) {
								for (int d = 0; d < dr.length; d++) {
									int nR = treeMap[r][c].get(i).r + dr[d];
									int nC = treeMap[r][c].get(i).c + dc[d];

									if (nR <= 0 || nC <= 0 || nR >= map.length || nC >= map[0].length) {
										continue;
									}

									if (treeMap[nR][nC] == null) {
										treeMap[nR][nC] = new ArrayList<>();
									}
									treeCnt++;
									treeMap[nR][nC].add(new Tree(nR, nC, 1));
								}
							}

						}
					}
				}
			}
		}
		return treeCnt;
	} // end of func(solve)

	private static void print(ArrayList<Tree>[][] treeMap) {
		System.out.println();
		for (int r = 1; r < treeMap.length; r++) {
			for (int c = 1; c < treeMap.length; c++) {
				if (treeMap[r][c] != null) {
					System.out.print(treeMap[r][c].size() + " ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}

	}

	private static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;

		public Tree(Tree tree) {
			this.r = tree.r;
			this.c = tree.c;
			this.age = tree.age;
		}

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	} // end of Tree
} // end of class
