package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1. BFS 2. 플로이드워샬 (그래프 간의 최단 거리를 인접행렬로 만든다.) 2-1. 개선 -> 메모이제이션 -> DP
 *
 * 부모를 저장한다.
 * 
 */

public class Main_2462_키순서_Prof {
	private static int[][] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 학생들 수, 2 <= N <= 500
		int M = Integer.parseInt(st.nextToken()); // 비교 횟수, 2 <= M <= N*(N-1)/2

		a = new int[N + 1][N + 1]; // 1 ~ N 정점만 활용, 인접 행렬

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int small = Integer.parseInt(st.nextToken()); // 작은
			int tall = Integer.parseInt(st.nextToken()); // 큰
			a[small][tall] = 1;
		}

		// 인접 행렬에서 사용하지 않은 0 빈칸을 플래그 변수로 활용하겠음
		for (int i = 0; i < a.length; i++) {
			a[i][0] = -1; // 초기화
		}

		int totalN = 0; // 순서를 정확히 알게되는 학생 인원 수

//		System.out.println("순회하기 전");
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}

		for (int i = 1; i < a.length; i++) {
			up(i); // dfs로 순회
		}
		// 나보다 키가 작은 아이들, 큰 아이들의 합을 구해서 N-1이면 순서를 정확히 알 수 있는 사람임
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a.length; j++) {
				a[0][i] += a[j][i]; // 정점 i로 진입하는 정점들의 개수를 저장
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (a[0][i] + a[i][0] == N - 1) {
				totalN++;
			}
		}

//		System.out.println("순회한 후");
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}

		System.out.println(totalN);
	} // end of main

	public static void up(int v) {
		if (a[v][0] != -1) { // 정점 v에 대해서 부모를 체크해둔 상태라면
			return;
		}

		// 현재 v정점의 부모가 누구인지를 인접행렬에 저장
		for (int i = 1; i < a.length; i++) {
			if (a[v][i] == 1) {
				up(i); // 재귀 호출
				for (int j = 1; j < a.length; j++) {
					a[v][j] |= a[i][j];
				}
			}
		}

		// 인접행렬의 0번째 칸은 v정점의 부모의 개수 몇개인지 저장해두자
		int sum = 1;
		for (int j = 0; j < a.length; j++) {
			sum += a[v][j]; // 인접한 부모는 1, 인접안했으면 0이니까. 싹 더하면, 부모의 개수가 됨
		}
		a[v][0] = sum;
	}
} // end of class
