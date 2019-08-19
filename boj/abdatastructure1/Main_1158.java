package boj.abdatastructure1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] queue = new int[N + 1];
		boolean[] chk = new boolean[N + 1];
		int front = 0;
		for (int i = 1; i < queue.length; i++) {
			queue[i] = i;
		}
		int[] Answer = new int[N + 1];
		int index = 0;

		while (index != N) { // 정답을 다 채운 후 끝
			for (int i = 1; i <= M; i++) {
				++front;
				if (front > N) {
					front = 1;
				}
				while (chk[queue[front]]) {
					++front;
					if (front > N) {
						front = 1;
					}
				}
			}
			chk[queue[front]] = true;
			Answer[index++] = queue[front];
		}

		System.out.print("<");
		for (int i = 0; i < N; i++) {
			System.out.print(Answer[i]);
			if (i == N - 1) {
				System.out.println(">");
			} else {
				System.out.print(", ");
			}
		}
	}
}
