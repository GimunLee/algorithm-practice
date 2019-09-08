package swea.mock0907;

import java.util.ArrayList;
import java.util.Arrays;

public class PermuCombi {
	private static int[] input = { 1, 2, 3, 4 };

	public static void main(String[] args) {

		System.out.println("-- 조합 --");
		set = new int[3];
		combi(0, 0);
		System.out.println("-- 중복조합 --");
		set = new int[3];
		reduCombi(0, 0);
		System.out.println("-- 순열 --");
		set = new int[3];
		visited = new boolean[5];
		permu(0);
		System.out.println("-- 중복순열 --");
		set = new int[3];
		reduPermu(0);
		System.out.println("-- 부분집합 --");
		list = new ArrayList<Integer>();
		subset(0);
	}

	private static int[] set;
	private static ArrayList<Integer> list;

	private static void subset(int idx) {
		if (idx == input.length - 1) {
			return;
		}
		System.out.println(list);
		for (int i = idx; i < input.length; i++) {
			list.add(input[i]);
			subset(i + 1);
			list.remove(list.size() - 1);
		}
	}

	private static boolean[] visited;

	private static void permu(int len) {
		if (len == 3) {
			System.out.println(Arrays.toString(set));
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				set[len] = input[i];
				permu(len + 1);
				visited[i] = false;
			}
		}
	}

	private static void reduPermu(int len) {
		if (len == 3) {
			System.out.println(Arrays.toString(set));
			return;
		}

		for (int i = 0; i < input.length; i++) {
			set[len] = input[i];
			reduPermu(len + 1);
		}
	}

	private static void combi(int idx, int len) {
		if (len == 3) {
			System.out.println(Arrays.toString(set));
			return;
		}

		for (int i = idx; i < input.length; i++) {
			set[len] = input[i];
			combi(i + 1, len + 1);
		}
	}


	private static void reduCombi(int idx, int len) {
		if (len == 3) {
			System.out.println(Arrays.toString(set));
			return;
		}

		for (int i = idx; i < input.length; i++) {
			set[len] = input[i];
			reduCombi(i, len + 1);
		}
	}
}
