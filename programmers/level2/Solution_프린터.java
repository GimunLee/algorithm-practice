package programmers.level2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_«¡∏∞≈Õ {
	public static void main(String[] args) {
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;
		System.out.println(solution(priorities, location));
	} // end of main

	public static int solution(int[] priorities, int location) {
		int answer = 0;

		Queue<Pair> que = new LinkedList<Pair>();

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < priorities.length; i++) {
			pq.add(priorities[i]);
			que.add(new Pair(i, priorities[i]));
		}

		int order = -1;
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			order++;
			while (!que.isEmpty()) {
				Pair print = que.poll();
				if (print.priority == cur) {
					if (print.idx == location) {
						return order + 1;
					}
					break;
				} else {
					que.add(print);
				}
			}
		}

		return answer;
	} // end of func(solution)

	private static class Pair {
		int idx, priority;

		public Pair(int idx, int priority) {
			this.idx = idx;
			this.priority = priority;
		}
	}

} // end of class
