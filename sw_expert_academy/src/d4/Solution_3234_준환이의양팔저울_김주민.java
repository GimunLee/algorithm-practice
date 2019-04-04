package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3234_ÁØÈ¯ÀÌÀÇ¾çÆÈÀú¿ï_±èÁÖ¹Î {

	private static boolean[] visited;
	private static int[] arr;
	private static int N;
	private static int ans;
	private static int[][] v;

	public static int dfs(int depth, int right, int left, int lvisit, int rvisit) {
		if (depth == N) {
			return 1;
		}

		int ans = 0;

		if (v[lvisit][rvisit] != 0) {
			return v[lvisit][rvisit];
		}

		for (int i = 0; i < N; i++) {

			if (!visited[i]) {
				visited[i] = true;
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						int temp = right + arr[i];
						if (temp > left)
							continue;
						ans += dfs(depth + 1, temp, left, lvisit, (rvisit | (1 << i)));
					} else {
						ans += dfs(depth + 1, right, left + arr[i], (lvisit | (1 << i)), rvisit);
					}
				}
				visited[i] = false;
			}

		}

		v[lvisit][rvisit] = ans;
		return v[lvisit][rvisit];
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int test = 1; test <= TC; test++) {
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N];
			visited = new boolean[N];
			v = new int[1 << (N + 1)][1 << (N + 1)];
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);

			System.out.println("#" + test + " " + dfs(0, 0, 0, 0, 0));
		}
	}

}
