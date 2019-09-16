package practiceb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_Day01_3820_롤러코스터 {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			ArrayList<Rail> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				list.add(new Rail(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
			}
			Collections.sort(list);
			long v = 1;
			for (int i = 0; i < N; i++) {
				Rail rail = list.get(i);
				v = ((rail.a % 1000000007 * v % 1000000007) % 1000000007 + (rail.b % 1000000007)) % 1000000007;
			}
			sb.append("#").append(tc).append(" ").append((int) v).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static class Rail implements Comparable<Rail> {
		long a, b;

		public Rail(long a, long b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Rail o) {
			double cur = (double) (this.a - 1) / (double) this.b;
			double next = (double) (o.a - 1) / (double) o.b;
			if (cur - next >= 0) {
				return -1;
			} else {
				return 1;
			}
		}
	}
} // end of class
