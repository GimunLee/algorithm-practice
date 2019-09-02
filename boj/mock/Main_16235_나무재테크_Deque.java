package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2019.09.01.(일)
 */
public class Main_16235_나무재테크_Deque {
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
		Deque<Tree>[][] treeMap = new LinkedList[N + 1][N + 1];

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
			treeMap[r][c] = new LinkedList<Tree>();
			treeMap[r][c].add(new Tree(r, c, age));
			treeCnt++;
		}
		// -- end of input
		int ANSER = solve(map, A, K, treeCnt, treeMap);
		System.out.println(ANSER);
	} // end of main

	private static int solve(int[][] map, int[][] A, int K, int treeCnt, Deque<Tree>[][] treeMap) {
		Queue<Tree> spreadQueue = new LinkedList<>();

		for (int k = 0; k < K; k++) {

			// 봄 (양분 먹기), 여름 (나무 죽이기)
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					if (treeMap[r][c] == null) { // 나무가 없다.
						continue;
					}
					int energy = map[r][c];
					int size = treeMap[r][c].size();
					for (int i = 0; i < size; i++) {
						Tree tree = treeMap[r][c].pollFirst();
						if (energy < tree.age) { // 나무 나이보다 양분이 적은 경우
							treeCnt--;
							if (treeCnt == 0) {
								return 0;
							}
							map[r][c] += (tree.age / 2);
							if (treeMap[r][c].size() == 0) {
								treeMap[r][c] = null;
								break;
							}
						} else {
							map[r][c] -= tree.age;
							energy -= tree.age;
							tree.age += 1;
							treeMap[r][c].addLast(tree);
							if (tree.age % 5 == 0) {
								spreadQueue.add(tree);
							}
						}
					}
				}
			}

			// 가을
			while (!spreadQueue.isEmpty()) {
				Tree tree = spreadQueue.poll();
				int r = tree.r;
				int c = tree.c;

				for (int d = 0; d < dr.length; d++) {
					int nR = r + dr[d];
					int nC = c + dc[d];

					if (nR <= 0 || nC <= 0 || nR >= map.length || nC >= map[0].length) {
						continue;
					}

					if (treeMap[nR][nC] == null) {
						treeMap[nR][nC] = new LinkedList<>();
					}
					treeCnt++;
					treeMap[nR][nC].addFirst(new Tree(nR, nC, 1));
				}
			}

			// 겨울
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					map[r][c] += A[r][c]; // (겨울) 양분 추가
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
