package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_10974_모든순열 {
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // N : 숫자의 끝 수

		result = new int[N + 1];
		boolean[] visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			System.out.println("밖 전 : " + i);
			DFS(visited, N, i, 0);
			System.out.println("밖 후 : " + i);

			visited[i] = false;
		}
	}

	public static void DFS(boolean[] visited, int N, int start, int depth) {
		result[depth] = start;
		if (depth == N - 1) {
			for (int i = 0; i < N; i++)
				System.out.print(result[i] + " ");
			System.out.println();
			System.out.println("*********************************");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			System.out.println("안 전 : " + i + " | " + depth);
			DFS(visited, N, i, depth+1);
			System.out.println("안 후 : " + i + " | " + depth);
			visited[i] = false;
		}
	}
}
