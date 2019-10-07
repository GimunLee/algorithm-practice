package programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_기능개발 {
	public static void main(String[] args) {
		int[] proresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };
		int[] answer = solution(proresses, speeds);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<Work> que = new LinkedList<>();
		for (int i = 0; i < progresses.length; i++) {
			que.add(new Work(progresses[i], speeds[i]));
		}
		Queue<Work> remainQue = new LinkedList<>();
		while (!que.isEmpty()) {
			int tmp = 0;
			while (!que.isEmpty()) {
				Work work = que.poll();
				work.progress += work.speed;
				if (work.progress >= 100 && remainQue.isEmpty()) {
					tmp++;
				} else {
					remainQue.add(work);
				}
			}

			if (tmp != 0) {
				list.add(tmp);
			}

			while (!remainQue.isEmpty()) {
				que.add(remainQue.poll());
			}

		}

		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}

	private static class Work {
		int progress, speed;

		public Work(int progress, int speed) {
			this.progress = progress;
			this.speed = speed;
		}
	}
}
