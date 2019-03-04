package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	static int[][] move = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우
	static int[][] map;
	static int[][] map_temp;
	static boolean[][] visited;
	static int[][] q = new int[1000][2]; // [0] : row, [1] : column
	static int front = -1;
	static int rear = -1;
	static int ans = 0;

	static int size_v;
	static int[] r_combi;
	static int[] c_combi;

	static int r, c;

	public static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r > (map.length - 1) || c > (map[0].length - 1)) {
			return false;
		}
		return true;
	}

	private static void bfs(int r, int c) {
		visited[r][c] = true;

		while (front != rear) {
			++front;
			r = q[front][0]; // 현 위치
			c = q[front][1];

			for (int i = 0; i < 4; i++) { // 방향
				int r_n = r + move[i][0];
				int c_n = c + move[i][1];

				if (inRange(r_n, c_n)) {
					if (!visited[r_n][c_n] && map_temp[r_n][c_n] == 0) { // 빈칸이면,
						visited[r_n][c_n] = true;
						map_temp[r_n][c_n] = 2;
						++rear;
						q[rear][0] = r_n;
						q[rear][1] = c_n;
					}
				}
			}
		}
	}

	static ArrayList<Integer> rList = new ArrayList<Integer>();
	static ArrayList<Integer> cList = new ArrayList<Integer>();

	static ArrayList<Integer> rList_t = new ArrayList<Integer>();
	static ArrayList<Integer> cList_t = new ArrayList<Integer>();

	static void combination(int size_combi, int cnt, int t) {
		if (cnt == 3) {
			map_temp = new int[r][c];

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					map_temp[i][j] = map[i][j];
				}
			}

			for (int i = 0; i < rList.size(); i++) {
				map_temp[rList.get(i)][cList.get(i)] = 1;
			}

//			if (rList.equals(rList_t) && cList.equals(cList_t)) {
//				System.out.println("=============================");
//				for (int i = 0; i < map.length; i++) {
//					System.out.println(Arrays.toString(map_temp[i]));
//				}
//			}

			visited = new boolean[r][c];
			front = -1;
			rear = size_v-1;

			for (int i = 0; i < size_v; i++) {
				int r_v = q[i][0];
				int c_v = q[i][1];

				if (!visited[r_v][c_v]) {
					bfs(r_v, c_v);
				}
			}

//			if (rList.equals(rList_t) && cList.equals(cList_t)) {
//				System.out.println("=============================");
//				for (int i = 0; i < map.length; i++) {
//					System.out.println(Arrays.toString(map_temp[i]));
//				}
//			}

			int temp = 0;

			for (int i = 0; i < map_temp.length; i++) {
				for (int j = 0; j < map_temp[0].length; j++) {
					if (map_temp[i][j] == 0) {
						temp++;
					}
				}
			}

			ans = Math.max(temp, ans);
			return;
		}

		for (int i = t; i < size_combi; i++) {
			rList.add(r_combi[i]);
			cList.add(c_combi[i]);
			combination(size_combi, cnt + 1, i + 1);
			rList.remove(rList.size() - 1);
			cList.remove(cList.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		int size = 0;

		// 빈칸을 담을 배열
		r_combi = new int[r * c];
		c_combi = new int[r * c];
		int size_combi = 0;

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					++rear;
					q[rear][0] = i;
					q[rear][1] = j;
					size_v++;
				} else if (map[i][j] == 0) {
					r_combi[size_combi] = i;
					c_combi[size_combi] = j;
					size_combi++;
				}
			}
		}
		
		// -- 조합 벽을 세운다.

		rList_t.add(0);
		rList_t.add(1);
		rList_t.add(3);

		cList_t.add(4);
		cList_t.add(3);
		cList_t.add(3);

		combination(size_combi, 0, 0);

		System.out.println(ans);
	}

}
