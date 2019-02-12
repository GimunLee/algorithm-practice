package d5;

import java.util.Scanner;
/**
 * 
 */
public class Solution_1247 {
	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Position[] position;
	static int N;
	static int Answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int tc = 1; tc <= C; tc++) {
			N = sc.nextInt() + 2;
			position = new Position[N];
			int x, y;
			for (int i = 0; i < N; i++) {
				x = sc.nextInt();
				y = sc.nextInt();
				position[i] = new Position(x, y);
			}
			Answer = Integer.MAX_VALUE;

			solve(2);
			System.out.println("#" + tc + " " + Answer);
		}
	}

	static void solve(int n) {
		if (n == N) {
			int dis = 0;

			dis += Math.abs(position[0].x - position[2].x) + Math.abs(position[0].y - position[2].y)
					+ Math.abs(position[1].x - position[N - 1].x) + Math.abs(position[1].y - position[N - 1].y);

			for (int i = 2; i < N - 1; i++)
				dis += Math.abs(position[i].x - position[i + 1].x) + Math.abs(position[i].y - position[i + 1].y);
			Answer = Math.min(dis, Answer);
			return;
		}
		for (int i = n; i < N; i++) {
			Position temp = position[n];
			position[n] = position[i];
			position[i] = temp;

			solve(n + 1);

			temp = position[n];
			position[n] = position[i];
			position[i] = temp;
		}
	}
}