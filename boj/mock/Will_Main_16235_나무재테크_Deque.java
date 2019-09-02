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
 * 2019.09.02.(월) Upload
 */
public class Will_Main_16235_나무재테크_Deque {
	// 인접한 8칸을 접근할 때 사용할 배열
	private static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 }; // 행 (row)
	private static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 }; // 열 (column)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 맵의 크기
		int M = Integer.parseInt(st.nextToken()); // 나무의 수
		int K = Integer.parseInt(st.nextToken()); // K년 후

		int[][] map = new int[N + 1][N + 1]; // map 생성 (좌표가 +1 씩 들어오기 떄문에 N+1만큼 생성)
		int[][] A = new int[N + 1][N + 1]; // A 생성 (좌표가 +1 씩 들어오기 떄문에 N+1만큼 생성)

		// 앞에 넣고, 뒤에 넣기 좋은 자료구조 Deque 사용
		Deque<Tree>[][] treeMap = new LinkedList[N + 1][N + 1]; // 좌표마다 나무를 Deque로 저장

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = 5; // 최초의 양분은 5
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int treeCnt = 0; // 나무의 갯수
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

		System.out.println(ANSER); // 정답 출력
	} // end of main

	private static int solve(int[][] map, int[][] A, int K, int treeCnt, Deque<Tree>[][] treeMap) {

		Queue<Tree> spreadQueue = new LinkedList<>(); // 가을에 번식하는 나무를 저장
		for (int k = 0; k < K; k++) { // K년만큼 반복

			// 봄 (양분 먹고 나이 먹기), 여름 (죽은 나무 양분으로 만들기) 한번에 처리
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					if (treeMap[r][c] == null) { // 나무가 없는 좌표이므로, continue
						continue;
					}

					int energy = map[r][c]; // 땅에 있는 양분을 임시 저장 (여름을 한번에 처리하기 때문에)
					int size = treeMap[r][c].size(); // 나무의 갯수를 미리 저장

					for (int i = 0; i < size; i++) { // 나무의 갯수만큼 탐색
						Tree tree = treeMap[r][c].pollFirst(); // 나이순이므로 앞에서부터 빼봄
						if (energy < tree.age) { // 나무 나이보다 양분이 적은 경우
							treeCnt--; // 나무는 죽음
							if (treeCnt == 0) { // 앞으로 탐색해야할 나무가 없다면 0 반환
								return 0;
							}
							map[r][c] += (tree.age / 2); // 양분을 늘려줌
							if (treeMap[r][c].size() == 0) { // 해당 위치에 나무가 한개도 없다면 null로 만들어줌
								treeMap[r][c] = null;
								break;
							}
						} else {
							map[r][c] -= tree.age; // 양분 먹기
							energy -= tree.age; // 양분 먹기
							tree.age += 1; // 나무 나이 먹기
							treeMap[r][c].addLast(tree); // 나무를 뒤부터 저장 (따로 정렬하지 않아도 됨)
							if (tree.age % 5 == 0) { // 5의 배수라면
								spreadQueue.add(tree); // 가을에 번식해야하기 위해 Queue에 저장
							}
						}
					}
				}
			} // end of (Spring & Summer)

			// 가을 (나무 번식하기)
			while (!spreadQueue.isEmpty()) {
				Tree tree = spreadQueue.poll();
				int r = tree.r;
				int c = tree.c;

				for (int d = 0; d < dr.length; d++) { // 인접한 8칸 번식
					int nR = r + dr[d];
					int nC = c + dc[d];

					if (nR <= 0 || nC <= 0 || nR >= map.length || nC >= map[0].length) {
						continue;
					}

					if (treeMap[nR][nC] == null) { // 해당 자리에 나무가 한그루도 없었다면
						treeMap[nR][nC] = new LinkedList<>();
					}
					treeCnt++; // 나무 갯수 올리기
					treeMap[nR][nC].addFirst(new Tree(nR, nC, 1)); // 1살이이므로 앞에 추가 (따로 졍렬하지 않아도됨)
				}
			} // end of (Fall)

			// 겨울
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					map[r][c] += A[r][c]; // 양분 추가
				}
			} // end of (Winter)

		}
		return treeCnt; // K년 후 나무의 갯수 반환
	} // end of func(solve)

	/** 디버깅용 print 함수 */
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
	} // end of func(print)

	private static class Tree {
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
	} // end of Tree
} // end of class
