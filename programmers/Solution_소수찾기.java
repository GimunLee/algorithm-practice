package programmers;

import java.util.HashSet;
import java.util.Iterator;

public class Solution_소수찾기 {
	public static void main(String[] args) {
		String numbers = "011";
		System.out.println(solution(numbers));
	}

	public static int solution(String numbers) {
		int answer = 0;

		input = numbers.toCharArray();
		visited = new boolean[numbers.length()];
		sb = new StringBuilder();
		hashSet = new HashSet<Integer>();
		for (int i = 1; i <= input.length; i++) {
			permu(0, i);
		}

		Iterator<Integer> it = hashSet.iterator();

		while (it.hasNext()) {
			boolean flag = false;
			int num = it.next();
			if (num == 1 || num == 0) {
				continue;
			}
			for (int i = 2; i < num; i++) {
				if (num % i == 0) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				answer++;
			}
		}
		return answer;
	}

	private static boolean[] visited;
	private static StringBuilder sb;
	private static char[] input;
	private static HashSet<Integer> hashSet;

	private static void permu(int len, int limit) {
		if (len == limit) {
			hashSet.add(Integer.parseInt(sb.toString()));
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sb.append(input[i]);
				permu(len + 1, limit);
				visited[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}
