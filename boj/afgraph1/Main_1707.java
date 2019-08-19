package boj.afgraph1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1707 {
	static ArrayList<Integer>[] grapth;
	static boolean[] check;
	static int[] collective;
	static boolean ans;

	private static void dfs(int node) {
//		System.out.print("(" + node + ") ");
		check[node] = true;

		if (grapth[node] == null)
			return;

		for (int i = 0; i < grapth[node].size(); i++) {
			if (ans)
				return;
			
			int next = grapth[node].get(i);

			if (collective[next] == 0 && collective[node] == 1)
				collective[next] = 2;
			else if (collective[next] == 0 && collective[node] == 2)
				collective[next] = 1;

			if (collective[node] == collective[next]) { // 집합이 2개로 안 나뉘는 경우
				ans = true;
				return;
			}

			if (!check[next])
				dfs(next);
		}
		
		if(!ans) {
			return;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			ans = false;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int V = Integer.parseInt(st.nextToken()); // 정점의 개수
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수

			grapth = new ArrayList[V + 1];
			check = new boolean[V + 1];
			collective = new int[V + 1];
			
			
			for (int i = 1; i <= V; i++) {
				grapth[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				grapth[start].add(end);
				grapth[end].add(start);
			}

			for (int i = 1; i <= V; i++) {
				if (ans)
					break;
				if(collective[i] == 0) {
					collective[i] = 1;
					dfs(i);	
				}
			}

			if (!ans) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
