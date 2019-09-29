package programmers.level2;

import java.util.PriorityQueue;

public class Solution_´õ¸Ê°Ô {
	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 10000;

		System.out.println(solution(scoville, K));

	}

	public static int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < scoville.length; i++) {
			queue.add(scoville[i]);
		}
		boolean flag = false;
		while (!queue.isEmpty()) {
			int food1 = queue.poll();
			if (food1 >= K) {
				flag = true;
				break;
			}
			answer++;
			if (queue.isEmpty()) {
				break;
			}
			int food2 = queue.poll() * 2;
			int sum = food1 + food2;
			queue.add(sum);
		}
		if (!flag) {
			answer = -1;
		}
		return answer;
	}
}
