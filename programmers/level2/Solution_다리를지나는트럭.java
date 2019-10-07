package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_다리를지나는트럭 {
	public static void main(String[] args) {
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		int answer = solution(bridge_length, weight, truck_weights);
		System.out.println(answer);
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Truck> que = new LinkedList<>();
		que.add(new Truck(truck_weights[0], 0));
		int sum = truck_weights[0];
		int index = 1;
		while (!que.isEmpty()) {
			answer++;
			int size = que.size();
			for (int i = 0; i < size; i++) {
				Truck truck = que.poll();
				truck.position++;
				if (truck.position == bridge_length) {
					sum -= truck.weight;
					continue;
				}
				que.add(truck);
			}

			if (index < truck_weights.length && sum + truck_weights[index] <= weight) { // 수용 가능
				sum += truck_weights[index];
				que.add(new Truck(truck_weights[index], 0));
				index++;
			}
		}

		return answer + 1;
	}

	private static class Truck {
		int weight, position;

		public Truck(int weight, int position) {
			this.weight = weight;
			this.position = position;
		}
	}
}
