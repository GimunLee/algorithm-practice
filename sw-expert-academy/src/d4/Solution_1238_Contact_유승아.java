package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA 1238 Contact
//2019-03-12
public class Solution_1238_Contact_유승아 {
	static int[][] map;
	static int start;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 10; tc++) {
			map = new int[101][101];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N / 2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
			}
			bfs(tc);
		}
	}// end of main

	public static void bfs(int tc) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> beforelist = null;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[101];
		q.add(start);
		visit[start] = true;
		while (!q.isEmpty()) {
			int qSize = q.size();

			beforelist = new ArrayList<>(list);
			list.clear();
			System.out.println(beforelist);
			for (int t = 0; t < qSize; t++) {
				int a = q.poll();

				for (int i = 1; i < map[a].length; i++) {
					if (map[a][i] == 1 && !visit[i]) {
						q.add(i);
						visit[i] = true;
						list.add(i);
					}
				}
			} // 시간당 연락받는 사람
				// System.out.println(list);

		}

		beforelist.sort(null);
		System.out.println("#" + tc + " " + beforelist.get(beforelist.size() - 1));
	}
}